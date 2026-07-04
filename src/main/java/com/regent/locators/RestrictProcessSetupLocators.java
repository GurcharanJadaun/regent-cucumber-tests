package com.regent.locators;

/** Ported from regent.pages.RestrictProcessSetupPage. Inlines the fund-name grid filter locators
 * that lived on the source's Filter/TaskSetupPage base classes, since this project has no
 * equivalent shared hierarchy. */
public interface RestrictProcessSetupLocators {
    String EDIT_BUTTON = "//button[@rem-trigger-event='editRestrictProcessSetupClick']";
    String SAVE_BUTTON = "//button[normalize-space()='Save']";

    String AVAILABLE_PROCESS_ADD_LINK = "//div[@id='availableProcessesGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[2]/a";

    String SELECT_ALL_FUNDS_CHECKBOX = "#TaskType_AllFunds";
    String AVAILABLE_FUND_NAME_FILTER = "//div[@id='availableFundsGrid']//th[@data-title='Fund Name']/a";
    String AVAILABLE_FUND_ADD_LINK = "//div[@id='availableFundsGrid']//tr[contains(string(),'%s')]/td[@class='k-command-cell']/a";

    // Kendo grid filter popup, shared by any grid column's filter link
    String FILTER_INPUT  = "//form[@data-role='popup' and contains(@style,'block')]//input[@title][not(contains(@style,'none'))]";
    String FILTER_SUBMIT = "//form[@data-role='popup' and contains(@style,'block')]//button[@type='submit' and string()='Filter']";
}
