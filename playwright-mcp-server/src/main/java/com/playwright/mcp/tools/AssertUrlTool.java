package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AssertUrlTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public AssertUrlTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_assert_url"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("expected").put("type", "string").put("description", "Expected URL value or pattern");
        props.putObject("mode").put("type", "string")
                .put("description", "Comparison mode: equals (default), contains, matches (regex)");
        schema.putArray("required").add("expected");
        return new ToolDefinition(getName(),
                "Assert the current page URL equals, contains, or matches a pattern", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String expected = arguments.path("expected").asText();
        if (expected.isBlank()) return ToolResult.error("'expected' argument is required");
        String mode = arguments.path("mode").asText("equals");
        try {
            String actual = browser.getPage().url();
            boolean passed;
            if ("contains".equals(mode)) {
                passed = actual.contains(expected);
            } else if ("matches".equals(mode)) {
                passed = Pattern.compile(expected).matcher(actual).find();
            } else {
                passed = actual.equals(expected);
            }
            if (passed) {
                return ToolResult.text("PASS — URL " + mode + " '" + expected + "'\nActual: " + actual);
            } else {
                return ToolResult.error("FAIL — URL assertion (" + mode + ")\n  Expected: " + expected + "\n  Actual:   " + actual);
            }
        } catch (Exception e) {
            return ToolResult.error("AssertUrl failed: " + e.getMessage());
        }
    }
}
