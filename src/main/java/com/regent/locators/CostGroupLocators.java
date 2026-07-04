package com.regent.locators;

/** Ported from regent.pages.CostGroupPage — Cost Group / Cost Items grid. */
public interface CostGroupLocators {
    String NAME_FILTER = "//div[@id='gridCostItems']//th[@data-field='Name']/a[@title='Filter']";
    String FILTER_INPUT = "//form[@data-role='popup'][contains(@style,'block')]//input";
    String FILTER_BUTTON = "//form[@data-role='popup'][contains(@style,'block')]//button[text()='Filter']";

    String VIEW_DETAILS_BUTTON = "//tr[contains(string(),'%s')]/td/a";
    String COST_ITEM_GRID_CELLS = "//div[@id='gridCostItems']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td";
    String COST_ITEM_GRID_HEADER_COLUMNS = "//div[@id='gridCostItems']/div[@class='k-grid-header']//th[@role='columnheader']";
}
