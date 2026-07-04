package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.Page;
import com.playwright.mcp.browser.BrowserSessionManager;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExecuteScriptTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(ExecuteScriptTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public ExecuteScriptTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper = mapper;
    }

    @Override
    public String getName() { return "browser_execute_script"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("script")
                .put("type", "string")
                .put("description", "Inline JavaScript to execute. Use 'return' to return a value. Async/await is supported. Mutually exclusive with 'file'.");
        props.putObject("file")
                .put("type", "string")
                .put("description", "Absolute or relative path to a .js file to read and execute. Mutually exclusive with 'script'.");
        props.putObject("args")
                .put("type", "array")
                .put("description", "Optional JSON array of arguments accessible inside the script as the 'arguments' array (e.g. [\"#selector\", 42])");
        return new ToolDefinition(getName(),
                "Execute JavaScript in the page context — either inline via 'script' or from a file via 'file'. Supports multi-line scripts, async/await, arguments, and returns results as JSON.", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String script = arguments.path("script").asText(null);
        String filePath = arguments.path("file").asText(null);

        if ((script == null || script.isBlank()) && (filePath == null || filePath.isBlank())) {
            return ToolResult.error("Either 'script' (inline JS) or 'file' (path to .js file) is required");
        }

        if (filePath != null && !filePath.isBlank()) {
            try {
                Path path = Paths.get(filePath);
                if (!Files.exists(path)) {
                    return ToolResult.error("File not found: " + filePath);
                }
                script = Files.readString(path);
                log.debug("Loaded script from file: {}", filePath);
            } catch (Exception e) {
                return ToolResult.error("Failed to read file '" + filePath + "': " + e.getMessage());
            }
        }

        try {
            Page page = browser.getPage();

            // Build argument list from the optional "args" JSON array
            List<Object> argList = new ArrayList<>();
            JsonNode argsNode = arguments.path("args");
            if (argsNode.isArray()) {
                for (JsonNode arg : argsNode) {
                    if (arg.isTextual())       argList.add(arg.asText());
                    else if (arg.isBoolean())  argList.add(arg.asBoolean());
                    else if (arg.isDouble())   argList.add(arg.asDouble());
                    else if (arg.isLong())     argList.add(arg.asLong());
                    else if (arg.isInt())      argList.add(arg.asInt());
                    else if (arg.isNull())     argList.add(null);
                    else                       argList.add(arg.toString()); // object/array as JSON string
                }
            }

            // Wrap in an async IIFE so 'return' works and await is allowed at top level
            String wrapped = "(async (arguments) => { " + script + " })(" +
                    buildJsArray(argList) + ")";

            Object result;
            if (argList.isEmpty()) {
                result = page.evaluate(wrapped);
            } else {
                result = page.evaluate(wrapped);
            }

            String resultStr;
            if (result == null) {
                resultStr = "null";
            } else {
                try {
                    // Attempt to pretty-print if result is already JSON-like
                    resultStr = mapper.writeValueAsString(result);
                } catch (Exception ex) {
                    resultStr = result.toString();
                }
            }

            log.debug("execute_script result: {}", resultStr);
            return ToolResult.text(resultStr);

        } catch (Exception e) {
            log.error("execute_script failed: {}", e.getMessage());
            return ToolResult.error("Script execution failed: " + e.getMessage());
        }
    }

    private String buildJsArray(List<Object> args) {
        if (args.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < args.size(); i++) {
            if (i > 0) sb.append(",");
            Object a = args.get(i);
            if (a == null)               sb.append("null");
            else if (a instanceof String) sb.append("\"").append(((String) a).replace("\"", "\\\"")).append("\"");
            else                          sb.append(a);
        }
        sb.append("]");
        return sb.toString();
    }
}
