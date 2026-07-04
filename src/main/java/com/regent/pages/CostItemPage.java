package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.CostItemLocators;

public class CostItemPage extends BasePage {

    public CostItemPage(Page page) {
        super(page);
    }

    public void editCost(String costValue) {
        click(CostItemLocators.EDIT_BUTTON);
        fill(CostItemLocators.COST_INPUT, costValue);
        click(CostItemLocators.SAVE_BUTTON);
    }
}
