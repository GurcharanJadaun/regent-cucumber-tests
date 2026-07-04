package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WaitForTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(WaitForTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public WaitForTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_wait_for"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector to wait for");
        props.putObject("state").put("type", "string").put("description", "Element state: visible (default), hidden, attached, detached");
        props.putObject("timeout").put("type", "number").put("description", "Timeout in ms (default 30000)");
        return new ToolDefinition(getName(),
                "Wait for an element to reach a specific state on the page", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        String stateStr = arguments.path("state").asText("visible");
        double timeout = arguments.path("timeout").asDouble(30000);
        try {
            Page page = browser.getPage();
            WaitForSelectorState state;
            String lc = stateStr.toLowerCase();
            if ("hidden".equals(lc)) {
                state = WaitForSelectorState.HIDDEN;
            } else if ("attached".equals(lc)) {
                state = WaitForSelectorState.ATTACHED;
            } else if ("detached".equals(lc)) {
                state = WaitForSelectorState.DETACHED;
            } else {
                state = WaitForSelectorState.VISIBLE;
            }
            page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                    .setState(state).setTimeout(timeout));
            log.debug("Wait complete for '{}' state={}", selector, stateStr);
            return ToolResult.text("Element '" + selector + "' is now " + stateStr);
        } catch (Exception e) {
            log.error("WaitFor failed on {}: {}", selector, e.getMessage());
            return ToolResult.error("WaitFor failed on '" + selector + "': " + e.getMessage());
        }
    }
}
