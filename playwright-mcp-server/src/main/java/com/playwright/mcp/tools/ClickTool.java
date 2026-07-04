package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClickTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(ClickTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public ClickTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_click"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector or text selector for the element to click");
        props.putObject("timeout").put("type", "number").put("description", "Timeout in ms (default 30000)");
        schema.putArray("required").add("selector");
        return new ToolDefinition(getName(), "Click an element on the page by selector", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        double timeout = arguments.path("timeout").asDouble(30000);
        try {
            Page page = browser.getPage();
            page.click(selector, new Page.ClickOptions().setTimeout(timeout));
            log.debug("Clicked: {}", selector);
            return ToolResult.text("Clicked element: " + selector);
        } catch (Exception e) {
            log.error("Click failed on {}: {}", selector, e.getMessage());
            return ToolResult.error("Click failed on '" + selector + "': " + e.getMessage());
        }
    }
}
