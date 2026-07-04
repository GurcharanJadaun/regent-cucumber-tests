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
public class TypeTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(TypeTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public TypeTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_type"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector for the input element");
        props.putObject("text").put("type", "string").put("description", "Text to type into the element");
        props.putObject("clear").put("type", "boolean").put("description", "Clear existing text before typing (default true)");
        props.putObject("delay").put("type", "number").put("description", "Delay between keystrokes in ms (default 0)");
        schema.putArray("required").add("selector").add("text");
        return new ToolDefinition(getName(), "Type text into an input element", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        String text = arguments.path("text").asText();
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        boolean clear = arguments.path("clear").asBoolean(true);
        double delay = arguments.path("delay").asDouble(0);
        try {
            Page page = browser.getPage();
            if (clear) {
                page.fill(selector, "");
            }
            page.type(selector, text, new Page.TypeOptions().setDelay(delay));
            log.debug("Typed '{}' into: {}", text, selector);
            return ToolResult.text("Typed text into '" + selector + "': " + text);
        } catch (Exception e) {
            log.error("Type failed on {}: {}", selector, e.getMessage());
            return ToolResult.error("Type failed on '" + selector + "': " + e.getMessage());
        }
    }
}
