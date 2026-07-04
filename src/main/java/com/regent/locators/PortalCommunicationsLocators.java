package com.regent.locators;

/** Locators ported from regent.pages.PortalCommunicationsPage in the reference project. */
public interface PortalCommunicationsLocators {
    String EDIT_BUTTON        = "//div[contains(@data-rem-widgetname, 'InstitutionPortalMessageCenter')]//button[contains(@class,'edit_button')]";
    String SAVE_BUTTON        = "//div[contains(@data-rem-widgetname, 'InstitutionPortalMessageCenter')]//button[@type='submit']";

    String DISPLAY_ON_DASHBOARD_CHECKBOX = "#MessageCenter_DisplayMessageCenterOnDashboard";
    String ENABLE_NOTIFICATIONS_CHECKBOX = "#MessageCenter_EnableNotifications";
    String ENABLE_PORTAL_MESSAGING_CHECKBOX = "#MessageCenter_EnablePortalMessaging";
    String MESSAGES_READ_ONLY_CHECKBOX = "#MessageCenter_StudentMessagesAreReadOnly";
    String DISPLAY_INSTRUCTIONAL_MESSAGE_CHECKBOX = "#MessageCenter_DisplayInstructionalMessage";
    String INSTRUCTIONAL_MESSAGE_HEADER = "#MessageCenter_InstructionalMessageHeader";

    // Instructional message body lives inside a Kendo editor iframe
    String INSTRUCTIONAL_MESSAGE_IFRAME = "iframe.k-content";
    String MESSAGE_BODY_TEXT            = "body[autocorrect='off']";
}
