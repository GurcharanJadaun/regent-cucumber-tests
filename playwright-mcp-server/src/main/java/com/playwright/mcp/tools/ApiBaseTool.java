package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.api.ApiSessionManager;
import com.playwright.mcp.model.ToolResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Shared helpers for all API tools.
 */
abstract class ApiBaseTool implements BrowserTool {

    protected final ApiSessionManager api;
    protected final ObjectMapper mapper;

    protected ApiBaseTool(ApiSessionManager api, ObjectMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    /** Parse optional "headers" node (object) into a String→String map. */
    protected Map<String, String> parseHeaders(JsonNode headersNode) {
        Map<String, String> result = new HashMap<>();
        if (headersNode != null && headersNode.isObject()) {
            headersNode.fields().forEachRemaining(e -> result.put(e.getKey(), e.getValue().asText()));
        }
        return result;
    }

    /** Format an API response for return to the MCP host. */
    protected ToolResult formatResponse(ApiSessionManager.ApiResponse response) {
        String text = "Status: " + response.statusCode() + "\n\n" + response.body();
        return response.isSuccess() ? ToolResult.text(text) : ToolResult.error(text);
    }

    /** Build a schema ObjectNode with url + optional headers + optional timeout. */
    protected ObjectNode baseSchema(boolean withBody) {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");

        props.putObject("url")
                .put("type", "string")
                .put("description", "The full URL to call");

        if (withBody) {
            props.putObject("body")
                    .put("type", "string")
                    .put("description", "Request body (JSON string or plain text)");
        }

        props.putObject("headers")
                .put("type", "object")
                .put("description", "Additional request headers (key-value pairs). Merged on top of session defaults.");

        props.putObject("timeout")
                .put("type", "integer")
                .put("description", "Request timeout in seconds (default: 30)");

        schema.putArray("required").add("url");
        return schema;
    }
}
