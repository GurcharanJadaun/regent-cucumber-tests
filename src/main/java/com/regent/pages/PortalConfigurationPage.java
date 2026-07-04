package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.PortalConfigurationLocators;

/**
 * Ported from regent.pages.PortalConfigurationPage. The source's tab-click methods each returned
 * a distinct destination page object (PortalAccountMgtPage, UsefulLinksPage, etc.) that are out of
 * scope for this batch — kept as void navigation actions here; callers construct the destination
 * page object themselves once it exists.
 */
public class PortalConfigurationPage extends BasePage {

    public PortalConfigurationPage(Page page) {
        super(page);
    }

    public void clickOnAccountMgtTab() {
        click(PortalConfigurationLocators.ACCOUNT_MGT_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnUsefulLinksTab() {
        click(PortalConfigurationLocators.USEFUL_LINKS_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnDocumentsTab() {
        click(PortalConfigurationLocators.DOCUMENTS_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Publish triggers a native confirm() alert; Playwright's default dialog handler auto-dismisses
     * it unless a listener is registered first, so accept it explicitly here (replaces the source's
     * clickWithJS() + Selenium alert().accept() combo). */
    public void clickOnPublishPortalConfigurationButton() {
        page.onceDialog(dialog -> dialog.accept());
        click(PortalConfigurationLocators.PUBLISH_CONFIGURATION_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnCommunicationsTab() {
        click(PortalConfigurationLocators.COMMUNICATIONS_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnFinancialAidTab() {
        click(PortalConfigurationLocators.FINANCIAL_AID_TAB);
        waitForAjaxRequestToBeFinished();
    }
}
