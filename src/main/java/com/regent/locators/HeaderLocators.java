package com.regent.locators;

/** Ported from regent.pages.Header — post-login top bar (search, logout, version info). */
public interface HeaderLocators {
    String INFO_ICON = "//span[contains(@class,'rem-icon-information')]";
    String INFO_POPUP = "//div[contains(@class,'ui-cluetip-content')]";
    String LOGOUT_LINK = "//div[@id='profile']//a[text()='Log Off']";
    String SEARCH_INPUT = "#searchText";
    String SEARCH_RESULTS = "//div[@class='k-list-scroller']//li";
    String SUBMIT_SEARCH = "//div[@id='studentSearch']//button[@type='submit']";
    String CHANGE_PASSWORD_LINK = "//a[contains(@href,'ChangePassword')]";
}
