package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.regent.locators.CommunicationsLocators;

/** Ported from regent.pages.student.CommunicationsPage. SendMessagePopUp/CommunicationDetailsPopUp
 * return types from the source aren't ported, so those methods just perform the click/navigation
 * here; a step definition drives whatever popup page object it needs next. */
public class CommunicationsPage extends BasePage {

    public CommunicationsPage(Page page) {
        super(page);
    }

    public boolean isMessagePresent(String subject, String message) {
        for (Locator row : page.locator(CommunicationsLocators.MESSAGE_ROWS).all()) {
            String text = row.textContent();
            if (text.contains(subject) && text.contains(message)) return true;
        }
        return false;
    }

    /** Verifies subject/body cells specifically (columns 3 and 4), stricter than isMessagePresent(). */
    public boolean isMessageDisplayed(String subject, String message) {
        for (Locator row : page.locator(CommunicationsLocators.MESSAGE_ROWS).all()) {
            Locator cells = row.locator("td");
            String subjectCell = cells.nth(2).textContent().trim();
            String bodyCell = cells.nth(3).textContent().trim();
            if (subjectCell.equals(subject) && bodyCell.equals(message)) return true;
        }
        return false;
    }

    public void clickOnMessage(String message) {
        click(String.format(CommunicationsLocators.MESSAGE_ROW_BY_TEXT, message));
    }

    public void clickReply() {
        click(CommunicationsLocators.REPLY_LINK);
    }

    public void clickOnMessagesTab() {
        click(CommunicationsLocators.MESSAGES_TAB);
    }

    public void clickOnMessagesRefresh() {
        click(CommunicationsLocators.MESSAGES_REFRESH_BUTTON);
    }

    public void clickCreateMessage() {
        click(CommunicationsLocators.CREATE_MESSAGE_BUTTON);
    }

    public String getMessageDetailBody() {
        return getText(CommunicationsLocators.MESSAGE_DETAIL_BODY);
    }

    public void sendNotification(String notification) {
        click(CommunicationsLocators.NOTIFICATION_DROPDOWN);
        click(String.format(CommunicationsLocators.NOTIFICATION_OPTION, notification));
        click(CommunicationsLocators.ADD_NOTIFICATION_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public boolean isNotificationInActivityLog(String notification) {
        return isVisible(String.format(CommunicationsLocators.NOTIFICATIONS_ACTIVITY_LOG_ROW_BY_TEXT, notification));
    }

    public void clickNotificationLinkInActivity(String notification) {
        click(String.format(CommunicationsLocators.NOTIFICATIONS_ACTIVITY_ITEM_LINK_BY_TEXT, notification));
        waitForAjaxRequestToBeFinished();
    }
}
