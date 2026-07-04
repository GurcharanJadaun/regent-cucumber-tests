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
public class AssertTextTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public AssertTextTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_assert_text"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string")
                .put("description", "CSS selector for the element (omit to check full page text)");
        props.putObject("expected").put("type", "string").put("description", "Expected text value or pattern");
        props.putObject("mode").put("type", "string")
                .put("description", "Comparison mode: contains (default), equals, matches (regex)");
        props.putObject("ignoreCase").put("type", "boolean").put("description", "Case-insensitive comparison (default false)");
        schema.putArray("required").add("expected");
        return new ToolDefinition(getName(),
                "Assert that an element or page body contains/equals/matches a text value", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String expected = arguments.path("expected").asText();
        if (expected.isBlank()) return ToolResult.error("'expected' argument is required");
        String selector = arguments.path("selector").asText(null);
        String mode = arguments.path("mode").asText("contains");
        boolean ignoreCase = arguments.path("ignoreCase").asBoolean(false);

        try {
            String actual;
            if (selector != null && !selector.isBlank()) {
                actual = browser.getPage().locator(selector).innerText();
            } else {
                actual = (String) browser.getPage().evaluate("document.body.innerText");
            }
            if (actual == null) actual = "";

            String cmpActual   = ignoreCase ? actual.toLowerCase()    : actual;
            String cmpExpected = ignoreCase ? expected.toLowerCase()  : expected;

            boolean passed;
            if ("matches".equals(mode)) {
                int flags = ignoreCase ? Pattern.CASE_INSENSITIVE : 0;
                passed = Pattern.compile(expected, flags).matcher(actual).find();
            } else if ("equals".equals(mode)) {
                passed = cmpActual.equals(cmpExpected);
            } else {
                passed = cmpActual.contains(cmpExpected);
            }

            String target = selector != null ? "'" + selector + "'" : "page body";
            if (passed) {
                return ToolResult.text("PASS — Text of " + target + " " + mode + " '" + expected + "'");
            } else {
                return ToolResult.error("FAIL — Text assertion (" + mode + ") on " + target
                        + "\n  Expected: " + expected
                        + "\n  Actual:   " + (actual.length() > 300 ? actual.substring(0, 300) + "…" : actual));
            }
        } catch (Exception e) {
            return ToolResult.error("AssertText failed: " + e.getMessage());
        }
    }
}
