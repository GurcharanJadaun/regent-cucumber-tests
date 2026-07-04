package com.regent.locators;

/**
 * Ported from regent.pages.AddTaskSetupPage. Generic dropdowns are keyed by field name
 * (TaskTypeId, TaskQueueId, InitialPriorityCode, InitialTaskStatusCode,
 * DateUsedForTaskEscalationCode) and reused across several selects on the same form, so — per
 * the Kendo stale-duplicate rule — options are filtered by :visible rather than positional [last()].
 */
public interface AddTaskSetupLocators {
    String TASK_SETUP_DROPDOWN = "span[aria-owns='TaskSetup_%s_listbox']";
    // Source used contains(string(),...) (substring, not exact) — kept as-is, just :visible-filtered.
    String TASK_SETUP_OPTION   = "#TaskSetup_%s_listbox li:visible:has-text('%s')";

    String TASK_DESCRIPTION = "#TaskSetup_DescriptionTemplate";
    String DUE_DATE_OFFSET_INPUT = "#TaskSetup_DueDateDayOffset";
    String LOW_DAYS_PRIOR_INPUT    = "#TaskSetup_LowDaysPriorToDueDate";
    String MEDIUM_DAYS_PRIOR_INPUT = "#TaskSetup_MediumDaysPriorToDueDate";
    String HIGH_DAYS_PRIOR_INPUT   = "#TaskSetup_HighDaysPriorToDueDate";
    String URGENT_DAYS_PRIOR_INPUT = "#TaskSetup_UrgentDaysPriorToDueDate";

    String SAVE_BUTTON   = "//button[normalize-space()='Save']";
    String CANCEL_BUTTON = "//button[normalize-space()='Cancel']";

    // Field names used with the generic dropdown locators above
    String FIELD_TASK_TYPE_ID = "TaskTypeId";
    String FIELD_TASK_QUEUE_ID = "TaskQueueId";
    String FIELD_INITIAL_PRIORITY_CODE = "InitialPriorityCode";
    String FIELD_INITIAL_TASK_STATUS_CODE = "InitialTaskStatusCode";
    String FIELD_DATE_USED_FOR_TASK_ESCALATION_CODE = "DateUsedForTaskEscalationCode";
}
