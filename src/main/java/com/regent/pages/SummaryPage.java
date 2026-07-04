package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.SummaryLocators;

/** Ported from regent.pages.student.SummaryPage. */
public class SummaryPage extends BasePage {

    public SummaryPage(Page page) {
        super(page);
    }

    public String getStudentFullName() {
        return getText(SummaryLocators.STUDENT_FULL_NAME);
    }

    public String getStudentsFirstAndLastNames() {
        String[] name = getStudentFullName().split(" ");
        if (name.length == 3) {
            return name[0] + " " + name[2];
        }
        return getStudentFullName();
    }

    public String getStudentExternalId() {
        return getText(SummaryLocators.STUDENT_EXTERNAL_ID);
    }

    public String getStudentSnn() {
        return getText(String.format(SummaryLocators.STUDENT_INFO_FROM_MAIN_PAGE, "SSN"));
    }

    public String getStudentFullSnn() {
        click(SummaryLocators.SSN_HINT_LINK);
        return getText(SummaryLocators.FULL_SSN_CLUETIP).replace("SSN: ", "").trim();
    }

    public String getCurrentSAPStatus() {
        return getText(String.format(SummaryLocators.SAP_STATUS, "Current SAP Status"));
    }

    public boolean isSblLoadActivityPresent() {
        String selector = String.format(SummaryLocators.RECENT_ACTIVITY_ITEM, "SBL Load");
        return isVisible(selector);
    }

    public boolean isIsirRelatedActivityPresent() {
        String selector = String.format(SummaryLocators.RECENT_ACTIVITY_ITEM, "ISIR Related");
        return isVisible(selector);
    }

    public String getRecentActivity(String rowKey) {
        return getText(String.format(SummaryLocators.RECENT_ACTIVITY_ROW, rowKey));
    }

    public String getSummaryItem(String itemLabel) {
        return getText(String.format(SummaryLocators.SUMMARY_ITEM, itemLabel));
    }
}
