package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddTermLocators;

/** Ported from regent.pages.AddTermPage. Date/enterprise-model plumbing from the source
 * (CourseDataSbl, ProgramData, Enterprise) isn't available on this side, so dates and the
 * calendar type are passed in as plain strings already formatted by the caller. */
public class AddTermPage extends BasePage {

    public AddTermPage(Page page) {
        super(page);
    }

    /** Creates a term. Dismisses the native "Save" confirm dialog Kendo raises on submit. */
    public void createTerm(String name, String externalTermId, String startDate, String endDate, String calendarType) {
        fill(AddTermLocators.TERM_NAME, name);
        fill(AddTermLocators.EXTERNAL_TERM_ID, externalTermId);
        fill(AddTermLocators.TERM_START_DATE, startDate);
        fill(AddTermLocators.TERM_END_DATE, endDate);
        page.locator(AddTermLocators.INHERIT_CPU_CHECKBOX).uncheck();
        click(AddTermLocators.CALENDAR_TYPE_DROPDOWN);
        click(String.format(AddTermLocators.CALENDAR_TYPE_OPTION, calendarType));
        page.onceDialog(dialog -> dialog.accept());
        click(AddTermLocators.SAVE_BUTTON);
    }

    public void setTermStartDate(String startDate) {
        fill(AddTermLocators.TERM_START_DATE, startDate);
    }

    public void setTermEndDate(String endDate) {
        fill(AddTermLocators.TERM_END_DATE, endDate);
    }

    public void setTermCensusDate(String censusDate) {
        fill(AddTermLocators.TERM_CENSUS_DATE, censusDate);
    }

    public void clickSave() {
        click(AddTermLocators.SAVE_BUTTON);
    }
}
