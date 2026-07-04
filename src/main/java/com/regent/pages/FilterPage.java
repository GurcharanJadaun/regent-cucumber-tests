package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.FilterLocators;

/**
 * Generic Kendo grid column-filter helper, ported from regent.pages.Filter. That source class was
 * a superclass (ExportProcessPage extends Filter); Playwright pages here favor composition, so
 * this is a standalone page other pages can instantiate/delegate to instead of extending.
 */
public class FilterPage extends BasePage {

    public FilterPage(Page page) {
        super(page);
    }

    /** Simple single-value filter: open the column filter, type the value, submit. */
    public void filterBy(String columnLabel, String value) {
        click(String.format(FilterLocators.FILTER_BUTTON, columnLabel));
        click(FilterLocators.FILTER_INPUT);
        fill(FilterLocators.FILTER_INPUT, value);
        click(FilterLocators.FILTER_SUBMIT);
        waitForAjaxRequestToBeFinished();
    }

    /** Filter with an explicit operator (e.g. "Is equal to") chosen from the filter-options dropdown. */
    public void filterBy(String columnLabel, String filterOption, String value) {
        click(String.format(FilterLocators.FILTER_BUTTON, columnLabel));
        click(FilterLocators.FILTER_OPTIONS_BUTTON);
        click(String.format(FilterLocators.FILTER_OPTIONS_SELECT, filterOption));
        fill(FilterLocators.FILTER_INPUT, value);
        click(FilterLocators.FILTER_SUBMIT);
        waitForAjaxRequestToBeFinished();
    }
}
