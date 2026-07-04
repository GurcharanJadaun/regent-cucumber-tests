package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ReportBaseLocators;

/**
 * Ported from regent.pages.reports.ReportBasePage. Common SSRS report-viewer chrome
 * (Enterprise/Institution filters, view/find controls) shared by report page objects
 * such as ISIRUnmatchedPage.
 */
public class ReportBasePage extends BasePage {

    public ReportBasePage(Page page) {
        super(page);
    }

    /** Polls the SSRS "Loading..." indicator until it disappears (report render can be slow). */
    protected void waitForReportReady() {
        for (int i = 0; i < 120; i++) {
            if (!isVisibleNow(ReportBaseLocators.LOADING_INDICATOR)) return;
            page.waitForTimeout(1000);
        }
    }
}
