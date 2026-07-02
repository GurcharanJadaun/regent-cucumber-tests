package com.regent.locators;

public interface AcademicPlanLocators {
    // Map / packaging buttons on the Academic Plan page
    String MAP_BUTTON            = "//button[@rem-trigger-event='modifyAcademicPlanClick']";
    String REFRESH_BUTTON        = "//button[@rem-trigger-event='refreshAcademicPlanClick']";
    String NO_PLAN_MESSAGE       = "//div[@class='academicPlanPartial']/h6[contains(string(),'No Current Academic Plan')]";

    // Award grid — current AY section. Each award is its own row, not a grid cell.
    String AWARD_ROW_BY_NAME     = "//div[contains(@class,'rem-academicYear-details') and contains(@class,'current')]//div[contains(@class,'rem-enrollment-row') and contains(@class,'rem-award-status-valid') and contains(string(),'%s')]";

    // Packaging popup
    String BASIC_PACKAGING_BTN   = "id=btnBasicPackaging";
    String FINISH_BUTTON         = "//button[contains(string(),'Finish')]";
    String PACKAGING_BLOCKED_MSG = "//span[string()='Packaging is being blocked due to:']";

    // Award names as they appear in the grid
    String PELL_GRANT       = "Pell Grant";
    String DL_SUBSIDIZED    = "Direct Subsidized Loan";
    String DL_UNSUBSIDIZED  = "Direct Unsubsidized Loan";
}
