package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

@Component
public class AssertValueTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public AssertValueTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_assert_value"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector for the input/select element");
        props.putObject("expected").put("type", "string").put("description", "Expected input value");
        props.putObject("mode").put("type", "string")
                .put("description", "Comparison mode: equals (default), contains");
        schema.putArray("required").add("selector").add("expected");
        return new ToolDefinition(getName(),
                "Assert the value attribute of an input, textarea, or select element", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        String expected = arguments.path("expected").asText();
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        if (expected.isBlank()) return ToolResult.error("'expected' argument is required");
        String mode = arguments.path("mode").asText("equals");
        try {
            String actual = browser.getPage().locator(selector).inputValue();
            boolean passed = "contains".equals(mode) ? actual.contains(expected) : actual.equals(expected);
            if (passed) {
                return ToolResult.text("PASS — Value of '" + selector + "' " + mode + " '" + expected + "'");
            } else {
                return ToolResult.error("FAIL — Value assertion (" + mode + ") on '" + selector
                        + "'\n  Expected: " + expected + "\n  Actual:   " + actual);
            }
        } catch (Exception e) {
            return ToolResult.error("AssertValue failed: " + e.getMessage());
        }
    }
}
