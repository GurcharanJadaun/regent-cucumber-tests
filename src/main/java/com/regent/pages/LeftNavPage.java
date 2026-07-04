package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AcademicPlanLocators;
import com.regent.locators.AwardsLocators;
import com.regent.locators.DocumentsLocators;
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
        clickTabUntilContentLoads(LeftNavLocators.ACADEMIC_PLAN_TAB, AcademicPlanLocators.MAP_BUTTON);
    }

    /** Navigate to student profile Documents tab (student must already be open). */
    public void clickDocumentsTab() {
        clickTabUntilContentLoads(LeftNavLocators.DOCUMENTS_TAB, DocumentsLocators.ADD_DOCUMENT_BUTTON);
    }

    /** Navigate to student profile Awards tab (student must already be open). */
    public void clickAwardsTab() {
        clickTabUntilContentLoads(LeftNavLocators.AWARDS_TAB, AwardsLocators.ADD_AWARD_BUTTON);
    }

    /** Navigate to student profile Communications tab (student must already be open). */
    public void clickCommunicationsTab() {
        click(LeftNavLocators.COMMUNICATIONS_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile Details tab (student must already be open). */
    public void clickStudentDetailsTab() {
        click(LeftNavLocators.DETAILS_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile Isir tab (student must already be open). */
    public void clickIsirTab() {
        click(LeftNavLocators.ISIR_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile Loans tab (student must already be open). */
    public void clickLoansTab() {
        click(LeftNavLocators.LOANS_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile Courses tab (student must already be open). */
    public void clickCoursesTab() {
        click(LeftNavLocators.COURSES_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile Tasks tab (student must already be open). */
    public void clickTasksTab() {
        click(LeftNavLocators.TASKS_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile Activity tab (student must already be open). */
    public void clickActivityTab() {
        waitForAjaxRequestToBeFinished();
        click(LeftNavLocators.ACTIVITY_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile History tab (student must already be open). */
    public void clickHistoryTab() {
        click(LeftNavLocators.HISTORY_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile Summary tab (student must already be open). */
    public void clickSummaryTab() {
        click(LeftNavLocators.SUMMARY_TAB);
    }

    /** Navigate to student profile COD tab (student must already be open). */
    public void clickCodTab() {
        click(LeftNavLocators.COD_TAB);
        waitForAjaxRequestToBeFinished();
    }

    /** Navigate to student profile Advisor Assist tab (student must already be open). */
    public void clickAdvisorAssistTab() {
        click(LeftNavLocators.ADVISOR_ASSIST_TAB);
    }

    // ── Student-profile-wide actions (StudentBasePage in the reference project) ─────

    public void clickRefreshButton() {
        click(LeftNavLocators.REFRESH_BUTTON);
    }

    public void clickAddToWatchList() {
        waitForAjaxRequestToBeFinished();
        click(LeftNavLocators.ADD_TO_WATCH_LIST_BUTTON);
    }

    public void clickRemoveFromWatchList() {
        click(LeftNavLocators.REMOVE_FROM_WATCH_LIST_BUTTON);
    }

    public boolean isAddToWatchListPresent() {
        return isVisible(LeftNavLocators.ADD_TO_WATCH_LIST_BUTTON);
    }

    public boolean isRemoveFromWatchListPresent() {
        return isVisible(LeftNavLocators.REMOVE_FROM_WATCH_LIST_BUTTON);
    }

    public void closeStudentTab() {
        click(LeftNavLocators.STUDENT_TAB_CLOSE);
    }

    /**
     * Root cause of the intermittent "tab highlights but content never loads" failures: this app
     * keeps previously-viewed students open as background tabs (see LeftNavLocators), so a stale,
     * invisible duplicate nav item — sometimes still carrying a leftover active-state class from
     * before its tab was backgrounded — could get targeted instead of the current foreground
     * student's own tab. The :visible-filtered locators fix that at the source; this retry loop
     * is kept as a low-cost second line of defense.
     */
    private void clickTabUntilContentLoads(String tabSelector, String contentMarkerSelector) {
        for (int attempt = 0; attempt < 5; attempt++) {
            click(tabSelector);
            waitForPageLoad();
            for (int i = 0; i < 10; i++) {
                if (page.locator(contentMarkerSelector).count() > 0) return;
                page.waitForTimeout(2000);
            }
        }
    }

    public boolean isNavMenuVisible() {
        return isVisible(LeftNavLocators.NAV_ACCORDION);
    }

    // ── Admin / Home / Reports panels ────────────────────────────────────────

    public void clickAdministration() {
        click(LeftNavLocators.ADMIN_PANEL);
    }

    public void clickHome() {
        click(LeftNavLocators.HOME_PANEL);
        waitForAjaxRequestToBeFinished();
    }

    public void clickHomeTaskGroup(String taskStatusCategory, String taskTypeGroup) {
        String selector = String.format(LeftNavLocators.HOME_TASK_GROUP, taskStatusCategory, taskTypeGroup);
        click(selector);
        waitForAjaxRequestToBeFinished();
    }

    public void navigateToReports() {
        click(LeftNavLocators.REPORTS_PANEL);
        waitForAjaxRequestToBeFinished();
        click(LeftNavLocators.STANDARD_REPORTS_PANEL);
        waitForAjaxRequestToBeFinished();
    }

    public void clickReportLink(String reportName) {
        String selector = String.format(LeftNavLocators.REPORT_LINK, reportName);
        click(selector);
    }

    public boolean isStudentPresentInWatchList(String student) {
        String selector = String.format(LeftNavLocators.WATCH_LIST_ITEM, student);
        return isVisible(selector);
    }

    public void clickStudentSearch() {
        clickMenuItem("Student Search");
    }

    // ── Administration tree ──────────────────────────────────────────────────

    public void clickOnEnterprise(String enterpriseValue) {
        String selector = String.format(LeftNavLocators.ENTERPRISE_LINK, enterpriseValue);
        page.locator(selector).dblclick();
        waitForAjaxRequestToBeFinished();
    }

    public boolean isEnterprisePresent(String enterpriseName) {
        String selector = String.format(LeftNavLocators.ENTERPRISE_LINK, enterpriseName);
        return isVisible(selector);
    }

    public void clickOnCampus() {
        page.locator(LeftNavLocators.CAMPUS_ITEM).dblclick();
    }

    public void expandCampus(String campusName) {
        String selector = String.format(LeftNavLocators.EXPAND_CAMPUS, campusName);
        click(selector);
    }

    public String getCampusName() {
        return getText(LeftNavLocators.CAMPUS_ITEM);
    }

    public void clickOnInstitution() {
        page.locator(LeftNavLocators.INSTITUTION_LINK).dblclick();
        waitForAjaxRequestToBeFinished();
    }

    /** Expands the Enterprise node first if the Institution link isn't present yet. */
    public void navigateToInstitution() {
        if (!isVisible(LeftNavLocators.INSTITUTION_LINK)) {
            String selector = String.format(LeftNavLocators.ENTERPRISE_LINK, "");
            page.locator(selector).dblclick();
            waitForAjaxRequestToBeFinished();
        }
        clickOnInstitution();
    }

    public String getInstitutionName() {
        return getText(LeftNavLocators.INSTITUTION_LINK);
    }

    public void clickUsersMenuItem() {
        clickMenuItem("Users");
    }

    public void clickSites() {
        click(LeftNavLocators.SITES_LINK);
    }

    public void clickProgramSetup() {
        click(LeftNavLocators.PROGRAM_SETUP_LINK);
    }

    public void clickTermSetup() {
        click(LeftNavLocators.TERM_SETUP_LINK);
    }

    public void clickFundSetup() {
        click(LeftNavLocators.FUND_SETUP_LINK);
    }

    public void clickCostSetup() {
        click(LeftNavLocators.COST_SETUP_LINK);
    }

    public void clickOnTaskManagement() {
        page.locator(LeftNavLocators.TASK_MANAGEMENT_LINK).dblclick();
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnTaskSetup() {
        click(LeftNavLocators.TASK_SETUP_LINK);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnDocumentSetup() {
        click(LeftNavLocators.DOCUMENT_SETUP_LINK);
        waitForAjaxRequestToBeFinished();
    }

    // ── Processes menu items ─────────────────────────────────────────────────

    public void clickBatchPackage() {
        clickProcesses();
        clickMenuItem("Batch Package");
    }

    public void clickSapProcessItem() {
        clickProcesses();
        clickMenuItem("SAP Process");
    }

    public void clickExportProcessItem() {
        clickProcesses();
        clickMenuItem("Export Process");
    }

    /** App moved Export EST under Export Process Type as of 5.6 — fall back if the direct item is gone. */
    public void clickExportEst() {
        clickProcesses();
        String directItem = String.format(LeftNavLocators.MENU_ITEM_LINK, "Export EST");
        if (page.locator(directItem).count() > 0) {
            click(directItem);
        } else {
            clickMenuItem("Export Process");
        }
    }

    public void clickGenerateCommunications() {
        clickMenuItem("Generate Communications");
    }

    public void clickOnRoles() {
        clickMenuItem("Roles");
    }
}
