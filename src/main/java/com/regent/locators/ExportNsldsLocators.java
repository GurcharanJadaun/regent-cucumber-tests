package com.regent.locators;

/** Ported from regent.pages.ExportNsldsPage. */
public interface ExportNsldsLocators {
    String DROPDOWN_SELECT = "span[aria-owns='%s_listbox']";
    String OPTION_ITEM     = "//ul[@aria-hidden='false']/li[contains(string(),'%s')]";

    String DAYS_BEFORE_ACADEMIC_YEAR_START   = "#IOProcess_DaysBeforeAcademicYearStart";
    String DAYS_BEFORE_SCHEDULED_DISBURSEMENT = "#IOProcess_DaysBeforeScheduledDisbursement";
    String BEGIN_MONITORING_DATE             = "#IOProcess_BeginMonitoringDate";
    String EXPORT_BUTTON                     = "//button[contains(string(),'Export')]";
}
