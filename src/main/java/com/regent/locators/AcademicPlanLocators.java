package com.regent.locators;

public interface AcademicPlanLocators {
    // Map / packaging buttons on the Academic Plan page
    String MAP_BUTTON            = "//button[@rem-trigger-event='modifyAcademicPlanClick']";
    String REFRESH_BUTTON        = "//button[@rem-trigger-event='refreshAcademicPlanClick']";
    String NO_PLAN_MESSAGE       = "//div[@class='academicPlanPartial']/h6[contains(string(),'No Current Academic Plan')]";

    // Award grid — current AY section
    String AWARD_ROW_BY_NAME     = "//div[@class='rem-academicYear-details current']//div[@class='rem-grid-col' and contains(string(),'%s')]";

    // Packaging popup
    String BASIC_PACKAGING_BTN   = "id=btnBasicPackaging";
    String FINISH_BUTTON         = "//button[contains(string(),'Finish')]";
    String PACKAGING_BLOCKED_MSG = "//span[string()='Packaging is being blocked due to:']";

    // Award names as they appear in the grid
    String PELL_GRANT       = "Pell Grant";
    String DL_SUBSIDIZED    = "DL Subsidized";
    String DL_UNSUBSIDIZED  = "DL Unsubsidized";
}
