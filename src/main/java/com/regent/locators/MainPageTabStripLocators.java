package com.regent.locators;

/** Locators ported from regent.pages.MainPageTabStrip. */
public interface MainPageTabStripLocators {

    String DASHBOARD_TAB       = "//span[@class='k-link' and text()='Dashboard']";
    String STUDENT_TAB         = "//span[@class='k-link' and contains(@data-content-url,'tab=Summary') and contains(string(),'%s')]";
    String PROCESS_LOG_TAB     = "//span[@class='k-link' and contains(@data-content-url,'Process/ListPartial')]";
    String STUDENT_TAB_CLOSE   = "//span[@class='k-link' and contains(@data-content-url,'tab=Summary')]/span[contains(@class,'close')]";
    String TAB_CLOSE_BY_NAME   = "//ul[contains(@class,'hiddenTabStrip')]/li/span[@class='k-link' and contains(string(),'%s')]/span[contains(@class,'close')]";
}
