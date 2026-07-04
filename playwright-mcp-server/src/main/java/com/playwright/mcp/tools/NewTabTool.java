package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

@Component
public class NewTabTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public NewTabTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_new_tab"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("url").put("type", "string").put("description", "Optional URL to navigate the new tab to");
        return new ToolDefinition(getName(), "Open a new browser tab and switch to it", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String url = arguments.path("url").asText(null);
        try {
            browser.newTab(url);
            int idx = browser.listTabs().size() - 1;
            return ToolResult.text("Opened new tab #" + idx + (url != null ? " at: " + url : " (blank)"));
        } catch (Exception e) {
            return ToolResult.error("NewTab failed: " + e.getMessage());
        }
    }
}
