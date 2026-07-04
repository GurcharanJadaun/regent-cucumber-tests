package com.regent.locators;

/** Generic Kendo grid column-filter popup, ported from regent.pages.Filter. */
public interface FilterLocators {
    String FILTER_BUTTON  = "(//div[@class='k-content k-state-active'][contains(@style,'block')]//th[string()='%s']/a[contains(@class,'filter')])[last()]";
    String FILTER_INPUT   = "//form[@data-role='popup' and contains(@style,'block')]//input[@title][not(contains(@style,'none'))]";
    String FILTER_SUBMIT  = "//form[@data-role='popup' and contains(@style,'block')]//button[@type='submit' and string()='Filter']";
    String FILTER_OPTIONS_BUTTON = "//form[@data-role='popup' and contains(@style,'block')]//span[@role='listbox' and @class='k-widget k-dropdown k-header']";
    String FILTER_OPTIONS_SELECT = "//form[contains(@style,'display: block;')]//div[@class='k-animation-container' and contains(@style,'display: block;')]//li[@tabindex='-1' and contains(text(),'%s')]";
}
