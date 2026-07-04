package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.TaskSetupLocators;

/** Ported from regent.pages.TaskSetupPage (+ the Filter base it extends, for filterByTaskName). */
public class TaskSetupPage extends BasePage {

    public TaskSetupPage(Page page) {
        super(page);
    }

    public void filterByTaskName(String taskName) {
        click(TaskSetupLocators.TASK_TYPE_NAME_FILTER);
        fill(TaskSetupLocators.FILTER_INPUT, taskName);
        click(TaskSetupLocators.FILTER_SUBMIT);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnGridTask(String taskName) {
        String selector = String.format(TaskSetupLocators.TASK_SETUP_GRID_ROW, taskName);
        click(selector);
        waitForAjaxRequestToBeFinished();
    }

    public boolean taskExists(String taskName) {
        String selector = String.format(TaskSetupLocators.TASK_SETUP_GRID_ROW, taskName);
        return isVisible(selector);
    }

    public void addTaskSetup() {
        click(TaskSetupLocators.ADD_TASK_SETUP_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void refreshTaskSetup() {
        click(TaskSetupLocators.TASK_GRID_REFRESH_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickRestrictProcessSetupTab() {
        click(TaskSetupLocators.RESTRICT_PROCESS_SETUP_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void activateTask(boolean activate) {
        boolean isChecked = page.locator(TaskSetupLocators.ACTIVE_TASK_CHECKBOX_INPUT).isChecked();
        if (isChecked != activate) {
            click(TaskSetupLocators.ACTIVE_TASK_CHECKBOX_INPUT);
        }
    }

    public void editTask() {
        click(TaskSetupLocators.EDIT_TASK_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void saveTask() {
        click(TaskSetupLocators.SAVE_TASK_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public boolean isTaskActive() {
        String checked = page.locator(TaskSetupLocators.ACTIVE_CHECKBOX_DETAILS_FIELD).getAttribute("checked");
        return "true".equals(checked);
    }

    /** Deletes the task and accepts the native confirm() dialog the app raises. */
    public void deleteTask() {
        page.onceDialog(dialog -> dialog.accept());
        click(TaskSetupLocators.DELETE_TASK_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public boolean isTaskTypeOptions() {
        click(TaskSetupLocators.TASK_TYPE_DROPDOWN);
        boolean optionsListed = isVisibleNow(TaskSetupLocators.TASK_TYPE_OPTIONS);
        click(TaskSetupLocators.TASK_SETUP_ID_LABEL);
        return optionsListed;
    }

    public boolean isDefaultQueueOptions() {
        click(TaskSetupLocators.TASK_QUEUE_DROPDOWN);
        boolean optionsListed = isVisibleNow(TaskSetupLocators.TASK_QUEUE_OPTIONS);
        click(TaskSetupLocators.TASK_SETUP_ID_LABEL);
        return optionsListed;
    }

    public boolean isDefaultPriorityOptions() {
        click(TaskSetupLocators.TASK_PRIORITY_DROPDOWN);
        boolean optionsListed = isVisibleNow(TaskSetupLocators.TASK_PRIORITY_OPTIONS);
        click(TaskSetupLocators.TASK_SETUP_ID_LABEL);
        return optionsListed;
    }

    public boolean isDefaultTaskStatusOptions() {
        click(TaskSetupLocators.TASK_STATUS_DROPDOWN);
        boolean optionsListed = isVisibleNow(TaskSetupLocators.TASK_STATUS_OPTIONS);
        click(TaskSetupLocators.TASK_SETUP_ID_LABEL);
        return optionsListed;
    }

    public boolean isActiveInput() {
        return isVisible(TaskSetupLocators.ACTIVE_TASK_CHECKBOX_INPUT);
    }

    public void setTaskDescription(String description) {
        page.locator(TaskSetupLocators.TASK_DESCRIPTION_INPUT).scrollIntoViewIfNeeded();
        fill(TaskSetupLocators.TASK_DESCRIPTION_INPUT, description);
    }

    public String getTaskDescription() {
        return getText(TaskSetupLocators.TASK_DESCRIPTION);
    }
}
