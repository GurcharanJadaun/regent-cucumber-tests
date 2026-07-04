package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.LoansLocators;

/** Ported from regent.pages.student.LoansPage. */
public class LoansPage extends BasePage {

    public LoansPage(Page page) {
        super(page);
    }

    public void selectMpn() {
        click(LoansLocators.MPN_TAB);
        click(LoansLocators.SELECT_MPN_BUTTON);
    }

    public void clickSelectMpn() {
        click(LoansLocators.SELECT_MPN_BUTTON);
    }

    public void clickMpnTab() {
        click(LoansLocators.MPN_TAB);
    }

    public void selectLoanFromGrid(String loanType) {
        click(String.format(LoansLocators.LOAN_GRID_ROW, loanType));
    }

    public String getLoanId(String loanType) {
        return getText(String.format(LoansLocators.LOAN_GRID_CELL, loanType, 1));
    }

    public boolean isCodImportedInLoan() {
        return getText(LoansLocators.DIRECT_SUB_LOAN_STATUS).equals("Imported");
    }

    public double getAcceptedAmountBySubsidizedLoan() {
        return parseAmount(getText(LoansLocators.ACCEPTED_AMOUNT));
    }

    public double getPaidAmountBySubsidizedLoan() {
        return parseAmount(getText(LoansLocators.PAID_AMOUNT));
    }

    private double parseAmount(String amount) {
        return Double.parseDouble(amount.replace(",", "").replace("$", ""));
    }

    public String getGridLoanStatus(String rowKey) {
        return getText(String.format(LoansLocators.LOAN_GRID_CELL, rowKey, 6));
    }

    public String getGridLoanEligibleAmount(String rowKey) {
        return getText(String.format(LoansLocators.LOAN_GRID_CELL, rowKey, 7));
    }

    public String getGridLoanAcceptedAmount(String rowKey) {
        return getText(String.format(LoansLocators.LOAN_GRID_CELL, rowKey, 8));
    }

    public String getFinancialAwardBeginDate() {
        return getText(String.format(LoansLocators.GENERAL_DETAILS_FIELD, "Loan_LoanPeriodStartDate"));
    }

    public String getFinancialAwardEndDate() {
        return getText(String.format(LoansLocators.GENERAL_DETAILS_FIELD, "Loan_LoanPeriodEndDate"));
    }

    public String getAcademicYearBeginDate() {
        return getText(String.format(LoansLocators.GENERAL_DETAILS_FIELD, "Loan_academicYearBeginDate"));
    }

    public String getAcademicYearEndDate() {
        return getText(String.format(LoansLocators.GENERAL_DETAILS_FIELD, "Loan_academicYearEndDate"));
    }

    // ── PLUS Borrower Information Tab ────────────────────────────────────────

    public void clickPlusBorrowerTab() {
        click(LoansLocators.PLUS_BORROWER_INFO_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickEditBorrowerInfo() {
        click(LoansLocators.EDIT_BORROWER_INFO_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickSaveBorrowerInfo() {
        click(LoansLocators.SAVE_BORROWER_INFO_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void setBorrowerFirstName(String firstName) {
        fill(LoansLocators.INPUT_BORROWER_FIRST_NAME, firstName);
    }

    public void setBorrowerMiddleName(String middleName) {
        fill(LoansLocators.INPUT_BORROWER_MIDDLE_NAME, middleName);
    }

    public void setBorrowerLastName(String lastName) {
        fill(LoansLocators.INPUT_BORROWER_LAST_NAME, lastName);
    }

    public void setBorrowerSuffix(String suffix) {
        fill(LoansLocators.INPUT_BORROWER_SUFFIX, suffix);
    }

    public void setBorrowerDOB(String dob) {
        fill(LoansLocators.INPUT_BORROWER_DOB, dob);
    }

    public void setBorrowerSSN(String ssn) {
        fill(LoansLocators.INPUT_BORROWER_SSN, ssn);
    }

    public void setBorrowerAddress(String address) {
        fill(LoansLocators.INPUT_BORROWER_ADDRESS, address);
    }

    public void setBorrowerCity(String city) {
        fill(LoansLocators.INPUT_BORROWER_CITY, city);
    }

    public void setBorrowerPostalCode(String postalCode) {
        fill(LoansLocators.INPUT_BORROWER_POSTAL_CODE, postalCode);
    }

    public void setBorrowerEmail(String emailAddress) {
        fill(LoansLocators.INPUT_BORROWER_EMAIL, emailAddress);
    }

    public void setBorrowerPhone(String phoneNumber) {
        fill(LoansLocators.INPUT_BORROWER_PHONE, phoneNumber);
    }

    public void setBorrowerCitizenship(String citizenship) {
        click(LoansLocators.CITIZENSHIP_STATUS_SELECT);
        waitForAjaxRequestToBeFinished();
        click(String.format(LoansLocators.CITIZENSHIP_STATUS_OPTION, citizenship));
    }

    public void setBorrowerDefaultOverpay(String defaultCode) {
        click(LoansLocators.DEFAULT_OVERPAY_SELECT);
        waitForAjaxRequestToBeFinished();
        click(String.format(LoansLocators.DEFAULT_OVERPAY_OPTION, defaultCode));
    }

    public void setBorrowerState(String state) {
        click(LoansLocators.STATE_SELECT);
        waitForAjaxRequestToBeFinished();
        click(String.format(LoansLocators.STATE_OPTION, state));
    }
}
