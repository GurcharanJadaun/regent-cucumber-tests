package com.regent.locators;

/** Ported from regent.pages.PortalFinancialAidPage. */
public interface PortalFinancialAidLocators {
    String EDIT_BUTTON = "//div[contains(@data-rem-widgetname, 'InstitutionPortalFinancialAid')]//button[contains(@class,'edit_button')]";
    String SAVE_BUTTON = "//div[contains(@data-rem-widgetname, 'InstitutionPortalFinancialAid')]//button[@type='submit']";

    String DISPLAY_ON_DASHBOARD_CHECKBOX  = "#FinancialAid_DisplayFinancialAidOnDashboard";
    String DISPLAY_COST_OF_ATTENDANCE_CHECKBOX = "#FinancialAid_DisplayFullCostOfAttendance";
    String DISPLAY_DIRECT_COSTS_CHECKBOX  = "#FinancialAid_DisplayDirectCostsSummary";
    String INSTRUCTIONAL_MESSAGE_HEADER   = "#FinancialAid_InstructionalMessageHeader";

    // Generic Kendo dropdown keyed by listbox id
    String DROPDOWN_TRIGGER = "span[aria-owns='%s']";
    String DROPDOWN_OPTION  = "#%s li:visible:has-text('%s')";

    String FA_DETAILED_FIELD_VALUE_BY_LABEL = "//div[@data-rem-widgetname='InstitutionPortalFinancialAidView']//div[@class='row' and contains(string(),'%s')]//div[@class='details-field']";

    String DISPLAY_DECLINED_AWARDS_LISTBOX  = "FinancialAid_DisplayDeclinedAwards_listbox";
    String DISPLAY_CANCELLED_AWARDS_LISTBOX = "FinancialAid_DisplayCancelledAwards_listbox";
    String DISPLAY_ESTIMATED_AWARDS_LISTBOX = "FinancialAid_DisplayEstimatedAwards_listbox";
    String HIDE_LOANS_WHEN_OVERLAP_EXISTS_LISTBOX = "FinancialAid_HideLoansWhenOverlapExists_listbox";
}
