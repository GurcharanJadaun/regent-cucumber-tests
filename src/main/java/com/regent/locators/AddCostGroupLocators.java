package com.regent.locators;

public interface AddCostGroupLocators {
    String COST_GROUP_NAME        = "#CostGroup_Name";
    String COST_START_DATE        = "#CostGroup_EffectiveStartDate";
    String COST_END_DATE          = "#CostGroup_EffectiveEndDate";

    String SITES_GRID_ROW_BY_TEXT    = "//div[@id='availableSitesGrid']//tr[contains(string(),'%s')]//a";
    String AVAILABLE_SITES_FILTER    = "//div[@id='availableSitesGrid']//a[@title='Filter']";
    String PROGRAM_FILTER_BUTTON     = "//div[@id='availableProgramsGrid']//a[@title='Filter']";
    String PROGRAM_ROW_BY_TEXT       = "//div[@id='availableProgramsGrid']//tr[contains(string(),'%s')]//a";

    String FILTER_INPUT  = "//form[@data-role='popup'][contains(@style,'block')]//input";
    String FILTER_BUTTON = "//form[@data-role='popup'][contains(@style,'block')]//button[text()='Filter']";

    String SAVE_BUTTON            = "//div[contains(@id,'CostGroupAdd')]//button[@type='submit']";
    String EDIT_BUTTON            = "//button[@rem-trigger-event='editCostGroupClick']";
    String ADD_COST_BUTTON        = "//button[@rem-trigger-event='costItemAddClick']";
    String DELETE_BUTTON          = "//button[@rem-trigger-event='deleteCostGroupClick']";
    String COST_ITEM_VIEW_BUTTON_BY_TEXT = "//div[@id='gridCostItems']//tr[contains(string(),'%s')]//a";
}
