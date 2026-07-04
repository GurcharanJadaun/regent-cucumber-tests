package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.MainPageTabStripLocators;

/** Ported from regent.pages.MainPageTabStrip. */
public class MainPageTabStrip extends BasePage {

    public MainPageTabStrip(Page page) {
        super(page);
    }

    public void clickDashboardTab() {
        click(MainPageTabStripLocators.DASHBOARD_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickProcessLogTab() {
        click(MainPageTabStripLocators.PROCESS_LOG_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickStudentTab(String studentName) {
        String selector = String.format(MainPageTabStripLocators.STUDENT_TAB, studentName);
        click(selector);
        waitForAjaxRequestToBeFinished();
    }

    public void closeStudentTab() {
        click(MainPageTabStripLocators.STUDENT_TAB_CLOSE);
    }

    public void closeTab(String tabName) {
        String selector = String.format(MainPageTabStripLocators.TAB_CLOSE_BY_NAME, tabName);
        click(selector);
        waitForAjaxRequestToBeFinished();
    }
}
