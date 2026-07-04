package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CloseTabTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public CloseTabTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_close_tab"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("index").put("type", "number")
                .put("description", "Tab index to close (omit to close the active tab)");
        return new ToolDefinition(getName(),
                "Close a browser tab by index (defaults to the active tab)", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        try {
            int index;
            if (arguments.has("index")) {
                index = arguments.path("index").asInt();
            } else {
                // find active tab index
                List<BrowserSessionManager.TabInfo> tabs = browser.listTabs();
                index = tabs.stream().filter(BrowserSessionManager.TabInfo::active)
                        .mapToInt(BrowserSessionManager.TabInfo::index).findFirst().orElse(0);
            }
            String closed = browser.closeTab(index);
            return ToolResult.text("Closed tab #" + index + " (" + closed + ")");
        } catch (IllegalArgumentException e) {
            return ToolResult.error(e.getMessage());
        } catch (Exception e) {
            return ToolResult.error("CloseTab failed: " + e.getMessage());
        }
    }
}
