package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ScreenshotTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public ScreenshotTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_screenshot"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("fullPage")
                .put("type", "boolean")
                .put("description", "Capture the full scrollable page, not just the viewport (default: false)");
        props.putObject("selector")
                .put("type", "string")
                .put("description", "CSS selector — screenshot only that element instead of the whole page");
        props.putObject("path")
                .put("type", "string")
                .put("description", "Absolute file path to save the PNG on disk (e.g. C:\\screenshots\\page.png). " +
                        "When provided the file is saved and the path is returned; no base64 is returned.");
        props.putObject("type")
                .put("type", "string")
                .put("description", "Image format: png (default) or jpeg");
        return new ToolDefinition(getName(),
                "Take a screenshot of the current page or a specific element. " +
                "Returns base64 image data or saves to a file when 'path' is given. " +
                "Supports full-page capture for crawling.", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        boolean fullPage  = arguments.path("fullPage").asBoolean(false);
        String selector   = arguments.path("selector").asText(null);
        String savePath   = arguments.path("path").asText(null);
        String imgType    = arguments.path("type").asText("png");
        boolean isJpeg    = "jpeg".equalsIgnoreCase(imgType) || "jpg".equalsIgnoreCase(imgType);

        try {
            Page page = browser.getPage();
            byte[] bytes;

            if (selector != null && !selector.isBlank()) {
                var locOpts = new com.microsoft.playwright.Locator.ScreenshotOptions();
                if (savePath != null && !savePath.isBlank()) {
                    locOpts.setPath(Paths.get(savePath));
                }
                bytes = page.locator(selector).screenshot(locOpts);
            } else {
                var opts = new Page.ScreenshotOptions().setFullPage(fullPage);
                if (isJpeg) opts.setType(com.microsoft.playwright.options.ScreenshotType.JPEG);
                if (savePath != null && !savePath.isBlank()) {
                    opts.setPath(Paths.get(savePath));
                }
                bytes = page.screenshot(opts);
            }

            if (savePath != null && !savePath.isBlank()) {
                log.debug("Screenshot saved to {} ({} bytes)", savePath, bytes.length);
                return ToolResult.text("Screenshot saved to: " + savePath + " (" + bytes.length + " bytes)");
            }

            String base64 = Base64.getEncoder().encodeToString(bytes);
            String mime   = isJpeg ? "image/jpeg" : "image/png";
            log.debug("Screenshot taken ({} bytes, {})", bytes.length, mime);
            return ToolResult.image(base64, mime);

        } catch (Exception e) {
            log.error("Screenshot failed: {}", e.getMessage());
            return ToolResult.error("Screenshot failed: " + e.getMessage());
        }
    }
}
