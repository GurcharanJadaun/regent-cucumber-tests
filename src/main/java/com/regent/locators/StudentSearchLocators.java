package com.regent.locators;

/** Ported from regent.pages.StudentSearchPage. */
public interface StudentSearchLocators {
    String USER_ITEM        = "//div[@id='StudentSearch']//table[@class='k-focusable k-selectable' or @role='grid']//tbody//tr";
    String USER_ITEM_COLUMN = "//div[@id='StudentSearch']//table[@class='k-focusable k-selectable' or @role='grid']//tbody//tr/td[%s]";

    String FILTER               = "//div[@id='StudentSearch']//th[contains(string(),'%s')]/a[contains(@class,'k-grid-filter')]";
    String CONTAINS_FILTER_INPUT = "//form[contains(@style,'display: block')]//input[@class='k-textbox'][1]";
    String SUBMIT_FILTER_BUTTON  = "//form[contains(@style,'display: block')]//button[@type='submit']";
    String RESET_FILTER_BUTTON   = "//form[contains(@style,'display: block')]//button[@type='reset']";
    String NUMBER_OF_RESULTS_TEXT = "//div[@id='StudentSearch']//div[@data-rem-id='grid']//*[@class='k-pager-info k-label']";
    String SORT_COLUMN           = "//div[@id='StudentSearch']//th[contains(string(),'%s')]/a[contains(@class,'k-link')]";
    String PAGINATION            = "//div[@class='k-content k-state-active'][contains(@style,'block')]//div[@id='StudentSearch']//ul[@class='k-pager-numbers k-reset']//li";
    String TYPE_OF_SEARCH        = "//form[contains(@style,'display: block')]//span[@class='k-widget k-dropdown k-header'][1]";
    String SEARCH_OPTIONS        = "//ul[@aria-hidden='false']/li[contains(@class,'k-item')][text()='%s']";
    String STUDENT_SEARCH_GRID_CELLS = "//div[@data-rem-widgetname='StudentGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td";
}
