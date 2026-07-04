package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.SapProcessLocators;

/** Ported from regent.pages.SapProcessPage. */
public class SapProcessPage extends BasePage {

    public SapProcessPage(Page page) {
        super(page);
    }

    public SapProcessPage selectEnterprise(String enterprise) {
        click(SapProcessLocators.ENTERPRISE_SELECT);
        click(String.format(SapProcessLocators.SELECT_ITEM, enterprise));
        return this;
    }

    public SapProcessPage selectInstitution(String institution) {
        click(SapProcessLocators.INSTITUTION_SELECT);
        click(String.format(SapProcessLocators.SELECT_ITEM, institution));
        return this;
    }

    public SapProcessPage selectCampus(String campus) {
        click(String.format(SapProcessLocators.LIST_ELEMENT, campus));
        return this;
    }

    public SapProcessPage selectSite(String site) {
        click(String.format(SapProcessLocators.LIST_ELEMENT, site));
        return this;
    }

    /**
     * Terms are paginated and the target term may be on any page, so this pages through until it's
     * found, ignoring pages where a click attempt fails (term not present on that page).
     */
    public SapProcessPage findTermAndAdd(String term) {
        int numberOfPages = page.locator(SapProcessLocators.TERMS_PAGINATION).count() + 1;
        for (int termPage = 1; termPage <= numberOfPages; termPage++) {
            try {
                page.locator(SapProcessLocators.TERMS_PAGINATION).nth(termPage - 1).click();
                click(String.format(SapProcessLocators.TERM_ITEM, term));
                return this;
            } catch (Exception ignored) {
                // term not on this page; try the next one
            }
        }
        return this;
    }

    public SapProcessPage selectAllStudentLevels() {
        click(SapProcessLocators.SELECT_ALL_STUDENT_LEVELS_CHECKBOX);
        return this;
    }

    public SapProcessPage selectAllProgramTypes() {
        click(SapProcessLocators.SELECT_ALL_PROGRAM_TYPES_CHECKBOX);
        return this;
    }

    public SapProcessPage selectAllPrograms() {
        click(SapProcessLocators.SELECT_ALL_PROGRAMS_CHECKBOX);
        return this;
    }

    public void executeSapProcess() {
        click(SapProcessLocators.EXECUTE_BUTTON);
    }
}
