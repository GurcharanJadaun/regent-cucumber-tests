package com.regent.locators;

/** Locators ported from regent.pages.reports.ReportBasePage in the reference project. */
public interface ReportBaseLocators {
    String ENTERPRISE_LABEL       = "//span[normalize-space()='Enterprise']";
    String ENTERPRISE_DROPDOWN    = "#rvReportViewer_ctl04_ctl03_ddValue";
    String ENTERPRISE_OPTION      = "//select[@id='rvReportViewer_ctl04_ctl03_ddValue']/option[text()='%s']";
    String INSTITUTION_INPUT      = "#rvReportViewer_ctl04_ctl05_txtValue";
    String INSTITUTION_CHECKBOX   = "#rvReportViewer_ctl04_ctl05_divDropDown_ctl01";
    String VIEW_REPORT_BUTTON     = "#rvReportViewer_ctl04_ctl00";
    String FIND_TEXT_INPUT        = "#rvReportViewer_ctl05_ctl03_ctl00";
    String FIND_LINK              = "#rvReportViewer_ctl05_ctl03_ctl01";
    String LOADING_INDICATOR      = "//span[normalize-space()='Loading...']";
}
