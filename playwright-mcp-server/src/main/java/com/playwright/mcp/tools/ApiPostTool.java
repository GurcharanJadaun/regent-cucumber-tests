package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playwright.mcp.api.ApiSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApiPostTool extends ApiBaseTool {

    private static final Logger log = LoggerFactory.getLogger(ApiPostTool.class);

    public ApiPostTool(ApiSessionManager api, ObjectMapper mapper) {
        super(api, mapper);
    }

    @Override
    public String getName() { return "api_post"; }

    @Override
    public ToolDefinition getDefinition() {
        return new ToolDefinition(getName(), "Send an HTTP POST request with an optional JSON body and return the status code and response body", baseSchema(true));
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String url = arguments.path("url").asText();
        if (url.isBlank()) return ToolResult.error("'url' argument is required");
        String body    = arguments.path("body").asText(null);
        int timeout    = arguments.path("timeout").asInt(30);
        try {
            log.debug("POST {}", url);
            ApiSessionManager.ApiResponse response = api.execute("POST", url, parseHeaders(arguments.path("headers")), body, timeout);
            return formatResponse(response);
        } catch (Exception e) {
            log.error("POST {} failed: {}", url, e.getMessage());
            return ToolResult.error("POST failed: " + e.getMessage());
        }
    }
}
