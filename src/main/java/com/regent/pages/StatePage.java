package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.StateLocators;

/** Ported from regent.pages.student.StatePage. */
public class StatePage extends BasePage {

    public StatePage(Page page) {
        super(page);
    }

    public String getStateAwardAcceptedAmount(String federalAwardYear, String fund) {
        String selector = String.format(StateLocators.STATE_AWARDS_GRID_ROW_CELLS, federalAwardYear, fund);
        return page.locator(selector).nth(6).textContent().trim();
    }

    public String getStateAwardPaidAmount(String federalAwardYear, String fund) {
        String selector = String.format(StateLocators.STATE_AWARDS_GRID_ROW_CELLS, federalAwardYear, fund);
        return page.locator(selector).nth(7).textContent().trim();
    }
}
