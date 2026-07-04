package com.regent.locators;

/** Ported from regent.pages.R2t4PopUp. This wizard is opened/edited repeatedly across a student's
 * R2T4 history, so the payment-period Kendo dropdown and the PWD tab-strip link use :visible
 * filtering instead of positional [last()]/[position()=last()] — those stale-duplicate-DOM
 * failure modes are the same ones documented in LeftNavLocators. */
public interface R2t4PopUpLocators {
    String ADD_R2T4_BUTTON    = "//button[@rem-trigger-event='R2T4AddClick']";
    String EDIT_R2T4_BUTTON   = "//div[@data-rem-widgetname='R2T4ViewR2T4']//button[@rem-trigger-event='editR2T4TopClick']";

    String PAYMENT_PERIOD_DROPDOWN     = "span[aria-owns='R2T4Model_PaymentPeriodId_listbox']";
    String PAYMENT_PERIOD_OPTION       = "#R2T4Model_PaymentPeriodId_listbox li:visible:text-is('%s')";
    String PAYMENT_PERIOD_EDIT_SELECTED = "span[aria-owns='R2T4Model_PaymentPeriodId_listbox'] span span:nth-child(1)";

    String DATE_OF_SCHOOL_WITHDREW = "#R2T4Model_DateOfDetermination";
    String DATE_OF_WITHDREW        = "#R2T4Model_WithdrawDate";

    String BLOCK_DISBURSEMENTS_CHECKBOX = "#R2T4Model_BlockDisbursements";
    String USE_50_PERCENT_CHECKBOX      = "#R2T4Model_Use50Percent";

    String SAVE_BUTTON     = "//button[contains(string(),'Save')]";
    String FINALIZE_BUTTON = "//button[@rem-trigger-event='R2T4PWDFinalizeTopClick']";
    String DELETE_BUTTON   = "//button[@rem-trigger-event='DeleteR2T4TopClick']";

    String PWD_TAB = "//div[@data-rem-id='tabs']//li/span[normalize-space()='PWD']:visible";

    String DATA_ROW = "//div[@data-rem-widgetname='R2T4Grid']//tr[contains(string(),'%s')]";
    String RESULT    = "//div[@data-rem-widgetname='R2T4Grid']//tr[contains(string(),'%s')]/td[9]";
    String LOADING    = "//div[@class='loadmask']";

    String INPUT_DATE_OF_DETERMINATION = "#R2T4Model_DateOfDetermination";
    String INPUT_DATE_OF_WITHDRAWAL    = "#R2T4Model_WithdrawDate";
    String PERIOD_START_DATE = "//div[@class='details-field r2t4_PeriodStartDate']";
    String PERIOD_END_DATE   = "//div[@class='details-field r2t4_PeriodEndDate']";
    String INPUT_NOT_REQUIRED_REASON = "#R2T4Model_NotRequiredReason";

    String INPUT_STEP2_DATE_OF_WITHDRAWAL = "#R2T4Model_Step2DateOfWithdraw";
    String INPUT_STEP2_START_DATE         = "#R2T4Model_Step2StartDate";

    String STEP2_BREAK_DAYS_1 = "//div[@class='rem-enrollment-row' and contains(string(),'Date of Withdrawal')]/div[@class='rem-grid-col-view']/div[1]/span";
    String STEP2_COMPLETED_DAYS = "//div[@class='rem-r2t4-grid-col-total-view rem-grid-row' and contains(string(),'Completed Days')]/span";

    String INPUT_STEP2_SCHEDULED_END_DATE   = "#R2T4Model_Step2ScheduledEndDate";
    String INPUT_STEP2_SCHEDULED_START_DATE = "#R2T4Model_Step2ScheduledStartDate";

    String STEP2_BREAK_DAYS_2 = "//div[@class='rem-enrollment-row' and contains(string(),'Scheduled PP End Date')]/div[@class='rem-grid-col-view']/div[1]/span";
    String STEP2_SCHEDULED_DAYS = "//div[@class='rem-r2t4-grid-col-total-view rem-grid-row' and contains(string(),'Scheduled PP Days')]/span";

    String INPUT_STEP2_AY_END_DATE_OVERRIDE = "#R2T4Model_Step2AcademicYearEndDateOverride";
    String INPUT_STEP2_LP_END_DATE_OVERRIDE = "#R2T4Model_Step2LoanPeriodEndDateOverride";
    String INPUT_STEP2_ADJUSTED_COMPLETED_DAYS = "#R2T4Model_Step2DuplicateCompletedDays";
    String INPUT_STEP2_ADJUSTED_SCHEDULED_DAYS = "#R2T4Model_Step2DuplicateScheduledDays";

    String STEP2_TOTAL_COMPLETED_DAYS = "//div[@class='rem-enrollment-row' and contains(string(),'Total Completed Days')]/span";
    String STEP2_TOTAL_PP_DAYS        = "//div[@class='rem-enrollment-row' and contains(string(),'÷ Total PP Days')]/span";
    String STEP2_PERCENTAGE_RESULT    = "//div[@class='rem-enrollment-row' and contains(string(),'Equals')]/span";
    String STEP2_CALCULATE_BUTTON     = "//button[@rem-trigger-event='calculateStep2']";

    String SAVE_BUTTON_TOP = "(//button[@type='submit' and contains(string(),'Save')])[1]";

    // View objects
    String HEADER_FIELD_DATA = "//div[@class='details-label' and contains(string(),'%s')]/following-sibling::div[@class='details-field']";
    String HEADER_PAYMENT_PERIOD_VIEW = "//div[@data-rem-id='PaymentPeriodSection']/div[@class='details-field']";
    String HEADER_START_DATE_VIEW = "//label[@for='R2T4Model_PeriodStartDate']/parent::div/parent::div//div[@class='details-field']";
    String HEADER_END_DATE_VIEW   = "//label[@for='R2T4Model_PeriodEndDate']/parent::div/parent::div//div[@class='details-field']";
    String HEADER_DETERMINATION_DATE_VIEW = "//label[@for='R2T4Model_DateOfDetermination']/parent::div/parent::div//div[@class='details-field']";
    String HEADER_WITHDRAWAL_DATE_VIEW    = "//label[@for='R2T4Model_WithdrawDate']/parent::div/parent::div//div[@class='details-field']";
    String HEADER_USE_50_PERCENT_VIEW = "//label[@for='R2T4Model_Use50Percent']/parent::div/parent::div//div[@class='details-field']/input[@checked='checked']";

    String STEP2_DATE_OF_WITHDRAWAL_VIEW = "//div[@class='rem-grid-col-view' and contains(string(),'Date of Withdrawal')]/span";
    String STEP2_PP_START_DATE_VIEW_1 = "(//div[@class='rem-grid-col-view' and contains(string(),'PP Start')]/span)[1]";
    String STEP2_BREAK_DAYS_VIEW_1    = "(//div[@class='rem-grid-col-view' and contains(string(),'Break Days')]/span)[1]";
    String STEP2_SCHEDULED_PP_END_DATE_VIEW = "//div[@class='rem-grid-col-view' and contains(string(),'Scheduled PP')]/span";
    String STEP2_PP_START_DATE_VIEW_2 = "(//div[@class='rem-grid-col-view' and contains(string(),'PP Start')]/span)[2]";
    String STEP2_AY_END_DATE_OVERRIDE_VIEW = "//div[@class='rem-grid-col-view' and contains(string(),'AY End Date Override')]/span";
    String STEP2_LP_END_DATE_OVERRIDE_VIEW = "//div[@class='rem-grid-col-view' and contains(string(),'LP End Date Override')]/span";
    String STEP2_BREAK_DAYS_VIEW_2 = "(//div[@class='rem-grid-col-view' and contains(string(),'Break Days')]/span)[2]";
    String STEP2_COMPLETED_DAYS_VIEW = "(//div[@class='rem-r2t4-grid-col-total-view' and contains(string(),'Completed Days')]/span)[1]";
    String STEP2_SCHEDULED_PP_DAYS_VIEW = "//div[@class='rem-r2t4-grid-col-total-view' and contains(string(),'Scheduled PP')]/span";
    String STEP2_ADJUSTED_COMPLETED_DAYS_VIEW = "//div[@class='rem-r2t4-grid-col-total-view' and contains(string(),'Adjusted Completed')]/span";
    String STEP2_ADJUSTED_SCHEDULED_DAYS_VIEW = "//div[@class='rem-r2t4-grid-col-total-view' and contains(string(),'Adjusted Scheduled')]/span";
    String STEP2_TOTAL_COMPLETED_DAYS_VIEW = "//div[@class='rem-enrollment-row' and contains(string(),'Total Completed')]/span";
    String STEP2_TOTAL_PP_DAYS_VIEW = "//div[@class='rem-enrollment-row' and contains(string(),'Total PP')]/span";
    String STEP2_EQUALS_VIEW = "//div[@class='rem-enrollment-row' and contains(string(),'Equals')]/span";

    String STEP2_COURSES_GRID_CELLS = "//div[@data-rem-widgetname='R2T4StudentCoursesGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td";

    String CANCEL_BUTTON = "//button[@rem-trigger-event='cancelR2T4TopClick']";
    String CLOSE_BUTTON  = "//button[@rem-trigger-event='cancelR2T4WizardClick']";
    String FINALIZE_R2T4_BUTTON = "//button[@rem-trigger-event='R2T4FinalizeTopClick']";
    String REVERSE_R2T4_BUTTON  = "//button[@rem-trigger-event='SetR2T4ReversedTopClick']";

    String HISTORY_ROW = "(//div[@data-rem-widgetname='R2T4Grid']//table)[2]//tr[contains(string(),'%s')]";
    String REFRESH_BUTTON = "//button[@rem-trigger-event='resetWizardTabs']";

    String R2T4_HISTORY_ROW_CELLS = "(//div[@data-rem-widgetname='R2T4Grid']//table)[2]//tr[contains(string(),'%s')]/td";
    String R2T4_HISTORY_STATUS    = "//div[@data-rem-widgetname='R2T4Grid']/div[contains(@class,'k-grid-content')]/table//tr[contains(string(),'%s')]/td[5]";
    String R2T4_HISTORY_COMPLETED = "(//div[@data-rem-widgetname='R2T4Grid']//table)[2]//tr[contains(string(),'%s')]/td[8]";
    String R2T4_HISTORY_RESULT    = "(//div[@data-rem-widgetname='R2T4Grid']//table)[2]//tr[contains(string(),'%s')]/td[9]";
    String R2T4_HISTORY_MANUAL_AUTOMATED = "(//div[@data-rem-widgetname='R2T4Grid']//table)[2]//tr[contains(string(),'%s')]/td[10]";

    String R2T4_STATUS_MESSAGE = "//div[contains(@style,'color: Red') and contains(string(),'%s')]";
    String R2T4_STEP2_HEADER = "//h5[contains(string(),'STEP 2: Percentage')]";
    String R2T4_STEP2_COURSE_INFO_HEADER = "//h5[contains(string(),'Student Course Information')]";

    String COMPLETE_PWD_WARNING = "//div[@data-rem-id='CompletePwdWarning' and contains(string(),'Please complete the PWD worksheet')]";
    String LOADING_INDICATOR = "//div[@class='loadmask-msg']";

    // New R2T4 confirmation popup (5.4.1.0) - can appear more than once per session, use :visible
    String CONFIRMATION_POPUP = "div[data-rem-widgetname='R2T4TabConfirmModalForm']:visible";
    String CONFIRM_YES_BUTTON = "button[rem-trigger-event='confirmedButtonClick']:visible";

    String PWD_TAB_HEADING = "(//h3[contains(text(),'Post-Withdrawal Disbursement Worksheet (PWD)')])[2]";
    String R2T4_TAB_HEADING = "(//h3[normalize-space()='R2T4 Calculation Wizard'])[2]";
    String STEP_HEADING = "//h5[contains(string(),'%s')]";

    String R2T4_STEP1_TABLE_COL = "//div[@data-rem-id='r2t4PrintArea']//div[@class='rem-enrollment' and contains(string(),'%s')]/div[@class='rem-enrollment-row' and contains(string(),'%s')]/div[@class='rem-grid-col']";
    String R2T4_EDIT_STEP1_TABLE_COL = "//div[@class='rem-enrollment' and contains(string(),'%s')]/div[@class='r2t4-grid-row' and contains(string(),'%s')]/div[@class='r2t4-grid-col']";

    String STEP1_G_TOTAL_TITLE_IV_AID_TABLE_ITEM = "//div[@class='rem-enrollment-awards' and contains(string(),'G. Total')]//div[@class='rem-enrollment-row' and contains(string(),'%s')]/div[@class='rem-grid-col']/span";

    String STEP3_BOX = "//div[@class='form-container' and contains(string(),'STEP 3')]//div[@class='rem-enrollment-awards']//div[@class='rem-r2t4-grid-col-view' and contains(string(),'%s')]/span";
    String STEP3_TOTAL_I_BOX = "//div[@class='form-container' and contains(string(),'STEP 3')]//div[@class='rem-enrollment-awards']//div[@class='rem-r2t4-grid-col-total-view']/span";

    String STEP4_BOX = "//div[@class='form-container' and contains(string(),'STEP 4')]//div[@class='rem-enrollment-awards' and contains(string(),'%s')]//div[@class='rem-r2t4-grid-col-view' and contains(string(),'%s')]/span";
    String STEP4_TOTAL_BOX = "//div[@class='form-container' and contains(string(),'STEP 4')]//div[@class='rem-enrollment-awards' and contains(string(),'%s')]//div[@class='rem-r2t4-grid-col-total-view']/span";

    String STEP2_H_VALUE = "//div[@class='form-container' and contains(string(),'STEP 2')]//div[contains(text(),'H.')]/span";
    String STEP2_H_INPUT = "//div[@class='form-container' and contains(string(),'STEP 2')]//div[contains(text(),'H.')]/input[@id='R2T4Model_Step2H_PercentagePPCompleted']";

    // Step 5
    String STEP5_INSTITUTIONAL_CHARGES_TABLE_ITEM = "//div[@class='rem-enrollment' and contains(string(),'Institutional Charges')]/div[@class='rem-enrollment-row' and contains(string(),'%s')]/div[@class='rem-grid-col'][1]";
    String STEP5_BOX = "//div[@class='form-container' and contains(string(),'STEP 5')]//div[@class='rem-enrollment-awards' and contains(string(),'%s')]//div[@class='rem-r2t4-grid-col-view' and contains(string(),'%s')]/span";
    String STEP5_TOTAL_BOX = "//div[@class='form-container' and contains(string(),'STEP 5')]//div[@class='rem-enrollment-awards' and contains(string(),'%s')]//div[@class='rem-r2t4-grid-col-total-view']/span";
    String STEP5_O_BOX_VALUE = "//div[@class='form-container' and contains(string(),'STEP 5')]//div[@class='rem-enrollment']/div[@class='rem-grid-col' and contains(string(),'O.')]/span";
    String STEP5_TUITION_INPUT = "#R2T4Model_Step5Tuition";
    String STEP5_ROOM_INPUT    = "#R2T4Model_Step5Room";
    String STEP5_BOARD_INPUT   = "#R2T4Model_Step5Board";
    String STEP5_CALCULATE_BUTTON = "//button[@rem-trigger-event='calculateStep5']";
    String STEP5_ADD_ADDITIONAL_COST_BUTTON = "//button[@rem-trigger-event='step5AddInstitutionalChargeClick']";

    // Step 6
    String STEP6_RETURN_AMOUNTS = "//div[@class='rem-enrollment' and contains(string(),'Title IV Programs')]/div[@class='rem-enrollment-row' and contains(string(),'%s')]/div[@class='rem-grid-col'][1]";
    String STEP6_RETURN_UNEARNED_IOP = "//div[@class='rem-enrollment' and contains(string(),'Title IV Programs')]/div[@class='rem-enrollment-row' and contains(string(),'%s')]/div[@class='rem-grid-col'][2]";
    String STEP6_TOTAL_LOANS_NET_DISBURSED_RETURN_AMOUNT = "//div[@class='rem-grid-col details-label' and contains(string(),'Total Loans the school must return')]/following-sibling::div[@class='rem-grid-col'][1]/span";
    String STEP6_TOTAL_LOANS_RETURN_UNEARNED_IOP_AMOUNT   = "//div[@class='rem-grid-col details-label' and contains(string(),'Total Loans the school must return')]/following-sibling::div[@class='rem-grid-col'][2]/span";
    String STEP6_TOTAL_LOANS_LOAN_TOTAL = "//div[@class='rem-grid-col details-label' and contains(string(),'Total Loans the school must return')]/following-sibling::div[@class='rem-grid-col'][3]/span";
    String STEP6_ITEM_TO_RETURN = "//div[@class='rem-enrollment' and contains(string(),'Title IV Programs')]/div[@class='rem-enrollment-row' and contains(string(),'%s')]/div[@class='rem-grid-col']/span[1]";

    // Step 9
    String STEP9_TF_VALUE = "//div[@class='form-container' and contains(string(),'STEP 9')]//div[contains(text(),'F.')]/span";
    String STEP9_T_PERCENT_VALUE = "//div[@class='form-container' and contains(string(),'STEP 9')]//div[contains(text(),'F.')]/following-sibling::div[@class='rem-r2t4-grid-col-view']/span";
    String STEP9_TT_VALUE = "//div[@class='form-container' and contains(string(),'STEP 9')]/div[@class='row' and contains(string(),'T. Amount')]/following-sibling::div[1]//div[contains(text(),'T.')]/span";
}
