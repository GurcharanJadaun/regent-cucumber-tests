package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.DetailsPhonePopupLocators;

/** Ported from regent.pages.student.DetailsPhonePopup. */
public class DetailsPhonePopup extends BasePage {

    public DetailsPhonePopup(Page page) {
        super(page);
    }

    public void enterPhoneFields(String phoneType, String phoneNumber) {
        fill(DetailsPhonePopupLocators.PHONE_NUMBER_INPUT, "");
        selectPhoneType(phoneType);
        fill(DetailsPhonePopupLocators.PHONE_NUMBER_INPUT, phoneNumber);
    }

    public void selectPhoneType(String phoneType) {
        click(DetailsPhonePopupLocators.PHONE_TYPE_DROPDOWN);
        String optionSelector = String.format(DetailsPhonePopupLocators.PHONE_TYPE_OPTION, phoneType);
        click(optionSelector);
    }

    public void enterPhoneNumber(String phoneNumber) {
        fill(DetailsPhonePopupLocators.PHONE_NUMBER_INPUT, phoneNumber);
    }

    public void clickSave() {
        page.locator(DetailsPhonePopupLocators.SAVE_BUTTON).scrollIntoViewIfNeeded();
        click(DetailsPhonePopupLocators.SAVE_BUTTON);
    }

    public void clickDelete() {
        click(DetailsPhonePopupLocators.DELETE_BUTTON);
    }

    /** Deletes and accepts the native confirm() dialog the app raises for this action. */
    public void clickDeleteAcceptAlert() {
        page.onceDialog(dialog -> dialog.accept());
        click(DetailsPhonePopupLocators.DELETE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
