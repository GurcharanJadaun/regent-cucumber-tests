package com.playwright.mcp.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.model.*;
import com.playwright.mcp.tools.BrowserTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Handles MCP JSON-RPC 2.0 protocol dispatch.
 * Methods: initialize, initialized, ping, tools/list, tools/call,
 *          resources/list, resources/templates/list, prompts/list
 */
@Component
public class McpProtocolHandler {

    private static final Logger log = LoggerFactory.getLogger(McpProtocolHandler.class);

    private static final String PROTOCOL_VERSION = "2024-11-05";
    private static final String SERVER_NAME = "playwright-mcp-server";
    private static final String SERVER_VERSION = "1.0.0";

    private final ObjectMapper mapper;
    private final Map<String, BrowserTool> tools;

    public McpProtocolHandler(ObjectMapper mapper, List<BrowserTool> toolList) {
        this.mapper = mapper;
        this.tools = toolList.stream()
                .collect(Collectors.toMap(BrowserTool::getName, Function.identity()));
        log.info("Registered {} tools: {}", tools.size(), tools.keySet());
    }

    /**
     * Process a single JSON-RPC message line and return the response JSON string.
     * Returns null for notifications (no id) that require no response.
     */
    public String handle(String rawMessage) {
        JsonRpcRequest request;
        try {
            request = mapper.readValue(rawMessage, JsonRpcRequest.class);
        } catch (Exception e) {
            log.error("Parse error: {}", e.getMessage());
            return serialize(JsonRpcResponse.error(null, JsonRpcError.PARSE_ERROR, "Parse error: " + e.getMessage()));
        }

        String method = request.getMethod();
        JsonNode id = request.getId();

        log.debug("Received: method={} id={}", method, id);

        try {
            if ("initialize".equals(method)) {
                return serialize(handleInitialize(id, request.getParams()));
            } else if ("notifications/initialized".equals(method) || "initialized".equals(method)) {
                return null; // notifications require no response
            } else if ("ping".equals(method)) {
                return serialize(JsonRpcResponse.success(id, mapper.createObjectNode()));
            } else if ("tools/list".equals(method)) {
                return serialize(handleToolsList(id));
            } else if ("tools/call".equals(method)) {
                return serialize(handleToolsCall(id, request.getParams()));
            } else if ("resources/list".equals(method)) {
                return serialize(handleResourcesList(id));
            } else if ("resources/templates/list".equals(method)) {
                return serialize(handleResourcesTemplatesList(id));
            } else if ("prompts/list".equals(method)) {
                return serialize(handlePromptsList(id));
            } else {
                log.warn("Method not found: {}", method);
                return serialize(JsonRpcResponse.error(id, JsonRpcError.METHOD_NOT_FOUND,
                        "Method not found: " + method));
            }
        } catch (Exception e) {
            log.error("Internal error handling {}: {}", method, e.getMessage(), e);
            return serialize(JsonRpcResponse.error(id, JsonRpcError.INTERNAL_ERROR, e.getMessage()));
        }
    }

    private JsonRpcResponse handleInitialize(JsonNode id, JsonNode params) {
        ObjectNode result = mapper.createObjectNode();
        result.put("protocolVersion", PROTOCOL_VERSION);

        ObjectNode capabilities = result.putObject("capabilities");
        capabilities.putObject("tools");
        capabilities.putObject("resources");
        capabilities.putObject("prompts");

        ObjectNode serverInfo = result.putObject("serverInfo");
        serverInfo.put("name", SERVER_NAME);
        serverInfo.put("version", SERVER_VERSION);

        log.info("MCP handshake complete — client: {}",
                params != null ? params.path("clientInfo") : "unknown");
        return JsonRpcResponse.success(id, result);
    }

    private JsonRpcResponse handleToolsList(JsonNode id) {
        ArrayNode toolsArray = mapper.createArrayNode();
        for (BrowserTool tool : tools.values()) {
            ToolDefinition def = tool.getDefinition();
            ObjectNode entry = mapper.createObjectNode();
            entry.put("name", def.getName());
            entry.put("description", def.getDescription());
            entry.set("inputSchema", def.getInputSchema());
            toolsArray.add(entry);
        }
        ObjectNode result = mapper.createObjectNode();
        result.set("tools", toolsArray);
        return JsonRpcResponse.success(id, result);
    }

    private JsonRpcResponse handleToolsCall(JsonNode id, JsonNode params) {
        if (params == null) {
            return JsonRpcResponse.error(id, JsonRpcError.INVALID_PARAMS, "params required for tools/call");
        }
        String toolName = params.path("name").asText();
        JsonNode arguments = params.path("arguments");

        BrowserTool tool = tools.get(toolName);
        if (tool == null) {
            return JsonRpcResponse.error(id, JsonRpcError.METHOD_NOT_FOUND,
                    "Unknown tool: " + toolName);
        }

        log.info("Executing tool: {} with args: {}", toolName, arguments);
        ToolResult toolResult = tool.execute(arguments);

        // Serialize ToolResult into MCP content format
        ObjectNode result = mapper.createObjectNode();
        result.put("isError", toolResult.getIsError());
        ArrayNode content = result.putArray("content");
        for (ToolResult.ContentItem item : toolResult.getContent()) {
            ObjectNode ci = mapper.createObjectNode();
            ci.put("type", item.getType());
            if (item.getText() != null)     ci.put("text", item.getText());
            if (item.getData() != null)     ci.put("data", item.getData());
            if (item.getMimeType() != null) ci.put("mimeType", item.getMimeType());
            content.add(ci);
        }
        return JsonRpcResponse.success(id, result);
    }

    private JsonRpcResponse handleResourcesList(JsonNode id) {
        ObjectNode result = mapper.createObjectNode();
        result.putArray("resources");   // no static resources exposed
        return JsonRpcResponse.success(id, result);
    }

    private JsonRpcResponse handleResourcesTemplatesList(JsonNode id) {
        ObjectNode result = mapper.createObjectNode();
        result.putArray("resourceTemplates");   // no resource templates exposed
        return JsonRpcResponse.success(id, result);
    }

    private JsonRpcResponse handlePromptsList(JsonNode id) {
        ObjectNode result = mapper.createObjectNode();
        result.putArray("prompts");   // no server-side prompts exposed
        return JsonRpcResponse.success(id, result);
    }

    private String serialize(JsonRpcResponse response) {
        if (response == null) return null;
        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            log.error("Serialization error: {}", e.getMessage());
            return "{\"jsonrpc\":\"2.0\",\"error\":{\"code\":-32603,\"message\":\"Serialization error\"}}";
        }
    }
}
