package com.regent.locators;

/** Ported from regent.pages.AddContactPopUp. */
public interface AddContactPopUpLocators {
    String CONTACT_TYPE = "span[aria-owns='ContactTypeId_listbox']";
    String CONTACT_TYPE_ITEM = "//ul[@id='ContactTypeId_listbox']/li[contains(string(),'%s')]";

    String SELECT_PHONE = "//li[@class='k-item' and contains(string(), 'Phone')]";
    String PHONE_TYPE = "span[aria-owns='Phone_PhoneTypeCode_listbox']";
    String OTHER_PHONE_TYPE = "//ul[@id='Phone_PhoneTypeCode_listbox']/li[contains(string(),'Other')]";
    String PHONE_NUMBER_FIELD = "#Phone_Number";
    String SAVE_BUTTON = "//button[@class='k-button' and contains(string(), 'Save')]";
}
