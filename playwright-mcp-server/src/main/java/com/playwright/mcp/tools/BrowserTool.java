package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;

/**
 * Contract for all MCP browser tools.
 */
public interface BrowserTool {

    /** Unique tool name exposed in tools/list */
    String getName();

    /** MCP tool definition (name, description, inputSchema) */
    ToolDefinition getDefinition();

    /** Execute the tool with the given arguments JSON node */
    ToolResult execute(JsonNode arguments);
}
