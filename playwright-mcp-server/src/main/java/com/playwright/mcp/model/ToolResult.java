package com.playwright.mcp.model;

import java.util.ArrayList;
import java.util.List;

public class ToolResult {

    private final List<ContentItem> content;
    private final boolean isError;

    private ToolResult(List<ContentItem> content, boolean isError) {
        this.content = content;
        this.isError = isError;
    }

    public static ToolResult text(String text) {
        return new ToolResult(List.of(ContentItem.text(text)), false);
    }

    public static ToolResult image(String base64Data, String mimeType) {
        return new ToolResult(List.of(ContentItem.image(base64Data, mimeType)), false);
    }

    public static ToolResult error(String message) {
        return new ToolResult(List.of(ContentItem.text(message)), true);
    }

    public List<ContentItem> getContent() { return content; }
    public boolean getIsError() { return isError; }

    public static class ContentItem {
        private final String type;
        private final String text;
        private final String data;
        private final String mimeType;

        private ContentItem(String type, String text, String data, String mimeType) {
            this.type = type;
            this.text = text;
            this.data = data;
            this.mimeType = mimeType;
        }

        public static ContentItem text(String text) {
            return new ContentItem("text", text, null, null);
        }

        public static ContentItem image(String base64Data, String mimeType) {
            return new ContentItem("image", null, base64Data, mimeType);
        }

        public String getType() { return type; }
        public String getText() { return text; }
        public String getData() { return data; }
        public String getMimeType() { return mimeType; }
    }
}
