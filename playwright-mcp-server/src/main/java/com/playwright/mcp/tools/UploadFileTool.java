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

@Component
public class UploadFileTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(UploadFileTool.class);
    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public UploadFileTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper  = mapper;
    }

    @Override
    public String getName() { return "browser_upload_file"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");
        props.putObject("selector")
                .put("type", "string")
                .put("description", "CSS selector or XPath for the file input element");
        props.putObject("filePath")
                .put("type", "string")
                .put("description", "Absolute path to the file to upload (e.g. D:\\files\\student_sbl.xml)");
        schema.putArray("required").add("selector").add("filePath");
        return new ToolDefinition(getName(),
                "Upload a file to a file input element using Playwright's setInputFiles(). " +
                "Use this instead of browser_fill for <input type='file'> elements.", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String selector = arguments.path("selector").asText(null);
        String filePath = arguments.path("filePath").asText(null);

        if (selector == null || selector.isBlank())
            return ToolResult.error("'selector' is required");
        if (filePath == null || filePath.isBlank())
            return ToolResult.error("'filePath' is required");

        Path path = Paths.get(filePath);
        if (!Files.exists(path))
            return ToolResult.error("File not found: " + filePath);

        try {
            Page page = browser.getPage();
            page.locator(selector).setInputFiles(path);
            log.info("Uploaded file '{}' to '{}'", path.getFileName(), selector);
            return ToolResult.text("File uploaded: " + path.getFileName() + " → " + selector);
        } catch (Exception e) {
            log.error("File upload failed: {}", e.getMessage());
            return ToolResult.error("File upload failed: " + e.getMessage());
        }
    }
}
