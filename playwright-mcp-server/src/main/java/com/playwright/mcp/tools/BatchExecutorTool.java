package com.playwright.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.playwright.mcp.model.ToolDefinition;
import com.playwright.mcp.model.ToolResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Executes a sequence of browser tool steps defined in a JSON array.
 *
 * Supports resumability: if a step fails the AI can handle it independently,
 * then re-submit the same steps array with startFrom pointing to the next step
 * to continue without re-running already-completed steps.
 *
 * Input schema:
 * {
 *   "steps": [
 *     { "id": "login",   "tool": "browser_navigate", "args": { "url": "https://app.com" } },
 *     { "id": "fill",    "tool": "browser_fill",     "args": { "selector": "#user", "value": "admin" } }
 *   ],
 *   "stopOnError": true,    // default true
 *   "startFrom": "fill"     // resume from step id OR 1-based index (e.g. 2)
 * }
 */
@Component
public class BatchExecutorTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(BatchExecutorTool.class);

    private final ObjectMapper mapper;
    private final Map<String, BrowserTool> tools;

    public BatchExecutorTool(ObjectMapper mapper, @Lazy List<BrowserTool> toolList) {
        this.mapper = mapper;
        this.tools = toolList.stream()
                .filter(t -> !t.getName().equals(getName()))
                .collect(Collectors.toMap(BrowserTool::getName, Function.identity()));
    }

    @Override
    public String getName() { return "browser_batch_execute"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");

        // steps
        ObjectNode stepsSchema = props.putObject("steps");
        stepsSchema.put("type", "array");
        stepsSchema.put("description", "Ordered list of browser tool steps to execute sequentially");
        ObjectNode stepItem = stepsSchema.putObject("items");
        stepItem.put("type", "object");
        ObjectNode stepProps = stepItem.putObject("properties");
        stepProps.putObject("id")
                .put("type", "string")
                .put("description", "Unique label for this step — required if you plan to use startFrom");
        stepProps.putObject("tool")
                .put("type", "string")
                .put("description", "Name of the browser tool to call (e.g. browser_navigate, browser_click)");
        stepProps.putObject("args")
                .put("type", "object")
                .put("description", "Arguments to pass to the tool");
        stepItem.putArray("required").add("tool");

        // stopOnError
        props.putObject("stopOnError")
                .put("type", "boolean")
                .put("description", "Stop on first failure and return results so far (default: true). Set false to run all steps regardless.");

        // startFrom — key new feature
        props.putObject("startFrom")
                .put("type", "string")
                .put("description",
                        "Resume execution from this step, skipping all earlier steps. " +
                        "Accepts either a step 'id' string (e.g. \"fill-password\") or a 1-based index as a string (e.g. \"3\"). " +
                        "Steps before startFrom are recorded as status=skipped in the result. " +
                        "Use this after the AI has fixed a failed step to continue the batch without re-running completed steps.");

        schema.putArray("required").add("steps");

        return new ToolDefinition(getName(),
                "Execute multiple browser tool steps in sequence. Supports resumability via startFrom: " +
                "after a failure the AI fixes the issue then re-submits the same steps with startFrom=<next step id or index>. " +
                "Returns per-step status and a summary.", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        JsonNode stepsNode = arguments.path("steps");
        if (!stepsNode.isArray() || stepsNode.isEmpty()) {
            return ToolResult.error("'steps' must be a non-empty array");
        }

        boolean stopOnError = arguments.path("stopOnError").asBoolean(true);

        // Resolve startFrom to a 0-based index
        int startIndex = resolveStartFrom(arguments.path("startFrom"), stepsNode);
        if (startIndex < 0) {
            return ToolResult.error("startFrom value '" + arguments.path("startFrom").asText() +
                    "' does not match any step id or valid index");
        }

        ArrayNode resultsArray = mapper.createArrayNode();
        int passed = 0, failed = 0, skipped = 0;

        for (int i = 0; i < stepsNode.size(); i++) {
            JsonNode step   = stepsNode.get(i);
            String toolName = step.path("tool").asText(null);
            String stepId   = step.path("id").asText("step-" + (i + 1));
            JsonNode args   = step.path("args");

            ObjectNode stepResult = mapper.createObjectNode();
            stepResult.put("id",    stepId);
            stepResult.put("tool",  toolName != null ? toolName : "");
            stepResult.put("index", i + 1);

            // Skip steps before startFrom
            if (i < startIndex) {
                stepResult.put("status", "skipped");
                stepResult.put("reason", "resumed — step already completed before startFrom");
                resultsArray.add(stepResult);
                skipped++;
                continue;
            }

            // Validate tool name
            if (toolName == null || toolName.isBlank()) {
                stepResult.put("status", "failed");
                stepResult.put("error", "Missing 'tool' field in step");
                resultsArray.add(stepResult);
                failed++;
                if (stopOnError) {
                    skipped += countRemaining(stepsNode.size(), i);
                    appendSkippedSteps(resultsArray, stepsNode, i + 1);
                    log.warn("Batch stopped at step {} — missing tool name", i + 1);
                    break;
                }
                continue;
            }

            BrowserTool tool = tools.get(toolName);
            if (tool == null) {
                stepResult.put("status", "failed");
                stepResult.put("error", "Unknown tool: " + toolName);
                resultsArray.add(stepResult);
                failed++;
                if (stopOnError) {
                    skipped += countRemaining(stepsNode.size(), i);
                    appendSkippedSteps(resultsArray, stepsNode, i + 1);
                    log.warn("Batch stopped at step {} — unknown tool '{}'", i + 1, toolName);
                    break;
                }
                continue;
            }

            log.info("Batch step {}/{}: {} args={}", i + 1, stepsNode.size(), toolName, args);
            try {
                ToolResult result = tool.execute(
                        args.isNull() || args.isMissingNode() ? mapper.createObjectNode() : args);

                String output = result.getContent().stream()
                        .filter(c -> "text".equals(c.getType()))
                        .map(ToolResult.ContentItem::getText)
                        .findFirst()
                        .orElse("");

                if (result.getIsError()) {
                    stepResult.put("status", "failed");
                    stepResult.put("error", output);
                    // Tell AI exactly where to resume after fixing
                    stepResult.put("resumeHint",
                            "Fix this step, then call browser_batch_execute with startFrom=\"" + nextStepId(stepsNode, i) + "\"");
                    failed++;
                    log.warn("Batch step {} '{}' FAILED: {}", i + 1, stepId, output);
                } else {
                    stepResult.put("status", "passed");
                    stepResult.put("result", output);
                    passed++;
                    log.info("Batch step {} '{}' PASSED", i + 1, stepId);
                }
            } catch (Exception e) {
                stepResult.put("status", "failed");
                stepResult.put("error", "Unexpected error: " + e.getMessage());
                stepResult.put("resumeHint",
                        "Fix this step, then call browser_batch_execute with startFrom=\"" + nextStepId(stepsNode, i) + "\"");
                failed++;
                log.error("Batch step {} threw exception: {}", i + 1, e.getMessage(), e);
            }

            resultsArray.add(stepResult);

            if (failed > 0 && stopOnError) {
                skipped += countRemaining(stepsNode.size(), i);
                appendSkippedSteps(resultsArray, stepsNode, i + 1);
                break;
            }
        }

        // Summary
        ObjectNode response = mapper.createObjectNode();
        response.set("steps", resultsArray);
        ObjectNode summary = response.putObject("summary");
        summary.put("total",   stepsNode.size());
        summary.put("passed",  passed);
        summary.put("failed",  failed);
        summary.put("skipped", skipped);
        summary.put("success", failed == 0);
        if (failed > 0) {
            summary.put("note",
                    "One or more steps failed. Fix the failing step(s) using other browser tools, " +
                    "then re-submit this batch with startFrom set to the step id or index after the last failure.");
        }

        log.info("Batch complete — {} passed, {} failed, {} skipped", passed, failed, skipped);

        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            return failed > 0 ? ToolResult.error(json) : ToolResult.text(json);
        } catch (Exception e) {
            return ToolResult.error("Failed to serialize batch results: " + e.getMessage());
        }
    }

    /**
     * Resolves startFrom (step id string or 1-based index string) to a 0-based array index.
     * Returns 0 if startFrom is absent/blank (run from the beginning).
     * Returns -1 if the value is set but doesn't match anything.
     */
    private int resolveStartFrom(JsonNode startFromNode, JsonNode stepsNode) {
        if (startFromNode.isMissingNode() || startFromNode.isNull()) return 0;
        String startFrom = startFromNode.asText("").trim();
        if (startFrom.isEmpty()) return 0;

        // Try numeric index (1-based)
        try {
            int idx = Integer.parseInt(startFrom);
            if (idx >= 1 && idx <= stepsNode.size()) return idx - 1;
            return -1;
        } catch (NumberFormatException ignored) { }

        // Try matching step id
        for (int i = 0; i < stepsNode.size(); i++) {
            String id = stepsNode.get(i).path("id").asText(null);
            if (startFrom.equals(id)) return i;
        }
        return -1;
    }

    /** Returns the id (or fallback index label) of the step AFTER index i. */
    private String nextStepId(JsonNode stepsNode, int currentIndex) {
        int next = currentIndex + 1;
        if (next >= stepsNode.size()) return "N/A — this was the last step";
        String id = stepsNode.get(next).path("id").asText(null);
        return (id != null && !id.isBlank()) ? id : String.valueOf(next + 1);
    }

    private int countRemaining(int total, int currentIndex) {
        return Math.max(0, total - currentIndex - 1);
    }

    private void appendSkippedSteps(ArrayNode results, JsonNode stepsNode, int fromIndex) {
        for (int i = fromIndex; i < stepsNode.size(); i++) {
            JsonNode step = stepsNode.get(i);
            ObjectNode sr = mapper.createObjectNode();
            sr.put("id",     step.path("id").asText("step-" + (i + 1)));
            sr.put("tool",   step.path("tool").asText(""));
            sr.put("index",  i + 1);
            sr.put("status", "skipped");
            sr.put("reason", "batch stopped due to earlier failure");
            results.add(sr);
        }
    }
}
