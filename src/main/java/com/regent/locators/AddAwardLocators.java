package com.regent.locators;

/** Locators for the multi-step Add Award wizard, ported from regent.pages.{AddAwardPopUp,
 * AddAwardStep1PopUp,AddAwardStep2PopUp}. Filtered by :visible since Kendo leaves stale hidden
 * copies of these widgets in the DOM between wizard steps (same pattern as DocumentsLocators). */
public interface AddAwardLocators {
    String CONTINUE_BUTTON = "div.wizard-header-2 > button[type='submit']:visible";

    // Step 1 — fund & period selection. This enterprise's config only ever offers one period
    // per fund (the full award year), so no text matching is needed for it.
    String FUND_DROPDOWN       = "span[aria-owns='Step0_FundId_listbox']:visible";
    String FUND_OPTION         = "#Step0_FundId_listbox li:visible:text-is('%s')";
    String PERIOD_DROPDOWN     = "span[aria-owns='Step0_PeriodId_listbox']:visible";
    String PERIOD_FIRST_OPTION = "#Step0_PeriodId_listbox li:visible";
    String OVERRIDE_ELIGIBILITY_CHECKBOX = "#Step0_OverrideEligibilityRules";

    // Step 2 — notes (New Offer already defaults to Calculated Eligibility, no override needed)
    String NOTES_FIELD = "#Step1_Notes";

    // Fund names as they appear in the Select Fund dropdown
    String PARENT_PLUS_FUND = "Direct Parent PLUS";

    // Step 1 — period option matched by visible text (for callers that pass an explicit period
    // instead of relying on the single-default-option shortcut above), fund/period query variants,
    // verbose log trace level, cancel, and validation error blocks.
    String PERIOD_OPTION            = "#Step0_PeriodId_listbox li:visible:text-is('%s')";
    String FUND_OPTION_ANY          = "#Step0_FundId_listbox li:text-is('%s')";
    String LOG_TRACE_LEVEL_DROPDOWN = "span[aria-owns='LogTraceLevel_listbox']:visible";
    String VERBOSE_LOG_LEVEL_OPTION = "div#LogTraceLevel-list:not([aria-hidden='true']) li:text-is('Verbose')";
    String CANCEL_BUTTON            = "//button[@rem-trigger-event='cancelModifyAwardWizardClick']";
    String STEP1_ERROR_MESSAGE      = "//div[@class='validation-summary-errors' and contains(string(),'%s')]";
    String STEP1_SUMMARY_ERROR_1    = "//div[@class='validation-summary-errors']/ul/li[1]";
    String STEP1_BLOCKING_ERROR     = "//div[@class='validation-summary-errors' and contains(string(),'%s')]//li[contains(string(),'%s')]";

    // Step 2 — status code, offer/accepted amount, eligibility override, error block
    String STATUS_CODE_DROPDOWN = "span[aria-owns='Step1_StatusCode_listbox']:visible";
    String STATUS_CODE_OPTION   = "#Step1_StatusCode_listbox li:visible:text-is('%s')";
    String NEW_OFFER_FIELD      = "#Step1_NewOffer";
    String ACCEPTED_AMOUNT_FIELD = "#Step1_AcceptedAmount";
    String STEP2_OVERRIDE_ELIGIBILITY_CHECKBOX = "#Step1_OverrideEligibilityRules";
    String CALCULATED_ELIGIBILITY = "//div[@class='twelve columns' and contains(string(),'Calculated Eligibility')]/div[@class='details-field']";
    String STEP2_ERROR_MESSAGE  = "//div[@class='validation-summary-errors' and contains(string(),'%s')]";

    // Step 3 — Adjust Payment Period Amount(s)
    String STEP3_PAYMENT_PERIOD_AMOUNT = "//div[@id='gridPaymentPeriod']//tr[contains(string(),'%s')]//input[@data-rem-id='amountTextbox']";
    String STEP3_ADJUSTED_TOTAL_HEADING = "//h5[contains(string(),'Adjusted Total')]";
    String STEP3_ADJUSTED_AMOUNT_TOTAL  = "#awardAmountTotal";
    String STEP3_LOAN_PERIOD_AMOUNT_TOTAL = "#amountTotal";

    // Step 4 — Adjust Disbursements
    String STEP4_ADJUSTED_AMOUNT = ".k-formatted-value.trigger-adj-amount.number-input.fill-width.k-input";
    String STEP4_SCHEDULED_AMOUNT = "(//div[@class='rem-maw-disbursements']/div[starts-with(@class,'rem-enrollment-row-')]/div/div[3])[%s]";
    String STEP4_ADJUST_DISBURSEMENTS_TITLE = "//div[@class='rem-activity-title' and contains(string(),'Adjust Disbursements')]";
    String STEP4_PERIOD_TOTALS = "(//span[./strong[contains(string(),'%s')]])[%s]";
    String STEP4_DISBURSEMENT_STATUS_DROPDOWN = "(//div[@class='rem-maw-disbursements']/div[starts-with(@class,'rem-enrollment-row-')]/div/div[9]/span)[%s]";
    String STEP4_DISBURSEMENT_STATUS_OPTION = "//ul[@id='Step3_PaymentPeriodDisbursementRows_%s__DisbursementRows_0__DisbursementStatus_listbox' and @aria-hidden='false']/li[@role='option' and text()='%s']";
}
