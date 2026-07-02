package com.regent.locators;

public interface LeftNavLocators {
    // Top-level panels: only match the collapsed state (exact class), same as the
    // reference smoke-suite-automation LeftSideMenu — used for click-to-expand only.
    String PROCESSES_PANEL  = "//li[@class='k-item k-state-default']/span[contains(text(),'Processes')]";
    String STUDENTS_PANEL   = "//li[@class='k-item k-state-default k-first']/span[contains(text(),'Students')]";

    // Stable accordion container present regardless of Kendo expand/collapse state
    String NAV_ACCORDION    = "#westAccordion";

    // Generic menu item link (use with String.format for the link text)
    String MENU_ITEM_LINK   = "//a[string()='%s']";

    // Student profile sub-tabs
    String ACADEMIC_PLAN_TAB = "//li[@data-rem-id='AcademicPlan']/span[@class='k-link']";
    String DETAILS_TAB        = "//li[@data-rem-id='Details']/span[@class='k-link']";
}
