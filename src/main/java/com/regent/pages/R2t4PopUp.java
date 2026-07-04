package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.R2t4PopUpLocators;

/**
 * Ported from regent.pages.R2t4PopUp. Methods that returned source-only page types with no port
 * yet (AvailableR2T4sPopUp, PWDPopUp) return void instead; grant/loan name enums (R2T4GrantName,
 * R2T4LoanName) are replaced with plain label strings since those enums aren't ported.
 */
public class R2t4PopUp extends BasePage {

    public R2t4PopUp(Page page) {
        super(page);
    }

    // ── History grid ─────────────────────────────────────────────────────────

    public String getR2T4HistoryGridDateOfDetermination(String rowKey) {
        return nthCellText(R2t4PopUpLocators.R2T4_HISTORY_ROW_CELLS, rowKey, 1);
    }

    public String getR2T4HistoryGridWithdrawalDate(String rowKey) {
        return nthCellText(R2t4PopUpLocators.R2T4_HISTORY_ROW_CELLS, rowKey, 2);
    }

    public String getR2T4HistoryGridStatus(String rowKey) {
        return nthCellText(R2t4PopUpLocators.R2T4_HISTORY_ROW_CELLS, rowKey, 5);
    }

    public String getR2T4HistoryGridResult(String rowKey) {
        return nthCellText(R2t4PopUpLocators.R2T4_HISTORY_ROW_CELLS, rowKey, 9);
    }

    private String nthCellText(String template, String rowKey, int elementNumber) {
        String selector = String.format(template, rowKey);
        return page.locator(selector).nth(elementNumber - 1).textContent().trim();
    }

    // ── Navigation / setup ───────────────────────────────────────────────────

    public void scrollToStep(int step) {
        String selector = String.format(R2t4PopUpLocators.STEP_HEADING, "STEP " + step);
        page.locator(selector).first().scrollIntoViewIfNeeded();
    }

    public void setUse50PercentCheckBox(boolean check) {
        setChecked(R2t4PopUpLocators.USE_50_PERCENT_CHECKBOX, check);
    }

    public void setBlockDisbursementCheckBox(boolean check) {
        setChecked(R2t4PopUpLocators.BLOCK_DISBURSEMENTS_CHECKBOX, check);
    }

    private void setChecked(String selector, boolean checked) {
        if (checked) {
            page.locator(selector).check();
        } else {
            page.locator(selector).uncheck();
        }
    }

    // ── Step 1 ───────────────────────────────────────────────────────────────

    public String getStep1PellRecalculationItem(String labelName, boolean inEditMode) {
        return step1TableColText("Pell Recalculation", labelName, inEditMode, 1, 0);
    }

    public String getStep1TitleIVGrantAmountDisbursed(String grantName, boolean inEditMode) {
        return step1TableColText("Title IV Grant", grantName, inEditMode, 1, 0);
    }

    public String getStep1TitleIVGrantNetInadvertentOverPayment(String grantName, boolean inEditMode) {
        return step1TableColText("Title IV Grant", grantName, inEditMode, 2, 1);
    }

    public String getStep1TitleIVGrantAmountCouldHaveBeenDisbursed(String grantName, boolean inEditMode) {
        return step1TableColText("Title IV Grant", grantName, inEditMode, 3, 2);
    }

    public String getStep1TitleIVLoanGrossAwardAmount(String loanName, boolean inEditMode) {
        return step1TableColText("Title IV Loan", loanName, inEditMode, 1, 0);
    }

    public String getStep1TitleIVLoanNetAmountDisbursed(String loanName, boolean inEditMode) {
        return step1TableColText("Title IV Loan", loanName, inEditMode, 2, 1);
    }

    public String getStep1TitleIVLoanNetInadvertentOverPayment(String loanName, boolean inEditMode) {
        return step1TableColText("Title IV Loan", loanName, inEditMode, 3, 2);
    }

    public String getStep1TitleIVLoanAmountCouldHaveBeenDisbursed(String loanName, boolean inEditMode) {
        return step1TableColText("Title IV Loan", loanName, inEditMode, 4, 3);
    }

    private String step1TableColText(String section, String labelName, boolean inEditMode, int editIndex, int viewIndex) {
        String template = inEditMode ? R2t4PopUpLocators.R2T4_EDIT_STEP1_TABLE_COL : R2t4PopUpLocators.R2T4_STEP1_TABLE_COL;
        int index = inEditMode ? editIndex : viewIndex;
        String selector = String.format(template, section, labelName);
        return page.locator(selector).nth(index).textContent().trim();
    }

    public String getStep1GTotalTitleIVAidTableItem(String labelName) {
        String selector = String.format(R2t4PopUpLocators.STEP1_G_TOTAL_TITLE_IV_AID_TABLE_ITEM, labelName);
        return getText(selector);
    }

    public String getStep2HValue(boolean inEditMode) {
        if (inEditMode) {
            return page.locator(R2t4PopUpLocators.STEP2_H_INPUT).getAttribute("value");
        } else {
            return getText(R2t4PopUpLocators.STEP2_H_VALUE);
        }
    }

    // ── Step 3 & 4 ───────────────────────────────────────────────────────────

    public String getStep3Box(String boxLabel) {
        String selector = String.format(R2t4PopUpLocators.STEP3_BOX, boxLabel);
        return getText(selector);
    }

    public String getStep3TotalI() {
        return getText(R2t4PopUpLocators.STEP3_TOTAL_I_BOX);
    }

    public String getStep4JBox(String boxLabel) {
        String selector = String.format(R2t4PopUpLocators.STEP4_BOX, "J.", boxLabel);
        return getText(selector);
    }

    public String getStep4TotalJ() {
        String selector = String.format(R2t4PopUpLocators.STEP4_TOTAL_BOX, "J.");
        return getText(selector);
    }

    public String getStep4KBox(String boxLabel) {
        String selector = String.format(R2t4PopUpLocators.STEP4_BOX, "K.", boxLabel);
        return getText(selector);
    }

    public String getStep4TotalK() {
        String selector = String.format(R2t4PopUpLocators.STEP4_TOTAL_BOX, "K.");
        return getText(selector);
    }

    // ── Step 5 ───────────────────────────────────────────────────────────────

    public void editStep5AndCalculate(String tuition, String room, String board) {
        fill(R2t4PopUpLocators.STEP5_TUITION_INPUT, tuition);
        fill(R2t4PopUpLocators.STEP5_ROOM_INPUT, room);
        fill(R2t4PopUpLocators.STEP5_BOARD_INPUT, board);
        click(R2t4PopUpLocators.STEP5_CALCULATE_BUTTON);
    }

    public String getStep5Tuition() {
        scrollToStep(6);
        String selector = String.format(R2t4PopUpLocators.STEP5_INSTITUTIONAL_CHARGES_TABLE_ITEM, "Tuition");
        return getText(selector);
    }

    public String getStep5Room() {
        String selector = String.format(R2t4PopUpLocators.STEP5_INSTITUTIONAL_CHARGES_TABLE_ITEM, "Room");
        return getText(selector);
    }

    public String getStep5Board() {
        String selector = String.format(R2t4PopUpLocators.STEP5_INSTITUTIONAL_CHARGES_TABLE_ITEM, "Board");
        return getText(selector);
    }

    public String getStep5HBox() {
        String selector = String.format(R2t4PopUpLocators.STEP5_BOX, "H", "H");
        return getText(selector);
    }

    public String getStep5MTotal() {
        // can't use M because it also appears in N
        String selector = String.format(R2t4PopUpLocators.STEP5_TOTAL_BOX, "H");
        return getText(selector);
    }

    public String getStep5LBox() {
        String selector = String.format(R2t4PopUpLocators.STEP5_BOX, "N", "L");
        return getText(selector);
    }

    public String getStep5NMBox() {
        String selector = String.format(R2t4PopUpLocators.STEP5_BOX, "N", "M");
        return getText(selector);
    }

    public String getStep5NTotal() {
        String selector = String.format(R2t4PopUpLocators.STEP5_TOTAL_BOX, "N");
        return getText(selector);
    }

    public String getStep5OValue() {
        return getText(R2t4PopUpLocators.STEP5_O_BOX_VALUE);
    }

    // ── Step 6 ───────────────────────────────────────────────────────────────

    public String getStep6ReturnsFromNetDisbursedAmounts(String loanName) {
        String selector = String.format(R2t4PopUpLocators.STEP6_RETURN_AMOUNTS, loanName);
        return getText(selector);
    }

    public String getStep6AdditionalReturnsUnearnedIOP(String loanName) {
        String selector = String.format(R2t4PopUpLocators.STEP6_RETURN_UNEARNED_IOP, loanName);
        return getText(selector);
    }

    public String getStep6AmountToBeReturned(String grantName) {
        String selector = String.format(R2t4PopUpLocators.STEP6_RETURN_AMOUNTS, grantName);
        return getText(selector);
    }

    public String getStep6UnearnedNetIOPToReturn(String grantName) {
        String selector = String.format(R2t4PopUpLocators.STEP6_RETURN_UNEARNED_IOP, grantName);
        return getText(selector);
    }

    public String getStep6LoanTotalNetDisbursementAmounts() {
        scrollToItemToReturn("Total Amounts to Return");
        return getText(R2t4PopUpLocators.STEP6_TOTAL_LOANS_NET_DISBURSED_RETURN_AMOUNT);
    }

    public String getStep6TotalAmountsToReturn() {
        scrollToItemToReturn("Total Amounts to Return");
        String selector = String.format(R2t4PopUpLocators.STEP6_ITEM_TO_RETURN, "Total Amounts to Return");
        return getText(selector);
    }

    private void scrollToItemToReturn(String label) {
        String selector = String.format(R2t4PopUpLocators.STEP6_ITEM_TO_RETURN, label);
        page.locator(selector).first().scrollIntoViewIfNeeded();
    }

    // ── Step 9 ───────────────────────────────────────────────────────────────

    public String getStep9TFBox() {
        scrollToStep(10);
        return getText(R2t4PopUpLocators.STEP9_TF_VALUE);
    }

    public String getStep9TPercentBox() {
        scrollToStep(10);
        return getText(R2t4PopUpLocators.STEP9_T_PERCENT_VALUE);
    }

    public String getStep9TTBox() {
        scrollToStep(10);
        return getText(R2t4PopUpLocators.STEP9_TT_VALUE);
    }

    // ── Status / result queries ──────────────────────────────────────────────

    public boolean confirmCompletePwdWarning() {
        return isVisible(R2t4PopUpLocators.COMPLETE_PWD_WARNING);
    }

    public boolean confirmR2T4Status(String searchText, String status) {
        String selector = String.format(R2t4PopUpLocators.R2T4_HISTORY_STATUS, searchText);
        return status.equals(getText(selector));
    }

    public boolean confirmR2T4Result(String searchText, String result) {
        String selector = String.format(R2t4PopUpLocators.R2T4_HISTORY_RESULT, searchText);
        return result.equals(getText(selector));
    }

    public String getR2T4CompletedDate(String searchText) {
        String selector = String.format(R2t4PopUpLocators.R2T4_HISTORY_COMPLETED, searchText);
        return getText(selector);
    }

    public boolean confirmR2T4ManualAutomated(String searchText, String manualAuto) {
        String selector = String.format(R2t4PopUpLocators.R2T4_HISTORY_MANUAL_AUTOMATED, searchText);
        return manualAuto.equals(getText(selector));
    }

    public boolean confirmR2T4StatusMessage(String statusMessage) {
        String selector = String.format(R2t4PopUpLocators.R2T4_STATUS_MESSAGE, statusMessage);
        return isVisible(selector);
    }

    // ── Actions ──────────────────────────────────────────────────────────────

    public void selectR2T4(String dateOfDOD) {
        String selector = String.format(R2t4PopUpLocators.HISTORY_ROW, dateOfDOD);
        click(selector);
    }

    public void closeR2T4() {
        click(R2t4PopUpLocators.CLOSE_BUTTON);
    }

    public void refreshR2T4() {
        click(R2t4PopUpLocators.REFRESH_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickCancel() {
        click(R2t4PopUpLocators.CANCEL_BUTTON);
    }

    public void clickAddR2t4Button() {
        click(R2t4PopUpLocators.ADD_R2T4_BUTTON);
    }

    public void selectPaymentPeriod(String paymentPeriod) {
        click(R2t4PopUpLocators.PAYMENT_PERIOD_DROPDOWN);
        page.waitForTimeout(3000);
        String optionSelector = String.format(R2t4PopUpLocators.PAYMENT_PERIOD_OPTION, paymentPeriod);
        click(optionSelector);
    }

    public void addR2T4(String paymentPeriod, String dateOfDetermination, String dateOfWithdrawal) {
        clickAddR2t4Button();
        selectPaymentPeriod(paymentPeriod);
        waitForAjaxRequestToBeFinished();
        if (!dateOfDetermination.isEmpty()) {
            fill(R2t4PopUpLocators.DATE_OF_SCHOOL_WITHDREW, dateOfDetermination);
            click(R2t4PopUpLocators.PERIOD_START_DATE);
            waitForAjaxRequestToBeFinished();
        }
        if (!dateOfWithdrawal.isEmpty()) {
            fill(R2t4PopUpLocators.DATE_OF_WITHDREW, dateOfWithdrawal);
            click(R2t4PopUpLocators.PERIOD_START_DATE); // click out of DOW field
        }
        waitForAjaxRequestToBeFinished();
        waitForLoadMaskCompletion();
    }

    public void chooseR2T4Options(String paymentPeriod, String dateOfDetermination, String dateOfWithdrawal) {
        if (!paymentPeriod.isEmpty()) {
            selectPaymentPeriod(paymentPeriod);
        }
        if (!dateOfDetermination.isEmpty()) {
            fill(R2t4PopUpLocators.DATE_OF_SCHOOL_WITHDREW, dateOfDetermination);
            click(R2t4PopUpLocators.PERIOD_START_DATE);
        }
        if (!dateOfWithdrawal.isEmpty()) {
            fill(R2t4PopUpLocators.DATE_OF_WITHDREW, dateOfWithdrawal);
            click(R2t4PopUpLocators.PERIOD_START_DATE); // click out of DOW field
        }
        waitForAjaxRequestToBeFinished();
        waitForLoadMaskCompletion();
    }

    public void clickPwdAndFinalize(String keyStr) {
        String selector = String.format(R2t4PopUpLocators.DATA_ROW, keyStr);
        click(selector);
        click(R2t4PopUpLocators.PWD_TAB);
        click(R2t4PopUpLocators.FINALIZE_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadingIndicatorGone();
    }

    public void clickFinalizeR2t4() {
        click(R2t4PopUpLocators.FINALIZE_R2T4_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadingIndicatorGone();
    }

    public void clickReverseR2t4() {
        click(R2t4PopUpLocators.REVERSE_R2T4_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickPwd(String keyStr) {
        if (!keyStr.isEmpty()) {
            String selector = String.format(R2t4PopUpLocators.DATA_ROW, keyStr);
            click(selector);
            page.locator(R2t4PopUpLocators.R2T4_TAB_HEADING).waitFor();
        }
        click(R2t4PopUpLocators.PWD_TAB);
        waitForAjaxRequestToBeFinished();
        page.locator(R2t4PopUpLocators.PWD_TAB_HEADING).waitFor();
    }

    public String getR2t4Result(String courseEndDate) {
        String selector = String.format(R2t4PopUpLocators.RESULT, courseEndDate);
        return getText(selector);
    }

    public String getPaymentPeriod() {
        return getText(R2t4PopUpLocators.HEADER_PAYMENT_PERIOD_VIEW);
    }

    public String getPaymentPeriod(boolean isScreenInInputMode) {
        if (isScreenInInputMode) {
            return getText(R2t4PopUpLocators.PAYMENT_PERIOD_EDIT_SELECTED);
        } else {
            return getText(R2t4PopUpLocators.HEADER_PAYMENT_PERIOD_VIEW);
        }
    }

    public String getPeriodStartDate(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.PERIOD_START_DATE) : getText(R2t4PopUpLocators.HEADER_START_DATE_VIEW);
    }

    public String getPeriodEndDate(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.PERIOD_END_DATE) : getText(R2t4PopUpLocators.HEADER_END_DATE_VIEW);
    }

    public String getDateOfDetermination(boolean isScreenInInputMode) {
        return isScreenInInputMode
                ? page.locator(R2t4PopUpLocators.INPUT_DATE_OF_DETERMINATION).inputValue()
                : getText(R2t4PopUpLocators.HEADER_DETERMINATION_DATE_VIEW);
    }

    public String getDateOfWithdrawal(boolean isScreenInInputMode) {
        return isScreenInInputMode
                ? page.locator(R2t4PopUpLocators.INPUT_DATE_OF_WITHDRAWAL).inputValue()
                : getText(R2t4PopUpLocators.HEADER_WITHDRAWAL_DATE_VIEW);
    }

    public boolean isUse50Percent() {
        return isVisible(R2t4PopUpLocators.HEADER_USE_50_PERCENT_VIEW);
    }

    // ── Step 2 values ────────────────────────────────────────────────────────

    public String getStep2DateOfWithdrawal(boolean isScreenInInputMode) {
        return isScreenInInputMode
                ? page.locator(R2t4PopUpLocators.INPUT_STEP2_DATE_OF_WITHDRAWAL).inputValue()
                : getText(R2t4PopUpLocators.STEP2_DATE_OF_WITHDRAWAL_VIEW);
    }

    public String getStep2StartDate(boolean isScreenInInputMode) {
        return isScreenInInputMode
                ? page.locator(R2t4PopUpLocators.INPUT_STEP2_START_DATE).inputValue()
                : getText(R2t4PopUpLocators.STEP2_PP_START_DATE_VIEW_1);
    }

    public String getStep2BreakDays1(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.STEP2_BREAK_DAYS_1) : getText(R2t4PopUpLocators.STEP2_BREAK_DAYS_VIEW_1);
    }

    public String getStep2CompletedDays(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.STEP2_COMPLETED_DAYS) : getText(R2t4PopUpLocators.STEP2_COMPLETED_DAYS_VIEW);
    }

    public String getStep2ScheduledEndDate(boolean isScreenInInputMode) {
        return isScreenInInputMode
                ? page.locator(R2t4PopUpLocators.INPUT_STEP2_SCHEDULED_END_DATE).inputValue()
                : getText(R2t4PopUpLocators.STEP2_SCHEDULED_PP_END_DATE_VIEW);
    }

    public void setStep2ScheduledEndDate(String newEndDate) {
        fill(R2t4PopUpLocators.INPUT_STEP2_SCHEDULED_END_DATE, newEndDate);
    }

    public String getStep2ScheduledStartDate(boolean isScreenInInputMode) {
        return isScreenInInputMode
                ? page.locator(R2t4PopUpLocators.INPUT_STEP2_SCHEDULED_START_DATE).inputValue()
                : getText(R2t4PopUpLocators.STEP2_PP_START_DATE_VIEW_2);
    }

    public String getStep2AYEndDateOverride(boolean isScreenInInputMode) {
        return isScreenInInputMode
                ? page.locator(R2t4PopUpLocators.INPUT_STEP2_AY_END_DATE_OVERRIDE).inputValue()
                : getText(R2t4PopUpLocators.STEP2_AY_END_DATE_OVERRIDE_VIEW);
    }

    public void setStep2AYEndDateOverride(String newAyEndDate) {
        fill(R2t4PopUpLocators.INPUT_STEP2_AY_END_DATE_OVERRIDE, newAyEndDate);
    }

    public String getStep2LPEndDateOverride(boolean isScreenInInputMode) {
        return isScreenInInputMode
                ? page.locator(R2t4PopUpLocators.INPUT_STEP2_LP_END_DATE_OVERRIDE).inputValue()
                : getText(R2t4PopUpLocators.STEP2_LP_END_DATE_OVERRIDE_VIEW);
    }

    public void setStep2LPEndDateOverride(String newLPEndDate) {
        fill(R2t4PopUpLocators.INPUT_STEP2_LP_END_DATE_OVERRIDE, newLPEndDate);
    }

    public String getStep2BreakDays2(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.STEP2_BREAK_DAYS_2) : getText(R2t4PopUpLocators.STEP2_BREAK_DAYS_VIEW_2);
    }

    public String getStep2ScheduledDays(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.STEP2_SCHEDULED_DAYS) : getText(R2t4PopUpLocators.STEP2_SCHEDULED_PP_DAYS_VIEW);
    }

    public String getStep2TotalCompletedDays(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.STEP2_TOTAL_COMPLETED_DAYS) : getText(R2t4PopUpLocators.STEP2_TOTAL_COMPLETED_DAYS_VIEW);
    }

    public String getStep2TotalPPDays(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.STEP2_TOTAL_PP_DAYS) : getText(R2t4PopUpLocators.STEP2_TOTAL_PP_DAYS_VIEW);
    }

    public String getStep2Equals(boolean isScreenInInputMode) {
        return isScreenInInputMode ? getText(R2t4PopUpLocators.STEP2_PERCENTAGE_RESULT) : getText(R2t4PopUpLocators.STEP2_EQUALS_VIEW);
    }

    // ── Save / edit / delete ─────────────────────────────────────────────────

    public void clickSave4Button() {
        click(R2t4PopUpLocators.SAVE_BUTTON_TOP);
        waitForAjaxRequestToBeFinished();
        dismissConfirmationPopupIfPresent();
        page.locator(R2t4PopUpLocators.SAVE_BUTTON_TOP).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN));
        click(R2t4PopUpLocators.EDIT_R2T4_BUTTON);
    }

    private void dismissConfirmationPopupIfPresent() {
        if (isVisible(R2t4PopUpLocators.CONFIRMATION_POPUP)) {
            click(R2t4PopUpLocators.CONFIRM_YES_BUTTON);
        }
    }

    private void waitForLoadMaskCompletion() {
        for (int idx = 0; idx < 60; idx++) {
            if (page.locator(R2t4PopUpLocators.LOADING).count() == 0) break;
            page.waitForTimeout(1000);
        }
    }

    private void waitForLoadingIndicatorGone() {
        page.locator(R2t4PopUpLocators.LOADING_INDICATOR).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN));
    }

    public void clickSaveR2T44Button() {
        click(R2t4PopUpLocators.SAVE_BUTTON_TOP);
        waitForAjaxRequestToBeFinished();
        dismissConfirmationPopupIfPresent();
        waitForLoadMaskCompletion();
    }

    /** Use this to see the toast message triggered by Save. */
    public void clickSaveButton() {
        page.locator(R2t4PopUpLocators.SAVE_BUTTON_TOP).first().click();
    }

    public void clickEdit4Button() {
        click(R2t4PopUpLocators.EDIT_R2T4_BUTTON);
        waitForAjaxRequestToBeFinished();
        page.locator(R2t4PopUpLocators.EDIT_R2T4_BUTTON).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN));
        page.locator(R2t4PopUpLocators.SAVE_BUTTON_TOP).waitFor();
    }

    public void clickDeleteButton() {
        click(R2t4PopUpLocators.DELETE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickStep2CalculateButton() {
        click(R2t4PopUpLocators.STEP2_CALCULATE_BUTTON);
    }

    public String getHeaderFieldData(String fieldLabel) {
        String selector = String.format(R2t4PopUpLocators.HEADER_FIELD_DATA, fieldLabel);
        return getText(selector);
    }

    public String getStep2AdjustedCompletedDaysView() {
        return getText(R2t4PopUpLocators.STEP2_ADJUSTED_COMPLETED_DAYS_VIEW);
    }

    public String getStep2AdjustedScheduledDaysView() {
        return getText(R2t4PopUpLocators.STEP2_ADJUSTED_SCHEDULED_DAYS_VIEW);
    }

    public void viewStep2() {
        click(R2t4PopUpLocators.R2T4_STEP2_COURSE_INFO_HEADER);
    }

    public String getStep2CoursePaymentPeriod(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 1);
    }

    public String getStep2CourseNumber(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 2);
    }

    public String getStep2CourseName(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 3);
    }

    public String getStep2CourseStartDate(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 4);
    }

    public String getStep2CourseEndDate(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 5);
    }

    public String getStep2CourseUnits(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 6);
    }

    public String getStep2CourseEarnedUnits(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 7);
    }

    public String getStep2CourseProgramApplicable(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 8);
    }

    public String getStep2CourseTransfer(String rowKey) {
        return nthCellText(R2t4PopUpLocators.STEP2_COURSES_GRID_CELLS, rowKey, 9);
    }

    public boolean noR2t4sInGrid() {
        String selector = String.format(R2t4PopUpLocators.HISTORY_ROW, "");
        return page.locator(selector).count() == 0;
    }
}
