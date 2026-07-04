package com.playwright.mcp.api;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Shared HTTP client for all API tools.
 * Holds default headers (e.g. Authorization) that persist across tool calls.
 */
@Component
public class ApiSessionManager {

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    private final Map<String, String> defaultHeaders = new ConcurrentHashMap<>();

    public void setHeader(String key, String value) {
        if (value == null || value.isBlank()) {
            defaultHeaders.remove(key);
        } else {
            defaultHeaders.put(key, value);
        }
    }

    public void clearHeaders() {
        defaultHeaders.clear();
    }

    public Map<String, String> getDefaultHeaders() {
        return new HashMap<>(defaultHeaders);
    }

    public ApiResponse execute(String method, String url, Map<String, String> extraHeaders,
                               String body, int timeoutSeconds) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(timeoutSeconds));

        defaultHeaders.forEach(builder::header);
        if (extraHeaders != null) {
            extraHeaders.forEach(builder::header);
        }

        HttpRequest.BodyPublisher bodyPublisher = (body != null && !body.isBlank())
                ? HttpRequest.BodyPublishers.ofString(body)
                : HttpRequest.BodyPublishers.noBody();

        switch (method.toUpperCase()) {
            case "GET"    -> builder.GET();
            case "POST"   -> builder.POST(bodyPublisher);
            case "PUT"    -> builder.PUT(bodyPublisher);
            case "DELETE" -> builder.DELETE();
            case "PATCH"  -> builder.method("PATCH", bodyPublisher);
            default       -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        return new ApiResponse(response.statusCode(), response.body(), response.headers().map());
    }

    public record ApiResponse(int statusCode, String body, Map<String, List<String>> headers) {
        public boolean isSuccess() {
            return statusCode >= 200 && statusCode < 300;
        }
    }
}
