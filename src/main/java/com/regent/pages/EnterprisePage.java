package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.EnterpriseLocators;

public class EnterprisePage extends BasePage {

    public EnterprisePage(Page page) {
        super(page);
    }

    public void clickReloadRNASetupButton() {
        click(EnterpriseLocators.RELOAD_RNA_SETUP_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
