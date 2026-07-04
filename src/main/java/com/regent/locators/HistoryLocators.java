package com.regent.locators;

/** Ported from regent.pages.student.HistoryPage. */
public interface HistoryLocators {
    String MONITOR_BEGIN_HEADER = "//div[@id='gridNSLDSHistorySent']//th[@data-field='MonitorBeginDate']";

    String NSLDS_TABLE_DATA = "//div[@id='gridNSLDSHistorySent']/div[contains(@class,'grid-content')]//tr[contains(string(),'%s')]/td";
    String MONITOR_BEGIN_DATE = NSLDS_TABLE_DATA + "[1]";
    String REQUEST_INDICATOR = NSLDS_TABLE_DATA + "[2]";
    String DAY_7_HOLD = NSLDS_TABLE_DATA + "[3]//input[@type='checkbox']";
    String USER_DATE_TIME = NSLDS_TABLE_DATA + "[4]";
}
