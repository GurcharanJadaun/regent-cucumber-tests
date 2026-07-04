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
public class GetTextTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(GetTextTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public GetTextTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_get_text"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector (omit to get full page text)");
        return new ToolDefinition(getName(), "Get text content from an element or the entire page", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText(null);
        try {
            Page page = browser.getPage();
            String text;
            if (selector != null && !selector.isBlank()) {
                text = page.locator(selector).innerText();
            } else {
                text = (String) page.evaluate("document.body.innerText");
            }
            log.debug("Got text from: {}", selector != null ? selector : "body");
            return ToolResult.text(text != null ? text : "");
        } catch (Exception e) {
            log.error("GetText failed: {}", e.getMessage());
            return ToolResult.error("GetText failed: " + e.getMessage());
        }
    }
}
