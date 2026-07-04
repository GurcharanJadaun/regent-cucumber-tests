package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddAwardLocators;

/**
 * Ported from regent.pages.{AddAwardPopUp,AddAwardStep1PopUp,AddAwardStep2PopUp} — collapsed into
 * one page object since this project doesn't need per-step subclassing. The wizard has 5 steps
 * (Fund/Period, Notes/Offer, Adjust Payment Period Amounts, Adjust Disbursements, Finish summary);
 * steps 3-5 all just accept the defaults via the same Continue/Finish button.
 */
public class AddAwardPage extends BasePage {

    public AddAwardPage(Page page) {
        super(page);
    }

    /** Step 1: select fund + the (only available) period, enable the eligibility override. */
    public void chooseOptionsOnStep1(String fundName) {
        click(AddAwardLocators.FUND_DROPDOWN);
        click(String.format(AddAwardLocators.FUND_OPTION, fundName));
        click(AddAwardLocators.PERIOD_DROPDOWN);
        click(AddAwardLocators.PERIOD_FIRST_OPTION);
        page.locator(AddAwardLocators.OVERRIDE_ELIGIBILITY_CHECKBOX).check();
        clickContinue();
    }

    /** Step 2: notes (New Offer already defaults to Calculated Eligibility). */
    public void chooseOptionsOnStep2() {
        page.locator(AddAwardLocators.NOTES_FIELD).fill("Award Add");
        clickContinue();
    }

    /** Step 3: Adjust Payment Period Amount(s) — accept the defaults. */
    public void clickContinueButtonOnStep3() {
        clickContinue();
    }

    /** Step 4: Adjust Disbursements — accept the defaults. */
    public void clickContinueButtonOnStep4() {
        clickContinue();
    }

    /** Step 5: Modified Academic Plan Information summary — same button, now labeled Finish. */
    public void finishAddAwardProcess() {
        clickContinue();
    }

    private void clickContinue() {
        click(AddAwardLocators.CONTINUE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    // ---- Step 1 additions ----

    /** Step 1: select fund + an explicit period by text, optionally override eligibility / enable verbose log level. */
    public void addAwardStep1(String fundName, String period, boolean overrideEligibilityRules, boolean verboseLogLevelOn) {
        click(AddAwardLocators.FUND_DROPDOWN);
        click(String.format(AddAwardLocators.FUND_OPTION, fundName));
        click(AddAwardLocators.PERIOD_DROPDOWN);
        click(String.format(AddAwardLocators.PERIOD_OPTION, period));
        if (overrideEligibilityRules) {
            page.locator(AddAwardLocators.OVERRIDE_ELIGIBILITY_CHECKBOX).check();
        }
        if (verboseLogLevelOn) {
            click(AddAwardLocators.LOG_TRACE_LEVEL_DROPDOWN);
            click(AddAwardLocators.VERBOSE_LOG_LEVEL_OPTION);
        }
        clickContinue();
    }

    /** True if fundName appears (anywhere, not just visible options) in the Select Fund dropdown. */
    public boolean isFundListed(String fundName) {
        click(AddAwardLocators.FUND_DROPDOWN);
        return isVisible(String.format(AddAwardLocators.FUND_OPTION_ANY, fundName));
    }

    /** Negative path: pick fund + current FAY period, submit, and report whether expectedErrorMessage appears. */
    public boolean addAwardStep1CausesError(String fundName, String period, String expectedErrorMessage) {
        click(AddAwardLocators.FUND_DROPDOWN);
        click(String.format(AddAwardLocators.FUND_OPTION, fundName));
        click(AddAwardLocators.PERIOD_DROPDOWN);
        click(String.format(AddAwardLocators.PERIOD_OPTION, period));
        clickContinue();
        return isVisible(String.format(AddAwardLocators.STEP1_ERROR_MESSAGE, expectedErrorMessage));
    }

    /** Negative path: pick fund + period, submit, and return the first validation summary error's text. */
    public String addAwardStep1GetError(String fundName, String period) {
        click(AddAwardLocators.FUND_DROPDOWN);
        click(String.format(AddAwardLocators.FUND_OPTION, fundName));
        click(AddAwardLocators.PERIOD_DROPDOWN);
        click(String.format(AddAwardLocators.PERIOD_OPTION, period));
        clickContinue();
        return getText(AddAwardLocators.STEP1_SUMMARY_ERROR_1);
    }

    /** Error shown BEFORE any fund is selected (packaging blocked). */
    public boolean addAwardStep1BlockingError(String expectedErrorMessage) {
        return isVisible(String.format(AddAwardLocators.STEP1_BLOCKING_ERROR, "Packaging is being blocked due to:", expectedErrorMessage));
    }

    /** Abandons the wizard from Step 1. */
    public void clickCancelButton() {
        click(AddAwardLocators.CANCEL_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public String getErrorBlock(String errorMessage) {
        return getText(String.format(AddAwardLocators.STEP1_ERROR_MESSAGE, errorMessage));
    }

    // ---- Step 2 additions ----

    public String getOfferAmount() {
        return page.locator(AddAwardLocators.NEW_OFFER_FIELD).inputValue();
    }

    public String getEligibility() {
        return getText(AddAwardLocators.CALCULATED_ELIGIBILITY);
    }

    /** Step 2: full control over eligibility override, status, offer/accepted amount, and notes. */
    public void addStep2(boolean overrideEligibility, String status, String offerAmount, String acceptedAmount, String notes) {
        if (overrideEligibility) {
            page.locator(AddAwardLocators.STEP2_OVERRIDE_ELIGIBILITY_CHECKBOX).check();
        }
        if (status != null && !status.equalsIgnoreCase("Default")) {
            click(AddAwardLocators.STATUS_CODE_DROPDOWN);
            click(String.format(AddAwardLocators.STATUS_CODE_OPTION, status));
        }
        if (offerAmount != null && !offerAmount.isEmpty()) {
            page.locator(AddAwardLocators.NEW_OFFER_FIELD).fill(offerAmount);
        }
        if (acceptedAmount != null && !acceptedAmount.isEmpty()) {
            page.locator(AddAwardLocators.ACCEPTED_AMOUNT_FIELD).fill(acceptedAmount);
        }
        page.locator(AddAwardLocators.NOTES_FIELD).fill(notes);
        clickContinue();
    }

    /** Negative path variant of addStep2 — reports whether expectedErrorMessage appears instead of advancing cleanly. */
    public boolean addStep2CausesError(boolean overrideEligibility, String status, String offerAmount, String acceptedAmount, String notes, String expectedErrorMessage) {
        if (overrideEligibility) {
            page.locator(AddAwardLocators.STEP2_OVERRIDE_ELIGIBILITY_CHECKBOX).check();
        }
        if (status != null && !status.equalsIgnoreCase("Default")) {
            click(AddAwardLocators.STATUS_CODE_DROPDOWN);
            click(String.format(AddAwardLocators.STATUS_CODE_OPTION, status));
        }
        if (offerAmount != null && !offerAmount.isEmpty()) {
            page.locator(AddAwardLocators.NEW_OFFER_FIELD).fill(offerAmount);
        }
        if (acceptedAmount != null && !acceptedAmount.isEmpty()) {
            page.locator(AddAwardLocators.ACCEPTED_AMOUNT_FIELD).fill(acceptedAmount);
        }
        page.locator(AddAwardLocators.NOTES_FIELD).fill(notes);
        clickContinue();
        return isVisible(String.format(AddAwardLocators.STEP2_ERROR_MESSAGE, expectedErrorMessage));
    }

    // ---- Step 3 additions (Adjust Payment Period Amount(s)) ----

    /** Overrides a payment period's amount if it differs from the default, then blurs to trigger recalculation. */
    public void setPaymentPeriodAmount(String paymentPeriod, String amount) {
        String fieldSelector = String.format(AddAwardLocators.STEP3_PAYMENT_PERIOD_AMOUNT, paymentPeriod);
        String defaultAmount = page.locator(fieldSelector).inputValue();
        if (!defaultAmount.equals(amount)) {
            page.locator(fieldSelector).fill(amount);
            click(AddAwardLocators.STEP3_ADJUSTED_TOTAL_HEADING);
        }
    }

    public String getAdjustedTotal() {
        return getText(AddAwardLocators.STEP3_ADJUSTED_AMOUNT_TOTAL);
    }

    public String getLoanPeriodTotal() {
        return getText(AddAwardLocators.STEP3_LOAN_PERIOD_AMOUNT_TOTAL);
    }

    // ---- Step 4 additions (Adjust Disbursements) ----

    /** disbursementNumber is 1-based, matching the row's position in Step3_PaymentPeriodDisbursementRows. */
    public void setDisbursementStatus(int disbursementNumber, String status) {
        click(String.format(AddAwardLocators.STEP4_DISBURSEMENT_STATUS_DROPDOWN, disbursementNumber));
        waitForAjaxRequestToBeFinished();
        String disbRow = String.valueOf(disbursementNumber - 1);
        click(String.format(AddAwardLocators.STEP4_DISBURSEMENT_STATUS_OPTION, disbRow, status));
        waitForAjaxRequestToBeFinished();
    }

    /** disbursement is 1-based. Only overrides the amount (and triggers recalculation) if it differs from the schedule. */
    public void setAdjustedAmount(int disbursement, String amount) {
        String scheduledDisbursementAmount = getText(String.format(AddAwardLocators.STEP4_SCHEDULED_AMOUNT, disbursement));
        if (!amount.equals(scheduledDisbursementAmount)) {
            page.locator(AddAwardLocators.STEP4_ADJUSTED_AMOUNT).nth(disbursement - 1).fill(amount);
            click(AddAwardLocators.STEP4_ADJUST_DISBURSEMENTS_TITLE);
        }
    }

    /** index is 1-based. */
    public String getPaymentPeriodTotal(int index) {
        String text = getText(String.format(AddAwardLocators.STEP4_PERIOD_TOTALS, "Payment Period Total", index));
        return text.split("\\$")[1].trim();
    }

    /** index is 1-based. */
    public String getDisbursementsTotal(int index) {
        String text = getText(String.format(AddAwardLocators.STEP4_PERIOD_TOTALS, "Disbursements Total", index));
        return text.split("\\$")[1].trim();
    }
}
