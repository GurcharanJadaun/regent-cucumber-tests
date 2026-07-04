package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddTaskSetupLocators;

public class AddTaskSetupPage extends BasePage {

    public AddTaskSetupPage(Page page) {
        super(page);
    }

    private void selectFromDropdown(String fieldName, String value) {
        click(String.format(AddTaskSetupLocators.TASK_SETUP_DROPDOWN, fieldName));
        click(String.format(AddTaskSetupLocators.TASK_SETUP_OPTION, fieldName, value));
    }

    public void selectTaskType(String taskType) {
        selectFromDropdown(AddTaskSetupLocators.FIELD_TASK_TYPE_ID, taskType);
    }

    public void selectDefaultQueue(String defaultQueue) {
        selectFromDropdown(AddTaskSetupLocators.FIELD_TASK_QUEUE_ID, defaultQueue);
    }

    public void selectDefaultPriority(String defaultPriority) {
        selectFromDropdown(AddTaskSetupLocators.FIELD_INITIAL_PRIORITY_CODE, defaultPriority);
    }

    public void selectDefaultTaskStatus(String defaultStatus) {
        selectFromDropdown(AddTaskSetupLocators.FIELD_INITIAL_TASK_STATUS_CODE, defaultStatus);
    }

    public void setTaskDescription(String description) {
        fill(AddTaskSetupLocators.TASK_DESCRIPTION, description);
    }

    public void setDueDateOffset(String offset) {
        fill(AddTaskSetupLocators.DUE_DATE_OFFSET_INPUT, offset);
    }

    public void selectDateForTaskEscalation(String taskEscalationDateCode) {
        selectFromDropdown(AddTaskSetupLocators.FIELD_DATE_USED_FOR_TASK_ESCALATION_CODE, taskEscalationDateCode);
    }

    public void setLowDaysPrior(String lowDays) {
        fill(AddTaskSetupLocators.LOW_DAYS_PRIOR_INPUT, lowDays);
    }

    public void setMediumDaysPrior(String mediumDays) {
        fill(AddTaskSetupLocators.MEDIUM_DAYS_PRIOR_INPUT, mediumDays);
    }

    public void setHighDaysPrior(String highDays) {
        fill(AddTaskSetupLocators.HIGH_DAYS_PRIOR_INPUT, highDays);
    }

    public void setUrgentDaysPrior(String urgentDays) {
        fill(AddTaskSetupLocators.URGENT_DAYS_PRIOR_INPUT, urgentDays);
    }

    public void saveAddedTask() {
        click(AddTaskSetupLocators.SAVE_BUTTON);
    }
}
