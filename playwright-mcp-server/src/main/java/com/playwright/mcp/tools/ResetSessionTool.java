package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

@Component
public class ResetSessionTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public ResetSessionTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_reset_session"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        schema.putObject("properties");
        return new ToolDefinition(getName(),
                "Reset the browser session — clears cookies, storage, and opens a fresh page", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        try {
            browser.resetSession();
            return ToolResult.text("Browser session reset successfully. Ready for a new session.");
        } catch (Exception e) {
            return ToolResult.error("ResetSession failed: " + e.getMessage());
        }
    }
}
