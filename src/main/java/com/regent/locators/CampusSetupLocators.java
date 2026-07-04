package com.regent.locators;

/** Locators ported from regent.pages.CampusSetupPage. */
public interface CampusSetupLocators {

    String EXTERNAL_ID       = "(//div[contains(@id,'Location')]//div[@class='six columns']//div[@class='details-field'])[1]";
    String FEDERAL_SCHOOL_ID = "//div[contains(@id,'Location')]//label[contains(text(),'School')]/../../div[2]";
    String EDIT_CAMPUS_BUTTON = "//button[@rem-trigger-event='editLocationClick']";
    String SAVE_BUTTON        = "//button[contains(string(),'Save')]";

    String DELAY_FIRST_DISBURSEMENT_30_DAYS_DROPDOWN = "span[aria-owns='DelayFirstDisbursement30DaysDisplay_listbox']:visible";
    String DELAY_FIRST_DISBURSEMENT_30_DAYS_OPTION    = "#DelayFirstDisbursement30DaysDisplay_listbox li:visible:text-is('%s')";

    String R2T4_TAB_BUTTON = "//div[@data-rem-widgetname='LocationView']//span[contains(text(),'R2T4')]";
}
