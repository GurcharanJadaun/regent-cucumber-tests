package com.regent.locators;

/** Locators ported from regent.pages.SitesPage in the reference project. */
public interface SitesLocators {
    String SITE_NAME        = "//div[contains(@id,'Sites')]//table[@role='grid']//td[3]";
    String EXTERNAL_SITE_ID = "//div[contains(@id,'Sites')]//table[@role='grid']//td[2]";
}
