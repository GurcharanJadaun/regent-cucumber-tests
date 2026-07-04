# Playwright MCP Server

A STDIO-based [Model Context Protocol (MCP)](https://modelcontextprotocol.io) server built with **Java 17**, **Spring Boot 3.2.5**, and **Playwright 1.44.0**. It exposes 32 browser automation tools that any MCP-compatible AI client (Claude VS Code extension, Cline, etc.) can call directly.

---

## Architecture

```
AI Client (Claude Extension)
        │  JSON-RPC 2.0 over STDIO
        ▼
 McpServerRunner  ──►  McpProtocolHandler  ──►  BrowserTool (×32)
        │                                               │
        │                                       BrowserSessionManager
        │                                         (Playwright / Chromium)
        └── All logging routed to stderr (stdout reserved for JSON-RPC)
```

- **Transport:** STDIO — newline-delimited JSON-RPC 2.0  
- **Browser:** Playwright Chromium (configurable: chromium / firefox / webkit)  
- **Session:** Single browser instance held as a Spring singleton with a daemon thread  
- **Multi-tab:** Full tab management with `browser_new_tab`, `browser_switch_tab`, etc.

---

## Prerequisites

| Requirement | Version |
|---|---|
| Java JDK | 17+ |
| Maven | 3.8+ |
| Playwright browsers | Auto-installed on first run |

---

## Build

```bash
cd playwright-mcp-server
mvn clean package -q
```

This produces a fat JAR at:
```
target/playwright-mcp-server-1.0.0.jar
```

> **First run only:** Playwright will auto-download Chromium (~150 MB) on first startup.

---

## Configuration

Edit `src/main/resources/application.properties` before building:

```properties
# Browser type: chromium (default), firefox, webkit
browser.type=chromium

# Run without a visible window (true = headless, false = show browser UI)
browser.headless=false

# Default timeout in milliseconds for all browser operations
browser.timeout=30000

# Browser viewport size
browser.viewport.width=1280
browser.viewport.height=720
```

---

## Setup for Claude VS Code Extension

### Step 1 — Build the JAR

```bash
cd playwright-mcp-server
mvn clean package -q
```

### Step 2 — Create `.mcp.json` at the project root

Create a file named `.mcp.json` in the root of your workspace (`D:\Work\smoke-suite-automation\.mcp.json`):

```json
{
  "mcpServers": {
    "playwright": {
      "command": "C:\\Program Files\\Java\\jdk-17\\BIN\\java.exe",
      "args": [
        "-Dspring.main.banner-mode=off",
        "-Dlogging.pattern.console=",
        "-jar",
        "D:\\Work\\smoke-suite-automation\\playwright-mcp-server\\target\\playwright-mcp-server-1.0.0.jar"
      ],
      "env": {}
    }
  }
}
```

> **Windows note:** Use the full path to `java.exe` — VS Code's PATH may not include Java.  
> Find your Java path: `where java` in a terminal, then use that full path.

### Step 3 — Approve the server in project settings

Add `enableAllProjectMcpServers: true` to `.claude/settings.local.json`:

```json
{
  "enableAllProjectMcpServers": true,
  "permissions": {
    "allow": []
  }
}
```

### Step 4 — Reload VS Code

Press `Ctrl+Shift+P` → `Developer: Reload Window`

The Claude extension will start the MCP server automatically and all `browser_*` tools will appear.

---

## Setup for Cline Extension

Edit the Cline MCP settings file at:
```
C:\Users\<you>\AppData\Roaming\Code\User\globalStorage\saoudrizwan.claude-dev\settings\cline_mcp_settings.json
```

```json
{
  "mcpServers": {
    "playwright": {
      "command": "C:\\Program Files\\Java\\jdk-17\\BIN\\java.exe",
      "args": [
        "-Dspring.main.banner-mode=off",
        "-Dlogging.pattern.console=",
        "-jar",
        "D:\\Work\\smoke-suite-automation\\playwright-mcp-server\\target\\playwright-mcp-server-1.0.0.jar"
      ],
      "env": {},
      "disabled": false,
      "autoApprove": []
    }
  }
}
```

Restart VS Code to pick up the changes.

---

## Rebuilding After Changes

Before rebuilding, kill any running Java process holding the JAR lock:

```powershell
taskkill /F /IM java.exe /T
cd playwright-mcp-server
mvn clean package -q
```

Then reload VS Code (`Developer: Reload Window`).

---

## Tools Reference

### Navigation

| Tool | Description | Key Args |
|---|---|---|
| `browser_navigate` | Navigate to a URL | `url` |
| `browser_go_back` | Browser back | — |
| `browser_go_forward` | Browser forward | — |
| `browser_refresh` | Reload current page | — |
| `browser_get_url` | Return current URL | — |
| `browser_get_title` | Return page title | — |

### Interaction

| Tool | Description | Key Args |
|---|---|---|
| `browser_click` | Click an element | `selector` |
| `browser_fill` | Clear and fill an input | `selector`, `value` |
| `browser_type` | Type text (key by key) | `selector`, `text` |
| `browser_hover` | Hover over an element | `selector` |
| `browser_select_option` | Select a `<select>` option | `selector`, `value` |

### Content

| Tool | Description | Key Args |
|---|---|---|
| `browser_get_text` | Get visible text of an element | `selector` |
| `browser_screenshot` | Take a base64 screenshot | — |
| `browser_evaluate` | Run a JS expression and return result | `expression` |

### JavaScript Execution

| Tool | Description | Key Args |
|---|---|---|
| `browser_evaluate` | Run a simple JS expression | `expression` |
| `browser_execute_script` | Run a multi-line script or load from file | `script` or `file`, optional `args` array |

**`browser_execute_script` examples:**

```json
// Inline script with return value
{ "script": "return document.querySelectorAll('li').length" }

// Multi-line with async/await
{ "script": "await page.waitForTimeout(1000); return document.title" }

// Load script from a .js file
{ "file": "C:\\scripts\\myScript.js", "args": ["#selector"] }

// With arguments (accessed as arguments[0], arguments[1] in the script)
{ "script": "return document.querySelector(arguments[0]).innerText", "args": ["h1"] }
```

### Tab Management

| Tool | Description | Key Args |
|---|---|---|
| `browser_new_tab` | Open a new tab | optional `url` |
| `browser_list_tabs` | List all open tabs | — |
| `browser_switch_tab` | Switch active tab | `index` (0-based) |
| `browser_close_tab` | Close a tab | `index` (0-based) |

### Waits

| Tool | Description | Key Args |
|---|---|---|
| `browser_wait_for` | Wait for element state | `selector`, `state` (visible/hidden/attached/detached) |
| `browser_wait_for_url` | Wait until URL matches | `url`, `timeout` |
| `browser_wait_for_text` | Wait until element contains text | `selector`, `text` |
| `browser_wait_for_presence` | Wait for element to appear/disappear | `selector`, `state` (present/absent/visible/hidden) |
| `browser_wait_for_element_count` | Wait until element count meets condition | `selector`, `count`, `operator` (gte/lte/eq) |
| `browser_wait_for_load_state` | Wait for page load state | `state` (load/domcontentloaded/networkidle) |

### Assertions

All assertions return `isError: true` on failure with an `Expected / Actual` diff.  
`mode` options: `equals` (default) · `contains` · `matches` (regex)

| Tool | Description | Key Args |
|---|---|---|
| `browser_assert_url` | Assert current URL | `expected`, `mode` |
| `browser_assert_text` | Assert element text | `selector`, `expected`, `mode` |
| `browser_assert_title` | Assert page title | `expected`, `mode` |
| `browser_assert_visible` | Assert element is/isn't visible | `selector`, `visible` (true/false) |
| `browser_assert_value` | Assert input value | `selector`, `expected`, `mode` |

### Batch Execution

| Tool | Description |
|---|---|
| `browser_batch_execute` | Execute multiple steps in one call with resume support |

#### Batch JSON structure

```json
{
  "steps": [
    { "id": "open",     "tool": "browser_navigate", "args": { "url": "https://example.com" } },
    { "id": "fill",     "tool": "browser_fill",     "args": { "selector": "#user", "value": "admin" } },
    { "id": "submit",   "tool": "browser_click",    "args": { "selector": "#login" } },
    { "id": "verify",   "tool": "browser_assert_url","args": { "expected": "/home", "mode": "contains" } }
  ],
  "stopOnError": true,
  "startFrom": null
}
```

| Field | Type | Default | Description |
|---|---|---|---|
| `steps` | array | required | Ordered steps to execute |
| `stopOnError` | boolean | `true` | Stop on first failure |
| `startFrom` | string | `null` | Step `id` or 1-based index to resume from |

#### Resume flow

1. Batch runs, step `fill` fails
2. Response includes `"resumeHint": "...startFrom=\"submit\""` 
3. AI fixes the issue using individual tools
4. AI re-submits the batch with `"startFrom": "submit"`
5. Steps `open` and `fill` are skipped, execution resumes from `submit`

#### Batch response format

```json
{
  "steps": [
    { "id": "open",   "index": 1, "status": "passed",  "result": "Navigated to https://example.com" },
    { "id": "fill",   "index": 2, "status": "failed",  "error": "Element not found: #user",
      "resumeHint": "Fix this step, then call browser_batch_execute with startFrom=\"submit\"" },
    { "id": "submit", "index": 3, "status": "skipped", "reason": "batch stopped due to earlier failure" },
    { "id": "verify", "index": 4, "status": "skipped", "reason": "batch stopped due to earlier failure" }
  ],
  "summary": {
    "total": 4, "passed": 1, "failed": 1, "skipped": 2, "success": false,
    "note": "Fix the failing step(s) then re-submit with startFrom set to the step after the failure."
  }
}
```

### Session

| Tool | Description |
|---|---|
| `browser_reset_session` | Close browser and start a fresh session |

---

## Troubleshooting

**Tools not appearing after reload**  
- Verify the JAR exists: `target/playwright-mcp-server-1.0.0.jar`  
- Verify Java path is correct: open a terminal and run the full `java.exe` path manually  
- Check `.mcp.json` is at the workspace root (same level as `pom.xml`)  
- Check `enableAllProjectMcpServers: true` is in `.claude/settings.local.json`

**JAR locked during rebuild**  
```powershell
taskkill /F /IM java.exe /T
```

**Verify server starts correctly**  
```powershell
& "C:\Program Files\Java\jdk-17\BIN\java.exe" `
  "-Dspring.main.banner-mode=off" `
  "-Dlogging.pattern.console=" `
  -jar ".\target\playwright-mcp-server-1.0.0.jar"
```
You should see `Playwright MCP server starting — listening on stdin` in stderr output.

**Playwright browsers not found**  
```bash
# From inside the playwright-mcp-server directory
mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install chromium"
```
