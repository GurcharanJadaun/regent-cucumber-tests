package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

/**
 * Distinguishes between DOM presence (attached/detached) and
 * visual visibility (visible/hidden) — complements the generic WaitForTool.
 */
@Component
public class WaitForPresenceTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public WaitForPresenceTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_wait_for_presence"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector to wait for");
        props.putObject("condition").put("type", "string")
                .put("description", "Condition to satisfy: present (DOM attached, default), absent (DOM detached), visible, hidden");
        props.putObject("timeout").put("type", "number").put("description", "Timeout in ms (default 30000)");
        schema.putArray("required").add("selector");
        return new ToolDefinition(getName(),
                "Wait for an element's DOM presence or CSS visibility — present, absent, visible, or hidden", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        String condition = arguments.path("condition").asText("present");
        double timeout = arguments.path("timeout").asDouble(30000);

        WaitForSelectorState state;
        if ("absent".equals(condition)) {
            state = WaitForSelectorState.DETACHED;
        } else if ("visible".equals(condition)) {
            state = WaitForSelectorState.VISIBLE;
        } else if ("hidden".equals(condition)) {
            state = WaitForSelectorState.HIDDEN;
        } else {
            state = WaitForSelectorState.ATTACHED;   // present
        }

        try {
            browser.getPage().waitForSelector(selector,
                    new Page.WaitForSelectorOptions().setState(state).setTimeout(timeout));
            return ToolResult.text("Element '" + selector + "' is now " + condition);
        } catch (Exception e) {
            return ToolResult.error("WaitForPresence '" + condition + "' timed out for '"
                    + selector + "': " + e.getMessage());
        }
    }
}
