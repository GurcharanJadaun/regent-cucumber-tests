package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

@Component
public class GoBackTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public GoBackTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_go_back"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        schema.putObject("properties");
        return new ToolDefinition(getName(), "Navigate back in browser history", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        try {
            browser.getPage().goBack();
            return ToolResult.text("Navigated back to: " + browser.getPage().url());
        } catch (Exception e) {
            return ToolResult.error("GoBack failed: " + e.getMessage());
        }
    }
}
