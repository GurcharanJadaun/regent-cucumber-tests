package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class WaitForUrlTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public WaitForUrlTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_wait_for_url"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("url").put("type", "string").put("description", "URL string or regex pattern to wait for");
        props.putObject("mode").put("type", "string")
                .put("description", "Match mode: contains (default), equals, matches (regex)");
        props.putObject("timeout").put("type", "number").put("description", "Timeout in ms (default 30000)");
        schema.putArray("required").add("url");
        return new ToolDefinition(getName(),
                "Wait until the current page URL matches the expected value", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String url = arguments.path("url").asText();
        if (url.isBlank()) return ToolResult.error("'url' argument is required");
        String mode = arguments.path("mode").asText("contains");
        double timeout = arguments.path("timeout").asDouble(30000);
        try {
            Page page = browser.getPage();
            if ("matches".equals(mode)) {
                page.waitForURL(Pattern.compile(url),
                        new Page.WaitForURLOptions().setTimeout(timeout));
            } else if ("equals".equals(mode)) {
                page.waitForURL(url, new Page.WaitForURLOptions().setTimeout(timeout));
            } else {
                // contains — use a glob-style wildcard pattern
                page.waitForURL("**" + url + "**",
                        new Page.WaitForURLOptions().setTimeout(timeout));
            }
            return ToolResult.text("URL now " + mode + " '" + url + "'\nCurrent: " + page.url());
        } catch (Exception e) {
            return ToolResult.error("WaitForUrl timed out or failed: " + e.getMessage()
                    + "\nCurrent URL: " + browser.getPage().url());
        }
    }
}
