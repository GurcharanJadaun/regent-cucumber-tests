package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.TaskManagementLocators;

/** Ported from regent.pages.TaskManagementPage. */
public class TaskManagementPage extends BasePage {

    public TaskManagementPage(Page page) {
        super(page);
    }

    public void runTaskAssignmentProcess() {
        click(TaskManagementLocators.RUN_TASK_ASSIGNMENT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void runTaskEscalationProcess() {
        click(TaskManagementLocators.RUN_TASK_ESCALATION_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
