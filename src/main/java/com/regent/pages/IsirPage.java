package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.IsirLocators;

/**
 * Ported from regent.pages.student.IsirPage. The source's IsirGridColumn / ISIRStudentInfoFields15
 * enums (just column-number / row-number wrappers) don't exist on this side, so callers pass the
 * raw 1-based column/row number directly.
 */
public class IsirPage extends BasePage {

    public IsirPage(Page page) {
        super(page);
    }

    public boolean getStudentActiveIsirRow() {
        return isVisible(IsirLocators.STUDENT_ACTIVE_ISIR_ROW);
    }

    /** "Make Active" raises a native confirm dialog; dismiss it by accepting. */
    public void makeISIRActive(String rowKey) {
        page.onceDialog(dialog -> dialog.accept());
        click(String.format(IsirLocators.MAKE_ACTIVE_BUTTON, rowKey));
        waitForAjaxRequestToBeFinished();
    }

    public void filterOnActiveIsirs() {
        click(IsirLocators.ISIR_GRID_FILTER);
        click(IsirLocators.ACTIVE_FILTER_INPUT);
        click(IsirLocators.ISIR_FILTER_BUTTON);
    }

    public String getIsirGridColumnValue(String rowKey, int gridColumnNumber) {
        String selector = String.format(IsirLocators.ISIR_GRID_ROW_CELLS, rowKey);
        return page.locator(selector).nth(gridColumnNumber - 1).textContent().trim();
    }

    public String getIsirGridColumnValue(String federalAwardYear, String isirId, int gridColumnNumber) {
        String selector = String.format(IsirLocators.ISIR_GRID_ROW_CELLS_2, federalAwardYear, isirId);
        return page.locator(selector).nth(gridColumnNumber - 1).textContent().trim();
    }

    public boolean isIsirSectionListed(String sectionName) {
        return isVisible(String.format(IsirLocators.ISIR_DETAIL_SECTION, sectionName));
    }

    public String getStudentInfoOriginalValue(String studentInfoTabIndex, String fieldRowNumber) {
        String selector = String.format(IsirLocators.STUDENT_INFO_ORIGINAL_VALUE, studentInfoTabIndex, fieldRowNumber);
        return getText(selector);
    }

    public String getStudentInfoCorrectedValue(String studentInfoTabIndex, String fieldRowNumber) {
        String selector = String.format(IsirLocators.STUDENT_INFO_CORRECTED_VALUE, studentInfoTabIndex, fieldRowNumber);
        return getText(selector);
    }

    public String getStudentInfoSymbolValue(String studentInfoTabIndex, String fieldRowNumber) {
        String selector = String.format(IsirLocators.STUDENT_INFO_SYMBOL_VALUE, studentInfoTabIndex, fieldRowNumber);
        return getText(selector);
    }

    public boolean isStudentInfoCorrectedValueRed(String studentInfoTabIndex, String fieldRowNumber) {
        String selector = String.format(IsirLocators.STUDENT_INFO_CORRECTED_VALUE_FONT, studentInfoTabIndex, fieldRowNumber);
        String color = page.locator(selector).first().getAttribute("color");
        return "red".equals(color);
    }

    /** Field label differs across ISIR record years: "E-mail" (older) vs "Email Address" (2025+). */
    public String getEmailValue() {
        String emailRow = String.format(IsirLocators.EMAIL_TEST_FIELD, "E-mail");
        if (page.locator(emailRow).count() > 0) {
            return getText(emailRow);
        }
        String emailAddressRow = String.format(IsirLocators.EMAIL_TEST_FIELD, "Email Address");
        if (page.locator(emailAddressRow).count() > 0) {
            return getText(emailAddressRow);
        }
        return "";
    }

    public void clickFinishButton() {
        click(IsirLocators.FINISH_BUTTON);
        page.locator(IsirLocators.FINISH_BUTTON).waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN)
                .setTimeout(timeout));
    }

    public void clickContinueButton() {
        click(IsirLocators.CONTINUE_BUTTON);
        try {
            page.locator(IsirLocators.CONTINUE_BUTTON).waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                    .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN)
                    .setTimeout(timeout));
        } catch (Exception e) {
            // still visible: force a second click, bypassing actionability checks
            page.locator(IsirLocators.CONTINUE_BUTTON).click(new com.microsoft.playwright.Locator.ClickOptions().setForce(true));
            page.locator(IsirLocators.CONTINUE_BUTTON).waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                    .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN)
                    .setTimeout(timeout));
        }
    }

    public void clickOnStudentInformation() {
        click(IsirLocators.STUDENT_INFORMATION_SECTION);
    }

    public void clickOnParentInformation() {
        click(String.format(IsirLocators.CORRECT_WIZARD_SECTION, "Parental Information"));
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnSignaturesSection() {
        click(String.format(IsirLocators.CORRECT_WIZARD_SECTION, "Signatures"));
        waitForAjaxRequestToBeFinished();
    }

    public void setStudentFirstName(String firstName) {
        fill(IsirLocators.STUDENTS_FIRST_NAME_INPUT, firstName);
    }

    public void setStudentMiddleInitial(String middleInitial) {
        fill(IsirLocators.STUDENTS_MIDDLE_INITIAL_INPUT, middleInitial);
    }

    public void setStudentLastName(String lastName) {
        fill(IsirLocators.STUDENTS_LAST_NAME_INPUT, lastName);
    }

    public void setStudentPermanentAddress(String address) {
        fill(IsirLocators.STUDENTS_PERMANENT_ADDRESS_INPUT, address);
    }

    public void setStudentPermanentCity(String city) {
        fill(IsirLocators.STUDENTS_PERMANENT_CITY_INPUT, city);
    }

    public void setStudentDOB(String dob) {
        fill(IsirLocators.STUDENTS_DOB_INPUT, dob);
    }

    public void setStudentPhoneNum(String phoneNumber) {
        fill(IsirLocators.STUDENTS_PHONE_NUM_INPUT, phoneNumber);
    }

    public void setStudentEmail(String email) {
        fill(IsirLocators.STUDENTS_EMAIL_INPUT, email);
    }

    public void setStudentDriversLicenseNum(String licenseNum) {
        fill(IsirLocators.STUDENTS_DRIVERS_LICENSE_NUM, licenseNum);
    }

    public void inputEmail(String email) {
        fill(IsirLocators.EMAIL_INPUT, email);
    }

    public void clickOnActiveIsir() {
        click(IsirLocators.STUDENT_ACTIVE_ISIR_ROW);
    }

    public void clickOnActiveIsir(String rowKey) {
        click(String.format(IsirLocators.GRID_ACTIVE_ISIR_ROW, rowKey));
        waitForAjaxRequestToBeFinished();
    }

    public void clickCorrectIsirButton() {
        click(IsirLocators.CORRECT_ISIR_BUTTON);
    }

    public void setVerificationStatusAndMarkCorrections() {
        click(IsirLocators.VERIFICATION_STATUS_TRIGGER);
        click(IsirLocators.VERIFICATION_STATUS_VERIFIED_OPTION);
        click(IsirLocators.CORRECTION_TRIGGER);
        click(String.format(IsirLocators.CORRECTION_OPTION, "Yes"));
    }

    public void setMarkCorrections(String option) {
        click(IsirLocators.CORRECTION_TRIGGER);
        click(String.format(IsirLocators.CORRECTION_OPTION, option));
    }

    public void setVerificationStatus() {
        click(IsirLocators.VERIFICATION_STATUS_TRIGGER);
        click(IsirLocators.VERIFICATION_STATUS_VERIFIED_OPTION);
    }

    public void setParent2LastName(String lastName) {
        fill(IsirLocators.PARENT2_LAST_NAME_INPUT, lastName);
    }

    public void setParentDislocatedWorker(String option) {
        click(IsirLocators.PARENT_DISLOCATED_WORKER_TRIGGER);
        click(String.format(IsirLocators.PARENT_DISLOCATED_WORKER_OPTION, option));
    }

    public void setParentGrossIncome(String amount) {
        fill(IsirLocators.PARENTS_GROSS_INCOME_INPUT, amount);
    }

    public void setParentIncomeTaxPaid(String amount) {
        fill(IsirLocators.PARENTS_INCOME_TAX_PAID_INPUT, amount);
    }

    public void setDependencyOverride(String option) {
        click(IsirLocators.DEPENDENCY_OVERRIDE_TRIGGER);
        click(String.format(IsirLocators.DEPENDENCY_OVERRIDE_OPTION, option));
    }

    public void setProfessionalJudgement(String option) {
        click(IsirLocators.PROFESSIONAL_JUDGEMENT_TRIGGER);
        click(String.format(IsirLocators.PROFESSIONAL_JUDGEMENT_OPTION, option));
    }

    public void setPackagingOption(String option) {
        click(IsirLocators.PACKAGE_OPTION_TRIGGER);
        click(String.format(IsirLocators.PACKAGE_OPTION_OPTION, option));
    }

    public String getStep2ResultsRecalculationPreviousValue(String fieldName) {
        String selector = String.format(IsirLocators.RESULTS_RECALC_ROW_CELLS, fieldName);
        return page.locator(selector).nth(1).textContent().trim();
    }

    public String getStep2ResultsRecalculationNewValue(String fieldName) {
        String selector = String.format(IsirLocators.RESULTS_RECALC_ROW_CELLS, fieldName);
        return page.locator(selector).nth(2).textContent().trim();
    }
}
