package com.regent.locators;

/** Ported from regent.pages.SapProcessPage. */
public interface SapProcessLocators {
    String ENTERPRISE_SELECT = "span[aria-owns='EnterpriseId_listbox']";
    String INSTITUTION_SELECT = "span[aria-owns='InstitutionId_listbox']";
    String SELECT_ITEM = "//li[contains(string(),'%s')]";
    String LIST_ELEMENT = "//tr[contains(string(),'%s')]//a[@role='button']";

    String TERMS_PAGINATION = "//div[@data-rem-id='TermsGrid']//ul[@class='k-pager-numbers k-reset']//li[not(@class='k-current-page')]";
    String TERM_ITEM = "//div[@data-rem-id='TermsGrid']//tr[contains(string(),'%s')]//a";

    String SELECT_ALL_STUDENT_LEVELS_CHECKBOX = "#IOProcess_SelectAllStudentLevels";
    String SELECT_ALL_PROGRAM_TYPES_CHECKBOX = "#IOProcess_SelectAllProgramTypes";
    String SELECT_ALL_PROGRAMS_CHECKBOX = "#IOProcess_SelectAllPrograms";

    String EXECUTE_BUTTON = "//button[contains(string(), 'Execute')]";
}
