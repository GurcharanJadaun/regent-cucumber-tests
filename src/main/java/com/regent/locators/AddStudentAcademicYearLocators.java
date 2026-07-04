package com.regent.locators;

public interface AddStudentAcademicYearLocators {
    // Program / Academic Year / Federal Award Year dropdowns (Kendo UI)
    String PROGRAM_DROPDOWN         = "span[aria-owns='CourseEnrollmentProgramId_listbox']";
    String PROGRAM_OPTION           = "//ul[@id='CourseEnrollmentProgramId_listbox']/li[contains(string(),'%s')]";

    String ACADEMIC_YEAR_DROPDOWN   = "span[aria-owns='CODRebuildReviewAcademicYearFederalAwardYear_AcademicYearNumber_listbox']";
    String ACADEMIC_YEAR_OPTION     = "//ul[@id='CODRebuildReviewAcademicYearFederalAwardYear_AcademicYearNumber_listbox']/li[contains(string(),'%s')]";

    String FAY_DROPDOWN             = "span[aria-owns='CODRebuildReviewAcademicYearFederalAwardYear_FederalAwardYearId_listbox']";
    String FAY_OPTION               = "//ul[@id='CODRebuildReviewAcademicYearFederalAwardYear_FederalAwardYearId_listbox']/li[contains(string(),'%s')]";

    // Academic Year fields
    String BEGIN_DATE               = "#CODRebuildReviewAcademicYearFederalAwardYear_AwardYearBeginDate";
    String END_DATE                 = "#CODRebuildReviewAcademicYearFederalAwardYear_AwardYearEndDate";
    String HAS_ABBR_PERIOD_CHECKBOX = "#CODRebuildReviewAcademicYearFederalAwardYear_AbbreviatedPeriod";
    String OVERRIDE_AY_UNITS        = "#CODRebuildReviewAcademicYearFederalAwardYear_TotalAcademicYearUnits";
    String OVERRIDE_AY_WEEKS        = "#CODRebuildReviewAcademicYearFederalAwardYear_TotalAcademicYearWeeks";
    String MANUAL_BEGIN_DATE        = "#CODRebuildReviewAcademicYearFederalAwardYear_AwardYearManualBeginDate";
    String MANUAL_END_DATE          = "#CODRebuildReviewAcademicYearFederalAwardYear_AwardYearManualEndDate";

    String ADD_STUDENT_AY_SAVE_BUTTON      = "//div[@data-rem-widgetname='CODRebuildReviewSBMEditor']//button[@type='submit'][normalize-space()='Save']";
    String SAVE_ADD_STUDENT_AY_VALIDATION_ERROR = "//div[@data-rem-widgetname='CODRebuildReviewSBMEditor']//div[@class='validation-summary-errors']/span";
    String CANCEL_ADD_STUDENT_AY_BUTTON    = "//button[@rem-trigger-event='cancelCODRebuildReviewSBMEditorClick']";

    // Modify Student Loan Period
    String ADD_LOAN_PERIOD_BUTTON             = "#sbmLoanPeriodAdd";
    String MODIFY_STUDENT_LP_BEGIN_DATE       = "#StudentBBAYModificationLoanPeriod_StartDate";
    String MODIFY_STUDENT_LP_END_DATE         = "#StudentBBAYModificationLoanPeriod_EndDate";
    String MODIFY_STUDENT_LP_SAVE_BUTTON      = "//div[@data-rem-widgetname='CODRebuildReviewSBMLoanPeriodEditor']//button[@type='submit'][normalize-space()='Save']";

    // NSLDS Awards Grid (fund name plugged in via %s)
    String NSLDS_FUND_GRID_ROW_TOTAL_USED_CELL     = "//div[@id='nsldsAwardsGrid']//tr[contains(@class,'k-master-row') and ./td[2][text()='%s']]/td[3]";
    String NSLDS_FUND_GRID_ROW_EDIT                = "//div[@id='nsldsAwardsGrid']//tr[contains(string(),'%s')]/td/a[text()='Edit']";
    String NSLDS_FUND_GRID_OVERRIDE_INPUT          = "//div[@id='nsldsAwardsGrid']//tr[contains(string(),'%s')]/td//input[@data-bind='value:OverrideUsedAmount']";
    String NSLDS_FUND_GRID_OVERRIDE_INPUT_FALLBACK = "//div[@id='nsldsAwardsGrid']//tr[contains(string(),'%s')]/td//input[1]";
    String NSLDS_FUND_GRID_OVERRIDE_UPDATE_BUTTON  = "//div[@id='nsldsAwardsGrid']//tr[contains(string(),'%s')]/td/a[text()='Update']";
}
