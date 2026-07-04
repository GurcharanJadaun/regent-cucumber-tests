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
public class EvaluateTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(EvaluateTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public EvaluateTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_evaluate"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("expression").put("type", "string").put("description", "JavaScript expression to evaluate in the page context");
        schema.putArray("required").add("expression");
        return new ToolDefinition(getName(),
                "Execute a JavaScript expression in the page context and return the result", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String expression = arguments.path("expression").asText();
        if (expression.isBlank()) return ToolResult.error("'expression' argument is required");
        try {
            Page page = browser.getPage();
            Object result = page.evaluate(expression);
            String resultStr = result != null ? result.toString() : "null";
            log.debug("Evaluated expression, result: {}", resultStr);
            return ToolResult.text(resultStr);
        } catch (Exception e) {
            log.error("Evaluate failed: {}", e.getMessage());
            return ToolResult.error("Evaluate failed: " + e.getMessage());
        }
    }
}
