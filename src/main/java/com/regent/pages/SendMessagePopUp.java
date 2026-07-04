package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.SendMessagePopUpLocators;

/** Ported from regent.pages.SendMessagePopUp. */
public class SendMessagePopUp extends BasePage {

    public SendMessagePopUp(Page page) {
        super(page);
    }

    public void sendMessage(String message) {
        page.frameLocator(SendMessagePopUpLocators.MESSAGE_IFRAME)
                .locator(SendMessagePopUpLocators.MESSAGE_BODY)
                .fill(message);
        click(SendMessagePopUpLocators.SEND_BUTTON);
    }

    public void sendMessage(String subject, String message) {
        fill(SendMessagePopUpLocators.SUBJECT_INPUT, subject);
        sendMessage(message);
    }
}
