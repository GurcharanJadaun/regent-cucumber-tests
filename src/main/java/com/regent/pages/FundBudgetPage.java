package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.FundBudgetLocators;

/** Ported from regent.pages.FundBudgetPage. */
public class FundBudgetPage extends BasePage {

    public FundBudgetPage(Page page) {
        super(page);
    }

    public void selectBudget(String budgetName) {
        click(String.format(FundBudgetLocators.BUDGET_ROW_BY_NAME, budgetName));
    }

    public void setBudgetAmountToOffered(String budgetName) {
        selectBudget(budgetName);
        String totalOffered = getText(FundBudgetLocators.TOTAL_OFFERED_BUDGET)
                .split("\\.")[0].replaceAll("[$,]", "");
        click(FundBudgetLocators.EDIT_BUDGET_BUTTON);
        fill(FundBudgetLocators.BUDGET_AMOUNT_INPUT, totalOffered);
        click(FundBudgetLocators.SAVE_BUTTON);
    }

    public void setBudgetAmount(String amount) {
        click(FundBudgetLocators.EDIT_BUDGET_BUTTON);
        if (amount.contains("$")) {
            amount = amount.replaceAll("[$,]", "").replace(".00", "");
        }
        fill(FundBudgetLocators.BUDGET_AMOUNT_INPUT, amount);
        click(FundBudgetLocators.SAVE_BUTTON);
    }

    public String getBudgetAmount() {
        return getText(FundBudgetLocators.BUDGET_AMOUNT);
    }

    public String getBudgetOfferedAmount() {
        return getText(FundBudgetLocators.TOTAL_OFFERED_BUDGET);
    }
}
