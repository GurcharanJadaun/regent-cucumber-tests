package com.regent.locators;

/** Ported from regent.pages.student.LoansPage. */
public interface LoansLocators {
    String DIRECT_SUB_LOAN_STATUS = "//div[@data-rem-widgetname='LoanGrid']//tr[contains(string(),'Direct Subsidized Loan')]/td[11]";
    String PAID_AMOUNT            = "//div[@data-rem-widgetname='LoanGrid']//tr[contains(string(),'Direct Subsidized Loan')]/td[9]";
    String ACCEPTED_AMOUNT        = "//div[@data-rem-widgetname='LoanGrid']//tr[contains(string(),'Direct Subsidized Loan')]/td[8]";
    String LOAN_GRID_ROW          = "//div[@data-rem-widgetname='LoanGrid']//tr[contains(string(),'%s')]";
    String LOAN_GRID_CELL         = "//div[@data-rem-widgetname='LoanGrid']//tr[contains(string(),'%s')]/td[%s]";

    String MPN_TAB          = "//li/span/span[contains(string(),'MPN Information')]";
    String SELECT_MPN_BUTTON = "//button[contains(string(),'Select MPN')]";

    // General Tab
    String AWARD_GENERAL_TAB   = "//li/span/span[contains(string(),'General')]";
    String GENERAL_DETAILS_FIELD = "//div[@class='details-label' and label[@for='%s']]/following-sibling::div";

    // PLUS Borrower Information Tab
    String PLUS_BORROWER_INFO_TAB   = "//li/span/span[contains(string(),'PLUS Borrower Information')]";
    String EDIT_BORROWER_INFO_BUTTON = "//button[@rem-trigger-event='editBorrowerClick']";
    String SAVE_BORROWER_INFO_BUTTON = "//div[@data-rem-widgetname='LoanBorrowerEdit']//button[@type='submit']";

    String INPUT_BORROWER_FIRST_NAME  = "#Loan_borrowerFirstName";
    String INPUT_BORROWER_MIDDLE_NAME = "#Loan_borrowerMiddleName";
    String INPUT_BORROWER_LAST_NAME   = "#Loan_borrowerLastName";
    String INPUT_BORROWER_SUFFIX      = "#Loan_borrowerSuffix";
    String INPUT_BORROWER_DOB         = "#Loan_borrowerDateOfBirth";
    String INPUT_BORROWER_SSN         = "#Loan_borrowerSocialSecurityNumber";
    String INPUT_BORROWER_ADDRESS     = "#Loan_borrowerAddress";
    String INPUT_BORROWER_CITY        = "#Loan_borrowerCity";
    String INPUT_BORROWER_POSTAL_CODE = "#Loan_borrowerPostalCode";
    String INPUT_BORROWER_EMAIL       = "#Loan_borrowerEmail";
    String INPUT_BORROWER_PHONE       = "#Loan_borrowerPhone";

    // Kendo dropdowns — stable aria-owns/listbox pattern per translation rule 2
    String CITIZENSHIP_STATUS_SELECT = "span[aria-owns='Loan_borrowerCitizenshipStatusCode_listbox']";
    String CITIZENSHIP_STATUS_OPTION = "//ul[@id='Loan_borrowerCitizenshipStatusCode_listbox']/li[contains(string(),'%s')]";
    String DEFAULT_OVERPAY_SELECT    = "span[aria-owns='Loan_BorrowerDefaultOverpayCodeCode_listbox']";
    String DEFAULT_OVERPAY_OPTION    = "//ul[@id='Loan_BorrowerDefaultOverpayCodeCode_listbox']/li[contains(string(),'%s')]";
    String STATE_SELECT              = "span[aria-owns='Loan_borrowerStateCode_listbox']";
    String STATE_OPTION              = "//ul[@id='Loan_borrowerStateCode_listbox']/li[contains(string(),'%s')]";
}
