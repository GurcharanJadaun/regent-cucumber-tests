package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.DocumentSetupLocators;

/** Ported from regent.pages.DocumentSetupPage. */
public class DocumentSetupPage extends BasePage {

    public DocumentSetupPage(Page page) {
        super(page);
    }

    public void filterOnDocumentName(String documentName) {
        click(DocumentSetupLocators.NAME_FILTER);
        waitForAjaxRequestToBeFinished();
        fill(DocumentSetupLocators.FILTER_INPUT, documentName);
        click(DocumentSetupLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnDocumentName(String documentName) {
        String selector = String.format(DocumentSetupLocators.DOCUMENT_SETUP_GRID_ROW, documentName);
        click(selector);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnRestrictionsTab() {
        String selector = String.format(DocumentSetupLocators.DOCUMENT_SETUP_TAB, "Restrictions");
        click(selector);
    }

    public void clickOnEditRestrictions() {
        click(DocumentSetupLocators.EDIT_RESTRICTIONS_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void setCancelFundCheckbox(boolean check) {
        if (check) {
            page.locator(DocumentSetupLocators.CANCEL_FUNDS_CHECKBOX).check();
        } else {
            page.locator(DocumentSetupLocators.CANCEL_FUNDS_CHECKBOX).uncheck();
        }
    }

    public void clickOnSaveRestrictions() {
        click(DocumentSetupLocators.SAVE_RESTRICTIONS_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
