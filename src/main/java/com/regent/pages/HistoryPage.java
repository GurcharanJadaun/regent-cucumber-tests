package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.HistoryLocators;

/** Ported from regent.pages.student.HistoryPage. */
public class HistoryPage extends BasePage {

    public HistoryPage(Page page) {
        super(page);
    }

    public void sortDescendingMonitorBeginDate() {
        click(HistoryLocators.MONITOR_BEGIN_HEADER);
        click(HistoryLocators.MONITOR_BEGIN_HEADER);
    }

    public String getRequestIndicator(String rowKey) {
        return getText(String.format(HistoryLocators.REQUEST_INDICATOR, rowKey));
    }

    public boolean is7DayHoldChecked(String rowKey) {
        String selector = String.format(HistoryLocators.DAY_7_HOLD, rowKey);
        return page.locator(selector).first().isChecked();
    }
}
