package com.regent.locators;

/** Locators ported from regent.pages.student.DetailsPhonePopup. */
public interface DetailsPhonePopupLocators {

    // Kendo phone-type dropdown. %s in the source is always the literal id fragment
    // "PhoneTypeCode_listbox" — kept as the stable aria-owns/listbox pattern (see translation rules).
    String PHONE_TYPE_DROPDOWN   = "span[aria-owns='PhoneTypeCode_listbox']";
    String PHONE_TYPE_OPTION     = "#PhoneTypeCode_listbox li:text-is('%s')";

    String PHONE_NUMBER_INPUT    = "//input[contains(@id,'Phone_Number')]";
    String SAVE_BUTTON           = "//button[@class='k-button' and contains(string(), 'Save')]";
    String DELETE_BUTTON         = "//button[@class='k-button' and contains(string(), 'Delete')]";
}
