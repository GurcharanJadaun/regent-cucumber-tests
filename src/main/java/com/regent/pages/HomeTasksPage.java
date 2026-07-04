package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.HomeTasksLocators;

import java.util.List;

/** Ported from regent.pages.HomeTasksPage. */
public class HomeTasksPage extends BasePage {

    public HomeTasksPage(Page page) {
        super(page);
    }

    public void filterByAssignedTo(String userName) {
        click(HomeTasksLocators.OPEN_TASKS_ASSIGNED_TO_FILTER);
        fill(HomeTasksLocators.FILTER_INPUT, userName);
        click(HomeTasksLocators.FILTER_SUBMIT);
        waitForAjaxRequestToBeFinished();
    }

    public String getOpenTaskPagerInfo() {
        return getText(HomeTasksLocators.OPEN_TASK_PAGER_INFO);
    }

    public String getHomeTaskId(String rowNumber) {
        String selector = String.format(HomeTasksLocators.TASK_GRID_ROW_CELL, rowNumber);
        return getGridCellText(selector, 2);
    }

    public String getHomeTaskGridTaskName(String taskId) {
        return getTaskGridCell(taskId, 3);
    }

    public String getHomeTaskGridTaskQueue(String taskId) {
        return getTaskGridCell(taskId, 4);
    }

    public String getHomeTaskGridPriority(String taskId) {
        return getTaskGridCell(taskId, 7);
    }

    public String getHomeTaskGridAssignedTo(String taskId) {
        return getTaskGridCell(taskId, 9);
    }

    public String getHomeTaskGridDueDate(String taskId) {
        return getTaskGridCell(taskId, 12);
    }

    public String getHomeTaskGridCurrentStatus(String taskId) {
        return getTaskGridCell(taskId, 14);
    }

    public String getHomeTaskGridExternaId(String taskId) {
        return getTaskGridCell(taskId, 15);
    }

    public boolean isHomeTaskFound(String taskRowKey, int columnNumber) {
        return getTaskGridCell(taskRowKey, columnNumber).equals(taskRowKey);
    }

    public void refreshOpenTaskGrid() {
        click(HomeTasksLocators.REFRESH_OPEN_TASK_GRID_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public String getCompletedTaskGridCurrentStatus(String taskId) {
        String selector = String.format(HomeTasksLocators.COMPLETED_TASK_GRID_CELL, taskId);
        return getGridCellText(selector, 14);
    }

    public void refreshCompletedTaskGrid() {
        click(HomeTasksLocators.REFRESH_COMPLETED_TASK_GRID_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnOpenTasksTab() {
        click(HomeTasksLocators.OPEN_TASK_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnCompletedTasksTab() {
        click(HomeTasksLocators.COMPLETED_TASK_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void changeTasksStatus(List<String> taskIds, String status) {
        checkOpenTaskRows(taskIds);
        selectChangeType("Status");
        click(HomeTasksLocators.STATUS_DROPDOWN);
        click(String.format(HomeTasksLocators.STATUS_OPTION, status));
        click(HomeTasksLocators.SUBMIT_OPEN_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void changeCompletedTasksStatus(List<String> taskIds, String status) {
        for (String taskId : taskIds) {
            click(String.format(HomeTasksLocators.COMPLETED_TASK_ROW_CHECKBOX, taskId));
        }
        click(HomeTasksLocators.COMPLETED_CHANGE_TYPE_DROPDOWN);
        click(String.format(HomeTasksLocators.COMPLETED_TYPE_OPTION, "Status"));
        click(HomeTasksLocators.COMPLETED_STATUS_DROPDOWN);
        click(String.format(HomeTasksLocators.COMPLETED_STATUS_OPTION, status));
        click(HomeTasksLocators.SUBMIT_COMPLETED_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void changeTasksDueDate(List<String> taskIds, String dueDate) {
        selectChangeType("Due Date");
        fill(HomeTasksLocators.DUE_DATE_INPUT, dueDate);
        checkOpenTaskRows(taskIds);
        click(HomeTasksLocators.SUBMIT_OPEN_BUTTON);
        waitForAjaxRequestToBeFinished();
        page.waitForTimeout(5000);
    }

    /** Note: queue names are not consistent across datasets. */
    public void changeTasksQueue(List<String> taskIds, String queueName) {
        checkOpenTaskRows(taskIds);
        selectChangeType("Queue");
        click(HomeTasksLocators.QUEUE_DROPDOWN);
        click(String.format(HomeTasksLocators.QUEUE_OPTION, queueName));
        click(HomeTasksLocators.SUBMIT_OPEN_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void changeTasksPriority(List<String> taskIds, String priority) {
        checkOpenTaskRows(taskIds);
        selectChangeType("Priority");
        click(HomeTasksLocators.PRIORITY_DROPDOWN);
        click(String.format(HomeTasksLocators.PRIORITY_OPTION, priority));
        click(HomeTasksLocators.SUBMIT_OPEN_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void changeTasksAssignee(List<String> taskIds, String assignee) {
        checkOpenTaskRows(taskIds);
        selectChangeType("Assignee");
        click(HomeTasksLocators.USER_DROPDOWN);
        click(String.format(HomeTasksLocators.USER_OPTION, assignee));
        click(HomeTasksLocators.SUBMIT_OPEN_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    private void checkOpenTaskRows(List<String> taskIds) {
        for (String taskId : taskIds) {
            click(String.format(HomeTasksLocators.TASK_ROW_CHECKBOX, taskId));
        }
    }

    private void selectChangeType(String type) {
        click(HomeTasksLocators.CHANGE_TYPE_DROPDOWN);
        click(String.format(HomeTasksLocators.CHANGE_TYPE_OPTION, type));
    }

    private String getTaskGridCell(String taskId, int columnNumber) {
        String selector = String.format(HomeTasksLocators.TASK_GRID_CELL, taskId);
        return getGridCellText(selector, columnNumber);
    }

    /** Locators here resolve to a row's <td> cells; pick the 1-based column like the source. */
    private String getGridCellText(String rowCellsSelector, int columnNumber) {
        return page.locator(rowCellsSelector).nth(columnNumber - 1).textContent().trim();
    }
}
