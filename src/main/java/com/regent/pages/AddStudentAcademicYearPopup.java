package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddStudentAcademicYearLocators;

public class AddStudentAcademicYearPopup extends BasePage {

    public AddStudentAcademicYearPopup(Page page) {
        super(page);
    }

    public void selectProgram(String programName) {
        click(AddStudentAcademicYearLocators.PROGRAM_DROPDOWN);
        click(String.format(AddStudentAcademicYearLocators.PROGRAM_OPTION, programName));
        waitForAjaxRequestToBeFinished();
    }

    public void selectAcademicYearNumber(String academicYearNumber) {
        click(AddStudentAcademicYearLocators.ACADEMIC_YEAR_DROPDOWN);
        click(String.format(AddStudentAcademicYearLocators.ACADEMIC_YEAR_OPTION, academicYearNumber));
        waitForAjaxRequestToBeFinished();
    }

    public void selectFederalAwardYear(String federalAwardYear) {
        click(AddStudentAcademicYearLocators.FAY_DROPDOWN);
        click(String.format(AddStudentAcademicYearLocators.FAY_OPTION, federalAwardYear));
        waitForAjaxRequestToBeFinished();
    }

    public void setBeginDate(String beginDate) {
        fill(AddStudentAcademicYearLocators.BEGIN_DATE, beginDate);
    }

    public void setEndDate(String endDate) {
        fill(AddStudentAcademicYearLocators.END_DATE, endDate);
    }

    public void checkHasAbbreviatedPeriod(boolean check) {
        boolean checked = page.locator(AddStudentAcademicYearLocators.HAS_ABBR_PERIOD_CHECKBOX).isChecked();
        if (checked != check) {
            click(AddStudentAcademicYearLocators.HAS_ABBR_PERIOD_CHECKBOX);
        }
        waitForAjaxRequestToBeFinished();
    }

    public void setOverrideAYUnits(String units) {
        fill(AddStudentAcademicYearLocators.OVERRIDE_AY_UNITS, units);
    }

    public void setOverrideAYWeeks(String weeks) {
        fill(AddStudentAcademicYearLocators.OVERRIDE_AY_WEEKS, weeks);
    }

    public void setManualBeginDate(String beginDate) {
        fill(AddStudentAcademicYearLocators.MANUAL_BEGIN_DATE, beginDate);
    }

    public void setManualEndDate(String endDate) {
        fill(AddStudentAcademicYearLocators.MANUAL_END_DATE, endDate);
    }

    public void clickSaveAddStudentAY() {
        click(AddStudentAcademicYearLocators.ADD_STUDENT_AY_SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public String clickSaveAddStudentAYGetError() {
        click(AddStudentAcademicYearLocators.ADD_STUDENT_AY_SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
        return getText(AddStudentAcademicYearLocators.SAVE_ADD_STUDENT_AY_VALIDATION_ERROR);
    }

    public void clickCancelAddStudentAY() {
        click(AddStudentAcademicYearLocators.CANCEL_ADD_STUDENT_AY_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickAddLoanPeriod() {
        click(AddStudentAcademicYearLocators.ADD_LOAN_PERIOD_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void setStudentLPStartDate(String startDate) {
        fill(AddStudentAcademicYearLocators.MODIFY_STUDENT_LP_BEGIN_DATE, startDate);
    }

    public void setStudentLPEndDate(String endDate) {
        fill(AddStudentAcademicYearLocators.MODIFY_STUDENT_LP_END_DATE, endDate);
    }

    public void clickSaveStudentLoanPeriod() {
        click(AddStudentAcademicYearLocators.MODIFY_STUDENT_LP_SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public String getNsldFundTotalUsed(String fundName) {
        return getText(String.format(AddStudentAcademicYearLocators.NSLDS_FUND_GRID_ROW_TOTAL_USED_CELL, fundName));
    }

    /**
     * The override input field's data-bind attribute isn't always present (Kendo swaps templates
     * depending on grid state), so fall back to positional first-input lookup, matching the source.
     */
    public void setNsldsFundOverrideAmount(String fundName, String overrideAmount) {
        click(String.format(AddStudentAcademicYearLocators.NSLDS_FUND_GRID_ROW_EDIT, fundName));
        String primaryInput = String.format(AddStudentAcademicYearLocators.NSLDS_FUND_GRID_OVERRIDE_INPUT, fundName);
        if (isVisibleNow(primaryInput)) {
            fill(primaryInput, overrideAmount);
        } else {
            String fallbackInput = String.format(AddStudentAcademicYearLocators.NSLDS_FUND_GRID_OVERRIDE_INPUT_FALLBACK, fundName);
            if (overrideAmount.isEmpty()) {
                page.locator(fallbackInput).fill("");
            } else {
                fill(fallbackInput, overrideAmount);
            }
        }
        click(String.format(AddStudentAcademicYearLocators.NSLDS_FUND_GRID_OVERRIDE_UPDATE_BUTTON, fundName));
        waitForAjaxRequestToBeFinished();
    }
}
