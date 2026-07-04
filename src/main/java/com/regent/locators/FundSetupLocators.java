package com.regent.locators;

/** Ported from regent.pages.FundSetupPage. */
public interface FundSetupLocators {
    String NAME_FILTER = "//div[@class='k-content k-state-active']//th[@data-title='Fund Name']//a[1]";
    String FUND_ROW = "//td[string()='%s']/parent::tr";
    String EDIT_BUTTON = "//button[@rem-trigger-event='editFundClickTop']";
    String AVAILABLE_DOCUMENTS_FILTER = "//th[@data-title='Available Document(s)']/a";

    // Kendo dropdowns edited repeatedly across scenarios — filter by :visible per the app-wide
    // stale-duplicate-listbox issue rather than positional [last()].
    String FUND_SCOPE_CODE_LISTBOX = "span[aria-owns='Fund_FundScopeCode_listbox']";
    String FUND_SCOPE_CODE_ITEM = "#Fund_FundScopeCode_listbox li:visible:text-is('%s')";
    String BUDGET_SCOPE_CODE_LISTBOX = "span[aria-owns='Fund_FundBudgetScopeCode_listbox']";
    String BUDGET_SCOPE_CODE_ITEM = "#Fund_FundBudgetScopeCode_listbox li:visible:text-is('%s')";

    String AWARD_START_DATE = "//tbody/tr//input[@data-rem-id='dt-picker-from']";
    String AWARD_END_DATE = "//tbody/tr//input[@data-rem-id='dt-picker-to']";
    String SAVE_FUND_BUTTON = "//button[@type='submit' and contains(string(), 'Save')]";

    String ALTERNATIVE_FAY_SCHEDULE_LISTBOX = "span[aria-owns='Fund_AwardPeriodCalendarTypeCode_listbox']";
    String ALTERNATIVE_FAY_SCHEDULE_ITEM = "#Fund_AwardPeriodCalendarTypeCode_listbox li:visible:text-is('Aug 01 - Jul 31')";

    String FAY_SELECT = "//tbody/tr//span[@class='k-widget k-dropdown k-header']";
    String FAY_ITEM = "//div[@aria-hidden='false']//li[text()='%s']";

    String FUND_INITIAL_STATUS_LISTBOX = "span[aria-owns='Fund_InitialAwardStatusCode_listbox']";
    String FUND_INITIAL_STATUS_ITEM = "#Fund_InitialAwardStatusCode_listbox li:visible:text-is('%s')";

    String FUND_USER_DEFINED_FIELDS_LISTBOX = "span[aria-owns='Fund_UserDefinedFieldsStatusCode_listbox']";
    String FUND_USER_DEFINED_FIELD_ITEM = "#Fund_UserDefinedFieldsStatusCode_listbox li:visible:text-is('%s')";

    // "Show Advanced Options" is expanded by default as of 7.0, so this section may already be absent
    String AWARD_RULES_ADVANCED_OPTIONS = "//div[@id='awardRules']//div[@data-rem-id='awardingRulesAdvancedSettings' and contains(string(),'Show Advanced Options')]";
    String BUDGETS_TAB = "//span[@class='k-link-text' and contains(text(),'Budgets')]";
}
