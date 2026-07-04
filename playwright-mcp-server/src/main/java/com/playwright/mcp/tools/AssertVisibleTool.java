package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

@Component
public class AssertVisibleTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public AssertVisibleTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_assert_visible"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector of the element to check");
        props.putObject("visible").put("type", "boolean")
                .put("description", "Expected visibility — true (default) or false");
        schema.putArray("required").add("selector");
        return new ToolDefinition(getName(),
                "Assert an element is visible (or hidden) on the page", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        boolean expectVisible = arguments.path("visible").asBoolean(true);
        try {
            boolean isVisible = browser.getPage().locator(selector).isVisible();
            boolean passed = (isVisible == expectVisible);
            String state = isVisible ? "visible" : "hidden";
            if (passed) {
                return ToolResult.text("PASS — '" + selector + "' is " + state + " as expected");
            } else {
                return ToolResult.error("FAIL — '" + selector + "' is " + state
                        + " but expected " + (expectVisible ? "visible" : "hidden"));
            }
        } catch (Exception e) {
            return ToolResult.error("AssertVisible failed: " + e.getMessage());
        }
    }
}
