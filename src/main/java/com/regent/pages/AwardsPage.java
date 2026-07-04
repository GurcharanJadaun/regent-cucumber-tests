package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AwardsLocators;

/** Ported from regent.pages.student.AwardsPage. */
public class AwardsPage extends BasePage {

    /** Set once expandAllAwards() finds the 7.0+ per-award expand/collapse icons. */
    private boolean isAfter0613 = false;

    public AwardsPage(Page page) {
        super(page);
    }

    public void clickAddAward() {
        click(AwardsLocators.ADD_AWARD_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickAdjustCostButton() {
        click(AwardsLocators.ADJUST_COST_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickAdjustResourceButton() {
        click(AwardsLocators.ADJUST_RESOURCE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickCalculateSapButton() {
        click(AwardsLocators.CALCULATE_SAP_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickPerformR2t4() {
        click(AwardsLocators.PERFORM_R2T4_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickRefresh() {
        click(AwardsLocators.REFRESH_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void expandAwards() {
        click(AwardsLocators.AWARDS_DETAILS_TOGGLE);
        expandAllAwards();
    }

    /** 7.0+ requires expanding each award's own expand/collapse icon individually. */
    private void expandAllAwards() {
        String plusIcon = String.format(AwardsLocators.COMPARE_AWARDS_AWARD_ROW_EXPAND_COLLAPSE, "", "plus");
        int count = page.locator(plusIcon).count();
        if (count > 0) {
            isAfter0613 = true;
            for (int i = 0; i < count; i++) {
                page.locator(plusIcon).nth(0).click();
                page.waitForTimeout(1000);
            }
        }
    }

    public void modifyAward(String awardName) {
        click(String.format(AwardsLocators.MODIFY_AWARD_LINK, awardName));
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnPaymentPeriodFromList(String paymentPeriod) {
        clearAnalyzeLoanPeriod();
        click(String.format(AwardsLocators.PAYMENT_PERIOD_IN_LIST, paymentPeriod));
    }

    public boolean isModuleIconInAwardSummary(String paymentPeriod) {
        return isVisible(String.format(AwardsLocators.MODULAR_ICON_PAYMENT_PERIOD, paymentPeriod));
    }

    public String getLastPaymentPeriodYears(String paymentPeriod) {
        return getText(String.format(AwardsLocators.LAST_PERIOD_IN_LIST_YEAR, paymentPeriod));
    }

    public boolean clickAwardSummaryAndGetAlternativeLoan() {
        click(AwardsLocators.AWARD_SUMMARY_TOGGLE);
        return isVisible(AwardsLocators.ALTERNATIVE_LOAN_SUMMARY_AWARD);
    }

    public String getAwardSummaryGridCellText(String gridTitle, String rowKey) {
        String columnNumber = page.locator(String.format(AwardsLocators.AWARD_SUMMARY_GRID_TITLE, gridTitle))
                .getAttribute("data-index");
        String cellSelector = String.format(AwardsLocators.AWARD_SUMMARY_GRID_ROW_CELLS, rowKey);
        return page.locator(cellSelector).nth(Integer.parseInt(columnNumber)).textContent().trim();
    }

    public boolean isAwardSummaryGridSapPpBasedPolicy(String rowKey) {
        return isVisible(String.format(AwardsLocators.AWARD_SUMMARY_GRID_SAP_PP_BASED, rowKey));
    }

    public boolean isAwardSummaryGridSapAyBasedPolicy(String rowKey) {
        return isVisible(String.format(AwardsLocators.AWARD_SUMMARY_GRID_SAP_AY_BASED, rowKey));
    }

    public void filterSummaryOnPaymentPd(String paymentPeriod) {
        click(String.format(AwardsLocators.AWARD_SUMMARY_GRID_FILTER, "Payment Pd"));
        fill(AwardsLocators.AWARD_FILTER_INPUT, paymentPeriod);
        click(AwardsLocators.AWARD_FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public String getCoaForPaymentPeriod() {
        click(AwardsLocators.COA_SUMMARY_TOGGLE);
        waitForAjaxRequestToBeFinished();
        return getText(AwardsLocators.TUITION_AND_FEES_FOR_PP);
    }

    public String getCOAForPaymentPeriod(String pp) {
        return getText(String.format(AwardsLocators.COA_FOR_PP, pp));
    }

    public String getAwardsForPaymentPeriod(String pp) {
        return getText(String.format(AwardsLocators.TOTAL_AWARDS_FOR_PP, pp));
    }

    /** Also used for Direct Cost. */
    public String getTuitionAndFeesTotal() {
        click(AwardsLocators.COA_SUMMARY_TOGGLE);
        waitForAjaxRequestToBeFinished();
        return getText(AwardsLocators.TUITION_AND_FEES_TOTAL);
    }

    private boolean isLoanPeriodSelected(String loanPeriod) {
        click(AwardsLocators.ANALYZE_LOAN_PERIOD_SELECT);
        boolean selected = getText(AwardsLocators.LOAN_PERIOD_OPTION_SELECTED).contains(loanPeriod);
        click(AwardsLocators.ANALYZE_LOAN_PERIOD_SELECT); // close dropdown
        return selected;
    }

    /** 7.0+ defaults Analyze Loan Period to a period, so it must be cleared before re-selecting. */
    private void clearAnalyzeLoanPeriod() {
        String loanPeriod = "Select Loan Period";
        if (!isLoanPeriodSelected(loanPeriod)) {
            click(AwardsLocators.ANALYZE_LOAN_PERIOD_SELECT);
            click(String.format(AwardsLocators.LOAN_PERIOD_OPTION, loanPeriod));
            waitForAjaxRequestToBeFinished();
        }
    }

    public void selectAnalyzeLoanPeriod(String loanPeriod) {
        if (!isLoanPeriodSelected(loanPeriod)) {
            clearAnalyzeLoanPeriod();
            click(AwardsLocators.ANALYZE_LOAN_PERIOD_SELECT);
            String optionSelector = String.format(AwardsLocators.LOAN_PERIOD_OPTION_CONTAINS, loanPeriod);
            if (page.locator(optionSelector).count() > 0) {
                click(AwardsLocators.LOAN_PERIOD_OPTION.replace("%s", loanPeriod));
            }
            waitForAjaxRequestToBeFinished();
        }
    }

    // ── Compare Awards ──────────────────────────────────────────────────────

    public String getEnrollmentPeriodsProgram(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Program", enrolledPeriodIndex);
    }

    public String getEnrollmentPeriodsGradeLevel(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Grade Level", enrolledPeriodIndex);
    }

    public String getEnrollmentPeriodsPaymentPeriodFAY(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Payment Period Federal", enrolledPeriodIndex);
    }

    public String getEnrollmentPeriodsAcademicYearFAY(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Academic Year Federal", enrolledPeriodIndex);
    }

    public String getEnrollmentPeriodsAcademicYear(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Academic Year:", enrolledPeriodIndex);
    }

    public String getAwardNameByIndex(int index) {
        return page.locator(AwardsLocators.AWARDS_NAMES).nth(index - 1).textContent().trim().replace(":", "");
    }

    public void expandEnrollmentDetails() {
        click(String.format(AwardsLocators.COMPARE_AWARDS_SECTION_HEADERS, "Enrollment Details"));
    }

    public String getAnticipatedUnits(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Anticipated Units", enrolledPeriodIndex);
    }

    public String getRegisteredUnits(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Registered Units", enrolledPeriodIndex);
    }

    public String getCensusUnits(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Census Units", enrolledPeriodIndex);
    }

    public String getCensusDate(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Census Date", enrolledPeriodIndex);
    }

    public String getEarnedUnits(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Earned Units", enrolledPeriodIndex);
    }

    public String getR2T4Status(int enrolledPeriodIndex) {
        return getSpecificDetailItem("R2T4 Status", enrolledPeriodIndex);
    }

    public String getCurrentSAPStatus(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Current SAP Status", enrolledPeriodIndex);
    }

    public boolean isCurrentSAPStatusPP(String enrolledPeriodColumnNum) {
        return isVisible(String.format(AwardsLocators.DETAIL_CURRENT_SAP_STATUS_ICON, enrolledPeriodColumnNum, "PP"));
    }

    public boolean isCurrentSAPStatusAY(String enrolledPeriodColumnNum) {
        return isVisible(String.format(AwardsLocators.DETAIL_CURRENT_SAP_STATUS_ICON, enrolledPeriodColumnNum, "AY"));
    }

    public String getPreviousSAPStatus(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Previous Period SAP Status", enrolledPeriodIndex);
    }

    public String getSAPAppealStatus(int enrolledPeriodIndex) {
        return getSpecificDetailItem("SAP Appeal Status", enrolledPeriodIndex);
    }

    // ── Cost of Attendance ──────────────────────────────────────────────────

    public String getCostOfAttendanceHeader(int enrolledPeriodIndex) {
        String selector = String.format(AwardsLocators.COMPARE_AWARDS_HEADER_ROW_COLUMNS, "Cost of Attendance");
        return page.locator(selector).nth(enrolledPeriodIndex).textContent().trim();
    }

    public void expandCostOfAttendance() {
        String selector = String.format(AwardsLocators.COMPARE_AWARDS_HEADER_ROW_COLUMNS, "Cost of Attendance");
        page.locator(selector).first().click();
        waitForAjaxRequestToBeFinished();
    }

    public String getTuitionAndFees(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Tuition and Fees", enrolledPeriodIndex);
    }

    /** Label changed from "Room and Board" to "Food and Housing" in 6.7. */
    public String getRoomAndBoard(int enrolledPeriodIndex) {
        return getFoodAndHousing(enrolledPeriodIndex);
    }

    public String getFoodAndHousing(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Food and Housing", enrolledPeriodIndex);
    }

    /** Label changed from "Books and Supplies" to "Books, Course Materials, Supplies, and Equipment" in 6.7. */
    public String getBooksAndSupplies(int enrolledPeriodIndex) {
        return getBooksCourseMaterialSupplies(enrolledPeriodIndex);
    }

    public String getBooksCourseMaterialSupplies(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Books, Course Materials", enrolledPeriodIndex);
    }

    public String getPersonalMisc(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Personal/Misc", enrolledPeriodIndex);
    }

    public String getTransportation(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Transportation", enrolledPeriodIndex);
    }

    public String getDependentCare(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Dependent Care", enrolledPeriodIndex);
    }

    public String getStudyAbroad(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Study Abroad", enrolledPeriodIndex);
    }

    public String getDisabilityExpenses(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Disability Expenses", enrolledPeriodIndex);
    }

    public String getEmploymentExpenses(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Employment Expenses", enrolledPeriodIndex);
    }

    public String getLoanFees(int enrolledPeriodIndex) {
        return getSpecificDetailItem("Loan Fees", enrolledPeriodIndex);
    }

    public void expandAllCostOfAttendanceSubSections() {
        int count = page.locator(AwardsLocators.COST_OF_ATTENDANCE_EXPANDABLE_SUBSECTIONS).count();
        for (int i = 0; i < count; i++) {
            page.locator(AwardsLocators.COST_OF_ATTENDANCE_EXPANDABLE_SUBSECTIONS).nth(0).click();
            page.waitForTimeout(1000);
        }
    }

    public String getSpecificDetailItem(String itemName, int enrolledPeriodIndex) {
        String selector = String.format(AwardsLocators.DETAIL_ROW_COLUMNS, itemName);
        return page.locator(selector).nth(enrolledPeriodIndex - 1).textContent().trim();
    }

    // ── Non-expandable header data ───────────────────────────────────────────

    public String getPellCOA(int enrolledPeriodIndex) {
        return getHeaderRowColumn("Pell COA", enrolledPeriodIndex);
    }

    public String getCensusCOA(int enrolledPeriodIndex) {
        return getHeaderRowColumn("Census COA", enrolledPeriodIndex);
    }

    public String getUnmetNeed(int enrolledPeriodIndex) {
        return getHeaderRowColumn("Unmet Need", enrolledPeriodIndex);
    }

    public String getCensusUnmetNeed(int enrolledPeriodIndex) {
        return getHeaderRowColumn("Census Unmet Need", enrolledPeriodIndex);
    }

    public String getUnmetCost(int enrolledPeriodIndex) {
        return getHeaderRowColumn("Unmet Cost", enrolledPeriodIndex);
    }

    public String getCensusUnmetCost(int enrolledPeriodIndex) {
        return getHeaderRowColumn("Census Unmet Cost", enrolledPeriodIndex);
    }

    public String getResources(int enrolledPeriodIndex) {
        return getHeaderRowColumn("Resources", enrolledPeriodIndex);
    }

    private String getHeaderRowColumn(String label, int enrolledPeriodIndex) {
        String selector = String.format(AwardsLocators.COMPARE_AWARDS_HEADER_ROW_COLUMNS, label);
        return page.locator(selector).nth(enrolledPeriodIndex).textContent().trim();
    }

    public String getSelectedEnrollmentPds(int enrolledPeriodIndex) {
        return page.locator(AwardsLocators.SELECTED_ENROLLMENT_PERIODS).nth(enrolledPeriodIndex).textContent().trim();
    }

    public boolean isAutowithdrawIconDisplayed(String paymentPeriod) {
        return isVisible(String.format(AwardsLocators.SELECTED_ENROLLMENT_PERIODS_AW_ICON, paymentPeriod));
    }

    public boolean isModuleIconInCompareAwards(String paymentPeriod) {
        return isVisible(String.format(AwardsLocators.SELECTED_ENROLLMENT_PERIODS_MOD_ICON, paymentPeriod));
    }

    /** Label changed from "FM Contribution" to "Student Aid Index Details" in 6.7. */
    public void expandFMContributionDetails() {
        click(String.format(AwardsLocators.COMPARE_AWARDS_SECTION_HEADERS, "Student Aid Index Details"));
        waitForAjaxRequestToBeFinished();
    }

    public void expandResources() {
        waitForAjaxRequestToBeFinished();
        click(AwardsLocators.RESOURCES_EXPAND_SECTION);
        waitForAjaxRequestToBeFinished();
    }

    public String getCompareAwardsDetailColumnData(String awardName, String rowLabel, int columnIndex) {
        String selector = isAfter0613
                ? String.format(AwardsLocators.COMPARE_AWARDS_AWARD_DETAILS_ROW2, awardName, rowLabel)
                : String.format(AwardsLocators.COMPARE_AWARDS_AWARD_DETAILS_ROW, awardName, rowLabel);
        return page.locator(selector).nth(columnIndex - 1).textContent().trim();
    }

    public String getAwardDetailsLinkAmount(String awardName, int columnNumber) {
        String selector = String.format(AwardsLocators.AWARD_LINK_AMOUNTS, awardName);
        return page.locator(selector).nth(columnNumber).textContent().trim();
    }

    public String getAwardDetailsLinkTotalAmount(String awardName) {
        return getText(String.format(AwardsLocators.AWARD_LINKS_TOTAL_AMOUNT, awardName));
    }

    /** Returns true once the Cancel button click has dismissed itself (element no longer present). */
    public boolean clickCancelButton() {
        click(AwardsLocators.CANCEL_BUTTON);
        return page.locator(AwardsLocators.CANCEL_BUTTON).count() == 0;
    }

    /**
     * Adds a dummy cost adjustment (SEI tests). Ported from addCostAdjustment(); the source's
     * Selenium Tab-key traversal and raw JS-set notes field are replaced with a direct fill.
     */
    public boolean addCostAdjustment(int amount) {
        click(AwardsLocators.ADJUST_COST_BUTTON);
        click(AwardsLocators.ADD_ADJUSTMENT_BUTTON);
        fill(AwardsLocators.COST_ADJUSTMENT_NAME, "Dummy Adjustment");
        waitForAjaxRequestToBeFinished();
        fill(AwardsLocators.ADJUSTMENT_AMOUNT, String.valueOf(amount));
        fill(AwardsLocators.ADJUSTMENT_NOTES, "notes");
        waitForAjaxRequestToBeFinished();
        click(AwardsLocators.ADD_BUTTON_ROW1);
        click(AwardsLocators.ADD_BUTTON_ROW2);
        click(AwardsLocators.SAVE_BUTTON);
        click(AwardsLocators.CLOSE_BUTTON);
        return page.locator(AwardsLocators.ADJUSTMENT_AMOUNT).count() == 0;
    }
}
