package com.regent.locators;

/** Locators ported from regent.pages.student.SummaryPage. */
public interface SummaryLocators {

    String STUDENT_FULL_NAME       = "//div[@class='twelve columns']/h3";
    String STUDENT_INFO_FROM_MAIN_PAGE = "//div[contains(string(),'%s')]/div[@class='details-field']";
    String STUDENT_EXTERNAL_ID     = "//div[3][contains(string(),'ID')]/div[@class='details-field']";
    String RECENT_ACTIVITY_ITEM    = "//div[@data-rem-id='StudentRecentActivitiesGrid']//strong[contains(text(),'%s')]";
    String RECENT_ACTIVITY_ROW     = "//div[@data-rem-id='StudentRecentActivitiesGrid']//tr/td[@role='gridcell' and contains(string(),'%s')]";
    String SUMMARY_ITEM            = "//div[@data-rem-widgetname='StudentSummaryView']//div[@class='row' and contains(string(),'%s')]/div[@class='details-field']";
    String SSN_HINT_LINK           = "//div[@data-rem-widgetname='StudentSummaryView']//a[@class='rem-ssn-hint']";
    String FULL_SSN_CLUETIP        = "//div[contains(@class,'ui-cluetip-content')]/div[contains(string(),'SSN:')]";
    String SAP_STATUS              = "//div[@class='details-label' and contains(string(),'%s')]/following-sibling::div[@class='details-field']/div";
}
