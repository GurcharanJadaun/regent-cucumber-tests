package com.regent.locators;

public interface AddTermLocators {
    String TERM_NAME          = "#Term_Name";
    String EXTERNAL_TERM_ID   = "#Term_ExternalId";
    String INHERIT_CPU_CHECKBOX = "#InheritAcCPUTypes";
    String TERM_START_DATE    = "#Term_StartDate";
    String TERM_END_DATE      = "#Term_EndDate";
    String TERM_CENSUS_DATE   = "#Term_CensusDate";

    String CALENDAR_TYPE_DROPDOWN = "span[aria-owns='Term_AcademicCalendarTypeCode_listbox']";
    String CALENDAR_TYPE_OPTION   = "//ul[@id='Term_AcademicCalendarTypeCode_listbox']/li[text()='%s']";

    String SAVE_BUTTON = "//button[contains(string(),'Save')]";
}
