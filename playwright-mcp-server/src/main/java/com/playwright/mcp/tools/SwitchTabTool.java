package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

@Component
public class SwitchTabTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public SwitchTabTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_switch_tab"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("index").put("type", "number").put("description", "Tab index to switch to (use browser_list_tabs to see indices)");
        schema.putArray("required").add("index");
        return new ToolDefinition(getName(), "Switch the active browser tab by index", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        if (!arguments.has("index")) return ToolResult.error("'index' argument is required");
        int index = arguments.path("index").asInt();
        try {
            browser.switchTab(index);
            String url = browser.getPage().url();
            return ToolResult.text("Switched to tab #" + index + " — " + url);
        } catch (IllegalArgumentException e) {
            return ToolResult.error(e.getMessage());
        } catch (Exception e) {
            return ToolResult.error("SwitchTab failed: " + e.getMessage());
        }
    }
}
