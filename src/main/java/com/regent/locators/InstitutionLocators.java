package com.regent.locators;

public interface InstitutionLocators {
    String EDIT_INSTITUTION_BUTTON = "//button[@rem-trigger-event='editInstitutionClick']";
    String ENABLE_YEAR_ROUND_PELL_CHECKBOX = "#Institution_EnableYearRoundPellAwarding";
    String SUBMIT_BUTTON = "//div[@class='buttonbar']//button[@type='submit']";

    // Kendo dropdowns below can be edited repeatedly across a test run, which leaves stale hidden
    // duplicate <li> option copies in the DOM (see IsirLocators/LeftNavLocators for the same
    // issue) — filter by :visible instead of the source's fragile [position()=last()].
    String COA_RECALCULATION_TRIGGER = "span[aria-owns='Institution_FreezeCOARecalcTermCode_listbox']";
    String COA_RECALCULATION_OPTION = "#Institution_FreezeCOARecalcTermCode_listbox li:visible:text-is('%s')";

    String COA_NON_TERM_RECALCULATION_TRIGGER = "span[aria-owns='Institution_FreezeCOARecalcNonTermForAllExcMnthWk_listbox']";
    String COA_NON_TERM_RECALCULATION_OPTION = "#Institution_FreezeCOARecalcNonTermForAllExcMnthWk_listbox li:visible:text-is('%s')";

    String PACKAGE_TO_NET_TRIGGER = "span[aria-owns='Institution_PackageToTheNetLoanAmountForDirectCosts_listbox']";
    String PACKAGE_TO_NET_OPTION = "#Institution_PackageToTheNetLoanAmountForDirectCosts_listbox li:visible:text-is('%s')";

    String PORTAL_BUTTON = "//span[contains(text(),'Portal')]";
    String REGENT_PLAN_TAB = "//span[normalize-space()='Regent Plan']";
    String R2T4_BUTTON = "//span[contains(text(),'R2T4')]";

    String YEAR_ROUND_PELL_CHECKBOX = "//div[@class='twelve columns' and contains(string(),'Enable Year Round Pell')]/div[@class='details-field']/input[@class='check-box']";
    String OPEID = "//div[@class='twelve columns' and .//label[@for='Institution_OpeId']]/div[@class='details-field']";

    String SPLIT_WEEKS_ACROSS_PP_TRIGGER = "span[aria-owns='Institution_SplitWeeksAcrossPaymentPeriod_listbox']";
    String SPLIT_WEEKS_ACROSS_PP_OPTION = "#Institution_SplitWeeksAcrossPaymentPeriod_listbox li:visible:text-is('%s')";

    // User Defined Fields
    String USER_DEFINED_FIELDS_TAB = "//li[contains(string(),'User Defined Fields')]";
    String EDIT_USER_DEFINED_FIELDS_BUTTON = "//button[@rem-trigger-event='editInstitutionUserDefinedFieldsClick']";
    String ADD_NEW_FIELD_BUTTON = "//button[contains(string(),'Add New Field')]";
    String FIELD_NAME_INPUT = "//div[@data-rem-widgetname='InstitutionUserDefinedFieldsEdit']//table/tbody/tr[last()]/td[1]/input";
    String FIELD_NOTES_INPUT = "//div[@data-rem-widgetname='InstitutionUserDefinedFieldsEdit']//table/tbody/tr[last()]/td[3]/input";
    String ALLOW_EFFECTIVE_DATES_CHECKBOX = "//div[@data-rem-widgetname='InstitutionUserDefinedFieldsEdit']//table/tbody/tr[last()]/td[5]/input[@type='checkbox']";
    String SAVE_USER_DEFINED_FIELD_BUTTON = "//button[contains(string(),'Save')]";
    String USER_DEFINED_FIELD_NAME = "//ul[@id='Fund_UserDefinedFieldsStatusCode_listbox']/li[@role='option' and contains(string(),'%s')]";

    String PUBLISH_PORTAL_CONFIGURATION_BUTTON = "//button[@rem-trigger-event='publishPortalConfigurationClick']";
}
