package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ExportEstLocators;

import java.util.List;

/** Ported from regent.pages.ExportEstPage — the Export EST (Export Student Transactions) wizard. */
public class ExportEstPage extends BasePage {

    public ExportEstPage(Page page) {
        super(page);
    }

    public boolean isEstPageHeaderVisible() {
        return isVisible(ExportEstLocators.EST_PAGE_HEADER);
    }

    public ExportEstPage setExportType(String estExportType) {
        click(ExportEstLocators.EST_TYPE_SELECT);
        click(String.format(ExportEstLocators.EST_TYPE_OPTION, estExportType));
        waitForAjaxRequestToBeFinished();
        return this;
    }

    public ExportEstPage setTransactionType(String transactionType) {
        click(ExportEstLocators.TRANSACTION_TYPE_SELECT);
        click(String.format(ExportEstLocators.TRANSACTION_TYPE_OPTION, transactionType));
        waitForAjaxRequestToBeFinished();
        return this;
    }

    public ExportEstPage setPaymentPeriodDates(String fromDate, String toDate) {
        fill(ExportEstLocators.PAYMENT_PERIOD_START, fromDate);
        fill(ExportEstLocators.PAYMENT_PERIOD_END, toDate);
        return this;
    }

    public ExportEstPage setAllFAYs() {
        click(ExportEstLocators.ALL_FAYS_CHECKBOX);
        return this;
    }

    public ExportEstPage setFAY(String fay) {
        click(String.format(ExportEstLocators.FAY_GRID_ITEM, fay));
        return this;
    }

    public ExportEstPage setAllEnterprises() {
        click(ExportEstLocators.ALL_ENTERPRISES_CHECKBOX);
        return this;
    }

    public ExportEstPage setEnterprise(String enterpriseName) {
        click(String.format(ExportEstLocators.ENTERPRISE_GRID_ITEM, enterpriseName));
        return this;
    }

    public ExportEstPage setAllInstitutions() {
        click(ExportEstLocators.ALL_INSTITUTIONS_CHECKBOX);
        return this;
    }

    public ExportEstPage setInstitution(String institutionName) {
        click(String.format(ExportEstLocators.INSTITUTION_GRID_ITEM, institutionName));
        return this;
    }

    public ExportEstPage setAllCampuses() {
        click(ExportEstLocators.ALL_CAMPUSES_CHECKBOX);
        return this;
    }

    public ExportEstPage setAllSites() {
        click(ExportEstLocators.ALL_SITES_CHECKBOX);
        return this;
    }

    /** Filters the campus grid first when the target campus isn't already showing. */
    public ExportEstPage setCampus(String campus) {
        String campusSelector = String.format(ExportEstLocators.CAMPUS_GRID_ITEM, campus);
        if (!isVisibleNow(campusSelector)) {
            click(String.format(ExportEstLocators.GRID_FILTER_BUTTON, "LocationsGrid", "Available Campuses"));
            waitForAjaxRequestToBeFinished();
            fill(ExportEstLocators.FILTER_INPUT, campus);
            click(ExportEstLocators.FILTER_SUBMIT);
            waitForAjaxRequestToBeFinished();
        }
        click(campusSelector);
        waitForAjaxRequestToBeFinished();
        return this;
    }

    public ExportEstPage setSite(String site) {
        click(String.format(ExportEstLocators.SITES_GRID_ITEM, site));
        return this;
    }

    /** Filters and selects the given student in the students grid. */
    public ExportEstPage setStudent(String externalStudentId, String lastName) {
        click(ExportEstLocators.STUDENTS_FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
        fill(ExportEstLocators.FILTER_INPUT, externalStudentId);
        click(ExportEstLocators.FILTER_SUBMIT);
        waitForAjaxRequestToBeFinished();
        click(String.format(ExportEstLocators.STUDENT_ROW, lastName));
        waitForAjaxRequestToBeFinished();
        return this;
    }

    public ExportEstPage setAllTerms() {
        click(ExportEstLocators.ALL_TERMS_CHECKBOX);
        return this;
    }

    public ExportEstPage setTerm(String termName) {
        click(String.format(ExportEstLocators.TERM_ITEM, termName));
        return this;
    }

    public ExportEstPage setAllProgramTypes() {
        click(ExportEstLocators.ALL_PROGRAM_TYPES_CHECKBOX);
        return this;
    }

    public ExportEstPage setAllPrograms() {
        click(ExportEstLocators.ALL_PROGRAMS_CHECKBOX);
        return this;
    }

    public ExportEstPage setAllFunds() {
        click(ExportEstLocators.ALL_FUNDS_CHECKBOX);
        return this;
    }

    /** Selects a single fund/loan by name, filtering the funds grid first if it isn't already showing. */
    public ExportEstPage setFund(String fundName) {
        String loanSelector = String.format(ExportEstLocators.LOAN_GRID_ITEM, fundName);
        if (!isVisibleNow(loanSelector)) {
            click(ExportEstLocators.FUND_FILTER);
            fill(ExportEstLocators.FILTER_INPUT, fundName);
            click(ExportEstLocators.FILTER_SUBMIT);
        }
        click(loanSelector);
        return this;
    }

    public ExportEstPage setFunds(List<String> fundList) {
        for (String fundToExport : fundList) {
            setFund(fundToExport);
        }
        return this;
    }

    public void clickExport() {
        click(ExportEstLocators.EXPORT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public ExportEstPage selectStudentBasedOnDisbursements() {
        click(ExportEstLocators.SITE_FROM_DISBURSEMENT_RADIO);
        return this;
    }

    public ExportEstPage setOffsetDays(String offsetDays) {
        fill(ExportEstLocators.INPUT_OFFSET_DAYS, offsetDays);
        return this;
    }

    public ExportEstPage selectTurnOffEligibilityChecks(boolean check) {
        setCheckbox(ExportEstLocators.OVERRIDE_ELIGIBILITY_CHECKBOX, check);
        return this;
    }

    public ExportEstPage selectTurnOffDocumentChecks(boolean check) {
        setCheckbox(ExportEstLocators.OVERRIDE_DOCUMENT_REQ_CHECKBOX, check);
        return this;
    }

    public ExportEstPage selectTurnOffSapChecks(boolean check) {
        setCheckbox(ExportEstLocators.OVERRIDE_SAP_CHECKBOX, check);
        return this;
    }

    public ExportEstPage selectTurnOffCodChecks(boolean check) {
        setCheckbox(ExportEstLocators.OVERRIDE_COD_CHECKBOX, check);
        return this;
    }

    public ExportEstPage selectInclude0Disbursement(boolean check) {
        setCheckbox(ExportEstLocators.INCLUDE_0_DISBURSEMENTS_CHECKBOX, check);
        return this;
    }

    public ExportEstPage selectIncludeNonDisbursableFunds(boolean check) {
        setCheckbox(ExportEstLocators.INCLUDE_NON_DISBURSABLE_FUNDS_CHECKBOX, check);
        return this;
    }

    public ExportEstPage selectInclude0DisbursableNoValidation(boolean check) {
        setCheckbox(ExportEstLocators.INCLUDE_0_DISB_NO_VALIDATION_CHECKBOX, check);
        return this;
    }

    public ExportEstPage selectIncludeUnfundedAwards(boolean check) {
        setCheckbox(ExportEstLocators.INCLUDE_UNFUNDED_AWARDS_CHECKBOX, check);
        return this;
    }

    public ExportEstPage selectIncludeOfferedAwards(boolean check) {
        setCheckbox(ExportEstLocators.INCLUDE_OFFERED_AWARDS_CHECKBOX, check);
        return this;
    }

    private void setCheckbox(String selector, boolean check) {
        if (check) {
            page.locator(selector).check();
        } else {
            page.locator(selector).uncheck();
        }
    }
}
