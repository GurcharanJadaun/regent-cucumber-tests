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
public class ApiGetTool extends ApiBaseTool {

    private static final Logger log = LoggerFactory.getLogger(ApiGetTool.class);

    public ApiGetTool(ApiSessionManager api, ObjectMapper mapper) {
        super(api, mapper);
    }

    @Override
    public String getName() { return "api_get"; }

    @Override
    public ToolDefinition getDefinition() {
        return new ToolDefinition(getName(), "Send an HTTP GET request and return the status code and response body", baseSchema(false));
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String url = arguments.path("url").asText();
        if (url.isBlank()) return ToolResult.error("'url' argument is required");
        int timeout = arguments.path("timeout").asInt(30);
        try {
            log.debug("GET {}", url);
            ApiSessionManager.ApiResponse response = api.execute("GET", url, parseHeaders(arguments.path("headers")), null, timeout);
            return formatResponse(response);
        } catch (Exception e) {
            log.error("GET {} failed: {}", url, e.getMessage());
            return ToolResult.error("GET failed: " + e.getMessage());
        }
    }
}
