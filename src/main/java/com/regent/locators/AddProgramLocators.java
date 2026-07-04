package com.regent.locators;

public interface AddProgramLocators {
    String PROGRAM_NAME          = "#Program_Name";
    String PROGRAM_TYPE_DROPDOWN = "span[aria-owns='Program_ProgramTypeCode_listbox']";
    String PROGRAM_TYPE_OPTION   = "//ul[@id='Program_ProgramTypeCode_listbox']/li[text()='%s']";

    String CIP_CODE              = "#Program_CIPCode";
    String PROGRAM_DISPLAY_NAME  = "#Program_DisplayName";
    String PROGRAM_EXTERNAL_ID   = "#Program_ExternalId";

    String NUMBER_OF_YEARS_DROPDOWN = "span[aria-owns='Program_NumberOfYears_listbox']";
    String NUMBER_OF_YEARS_OPTION   = "//ul[@id='Program_NumberOfYears_listbox']/li[text()='%s']";

    String NUMBER_OF_UNITS = "#Program_NumberOfUnits";
    String NUMBER_OF_WEEKS = "#Program_NumberOfWeeks";

    String CPU_TYPES_CHECKBOX = "#InheritAcCPUTypes";

    String ACADEMIC_CALENDAR_TYPE_DROPDOWN = "span[aria-owns='Program_AcademicCalendarTypeCode_listbox']";
    String ACADEMIC_CALENDAR_TYPE_OPTION   = "//ul[@id='Program_AcademicCalendarTypeCode_listbox']/li[contains(text(), 'SAY')]";

    String PELL_FORMULA_DROPDOWN = "span[aria-owns='Program_PellFormulaTypeCode_listbox']";
    String PELL_FORMULA_OPTION   = "//ul[@id='Program_PellFormulaTypeCode_listbox']/li[contains(text(), '1')]";

    String PELL_COA        = "#Program_PellCOA";
    String UNITS_FOR_YEAR2 = "#Program_UnitsForYear2";
    String UNITS_FOR_YEAR3 = "#Program_UnitsForYear3";
    String UNITS_FOR_YEAR4 = "#Program_UnitsForYear4";
    String UNITS_FOR_YEAR5 = "#Program_UnitsForYear5";

    String SAVE_BUTTON = "//button[text()='Save']";

    String DEFAULT_ENROLL_LEVEL_DROPDOWN = "span[aria-owns='Program_DefaultEnrollmentLevelSetupId_listbox']";
    String DEFAULT_ENROLL_LEVEL_OPTION   = "//ul[@id='Program_DefaultEnrollmentLevelSetupId_listbox']/li[contains(text(), '%s')]";
}
