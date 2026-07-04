package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.FundSetupLocators;

/** Ported from regent.pages.FundSetupPage. */
public class FundSetupPage extends BasePage {

    public FundSetupPage(Page page) {
        super(page);
    }

    public void filterByFundName(String fundName) {
        click(FundSetupLocators.NAME_FILTER);
        // filterInput/filterSubmit come from the source's Filter base — reuse the shared popup pattern.
        fill("//form[@data-role='popup' and contains(@style,'block')]//input[@title][not(contains(@style,'none'))]", fundName);
        click("//form[@data-role='popup' and contains(@style,'block')]//button[@type='submit' and string()='Filter']");
    }

    public void clickOnFund(String fundName) {
        click(String.format(FundSetupLocators.FUND_ROW, fundName));
    }

    private void expandAdvancedOptionsIfPresent() {
        if (isVisibleNow(FundSetupLocators.AWARD_RULES_ADVANCED_OPTIONS)) {
            click(FundSetupLocators.AWARD_RULES_ADVANCED_OPTIONS);
            waitForAjaxRequestToBeFinished();
        }
    }

    /** Sets fund/budget scope to Academic Year and the three-year AY award date ranges. */
    public void updateFundToAY() {
        click(FundSetupLocators.EDIT_BUTTON);
        click(FundSetupLocators.FUND_SCOPE_CODE_LISTBOX);
        click(String.format(FundSetupLocators.FUND_SCOPE_CODE_ITEM, "Academic Year"));

        click(FundSetupLocators.BUDGET_SCOPE_CODE_LISTBOX);
        click(String.format(FundSetupLocators.BUDGET_SCOPE_CODE_ITEM, "Academic Year"));

        expandAdvancedOptionsIfPresent();

        page.locator(FundSetupLocators.AWARD_START_DATE).nth(0).fill("07/01/2018");
        page.locator(FundSetupLocators.AWARD_START_DATE).nth(1).fill("07/01/2019");
        page.locator(FundSetupLocators.AWARD_START_DATE).nth(2).fill("07/01/2020");
        page.locator(FundSetupLocators.AWARD_END_DATE).nth(0).fill("06/30/2019");
        page.locator(FundSetupLocators.AWARD_END_DATE).nth(1).fill("06/30/2020");
        page.locator(FundSetupLocators.AWARD_END_DATE).nth(2).fill("06/30/2021");

        click(FundSetupLocators.SAVE_FUND_BUTTON);
    }

    /** Sets fund/budget scope to Federal Award Year with the alternate Aug-Jul FAY schedule. */
    public void updateFundToFAY() {
        click(FundSetupLocators.EDIT_BUTTON);
        click(FundSetupLocators.FUND_SCOPE_CODE_LISTBOX);
        click(String.format(FundSetupLocators.FUND_SCOPE_CODE_ITEM, "Federal Award Year"));

        // Budget Scope must equal Fund Scope
        click(FundSetupLocators.BUDGET_SCOPE_CODE_LISTBOX);
        click(String.format(FundSetupLocators.BUDGET_SCOPE_CODE_ITEM, "Federal Award Year"));

        click(FundSetupLocators.ALTERNATIVE_FAY_SCHEDULE_LISTBOX);
        click(FundSetupLocators.ALTERNATIVE_FAY_SCHEDULE_ITEM);

        expandAdvancedOptionsIfPresent();

        page.locator(FundSetupLocators.FAY_SELECT).nth(0).click();
        click(String.format(FundSetupLocators.FAY_ITEM, "2017-2018"));

        page.locator(FundSetupLocators.FAY_SELECT).nth(1).click();
        click(String.format(FundSetupLocators.FAY_ITEM, "2018-2019"));

        click(FundSetupLocators.SAVE_FUND_BUTTON);
    }

    public void clickEdit() {
        click(FundSetupLocators.EDIT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickSave() {
        click(FundSetupLocators.SAVE_FUND_BUTTON);
    }

    public void setInitialAwardStatus(String awardStatus) {
        click(FundSetupLocators.FUND_INITIAL_STATUS_LISTBOX);
        click(String.format(FundSetupLocators.FUND_INITIAL_STATUS_ITEM, awardStatus));
    }

    public void setInitialUseUDFAwardStatus(String udfName) {
        expandAdvancedOptionsIfPresent();
        click(FundSetupLocators.FUND_USER_DEFINED_FIELDS_LISTBOX);
        click(String.format(FundSetupLocators.FUND_USER_DEFINED_FIELD_ITEM, udfName));
    }

    /** Navigates to the Budgets tab. FundBudgetPage itself isn't part of this migration batch. */
    public void clickBudgetTab() {
        click(FundSetupLocators.BUDGETS_TAB);
    }
}
