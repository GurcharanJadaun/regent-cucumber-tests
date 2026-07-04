package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.AcademicPlanLocators;
import org.testng.Assert;

public class AcademicPlanPage extends BasePage {

    private static final int MAX_REFRESH = 400;

    public AcademicPlanPage(Page page) {
        super(page);
    }

    // ── Map / packaging ───────────────────────────────────────────────────────

    /**
     * Same AJAX-tab-load race as DocumentsPage.waitForDocumentsTabReady(): the Academic Plan
     * tab's content (including the Map button) renders slightly after the tab switch settles,
     * and can take much longer right after a fresh SBL/ISIR import. Poll before clicking rather
     * than trusting a single readiness check.
     */
    public void clickMapButton() {
        for (int i = 0; i < 30; i++) {
            if (page.locator(AcademicPlanLocators.MAP_BUTTON).count() > 0) break;
            page.waitForTimeout(3000);
        }
        click(AcademicPlanLocators.MAP_BUTTON);
        page.waitForTimeout(1000);
    }

    /** Click "Basic Packaging" in the Map popup (no log-level override). */
    public void runBasicPackaging() {
        click(AcademicPlanLocators.BASIC_PACKAGING_BTN);
        page.waitForTimeout(1000);
    }

    /** Wait up to ~2 minutes for the Finish button to become enabled, then click it. */
    public void clickFinish() {
        for (int i = 0; i <= 60; i++) {
            page.waitForTimeout(2000);
            Locator finishBtn = page.locator(AcademicPlanLocators.FINISH_BUTTON);
            if (finishBtn.count() > 0 && finishBtn.isEnabled()) {
                finishBtn.click();
                waitForPageLoad();
                return;
            }
        }
        Assert.fail("Finish button never became enabled after packaging");
    }

    /**
     * After clicking Finish, poll the Academic Plan page until the plan appears
     * (up to ~20 minutes, matching original Selenium wait).
     */
    public void waitForAcademicPlanAppear() {
        for (int i = 0; i < MAX_REFRESH; i++) {
            // If the "No Current Academic Plan" message is gone, we're done
            if (!isVisible(AcademicPlanLocators.NO_PLAN_MESSAGE)) return;

            try {
                page.locator(AcademicPlanLocators.REFRESH_BUTTON).click();
            } catch (Exception ignored) {}
            page.waitForTimeout(3000);
        }
        Assert.fail("Academic Plan did not appear after packaging (timeout ~20 min)");
    }

    /**
     * The awards grid does not auto-refresh after a packaging run that revokes an award
     * (e.g. after a document reverts to a non-Satisfied status), so a manual refresh is
     * needed before checking for absence — ported from the reference's waitAndRefresh().
     */
    public void refreshAcademicPlan() {
        page.waitForTimeout(10000);
        click(AcademicPlanLocators.REFRESH_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    // ── Award verification ────────────────────────────────────────────────────

    /**
     * An award can appear once per payment period, so the locator often resolves to multiple
     * rows. isVisible()'s waitFor() enforces Playwright's strict mode (exactly one match) and
     * would silently report "not found" via its catch block whenever more than one row matches
     * — so this checks the first match directly instead.
     */
    public boolean isAwardPresent(String awardName) {
        String selector = String.format(AcademicPlanLocators.AWARD_ROW_BY_NAME, awardName);
        return page.locator(selector).first().isVisible();
    }

    /**
     * Revoking an already-created award (packaging after a document reverts to a non-Satisfied
     * status) is processed asynchronously server-side, so a single refresh isn't reliably enough
     * time for it to disappear from the grid. Polls with periodic refreshes instead of a fixed
     * wait; returns immediately (no extra delay) when the award was never present to begin with.
     */
    public boolean isAwardAbsentEventually(String awardName, int timeoutSeconds) {
        long deadline = System.currentTimeMillis() + timeoutSeconds * 1000L;
        while (true) {
            if (!isAwardPresent(awardName)) return true;
            if (System.currentTimeMillis() >= deadline) return false;
            try {
                page.locator(AcademicPlanLocators.REFRESH_BUTTON).click();
            } catch (Exception ignored) {}
            waitForAjaxRequestToBeFinished();
            page.waitForTimeout(5000);
        }
    }

    /**
     * A manually-added award only stays hidden until the next packaging run, which promotes it
     * straight into the visible awards list — so the link may legitimately not exist by the time
     * this runs. Matches the reference's clickHiddenAwardLink(), which likewise skips silently
     * when not found rather than failing. Multiple identical "Hidden" links can exist (one per
     * payment period), so this uses .first() the same way isAwardPresent() does.
     */
    public void clickFirstHiddenAwardLink() {
        Locator link = page.locator(AcademicPlanLocators.HIDDEN_AWARD_LINK).first();
        if (link.count() == 0 || !link.isVisible()) return;
        link.click();
        waitForAjaxRequestToBeFinished();
    }

    public boolean isPellGrantAwarded()      { return isAwardPresent(AcademicPlanLocators.PELL_GRANT); }
    public boolean isDlSubsidizedAwarded()   { return isAwardPresent(AcademicPlanLocators.DL_SUBSIDIZED); }
    public boolean isDlUnsubsidizedAwarded() { return isAwardPresent(AcademicPlanLocators.DL_UNSUBSIDIZED); }

    // ── Plan-level actions ───────────────────────────────────────────────────

    public void clickProgramManagementButton() {
        click(AcademicPlanLocators.PROGRAM_MANAGEMENT_BTN);
        waitForAjaxRequestToBeFinished();
    }

    public void clickStudentBreaksButton() {
        click(AcademicPlanLocators.STUDENT_BREAKS_BTN);
        waitForAjaxRequestToBeFinished();
    }

    // ── Header ─────────────────────────────────────────────────────────────

    /** Value follows a fixed 13-char label prefix ("Program Name:" etc.), matching the source. */
    public String getHeaderProgramName() {
        return getText(AcademicPlanLocators.HEADER_PROGRAM_NAME).substring(13).trim();
    }

    public String getHeaderProgramStartDate() {
        return getText(AcademicPlanLocators.HEADER_PROGRAM_START_DATE).split(":")[1].trim();
    }

    public String getHeaderItemData(String itemLabel) {
        String selector = String.format(AcademicPlanLocators.HEADER_ITEM, itemLabel);
        return getText(selector).split(":")[1].trim();
    }

    // ── Program Change box ────────────────────────────────────────────────

    public String getProgramChangeProgram(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PROGRAM_CHANGE_PROGRAM_NAME, paymentPeriod)).trim();
    }

    public String getProgramChangeStart(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PROGRAM_CHANGE_START, paymentPeriod)).split(":")[1].trim();
    }

    public String getProgramChangeAnticipatedStart(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PROGRAM_CHANGE_ANTICIPATED_START, paymentPeriod)).split(":")[1].trim();
    }

    public String getProgramChangeCompleted(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PROGRAM_CHANGE_COMPLETED, paymentPeriod)).split(":")[1].trim();
    }

    /** Can legitimately have no ":" (no end date yet), matching the source's length check. */
    public String getProgramChangeEnd(String paymentPeriod) {
        String[] parts = getText(String.format(AcademicPlanLocators.PROGRAM_CHANGE_END, paymentPeriod)).split(":");
        return parts.length > 1 ? parts[1].trim() : "";
    }

    public String getProgramChangeLocation(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PROGRAM_CHANGE_LOCATION, paymentPeriod)).split(":")[1].trim();
    }

    public String getProgramChangeExternalTransfers(String paymentPeriod) {
        String selector = String.format(AcademicPlanLocators.PROGRAM_CHANGE_TRANSFERS, paymentPeriod, "External");
        return getText(selector).split(":")[1].trim();
    }

    public String getProgramChangeInternalTransfers(String paymentPeriod) {
        String selector = String.format(AcademicPlanLocators.PROGRAM_CHANGE_TRANSFERS, paymentPeriod, "Internal");
        return getText(selector).split(":")[1].trim();
    }

    public String getInternalTransfers() {
        return getText(AcademicPlanLocators.INTERNAL_TRANSFERS).replaceAll("Internal Transfers:", "").trim();
    }

    // ── Academic Year stats ──────────────────────────────────────────────────

    public String getAcademicYearDateRange(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_DATE_RANGE, academicYearIndicator));
    }

    public String getAcademicYearAnticipatedUnits(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_ANTICIPATED_UNITS, academicYearIndicator));
    }

    public String getAcademicYearRegisteredUnits(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_REGISTERED_UNITS, academicYearIndicator));
    }

    public String getAcademicYearAttendedUnits(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_ATTENDED_UNITS, academicYearIndicator));
    }

    public String getAcademicYearCensusUnits(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_CENSUS_UNITS, academicYearIndicator));
    }

    /** Column shifts when the "Census" column is absent for non-term programs (app 6.10+). */
    public String getAcademicYearCompletedUnits(String academicYearIndicator) {
        int column = hasCensusColumn(academicYearIndicator) ? 6 : 5;
        String selector = String.format(AcademicPlanLocators.ACADEMIC_YEAR_UNITS_ROW, academicYearIndicator) + "[" + column + "]";
        return getText(selector);
    }

    public String getAcademicYearProgressionUnits(String academicYearIndicator) {
        int column = hasCensusColumn(academicYearIndicator) ? 7 : 6;
        String selector = String.format(AcademicPlanLocators.ACADEMIC_YEAR_UNITS_ROW, academicYearIndicator) + "[" + column + "]";
        return getText(selector);
    }

    public String getAcademicYearAnticipatedWeeks(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_ANTICIPATED_WEEKS, academicYearIndicator));
    }

    public String getAcademicYearRegisteredWeeks(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_REGISTERED_WEEKS, academicYearIndicator));
    }

    public String getAcademicYearAttendedWeeks(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_ATTENDED_WEEKS, academicYearIndicator));
    }

    public String getAcademicYearCensusWeeks(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_CENSUS_WEEKS, academicYearIndicator));
    }

    public String getAcademicYearCompletedWeeks(String academicYearIndicator) {
        int column = hasCensusColumn(academicYearIndicator) ? 6 : 5;
        String selector = String.format(AcademicPlanLocators.ACADEMIC_YEAR_WEEKS_ROW, academicYearIndicator) + "[" + column + "]";
        return getText(selector);
    }

    public String getAcademicYearProgressionWeeks(String academicYearIndicator) {
        int column = hasCensusColumn(academicYearIndicator) ? 7 : 6;
        String selector = String.format(AcademicPlanLocators.ACADEMIC_YEAR_WEEKS_ROW, academicYearIndicator) + "[" + column + "]";
        return getText(selector);
    }

    private boolean hasCensusColumn(String academicYearIndicator) {
        String selector = String.format(AcademicPlanLocators.ACADEMIC_YEAR_WEEKS_UNITS_HEADINGS, academicYearIndicator) + "[5]";
        return getText(selector).equals("Census");
    }

    public String getAcademicYearTotalEstimated(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_TOTAL_ESTIMATED, academicYearIndicator));
    }

    public String getAcademicYearTotalOffered(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_TOTAL_OFFERED, academicYearIndicator));
    }

    public String getAcademicYearTotalAccepted(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_TOTAL_ACCEPTED, academicYearIndicator));
    }

    public String getAcademicYearTotalCOA(String academicYearIndicator) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_TOTAL_COA, academicYearIndicator));
    }

    public int getAcceptedValueForAcademicYear(int yearNumber) {
        String value = page.locator(AcademicPlanLocators.ACADEMIC_YEAR_ACCEPTED_AWARD).nth(yearNumber - 1).textContent();
        value = value.split("\\.")[0].replace("$", "").replace(",", "");
        return Integer.parseInt(value.trim());
    }

    /** Clicks every currently-collapsed Academic Year block open. Best-effort, matches source. */
    public void expandAcademicYearBlocks() {
        try {
            int count = page.locator(AcademicPlanLocators.ACADEMIC_YEAR_BLOCK).count();
            for (int i = 0; i < count; i++) {
                page.locator(AcademicPlanLocators.ACADEMIC_YEAR_BLOCK).nth(i).click();
                waitForAjaxRequestToBeFinished();
            }
        } catch (Exception ignored) {}
    }

    /** Clicks every currently-collapsed Payment Period block open. Best-effort, matches source. */
    public void expandPaymentPeriodBlocks() {
        try {
            Locator blocks = page.locator(AcademicPlanLocators.COLLAPSED_PAYMENT_PERIOD_SECTIONS);
            int count = blocks.count();
            for (int i = 0; i < count; i++) {
                Locator block = blocks.nth(i);
                if (block.isVisible()) {
                    block.click();
                    waitForAjaxRequestToBeFinished();
                }
            }
        } catch (Exception ignored) {}
    }

    public void expandAcademicYearBlock(String academicYear) {
        String selector = String.format(AcademicPlanLocators.ACADEMIC_YEAR_EXPAND_COLLAPSE, academicYear, "plus");
        click(selector);
        waitForAjaxRequestToBeFinished();
    }

    public void collapseAcademicYearBlock(String academicYear) {
        String selector = String.format(AcademicPlanLocators.ACADEMIC_YEAR_EXPAND_COLLAPSE, academicYear, "minus");
        click(selector);
        waitForAjaxRequestToBeFinished();
    }

    // ── Payment Period stats ─────────────────────────────────────────────────

    public String getPaymentPeriodName(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PAYMENT_PERIOD_NAME, paymentPeriod));
    }

    public String getPaymentPeriodStartDate(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PAYMENT_PERIOD_START_DATE, paymentPeriod));
    }

    public String getPaymentPeriodEndDate(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PAYMENT_PERIOD_END_DATE, paymentPeriod));
    }

    public String getPaymentPeriodGradeLevel(String paymentPeriod) {
        return getText(String.format(AcademicPlanLocators.PAYMENT_PERIOD_GRADE_LEVEL, paymentPeriod));
    }

    public String getPaymentPeriodEnrollmentLevel(String paymentPeriod) {
        String selector = String.format(AcademicPlanLocators.PAYMENT_PERIOD_ENROLLMENT_LEVEL, paymentPeriod);
        page.locator(selector).scrollIntoViewIfNeeded();
        return getText(selector);
    }

    public boolean isAutowithdrawnIconDisplayed(String paymentPeriod) {
        return page.locator(String.format(AcademicPlanLocators.AUTOWITHDRAWN_ICON, paymentPeriod)).count() > 0;
    }

    public boolean isModuleIconDisplayed(String paymentPeriod) {
        return page.locator(String.format(AcademicPlanLocators.MODULE_ICON, paymentPeriod)).count() > 0;
    }

    public boolean isHistoricIconDisplayed(String paymentPeriod) {
        return page.locator(String.format(AcademicPlanLocators.HISTORIC_ICON, paymentPeriod)).count() > 0;
    }

    public boolean is180IconDisplayed(String paymentPeriod) {
        return page.locator(String.format(AcademicPlanLocators.I180_ICON, paymentPeriod)).count() > 0;
    }

    /** Polls (refresh + re-check), matching the source's refreshUntilCondition() usage here. */
    public boolean doesPPContainsModularCourses(String paymentPeriod, boolean expectedValue) {
        String selector = String.format(AcademicPlanLocators.TERM_WITH_MODULAR_COURSES, paymentPeriod);
        for (int i = 0; i < 20; i++) {
            if ((page.locator(selector).count() > 0) == expectedValue) return true;
            click(AcademicPlanLocators.REFRESH_BUTTON);
            page.waitForTimeout(1000);
        }
        return false;
    }

    public boolean isNoAwardsInPaymentPeriod(String paymentPeriod) {
        return isVisible(String.format(AcademicPlanLocators.NO_AWARDS_IN_PAYMENT_PERIOD, paymentPeriod));
    }

    /** Polls up to 20 refreshes for the "no awards" message, matching the source's wait behavior. */
    public boolean isNoAwardsForPeriod(String paymentPeriod) {
        String selector = String.format(AcademicPlanLocators.NO_AWARDS_MESSAGE_FOR_PP, paymentPeriod);
        for (int i = 0; i < 20; i++) {
            if (page.locator(selector).count() > 0) return true;
            click(AcademicPlanLocators.REFRESH_BUTTON);
            page.waitForTimeout(1000);
            expandAcademicYearBlocks();
        }
        return false;
    }

    public boolean isStudentNotAwarded() {
        return isVisible(AcademicPlanLocators.NO_AWARDS_MESSAGE);
    }

    public int getNumberOfPaymentPeriods() {
        return page.locator(AcademicPlanLocators.PAYMENT_PERIOD_SECTION_ALL).count();
    }

    // ── Award detail lookups (by term + award name) ─────────────────────────

    public String getAwardAwardYear(String term, String awardName) {
        return getText(String.format(AcademicPlanLocators.AWARD_AWARD_YEAR, term, awardName));
    }

    private int getAwardRowNumber(String term, String awardName, String awardYear) {
        Locator rows = page.locator(String.format(AcademicPlanLocators.AWARD_AWARD_YEAR, term, awardName));
        int count = rows.count();
        for (int i = 0; i < count; i++) {
            if (rows.nth(i).textContent().trim().equals(awardYear)) return i;
        }
        return 0;
    }

    public String getAwardAwardYear(String term, String awardName, String awardYear) {
        int rowNumber = getAwardRowNumber(term, awardName, awardYear);
        return page.locator(String.format(AcademicPlanLocators.AWARD_AWARD_YEAR, term, awardName)).nth(rowNumber).textContent().trim();
    }

    public boolean isAwardInPaymentPeriod(String term, String awardName) {
        try {
            page.locator(String.format(AcademicPlanLocators.LAST_PAYMENT_PERIOD_AWARD, term)).scrollIntoViewIfNeeded();
        } catch (Exception ignored) {}
        return page.locator(String.format(AcademicPlanLocators.AWARD_AWARD_YEAR, term, awardName)).first().isVisible();
    }

    public boolean isManualAwardIconDisplayed(String term, String awardName) {
        return page.locator(String.format(AcademicPlanLocators.MANUAL_AWARD_ICON, term, awardName)).first().isVisible();
    }

    public String getAwardAmount(String term, String awardName) {
        return getText(String.format(AcademicPlanLocators.AWARD_AMOUNT, term, awardName)).replaceAll("[$,]", "");
    }

    public String getAwardDollarAmount(String term, String awardName) {
        Locator lastAward = page.locator(String.format(AcademicPlanLocators.LAST_PAYMENT_PERIOD_AWARD, term));
        if (lastAward.count() > 0) lastAward.scrollIntoViewIfNeeded();
        return getText(String.format(AcademicPlanLocators.AWARD_AMOUNT, term, awardName));
    }

    public String getAwardDollarAmount(String term, String awardName, String awardYear) {
        Locator lastAward = page.locator(String.format(AcademicPlanLocators.LAST_PAYMENT_PERIOD_AWARD, term));
        if (lastAward.count() > 0) lastAward.scrollIntoViewIfNeeded();
        int rowNumber = getAwardRowNumber(term, awardName, awardYear);
        return page.locator(String.format(AcademicPlanLocators.AWARD_AMOUNT, term, awardName)).nth(rowNumber).textContent().trim();
    }

    public String getAwardStatus(String term, String awardName) {
        return getText(String.format(AcademicPlanLocators.AWARD_STATUS, term, awardName));
    }

    public String getAwardStatus(String term, String awardName, String awardYear) {
        int rowNumber = getAwardRowNumber(term, awardName, awardYear);
        return page.locator(String.format(AcademicPlanLocators.AWARD_STATUS, term, awardName)).nth(rowNumber).textContent().trim();
    }

    public String getAwardPaidAmount(String term, String awardName) {
        return getText(String.format(AcademicPlanLocators.AWARD_PAID_AMOUNT, term, awardName)).replaceAll("[$,]", "");
    }

    public String getAwardPaidDollarAmount(String term, String awardName) {
        return getText(String.format(AcademicPlanLocators.AWARD_PAID_AMOUNT, term, awardName));
    }

    public String getAwardISIRTransNum(String term, String awardName) {
        return getText(String.format(AcademicPlanLocators.AWARD_ISIR_TRANS_NUM, term, awardName));
    }

    /** Reveals hidden awards for a specific term (source's clickHiddenAwardLink(String)). */
    public void clickHiddenAwardLink(String term) {
        Locator link = page.locator(String.format(AcademicPlanLocators.HIDDEN_AWARD_LINK_FOR_TERM, term));
        if (link.isVisible()) {
            link.click();
        }
        Locator lastAward = page.locator(String.format(AcademicPlanLocators.LAST_AWARD_IN_PAYMENT_PERIOD, term));
        if (lastAward.isVisible()) {
            lastAward.scrollIntoViewIfNeeded();
        }
    }

    // ── Academic Year / term lookups ─────────────────────────────────────────

    public String getAcademicYear(String targetTerm) {
        return getText(String.format(AcademicPlanLocators.ACADEMIC_YEAR_CONTAINER, targetTerm));
    }

    public boolean isTermInAcademicYear(int academicYearIndicator, String term) {
        String academicYearLabel = "Academic Year " + academicYearIndicator;
        String selector = String.format(AcademicPlanLocators.TERM_IN_ACADEMIC_YEAR, academicYearLabel, term);
        return page.locator(selector).first().isVisible();
    }

    // ── Student breaks ───────────────────────────────────────────────────────

    /**
     * Compares one break record's Start Date / End Date / Break Type columns (4 columns per
     * record) against expected values. Returns false (rather than failing) on first mismatch —
     * callers decide how to assert, matching this project's page-object/step-definition split.
     */
    public boolean isBreakAMatch(String academicYear, int breakRecord, String expStartDate, String expEndDate, String expBreakType) {
        Locator cols = page.locator(String.format(AcademicPlanLocators.BREAK_COLUMNS, academicYear));
        int colOffset = (breakRecord - 1) * 4;
        String actualStart = cols.nth(1 + colOffset).textContent().split(":")[1].trim();
        String actualEnd = cols.nth(2 + colOffset).textContent().split(":")[1].trim();
        String actualBreakType = cols.nth(3 + colOffset).textContent().split(":")[1].trim();
        return expStartDate.equals(actualStart) && expEndDate.equals(actualEnd) && expBreakType.equals(actualBreakType);
    }

    public void waitForBreakToAppear(String startDate) {
        String selector = String.format(AcademicPlanLocators.STUDENT_BREAK, startDate);
        for (int i = 0; i <= 15; i++) {
            if (page.locator(selector).count() > 0) return;
            click(AcademicPlanLocators.REFRESH_BUTTON);
            page.waitForTimeout(1000);
        }
        Assert.fail("Student Break did not appear");
    }
}
