package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

/**
 * Fast-fill: replaces element value atomically (no keystrokes).
 * Prefer over browser_type when keystroke-level events are not needed.
 */
@Component
public class FillTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public FillTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_fill"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector for the input element");
        props.putObject("value").put("type", "string").put("description", "Value to fill into the element");
        schema.putArray("required").add("selector").add("value");
        return new ToolDefinition(getName(),
                "Set the value of an input element directly (faster than typing)", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        String value = arguments.path("value").asText("");
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        try {
            browser.getPage().fill(selector, value);
            return ToolResult.text("Filled '" + selector + "' with value: " + value);
        } catch (Exception e) {
            return ToolResult.error("Fill failed on '" + selector + "': " + e.getMessage());
        }
    }
}
