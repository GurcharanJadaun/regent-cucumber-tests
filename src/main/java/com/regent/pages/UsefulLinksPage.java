package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.UsefulLinksLocators;

/** Ported from regent.pages.UsefulLinksPage. */
public class UsefulLinksPage extends BasePage {

    public UsefulLinksPage(Page page) {
        super(page);
    }

    public int getNumberOfUsefulLinks() {
        return page.locator(UsefulLinksLocators.USEFUL_LINK_TABLE_ROWS).count();
    }

    public String getUsefulLinkText(int rowNum) {
        page.locator(UsefulLinksLocators.TABLE_BOTTOM_ROW).scrollIntoViewIfNeeded();
        return getText(String.format(UsefulLinksLocators.USEFUL_LINKS_TEXT_BY_ROW, rowNum));
    }

    public String getUsefulLinkUrl(int rowNum) {
        return getText(String.format(UsefulLinksLocators.USEFUL_LINKS_URL_BY_ROW, rowNum));
    }

    public String getUsefulLinkUrlDescription(int rowNum) {
        return getText(String.format(UsefulLinksLocators.USEFUL_LINKS_DESC_BY_ROW, rowNum));
    }

    public void clickOnEditButton() {
        click(UsefulLinksLocators.EDIT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnSaveButton() {
        click(UsefulLinksLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnCancelButton() {
        click(UsefulLinksLocators.CANCEL_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void moveLinkToTop() {
        page.locator(UsefulLinksLocators.FEDERAL_STUDENT_AID_ROW).dragTo(page.locator(UsefulLinksLocators.TABLE_TOP));
    }

    /**
     * Repeats the drag until the row lands at the bottom — the grid can require more than one
     * drag to fully re-sort, matching the source's do/while retry.
     */
    public void moveLinkToBottom() {
        do {
            page.locator(UsefulLinksLocators.FEDERAL_STUDENT_AID_ROW).dragTo(page.locator(UsefulLinksLocators.TABLE_BOTTOM));
        } while (!getUsefulLinkText(getNumberOfUsefulLinks()).contains(
                page.locator(UsefulLinksLocators.FEDERAL_STUDENT_AID_ROW).textContent().trim()));
    }

    public void moveBottonLinkToTop() {
        waitForAjaxRequestToBeFinished();
        page.locator(UsefulLinksLocators.TABLE_BOTTOM_ROW).scrollIntoViewIfNeeded();
        page.locator(UsefulLinksLocators.TABLE_BOTTOM_ROW).dragTo(page.locator(UsefulLinksLocators.TABLE_TOP_ROW));
        waitForAjaxRequestToBeFinished();
    }

    public void moveTopLinkToBottom() {
        waitForAjaxRequestToBeFinished();
        page.locator(UsefulLinksLocators.TABLE_BOTTOM_ROW).scrollIntoViewIfNeeded();
        page.locator(UsefulLinksLocators.TABLE_TOP_ROW).dragTo(page.locator(UsefulLinksLocators.TABLE_BOTTOM_ROW));
        page.locator(UsefulLinksLocators.TABLE_BOTTOM_ROW).dragTo(page.locator(UsefulLinksLocators.TABLE_NEXT_TO_LAST_ROW));
        waitForAjaxRequestToBeFinished();
    }
}
