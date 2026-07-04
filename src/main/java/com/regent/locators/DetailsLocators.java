package com.regent.locators;

/** Locators ported from regent.pages.student.DetailsPage in the reference project. */
public interface DetailsLocators {

    // Left sub-section nav within the Details tab (e.g. StudentInformation, ContactInformation...)
    String DETAILS_SECTION_LINK = "//li[contains(@data-rem-id,'%s')]/a";

    String ADD_CONTACT_BUTTON   = "//button[contains(@rem-trigger-event,'%s')]";
    String ADDED_PHONE_NUMBER   = "//tr/td[contains(string(),'%s')]";
    String ADDED_REFERENCE      = "//li[@data-rem-id='ParentSpouseReferenceInformation']//tr/td[contains(string(),'Reference1')]";
    String FERPA_VALUE_FOR_REFERENCE_ROW = "//div[@data-rem-id='ParentSpouseGrid']//tr[contains(string(),'Reference1')]//td[7]";
    String EDIT_REFERENCE_BUTTON = "//div[@data-rem-id='ParentSpouseGrid']//tr[contains(string(),'Reference1')]//a[@role='button']";
    String PARENT_SPOUSE_REFERENCE_EDIT_BUTTON = "//div[@data-rem-id='ParentSpouseGrid']//tr[contains(string(),'%s')]//a[@role='button']";

    // Student Portal Authorizations
    String PAY_NON_INSTITUTIONAL_CHARGES_VALUE = "//label[@for='StudentPortalData_PayNonInstitutionalCharges']/../../div[last()]";
    String HOLD_EXCESS_TITLE4_VALUE = "//div[@class='details-label' and ./label[@for='StudentPortalData_HoldExcessTitle4']]/following-sibling::div[@class='details-field']";
    String CREDIT_BALANCE_OPTION_VALUE = "//div[@class='details-label' and ./label[@for='StudentPortalData_CreditBalanceOption']]/following-sibling::div[@class='details-field']";
    String PAY_PRIOR_YEAR_MINOR_CHARGES_VALUE = "//div[@class='details-label' and ./label[@for='StudentPortalData_PayPriorYearMinorCharges']]/following-sibling::div[@class='details-field']";
    String EDIT_AUTHORIZATION_BUTTON = "//button[@rem-trigger-event='editStudentPortalAuthorizations']";

    // Kendo dropdowns — stable aria-owns trigger + listbox-id-scoped option (rule: avoid
    // positional [last()] xpath, which is fragile once a widget has been opened/edited more
    // than once and Kendo leaves stale hidden duplicates behind).
    String PAY_NON_INSTITUTIONAL_CHARGES_DROPDOWN = "span[aria-owns='StudentPortalData_PayNonInstitutionalCharges_listbox']";
    String PAY_NON_INSTITUTIONAL_CHARGES_OPTION   = "//ul[@id='StudentPortalData_PayNonInstitutionalCharges_listbox']/li[text()='%s']";
    String HOLD_EXCESS_TITLE4_DROPDOWN = "span[aria-owns='StudentPortalData_HoldExcessTitle4_listbox']";
    String HOLD_EXCESS_TITLE4_OPTION   = "//ul[@id='StudentPortalData_HoldExcessTitle4_listbox']/li[text()='%s']";
    String CREDIT_BALANCE_OPTION_DROPDOWN = "span[aria-owns='StudentPortalData_CreditBalanceOption_listbox']";
    String CREDIT_BALANCE_OPTION_OPTION   = "//ul[@id='StudentPortalData_CreditBalanceOption_listbox']/li[text()='%s']";
    String PAY_PRIOR_YEAR_MINOR_CHARGES_DROPDOWN = "span[aria-owns='StudentPortalData_PayPriorYearMinorCharges_listbox']";
    String PAY_PRIOR_YEAR_MINOR_CHARGES_OPTION   = "//ul[@id='StudentPortalData_PayPriorYearMinorCharges_listbox']/li[text()='%s']";

    String SAVE_AUTHORIZATION_BUTTON = "//form[@action='/Student/UpdatePortalAuthorizationsSection']//button[text()='Save']";

    // Institutionally Defined Data
    String ADD_INSTITUTIONALLY_DEFINED_INFO_BUTTON = "//div[contains(@id,'StudentDetailsInstitutionallyDefinedData')]//button[@id='addButton']";
    String EDIT_INSTITUTIONALLY_DEFINED_INFO_ROW_BUTTON = "//div[@data-rem-id='sgrd_AddInf']/table/tbody/tr[contains(string(),'%s')]/td[7]/a";
    String REFRESH_DETAILS_BUTTON = "//div[@data-rem-widgetname='StudentDetailsView']//button[@rem-trigger-event='refreshDetails']";
    String INSTITUTIONALLY_DEFINED_DATA_ROW_CELL = "//div[@data-rem-id='sgrd_AddInf']//tr[contains(@class,'k-master-row') and contains(string(),'%s')]/td[@role='gridcell']";
    String IDD_ROW_EXPAND_COLLAPSE = "//div[@data-rem-id='sgrd_AddInf']//tr[contains(@class,'k-master-row') and contains(string(),'%s')]/td[@class='k-hierarchy-cell']/a[@aria-label='%s']";
    String IDD_HISTORY_GRID_CELLS = "//div[@data-rem-id='sgrd_AddInf']//tr[contains(@class,'k-master-row') and contains(string(),'%s')]/following-sibling::tr[@class='k-detail-row']//tbody/tr[%s]/td";

    // Communications opt-out
    String EDIT_OPT_OUT_BUTTON = "//button[normalize-space()='Edit']";
    String SAVE_OPT_OUT_BUTTON = "//button[normalize-space()='Save']";
    String EMAIL_OPT_OUT_CHECKBOX = "#StudentInformation_OptOutOfEmailCommunications";
    String TEXT_OPT_OUT_CHECKBOX  = "#StudentInformation_OptOutOfTextCommunications";
    String LETTER_OPT_OUT_CHECKBOX = "#StudentInformation_OptOutOfLetterCommunications";

    // Generic "details-field following a label" lookup, scoped to a named widget section
    String STUDENT_DETAILS_ITEM = "//div[@data-rem-widgetname='%s']//div[@class='details-label' and contains(string(),'%s')]/following-sibling::div[@class='details-field']";

    // Contact Information — Address grid
    String ADDRESS_GRID_CELLS  = "//div[@data-rem-widgetname='StudentContactsView']//div[@data-rem-id='sgrd_Addrss']//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']";
    String ADDRESS_EDIT_BUTTON = "//div[@data-rem-id='sgrd_Addrss']//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']/a[text()='Edit']";
    String ADDRESS_ROW         = "//div[@data-rem-id='sgrd_Addrss']//tbody/tr[contains(string(),'%s')]";

    // Contact Information — Phone grid
    String PHONE_GRID_CELLS  = "//div[@data-rem-widgetname='StudentContactsView']//div[@data-rem-id='sgrd_Phones']//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']";
    String PHONE_EDIT_BUTTON = "//div[@data-rem-id='sgrd_Phones']//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']/a[text()='Edit']";
    String PHONE_ROW         = "//div[@data-rem-id='sgrd_Phones']//tbody/tr[contains(string(),'%s')]";

    // Contact Information — Email grid
    String EMAIL_GRID_CELLS  = "//div[@data-rem-widgetname='StudentContactsView']//div[@data-rem-id='sgrd_Emails']//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']";
    String EMAIL_EDIT_BUTTON = "//div[@data-rem-id='sgrd_Emails']//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']/a[text()='Edit']";
    String EMAIL_ROW         = "//div[@data-rem-id='sgrd_Emails']//tbody/tr[contains(string(),'%s')]";

    // Test Scores
    String TEST_SCORES_GRID_CELLS = "//div[@data-rem-id='sgrd_Testsc']//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']";

    // Parent/Spouse/Reference grid
    String PARENT_SPOUSE_GRID_CELLS = "//div[@data-rem-widgetname='StudentParentSpouseSectionView']//div[@data-rem-id='ParentSpouseGrid']//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']";
    String PARENT_SPOUSE_GRID_EXPAND_ROW = "(//div[@data-rem-id='ParentSpouseGrid'])[last()]//tbody/tr[contains(string(),'%s')]/td[1]/a[@aria-label='Expand']";
    String PARENT_SPOUSE_ADDRESS_GRID_CELLS = "//div[@data-rem-id='ParentSpouseGrid']//div[contains(@class,'addressesGrid')]//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']";
    String PARENT_SPOUSE_EMAILS_GRID_CELLS  = "//div[@data-rem-id='ParentSpouseGrid']//div[contains(@class,'emailsGrid')]//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']";
    String PARENT_SPOUSE_PHONES_GRID_CELLS  = "//div[@data-rem-id='ParentSpouseGrid']//div[contains(@class,'phonesGrid')]//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']";
    String PARENT_SPOUSE_REF_CONTACT_EDIT_BUTTON = "//div[contains(@class,'%s')]//tbody/tr[contains(string(),'%s')]/td[@role='gridcell']/a[text()='Edit']";
    String PARENT_SPOUSE_REF_CONTACT_ADD_BUTTON  = "//button[@class='k-button %s-add-button']";

    // Ability To Benefit
    String ATB_DETAILS_FIELD = "//div[contains(@id,'StudentAbilityToBenefit')]//div[@class='details-label' and ./label[@for='%s']]/following-sibling::div";
}
