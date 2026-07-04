package com.regent.locators;

public interface ProgramManagementPopupLocators {
    String CONTINUE_BUTTON = "#btnContinue";
    String CANCEL_BUTTON   = "//button[@rem-trigger-event='cancelProgramChangeWizardClick']";

    String ACTION_TYPE_DROPDOWN = "span[aria-owns='Step1_ActivityType_listbox']";
    String ACTION_TYPE_OPTION   = "//ul[@id='Step1_ActivityType_listbox']/li[contains(string(),'%s')]";

    String IMPORT_AWARDS_TABLE_COLUMN       = "//div[@id='importedAwardGrid']//th[@data-field='%s']";
    String IMPORT_AWARDS_TABLE_COLUMN_VALUE = "//div[@id='importedAwardGrid']//tr[contains(string(),'%s')]/td[@role='gridcell']";
    String IMPORT_AWARDS_FUND_EXPAND        = "//div[@id='importedAwardGrid']//tr[contains(string(),'%s')]/td/a[contains(@class,'expand')]";

    String ADD_ACADEMIC_YEAR_BUTTON = "#sbmAdd";

    String MANUAL_AY_MOD_GRID_ISSUES_CELL = "//div[@data-rem-id='academicYearsGrid']/table[1]/tbody/tr[./td[2][text()='%s']]/td[12]/div/div";
    String MANUAL_AY_MOD_GRID_ROW_EDIT     = "//div[@data-rem-id='academicYearsGrid']/table[1]/tbody/tr[./td[2][text()='%s']]/td[13]/a";
}
