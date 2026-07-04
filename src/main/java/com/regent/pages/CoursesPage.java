package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.CoursesLocators;

/** Ported from regent.pages.student.CoursesPage. */
public class CoursesPage extends BasePage {

    public CoursesPage(Page page) {
        super(page);
    }

    public void viewAllCourses() {
        click(CoursesLocators.VIEW_ALL_RADIO);
    }

    public void filterOnCourseId(String courseId) {
        click(String.format(CoursesLocators.COURSE_FILTER, "courseId"));
        fill(CoursesLocators.COURSE_FILTER_INPUT, courseId);
        click(CoursesLocators.COURSE_FILTER_BUTTON);
    }

    public void filterOnLastAttended(String lastAttendedDate) {
        click(String.format(CoursesLocators.COURSE_FILTER, "lastAttendedDate"));
        fill(CoursesLocators.COURSE_FILTER_INPUT, lastAttendedDate);
        click(CoursesLocators.COURSE_FILTER_BUTTON);
    }

    public void filterOnPaymentPd(String paymentPd) {
        click(String.format(CoursesLocators.COURSE_FILTER, "termName"));
        fill(CoursesLocators.COURSE_FILTER_INPUT, paymentPd);
        click(CoursesLocators.COURSE_FILTER_BUTTON);
    }

    public String getCourseDetailTextItem(String itemLabel) {
        return getText(String.format(CoursesLocators.COURSE_DETAILS_TEXT_ITEM, itemLabel));
    }

    public boolean isCourseDetailItemManualOverridden(String itemLabel) {
        click(String.format(CoursesLocators.COURSE_DETAILS_TEXT_ITEM, itemLabel));
        return isVisible(String.format(CoursesLocators.COURSE_DETAILS_ITEM_MANUAL_OVERRIDE, itemLabel));
    }

    public boolean isCourseDetailItemCheckboxChecked(String itemLabel) {
        return page.locator(String.format(CoursesLocators.COURSE_DETAILS_CHECKBOX_ITEM, itemLabel)).first().isChecked();
    }

    public void changeHasAttended(boolean hasAttended) {
        click(CoursesLocators.HAS_ATTENDED_SELECT);
        click(String.format(CoursesLocators.HAS_ATTENDED_ITEM, hasAttended ? "True" : "False"));
    }

    public void changeCompleted(boolean completed) {
        click(CoursesLocators.COMPLETED_SELECT);
        click(String.format(CoursesLocators.COMPLETED_ITEM, completed ? "True" : "False"));
    }

    public void changeProgramApplicable(boolean programApplicable) {
        click(CoursesLocators.PROGRAM_APPLICABLE_SELECT);
        click(String.format(CoursesLocators.PROGRAM_APPLICABLE_ITEM, programApplicable ? "True" : "False"));
    }

    /** columnPosition is 1-based, matching the source's CourseGridColumn.getColumnNumber(). */
    public String getCourseGridCellValue(String rowKey, int columnPosition) {
        return page.locator(String.format(CoursesLocators.COURSE_GRID_ROW_CELLS, rowKey))
                .nth(columnPosition - 1).textContent().trim();
    }

    public boolean isCourseInActiveGrid(String rowKey) {
        return isVisible(String.format(CoursesLocators.ACTIVE_COURSE_GRID_ROW, rowKey));
    }

    private void clickLastPageButtonIfPresent() {
        if (page.locator(CoursesLocators.LAST_PAGE_BUTTON).count() > 0) {
            click(CoursesLocators.LAST_PAGE_BUTTON);
            waitForAjaxRequestToBeFinished();
        }
    }

    public void clickOnLastPaymentPeriod(String paymentPeriodName) {
        clickLastPageButtonIfPresent();
        click(String.format(CoursesLocators.LAST_PAYMENT_PERIOD, paymentPeriodName));
        waitForAjaxRequestToBeFinished();
    }

    public void clickEditButton() {
        click(CoursesLocators.EDIT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickRefreshCourseButton() {
        click(CoursesLocators.REFRESH_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void changeNumberOfRegisteredUnits(String lastAttendedDate, String registeredUnits) {
        clickEditButton();
        fill(CoursesLocators.LAST_ATTENDED_DATE_FIELD, lastAttendedDate);
        fill(CoursesLocators.REGISTERED_UNITS_INPUT, registeredUnits);
        fill(CoursesLocators.COMMENTS_FIELD, "Changed number of registered units to " + registeredUnits);
        clickSubmitButton();
    }

    public void changeRegisteredUnits(String units) {
        fill(CoursesLocators.REGISTERED_UNITS_INPUT, units);
    }

    public void changeWithdrawalDate(String withdrawalDate, String comment) {
        fill(CoursesLocators.WITHDRAWAL_DATE_INPUT, withdrawalDate);
        if (!comment.isEmpty()) {
            fill(CoursesLocators.COMMENTS_FIELD, comment);
        }
    }

    public void changeLastAttendedDate(String lastAttendedDate, String comment) {
        fill(CoursesLocators.LAST_ATTENDED_DATE_FIELD, lastAttendedDate);
        if (!comment.isEmpty()) {
            fill(CoursesLocators.COMMENTS_FIELD, comment);
        }
    }

    public void addComment(String comment) {
        fill(CoursesLocators.COMMENTS_FIELD, comment);
    }

    public void changeStartDate(String startDate) {
        fill(CoursesLocators.START_DATE_INPUT, startDate);
    }

    public void changeEndDate(String endDate) {
        fill(CoursesLocators.END_DATE_INPUT, endDate);
    }

    public void clickSubmitButton() {
        click(CoursesLocators.SUBMIT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnCourseWithName(String text) {
        String selector = String.format(CoursesLocators.COURSE_DATA_WITH_NAME, text);
        if (page.locator(selector).count() == 0) {
            clickNextPageButton();
        }
        click(selector);
    }

    public String getExternalModuleId() {
        return getText(CoursesLocators.EXTERNAL_MODULE_ID);
    }

    public void filterByStartDate() {
        click(CoursesLocators.START_DATE_FILTER);
    }

    public void clickNextPageButton() {
        click(CoursesLocators.NEXT_PAGE_BUTTON);
    }

    public void clickActiveCoursesTab() {
        click(CoursesLocators.ACTIVE_COURSES_TAB);
    }

    /** Confirms the native JS "are you sure" dialog that follows the Delete click. */
    public void deleteCourse(String courseId) {
        click(String.format(CoursesLocators.COURSE_GRID_ROW_CHECKBOX, courseId));
        page.onceDialog(dialog -> dialog.accept());
        click(CoursesLocators.DELETE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickDeletedCoursesTab() {
        click(CoursesLocators.DELETED_COURSES_TAB);
    }

    public void restoreDeletedCourse(String courseId) {
        click(String.format(CoursesLocators.DELETED_COURSE_GRID_ROW_CHECKBOX, courseId));
        page.onceDialog(dialog -> dialog.accept());
        click(CoursesLocators.RESTORE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public boolean isCourseInDeletedGrid(String courseId) {
        return isVisible(String.format(CoursesLocators.DELETED_COURSE_GRID_ROW, courseId));
    }

    public String getCommentData() {
        return getText(CoursesLocators.COMMENT_TEXT_AREA);
    }

    public boolean isCourseInGridManualOverridden(String courseId) {
        return isVisible(String.format(CoursesLocators.COURSE_GRID_ROW_MANUAL, courseId));
    }

    public void clearAllOverrides() {
        page.onceDialog(dialog -> dialog.accept());
        click(CoursesLocators.CLEAR_ALL_OVERRIDES_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void setQualityPoints(String points) {
        fill(CoursesLocators.QUALITY_POINTS_INPUT, points);
    }

    public void setLetterGrade(String grade) {
        fill(CoursesLocators.LETTER_GRADE_INPUT, grade);
    }
}
