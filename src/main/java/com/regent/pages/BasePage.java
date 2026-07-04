package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.config.ConfigReader;

public abstract class BasePage {

    protected final Page page;
    protected final int timeout;

    protected BasePage(Page page) {
        this.page = page;
        this.timeout = ConfigReader.getTimeout();
    }

    /**
     * Playwright's click()/fill() already auto-wait for the full actionability set (visible,
     * stable, enabled, receives events) before acting — that's Playwright's core model, unlike
     * Selenium where every wait must be explicit. An extra waitFor(VISIBLE) beforehand doesn't
     * change what gets waited for, it just repeats the same polling cycle twice per action, which
     * is why every click/fill in this app was taking roughly double what it needed to.
     */
    protected void click(String selector) {
        page.locator(selector).click(new Locator.ClickOptions().setTimeout(timeout));
    }

    protected void fill(String selector, String value) {
        page.locator(selector).fill(value, new Locator.FillOptions().setTimeout(timeout));
    }

    protected String getText(String selector) {
        page.locator(selector).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeout));
        return page.locator(selector).textContent().trim();
    }

    protected boolean isVisible(String selector) {
        try {
            page.locator(selector).waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(timeout));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Immediate, non-waiting visibility check — use inside polling loops that already retry on a cadence. */
    protected boolean isVisibleNow(String selector) {
        return page.locator(selector).isVisible();
    }

    protected void selectDropdownOption(String dropdownSelector, String optionText) {
        click(dropdownSelector);
        String optionSelector = String.format("//li[@role='option' and normalize-space()='%s']", optionText);
        click(optionSelector);
    }

    protected void waitForPageLoad() {
        page.waitForLoadState();
        waitForAjaxRequestToBeFinished();
    }

    /**
     * Polls for document.readyState=='complete' and (no jQuery, or jQuery.active==0).
     * Kendo tab content in this app loads via AJAX after the "load" event fires, so
     * page.waitForLoadState() alone returns before a newly-opened tab's grid has rendered.
     */
    protected void waitForAjaxRequestToBeFinished() {
        String checkScript =
                "() => document.readyState === 'complete' " +
                "&& (window.jQuery === undefined || window.jQuery.active === 0)";
        long deadline = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < deadline) {
            try {
                if (Boolean.TRUE.equals(page.evaluate(checkScript))) return;
            } catch (Exception ignored) {
                // page navigating mid-check; retry
            }
            page.waitForTimeout(250);
        }
    }
}
