package com.regent.locators;

/** Locators ported from regent.pages.TaskSetupPage (and the Filter base it extends). */
public interface TaskSetupLocators {

    String TASK_TYPE_NAME_FILTER   = "//div[@data-rem-id='taskSetupGrid']//th[@data-field='TaskTypeName']/a[contains(@class,'k-grid-filter')]";
    String FILTER_INPUT            = "//form[@data-role='popup' and contains(@style,'block')]//input[@title][not(contains(@style,'none'))]";
    String FILTER_SUBMIT           = "//form[@data-role='popup' and contains(@style,'block')]//button[@type='submit' and string()='Filter']";

    String TASK_SETUP_GRID_ROW     = "//div[@data-rem-id='taskSetupGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]";
    String TASK_SETUP_GRID_CELL    = "//div[@data-rem-id='taskSetupGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td";
    String ADD_TASK_SETUP_BUTTON   = "//button[normalize-space()='Add Task Setup']";
    String TASK_GRID_REFRESH_BUTTON = "//button[@class='k-button r-entity-menu'][normalize-space()='Refresh']";
    String RESTRICT_PROCESS_SETUP_TAB = "//span[contains(text(),'Restrict Process Setup')]";
    String EDIT_TASK_BUTTON        = "//button[@rem-trigger-event='editTaskSetupClick']";
    String ACTIVE_TASK_CHECKBOX_INPUT = "//input[@id='TaskSetup_Active']";
    String SAVE_TASK_BUTTON        = "//button[normalize-space()='Save']";
    String DELETE_TASK_BUTTON      = "//button[@rem-trigger-event='deleteTaskSetupClick']";

    // Details (view mode)
    String DETAILS_FIELD           = "//div[@class='details-label' and contains(string(),'%s')]/following-sibling::div[@class='details-field']";
    String ACTIVE_CHECKBOX_DETAILS_FIELD = "//div[@class='details-label' and contains(string(),'Active')]/following-sibling::div[@class='details-field']/input";
    String TASK_DESCRIPTION        = "//div[@class='details-label' and ./label[@for='TaskSetup_DescriptionTemplate']]/following-sibling::div[@class='details-field']";

    // Edit mode
    String TASK_SETUP_ID_LABEL     = "//label[@for='TaskSetup_Id']";

    // Kendo dropdowns — stable aria-owns/listbox pattern (see translation rules)
    String TASK_TYPE_DROPDOWN      = "span[aria-owns='TaskSetup_TaskTypeId_listbox']";
    String TASK_TYPE_OPTIONS       = "#TaskSetup_TaskTypeId_listbox";
    String TASK_QUEUE_DROPDOWN     = "span[aria-owns='TaskSetup_TaskQueueId_listbox']";
    String TASK_QUEUE_OPTIONS      = "#TaskSetup_TaskQueueId_listbox";
    String TASK_PRIORITY_DROPDOWN  = "span[aria-owns='TaskSetup_InitialPriorityCode_listbox']";
    String TASK_PRIORITY_OPTIONS   = "#TaskSetup_InitialPriorityCode_listbox";
    String TASK_STATUS_DROPDOWN    = "span[aria-owns='TaskSetup_InitialTaskStatusCode_listbox']";
    String TASK_STATUS_OPTIONS     = "#TaskSetup_InitialTaskStatusCode_listbox";

    String TASK_DESCRIPTION_INPUT  = "#TaskSetup_DescriptionTemplate";
}
