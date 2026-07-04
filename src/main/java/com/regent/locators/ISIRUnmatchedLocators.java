package com.regent.locators;

/** Locators ported from regent.pages.reports.ISIRUnmatchedPage in the reference project. */
public interface ISIRUnmatchedLocators {
    String FEDERAL_AWARD_YEAR_DROPDOWN = "#rvReportViewer_ctl04_ctl07_ddValue";
    String FEDERAL_AWARD_YEAR_OPTION   = "//select[@id='rvReportViewer_ctl04_ctl07_ddValue']/option[text()='%s']";
    String REPORT_ROW                  = "//table[@cols='15']//tr[./td[contains(string(),'%s')]]";
}
