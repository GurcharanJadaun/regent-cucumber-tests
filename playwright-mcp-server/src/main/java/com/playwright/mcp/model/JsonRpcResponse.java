package com.playwright.mcp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonRpcResponse {

    private final String jsonrpc = "2.0";
    private JsonNode id;
    private Object result;
    private JsonRpcError error;

    public static JsonRpcResponse success(JsonNode id, Object result) {
        JsonRpcResponse r = new JsonRpcResponse();
        r.id = id;
        r.result = result;
        return r;
    }

    public static JsonRpcResponse error(JsonNode id, int code, String message) {
        JsonRpcResponse r = new JsonRpcResponse();
        r.id = id;
        r.error = new JsonRpcError(code, message);
        return r;
    }

    public String getJsonrpc() { return jsonrpc; }
    public JsonNode getId() { return id; }
    public Object getResult() { return result; }
    public JsonRpcError getError() { return error; }
}
