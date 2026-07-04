package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.api.ApiSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Sets default headers that are automatically included in every subsequent API call.
 * Use this to configure auth tokens, Content-Type, or any other persistent headers.
 * Pass clear=true to wipe all session headers.
 */
@Component
public class ApiSetHeadersTool extends ApiBaseTool {

    public ApiSetHeadersTool(ApiSessionManager api, ObjectMapper mapper) {
        super(api, mapper);
    }

    @Override
    public String getName() { return "api_set_headers"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");

        props.putObject("headers")
                .put("type", "object")
                .put("description", "Key-value pairs to set as default headers for all subsequent API calls. Set a value to empty string to remove that header.");

        props.putObject("clear")
                .put("type", "boolean")
                .put("description", "If true, clears all existing session headers before applying the new ones (default: false)");

        return new ToolDefinition(getName(),
                "Set persistent default headers for all subsequent API calls (e.g. Authorization, Content-Type). Headers are merged into each request unless overridden per-call.",
                schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        boolean clear = arguments.path("clear").asBoolean(false);
        if (clear) {
            api.clearHeaders();
        }

        JsonNode headersNode = arguments.path("headers");
        List<String> applied = new ArrayList<>();

        if (headersNode.isObject()) {
            headersNode.fields().forEachRemaining(e -> {
                api.setHeader(e.getKey(), e.getValue().asText());
                applied.add(e.getKey() + ": " + e.getValue().asText());
            });
        }

        Map<String, String> current = api.getDefaultHeaders();
        String summary = "Session headers updated.\n"
                + (clear ? "Previous headers cleared.\n" : "")
                + (applied.isEmpty() ? "" : "Applied:\n  " + String.join("\n  ", applied) + "\n")
                + "\nActive session headers:\n  "
                + (current.isEmpty() ? "(none)" : String.join("\n  ",
                        current.entrySet().stream()
                               .map(e -> e.getKey() + ": " + e.getValue())
                               .toList()));

        return ToolResult.text(summary);
    }
}
