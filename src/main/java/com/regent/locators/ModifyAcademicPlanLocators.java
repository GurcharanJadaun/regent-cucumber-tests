package com.regent.locators;

/** Locators ported from regent.pages.ModifyAcademicPlanPopUp in the reference project. */
public interface ModifyAcademicPlanLocators {

    // Log level / capture context — repeatedly opened across a scenario's multiple packaging
    // runs, so filter by :visible per the Kendo stale-duplicate pattern.
    String OVERRIDE_LOG_LEVEL_DROPDOWN = "span[aria-owns='LogTraceLevel_listbox']:visible";
    String LOG_TRACE_LEVEL_VERBOSE     = "#LogTraceLevel_listbox li:visible:text-is('Verbose')";

    String BASIC_PACKAGING_BUTTON = "#btnBasicPackaging";
    String FINISH_BUTTON          = "//button[contains(string(),'Finish')]";
    String PACKAGING_ERROR_MESSAGE = "//span[string()='Packaging is being blocked due to:']";

    String VALIDATION_ERRORS_XPATH        = "//div[@class='validation-summary-errors' and contains(string(),'%s')]";
    String STEP1_BLOCKING_ERROR_MESSAGE   = VALIDATION_ERRORS_XPATH + "//li[contains(string(),'%s')]";
    String VALIDATION_ERRORS              = "//div[@class='validation-summary-errors']//li";

    String REFRESH_WINDOW_BUTTON = "//div[@class='k-window-actions']//a[@aria-label='Refresh']";

    // Anticipated Enrollment Level — re-opened per packaging run, filter by :visible.
    String ANTICIPATED_ENROLLMENT_LEVEL_SELECT = "span[aria-owns='Step1_StudentModifyAcademicPlanWizard_AnticipatedEnrollmentsLevel_listbox']:visible";
    String ANTICIPATED_ENROLLMENT_LEVEL_OPTION = "#Step1_StudentModifyAcademicPlanWizard_AnticipatedEnrollmentsLevel_listbox li:visible:text-is('%s')";

    String PACKAGING_PHILOSOPHY_TYPE_SELECT = "span[aria-owns='Step1_StudentModifyAcademicPlanWizard_PackagingPhilosophyOverrideTypeCode_listbox']:visible";
    String PACKAGING_PHILOSOPHY_TYPE_OPTION = "#Step1_StudentModifyAcademicPlanWizard_PackagingPhilosophyOverrideTypeCode_listbox li:visible:text-is('%s')";

    String PLAN_PACKAGING_PHILOSOPHY_SELECT = "span[aria-owns='Step1_StudentModifyAcademicPlanWizard_PackagingPhilosophyOverride_listbox']:visible";
    String PLAN_PACKAGING_PHILOSOPHY_OPTION = "#Step1_StudentModifyAcademicPlanWizard_PackagingPhilosophyOverride_listbox li:visible:text-is('%s')";

    String CAPTURE_CONTEXT_CHECKBOX       = "#CaptureContext";
    String DOWNLOAD_ANALYSIS_CONTEXT_BUTTON = "//button[@rem-trigger-event='downloadContextClick']";
    String CANCEL_BUTTON                  = "//button[@rem-trigger-event='cancelModifyAcademicPlanWizardClick']";
    String LOADING_MASK                   = "//div[@class='loadmask']";

    // Advanced Packaging
    String ADVANCED_PACKAGING_BUTTON = "#btnAdvancedPackaging";

    String OVERRIDE_FAY_DROPDOWN = "//div[@class='rem-enrollment-row' and ./div[@class='rem-grid-col' and contains(string(),'%s')]]//span[@aria-owns='Step2_CourseEnrollments_2__ManualFederalAwardYearId_listbox']";
    String OVERRIDE_FAY_OPTION   = "#Step2_CourseEnrollments_2__ManualFederalAwardYearId_listbox li:visible:text-is('%s')";

    String ROW_UNITS_INPUT_1 = "(//div[@class='rem-enrollment-row' and ./div[@class='rem-grid-col' and contains(string(),'%s')]]/div[6]//input)[1]";
    String ROW_UNITS_INPUT_2 = "//div[@class='rem-enrollment-row' and ./div[@class='rem-grid-col' and contains(string(),'%s')]]/div[6]//input[contains(@name,'Step2.CourseEnrollments')]";

    String STEP2_CONTINUE_BUTTON = "#btnContinue";

    String ISIR_INFO_GRID_ROW_CELLS = "//table[@id='isirGrid']//tr[contains(string(),'%s')]/td";
}
