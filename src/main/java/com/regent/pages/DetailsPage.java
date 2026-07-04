package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.DetailsLocators;

/**
 * Ported from regent.pages.student.DetailsPage. Section-navigation methods click into the
 * data-rem-id sub-tab strip within the Details tab (StudentInformation, ContactInformation, etc).
 */
public class DetailsPage extends BasePage {

    public DetailsPage(Page page) {
        super(page);
    }

    private void clickSection(String sectionId) {
        String selector = String.format(DetailsLocators.DETAILS_SECTION_LINK, sectionId);
        click(selector);
    }

    // ── Student Portal Authorizations ───────────────────────────────────────

    public void clickEditAuthorizationButton() {
        click(DetailsLocators.EDIT_AUTHORIZATION_BUTTON);
    }

    public void chosePayNonInstitutionalChargesValueAndSave(String value) {
        click(DetailsLocators.PAY_NON_INSTITUTIONAL_CHARGES_DROPDOWN);
        click(String.format(DetailsLocators.PAY_NON_INSTITUTIONAL_CHARGES_OPTION, value));
        click(DetailsLocators.SAVE_AUTHORIZATION_BUTTON);
    }

    public void editStudentPortalAuthorizations(String holdExcessTitle4, String creditBalanceOption,
                                                 String payPriorYearMinorCharges, String payNonInstitutionalCharges) {
        click(DetailsLocators.HOLD_EXCESS_TITLE4_DROPDOWN);
        click(String.format(DetailsLocators.HOLD_EXCESS_TITLE4_OPTION, holdExcessTitle4));
        click(DetailsLocators.CREDIT_BALANCE_OPTION_DROPDOWN);
        click(String.format(DetailsLocators.CREDIT_BALANCE_OPTION_OPTION, creditBalanceOption));
        click(DetailsLocators.PAY_PRIOR_YEAR_MINOR_CHARGES_DROPDOWN);
        click(String.format(DetailsLocators.PAY_PRIOR_YEAR_MINOR_CHARGES_OPTION, payPriorYearMinorCharges));
        click(DetailsLocators.PAY_NON_INSTITUTIONAL_CHARGES_DROPDOWN);
        click(String.format(DetailsLocators.PAY_NON_INSTITUTIONAL_CHARGES_OPTION, payNonInstitutionalCharges));
        click(DetailsLocators.SAVE_AUTHORIZATION_BUTTON);
    }

    public String getFerpaValueForReference() {
        return getText(DetailsLocators.FERPA_VALUE_FOR_REFERENCE_ROW);
    }

    public String getPayNonInstitutionalChargesValue() {
        return getText(DetailsLocators.PAY_NON_INSTITUTIONAL_CHARGES_VALUE);
    }

    public String getHoldExcessTitle4() {
        return getText(DetailsLocators.HOLD_EXCESS_TITLE4_VALUE);
    }

    public String getCreditBalanceOption() {
        return getText(DetailsLocators.CREDIT_BALANCE_OPTION_VALUE);
    }

    public String getPayPriorYearMinorCharges() {
        return getText(DetailsLocators.PAY_PRIOR_YEAR_MINOR_CHARGES_VALUE);
    }

    // ── Section navigation ──────────────────────────────────────────────────

    public void clickContactInformation() {
        clickSection("ContactInformation");
        waitForAjaxRequestToBeFinished();
    }

    public AddContactPopUp clickAddContactButton() {
        String selector = String.format(DetailsLocators.ADD_CONTACT_BUTTON, "addAddress");
        click(selector);
        return new AddContactPopUp(page);
    }

    public boolean isPhoneAdded(String phoneNumber) {
        String selector = String.format(DetailsLocators.ADDED_PHONE_NUMBER, phoneNumber);
        return isVisible(selector);
    }

    public void clickParentSpouseReferenceInformation() {
        clickSection("ParentSpouseReferenceInformation");
    }

    public void clickStudentPortalAuthorizations() {
        clickSection("StudentPortalAuthorizations");
    }

    public AddParentPopUp clickAddParentButton() {
        String selector = String.format(DetailsLocators.ADD_CONTACT_BUTTON, "addParentSpouse");
        click(selector);
        return new AddParentPopUp(page);
    }

    public boolean isReference1Added() {
        return isVisible(DetailsLocators.ADDED_REFERENCE);
    }

    public EditStudentReferencePopUp clickEditReferenceButton() {
        click(DetailsLocators.EDIT_REFERENCE_BUTTON);
        return new EditStudentReferencePopUp(page);
    }

    public void clickRefreshStudentDetails() {
        click(DetailsLocators.REFRESH_DETAILS_BUTTON);
    }

    // ── Institutionally Defined Data ────────────────────────────────────────

    public void clickInstitutionallyDefinedData() {
        clickSection("InstitutionallyDefinedData");
    }

    public InstitutionallyDefinedInfoPopUp clickAddInstitutionallyDefinedInfoButton() {
        click(DetailsLocators.ADD_INSTITUTIONALLY_DEFINED_INFO_BUTTON);
        waitForAjaxRequestToBeFinished();
        return new InstitutionallyDefinedInfoPopUp(page);
    }

    public InstitutionallyDefinedInfoPopUp clickEditInstitutionallyDefinedInfoRowButton(String udfName) {
        String selector = String.format(DetailsLocators.EDIT_INSTITUTIONALLY_DEFINED_INFO_ROW_BUTTON, udfName);
        click(selector);
        waitForAjaxRequestToBeFinished();
        return new InstitutionallyDefinedInfoPopUp(page);
    }

    public String getInstitutionallyDefinedName(String name) {
        return getIddCell(name, 1);
    }

    public String getInstitutionallyDefinedExternalId(String name) {
        return getIddCell(name, 2);
    }

    public String getInstitutionallyDefinedValue(String name) {
        return getIddCell(name, 3);
    }

    public String getInstitutionallyDefinedEffectiveStartDate(String name) {
        return getIddCell(name, 4);
    }

    public String getInstitutionallyDefinedEffectiveEndDate(String name) {
        return getIddCell(name, 5);
    }

    private String getIddCell(String name, int columnNumber) {
        String selector = String.format(DetailsLocators.INSTITUTIONALLY_DEFINED_DATA_ROW_CELL, name);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public void expandInstitutionalDefinedItemRow(String name) {
        click(String.format(DetailsLocators.IDD_ROW_EXPAND_COLLAPSE, name, "Expand"));
    }

    public void collapseInstitutionalDefinedItemRow(String name) {
        click(String.format(DetailsLocators.IDD_ROW_EXPAND_COLLAPSE, name, "Collapse"));
    }

    public String getInstitutionallyDefinedHistoryValue(String name, int rowNumber) {
        return getIddHistoryCell(name, rowNumber, 1);
    }

    public String getInstitutionallyDefinedHistoryEffectiveStart(String name, int rowNumber) {
        return getIddHistoryCell(name, rowNumber, 2);
    }

    public String getInstitutionallyDefinedHistoryEffectiveEnd(String name, int rowNumber) {
        return getIddHistoryCell(name, rowNumber, 3);
    }

    private String getIddHistoryCell(String name, int rowNumber, int columnNumber) {
        String selector = String.format(DetailsLocators.IDD_HISTORY_GRID_CELLS, name, rowNumber);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    // ── Communications opt-out ──────────────────────────────────────────────

    public void editCommunicationsOptOut(boolean emailOptOut, boolean textOptOut) {
        click(DetailsLocators.EDIT_OPT_OUT_BUTTON);
        setChecked(DetailsLocators.EMAIL_OPT_OUT_CHECKBOX, emailOptOut);
        setChecked(DetailsLocators.TEXT_OPT_OUT_CHECKBOX, textOptOut);
        click(DetailsLocators.SAVE_OPT_OUT_BUTTON);
    }

    private void setChecked(String selector, boolean checked) {
        if (checked) {
            page.locator(selector).check();
        } else {
            page.locator(selector).uncheck();
        }
    }

    // ── Student Information ─────────────────────────────────────────────────

    public void clickStudentInformation() {
        clickSection("StudentInformation");
    }

    public String getLastName() {
        return getStudentDetailsItem("StudentInformationView", "Last Name");
    }

    public String getFirstName() {
        return getStudentDetailsItem("StudentInformationView", "First Name");
    }

    public String getMiddleName() {
        return getStudentDetailsItem("StudentInformationView", "Middle Name");
    }

    public String getBirthDate() {
        return getStudentDetailsItem("StudentInformationView", "Birth Date");
    }

    public String getSSN() {
        return getStudentDetailsItem("StudentInformationView", "SSN");
    }

    /** For custom labels not covered by a dedicated getter. */
    public String getStudentInformationItem(String itemLabel) {
        return getStudentDetailsItem("StudentInformationView", itemLabel);
    }

    public String getDriversLicenseNo() {
        return getStudentDetailsItem("StudentInformationView", "Drivers License No");
    }

    private String getStudentDetailsItem(String widgetName, String label) {
        String selector = String.format(DetailsLocators.STUDENT_DETAILS_ITEM, widgetName, label);
        return getText(selector);
    }

    // ── Demographic Information ─────────────────────────────────────────────

    public void clickDemographicInformation() {
        clickSection("DemographicInformation");
    }

    public String getCitizenStatus() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Citizenship Status");
    }

    public String getResidencyStatus() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Residency Status");
    }

    public String getResidencyState() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Residency State");
    }

    public String getResidencyDate() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Res. Date");
    }

    public String getEthnicity() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Ethnicity");
    }

    public String getRace() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Race");
    }

    public String getGender() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Gender");
    }

    public String getMaritalStatus() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Marital Status");
    }

    public String getVeteranStatus() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Veteran Status");
    }

    public String getDisabilityStatus() {
        return getStudentDetailsItem("StudentDemographicInformationView", "Disability Status");
    }

    // ── Contact Information — Address ───────────────────────────────────────

    public boolean isAddressDisplayed(String rowKey) {
        String selector = String.format(DetailsLocators.ADDRESS_ROW, rowKey);
        return isVisible(selector);
    }

    public String getAddressType(String rowKey) {
        return getAddressCell(rowKey, 1);
    }

    public String getAddressSource(String rowKey) {
        return getAddressCell(rowKey, 2);
    }

    public String getStreetAddress1(String rowKey) {
        return getAddressCell(rowKey, 3);
    }

    public String getStreetAddress2(String rowKey) {
        return getAddressCell(rowKey, 4);
    }

    public String getAddressCity(String rowKey) {
        return getAddressCell(rowKey, 5);
    }

    public String getAddressState(String rowKey) {
        return getAddressCell(rowKey, 6);
    }

    public String getAddressPostalCode(String rowKey) {
        return getAddressCell(rowKey, 7);
    }

    public String getAddressStateProvince(String rowKey) {
        return getAddressCell(rowKey, 8);
    }

    public String getAddressNation(String rowKey) {
        return getAddressCell(rowKey, 9);
    }

    public String getAddressNotes(String rowKey) {
        return getAddressCell(rowKey, 11);
    }

    private String getAddressCell(String rowKey, int columnNumber) {
        String selector = String.format(DetailsLocators.ADDRESS_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public DetailsAddressPopup editAddress(String rowKey) {
        click(String.format(DetailsLocators.ADDRESS_EDIT_BUTTON, rowKey));
        return new DetailsAddressPopup(page);
    }

    // ── Contact Information — Phone ─────────────────────────────────────────

    public boolean isPhoneDisplayed(String rowKey) {
        String selector = String.format(DetailsLocators.PHONE_ROW, rowKey);
        return isVisible(selector);
    }

    public String getPhoneType(String rowKey) {
        return getPhoneCell(rowKey, 1);
    }

    public String getPhoneSource(String rowKey) {
        return getPhoneCell(rowKey, 2);
    }

    public String getPhoneNumber(String rowKey) {
        return getPhoneCell(rowKey, 3);
    }

    private String getPhoneCell(String rowKey, int columnNumber) {
        String selector = String.format(DetailsLocators.PHONE_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public DetailsPhonePopup editPhone(String rowKey) {
        click(String.format(DetailsLocators.PHONE_EDIT_BUTTON, rowKey));
        return new DetailsPhonePopup(page);
    }

    // ── Contact Information — Email ─────────────────────────────────────────

    public boolean isEmailDisplayed(String rowKey) {
        String selector = String.format(DetailsLocators.EMAIL_ROW, rowKey);
        return isVisible(selector);
    }

    public String getEmailType(String rowKey) {
        return getEmailCell(rowKey, 1);
    }

    public String getEmailSource(String rowKey) {
        return getEmailCell(rowKey, 2);
    }

    public String getEmail(String rowKey) {
        return getEmailCell(rowKey, 3);
    }

    private String getEmailCell(String rowKey, int columnNumber) {
        String selector = String.format(DetailsLocators.EMAIL_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public DetailsEmailPopup editEmail(String rowKey) {
        click(String.format(DetailsLocators.EMAIL_EDIT_BUTTON, rowKey));
        return new DetailsEmailPopup(page);
    }

    // ── Academic Information ────────────────────────────────────────────────

    public void clickAcademicInformation() {
        clickSection("AcademicInformation");
    }

    public String getSiteId() {
        return getStudentDetailsItem("StudentAcademicInfoView", "SiteId");
    }

    public String getHousingStatus() {
        return getStudentDetailsItem("StudentAcademicInfoView", "SIS Housing Status");
    }

    public String getAdmittedDate() {
        return getStudentDetailsItem("StudentAcademicInfoView", "Admitted Date");
    }

    public String getAnticipatedStartDate() {
        return getStudentDetailsItem("StudentAcademicInfoView", "Anticipated Start Date");
    }

    public String getTeacherExpert() {
        return getStudentDetailsItem("StudentAcademicInfoView", "Teacher Expert");
    }

    // ── Test Scores ──────────────────────────────────────────────────────────

    public void clickTestScores() {
        clickSection("TestScores");
    }

    public String getTestName(String rowKey) {
        return getTestScoreCell(rowKey, 1);
    }

    public String getTestScore(String rowKey) {
        return getTestScoreCell(rowKey, 2);
    }

    private String getTestScoreCell(String rowKey, int columnNumber) {
        String selector = String.format(DetailsLocators.TEST_SCORES_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    // ── Parent/Spouse/Reference ──────────────────────────────────────────────

    public void expandParentSpouseGridRow(String rowKey) {
        waitForAjaxRequestToBeFinished();
        String selector = String.format(DetailsLocators.PARENT_SPOUSE_GRID_EXPAND_ROW, rowKey);
        if (isVisibleNow(selector)) {
            click(selector);
        }
    }

    public AddParentPopUp clickParentSpouseRefEditButton(String rowKey) {
        click(String.format(DetailsLocators.PARENT_SPOUSE_REFERENCE_EDIT_BUTTON, rowKey));
        return new AddParentPopUp(page);
    }

    public String getParentSpouseRefSource(String rowKey) {
        return getParentSpouseCell(rowKey, 2);
    }

    public String getParentSpouseRefFirstName(String rowKey) {
        return getParentSpouseCell(rowKey, 3);
    }

    public String getParentSpouseRefLastName(String rowKey) {
        return getParentSpouseCell(rowKey, 4);
    }

    public String getParentSpouseRefPlusBorrower(String rowKey) {
        return getParentSpouseCell(rowKey, 5);
    }

    public String getParentSpouseRefFerpa(String rowKey) {
        return getParentSpouseCell(rowKey, 6);
    }

    private String getParentSpouseCell(String rowKey, int columnNumber) {
        String selector = String.format(DetailsLocators.PARENT_SPOUSE_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public String getParentSpouseRefAddressType(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 1);
    }

    public String getParentSpouseRefAddressSource(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 2);
    }

    public String getParentSpouseRefStreetAddress1(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 3);
    }

    public String getParentSpouseRefStreetAddress2(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 4);
    }

    public String getParentSpouseRefAddressCity(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 5);
    }

    public String getParentSpouseRefAddressState(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 6);
    }

    public String getParentSpouseRefAddressPostalCode(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 7);
    }

    public String getParentSpouseRefAddressNation(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 8);
    }

    public String getParentSpouseRefAddressNotes(String rowKey) {
        return getParentSpouseAddressCell(rowKey, 9);
    }

    private String getParentSpouseAddressCell(String rowKey, int columnNumber) {
        String selector = String.format(DetailsLocators.PARENT_SPOUSE_ADDRESS_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public boolean isParentSpouseRefAddressDisplayed(String rowKey) {
        String selector = String.format(DetailsLocators.PARENT_SPOUSE_ADDRESS_GRID_CELLS, rowKey);
        return page.locator(selector).first().isVisible();
    }

    public boolean isParentSpouseRefEmailDisplayed(String rowKey) {
        String selector = String.format(DetailsLocators.PARENT_SPOUSE_EMAILS_GRID_CELLS, rowKey);
        return page.locator(selector).first().isVisible();
    }

    public boolean isParentSpouseRefPhoneDisplayed(String rowKey) {
        String selector = String.format(DetailsLocators.PARENT_SPOUSE_PHONES_GRID_CELLS, rowKey);
        return page.locator(selector).first().isVisible();
    }

    public String getParentSpouseRefPhoneType(String rowKey) {
        return getParentSpousePhoneCell(rowKey, 1);
    }

    public String getParentSpouseRefPhoneSource(String rowKey) {
        return getParentSpousePhoneCell(rowKey, 2);
    }

    public String getParentSpouseRefPhoneNumber(String rowKey) {
        return getParentSpousePhoneCell(rowKey, 3);
    }

    private String getParentSpousePhoneCell(String rowKey, int columnNumber) {
        String selector = String.format(DetailsLocators.PARENT_SPOUSE_PHONES_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public String getParentSpouseRefEmailType(String rowKey) {
        return getParentSpouseEmailCell(rowKey, 1);
    }

    public String getParentSpouseRefEmailSource(String rowKey) {
        return getParentSpouseEmailCell(rowKey, 2);
    }

    public String getParentSpouseRefEmail(String rowKey) {
        return getParentSpouseEmailCell(rowKey, 3);
    }

    private String getParentSpouseEmailCell(String rowKey, int columnNumber) {
        String selector = String.format(DetailsLocators.PARENT_SPOUSE_EMAILS_GRID_CELLS, rowKey);
        return page.locator(selector).nth(columnNumber - 1).textContent().trim();
    }

    public DetailsAddressPopup editParentSpouseRefAddress(String rowKey) {
        click(String.format(DetailsLocators.PARENT_SPOUSE_REF_CONTACT_EDIT_BUTTON, "addressesGrid", rowKey));
        return new DetailsAddressPopup(page);
    }

    public DetailsEmailPopup editParentSpouseRefEmail(String rowKey) {
        click(String.format(DetailsLocators.PARENT_SPOUSE_REF_CONTACT_EDIT_BUTTON, "emailsGrid", rowKey));
        return new DetailsEmailPopup(page);
    }

    public DetailsPhonePopup editParentSpouseRefPhone(String rowKey) {
        click(String.format(DetailsLocators.PARENT_SPOUSE_REF_CONTACT_EDIT_BUTTON, "phonesGrid", rowKey));
        return new DetailsPhonePopup(page);
    }

    public DetailsAddressPopup addParentSpouseRefAddress() {
        click(String.format(DetailsLocators.PARENT_SPOUSE_REF_CONTACT_ADD_BUTTON, "address"));
        return new DetailsAddressPopup(page);
    }

    public DetailsEmailPopup addParentSpouseRefEmail() {
        click(String.format(DetailsLocators.PARENT_SPOUSE_REF_CONTACT_ADD_BUTTON, "email"));
        return new DetailsEmailPopup(page);
    }

    public DetailsPhonePopup addParentSpouseRefPhone() {
        click(String.format(DetailsLocators.PARENT_SPOUSE_REF_CONTACT_ADD_BUTTON, "phone"));
        return new DetailsPhonePopup(page);
    }

    // ── Ability To Benefit ───────────────────────────────────────────────────

    public void clickAbilityToBenefit() {
        clickSection("AbilityToBenefit");
        waitForAjaxRequestToBeFinished();
    }

    public String getStudentEligibilityCode() {
        String selector = String.format(DetailsLocators.ATB_DETAILS_FIELD, "SISRecord_AbilityToBenefitCode");
        return getText(selector).trim();
    }
}
