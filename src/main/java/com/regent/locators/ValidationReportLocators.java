package com.regent.locators;

public interface ValidationReportLocators {
    String ERROR_RECORD_BY_TEXT = "//tr[@valign='top' and contains(string(),'%s')]//td[12]//div";
    String MAIN_MESSAGE_BY_TEXT = "//tr[@valign='top' and contains(string(),'%s')]//td[9]//div";
    String REPORT_HEADING       = "//div[contains(text(),'Report: System - SBL Validation Errors')]";
}
