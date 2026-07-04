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

/**
 * Extracts DOM information from the current page in three formats:
 *
 *  html       - raw outer HTML of the page or a selected element
 *  text       - visible text content only (whitespace-normalized)
 *  structured - crawler-friendly JSON: title, url, meta, headings,
 *               links, forms, inputs, buttons, images, iframes
 */
@Component
public class GetDomTool implements BrowserTool {

    private static final Logger log = LoggerFactory.getLogger(GetDomTool.class);

    private final BrowserSessionManager browser;
    private final ObjectMapper mapper;

    public GetDomTool(BrowserSessionManager browser, ObjectMapper mapper) {
        this.browser = browser;
        this.mapper  = mapper;
    }

    @Override
    public String getName() { return "browser_get_dom"; }

    @Override
    public ToolDefinition getDefinition() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        ObjectNode props = schema.putObject("properties");

        props.putObject("format")
                .put("type", "string")
                .put("description",
                        "Output format: " +
                        "'html' (raw HTML, default), " +
                        "'text' (visible text only), " +
                        "'structured' (JSON with links, forms, headings, images — best for crawling)");

        props.putObject("selector")
                .put("type", "string")
                .put("description",
                        "CSS selector to scope extraction to a specific element. " +
                        "Omit to extract from the full page.");

        props.putObject("includeHidden")
                .put("type", "boolean")
                .put("description",
                        "For 'structured' format: include hidden/non-visible elements (default: false)");

        return new ToolDefinition(getName(),
                "Extract DOM information from the current page. " +
                "Use 'structured' format for crawling — returns links, forms, inputs, buttons, " +
                "headings, and images as clean JSON without needing to parse HTML.", schema);
    }

    @Override
    public ToolResult execute(JsonNode arguments) {
        String format        = arguments.path("format").asText("html");
        String selector      = arguments.path("selector").asText(null);
        boolean includeHidden = arguments.path("includeHidden").asBoolean(false);

        try {
            Page page = browser.getPage();

            if ("text".equals(format)) {
                return extractText(page, selector);
            } else if ("structured".equals(format)) {
                return extractStructured(page, selector, includeHidden);
            } else {
                return extractHtml(page, selector);
            }
        } catch (Exception e) {
            log.error("GetDom failed: {}", e.getMessage());
            return ToolResult.error("GetDom failed: " + e.getMessage());
        }
    }

    // ── HTML ────────────────────────────────────────────────────────────────

    private ToolResult extractHtml(Page page, String selector) {
        String html;
        if (selector != null && !selector.isBlank()) {
            html = (String) page.evaluate(
                    "sel => document.querySelector(sel)?.outerHTML ?? ''",
                    selector);
        } else {
            html = (String) page.evaluate("() => document.documentElement.outerHTML");
        }
        log.debug("GetDom html: {} chars", html == null ? 0 : html.length());
        return ToolResult.text(html != null ? html : "");
    }

    // ── Text ─────────────────────────────────────────────────────────────────

    private ToolResult extractText(Page page, String selector) {
        String text;
        if (selector != null && !selector.isBlank()) {
            text = (String) page.evaluate(
                    "sel => (document.querySelector(sel)?.innerText ?? '').replace(/\\s+/g, ' ').trim()",
                    selector);
        } else {
            text = (String) page.evaluate(
                    "() => document.body.innerText.replace(/\\s+/g, ' ').trim()");
        }
        log.debug("GetDom text: {} chars", text == null ? 0 : text.length());
        return ToolResult.text(text != null ? text : "");
    }

    // ── Structured ──────────────────────────────────────────────────────────

    private ToolResult extractStructured(Page page, String selector, boolean includeHidden) {
        String script = buildStructuredScript(selector, includeHidden);
        Object result = page.evaluate(script);

        String json;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        } catch (Exception e) {
            json = result != null ? result.toString() : "{}";
        }
        log.debug("GetDom structured: {} chars", json.length());
        return ToolResult.text(json);
    }

    private String buildStructuredScript(String selector, boolean includeHidden) {
        String root = (selector != null && !selector.isBlank())
                ? "document.querySelector(" + jsStr(selector) + ") || document"
                : "document";

        String visibilityFilter = includeHidden
                ? ""
                : "function isVisible(el) { const s = window.getComputedStyle(el); return s.display !== 'none' && s.visibility !== 'hidden' && el.offsetParent !== null; }";

        String visCheck = includeHidden ? "true" : "isVisible(el)";

        return "(() => {" +
            visibilityFilter +
            "const root = " + root + ";" +
            "const doc  = root.ownerDocument || root;" +

            // Meta
            "const metaTags = {};" +
            "doc.querySelectorAll('meta[name],meta[property]').forEach(m => {" +
            "  const k = m.getAttribute('name') || m.getAttribute('property');" +
            "  if (k) metaTags[k] = m.getAttribute('content') || '';" +
            "});" +

            // Headings
            "const headings = [];" +
            "root.querySelectorAll('h1,h2,h3,h4,h5,h6').forEach(el => {" +
            "  if (" + visCheck + ") headings.push({ level: el.tagName.toLowerCase(), text: el.innerText.trim() });" +
            "});" +

            // Links
            "const links = [];" +
            "root.querySelectorAll('a[href]').forEach(el => {" +
            "  if (" + visCheck + ") links.push({" +
            "    text: el.innerText.trim()," +
            "    href: el.href," +
            "    title: el.title || null," +
            "    target: el.target || null" +
            "  });" +
            "});" +

            // Images
            "const images = [];" +
            "root.querySelectorAll('img').forEach(el => {" +
            "  if (" + visCheck + ") images.push({" +
            "    src: el.src," +
            "    alt: el.alt || null," +
            "    width: el.naturalWidth || null," +
            "    height: el.naturalHeight || null" +
            "  });" +
            "});" +

            // Buttons & clickables
            "const buttons = [];" +
            "root.querySelectorAll('button,input[type=button],input[type=submit],input[type=reset],[role=button]').forEach(el => {" +
            "  if (" + visCheck + ") buttons.push({" +
            "    tag: el.tagName.toLowerCase()," +
            "    text: (el.innerText || el.value || '').trim()," +
            "    type: el.type || null," +
            "    id: el.id || null," +
            "    name: el.name || null," +
            "    disabled: el.disabled || false" +
            "  });" +
            "});" +

            // Inputs (excluding buttons)
            "const inputs = [];" +
            "root.querySelectorAll('input:not([type=button]):not([type=submit]):not([type=reset]),textarea,select').forEach(el => {" +
            "  if (" + visCheck + ") inputs.push({" +
            "    tag: el.tagName.toLowerCase()," +
            "    type: el.type || null," +
            "    id: el.id || null," +
            "    name: el.name || null," +
            "    placeholder: el.placeholder || null," +
            "    value: el.value || null," +
            "    required: el.required || false," +
            "    disabled: el.disabled || false," +
            "    selector: el.id ? '#'+el.id : (el.name ? '[name=\"'+el.name+'\"]' : el.tagName.toLowerCase())" +
            "  });" +
            "});" +

            // Forms
            "const forms = [];" +
            "root.querySelectorAll('form').forEach(form => {" +
            "  const fields = [];" +
            "  form.querySelectorAll('input,textarea,select').forEach(el => {" +
            "    fields.push({" +
            "      tag: el.tagName.toLowerCase()," +
            "      type: el.type || null," +
            "      name: el.name || null," +
            "      id: el.id || null," +
            "      required: el.required || false," +
            "      selector: el.id ? '#'+el.id : (el.name ? '[name=\"'+el.name+'\"]' : el.tagName.toLowerCase())" +
            "    });" +
            "  });" +
            "  forms.push({" +
            "    id: form.id || null," +
            "    action: form.action || null," +
            "    method: (form.method || 'get').toUpperCase()," +
            "    fields: fields" +
            "  });" +
            "});" +

            // iFrames
            "const iframes = [];" +
            "root.querySelectorAll('iframe').forEach(el => {" +
            "  iframes.push({ src: el.src || null, title: el.title || null, id: el.id || null });" +
            "});" +

            "return {" +
            "  url:      doc.location ? doc.location.href : null," +
            "  title:    doc.title || null," +
            "  meta:     metaTags," +
            "  headings: headings," +
            "  links:    links," +
            "  images:   images," +
            "  buttons:  buttons," +
            "  inputs:   inputs," +
            "  forms:    forms," +
            "  iframes:  iframes" +
            "};" +
            "})()";
    }

    private String jsStr(String s) {
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }
}
