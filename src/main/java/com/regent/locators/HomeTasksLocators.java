package com.regent.locators;

/** Locators ported from regent.pages.HomeTasksPage in the reference project. */
public interface HomeTasksLocators {

    // Open Tasks — bulk-change controls
    String REFRESH_OPEN_TASK_GRID_BUTTON = "//button[@rem-trigger-event='refreshOpenTaskGridClick']";

    // Kendo dropdowns: stable aria-owns trigger + listbox-id-scoped options (see class comment
    // pattern established in AddAwardLocators/DocumentsLocators — avoids fragile positional xpath).
    String CHANGE_TYPE_DROPDOWN   = "span[aria-owns='cb-type-box_listbox']";
    String CHANGE_TYPE_OPTION     = "//ul[@id='cb-type-box_listbox']/li[contains(string(),'%s')]";
    String STATUS_DROPDOWN        = "span[aria-owns='cb-stat-box_listbox']";
    String STATUS_OPTION          = "//ul[@id='cb-stat-box_listbox']/li[contains(string(),'%s')]";
    String QUEUE_DROPDOWN         = "span[aria-owns='cb-queu-box_listbox']";
    String QUEUE_OPTION           = "//ul[@id='cb-queu-box_listbox']/li[contains(string(),'%s')]";
    String PRIORITY_DROPDOWN      = "span[aria-owns='cb-prio-box_listbox']";
    String PRIORITY_OPTION        = "//ul[@id='cb-prio-box_listbox']/li[contains(string(),'%s')]";
    String USER_DROPDOWN          = "span[aria-owns='cb-user-box_listbox']";
    String USER_OPTION            = "//ul[@id='cb-user-box_listbox']/li[contains(string(),'%s')]";

    String SUBMIT_OPEN_BUTTON     = "//div[@data-rem-id='openTasksSection']/button[contains(string(),'Submit')]";
    String TASK_GRID_ROW_CELL     = "//div[@data-rem-id='opentTaskGrid']/div[contains(@class,'k-grid-content')]/table//tr[%s]/td";
    String TASK_GRID_CELL         = "//div[@data-rem-id='opentTaskGrid']/div[contains(@class,'k-grid-content')]/table//tr[contains(string(),'%s')]/td";
    String TASK_ROW_CHECKBOX      = "((//div[@data-rem-id='opentTaskGrid']//table)[2]//tr[contains(string(),'%s')]/td/input)[1]";
    String DUE_DATE_INPUT         = "//div[@data-rem-id='openTasksSection']//div[@data-rem-id='tb-date-box']//input";

    String OPEN_TASK_TAB          = "//li[contains(string(),'Open Tasks')]";
    String OPEN_TASK_PAGER_INFO   = "//div[@data-rem-id='opentTaskGrid']//span[@class='k-pager-info k-label']";
    String OPEN_TASKS_ASSIGNED_TO_FILTER = "//div[@data-rem-id='opentTaskGrid']//th[@data-field='AssignedToUserName']/a[contains(@class,'k-grid-filter')]";
    String FILTER_INPUT           = "//form[@data-role='popup' and contains(@style,'block')]//input[@title][not(contains(@style,'none'))]";
    String FILTER_SUBMIT          = "//form[@data-role='popup' and contains(@style,'block')]//button[@type='submit' and string()='Filter']";

    // Completed Tasks
    String COMPLETED_TASK_TAB               = "//li[contains(string(),'Completed Tasks')]";
    String REFRESH_COMPLETED_TASK_GRID_BUTTON = "//button[@rem-trigger-event='refreshCompletedTaskGridClick']";
    String SUBMIT_COMPLETED_BUTTON          = "//button[@rem-trigger-event='bulkCompletedTasksUpdate']";
    String COMPLETED_TASK_ROW_CHECKBOX      = "((//div[@data-rem-id='completedTaskGrid']//table)[2]//tr[contains(string(),'%s')]/td/input)[1]";
    String COMPLETED_TASK_GRID_CELL         = "//div[@data-rem-id='completedTaskGrid']/div[contains(@class,'k-grid-content')]/table//tr[contains(string(),'%s')]/td";
    String COMPLETED_CHANGE_TYPE_DROPDOWN   = "//div[@data-rem-id='completedTasksSection']//span[@aria-owns='cb-type-box_listbox']";
    String COMPLETED_TYPE_OPTION            = "(//ul/li[@role='option' and contains(string(),'%s')])[2]";
    String COMPLETED_STATUS_DROPDOWN        = "//div[@data-rem-id='completedTasksSection']//div[@data-rem-id='cb-stat-box']/span[@role='listbox']";
    String COMPLETED_STATUS_OPTION          = "(//ul/li[@role='option' and contains(string(),'%s')])[2]";
}
