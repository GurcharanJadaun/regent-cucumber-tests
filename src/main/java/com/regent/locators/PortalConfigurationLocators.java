package com.regent.locators;

/** Ported from regent.pages.PortalConfigurationPage. */
public interface PortalConfigurationLocators {
    String ACCOUNT_MGT_TAB     = "//span[text()='Account Mgt']";
    String USEFUL_LINKS_TAB    = "//span[contains(text(),'Useful Links')]";
    String DOCUMENTS_TAB       = "//span[contains(@data-content-url,'DocumentsViewPartial')]";
    String PUBLISH_CONFIGURATION_BUTTON = "//button[@rem-trigger-event='publishPortalConfigurationClick']";
    String COMMUNICATIONS_TAB  = "//span[contains(text(),'Communications')]";
    String FINANCIAL_AID_TAB   = "//span[contains(text(),'Financial Aid')]";
}
