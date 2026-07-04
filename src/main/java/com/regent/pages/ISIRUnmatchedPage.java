package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ISIRUnmatchedLocators;
import com.regent.locators.ReportBaseLocators;

/**
 * Ported from regent.pages.reports.ISIRUnmatchedPage. The source constructor polled for the
 * Enterprise dropdown to become interactable before returning (SSRS report viewer can take a
 * while to finish its own initial render); reproduced here as a separate readiness wait since
 * Playwright page objects are constructed independently of navigation timing.
 */
public class ISIRUnmatchedPage extends ReportBasePage {

    public ISIRUnmatchedPage(Page page) {
        super(page);
    }

    /** Call after navigating to the report — waits for the Enterprise dropdown to be ready. */
    public void waitUntilReady() {
        waitForAjaxRequestToBeFinished();
        for (int i = 0; i < 240; i++) {
            page.waitForTimeout(1000);
            if (isVisibleNow(ReportBaseLocators.ENTERPRISE_DROPDOWN)) break;
        }
    }

    public void setEnterprise(String enterpriseName) {
        click(ReportBaseLocators.ENTERPRISE_DROPDOWN);
        click(String.format(ReportBaseLocators.ENTERPRISE_OPTION, enterpriseName));
        waitForAjaxRequestToBeFinished();
    }

    public void setInstitution() {
        click(ReportBaseLocators.INSTITUTION_INPUT);
        click(ReportBaseLocators.INSTITUTION_CHECKBOX);
        click(ReportBaseLocators.ENTERPRISE_LABEL);
        waitForAjaxRequestToBeFinished();
    }

    public void setFederalAwardYear(String federalAwardYear) {
        click(ISIRUnmatchedLocators.FEDERAL_AWARD_YEAR_DROPDOWN);
        click(String.format(ISIRUnmatchedLocators.FEDERAL_AWARD_YEAR_OPTION, federalAwardYear));
        click(ReportBaseLocators.ENTERPRISE_LABEL);
        waitForAjaxRequestToBeFinished();
    }

    public void viewReport() {
        click(ReportBaseLocators.VIEW_REPORT_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForReportReady();
    }

    public void findText(String data) {
        fill(ReportBaseLocators.FIND_TEXT_INPUT, data);
        click(ReportBaseLocators.FIND_LINK);
        waitForAjaxRequestToBeFinished();
    }

    /**
     * Source used clickAcceptWithAlertMessage() to grab the native "not found" alert text.
     * Playwright surfaces dialogs via a page-level event, not a return value from click(), so the
     * caller must register page.onDialog(...) before invoking this if the message text is needed;
     * this just drives the search and dismisses the dialog.
     */
    public void searchForNonExistentText(String data) {
        fill(ReportBaseLocators.FIND_TEXT_INPUT, data);
        page.onceDialog(dialog -> dialog.accept());
        click(ReportBaseLocators.FIND_LINK);
        waitForAjaxRequestToBeFinished();
    }

    public boolean isStudentFound(String studentKey) {
        fill(ReportBaseLocators.FIND_TEXT_INPUT, studentKey);
        click(ReportBaseLocators.FIND_LINK);
        waitForAjaxRequestToBeFinished();
        waitForReportReady();
        String selector = String.format(ISIRUnmatchedLocators.REPORT_ROW, studentKey);
        return isVisible(selector);
    }
}
