package com.playwright.mcp.browser;

import com.microsoft.playwright.*;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Browser session that survives MCP server restarts AND browser crashes.
 *
 * On first call the manager launches Chromium as an independent OS process with
 * --remote-debugging-port={browser.cdp.port} and a fixed --user-data-dir, then
 * connects via CDP. On MCP server restarts the browser is still running so it
 * reconnects. If the browser process itself dies (e.g. killed externally), the
 * manager detects via browser.isConnected() and re-launches automatically.
 *
 * @PreDestroy calls playwright.close() (disconnects the driver), but does NOT
 * kill the browser process — that is the key to session persistence.
 */
@Component
public class BrowserSessionManager {

    private static final Logger log = LoggerFactory.getLogger(BrowserSessionManager.class);

    @Value("${browser.headless:false}")        private boolean headless;
    @Value("${browser.timeout:30000}")         private int defaultTimeout;
    @Value("${browser.viewport.width:1280}")   private int viewportWidth;
    @Value("${browser.viewport.height:720}")   private int viewportHeight;
    @Value("${browser.cdp.port:9222}")         private int cdpPort;
    @Value("${browser.user.data.dir:}")        private String userDataDir;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    private final List<Page> pages = new ArrayList<>();
    private volatile int activeIndex = 0;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final Object lock = new Object();
    private Thread daemonThread;

    // -------------------------------------------------------------------------
    // Public API
    // -------------------------------------------------------------------------

    /** Returns the currently active page, creating one if none exist. Reconnects if browser died. */
    public Page getPage() {
        ensureInitialized();
        synchronized (lock) {
            reconnectIfNeeded();
            pruneClosedPages();
            if (pages.isEmpty()) {
                pages.add(newPage());
                activeIndex = 0;
            }
            return pages.get(activeIndex);
        }
    }

    /** Opens a new tab, navigates to url if provided, and makes it active. Reconnects if browser died. */
    public Page newTab(String url) {
        ensureInitialized();
        synchronized (lock) {
            reconnectIfNeeded();
            Page p = newPage();
            if (url != null && !url.isBlank()) {
                p.navigate(url, new Page.NavigateOptions()
                        .setWaitUntil(com.microsoft.playwright.options.WaitUntilState.LOAD));
            }
            pages.add(p);
            activeIndex = pages.size() - 1;
            return p;
        }
    }

    /** Returns a snapshot of all open tabs. */
    public List<TabInfo> listTabs() {
        ensureInitialized();
        synchronized (lock) {
            pruneClosedPages();
            List<TabInfo> result = new ArrayList<>(pages.size());
            for (int i = 0; i < pages.size(); i++) {
                Page p = pages.get(i);
                result.add(new TabInfo(i, p.url(), p.title(), i == activeIndex));
            }
            return result;
        }
    }

    /** Switches the active tab to index. */
    public void switchTab(int index) {
        synchronized (lock) {
            pruneClosedPages();
            if (index < 0 || index >= pages.size())
                throw new IllegalArgumentException(
                        "Tab index " + index + " out of range [0-" + (pages.size() - 1) + "]");
            activeIndex = index;
        }
    }

    /** Closes the tab at index; opens a blank tab if it was the last one. */
    public String closeTab(int index) {
        synchronized (lock) {
            pruneClosedPages();
            if (index < 0 || index >= pages.size())
                throw new IllegalArgumentException(
                        "Tab index " + index + " out of range [0-" + (pages.size() - 1) + "]");
            String url = pages.get(index).url();
            pages.get(index).close();
            pages.remove(index);
            if (pages.isEmpty()) pages.add(newPage());
            activeIndex = Math.min(activeIndex, pages.size() - 1);
            return url;
        }
    }

    /**
     * Resets the session: closes all current pages and opens a fresh one.
     * In CDP mode we reuse the same context (can't create new ones), so
     * cookies are cleared via the browser API. Reconnects if browser died.
     */
    public void resetSession() {
        ensureInitialized();
        synchronized (lock) {
            log.info("Resetting browser session");
            pages.forEach(p -> { try { p.close(); } catch (Exception ignored) {} });
            pages.clear();
            reconnectIfNeeded();
            try { context.clearCookies(); } catch (Exception ignored) {}
            pages.add(newPage());
            activeIndex = 0;
            log.info("Browser session reset complete");
        }
    }

    public boolean isReady() {
        return initialized.get() && browser != null && browser.isConnected();
    }

    // -------------------------------------------------------------------------
    // Initialisation
    // -------------------------------------------------------------------------

    private void ensureInitialized() {
        if (initialized.get()) return;
        synchronized (lock) {
            if (initialized.get()) return;
            launch();
            startDaemonThread();
            initialized.set(true);
        }
    }

    /**
     * Must be called while holding {@code lock}.
     * If the browser process has died (e.g. killed by an external process), tears
     * down the dead Playwright instance and relaunches / reconnects the browser so
     * the next tool call succeeds without requiring an MCP server restart.
     */
    private void reconnectIfNeeded() {
        if (browser != null && browser.isConnected()) return;

        log.warn("Browser connection lost — relaunching browser daemon (CDP port {})", cdpPort);
        // Tear down the dead connection cleanly
        try { if (playwright != null) playwright.close(); } catch (Exception ignored) {}
        playwright = null;
        browser = null;
        context = null;
        pages.clear();
        activeIndex = 0;

        // Re-initialise: try to reattach to a surviving CDP process first, then launch fresh
        playwright = Playwright.create();
        if (!tryConnectCDP()) {
            launchBrowserDaemon();
            waitForCDPReady();
            if (!tryConnectCDP()) {
                throw new IllegalStateException(
                        "Failed to reconnect to browser via CDP on port " + cdpPort);
            }
        }
        log.info("Browser reconnected successfully");
    }

    private void launch() {
        log.info("Browser session manager starting (CDP port {})", cdpPort);
        playwright = Playwright.create();

        // Try to reconnect to an already-running browser from a previous session
        if (tryConnectCDP()) {
            log.info("Reconnected to existing browser — session preserved");
            return;
        }

        // No browser running — start one as an independent OS process
        log.info("No browser on port {} — launching new browser daemon", cdpPort);
        launchBrowserDaemon();
        waitForCDPReady();

        if (!tryConnectCDP()) {
            throw new IllegalStateException(
                    "Failed to connect to browser via CDP on port " + cdpPort);
        }
    }

    /**
     * Attempts to connect to an already-running Chromium via CDP.
     * Returns true and sets up context/pages on success; returns false on failure.
     */
    private boolean tryConnectCDP() {
        try {
            Browser connected = playwright.chromium().connectOverCDP("http://localhost:" + cdpPort);
            this.browser = connected;

            List<BrowserContext> contexts = browser.contexts();
            if (!contexts.isEmpty()) {
                context = contexts.get(0);
            } else {
                context = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(viewportWidth, viewportHeight)
                        .setIgnoreHTTPSErrors(true));
            }
            context.setDefaultTimeout(defaultTimeout);

            // Restore existing pages, or open a fresh one
            List<Page> existing = context.pages();
            pages.clear();
            if (!existing.isEmpty()) {
                pages.addAll(existing);
                activeIndex = pages.size() - 1;
                log.info("Restored {} existing page(s) from browser session", pages.size());
            } else {
                pages.add(newPage());
                activeIndex = 0;
            }
            return true;

        } catch (Exception e) {
            log.debug("CDP not available on port {}: {}", cdpPort, e.getMessage());
            return false;
        }
    }

    /**
     * Launches Chromium as an independent OS process (not owned by Playwright).
     * The process outlives this JVM, enabling session persistence across restarts.
     * A dedicated --user-data-dir isolates this browser from system Chrome instances
     * (e.g. ChromeDriver sessions used by test automation on the same machine).
     */
    private void launchBrowserDaemon() {
        String execPath = playwright.chromium().executablePath();
        log.info("Chromium executable: {}", execPath);

        // Resolve user-data-dir: prefer explicit config, fall back to a fixed dir
        // under the user home so the profile persists across restarts.
        String dataDir = (userDataDir != null && !userDataDir.isBlank())
                ? userDataDir
                : System.getProperty("user.home") + "/playwright-mcp-profile";
        log.info("Browser user-data-dir: {}", dataDir);

        List<String> cmd = new ArrayList<>();
        cmd.add(execPath);
        cmd.add("--remote-debugging-port=" + cdpPort);
        cmd.add("--remote-debugging-address=127.0.0.1");
        cmd.add("--user-data-dir=" + dataDir);
        cmd.add("--no-first-run");
        cmd.add("--no-default-browser-check");
        cmd.add("--disable-extensions");
        cmd.add("--disable-translate");
        cmd.add("--disable-sync");
        cmd.add("--disable-background-networking");
        if (headless) {
            cmd.add("--headless=new");
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.redirectErrorStream(true);
            pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            Process proc = pb.start();
            log.info("Browser daemon started (PID {})", proc.pid());
        } catch (IOException e) {
            throw new RuntimeException("Failed to start browser daemon: " + e.getMessage(), e);
        }
    }

    /** Polls http://localhost:{cdpPort}/json/version until Chrome CDP is ready. */
    private void waitForCDPReady() {
        long deadline = System.currentTimeMillis() + 15_000;
        while (System.currentTimeMillis() < deadline) {
            try {
                URI uri = URI.create("http://localhost:" + cdpPort + "/json/version");
                HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
                conn.setConnectTimeout(500);
                conn.setReadTimeout(500);
                int code = conn.getResponseCode();
                conn.disconnect();
                if (code == 200) {
                    log.info("CDP ready on port {}", cdpPort);
                    return;
                }
            } catch (Exception ignored) {}
            try { Thread.sleep(300); } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        log.warn("CDP on port {} did not become ready within 15 s", cdpPort);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private Page newPage() {
        Page p = context.newPage();
        p.setDefaultTimeout(defaultTimeout);
        p.setViewportSize(viewportWidth, viewportHeight);
        return p;
    }

    private void pruneClosedPages() {
        pages.removeIf(Page::isClosed);
        if (activeIndex >= pages.size()) {
            activeIndex = Math.max(0, pages.size() - 1);
        }
    }

    private void startDaemonThread() {
        daemonThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) Thread.sleep(60_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "browser-daemon");
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    /**
     * Called when the MCP server stops (rebuild, restart, etc.).
     * Closes the Playwright driver but does NOT kill the browser process —
     * the browser stays alive for the next MCP server start to reconnect.
     */
    @PreDestroy
    public void shutdown() {
        log.info("MCP server stopping — browser daemon preserved on CDP port {}", cdpPort);
        if (daemonThread != null) daemonThread.interrupt();
        try {
            // playwright.close() disconnects from the CDP browser without killing it
            if (playwright != null) playwright.close();
        } catch (Exception e) {
            log.warn("Playwright shutdown error: {}", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Value object
    // -------------------------------------------------------------------------

    public record TabInfo(int index, String url, String title, boolean active) {}
}
