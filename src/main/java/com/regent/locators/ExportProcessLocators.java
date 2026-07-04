package com.regent.locators;

/** Ported from regent.pages.ExportProcessPage. */
public interface ExportProcessLocators {
    String SELECT_PROCESS_DROPDOWN = "//span[@class='k-widget k-dropdown k-header r-ddl']";
    String PROCESS_OPTION          = "//div[@id='IOProcess_IOProcessTypeId-list']//li[text()='%s']";

    String ORIGINATIONS_CHECKBOX        = "#IOProcess_CodSendOriginations";
    String DISBURSEMENT_RECORDS_CHECKBOX = "#IOProcess_CodSendReleasedDisbursements";
    String NEGATIVE_TRANSACTIONS_ONLY_CHECKBOX = "#IOProcess_CodSendNegativeTransactionsOnly";

    // Generic Kendo dropdown keyed by field id (FederalAwardYearId, EnterpriseId, InstitutionId, LocationId)
    String DISTRICT_SELECT = "span[aria-owns='%s_listbox']";
    String DISTRICT_ITEM   = "//ul[@aria-hidden='false']/li[contains(string(),'%s')]";

    String STUDENT_ROW_ADD_LINK = "//tr[contains(string(),'%s')]//a[string()='Add']";
    String FUND_TYPE_ADD_LINK   = "//div[@data-rem-id='FundOfficialTypesGrid']//tr[contains(string(),'%s')]//a";

    String EXPORT_BUTTON         = "//button[contains(string(),'Export')]";
    String EXPORT_BUTTON_CURRENT = "(//button[contains(string(),'Export')])[last()]";

    // Export ISIR Correction options
    String ISIR_FEDERAL_AWARD_YEAR_DROPDOWN = "span[aria-owns='IOProcess_FederalAwardYearId_listbox']";
    String ISIR_FEDERAL_AWARD_YEAR_OPTION   = "//ul[@id='IOProcess_FederalAwardYearId_listbox']/li[text()='%s']";
    String ADD_SCHOOL_BUTTON = "//div[@data-rem-id='FederalSchoolCodesGrid']//tr[contains(string(),'%s')]//a";

    // Export process names (as shown in the dropdown)
    String EXPORT_EST           = "Export EST";
    String EXPORT_NSLDS_FILES   = "Export NSLDS Files";
    String EXPORT_COD           = "Export COD";
}
