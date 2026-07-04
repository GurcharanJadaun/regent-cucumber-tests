package com.regent.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.PWDLocators;

/** Ported from regent.pages.PWDPopUp (Return of Title IV / PWD worksheet popup). */
public class PWDPopUp extends BasePage {

    public PWDPopUp(Page page) {
        super(page);
    }

    public String getPwdStudentName() {
        return getText(PWDLocators.STUDENT_NAME);
    }

    public String getPwdDateFormCompleted() {
        return getText(PWDLocators.DATE_FORM_COMPLETED);
    }

    public String getDateSchoolDeterminedStudentWithdrew() {
        return getText(PWDLocators.DATE_SCHOOL_DETERMINED_WITHDREW);
    }

    public boolean is180Indicator() {
        return isVisibleNow(PWDLocators.MORE_THAN_180_DAYS_INDICATOR);
    }

    public void addPwdComment(String comment) {
        click(PWDLocators.ADD_COMMENT_BUTTON);
        // Source note: the first click sometimes doesn't open the comment box; retry once.
        if (!isVisibleNow(PWDLocators.COMMENT_TEXTAREA)) {
            click(PWDLocators.ADD_COMMENT_BUTTON);
        }
        fill(PWDLocators.COMMENT_TEXTAREA, comment);
        click(PWDLocators.SAVE_COMMENT_BUTTON);
    }

    public boolean isManualIndicatorInPwdCell(String rowKey, int columnPosition) {
        return isVisibleNow(String.format(PWDLocators.PWD_TABLE_MANUAL_ICON_AT_POSITION, rowKey, columnPosition));
    }

    public boolean isDoNotOfferChangeMessage() {
        return isVisibleNow(String.format(PWDLocators.DO_NOT_OFFER_CHANGE_WARNING, PWDLocators.DO_NOT_OFFER_CHANGE_MESSAGE));
    }

    public boolean isAnyPwdFieldsEditable() {
        return isVisibleNow(PWDLocators.ACTUAL_CANCEL_DATE_INPUT)
                || isVisibleNow(PWDLocators.DATE_PWD_RESPONSE_INPUT)
                || isVisibleNow(PWDLocators.NET_ACCEPTED_AMOUNT_INPUT)
                || isVisibleNow(PWDLocators.DO_NOT_OFFER_CHECKBOX_INPUT)
                || isVisibleNow(PWDLocators.RETURN_AMOUNT_REASON_DROPDOWN);
    }

    public void editActualCancelDate(String cancelDate) {
        fill(PWDLocators.ACTUAL_CANCEL_DATE_INPUT, cancelDate);
    }

    public void editDatePWDResponse(String responseDate) {
        fill(PWDLocators.DATE_PWD_RESPONSE_INPUT, responseDate);
    }

    /**
     * The amount input swaps to a second, differently-positioned <input> once focused — Kendo
     * quirk confirmed in the source (KLS 1/26/2023 comment). Click the row's step-1 input first
     * to trigger that swap, then clear and type into the step-2 input that replaces it.
     */
    public void editNetAcceptedAmount(String rowKey, String netAmount) {
        click(String.format(PWDLocators.NET_AMOUNT_ACCEPTED_INPUT_STEP1, rowKey));
        com.microsoft.playwright.Locator step2 = page.locator(PWDLocators.NET_AMOUNT_ACCEPTED_INPUT_STEP2);
        step2.waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE).setTimeout(timeout));
        step2.fill(netAmount);
    }

    public void editDoNotOffer(boolean check) {
        com.microsoft.playwright.Locator checkbox = page.locator(PWDLocators.DO_NOT_OFFER_CHECKBOX_INPUT);
        if (checkbox.isChecked() != check) checkbox.click();
    }

    public void clickEdit() {
        click(PWDLocators.EDIT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public boolean isPwdEditable() {
        return isVisibleNow(PWDLocators.EDIT_BUTTON);
    }

    public void clickSave() {
        click(PWDLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickCancel() {
        click(PWDLocators.CANCEL_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void editReturnAmountReason(String reason) {
        click(PWDLocators.RETURN_AMOUNT_REASON_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(PWDLocators.RETURN_AMOUNT_REASON_OPTION, reason));
    }

    public String getPwdTableGridCellValue(String rowKey, int columnPosition) {
        waitForAjaxRequestToBeFinished();
        return getText(String.format(PWDLocators.PWD_TABLE_CELL_AT_POSITION, rowKey, columnPosition));
    }

    public boolean isDoNotOfferChecked(String rowKey) {
        return page.locator(String.format(PWDLocators.DO_NOT_OFFER_CHECKBOX, rowKey)).isChecked();
    }

    /** Clicks Finalize and waits for the loading indicator and Finalize button to both clear. */
    public void clickFinalize() {
        click(PWDLocators.FINALIZE_BUTTON);
        waitForAjaxRequestToBeFinished();
        for (int i = 0; i <= 60; i++) {
            page.waitForTimeout(2000);
            if (!isVisibleNow(PWDLocators.LOADING_INDICATOR) && !isVisibleNow(PWDLocators.FINALIZE_BUTTON)) break;
        }
    }

    public void clickReverse() {
        click(PWDLocators.REVERSE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public String getNetAmountAccepted(String rowKey) {
        return getText(String.format(PWDLocators.NET_AMOUNT_ACCEPTED_CELL, rowKey));
    }

    public String getBox1Value() {
        return getText(PWDLocators.BOX1_VALUE);
    }

    public String getBox2Value() {
        return getText(PWDLocators.BOX2_VALUE);
    }
}
