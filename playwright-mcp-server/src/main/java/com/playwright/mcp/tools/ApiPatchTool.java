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
public class ApiPatchTool extends ApiBaseTool {

    private static final Logger log = LoggerFactory.getLogger(ApiPatchTool.class);

    public ApiPatchTool(ApiSessionManager api, ObjectMapper mapper) {
        super(api, mapper);
    }

    @Override
    public String getName() { return "api_patch"; }

    @Override
    public ToolDefinition getDefinition() {
        return new ToolDefinition(getName(), "Send an HTTP PATCH request with a partial update body and return the status code and response body", baseSchema(true));
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String url = arguments.path("url").asText();
        if (url.isBlank()) return ToolResult.error("'url' argument is required");
        String body = arguments.path("body").asText(null);
        int timeout = arguments.path("timeout").asInt(30);
        try {
            log.debug("PATCH {}", url);
            ApiSessionManager.ApiResponse response = api.execute("PATCH", url, parseHeaders(arguments.path("headers")), body, timeout);
            return formatResponse(response);
        } catch (Exception e) {
            log.error("PATCH {} failed: {}", url, e.getMessage());
            return ToolResult.error("PATCH failed: " + e.getMessage());
        }
    }
}
