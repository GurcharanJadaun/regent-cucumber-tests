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
import java.util.List;

@Component
public class WaitForTextTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public WaitForTextTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_wait_for_text"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("text").put("type", "string").put("description", "Text to wait for on the page");
        props.putObject("selector").put("type", "string")
                .put("description", "Optional CSS selector to scope the check (default: body)");
        props.putObject("timeout").put("type", "number").put("description", "Timeout in ms (default 30000)");
        schema.putArray("required").add("text");
        return new ToolDefinition(getName(),
                "Wait until a specific text appears in an element or anywhere on the page", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String text = arguments.path("text").asText();
        if (text.isBlank()) return ToolResult.error("'text' argument is required");
        String selector = arguments.path("selector").asText("body");
        double timeout = arguments.path("timeout").asDouble(30000);
        try {
            Page page = browser.getPage();
            // Resolve XPath selectors via document.evaluate; CSS via querySelector
            String js = "([sel, txt]) => {" +
                    "  let el;" +
                    "  if (sel.startsWith('xpath=')) {" +
                    "    try {" +
                    "      const r = document.evaluate(sel.substring(6), document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);" +
                    "      el = r.singleNodeValue;" +
                    "    } catch(e) { return false; }" +
                    "  } else {" +
                    "    el = document.querySelector(sel);" +
                    "  }" +
                    "  return el != null && el.innerText != null && el.innerText.includes(txt);" +
                    "}";
            page.waitForFunction(js,
                    Arrays.asList(selector, text),
                    new Page.WaitForFunctionOptions().setTimeout(timeout));
            return ToolResult.text("Text '" + text + "' appeared in '" + selector + "'");
        } catch (Exception e) {
            return ToolResult.error("WaitForText timed out — '" + text + "' not found in '"
                    + selector + "' within timeout.\n" + e.getMessage());
        }
    }
}
