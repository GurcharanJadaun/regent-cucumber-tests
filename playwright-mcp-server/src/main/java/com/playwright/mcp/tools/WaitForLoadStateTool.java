package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

@Component
public class WaitForLoadStateTool implements BrowserTool {

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public WaitForLoadStateTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_wait_for_load_state"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("state").put("type", "string")
                .put("description", "Load state to wait for: load (default), domcontentloaded, networkidle");
        props.putObject("timeout").put("type", "number").put("description", "Timeout in ms (default 30000)");
        return new ToolDefinition(getName(),
                "Wait for the page to reach a specific load state (load, domcontentloaded, networkidle)", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String stateStr = arguments.path("state").asText("load");
        double timeout = arguments.path("timeout").asDouble(30000);
        LoadState state;
        if ("domcontentloaded".equals(stateStr)) {
            state = LoadState.DOMCONTENTLOADED;
        } else if ("networkidle".equals(stateStr)) {
            state = LoadState.NETWORKIDLE;
        } else {
            state = LoadState.LOAD;
        }
        try {
            browser.getPage().waitForLoadState(state,
                    new Page.WaitForLoadStateOptions().setTimeout(timeout));
            return ToolResult.text("Page reached load state: " + stateStr);
        } catch (Exception e) {
            return ToolResult.error("WaitForLoadState '" + stateStr + "' timed out: " + e.getMessage());
        }
    }
}
