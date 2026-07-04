package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.SitesLocators;

/** Ported from regent.pages.SitesPage. */
public class SitesPage extends BasePage {

    public SitesPage(Page page) {
        super(page);
    }

    public String getSiteName() {
        return getText(SitesLocators.SITE_NAME);
    }

    public String getExternalSiteId() {
        return getText(SitesLocators.EXTERNAL_SITE_ID);
    }
}
