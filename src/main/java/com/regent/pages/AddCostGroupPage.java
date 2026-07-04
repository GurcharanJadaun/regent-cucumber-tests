package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddCostGroupLocators;

/** Ported from regent.pages.AddCostGroupPage. Enterprise/ProgramData/CostItemData model plumbing
 * from the source isn't available on this side, so names/dates are passed in as plain strings
 * already resolved by the caller; CostSetupPage/AddCostItemPage return values are dropped since
 * those page objects have no port yet. */
public class AddCostGroupPage extends BasePage {

    public AddCostGroupPage(Page page) {
        super(page);
    }

    public void addCostGroup(String costName, String startDate, String endDate, String site, String programName) {
        fill(AddCostGroupLocators.COST_GROUP_NAME, costName);
        fill(AddCostGroupLocators.COST_START_DATE, startDate);
        fill(AddCostGroupLocators.COST_END_DATE, endDate);
        click(AddCostGroupLocators.AVAILABLE_SITES_FILTER);
        fill(AddCostGroupLocators.FILTER_INPUT, site);
        click(AddCostGroupLocators.FILTER_BUTTON);
        click(String.format(AddCostGroupLocators.SITES_GRID_ROW_BY_TEXT, site));
        click(AddCostGroupLocators.PROGRAM_FILTER_BUTTON);
        fill(AddCostGroupLocators.FILTER_INPUT, programName);
        click(AddCostGroupLocators.FILTER_BUTTON);
        click(String.format(AddCostGroupLocators.PROGRAM_ROW_BY_TEXT, programName));
        click(AddCostGroupLocators.SAVE_BUTTON);
    }

    public void clickAddCostItemButton() {
        click(AddCostGroupLocators.EDIT_BUTTON);
        click(AddCostGroupLocators.ADD_COST_BUTTON);
    }

    public void clickDeleteButton() {
        page.onceDialog(dialog -> dialog.accept());
        click(AddCostGroupLocators.DELETE_BUTTON);
    }

    public void clickCostItemViewButton(String costItemName) {
        click(String.format(AddCostGroupLocators.COST_ITEM_VIEW_BUTTON_BY_TEXT, costItemName));
        waitForAjaxRequestToBeFinished();
    }
}
