package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddContactPopUpLocators;

/**
 * Ported from regent.pages.AddContactPopUp. The source returns per-contact-type popup page
 * objects (DetailsAddressPopup/DetailsPhonePopup/DetailsEmailPopup) that aren't part of this
 * migration batch, so the select* methods here are void — they just drive the contact-type
 * dropdown up to the point where that follow-on popup would appear.
 */
public class AddContactPopUp extends BasePage {

    public AddContactPopUp(Page page) {
        super(page);
    }

    public void selectOtherPhoneContactType() {
        click(AddContactPopUpLocators.CONTACT_TYPE);
        click(AddContactPopUpLocators.SELECT_PHONE);
        click(AddContactPopUpLocators.PHONE_TYPE);
        click(AddContactPopUpLocators.OTHER_PHONE_TYPE);
    }

    public void addPhoneNumberAndSave(String phoneNumber) {
        fill(AddContactPopUpLocators.PHONE_NUMBER_FIELD, phoneNumber);
        click(AddContactPopUpLocators.SAVE_BUTTON);
    }

    public void selectAddressType() {
        click(AddContactPopUpLocators.CONTACT_TYPE);
        click(String.format(AddContactPopUpLocators.CONTACT_TYPE_ITEM, "Address"));
    }

    public void selectPhoneType() {
        click(AddContactPopUpLocators.CONTACT_TYPE);
        click(String.format(AddContactPopUpLocators.CONTACT_TYPE_ITEM, "Phone"));
    }

    public void selectEmailType() {
        click(AddContactPopUpLocators.CONTACT_TYPE);
        click(String.format(AddContactPopUpLocators.CONTACT_TYPE_ITEM, "Email"));
    }
}
