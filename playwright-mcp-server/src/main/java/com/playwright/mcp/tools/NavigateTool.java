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
public class NavigateTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(NavigateTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public NavigateTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_navigate"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("url").put("type", "string").put("description", "The URL to navigate to");
        schema.putArray("required").add("url");
        return new ToolDefinition(getName(),
                "Navigate the browser to a URL and wait for page load", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String url = arguments.path("url").asText();
        if (url.isBlank()) return ToolResult.error("'url' argument is required");
        try {
            Page page = browser.getPage();
            Page.NavigateOptions opts = new Page.NavigateOptions()
                    .setWaitUntil(com.microsoft.playwright.options.WaitUntilState.LOAD);
            page.navigate(url, opts);
            String title = page.title();
            log.debug("Navigated to {} — title: {}", url, title);
            return ToolResult.text("Navigated to: " + url + "\nPage title: " + title);
        } catch (Exception e) {
            log.error("Navigate failed: {}", e.getMessage());
            return ToolResult.error("Navigation failed: " + e.getMessage());
        }
    }
}
