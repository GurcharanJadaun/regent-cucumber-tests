package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.TasksLocators;

/** Ported from regent.pages.student.TasksPage. */
public class TasksPage extends BasePage {

    private static final String CHANGE_TYPE_STATUS = "Status";

    public TasksPage(Page page) {
        super(page);
    }

    public void clickOnOpenTaskRefresh() {
        click(TasksLocators.OPEN_TASK_REFRESH);
    }

    public void changeTaskStatus(String taskName, String status) {
        click(String.format(TasksLocators.TASK_ROW_CHECKBOX, taskName));
        click(TasksLocators.CHANGE_TYPE_DROPDOWN);
        click(String.format(TasksLocators.TYPE_OPTION, CHANGE_TYPE_STATUS));
        click(TasksLocators.STATUS_DROPDOWN);
        click(String.format(TasksLocators.STATUS_OPTION, status));
        click(TasksLocators.SUBMIT_OPEN_BUTTON);
    }

    public void changeCompletedTaskStatus(String taskName, String status) {
        click(String.format(TasksLocators.COMPLETED_TASK_ROW_CHECKBOX, taskName));
        click(TasksLocators.COMPLETED_CHANGE_TYPE_DROPDOWN);
        click(String.format(TasksLocators.COMPLETED_TYPE_OPTION, CHANGE_TYPE_STATUS));
        click(TasksLocators.COMPLETED_STATUS_DROPDOWN);
        click(String.format(TasksLocators.COMPLETED_STATUS_OPTION, status));
        click(TasksLocators.SUBMIT_COMPLETED_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void changeAllTasksStatus(String status) {
        waitForAjaxRequestToBeFinished();
        if (page.locator(TasksLocators.TASK_ROWS).count() == 0) return;
        click(TasksLocators.SELECT_ALL_TASKS_CHECKBOX);
        click(TasksLocators.CHANGE_TYPE_DROPDOWN);
        click(String.format(TasksLocators.TYPE_OPTION, CHANGE_TYPE_STATUS));
        click(TasksLocators.STATUS_DROPDOWN);
        click(String.format(TasksLocators.STATUS_OPTION, status));
        click(TasksLocators.SUBMIT_OPEN_BUTTON);
    }

    public void sortByTaskId() {
        click(TasksLocators.TASK_ID_COLUMN_SORT);
    }

    public void clickOnTaskItem(String taskName, int index) {
        String selector = String.format(TasksLocators.TASK_ROW, taskName);
        page.locator(selector).nth(index).click();
        waitForAjaxRequestToBeFinished();
        page.locator(TasksLocators.LOADMASK).waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN)
                .setTimeout(timeout));
    }

    public String getTaskDetailItemValue(String itemLabel) {
        return getText(String.format(TasksLocators.TASK_DETAIL_ITEM, itemLabel));
    }

    public boolean verifyNoTasksExists() {
        return page.locator(TasksLocators.TASK_ROWS).count() == 0;
    }

    /** Returns whether the task exists; when found and select=true, also clicks it. */
    public boolean verifyTaskExists(String taskName, boolean select) {
        String selector = String.format(TasksLocators.TASK_NAME_ITEM, taskName);
        boolean found = page.locator(selector).count() > 0;
        if (select && found) {
            click(selector);
            waitForAjaxRequestToBeFinished();
        }
        return found;
    }

    public void clickOnCompletedTasksTab() {
        click(TasksLocators.COMPLETED_TASK_TAB);
    }

    /** Returns whether the completed task exists; when found and selectTask=true, also clicks it. */
    public boolean isTaskCompleted(String taskName, boolean selectTask) {
        String selector = String.format(TasksLocators.COMPLETED_TASK_ROW, taskName);
        boolean present = isVisible(selector);
        if (present && selectTask) {
            click(selector);
        }
        return present;
    }

    public String getCompletedTaskDetailItemValue(String itemLabel) {
        return getText(String.format(TasksLocators.COMPLETED_TASK_DETAIL_ITEM, itemLabel));
    }

    public String getOpenTaskGridTaskId(String rowKey)       { return getOpenTaskGridCell(rowKey, 2); }
    public String getOpenTaskGridTaskName(String rowKey)     { return getOpenTaskGridCell(rowKey, 3); }
    public String getOpenTaskGridTaskQueue(String rowKey)    { return getOpenTaskGridCell(rowKey, 4); }
    public String getOpenTaskGridDocumentName(String rowKey) { return getOpenTaskGridCell(rowKey, 5); }
    public String getOpenTaskGridFay(String rowKey)          { return getOpenTaskGridCell(rowKey, 6); }
    public String getOpenTaskGridPriority(String rowKey)     { return getOpenTaskGridCell(rowKey, 7); }
    public String getOpenTaskGridDaysOld(String rowKey)      { return getOpenTaskGridCell(rowKey, 8); }
    public String getOpenTaskGridAssignedTo(String rowKey)   { return getOpenTaskGridCell(rowKey, 9); }
    public String getOpenTaskGridCurrentStatus(String rowKey) { return getOpenTaskGridCell(rowKey, 13); }

    private String getOpenTaskGridCell(String rowKey, int cellIndex) {
        String rowSelector = String.format(TasksLocators.OPEN_TASK_GRID_CELL, rowKey);
        return page.locator(rowSelector).nth(cellIndex - 1).textContent().trim();
    }
}
