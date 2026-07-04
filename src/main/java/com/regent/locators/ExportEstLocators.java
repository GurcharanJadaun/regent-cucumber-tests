package com.regent.locators;

/** Ported from regent.pages.ExportEstPage — the Export EST wizard. */
public interface ExportEstLocators {
    String EST_PAGE_HEADER = "//h3[contains(string(),'Export Student Transactions')]";

    String EST_TYPE_SELECT   = "span[aria-owns='IOProcess_EstTypeCode_listbox']";
    String EST_TYPE_OPTION   = "//li[contains(string(),'%s')]";

    String TRANSACTION_TYPE_SELECT = "span[aria-owns='IOProcess_EstTransactionSignOptionCode_listbox']";
    String TRANSACTION_TYPE_OPTION = "//ul[@id='IOProcess_EstTransactionSignOptionCode_listbox']/li[contains(string(),'%s')]";

    String PAYMENT_PERIOD_START = "#IOProcess_PaymentPeriodRangeStart";
    String PAYMENT_PERIOD_END   = "#IOProcess_PaymentPeriodRangeEnd";

    String ALL_FAYS_CHECKBOX          = "#IOProcess_SelectAllFederalAwardYears";
    String FAY_GRID_ITEM              = "//div[@data-rem-id='FederalAwardYearsGrid']//tr[contains(string(),'%s')][1]//a";

    String ALL_ENTERPRISES_CHECKBOX   = "#IOProcess_SelectAllEnterprises";
    String ALL_INSTITUTIONS_CHECKBOX  = "#IOProcess_SelectAllInstitutions";
    String ALL_CAMPUSES_CHECKBOX      = "#IOProcess_SelectAllLocations";
    String ALL_SITES_CHECKBOX         = "#IOProcess_SelectAllSites";

    String SITE_FROM_DISBURSEMENT_RADIO = "//label[normalize-space()='Site with which disbursements are associated']/input[@id='IOProcess_EstUseSiteFromDisbursement']";

    String ENTERPRISE_GRID_ITEM  = "//div[@data-rem-id='EnterprisesGridArea'][not(contains(@style,'display: none'))]//tr[contains(string(),'%s')]//a";
    String INSTITUTION_GRID_ITEM = "//div[@data-rem-id='InstitutionsGridArea'][not(contains(@style,'display: none'))]//tr[contains(string(),'%s') ]//a";
    String CAMPUS_GRID_ITEM      = "//div[@data-rem-id='LocationsGridArea'][not(contains(@style,'display: none'))]//tr[contains(string(),'%s') ]//a";
    String SITES_GRID_ITEM       = "//div[@data-rem-id='SitesGridArea'][not(contains(@style,'display: none'))]//tr[contains(string(),'%s') ]//a";

    // Reference used positional [last()] here; that's fragile per established convention, so this
    // filters by :visible instead of relying on DOM order of stale duplicate grid filter buttons.
    String STUDENTS_FILTER_BUTTON = "//div[@data-rem-id='StudentsGrid']//th[@data-field='ExternalId1']/a[@title='Filter' and not(ancestor::*[contains(@style,'display: none')])]";

    String GRID_FILTER_BUTTON = "//div[@data-rem-id='%s']//th[@data-title='%s']/a[@title='Filter']";
    String FILTER_INPUT  = "//form[@aria-hidden='false']//input[@title='Value']";
    String FILTER_SUBMIT = "//form[@aria-hidden='false']//button[@type='submit']";

    String STUDENT_ROW = "//tr[contains(string(),'%s')]//a[string()='Add']";

    String TERMS_PAGINATION = "//div[@data-rem-id='TermsGrid']//ul[@class='k-pager-numbers k-reset']//li";
    String TERM_ITEM        = "//div[@data-rem-id='TermsGrid']//tr[contains(string(),'%s')]//a";

    String ALL_PROGRAM_TYPES_CHECKBOX = "#IOProcess_SelectAllProgramTypes";
    String ALL_PROGRAMS_CHECKBOX      = "#IOProcess_SelectAllPrograms";
    String ALL_TERMS_CHECKBOX         = "#IOProcess_SelectAllTerms";

    String LOAN_GRID_ITEM = "//div[@data-rem-id='FundsGrid']//tr[contains(string(), '%s')]//a";
    String EXPORT_BUTTON  = "//button[contains(string(),'Export')]";
    String FUND_FILTER    = "//div[@data-rem-id='FundsGrid']//th[@data-field='Name']//a[@title = 'Filter']";
    String ALL_FUNDS_CHECKBOX = "#IOProcess_SelectAllFunds";

    String SELECT_PROCESS_DROPDOWN = "span[aria-owns='IOProcess_IOProcessTypeId_listbox']";
    String PROCESS_FROM_LIST       = "//div[@id='IOProcess_IOProcessTypeId-list']//li[text()='%s']";

    // Expected Process Settings
    String EXPECTED_PROCESS_SETTINGS_HEADING = "//h3[text() = 'Expected Process Settings']";
    String INPUT_OFFSET_DAYS = "#IOProcess_EstOffsetDays";
    String OVERRIDE_ELIGIBILITY_CHECKBOX = "#IOProcess_EstOverrideEligibilityCheck";
    String OVERRIDE_DOCUMENT_REQ_CHECKBOX = "#IOProcess_EstOverrideDocumentRequirementCheck";
    String OVERRIDE_SAP_CHECKBOX = "#IOProcess_EstOverrideSAPCheck";
    String OVERRIDE_COD_CHECKBOX = "#IOProcess_EstOverrideCODCheck";
    String INCLUDE_0_DISBURSEMENTS_CHECKBOX = "#IOProcess_EstInclude0DollarDisbursements";
    String INCLUDE_NON_DISBURSABLE_FUNDS_CHECKBOX = "#IOProcess_EstOverrideAllowNonDisbursableFunds";
    String INCLUDE_0_DISB_NO_VALIDATION_CHECKBOX = "#IOProcess_EstInclude0DollarDisbursementsWithoutValidationChecks";
    String INCLUDE_UNFUNDED_AWARDS_CHECKBOX = "#IOProcess_EstIncludeUnfundedAwards";
    String INCLUDE_OFFERED_AWARDS_CHECKBOX = "#IOProcess_EstIncludeOfferedAwards";
}
