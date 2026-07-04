package com.regent.locators;

/** Locators ported from regent.pages.reports.ISIRVerificationDRTPage and its ReportBasePage
 * superclass — this is an SSRS ReportViewer page, not a Kendo widget, so plain xpath ids are used. */
public interface ISIRVerificationDRTLocators {

    // ReportBasePage (common report-parameter controls)
    String ENTERPRISE_LABEL        = "//span[normalize-space()='Enterprise']";
    String ENTERPRISE_DROPDOWN     = "//select[@id='rvReportViewer_ctl04_ctl03_ddValue']";
    String ENTERPRISE_OPTION       = "//select[@id='rvReportViewer_ctl04_ctl03_ddValue']/option[text()='%s']";
    String INSTITUTION_INPUT       = "//input[@id='rvReportViewer_ctl04_ctl05_txtValue']";
    String INSTITUTION_CHECKBOX    = "//input[@id='rvReportViewer_ctl04_ctl05_divDropDown_ctl01']";
    String VIEW_REPORT_BUTTON      = "//input[@id='rvReportViewer_ctl04_ctl00']";
    String FIND_TEXT_INPUT         = "//input[@id='rvReportViewer_ctl05_ctl03_ctl00']";
    String FIND_LINK               = "//a[@id='rvReportViewer_ctl05_ctl03_ctl01']";
    String LOADING_INDICATOR       = "//span[normalize-space()='Loading...']";

    // Campus
    String CAMPUS_DROPDOWN   = "//input[@id='rvReportViewer_ctl04_ctl07_txtValue']";
    String CAMPUS_OPTION_CB  = "//label[contains(string(),'%s')]/preceding-sibling::input";

    // Federal Award Year (native <select>)
    String FEDERAL_AWARD_YEAR_SELECT = "#rvReportViewer_ctl04_ctl11_ddValue";

    // Selected For Verification
    String SELECTED_FOR_VERIFICATION_DROPDOWN = "//input[@id='rvReportViewer_ctl04_ctl19_txtValue']";
    String SELECTED_FOR_VERIFICATION_ALL_CB   = "//input[@id='rvReportViewer_ctl04_ctl19_divDropDown_ctl00']";
    String SELECTED_FOR_VERIFICATION_Y_CB     = "//input[@id='rvReportViewer_ctl04_ctl19_divDropDown_ctl01']";
    String SELECTED_FOR_VERIFICATION_N_CB     = "//input[@id='rvReportViewer_ctl04_ctl19_divDropDown_ctl02']";

    // Verification Tracking Group
    String VERIFICATION_TRACKING_GROUP_DROPDOWN = "//input[@id='rvReportViewer_ctl04_ctl21_txtValue']";
    String GROUP_SELECT_ALL_CB = "//input[@id='rvReportViewer_ctl04_ctl21_divDropDown_ctl00']";
    String GROUP_V1_CB         = "//input[@id='rvReportViewer_ctl04_ctl21_divDropDown_ctl01']";
    String GROUP_V2_CB         = "//input[@id='rvReportViewer_ctl04_ctl21_divDropDown_ctl02']";
    String GROUP_V3_CB         = "//input[@id='rvReportViewer_ctl04_ctl21_divDropDown_ctl03']";
    String GROUP_V4_CB         = "//input[@id='rvReportViewer_ctl04_ctl21_divDropDown_ctl04']";
    String GROUP_V5_CB         = "//input[@id='rvReportViewer_ctl04_ctl21_divDropDown_ctl05']";
    String GROUP_V6_CB         = "//input[@id='rvReportViewer_ctl04_ctl21_divDropDown_ctl06']";
    String GROUP_NONE_CB       = "//input[@id='rvReportViewer_ctl04_ctl21_divDropDown_ctl07']";

    String REPORT_ROW = "//table[@cols='21']//tr[./td[contains(string(),'%s')]]";
}
