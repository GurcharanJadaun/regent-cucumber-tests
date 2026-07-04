package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.CostSetupLocators;

public class CostSetupPage extends BasePage {

    public CostSetupPage(Page page) {
        super(page);
    }

    public void clickOnCostSetupByName(String costSetupName) {
        click(String.format(CostSetupLocators.COST_SETUP_ROW, costSetupName));
    }

    public void filterCostGroupByName(String costGroupName) {
        click(CostSetupLocators.NAME_FILTER);
        waitForAjaxRequestToBeFinished();
        fill(CostSetupLocators.FILTER_INPUT, costGroupName);
        click(CostSetupLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void refreshCostGroups() {
        click(CostSetupLocators.REFRESH_COST_SETUP_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickViewDetails(String costGroupName) {
        click(String.format(CostSetupLocators.VIEW_DETAILS_BUTTON, costGroupName));
        waitForAjaxRequestToBeFinished();
    }

    public void clickAddGroupButton() {
        click(CostSetupLocators.COST_GROUP_FIRST_YES_ROW);
        click(CostSetupLocators.EDIT_GROUP_BUTTON);
        click(CostSetupLocators.ADD_GROUP_BUTTON);
    }

    public void clickViewCreatedCost(String costName) {
        click(CostSetupLocators.COST_GROUP_FIRST_YES_ROW);
        click(CostSetupLocators.NAME_FILTER);
        fill(CostSetupLocators.FILTER_INPUT, costName);
        click(CostSetupLocators.FILTER_BUTTON);
        click(String.format(CostSetupLocators.COST_ROW, costName));
    }
}
