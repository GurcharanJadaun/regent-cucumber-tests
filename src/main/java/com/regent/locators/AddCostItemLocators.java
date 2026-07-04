package com.regent.locators;

public interface AddCostItemLocators {
    String COST_NAME = "#CostItem_Name";

    String COST_FREQUENCY_DROPDOWN = "span[aria-owns='CostItem_CostFrequencyId_listbox']";
    String COST_FREQUENCY_OPTION   = "//ul[@id='CostItem_CostFrequencyId_listbox']/li[text()='%s']";

    String ROUNDING_DROPDOWN = "span[aria-owns='CostItem_RoundingMethodId_listbox']";
    String ROUNDING_OPTION   = "//ul[@id='CostItem_RoundingMethodId_listbox']/li[text()='%s']";

    String COST        = "#CostItem_BaseCost";
    String RATE_START  = "#CostItem_BaseRateThreshold";

    String ROUNDING_LEVEL_DROPDOWN = "span[aria-owns='CostItem_CostRoundingLevelCode_listbox']";
    String ROUNDING_LEVEL_OPTION   = "//ul[@id='CostItem_CostRoundingLevelCode_listbox']/li[text()='%s']";

    String DIRECT_COST_CHECKBOX      = "#CostItem_DirectCost";
    String BILLED_BY_SCHOOL_CHECKBOX = "#CostItem_BilledBySchool";
    String EXTERNAL_COST_ID          = "#CostItem_InstitutionalExternalCostId";
    String ALL_STATES_CHECKBOX       = "#CostItem_AllStates";

    // Dependency status
    String DEPENDENT_CHECKBOX                = "#ds_DEPENDENT";
    String INDEPENDENT_WITH_CHECKBOX         = "#ds_INDEPENDENTWITHDEPENDENTS";
    String INDEPENDENT_WITHOUT_CHECKBOX      = "#ds_INDEPENDENTWITHOUTDEPENDENTS";
    String BLANK_CHECKBOX                    = "#ds_BLANK";

    // Housing status
    String ON_CAMPUS_CHECKBOX   = "#hs_OnCampus";
    String WITH_PARENT_CHECKBOX = "#hs_WithParent";
    String OFF_CAMPUS_CHECKBOX  = "#hs_OffCampus";

    // Residency status
    String IN_STATE_CHECKBOX         = "#rs_InState";
    String OUT_OF_STATE_CHECKBOX     = "#rs_OutOfState";
    String IN_DISTRICT_CHECKBOX      = "#rs_InDistrict";
    String OUT_OF_DISTRICT_CHECKBOX  = "#rs_OutOfDistrict";
    String UNKNOWN_CHECKBOX          = "#rs_Unknown";

    String SAVE_BUTTON   = "//button[@data-bind='click: validateCostItem']";
    String DELETE_BUTTON = "//button[@rem-trigger-event='deleteCostItemClick']";
}
