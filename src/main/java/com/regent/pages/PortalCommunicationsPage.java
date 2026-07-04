package com.regent.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.regent.locators.PortalCommunicationsLocators;

/** Ported from regent.pages.PortalCommunicationsPage. */
public class PortalCommunicationsPage extends BasePage {

    public PortalCommunicationsPage(Page page) {
        super(page);
    }

    public void clickEditButton() {
        click(PortalCommunicationsLocators.EDIT_BUTTON);
    }

    public void clickSaveButton() {
        click(PortalCommunicationsLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void displayCommunicationsComponent(boolean enable) {
        setChecked(PortalCommunicationsLocators.DISPLAY_ON_DASHBOARD_CHECKBOX, enable);
    }

    public void enableNotifications(boolean enable) {
        setChecked(PortalCommunicationsLocators.ENABLE_NOTIFICATIONS_CHECKBOX, enable);
    }

    public void enableMessaging(boolean enable) {
        setChecked(PortalCommunicationsLocators.ENABLE_PORTAL_MESSAGING_CHECKBOX, enable);
    }

    public void messagesAreReadOnly(boolean enable) {
        setChecked(PortalCommunicationsLocators.MESSAGES_READ_ONLY_CHECKBOX, enable);
    }

    public void displayInstructionalMessage(boolean enable) {
        setChecked(PortalCommunicationsLocators.DISPLAY_INSTRUCTIONAL_MESSAGE_CHECKBOX, enable);
    }

    public void inputInstructionalMessageHeader(String message) {
        fill(PortalCommunicationsLocators.INSTRUCTIONAL_MESSAGE_HEADER, message);
    }

    /** Message body is a Kendo rich-text editor rendered inside its own iframe. */
    public void inputInstructionalMessage(String message) {
        FrameLocator frame = page.frameLocator(PortalCommunicationsLocators.INSTRUCTIONAL_MESSAGE_IFRAME);
        frame.locator(PortalCommunicationsLocators.MESSAGE_BODY_TEXT).fill(message);
        waitForAjaxRequestToBeFinished();
    }

    private void setChecked(String selector, boolean checked) {
        if (checked) {
            page.locator(selector).check();
        } else {
            page.locator(selector).uncheck();
        }
    }
}
