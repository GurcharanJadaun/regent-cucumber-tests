package com.regent.locators;

/** Ported from regent.pages.BatchPackagePage. */
public interface BatchPackageLocators {
    String LIST_ELEMENT = "//div[@class='row' and contains(@data-rem-id,'') and not(contains(@style,'display: none;'))]/div/div[@data-role='grid'][not(contains(@data-rem-id,'selected'))]//tr[contains(string(),'%s')]//a[@role='button']";

    String SELECT_ALL_PROGRAM_TYPES_CHECKBOX = "#IOProcess_SelectAllProgramTypes";
    String SELECT_ALL_PROGRAMS_CHECKBOX = "#IOProcess_SelectAllPrograms";
    String EXECUTE_BUTTON = "//button[contains(string(), 'Execute')]";
    String TOAST = "//div[contains(@class,'toast')]";

    String EXTERNAL_ID_1_FILTER = "(//div[@data-rem-id='StudentsGrid']//th[@data-title='External ID 1']/a[@title='Filter'])[last()]";
    String EXTERNAL_ID_1_FILTER_INPUT = "(//form[@title='Show items with value that:']//input[@title='Value'])[last()]";
    String FILTER_BUTTON = "(//form[@title='Show items with value that:']//button[@type='submit' and contains(text(),'Filter')])[last()]";

    String AVAILABLE_TABLE_ITEM_ADD = "//div[@data-rem-id='%s']//tr[./td[text()='%s']]//a[text()='Add']";
    String SELECT_ALL_STUDENTS_CHECKBOX = "#IOProcess_SelectAllStudents";
    String AVAILABLE_FILTER = "//div[@data-rem-id='%s']//th[@data-field='Name']//a[@title = 'Filter']";
    String FILTER_INPUT = "//form[@aria-hidden='false']//input[@title='Value']";
    String FILTER_SUBMIT = "//form[@aria-hidden='false']//button[@type='submit']";

    // Grid data-rem-id values used with AVAILABLE_TABLE_ITEM_ADD / AVAILABLE_FILTER
    String ENTERPRISES_GRID = "EnterprisesGrid";
    String INSTITUTIONS_GRID = "InstitutionsGrid";
    String LOCATIONS_GRID = "LocationsGrid";
    String SITES_GRID = "SitesGrid";
    String PROGRAM_TYPES_GRID = "ProgramTypesGrid";
    String PROGRAMS_GRID = "ProgramsGrid";
}
