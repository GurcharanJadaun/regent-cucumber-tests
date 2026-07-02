package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.LeftNavLocators;

public class LeftNavPage extends BasePage {

    public LeftNavPage(Page page) {
        super(page);
    }

    public void clickProcesses() {
        try {
            click(LeftNavLocators.PROCESSES_PANEL);
        } catch (Exception e) {
            // already expanded
        }
    }

    public void clickStudentsPanel() {
        try {
            click(LeftNavLocators.STUDENTS_PANEL);
        } catch (Exception e) {
            // already expanded
        }
    }

    private void clickMenuItem(String text) {
        String selector = String.format(LeftNavLocators.MENU_ITEM_LINK, text);
        click(selector);
        waitForPageLoad();
    }

    public void clickImportProcess() {
        clickProcesses();
        clickMenuItem("Import Process");
    }

    public void clickProcessLog() {
        clickProcesses();
        clickMenuItem("Process Log");
    }

    /** Navigate to student profile Academic Plan tab (student must already be open). */
    public void clickAcademicPlanTab() {
        click(LeftNavLocators.ACADEMIC_PLAN_TAB);
        waitForPageLoad();
    }

    public boolean isNavMenuVisible() {
        return isVisible(LeftNavLocators.NAV_ACCORDION);
    }
}
