package com.regent.locators;

public interface SendMessagePopUpLocators {
    // Message body lives inside an iframe in the source app; Playwright's frameLocator
    // handles that directly in the page object rather than needing a frame-switch locator.
    String MESSAGE_IFRAME = "iframe";
    String MESSAGE_BODY    = "body";
    String SUBJECT_INPUT   = "#Message_Subject";
    String SEND_BUTTON     = "form[action*='StudentMessageUpdate'] button[type='submit']";
}
