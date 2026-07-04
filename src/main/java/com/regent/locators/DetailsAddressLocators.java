package com.regent.locators;

public interface DetailsAddressLocators {
    // Field-key placeholders below (e.g. "AddressTypeCode_listbox", "Address_Line1") are plugged
    // in via String.format, matching the source's partial-id-match approach.
    String DROPDOWN_TRIGGER   = "//span[contains(@aria-owns,'%s')]";
    String DROPDOWN_OPTION    = "//ul[contains(@id,'%s')]/li[string()='%s']";
    String TEXT_INPUT         = "//input[contains(@id,'%s')]";
    String TEXTAREA_INPUT     = "//textarea[contains(@id,'%s')]";

    String SAVE_BUTTON   = "//button[@class='k-button' and contains(string(), 'Save')]";
    String DELETE_BUTTON = "//button[@class='k-button' and contains(string(), 'Delete')]";

    // Field keys used with DROPDOWN_TRIGGER / DROPDOWN_OPTION / TEXT_INPUT / TEXTAREA_INPUT
    String ADDRESS_TYPE_KEY    = "AddressTypeCode_listbox";
    String STATE_PROVINCE_KEY  = "StateProvinceCode_listbox";
    String COUNTRY_KEY         = "CountryCode_listbox";
    String LINE1_KEY           = "Address_Line1";
    String LINE2_KEY           = "Address_Line2";
    String CITY_KEY            = "Address_City";
    String POSTAL_CODE_KEY     = "PostalCode";
    String COUNTY_KEY          = "County";
    String NOTES_KEY           = "Address_Note";
}
