package com.regent.locators;

/** Locators ported from regent.pages.FundBudgetPage in the reference project. */
public interface FundBudgetLocators {
    String BUDGET_ROW_BY_NAME    = "//div[@id='gridBudget']/div[contains(@class,'k-grid-content')]//tr/td[position()=1 and normalize-space()='%s']";
    String EDIT_BUDGET_BUTTON    = "//button[@rem-trigger-event='editFundBudgetClick']";
    String BUDGET_AMOUNT         = "//div[@class='row' and div[@class='details-label' and contains(string(),'Budget Amount')]]/div[@class='details-field']";
    String TOTAL_OFFERED_BUDGET  = "//div[@id='actualsSection']//div[@class='rem-enrollment-row-subhead']/div[1]/span";
    String SAVE_BUTTON           = "//button[normalize-space()='Save']";
    String CANCEL_BUTTON         = "//button[normalize-space()='Cancel']";
    String BUDGET_AMOUNT_INPUT   = "#FundBudget_BudgetAmount";
}
