package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class WaitForElementCountTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public WaitForElementCountTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_wait_for_element_count"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector to count");
        props.putObject("count").put("type", "number").put("description", "Expected element count");
        props.putObject("operator").put("type", "string")
                .put("description", "Comparison operator: gte (default), lte, eq");
        props.putObject("timeout").put("type", "number").put("description", "Timeout in ms (default 30000)");
        schema.putArray("required").add("selector").add("count");
        return new ToolDefinition(getName(),
                "Wait until the number of elements matching a selector meets a numeric condition", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        int count = arguments.path("count").asInt(1);
        String op = arguments.path("operator").asText("gte");
        double timeout = arguments.path("timeout").asDouble(30000);
        try {
            Page page = browser.getPage();
            String js = "([sel, cnt, operator]) => { "
                    + "const n = document.querySelectorAll(sel).length; "
                    + "if (operator === 'lte') return n <= cnt; "
                    + "if (operator === 'eq')  return n === cnt; "
                    + "return n >= cnt; "       // gte default
                    + "}";
            page.waitForFunction(js,
                    Arrays.asList(selector, count, op),
                    new Page.WaitForFunctionOptions().setTimeout(timeout));
            int actual = browser.getPage().locator(selector).count();
            return ToolResult.text("Element count condition met — '" + selector
                    + "' count=" + actual + " " + op + " " + count);
        } catch (Exception e) {
            int actual = 0;
            try { actual = browser.getPage().locator(selector).count(); } catch (Exception ignored) {}
            return ToolResult.error("WaitForElementCount timed out — '"
                    + selector + "' count=" + actual + " did not satisfy " + op + " " + count
                    + "\n" + e.getMessage());
        }
    }
}
