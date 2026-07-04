package com.regent.locators;

public interface DetailsEmailLocators {
    // %s is a widget id fragment, e.g. "EmailTypeCode_listbox" — matches the source's contains()-based lookup
    String EMAIL_DETAILS_DROPDOWN_TRIGGER = "//span[contains(@aria-owns,'%s')]";
    String EMAIL_DETAILS_LIST_ITEM = "//ul[contains(@id,'%s')]/li[string()='%s']";
    String EMAIL_DETAIL_INPUT = "//input[contains(@id,'%s')]";
    String SAVE_BUTTON = "//button[@class='k-button' and contains(string(), 'Save')]";
    String DELETE_BUTTON = "//button[@class='k-button' and contains(string(), 'Delete')]";
}
