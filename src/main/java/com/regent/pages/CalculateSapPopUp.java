package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.CalculateSapLocators;

/** Ported from regent.pages.CalculateSapPopUp — Calculate SAP popup + Appeal Review tab. */
public class CalculateSapPopUp extends BasePage {

    public CalculateSapPopUp(Page page) {
        super(page);
    }

    public CalculateSapPopUp clickRunSAPButton() {
        click(CalculateSapLocators.RUN_SAP_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadmaskGone();
        return this;
    }

    /** Adds a SAP record for the given period with default GPA/status values, if one doesn't already exist. */
    public CalculateSapPopUp calculateSapForPeriod(String period) {
        if (isVisibleNow(String.format(CalculateSapLocators.SAP_RECORD_GRID_ITEM, period))) {
            return this;
        }
        click(CalculateSapLocators.REVIEW_PERIOD_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(CalculateSapLocators.PERIOD_ITEM, period));
        waitForAjaxRequestToBeFinished();
        click(CalculateSapLocators.ADD_SAP_BUTTON);
        waitForAjaxRequestToBeFinished();
        fill(CalculateSapLocators.SAP_DATE, "");
        fill(CalculateSapLocators.SAP_REVIEW_PERIOD_GPA, "3.5");
        fill(CalculateSapLocators.CUMULATIVE_GPA, "3.5");
        click(CalculateSapLocators.SELECT_SAP_STATUS_DROPDOWN);
        click(String.format(CalculateSapLocators.SAP_STATUS_OPTION, "Satisfactory"));
        click(CalculateSapLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadmaskGone();
        return this;
    }

    /** Adds a SAP record for the given period with an explicit result status + note, if one doesn't already exist. */
    public CalculateSapPopUp calculateSapForPeriod(String period, String resultStatus, String note) {
        if (isVisibleNow(String.format(CalculateSapLocators.SAP_RECORD_GRID_ITEM, period))) {
            return this;
        }
        click(CalculateSapLocators.REVIEW_PERIOD_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(CalculateSapLocators.PERIOD_ITEM, period));
        waitForAjaxRequestToBeFinished();
        click(CalculateSapLocators.ADD_SAP_BUTTON);
        waitForAjaxRequestToBeFinished();
        click(CalculateSapLocators.SELECT_SAP_STATUS_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(CalculateSapLocators.SAP_STATUS_OPTION, resultStatus));
        fill(CalculateSapLocators.INPUT_NOTES, note);
        click(CalculateSapLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadmaskGone();
        return this;
    }

    /** Adds a SAP record for the given period unconditionally (no existing-record check), with an explicit SAP date. */
    public CalculateSapPopUp addSapRecordForPeriod(String period, String sapDateValue, String resultStatus, String note) {
        click(CalculateSapLocators.REVIEW_PERIOD_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(CalculateSapLocators.PERIOD_ITEM, period));
        waitForAjaxRequestToBeFinished();
        click(CalculateSapLocators.ADD_SAP_BUTTON);
        waitForAjaxRequestToBeFinished();
        fill(CalculateSapLocators.SAP_DATE, sapDateValue);
        click(CalculateSapLocators.SELECT_SAP_STATUS_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(CalculateSapLocators.SAP_STATUS_OPTION, resultStatus));
        fill(CalculateSapLocators.INPUT_NOTES, note);
        click(CalculateSapLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadmaskGone();
        return this;
    }

    public boolean getSapRecordForPeriod(String period) {
        return isVisibleNow(String.format(CalculateSapLocators.SAP_RECORD_GRID_ITEM, period));
    }

    public void closeCalculateSapPopUp() {
        click(CalculateSapLocators.CLOSE_POPUP_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** Reads the SAP record grid cell text at the column identified by gridTitle, for the row matching rowKey. */
    public String getSapRecordGridCellText(String gridTitle, String rowKey) {
        String columnIndexAttr = page.locator(String.format(CalculateSapLocators.SAP_RECORD_GRID_TITLE, gridTitle))
                .getAttribute("data-index");
        int columnNumber = Integer.parseInt(columnIndexAttr);
        Locator cells = page.locator(String.format(CalculateSapLocators.SAP_RECORD_GRID_ROW_CELLS, rowKey));
        return cells.nth(columnNumber).textContent().trim();
    }

    public boolean isResultSapStatusPpBasedPolicy(String rowKey) {
        return isVisibleNow(String.format(CalculateSapLocators.SAP_RECORD_GRID_RESULT_PP_BASED_POLICY, rowKey));
    }

    public CalculateSapPopUp clickSapGridRow(String rowKey) {
        click(String.format(CalculateSapLocators.SAP_RECORD_GRID_ITEM, rowKey));
        waitForAjaxRequestToBeFinished();
        return this;
    }

    public CalculateSapPopUp clickAppealReviewTab() {
        click(CalculateSapLocators.APPEAL_REVIEW_TAB);
        waitForAjaxRequestToBeFinished();
        return this;
    }

    public void editAppealReview(String documentDate, String decisionMaker, String appealStatus, String appealSapStatus) {
        click(CalculateSapLocators.EDIT_APPEAL_REVIEW_BUTTON);
        waitForAjaxRequestToBeFinished();
        fill(CalculateSapLocators.DOCUMENT_DATE_INPUT, documentDate);
        click(CalculateSapLocators.DECISION_MAKER_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(CalculateSapLocators.DECISION_MAKER_OPTION, decisionMaker));
        click(CalculateSapLocators.APPEAL_STATUS_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(CalculateSapLocators.APPEAL_STATUS_OPTION, appealStatus));
        click(CalculateSapLocators.APPEAL_SAP_STATUS_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(CalculateSapLocators.APPEAL_SAP_STATUS_OPTION, appealSapStatus));
        click(CalculateSapLocators.SAVE_SAP_APPEAL_BUTTON);
        waitForAjaxRequestToBeFinished();
        click(CalculateSapLocators.CLOSE_POPUP_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    private void waitForLoadmaskGone() {
        page.locator(CalculateSapLocators.LOADMASK).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(timeout));
    }
}
