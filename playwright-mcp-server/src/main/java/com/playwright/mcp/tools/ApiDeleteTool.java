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
public class ApiDeleteTool extends ApiBaseTool {

    private static final Logger log = LoggerFactory.getLogger(ApiDeleteTool.class);

    public ApiDeleteTool(ApiSessionManager api, ObjectMapper mapper) {
        super(api, mapper);
    }

    @Override
    public String getName() { return "api_delete"; }

    @Override
    public ToolDefinition getDefinition() {
        return new ToolDefinition(getName(), "Send an HTTP DELETE request and return the status code and response body", baseSchema(false));
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String url = arguments.path("url").asText();
        if (url.isBlank()) return ToolResult.error("'url' argument is required");
        int timeout = arguments.path("timeout").asInt(30);
        try {
            log.debug("DELETE {}", url);
            ApiSessionManager.ApiResponse response = api.execute("DELETE", url, parseHeaders(arguments.path("headers")), null, timeout);
            return formatResponse(response);
        } catch (Exception e) {
            log.error("DELETE {} failed: {}", url, e.getMessage());
            return ToolResult.error("DELETE failed: " + e.getMessage());
        }
    }
}
