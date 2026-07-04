package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.TermSetupLocators;

/**
 * Ported from regent.pages.TermSetupPage. Source extended Filter for filterBy(); this composes
 * FilterPage instead (see FilterPage/ExportProcessPage for the established pattern).
 *
 * Skipped: getNumberOfRowWithCurrentYear()/getFirstTermForChosenProgram() — environment-URL-
 * branching, multi-page grid-scan search algorithms with embedded TestNG fail() calls rather than
 * simple page actions/queries; setModule() — composes retries around a CourseDataSbl model object
 * not available on this side; getModuleInfo() — returns a ModuleData model object not available
 * on this side (its individual field reads are still exposed via getModuleStartDate/EndDate/
 * ExternalID below). All should be reimplemented in step definitions/test helpers if still needed.
 */
public class TermSetupPage extends BasePage {

    private final FilterPage filterPage;

    public TermSetupPage(Page page) {
        super(page);
        this.filterPage = new FilterPage(page);
    }

    public void filterByCalendar(String type) {
        click(TermSetupLocators.CALENDAR_FILTER_HEADER);
        fill(TermSetupLocators.FILTER_INPUT, type);
        click(TermSetupLocators.FILTER_SUBMIT);
    }

    /** Filter to get the exact term name via "Is equal to". */
    public void filterByName(String name) {
        filterPage.filterBy("Name", TermSetupLocators.FILTER_IS_EQUAL_TO, name);
        waitForAjaxRequestToBeFinished();
    }

    public String getGridCensusDate(String rowKey) {
        return getText(String.format(TermSetupLocators.TERM_GRID_CENSUS_BY_TEXT, rowKey));
    }

    public String getGridStartDate(String rowKey) {
        String rowSelector = String.format(TermSetupLocators.TERM_GRID_CELL_BY_TEXT, rowKey);
        return page.locator(rowSelector).nth(5).textContent().trim();
    }

    public String getGridEndDate(String rowKey) {
        String rowSelector = String.format(TermSetupLocators.TERM_GRID_CELL_BY_TEXT, rowKey);
        return page.locator(rowSelector).nth(6).textContent().trim();
    }

    public void deleteTerm(String termName) {
        click(TermSetupLocators.NAME_FILTER_HEADER);
        click(TermSetupLocators.CLEAR_BUTTON);
        click(TermSetupLocators.NAME_FILTER_HEADER);
        fill(TermSetupLocators.FILTER_INPUT, termName);
        click(TermSetupLocators.FILTER_SUBMIT);
        String termRow = String.format(TermSetupLocators.TERM_GRID_ROW_BY_TEXT, termName);
        if (isVisibleNow(termRow)) {
            click(termRow);
            page.onceDialog(dialog -> dialog.accept());
            click(TermSetupLocators.DELETE_BUTTON);
        }
    }

    public void filterByExternalId(String externalId) {
        click(TermSetupLocators.EXTERNAL_ID_FILTER_HEADER);
        fill(TermSetupLocators.FILTER_INPUT, externalId);
        click(TermSetupLocators.FILTER_SUBMIT);
    }

    public void clickGoToTheLastPageButton() {
        click(TermSetupLocators.GO_TO_LAST_PAGE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void sortByStartDate() {
        click(TermSetupLocators.START_DATE_HEADER);
    }

    public void clickBackButton() {
        click(TermSetupLocators.BACK_BUTTON);
    }

    public int getTotalNumberOfRows() {
        return page.locator(TermSetupLocators.START_DATE_CELL).count();
    }

    public String getTermName(int rowNumber) {
        return page.locator(TermSetupLocators.TERM_NAME_CELL).nth(rowNumber - 1).textContent().trim();
    }

    /** External Term Id column position has shifted across app versions (10/9/8 header columns). */
    public String getExternalTermId(int rowNumber) {
        int numberOfColumns = page.locator(TermSetupLocators.TERM_TABLE_HEADERS).count();
        String selector;
        if (numberOfColumns == 10) {
            selector = TermSetupLocators.EXTERNAL_TERM_ID_COL10;
        } else if (numberOfColumns == 9) {
            selector = TermSetupLocators.EXTERNAL_TERM_ID_COL9;
        } else if (numberOfColumns == 8) {
            selector = TermSetupLocators.EXTERNAL_TERM_ID_COL8;
        } else {
            return "";
        }
        return page.locator(selector).nth(rowNumber - 1).textContent().trim();
    }

    public String getStartDate(int rowNumber) {
        return page.locator(TermSetupLocators.START_DATE_CELL).nth(rowNumber - 1).textContent().trim();
    }

    public String getEndDate(int rowNumber) {
        return page.locator(TermSetupLocators.END_DATE_CELL).nth(rowNumber - 1).textContent().trim();
    }

    public void clickAddTermButton() {
        click(TermSetupLocators.ADD_TERM_BUTTON);
    }

    public void clickOnTermFromGrid(String termId) {
        click(String.format(TermSetupLocators.TERM_GRID_ROW_BY_TEXT, termId));
    }

    public void clickDeleteButton() {
        page.onceDialog(dialog -> dialog.accept());
        click(TermSetupLocators.DELETE_BUTTON);
    }

    public void clickEditTermButton() {
        click(TermSetupLocators.EDIT_TERM_BUTTON);
    }

    public void clickModulesButton() {
        click(TermSetupLocators.MODULES_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public String getModuleStartDate(int moduleNumber) {
        return page.locator(TermSetupLocators.MODULE_START_DATE_CELL).nth(moduleNumber - 1).textContent().trim();
    }

    public String getModuleEndDate(int moduleNumber) {
        return page.locator(TermSetupLocators.MODULE_END_DATE_CELL).nth(moduleNumber - 1).textContent().trim();
    }

    public String getModuleExternalID(int moduleNumber) {
        return page.locator(TermSetupLocators.MODULE_EXTERNAL_ID_CELL).nth(moduleNumber - 1).textContent().trim();
    }

    public void clickOnTerm(int termNumber) {
        page.locator(TermSetupLocators.TERM_NAME_CELL).nth(termNumber - 1).click();
    }

    public boolean isModuleTabPresent() {
        return isVisible(TermSetupLocators.MODULES_BUTTON);
    }

    public boolean isModuleElementsPresent() {
        return isVisible(String.format(TermSetupLocators.MODULE_ATTRIBUTE_HEADER_BY_TEXT, "Name"))
                && isVisible(String.format(TermSetupLocators.MODULE_ATTRIBUTE_HEADER_BY_TEXT, "Start Date"))
                && isVisible(String.format(TermSetupLocators.MODULE_ATTRIBUTE_HEADER_BY_TEXT, "End Date"))
                && isVisible(String.format(TermSetupLocators.MODULE_ATTRIBUTE_HEADER_BY_TEXT, "Freeze COA Date"))
                && isVisible(String.format(TermSetupLocators.MODULE_ATTRIBUTE_HEADER_BY_TEXT, "Expected Attendance Date"))
                && isVisible(String.format(TermSetupLocators.MODULE_ATTRIBUTE_HEADER_BY_TEXT, "Last Day for Drop/Add"))
                && isVisible(String.format(TermSetupLocators.MODULE_ATTRIBUTE_HEADER_BY_TEXT, "External Module Id"));
    }

    public void clickAddModuleButton() {
        click(TermSetupLocators.ADD_MODULE_BUTTON);
    }

    public void clickSaveModuleButton() {
        click(TermSetupLocators.SAVE_MODULE_BUTTON);
    }

    public String getValidationMessage() {
        return getText(TermSetupLocators.VALIDATION_REPORT).replace("\n", " ");
    }

    public void enterModuleInfo(String name, String externalModuleId, String startDate, String endDate,
                                 String expectedAttendanceDate, String freezeCOADate) {
        fill(TermSetupLocators.MODULE_NAME_INPUT, name);
        fill(TermSetupLocators.EXTERNAL_MODULE_ID_INPUT, externalModuleId);
        fill(TermSetupLocators.MODULE_START_DATE_INPUT, startDate);
        fill(TermSetupLocators.MODULE_END_DATE_INPUT, endDate);
        if (expectedAttendanceDate != null && !expectedAttendanceDate.isEmpty()) {
            fill(TermSetupLocators.EXPECTED_ATTENDANCE_DATE_INPUT, expectedAttendanceDate);
        }
        if (freezeCOADate != null && !freezeCOADate.isEmpty()) {
            fill(TermSetupLocators.FREEZE_COA_DATE_INPUT, freezeCOADate);
        }
    }

    public void deleteModule(String moduleName) {
        click(String.format(TermSetupLocators.MODULE_ROW_BY_TEXT, moduleName));
        click(TermSetupLocators.DELETE_MODULE_BUTTON);
    }

    public void clickEditModuleButton(String moduleName) {
        click(String.format(TermSetupLocators.MODULE_ROW_BY_TEXT, moduleName));
        click(TermSetupLocators.EDIT_MODULE_BUTTON);
    }

    public void enterFreezeCOADate(String date) {
        fill(TermSetupLocators.FREEZE_COA_DATE_INPUT, date);
    }

    public void enterExpectedAttendanceDate(String date) {
        fill(TermSetupLocators.EXPECTED_ATTENDANCE_DATE_INPUT, date);
    }
}
