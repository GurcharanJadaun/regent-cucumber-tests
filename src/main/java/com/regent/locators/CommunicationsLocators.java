package com.regent.locators;

/** Ported from regent.pages.student.CommunicationsPage. */
public interface CommunicationsLocators {
    String MESSAGES_TAB = "//div[@data-rem-widgetname='StudentCommunicationsView']//span[@class='k-link-text' and contains(string(),'Messaging')]";

    String COMMUNICATIONS_DEFAULT_GRID_ROWS = "//div[@data-rem-widgetname='StudentNotificationsGridView']//div[contains(@data-rem-id,'Grid')]//tbody/tr";
    String MESSAGE_ROWS       = "//div[@data-rem-widgetname='StudentMessagesGrid']//tbody/tr";
    String MESSAGE_ROW_BY_TEXT = "//div[@data-rem-widgetname='StudentMessagesGrid']//tbody/tr[contains(string(),'%s')]";

    String REPLY_LINK           = "//button[@rem-trigger-event='replyMessageClick']";
    String MESSAGES_REFRESH_BUTTON = "//div[contains(@id,'StudentMessages')]//button[contains(string(),'Refresh')]";
    String CREATE_MESSAGE_BUTTON   = "//div[contains(@id,'StudentMessages')]//button[contains(string(),'Create')]";

    String MESSAGE_DETAIL_SUBJECT = "//div[@data-rem-id='dvMsgData']//div[@class='columns' and contains(string(),'Subject')]/div[@class='details-field']";
    String MESSAGE_DETAIL_BODY    = "//div[@data-rem-id='dvMsgData']//div[@data-rem-id='dvBody']";

    String NOTIFICATION_DROPDOWN     = "//div[contains(@id,'StudentNotifications')]//span[@aria-owns='CommunicationId_listbox']";
    String NOTIFICATION_OPTION       = "//ul[@id='CommunicationId_listbox']/li[contains(text(),'%s')]";
    String ADD_NOTIFICATION_BUTTON   = "//div[contains(@id,'StudentNotifications')]//button[@rem-trigger-event='GenerateActivityCommunication']";

    String NOTIFICATIONS_ACTIVITY_LOG_ROW_BY_TEXT = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]";
    String NOTIFICATIONS_ACTIVITY_ITEM_LINK_BY_TEXT = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr/td[position()=3 and contains(string(),'%s')]/a";
}
