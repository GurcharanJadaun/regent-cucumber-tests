package com.playwright.mcp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonRpcRequest {

    private String jsonrpc;
    private JsonNode id;        // can be string, number, or null
    private String method;
    private JsonNode params;

    public String getJsonrpc() { return jsonrpc; }
    public void setJsonrpc(String jsonrpc) { this.jsonrpc = jsonrpc; }

    public JsonNode getId() { return id; }
    public void setId(JsonNode id) { this.id = id; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public JsonNode getParams() { return params; }
    public void setParams(JsonNode params) { this.params = params; }

    public boolean isNotification() {
        return id == null || id.isNull();
    }
}
