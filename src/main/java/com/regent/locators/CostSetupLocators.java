package com.regent.locators;

public interface CostSetupLocators {
    String COST_GROUP_FIRST_YES_ROW = "//div[@data-rem-id='costSetupGrid']//table//tr[contains(string(),'Yes')][1]";
    String COST_SETUP_ROW           = "//div[@data-rem-id='costSetupGrid']//table//tr[contains(string(),'%s')]";

    String EDIT_GROUP_BUTTON = "//button[@rem-trigger-event='editCostSetupClick']";
    String ADD_GROUP_BUTTON  = "//button[@rem-trigger-event='costGroupAddClick']";

    String NAME_FILTER   = "//div[@id='gridCostGroups']//th[@data-field='Name']/a[@title='Filter']";
    String FILTER_INPUT  = "//form[@data-role='popup'][contains(@style,'block')]//input";
    String FILTER_BUTTON = "//form[@data-role='popup'][contains(@style,'block')]//button[text()='Filter']";

    String COST_ROW            = "//tr[contains(string(),'%s')]//a";
    String VIEW_DETAILS_BUTTON = "//tr[./td[text()='%s']]/td/a";

    String REFRESH_COST_SETUP_BUTTON = "//button[@rem-trigger-event='refreshCostSetupClick']";
}
