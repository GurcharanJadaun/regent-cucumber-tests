package com.regent.locators;

/** Ported from regent.pages.InstitutionR2T4Page. */
public interface InstitutionR2T4Locators {
    String EDIT_BUTTON   = "//button[@rem-trigger-event='editTabClick']";
    String SAVE_BUTTON   = "//button[@type='submit' and contains(string(),'Save')]";
    String CANCEL_BUTTON = "//button[normalize-space()='Cancel']";

    String POTENTIAL_R2T4_REQUIRED_DROPDOWN = "span[aria-owns='Settings_IsPotentialR2T4Required_listbox']";
    String POTENTIAL_R2T4_REQUIRED_OPTION   = "#Settings_IsPotentialR2T4Required_listbox li:visible:text-is('%s')";

    String OFFSET_DOD_NEED_R2T4          = "#Settings_EvaluationR2T4NeedDODOffset";
    String OFFSET_DOD_AUTO_FINALIZE_R2T4 = "#Settings_DODToAutoFinalizeRT24Offset";

    String IS_POTENTIAL_R2T4_REQUIRED_VALUE = "//div[@class='twelve columns' and contains(string(),'Identify Whether a Potential R2T4 is Required')]/div[@class='details-field']";
    String OFFSET_FROM_DOD_NEED_R2T4_VALUE  = "//div[@class='twelve columns' and contains(string(),'Offset from DOD for Evaluation')]//div[@class='details-field']";
    String OFFSET_FROM_DOD_AUTO_FINALIZE_R2T4_VALUE = "//div[@class='twelve columns' and contains(string(),'Offset from DOD to Auto')]//div[@class='details-field']";

    // Native <select> — use .selectOption()
    String AUTO_FINALIZE_TYPES_SELECT = "#Settings_AutoFinalizeR2T4TypeCodes";

    // General Settings dropdowns
    String DATE_USED_TO_IDENTIFY_IOP_DROPDOWN = "span[aria-owns='Settings_DateUsedToIdentR2T4InadvertentOverpaymentsCode_listbox']";
    String DATE_USED_TO_IDENTIFY_IOP_OPTION   = "#Settings_DateUsedToIdentR2T4InadvertentOverpaymentsCode_listbox li:visible:text-is('%s')";
    String DATE_USED_TO_IDENTIFY_IOP_OPTIONS  = "#Settings_DateUsedToIdentR2T4InadvertentOverpaymentsCode_listbox li:visible";
    String DATE_USED_TO_IDENTIFY_IOP_VALUE    = "span[aria-owns='Settings_DateUsedToIdentR2T4InadvertentOverpaymentsCode_listbox'] span.k-input";

    String DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN = "span[aria-owns='Settings_DateUsedToIdentR2T4InadvertentOverpaymentsDisbursementOptionCode_listbox']";
    String DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION   = "#Settings_DateUsedToIdentR2T4InadvertentOverpaymentsDisbursementOptionCode_listbox li:visible:text-is('%s')";
    String DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS  = "#Settings_DateUsedToIdentR2T4InadvertentOverpaymentsDisbursementOptionCode_listbox li:visible";
    String DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE    = "span[aria-owns='Settings_DateUsedToIdentR2T4InadvertentOverpaymentsDisbursementOptionCode_listbox'] span.k-input";

    String DATE_USED_TO_CONSIDER_LOAN_ORIGINATED_DROPDOWN = "span[aria-owns='Settings_DateUsedToConsiderNonRebuildLoansAsOriginatedCode_listbox']";
    String DATE_USED_TO_CONSIDER_LOAN_ORIGINATED_OPTION   = "#Settings_DateUsedToConsiderNonRebuildLoansAsOriginatedCode_listbox li:visible:text-is('%s')";

    String CRITERIA_MPN_LINKED_TO_LOAN_DROPDOWN = "span[aria-owns='Settings_MPNLinkedToLoanCriteriaTypeCode_listbox']";
    String CRITERIA_MPN_LINKED_TO_LOAN_OPTION   = "#Settings_MPNLinkedToLoanCriteriaTypeCode_listbox li:visible:text-is('%s')";

    String AUTO_CANCEL_EXCLUDE_TITLE_IV_DISBURSEMENT_DROPDOWN = "span[aria-owns='Settings_AutoCancelExcludedTitleIVDisbursementsForR2T4_listbox']";
    String AUTO_CANCEL_EXCLUDE_TITLE_IV_DISBURSEMENT_OPTION   = "#Settings_AutoCancelExcludedTitleIVDisbursementsForR2T4_listbox li:visible:text-is('%s')";

    String VALIDATION_FAILED_ERROR    = "//div[@data-rem-widgetname='R2T4SettingsTab']//div[@class='validation-summary-errors']/span[text()='Validation Failed.']";
    String VALIDATION_SUMMARY_ERRORS  = "//div[@data-rem-widgetname='R2T4SettingsTab']//div[@class='validation-summary-errors']//li";

    String OFFSET_DOD_AUTO_FINALIZE_R2T4_INFO_ICON = "//a[@rel='#DODToAutoFinalizeRT24OffsetHint']/span[contains(@class,'rem-icon-information')]";
    String INFO_TIP_POPUP = "//div[contains(@class,'ui-cluetip-content')]";

    // Option labels reused across getter methods
    String DATE_OF_DETERMINATION = "Date of Determination";
    String DATE_OF_WITHDRAWAL    = "Date of Withdrawal";
    String AFTER_WDD             = "Include disbursement as IOP if the disbursement release date is after the WDD";
    String EQUAL_OR_AFTER_WDD    = "Include disbursement as IOP if the disbursement release date equal to or after the WDD";
    String AFTER_DOD             = "Include disbursement as IOP if the disbursement release date is after the DOD";
    String EQUAL_OR_AFTER_DOD    = "Include disbursement as IOP if the disbursement release date equal to or after the DOD";
}
