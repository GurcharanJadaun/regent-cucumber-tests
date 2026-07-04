package com.regent.locators;

/** Locators ported from regent.pages.AddCostPopUp in the reference project. */
public interface AddCostLocators {
    String ADD_ADJUSTMENT_BUTTON   = "//button[@rem-trigger-event='enrollmentCostAdjustmentAddClick']";
    String REPACKAGE_BUTTON        = "//button[@rem-trigger-event='enrollmentCostAdjustmentRepackageClick']";
    String CLOSE_BUTTON            = "//button[@rem-trigger-event='cancelAdjustCostWizardClick']";

    String COST_ADJUST_NAME        = "#EnrollmentCostAdjustment_Description";
    String ADJUST_AMOUNT           = "#DisplayAdjustmentAmount";
    String ADJUST_NOTES            = "#EnrollmentCostAdjustment_Notes";
    String SAVE_BUTTON             = "//div[@class='buttonbar']//button[contains(string(),'Save')]";
    String SELECT_LAST_PAYMENT_PERIOD = "//div[@data-rem-id='availableEnrollmentPeriodsGrid']//tr[contains(string(), '%s')]//a";
    String COST_GRID               = "//div[@data-rem-widgetname='AdjustCostGrid']//tr[contains(string(),'%s')]";

    String APPLY_AMOUNT_DROPDOWN         = "span[aria-owns='EnrollmentCostAdjustment_ApllyAmount_listbox']";
    String APPLY_AMOUNT_OPTION           = "//ul[@id='EnrollmentCostAdjustment_ApllyAmount_listbox']/li[text()='%s']";
    String FEDERAL_COST_CATEGORY_DROPDOWN = "span[aria-owns='EnrollmentCostAdjustment_CostFederalCategoryCode_listbox']";
    String FEDERAL_COST_CATEGORY_OPTION   = "//ul[@id='EnrollmentCostAdjustment_CostFederalCategoryCode_listbox']/li[text()='%s']";

    String BILLED_BY_SCHOOL_CHECKBOX   = "#EnrollmentCostAdjustment_BilledBySchool";
    String DIRECT_COST_CHECKBOX        = "#EnrollmentCostAdjustment_DirectCost";
    String FORCE_COA_RECALC_CHECKBOX   = "#EnrollmentCostAdjustment_ForceCOARecalculation";

    String LOADING_MASK = "//div[@class='loadmask']";
}
