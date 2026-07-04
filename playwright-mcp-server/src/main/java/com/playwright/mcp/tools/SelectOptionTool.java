package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectOptionTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public SelectOptionTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_select_option"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector").put("type", "string").put("description", "CSS selector for the <select> element");
        props.putObject("value").put("type", "string").put("description", "Option value to select");
        props.putObject("label").put("type", "string").put("description", "Option label/text to select (alternative to value)");
        schema.putArray("required").add("selector");
        return new ToolDefinition(getName(), "Select an option in a <select> dropdown element", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText();
        if (selector.isBlank()) return ToolResult.error("'selector' argument is required");
        String value = arguments.path("value").asText(null);
        String label = arguments.path("label").asText(null);
        if (value == null && label == null) return ToolResult.error("Either 'value' or 'label' is required");
        try {
            Page page = browser.getPage();
            List<String> selected;
            if (value != null) {
                selected = page.selectOption(selector, value);
            } else {
                selected = page.selectOption(selector,
                        new com.microsoft.playwright.options.SelectOption().setLabel(label));
            }
            return ToolResult.text("Selected option(s): " + selected + " in " + selector);
        } catch (Exception e) {
            return ToolResult.error("SelectOption failed on '" + selector + "': " + e.getMessage());
        }
    }
}
