package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ProcessLogLocators;
import com.regent.utils.StudentUser;
import org.testng.Assert;

public class ProcessLogPage extends BasePage {

    private static final int MAX_POLLS  = 300;
    private static final int POLL_MS    = 2000;

    public ProcessLogPage(Page page) {
        super(page);
    }

    /**
     * Polls the process log until the row whose filename contains {@code fileTag} reaches
     * COMPLETE status. Refreshes every 2 s, fails after ~10 minutes. SBL imports additionally
     * trigger an async "Process CE Rollup" job; without waiting for it, downstream packaging
     * can run against stale enrollment data.
     *
     * @param fileTag  e.g. "autoxyzabc_sbl.xml" or "autoxyzabc_isir.txt"
     * @param isSbl    wait for the associated CE Rollup job to complete as well
     */
    public void waitForImportComplete(String fileTag, boolean isSbl) {
        setSorting();
        for (int i = 0; i < MAX_POLLS; i++) {
            clickRefresh();
            page.waitForTimeout(POLL_MS);

            String statusSelector = String.format(ProcessLogLocators.STATUS_BY_FILENAME, fileTag);
            if (!isVisibleNow(statusSelector)) continue;

            String status = getText(statusSelector).trim();
            if (ProcessLogLocators.COMPLETE.equals(status)) {
                if (isSbl) waitForCeRollup(fileTag);
                // Opens the row's detail view, which is what activates the Students tab
                // used afterward by navigateToStudent() — without this click it stays disabled.
                click(statusSelector);
                waitForAjaxRequestToBeFinished();
                return;
            }
            if (ProcessLogLocators.ERROR.equals(status)) {
                Assert.fail("Import process ended with ERROR for file: " + fileTag
                        + " — " + getErrorLogDetails(statusSelector));
            }
            // PROCESSING or not yet visible → keep polling
        }
        Assert.fail("Import process did not complete within timeout for file: " + fileTag);
    }

    /** Opens the failed row's detail view and reads the "Error Log" tab's message. */
    private String getErrorLogDetails(String statusSelector) {
        try {
            click(statusSelector);
            String errorLogTab = String.format(ProcessLogLocators.PROCESS_TAB, "Error Log");
            click(errorLogTab);
            waitForAjaxRequestToBeFinished();
            return getText(ProcessLogLocators.ERROR_LOG_DETAILS);
        } catch (Exception e) {
            return "(could not read error details: " + e.getMessage() + ")";
        }
    }

    /** Waits for the "Process CE Rollup" job triggered by an SBL import to complete, if one exists. */
    private void waitForCeRollup(String fileTag) {
        String processIdSelector = String.format(ProcessLogLocators.CE_ROLLUP_PROCESS_ID_BY_TAG, fileTag);
        if (!isVisibleNow(processIdSelector)) return; // no associated CE Rollup job
        String processId = getText(processIdSelector).trim();
        String statusSelector = String.format(ProcessLogLocators.STATUS_BY_PROCESS_ID, processId);

        for (int i = 0; i < MAX_POLLS; i++) {
            if (isVisibleNow(statusSelector)) {
                String status = getText(statusSelector).trim();
                if (ProcessLogLocators.COMPLETE.equals(status)) return;
                if (ProcessLogLocators.ERROR.equals(status)) {
                    Assert.fail("CE Rollup process ended with ERROR for file: " + fileTag);
                }
            }
            page.waitForTimeout(POLL_MS);
            clickRefresh();
        }
        Assert.fail("CE Rollup process did not complete within timeout for file: " + fileTag);
    }

    public void waitForSblComplete(StudentUser student) {
        waitForImportComplete(student.getSblFileName(), true);
    }

    public void waitForIsirComplete(StudentUser student) {
        waitForImportComplete(student.getIsirFileName(), false);
    }

    /**
     * After import completes, click the Students tab and then the View link for the student,
     * navigating to the student's profile page.
     */
    public void navigateToStudent(StudentUser student) {
        String tabSelector = String.format(ProcessLogLocators.PROCESS_TAB, "Students");
        click(tabSelector);
        page.waitForTimeout(1000);

        String viewSelector = String.format(ProcessLogLocators.VIEW_STUDENT_LINK, student.getFirstName());
        click(viewSelector);
        waitForPageLoad();
    }

    /**
     * The grid is system-wide (tens of thousands of rows across all users/jobs, 20/page).
     * Sort by newest Process Id so our freshly submitted row reliably lands on page 1
     * instead of being buried by unrelated background jobs (RNA Analysis, CE Rollup, etc.).
     */
    private void setSorting() {
        waitForAjaxRequestToBeFinished();
        if (!isVisibleNow(ProcessLogLocators.PROCESS_ID_DESC_SORT)) {
            try {
                click(ProcessLogLocators.STARTED_ON_SORT_LINK);
                waitForAjaxRequestToBeFinished();
                click(ProcessLogLocators.PROCESS_ID_SORT_LINK);
                waitForAjaxRequestToBeFinished();
                click(ProcessLogLocators.PROCESS_ID_SORT_LINK);
                waitForAjaxRequestToBeFinished();
            } catch (Exception ignored) {
                // sort controls may already reflect the desired state
            }
        }
    }

    private void clickRefresh() {
        try {
            page.locator(ProcessLogLocators.REFRESH_BUTTON)
                    .click(new com.microsoft.playwright.Locator.ClickOptions().setTimeout(3000));
        } catch (Exception ignored) {
            // refresh button may not be visible on first load
        }
    }
}
