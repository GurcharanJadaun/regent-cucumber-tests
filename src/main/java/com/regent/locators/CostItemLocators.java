package com.regent.locators;

/** Ported from regent.pages.CostItemPage. */
public interface CostItemLocators {
    String EDIT_BUTTON = "//div[@data-rem-id='CostItemGeneralDetails']//button[@rem-trigger-event='editCostItemClick']";
    String SAVE_BUTTON = "//button[contains(string(),'Save')]";
    String COST_INPUT  = "#CostItem_BaseCost";
}
