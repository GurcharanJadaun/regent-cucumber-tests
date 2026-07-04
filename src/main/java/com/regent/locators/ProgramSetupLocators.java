package com.regent.locators;

/** Locators ported from regent.pages.ProgramSetupPage (and its Filter superclass) in the reference project. */
public interface ProgramSetupLocators {

    // Programs grid
    String PROGRAM_ROW               = "//div[@data-rem-id='gridPrograms']//td[3][text()>=4]/../td[%s]";
    String PROGRAM_ROW_BY_NAME       = "//div[@data-rem-id='gridPrograms']/div[contains(@class,'k-grid-content')]/table/tbody/tr[contains(string(),'%s')]";
    String CALENDAR_TYPE             = "//div[@data-rem-id='gridPrograms']//td[3][text()>=2]/../td[6]";
    String PROGRAM_NAME              = "//div[@data-rem-id='gridPrograms']//td[3][text()>=4]/../td[1]";
    String FIRST_PROGRAM             = "//div[@data-rem-id='gridPrograms']//tbody/tr";

    // Grid column filters
    String TYPE_FILTER               = "//th[contains(string(),'%s')]/a[contains(@class,'k-grid-filter')]";
    String CALENDAR_TYPE_FILTER      = "//th[@data-field='AcademicCalendarTypeName']/a[@title='Filter']";
    String NAME_FILTER               = "//div[@data-rem-id='gridPrograms']//th[contains(string(),'Name')]/a[contains(@class,'k-grid-filter')]";
    String FILTER_INPUT              = "//form[@data-role='popup'][contains(@style,'block')]//input";
    String FILTER_BUTTON             = "//form[@data-role='popup'][contains(@style,'block')]//button[text()='Filter']";
    String CLEAR_BUTTON              = "//form[@data-role='popup'][contains(@style,'block')]//button[text()='Clear']";

    // Terms / Enrollment Levels tab
    String TERMS_ENROLLMENT_LEVELS_BUTTON = "//span[contains(text(),'Terms / Enrollment Levels')]";
    String EDIT_TERMS_BUTTON         = "//button[@rem-trigger-event='editProgramTermClick']";
    String TERMS_FILTER              = "//th[@data-field='TermName']//span[@class='k-icon k-i-filter']";
    String TERM_INPUT                = "//form[contains(@style,'display: block')]//input[@title='Value']";
    String SUBMIT_FILTER_BUTTON      = "//form[contains(@style,'display: block')]//button[@type='submit']";
    String ADD_TERM_BUTTON           = "//a[@class='k-button k-button-icontext add-button k-grid-Add']";
    String SAVE_BUTTON               = "//div[@class='buttonbar']//button[@type='submit']";
    String TERM_FILTER               = "//th[@data-title='Term']/a";
    String TERM_ROW                  = "//tr[contains(string(),'%s')]//a";

    // Program add/edit/delete
    String ADD_PROGRAM_BUTTON        = "//button[@rem-trigger-event='programAddClick']";
    String EDIT_PROGRAM_BUTTON       = "//button[@rem-trigger-event='editProgramGeneralClick']";
    String DELETE_BUTTON             = "//button[@rem-trigger-event='deleteProgramGeneralClick']";

    // Auto Include Header/Trailer
    String AUTO_INCLUDE_HEADER_INFO           = "#autoIncludeHeaderTrailerClueTip";
    String AUTO_INCLUDE_HEADER_FOR_AY_FUNDS   = "//label[@for='Program_AutoIncludeHeaderTrailer']";
    String AUTO_INCLUDE_HEADER_DESC_POPUP     = "//div[@id='cluetip' and not(contains(@style,'display: none;'))]";
    String AUTO_INCLUDE_HEADER_INPUT          = "//span[@aria-labelledby='Program_AutoIncludeHeaderTrailer_label']";

    // Calendar type select (Kendo dropdown) — stable aria-owns pattern per translation rules.
    String CALENDAR_TYPE_SELECT_BUTTON = "span[aria-owns='Program_AcademicCalendarTypeCode_listbox']";
    String CALENDAR_TYPE_SELECT_OPTION = "//ul[@id='Program_AcademicCalendarTypeCode_listbox']/li[contains(string(),'%s')]";

    // R2T4 tab
    String R2T4_TAB                      = "//div[@data-rem-widgetname='ProgramView']//span[contains(text(),'R2T4')]";
    String METHOD_FOR_PROJECTING_NONTERM_R2T4PP = "//*[@class='details-label-long' and contains(string(),'Method for Projecting Nonterm R2T4')]/following-sibling::div";
    String EDIT_R2T4_BUTTON              = "//button[@rem-trigger-event='editTabClick']";
    String SAVE_R2T4_BUTTON              = "//form[@action='/Program/R2T4SettingsTabUpdate']//button[@type='submit']";

    // R2T4 method-for-projecting select. This dropdown is opened/edited repeatedly within a single
    // R2T4 settings edit session, so Kendo can leave stale hidden duplicate <li> copies in the DOM —
    // filter by :visible rather than positional [last()].
    String R2T4_METHOD_SELECTION         = "span[aria-owns='Settings_ProjectingNontermR2T4PPEndDateMethodCode_listbox']";
    String R2T4_METHOD_OPTION            = "#Settings_ProjectingNontermR2T4PPEndDateMethodCode_listbox li:visible:text-is('%s')";

    String DAYS_IN_R2T4_WHEN_0_UNITS     = "#Settings_DaysInR2T4PPWhen0UnitsCompleted";

    String POTENTIAL_R2T4_IS_REQUIRED_SELECT = "span[aria-owns='Settings_IsPotentialR2T4Required_listbox']";
    String POTENTIAL_R2T4_IS_REQUIRED_OPTION = "#Settings_IsPotentialR2T4Required_listbox li:visible:text-is('%s')";

    String OFFSET_DOD_NEED_R2T4          = "#Settings_EvaluationR2T4NeedDODOffset";
    String OFFSET_DOD_AUTO_FINALIZE_R2T4 = "#Settings_DODToAutoFinalizeRT24Offset";

    String VALIDATION_SUMMARY_ERRORS     = "//div[@data-rem-widgetname='R2T4SettingsTab']//div[@class='validation-summary-errors']//li";
    String OFFSET_DOD_AUTO_FINALIZE_R2T4_INFO_ICON = "//a[@rel='#DODToAutoFinalizeRT24OffsetHint']/span[contains(@class,'rem-icon-information')]";
    String INFO_TIP_POPUP                = "//div[contains(@class,'ui-cluetip-content')]";

    String INHERIT_OFFSET_DOD_NEED_R2T4_CHECKBOX          = "#InheritEvaluationR2T4NeedDODOffset";
    String INHERIT_OFFSET_DOD_AUTO_FINALIZE_R2T4_CHECKBOX = "#InheritDODToAutoFinalizeRT24Offset";

    String CANCEL_BUTTON = "//button[normalize-space()='Cancel']";

    String OFFSET_FROM_DOD_NEED_R2T4_VALUE            = "//div[@class='four-five columns' and contains(string(),'Offset from DOD for Evaluation')]//div[@class='details-field-short']";
    String OFFSET_FROM_DOD_TO_AUTO_FINALIZE_R2T4_VALUE = "//div[@class='four-five columns' and contains(string(),'Offset from DOD to Auto')]//div[@class='details-field-short']";

    // Date Used to Identify IOP — repeatedly re-opened while stepping through options, so filter by :visible.
    String DATE_USED_TO_IDENTIFY_IOP_SELECT       = "span[aria-owns='Settings_DateUsedToIdentR2T4InadvertentOverpaymentsCode_listbox']";
    String DATE_USED_TO_IDENTIFY_IOP_OPTION       = "#Settings_DateUsedToIdentR2T4InadvertentOverpaymentsCode_listbox li:visible:text-is('%s')";
    String DATE_USED_TO_IDENTIFY_IOP_OPTIONS      = "#Settings_DateUsedToIdentR2T4InadvertentOverpaymentsCode_listbox li:visible";
    String DATE_USED_TO_IDENTIFY_IOP_OPTION_VALUE = "span[aria-owns='Settings_DateUsedToIdentR2T4InadvertentOverpaymentsCode_listbox'] span.k-input";

    String DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_SELECT  = "span[aria-owns='Settings_DateUsedToIdentR2T4InadvertentOverpaymentsDisbursementOptionCode_listbox']";
    String DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION  = "#Settings_DateUsedToIdentR2T4InadvertentOverpaymentsDisbursementOptionCode_listbox li:visible:text-is('%s')";
    String DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS = "#Settings_DateUsedToIdentR2T4InadvertentOverpaymentsDisbursementOptionCode_listbox li:visible";
    String DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE   = "span[aria-owns='Settings_DateUsedToIdentR2T4InadvertentOverpaymentsDisbursementOptionCode_listbox'] span.k-input";

    String LOADING_MASK = "//div[@class='loadmask']";

    // SEI Capella
    String FSA_ELIGIBLE_END_DATE_TEXTBOX = "#Program_FSAEligibleEndDate";
    String SAVE_PROGRAM_BUTTON           = "//div[contains(@class,'twelve')]//button[@type='submit']";
    String FSA_END_DATE                  = "//*[@class='details-label' and contains(string(),'FSA Eligible End Date')]/following-sibling::div";
}
