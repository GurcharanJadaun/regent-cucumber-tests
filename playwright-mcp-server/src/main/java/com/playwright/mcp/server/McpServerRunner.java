package com.playwright.mcp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * STDIO-based MCP server loop.
 *
 * Reads newline-delimited JSON-RPC messages from stdin, dispatches them through
 * McpProtocolHandler, and writes responses to stdout — one JSON line per response.
 *
 * stdout is exclusively for MCP messages; all logging is routed to stderr via logback.
 */
@Component
public class McpServerRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(McpServerRunner.class);

    private final McpProtocolHandler handler;

    public McpServerRunner(McpProtocolHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Playwright MCP server starting — listening on stdin");

        // Wrap stdout in a buffered, UTF-8 writer; do NOT use auto-close
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)), true);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String line;
        while ((line = in.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            log.debug(">>> {}", line);
            try {
                String response = handler.handle(line);
                if (response != null) {
                    log.debug("<<< {}", response);
                    out.println(response);
                    // PrintWriter auto-flush is true, but flush explicitly for safety
                    out.flush();
                }
            } catch (Exception e) {
                log.error("Unhandled error processing message: {}", e.getMessage(), e);
                // Send an internal error response so the client doesn't hang
                out.println("{\"jsonrpc\":\"2.0\",\"id\":null,\"error\":{\"code\":-32603,\"message\":\"Internal server error\"}}");
                out.flush();
            }
        }

        log.info("stdin closed — MCP server shutting down");
    }
}
