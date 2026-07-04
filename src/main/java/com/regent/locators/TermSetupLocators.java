package com.regent.locators;

/** Ported from regent.pages.TermSetupPage. */
public interface TermSetupLocators {
    String GO_TO_LAST_PAGE_BUTTON = "//div[@class='k-content k-state-active'][contains(@style,'block')][@data-rem-widgetname='TermGridView']//span[@class='k-icon k-i-arrow-end-right']";
    String BACK_BUTTON  = "(//div[@class='k-content k-state-active']//a[@class='k-link k-pager-nav'][@title='Go to the previous page'])[last()]";
    String NEXT_BUTTON  = "//div[@data-rem-widgetname='TermGrid']//a[@title='Go to the next page']";
    String START_DATE_HEADER = "//th/a[text()='Start']";

    String TERM_NAME_CELL = "//div[contains(@id,'LocationTerms')]//tbody/tr/td[1]";

    String TERM_GRID_CENSUS_BY_TEXT = "//div[@data-rem-widgetname='TermGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[8]";
    String TERM_GRID_CELL_BY_TEXT   = "//div[@data-rem-widgetname='TermGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td";

    // Column position for External Term Id has moved across app versions (10/9/8 cols)
    String EXTERNAL_TERM_ID_COL10 = "//div[contains(@id,'LocationTerms')]//tbody/tr/td[10]";
    String EXTERNAL_TERM_ID_COL9  = "//div[contains(@id,'LocationTerms')]//tbody/tr/td[9]";
    String EXTERNAL_TERM_ID_COL8  = "//div[contains(@id,'LocationTerms')]//tbody/tr/td[8]";

    String START_DATE_CELL = "//div[contains(@id,'LocationTerms')]//div[@data-rem-id='grid']//tbody/tr/td[6]";
    String END_DATE_CELL   = "//div[@data-rem-widgetname='TermGrid']//tbody/tr/td[7]";

    String CALENDAR_FILTER_HEADER    = "//div[@class='k-content k-state-active']//th[@data-title='Academic Calendar']//a[1]";
    String NAME_FILTER_HEADER        = "//div[@class='k-content k-state-active']//th[@data-title='Name']//a[1]";
    String EXTERNAL_ID_FILTER_HEADER = "//div[@class='k-content k-state-active']//th[@data-title='External Term ID']//a[1]";

    String FILTER_INPUT   = "//form[@data-role='popup'][contains(@style,'block')]//input[@title='Value']";
    String FILTER_SUBMIT  = "//form[@data-role='popup'][contains(@style,'block')]//button[@class='k-button k-primary']";
    String CLEAR_BUTTON   = "//form[@data-role='popup'][contains(@style,'block')]//button[text()='Clear']";

    String ADD_TERM_BUTTON  = "//button[@rem-trigger-event='termAddClick']";
    String TERM_GRID_ROW_BY_TEXT = "//div[@data-rem-widgetname='TermGrid']//tr[contains(string(),'%s')]";
    String DELETE_BUTTON    = "//button[@rem-trigger-event='deleteTermClick']";
    String EDIT_TERM_BUTTON = "//button[@rem-trigger-event='editTermClick']";

    String MODULES_BUTTON = "//span[contains(text(),'Modules')]";
    String MODULE_ROW_BY_TEXT = "//div[@data-rem-widgetname='TermModuleGrid']//tr[contains(string(),'%s')]";

    String MODULE_NAME_CELL       = "//div[contains(@style,'display: block; opacity: 1;') and contains(@id,'Term')]//table[@class='k-selectable']//tr/td[1]";
    String MODULE_START_DATE_CELL = "//div[contains(@style,'display: block; opacity: 1;') and contains(@id,'Term')]//table[@class='k-selectable']//tr/td[2]";
    String MODULE_END_DATE_CELL   = "//div[contains(@style,'display: block; opacity: 1;') and contains(@id,'Term')]//table[@class='k-selectable']//tr/td[3]";
    String MODULE_FREEZE_COA_DATE_CELL = "//div[contains(@style,'display: block; opacity: 1;') and contains(@id,'Term')]//table[@class='k-selectable']//tr/td[4]";
    String MODULE_EXPECTED_ATTENDANCE_DATE_CELL = "//div[contains(@style,'display: block; opacity: 1;') and contains(@id,'Term')]//table[@class='k-selectable']//tr/td[5]";
    String MODULE_EXTERNAL_ID_CELL = "//div[contains(@style,'display: block; opacity: 1;') and contains(@id,'Term')]//table[@class='k-selectable']//tr/td[7]";

    String FILTER_OPTIONS_LIST_ITEM_BY_TEXT = "//ul[@aria-hidden='false']//li[contains(text(),'%s')]";
    String MODULE_ATTRIBUTE_HEADER_BY_TEXT  = "//div[@data-rem-widgetname='TermModuleGrid']//th[contains(string(),'%s')]";

    String ADD_MODULE_BUTTON  = "//button[@rem-trigger-event='moduleAddClick']";
    String SAVE_MODULE_BUTTON = "//form[@action='/Term/UpdateModule']//button[@type='submit']";
    String VALIDATION_REPORT  = "//div[@class='validation-summary-errors']";

    String MODULE_NAME_INPUT          = "#Module_Name";
    String MODULE_START_DATE_INPUT    = "#Module_StartDate";
    String MODULE_END_DATE_INPUT      = "#Module_EndDate";
    String EXTERNAL_MODULE_ID_INPUT   = "#Module_ExternalModuleId";
    String FREEZE_COA_DATE_INPUT      = "#Module_FreezeCOADate";
    String EXPECTED_ATTENDANCE_DATE_INPUT = "#Module_ExpectedAttendanceDate";

    String DELETE_MODULE_BUTTON = "//button[@rem-trigger-event='deleteModuleClick']";
    String EDIT_MODULE_BUTTON   = "//button[@rem-trigger-event='editModuleClick']";

    String PROGRAMS_BUTTON = "//div[@data-rem-widgetname='TermView']//span[contains(text(),'Programs')]";
    String PROGRAM_FILTER  = "//div[@class='k-content k-state-active' and contains(@style,'display: block;')]//th[@data-field='ProgramName']//a";
    String PROGRAM_CELL_BY_COLUMN = "//div[contains(@data-rem-id,'TermProgramsGrid')]//tbody//td[%s]";
    String PROGRAM_TABLE_HEADER_COLUMNS = "//div[contains(@data-rem-id,'TermProgramsGrid')]//thead//th";

    String CURRENT_PAGE_NUMBER = "//div[@class='k-content k-state-active' and contains(@style,'display: block; ')]//span[@class='k-state-selected']";
    String REFRESH_BUTTON = "//div[@class='k-content k-state-active']//button[@rem-trigger-event='refreshGridClick']";
    String TERM_TABLE_HEADERS = "//div[@data-rem-widgetname='TermGrid']/div[@class='k-grid-header']//table//th";

    String FILTER_IS_EQUAL_TO = "Is equal to";
}
