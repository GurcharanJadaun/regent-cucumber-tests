package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.StudentBreaksLocators;

/** Ported from regent.pages.student.StudentBreaksPopUp. */
public class StudentBreaksPopUp extends BasePage {

    public StudentBreaksPopUp(Page page) {
        super(page);
    }

    public void clickAddStudentBreak() {
        click(StudentBreaksLocators.ADD_STUDENT_BREAK_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** breakType is the raw option text (e.g. "LOA", "CORE", "Unapproved", "Administrative"). */
    public void addStudentBreak(String breakType, String reason, boolean approved, String startDate, String endDate, String note) {
        clickAddStudentBreak();
        selectBreakType(breakType);
        enterReason(reason);
        selectApprovedCheckbox(approved);
        enterStartDate(startDate);
        enterEndDate(endDate);
        enterNote(note);
        saveBreak();
    }

    public void selectBreakType(String breakType) {
        page.locator(StudentBreaksLocators.TYPE_SELECT).selectOption(breakType);
    }

    public void enterReason(String reason) {
        fill(StudentBreaksLocators.BREAK_REASON, reason);
    }

    public void enterExternalBreakId(String extBreakId) {
        fill(StudentBreaksLocators.BREAK_ID, extBreakId);
    }

    public void selectApprovedCheckbox(boolean approved) {
        if (approved) {
            page.locator(StudentBreaksLocators.APPROVED_CHECKBOX).check();
        } else {
            page.locator(StudentBreaksLocators.APPROVED_CHECKBOX).uncheck();
        }
    }

    public void enterStartDate(String startDate) {
        fill(StudentBreaksLocators.BREAK_START_DATE, startDate);
    }

    public void enterEndDate(String endDate) {
        fill(StudentBreaksLocators.BREAK_END_DATE, endDate);
    }

    public void enterNote(String note) {
        fill(StudentBreaksLocators.BREAK_NOTE, note);
    }

    public void saveBreak() {
        click(StudentBreaksLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void rePackage() {
        click(StudentBreaksLocators.REPACKAGE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** Returns to the Academic Plan page; caller constructs an AcademicPlanPage as needed. */
    public void closeStudentBreak() {
        click(StudentBreaksLocators.CLOSE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void repackageAndClose() {
        rePackage();
        closeStudentBreak();
    }
}
