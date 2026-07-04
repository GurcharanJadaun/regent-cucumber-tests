package com.regent.locators;

/** Ported from regent.pages.RegentPlanConfigurationPage. */
public interface RegentPlanConfigurationLocators {
    String EDIT_GENERAL_TAB_BUTTON = "//button[@rem-trigger-event='editRegentPlanGeneralClick']";
    String SAVE_BUTTON = "//button[normalize-space()='Save']";

    String ENABLE_REGENT_PLAN_CHECKBOX = "#EnableRegentPlan";
    String DISPLAY_TILE_ON_DASHBOARD_CHECKBOX = "#DisplayTileOnDashboard";
    String DISPLAY_TOOLS_IN_REGENT_PLAN_CHECKBOX = "#RegentPlanDisplayToolsTileInRegentPlanComponent";
    String DISPLAY_ON_MENU_CHECKBOX = "#DisplayOnMenu";
    String DISPLAY_NAME = "#DisplayName";
    String DISPLAY_CURRENT_PROGRAM_CHECKBOX = "#RegentPlanDisplayCurrentProgram";
    String DISPLAY_SUBSIDIZED_LOAN_USAGE_CHECKBOX = "#DisplaySubsidizedLoanUsage";

    String MY_BORROWING_TAB = "//span[contains(text(),'My Borrowing')]";
}
