package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ProgramManagementPopupLocators;

/**
 * Ported from regent.pages.student.ProgramManagementPopupPage. Methods that navigated to
 * ProgramManagementPopupStep2Page / AddStudentAcademicYearPopup in the source return void here
 * since those wizard pages have no port yet in this project.
 */
public class ProgramManagementPopupPage extends BasePage {

    public ProgramManagementPopupPage(Page page) {
        super(page);
    }

    public void selectActionType(String actionType) {
        click(ProgramManagementPopupLocators.ACTION_TYPE_DROPDOWN);
        click(String.format(ProgramManagementPopupLocators.ACTION_TYPE_OPTION, actionType));
        waitForAjaxRequestToBeFinished();
    }

    /**
     * The imported-awards grid's column order is data-driven, so the source resolves the
     * target column's index from the header's data-index attribute before reading the row cell.
     */
    public String getImportAwardFundColumnData(String fundName, String columnField) {
        String headerSelector = String.format(ProgramManagementPopupLocators.IMPORT_AWARDS_TABLE_COLUMN, columnField);
        String dataIndex = page.locator(headerSelector).first().getAttribute("data-index");
        int columnIndex = Integer.parseInt(dataIndex) + 1;
        String rowCellsSelector = String.format(ProgramManagementPopupLocators.IMPORT_AWARDS_TABLE_COLUMN_VALUE, fundName);
        return page.locator(rowCellsSelector).nth(columnIndex - 1).textContent().trim();
    }

    public void expandImportAward(String fundName) {
        click(String.format(ProgramManagementPopupLocators.IMPORT_AWARDS_FUND_EXPAND, fundName));
        waitForAjaxRequestToBeFinished();
    }

    public void clickContinue() {
        click(ProgramManagementPopupLocators.CONTINUE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** Continue triggers a native confirm() dialog in some wizard paths; auto-accept it. */
    public void clickContinueWithAlert() {
        page.onceDialog(dialog -> dialog.accept());
        click(ProgramManagementPopupLocators.CONTINUE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickCancelButton() {
        click(ProgramManagementPopupLocators.CANCEL_BUTTON);
    }

    public void clickAddAcademicYear() {
        click(ProgramManagementPopupLocators.ADD_ACADEMIC_YEAR_BUTTON);
    }

    // ── Manual Academic Year Modifications Grid ─────────────────────────────

    public String getManualAYModificationsIssues(String academicYear) {
        String selector = String.format(ProgramManagementPopupLocators.MANUAL_AY_MOD_GRID_ISSUES_CELL, academicYear);
        return getText(selector);
    }

    public void editManualAcademicYear(String academicYear) {
        click(String.format(ProgramManagementPopupLocators.MANUAL_AY_MOD_GRID_ROW_EDIT, academicYear));
    }
}
