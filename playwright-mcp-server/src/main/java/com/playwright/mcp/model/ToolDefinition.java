package com.playwright.mcp.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ToolDefinition {

    private final String name;
    private final String description;
    private final ObjectNode inputSchema;

    public ToolDefinition(String name, String description, ObjectNode inputSchema) {
        this.name = name;
        this.description = description;
        this.inputSchema = inputSchema;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public ObjectNode getInputSchema() { return inputSchema; }
}
