package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.DetailsEmailLocators;

/**
 * Ported from regent.pages.student.DetailsEmailPopup. Source methods return DetailsPage; that
 * page object doesn't exist on this side yet, so these return void — the caller instantiates
 * DetailsPage itself once needed.
 */
public class DetailsEmailPopup extends BasePage {

    public DetailsEmailPopup(Page page) {
        super(page);
    }

    public void enterEmailFields(String emailType, String emailAddress) {
        String emailAddressInput = String.format(DetailsEmailLocators.EMAIL_DETAIL_INPUT, "EmailAddress");
        page.locator(emailAddressInput).fill("");
        selectEmailType(emailType);
        fill(emailAddressInput, emailAddress);
    }

    public void selectEmailType(String emailType) {
        click(String.format(DetailsEmailLocators.EMAIL_DETAILS_DROPDOWN_TRIGGER, "EmailTypeCode_listbox"));
        click(String.format(DetailsEmailLocators.EMAIL_DETAILS_LIST_ITEM, "EmailTypeCode_listbox", emailType));
    }

    public void enterEmailAddress(String emailAddress) {
        String emailAddressInput = String.format(DetailsEmailLocators.EMAIL_DETAIL_INPUT, "EmailAddress");
        page.locator(emailAddressInput).fill("");
        fill(emailAddressInput, emailAddress);
    }

    public void clickSave() {
        click(DetailsEmailLocators.SAVE_BUTTON);
    }

    public void clickDelete() {
        click(DetailsEmailLocators.DELETE_BUTTON);
    }

    /** Delete raises a native confirm dialog; dismiss it by accepting. */
    public void clickDeleteAcceptAlert() {
        page.onceDialog(dialog -> dialog.accept());
        click(DetailsEmailLocators.DELETE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
