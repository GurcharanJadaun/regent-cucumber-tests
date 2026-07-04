package com.regent.locators;

/** Locators ported from regent.pages.student.DisbursementsDetailsPopUp in the reference project. */
public interface DisbursementsDetailsLocators {
    String DISBURSEMENT_AMOUNT = "//div[@data-rem-id='awardDisbursements']//tr//td[3]";
    String CLOSE_POPUP_BUTTON  = "//a[@aria-label='Close']";

    // Row keyed by disbursement # (column 2); use with String.format then pick the column via nth-child equivalent below.
    String DISBURSEMENT_ROW_CELLS = "//div[@data-rem-id='awardDisbursements']//tr[./td[position()=2 and normalize-space()='%s']]/td";
}
