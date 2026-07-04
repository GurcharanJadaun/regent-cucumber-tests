package com.regent.locators;

/** Locators ported from regent.pages.student.TasksPage in the reference project. */
public interface TasksLocators {

    String SELECT_ALL_TASKS_CHECKBOX = "//div[@data-rem-id='opentTaskGrid']//input[@data-rem-id='chSelAll']";

    // Open-task change-type / change-status Kendo dropdowns
    String CHANGE_TYPE_DROPDOWN = "span[aria-owns='cb-type-box_listbox']:visible";
    String TYPE_OPTION          = "//ul/li[@role='option' and contains(string(),'%s')]";
    String STATUS_DROPDOWN      = "//div[@data-rem-id='cb-stat-box']/span[@role='listbox']";
    String STATUS_OPTION        = "//ul/li[@role='option' and contains(string(),'%s')]";

    // Completed-task change-type / change-status Kendo dropdowns (same staleness risk as above,
    // since these can be opened repeatedly within one scenario — see DocumentsLocators comment)
    String COMPLETED_CHANGE_TYPE_DROPDOWN = "//div[@data-rem-id='completedTasksSection']//span[@aria-owns='cb-type-box_listbox']";
    String COMPLETED_TYPE_OPTION          = "//div[@class='k-animation-container']//ul/li[@role='option' and contains(string(),'%s')]";
    String COMPLETED_STATUS_DROPDOWN      = "//div[@data-rem-id='completedTasksSection']//div[@data-rem-id='cb-stat-box']/span[@role='listbox']";
    String COMPLETED_STATUS_OPTION        = "//div[@class='k-animation-container']//ul/li[@role='option' and contains(string(),'%s')]";

    String SUBMIT_OPEN_BUTTON      = "//div[@data-rem-id='openTasksSection']/button[contains(string(),'Submit')]";
    String SUBMIT_COMPLETED_BUTTON = "//div[@data-rem-id='completedTasksSection']/button[contains(string(),'Submit')]";

    String TASK_COLUMN         = "//div[@data-rem-id='opentTaskGrid']//thead//th[@data-title='%s']";
    String TASK_ID_COLUMN_SORT = "//div[@data-rem-id='opentTaskGrid']//thead//th[@data-title='ID']/a[2]";
    String TASK_ROWS           = "//div[@data-rem-id='opentTaskGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr";
    String TASK_NAME_ITEM      = "//div[@data-rem-id='opentTaskGrid']/div[contains(@class,'k-grid-content')]/table//tr/td[position()=3 and contains(string(),'%s')]";
    String OPEN_TASK_GRID_CELL = "//div[@data-rem-id='opentTaskGrid']/div[contains(@class,'k-grid-content')]/table//tr[contains(string(),'%s')]/td";
    String TASK_DETAIL_ITEM    = "//div[@class='row' and contains(string(),'%s')]/div[@class='details-field']";
    String TASK_ROW            = "(//div[@data-rem-id='opentTaskGrid']//table)[2]//tr[contains(string(),'%s')]";
    String TASK_ROW_CHECKBOX   = "((//div[@data-rem-id='opentTaskGrid']//table)[2]//tr[contains(string(),'%s')]/td/input)[1]";
    String OPEN_TASK_REFRESH   = "//button[@rem-trigger-event='refreshOpenTaskGridClick']";
    String COMPLETED_TASK_TAB  = "//li[contains(string(),'Completed Tasks')]";

    // Completed Task objects
    String COMPLETED_TASK_ROW_CHECKBOX = "((//div[@data-rem-id='completedTaskGrid']//table)[2]//tr[contains(string(),'%s')]/td/input)[1]";
    String COMPLETED_TASK_COLUMN       = "//div[@data-rem-id='completedTaskGrid']//thead//th[@data-title='%s']";
    String COMPLETED_TASK_ROW          = "(//div[@data-rem-id='completedTaskGrid']//table)[2]//tr[contains(string(),'%s')]";
    String COMPLETED_TASK_DETAIL_ITEM  = "//div[@data-rem-id='completedTask-tabs']//div[@class='row' and contains(string(),'%s')]/div[@class='details-field']";

    String LOADMASK = "//div[@class='loadmask']";
}
