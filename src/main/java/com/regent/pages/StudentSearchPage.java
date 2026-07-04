package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.StudentSearchLocators;

/**
 * Ported from regent.pages.StudentSearchPage. The source's sort-verification and result-checking
 * methods (filterAndCheckResult, checkAllSearchResultsAreValid, clickOnFieldToSortAndVerifySorting,
 * etc.) were pure TestNG assertion wrappers with no independent action — skipped per project
 * convention that assertions belong in step definitions, not page objects.
 */
public class StudentSearchPage extends BasePage {

    public StudentSearchPage(Page page) {
        super(page);
    }

    public void clickOnUserNumber(int number) {
        waitForAjaxRequestToBeFinished();
        page.locator(StudentSearchLocators.USER_ITEM).nth(number - 1).click();
        waitForAjaxRequestToBeFinished();
    }

    public int getNumberOfResults() {
        String text = getText(StudentSearchLocators.NUMBER_OF_RESULTS_TEXT);
        return Integer.parseInt(text.split("of ")[1].split(" ")[0]);
    }

    public void filterOnColumn(String columnFilterText, String value, String searchOptionText) {
        click(String.format(StudentSearchLocators.FILTER, columnFilterText));
        click(StudentSearchLocators.TYPE_OF_SEARCH);
        click(String.format(StudentSearchLocators.SEARCH_OPTIONS, searchOptionText));
        fill(StudentSearchLocators.CONTAINS_FILTER_INPUT, value);
        click(StudentSearchLocators.SUBMIT_FILTER_BUTTON);
    }

    public void clickOnFieldToResetSorting(String columnFilterText) {
        click(String.format(StudentSearchLocators.SORT_COLUMN, columnFilterText));
    }

    public String getStudentSearchGridCell(String rowKey, int columnNumber) {
        String selector = String.format(StudentSearchLocators.STUDENT_SEARCH_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public void clickOnRow(String rowKey) {
        String selector = String.format(StudentSearchLocators.STUDENT_SEARCH_GRID_CELLS, rowKey);
        page.locator(selector).nth(1).click();
    }

    public void clearFilterResults(String columnFilterText) {
        click(String.format(StudentSearchLocators.FILTER, columnFilterText));
        click(StudentSearchLocators.RESET_FILTER_BUTTON);
    }
}
