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
public class ListTabsTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public ListTabsTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_list_tabs"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        schema.putObject("properties");
        return new ToolDefinition(getName(),
                "List all open browser tabs with their index, URL, title, and active status", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        try {
            List<BrowserSessionManager.TabInfo> tabs = browser.listTabs();
            StringBuilder sb = new StringBuilder("Open tabs (" + tabs.size() + "):\n");
            for (BrowserSessionManager.TabInfo tab : tabs) {
                sb.append(String.format("  [%d]%s %s — %s%n",
                        tab.index(),
                        tab.active() ? " (active)" : "",
                        tab.url(),
                        tab.title()));
            }
            return ToolResult.text(sb.toString().trim());
        } catch (Exception e) {
            return ToolResult.error("ListTabs failed: " + e.getMessage());
        }
    }
}
