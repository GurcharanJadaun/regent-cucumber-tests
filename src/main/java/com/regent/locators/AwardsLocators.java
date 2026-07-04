package com.regent.locators;

public interface AwardsLocators {
    String ADD_AWARD_BUTTON      = "//button[@rem-trigger-event='addAwardClick']";
    String ADJUST_COST_BUTTON    = "//button[@rem-trigger-event='adjustCostClick']";
    String ADJUST_RESOURCE_BUTTON = "//button[@rem-trigger-event='adjustResourceClick']";
    String CALCULATE_SAP_BUTTON  = "//button[@rem-trigger-event='calculateSAPClick']";
    String PERFORM_R2T4_BUTTON   = "//button[@rem-trigger-event='performR2T4Click']";
    String REFRESH_BUTTON        = "//button[@rem-trigger-event='refreshAwards']";

    // Award summary grid (dgvPeriods)
    String AWARD_SUMMARY_GRID_TITLE      = "//div[@data-rem-id='dgvPeriods']/div[contains(@class,'k-grid-header')]//th[@data-title='%s']";
    String AWARD_SUMMARY_GRID_ROW_CELLS  = "//div[@data-rem-id='dgvPeriods']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[@role='gridcell']";
    String AWARD_SUMMARY_GRID_SAP_PP_BASED = "//div[@data-rem-id='dgvPeriods']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[@role='gridcell'][13]//span[contains(@title,'PP-based')]";
    String AWARD_SUMMARY_GRID_SAP_AY_BASED = "//div[@data-rem-id='dgvPeriods']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[@role='gridcell'][13]//span[contains(@title,'AY-based')]";
    String AWARD_SUMMARY_GRID_FILTER     = "//div[@data-rem-id='dgvPeriods']/div[contains(@class,'k-grid-header')]//th[@data-title='%s']/a[@title='Filter']";
    String AWARD_FILTER_INPUT            = "//form[@title='Show items with value that:' and @aria-hidden='false']//input[@title='Value']";
    String AWARD_FILTER_BUTTON           = "//form[@title='Show items with value that:' and @aria-hidden='false']//button[text()='Filter']";
    String PAYMENT_PERIOD_IN_LIST        = "//div[@data-rem-id='dgvPeriods']//tr[contains(string(),'%s')]";
    String MODULAR_ICON_PAYMENT_PERIOD   = "//div[@data-rem-id='dgvPeriods']//td[@role='gridcell' and normalize-space()='%s']//span[@class='k-sprite icon rem-customicon-module']";
    String LAST_PERIOD_IN_LIST_YEAR      = "//div[@data-rem-id='dgvPeriods']//tr[contains(string(),'%s')]/td[5]";
    String COA_FOR_PERIOD                = "//div[@data-rem-id='dgvPeriods']//tr[contains(string(),'%s')]/td[13]";

    // Award/COA summary collapsible headers
    String AWARD_SUMMARY_TOGGLE   = "//div[@data-rem-type='collapsable-summary' and contains(string(),'Awards')]//span[@class='k-icon k-plus']";
    String ALTERNATIVE_LOAN_SUMMARY_AWARD = "//div[@class='rem-grid-paymentPeriodIndex details-label' and contains(string(),'Alternative Loan:')]";
    String COA_FOR_PP              = "//tr[contains(string(),'%s')]//td[14]";
    String TOTAL_AWARDS_FOR_PP     = "//tr[contains(string(),'%s')]//td[20]";
    String COA_SUMMARY_TOGGLE      = "//div[@data-rem-type='collapsable-summary' and contains(string(),'Cost of Attendance')]//span[@class='k-icon k-plus']";
    String TUITION_AND_FEES_FOR_PP = "//div[@data-rem-type='collapsable-summary' and contains(string(),'Tuition')]/div[@class='rem-grid-col'][1]";
    String TUITION_AND_FEES_TOTAL  = "//div[@class='rem-enrollment-row' and contains(string(),'Tuition and Fees')]/div[@class='rem-grid-col'][last()]";

    // Analyze Loan Period (Kendo dropdown, opened repeatedly across a scenario)
    String ANALYZE_LOAN_PERIOD_SELECT   = "span[aria-owns='LoanPeriodSelectList_listbox']";
    String LOAN_PERIOD_OPTION           = "#LoanPeriodSelectList_listbox li:visible:text-is('%s')";
    String LOAN_PERIOD_OPTION_CONTAINS  = "//ul[@id='LoanPeriodSelectList_listbox']/li[@role='option' and contains(string(),'%s')]";
    String LOAN_PERIOD_OPTION_SELECTED  = "//ul[@id='LoanPeriodSelectList_listbox']/li[@role='option' and @aria-selected='true']";

    // Awards details / modify
    String AWARDS_DETAILS_TOGGLE  = "//div[@class='rem-grid-col' and contains(string(),'Awards')]";
    String MODIFY_AWARD_LINK      = "(//div[@class='rem-enrollment-row rem-enrollment-row-subhead' and contains(string(),'%s')]//a[@data-rem-id='modify-award'])[1]";
    String AWARD_LINK_AMOUNTS     = "//div[@class='rem-enrollment-row rem-enrollment-row-subhead' and contains(string(),'%s')]/div[@class='rem-grid-col']/a[@data-rem-id='hlDisbDetails']";
    String AWARD_LINKS_TOTAL_AMOUNT = "//div[@class='rem-enrollment-row rem-enrollment-row-subhead' and contains(string(),'%s')]/div[@class='rem-grid-col'][last()]";

    // Compare Awards
    String COMPARE_AWARDS_AWARD_SUBHEADING       = "//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row rem-enrollment-row-subhead']";
    String COMPARE_AWARDS_AWARD_SUBHEADING_LABEL = "(//div[@data-rem-widgetname='CompareAwardsView'])[last()]//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row rem-enrollment-row-subhead']";
    String COMPARE_AWARDS_AWARD_DETAILS_ROW       = "//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row rem-enrollment-row-subhead' and contains(string(),'%s')]/following-sibling::div[contains(string(),'%s')]/div[@class='rem-grid-col']";
    String COMPARE_AWARDS_AWARD_DETAILS_ROW2      = "//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row rem-enrollment-row-subhead' and contains(string(),'%s')]/following-sibling::div[@data-rem-type='collapsable-details']/div[contains(string(),'%s')]/div[@class='rem-grid-col']";
    String COMPARE_AWARDS_AWARD_SUBHEADING_ROW    = "//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row rem-enrollment-row-subhead' and contains(string(),'%s')]";
    String COMPARE_AWARDS_AWARD_ROW_EXPAND_COLLAPSE = "//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row rem-enrollment-row-subhead' and contains(string(),'%s')]//span[@class='k-icon k-%s']";
    String AWARD_DETAIL_COLUMNS_DATA = "//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row' and contains(string(),'%s')]['%s']/div[@class='rem-grid-col']";
    String AWARD_DETAIL_ROWS_DATA    = "//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row' and contains(string(),'%s')]";
    String AWARD_DETAIL_ROW_COLUMNS  = "//div[@class='rem-enrollment-awards' and contains(string(),'Awards')]//div[@class='rem-enrollment-row' and contains(string(),'%s')][%s]/div[@class='rem-grid-col']";

    String DETAIL_ROW_COLUMNS = "//div[@data-rem-widgetname='CompareAwardsView']//div[@class='rem-enrollment-row' and contains(string(),'%s')]/div[@class='rem-grid-col']";
    String DETAIL_CURRENT_SAP_STATUS_ICON = "//div[@data-rem-widgetname='CompareAwardsView']//div[@class='rem-enrollment-row' and contains(string(),'Current SAP Status')]/div[@class='rem-grid-col'][%s]/span[contains(@title,'%s-based')]";

    String COMPARE_AWARDS_HEADER = "//div[@data-rem-widgetname='CompareAwardsView']//div[@class='rem-enrollment-awards' and contains(string(),'%s')]/div[contains(@class,'rem-enrollment-awards-header')]";
    String COMPARE_AWARDS_SECTION_HEADERS = COMPARE_AWARDS_HEADER;
    String COMPARE_AWARDS_HEADER_ROW_COLUMNS = COMPARE_AWARDS_HEADER + "/div[@class='rem-grid-col']";
    String COST_OF_ATTENDANCE_EXPANDABLE_SUBSECTIONS = "//div[@data-rem-type='collapsable-details' and contains(string(),'Tuition')]//div[@class='rem-enrollment-row']//span[contains(@class,'k-plus')]";
    String COMPARE_AWARDS_ROW_COLUMNS = "//div[@data-rem-widgetname='CompareAwardsView']//div[@class='rem-enrollment-awards' and contains(string(),'%s')]/div[@class='rem-grid-col']";

    String SELECTED_ENROLLMENT_PERIODS = "//div[@data-rem-widgetname='CompareAwardsView']//div[@class='rem-enrollment-row-title' and contains(string(),'Selected Enrollment')]/div[@class='rem-grid-col']";
    String SELECTED_ENROLLMENT_PERIODS_AW_ICON  = SELECTED_ENROLLMENT_PERIODS + "/a[contains(@title,'auto-withdrawn')]/span[contains(@class,'rem-customicon-aw')]";
    String SELECTED_ENROLLMENT_PERIODS_MOD_ICON = SELECTED_ENROLLMENT_PERIODS + "/a[contains(@title,'modular courses')]/span[contains(@class,'rem-customicon-module')]";

    String RESOURCES_EXPAND_SECTION = "//div[@data-rem-widgetname='CompareAwardsView']//div[@class='rem-enrollment-awards']/div[contains(@class,'rem-enrollment-awards-header')]/div[contains(string(),'Resources')]";

    String AWARDS_NAMES = "//div[@data-rem-type='collapsable-summary' and contains(string(),'Awards')]/following-sibling::div[@data-rem-type='collapsable-details']//div[@class='rem-enrollment-row rem-enrollment-row-subhead']/div[@class='rem-grid-paymentPeriodIndex details-label']";

    // Add Cost Adjustment (SEI tests)
    String CANCEL_BUTTON            = "//button[contains(.,'Cancel')]";
    String ADD_ADJUSTMENT_BUTTON    = "//button[.='Add Adjustment']";
    String COST_ADJUSTMENT_NAME     = "//input[@id='EnrollmentCostAdjustment_Description']";
    String ADJUSTMENT_AMOUNT        = "//input[@id='DisplayAdjustmentAmount']";
    String ADJUSTMENT_NOTES         = "//textarea[@id='EnrollmentCostAdjustment_Notes']";
    String ADD_BUTTON_ROW2          = "//div[@class='r-clear-float']//tr[2]//a[.='Add']";
    String ADD_BUTTON_ROW1          = "//div[@class='r-clear-float']//tr[1]//a[.='Add']";
    String SAVE_BUTTON              = "//button[contains(.,'Save')]";
    String CLOSE_BUTTON             = "//button[.='Close']";
}
