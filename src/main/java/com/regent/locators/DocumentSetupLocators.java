package com.regent.locators;

/** Locators ported from regent.pages.DocumentSetupPage in the reference project. */
public interface DocumentSetupLocators {
    String NAME_FILTER              = "//div[@data-rem-widgetname='DocumentGrid']//th[@data-field='Name']/a[@title='Filter']";
    String FILTER_INPUT             = "//form[@data-role='popup'][contains(@style,'block')]//input";
    String FILTER_BUTTON            = "//form[@data-role='popup'][contains(@style,'block')]//button[text()='Filter']";
    String DOCUMENT_SETUP_TAB       = "//div[@data-rem-widgetname='DocumentView']//span[@class='k-link-text' and text()='%s']";
    String EDIT_RESTRICTIONS_BUTTON = "//div[@data-rem-widgetname='DocumentViewRestrictions']//button[@rem-trigger-event='editDocumentRestrictionsClick']";
    String SAVE_RESTRICTIONS_BUTTON = "//div[@data-rem-widgetname='DocumentEditRestrictions']//button[normalize-space()='Save']";
    String CANCEL_FUNDS_CHECKBOX    = "#CancelFunds";
    String DOCUMENT_SETUP_GRID_ROW  = "//div[@data-rem-widgetname='DocumentGridView']//div[contains(@class,'grid-content')]//tr[./td[text()='%s']]";
}
