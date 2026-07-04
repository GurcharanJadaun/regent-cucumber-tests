package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.RegentPlanConfigurationLocators;

/** Ported from regent.pages.RegentPlanConfigurationPage. */
public class RegentPlanConfigurationPage extends BasePage {

    public RegentPlanConfigurationPage(Page page) {
        super(page);
    }

    public RegentPlanConfigurationPage editGeneralPage() {
        click(RegentPlanConfigurationLocators.EDIT_GENERAL_TAB_BUTTON);
        return this;
    }

    public RegentPlanConfigurationPage saveChanges() {
        click(RegentPlanConfigurationLocators.SAVE_BUTTON);
        return this;
    }

    public RegentPlanConfigurationPage editEnableRegentPlanCB(boolean check) {
        setCheckbox(RegentPlanConfigurationLocators.ENABLE_REGENT_PLAN_CHECKBOX, check);
        return this;
    }

    public RegentPlanConfigurationPage editDisplayTileOnDashboardCB(boolean check) {
        setCheckbox(RegentPlanConfigurationLocators.DISPLAY_TILE_ON_DASHBOARD_CHECKBOX, check);
        return this;
    }

    public RegentPlanConfigurationPage editDisplayToolsInRegentPlanCB(boolean check) {
        setCheckbox(RegentPlanConfigurationLocators.DISPLAY_TOOLS_IN_REGENT_PLAN_CHECKBOX, check);
        return this;
    }

    public RegentPlanConfigurationPage editDisplayOnMenuCB(boolean check) {
        setCheckbox(RegentPlanConfigurationLocators.DISPLAY_ON_MENU_CHECKBOX, check);
        return this;
    }

    public RegentPlanConfigurationPage editDisplayName(String name) {
        fill(RegentPlanConfigurationLocators.DISPLAY_NAME, name);
        return this;
    }

    public RegentPlanConfigurationPage editDisplayCurrentProgramCB(boolean check) {
        setCheckbox(RegentPlanConfigurationLocators.DISPLAY_CURRENT_PROGRAM_CHECKBOX, check);
        return this;
    }

    public RegentPlanConfigurationPage editDisplaySubsidizedLoanUsageCB(boolean check) {
        setCheckbox(RegentPlanConfigurationLocators.DISPLAY_SUBSIDIZED_LOAN_USAGE_CHECKBOX, check);
        return this;
    }

    public void clickMyBorrowingTab() {
        click(RegentPlanConfigurationLocators.MY_BORROWING_TAB);
    }

    private void setCheckbox(String selector, boolean check) {
        if (check) {
            page.locator(selector).check();
        } else {
            page.locator(selector).uncheck();
        }
    }
}
