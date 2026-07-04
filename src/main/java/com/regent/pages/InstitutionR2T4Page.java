package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.regent.locators.InstitutionR2T4Locators;

import java.util.List;

/** Ported from regent.pages.InstitutionR2T4Page. */
public class InstitutionR2T4Page extends BasePage {

    public InstitutionR2T4Page(Page page) {
        super(page);
    }

    public void editPage() {
        click(InstitutionR2T4Locators.EDIT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void saveEdits() {
        click(InstitutionR2T4Locators.SAVE_BUTTON);
    }

    public void clickCancelButton() {
        click(InstitutionR2T4Locators.CANCEL_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void setDateUsedToConsiderLoanAsOriginated(String option) {
        click(InstitutionR2T4Locators.DATE_USED_TO_CONSIDER_LOAN_ORIGINATED_DROPDOWN);
        click(String.format(InstitutionR2T4Locators.DATE_USED_TO_CONSIDER_LOAN_ORIGINATED_OPTION, option));
    }

    public void setCriteriaMPNLinkedToLoan(String option) {
        click(InstitutionR2T4Locators.CRITERIA_MPN_LINKED_TO_LOAN_DROPDOWN);
        click(String.format(InstitutionR2T4Locators.CRITERIA_MPN_LINKED_TO_LOAN_OPTION, option));
    }

    public void setPotentialR2T4IsRequiredOption(boolean option) {
        click(InstitutionR2T4Locators.POTENTIAL_R2T4_REQUIRED_DROPDOWN);
        click(String.format(InstitutionR2T4Locators.POTENTIAL_R2T4_REQUIRED_OPTION, option ? "Yes" : "No"));
        waitForAjaxRequestToBeFinished();
    }

    public void setR2T4AutomationSettings(boolean option, String offsetNeed, String offsetAutoFinalize, String typesToAutoFinalize) {
        click(InstitutionR2T4Locators.EDIT_BUTTON);
        click(InstitutionR2T4Locators.POTENTIAL_R2T4_REQUIRED_DROPDOWN);
        click(String.format(InstitutionR2T4Locators.POTENTIAL_R2T4_REQUIRED_OPTION, option ? "Yes" : "No"));
        if (option) {
            fill(InstitutionR2T4Locators.OFFSET_DOD_NEED_R2T4, offsetNeed);
            fill(InstitutionR2T4Locators.OFFSET_DOD_AUTO_FINALIZE_R2T4, offsetAutoFinalize);
        }
        setAutomationTypes(typesToAutoFinalize.equalsIgnoreCase("NONE") ? "NONE"
                : typesToAutoFinalize.equalsIgnoreCase("ALL") ? "ALL" : offsetAutoFinalize);
        click(InstitutionR2T4Locators.SAVE_BUTTON);
    }

    public boolean isOffsetDodNeedR2T4InputDisabled() {
        String cssClass = page.locator(InstitutionR2T4Locators.OFFSET_DOD_NEED_R2T4).getAttribute("class");
        return cssClass != null && cssClass.contains("disabled");
    }

    public boolean isOffsetDodAutoFinalizeR2T4InputDisabled() {
        String cssClass = page.locator(InstitutionR2T4Locators.OFFSET_DOD_AUTO_FINALIZE_R2T4).getAttribute("class");
        return cssClass != null && cssClass.contains("disabled");
    }

    public void setOffsetDodNeedR2T4(String offset) {
        fill(InstitutionR2T4Locators.OFFSET_DOD_NEED_R2T4, offset);
    }

    public void setOffsetDodAutoFinalizeR2T4(String offset) {
        fill(InstitutionR2T4Locators.OFFSET_DOD_AUTO_FINALIZE_R2T4, offset);
    }

    public String getIdentifyPotentialR2T4Required() {
        return getText(InstitutionR2T4Locators.IS_POTENTIAL_R2T4_REQUIRED_VALUE);
    }

    public String getOffsetDODNeedR2T4() {
        return getText(InstitutionR2T4Locators.OFFSET_FROM_DOD_NEED_R2T4_VALUE);
    }

    public String getOffsetDODAutoFinalizeR2T4() {
        return getText(InstitutionR2T4Locators.OFFSET_FROM_DOD_AUTO_FINALIZE_R2T4_VALUE);
    }

    /** typesToAutoFinalize is a real <select>; ALL/NONE are sentinel values, otherwise selects the given option text. */
    public void setAutomationTypes(String typesToAutoFinalize) {
        Locator select = page.locator(InstitutionR2T4Locators.AUTO_FINALIZE_TYPES_SELECT);
        if (typesToAutoFinalize.equalsIgnoreCase("ALL")) {
            List<String> allValues = select.locator("option").allInnerTexts();
            select.selectOption(allValues.stream().map(v -> new SelectOption().setLabel(v)).toArray(SelectOption[]::new));
        } else if (typesToAutoFinalize.equalsIgnoreCase("NONE")) {
            select.selectOption(new SelectOption[0]);
        } else {
            select.selectOption(new SelectOption().setLabel(typesToAutoFinalize));
        }
    }

    public boolean isValidationError(String errorMessage) {
        return page.locator(InstitutionR2T4Locators.VALIDATION_SUMMARY_ERRORS).allTextContents()
                .stream().anyMatch(text -> text.equals(errorMessage));
    }

    public boolean isValidationFailedErrorDisplayed() {
        return isVisible(InstitutionR2T4Locators.VALIDATION_FAILED_ERROR);
    }

    public boolean isValidationError(String errorMessage, int elementNumber) {
        return page.locator(InstitutionR2T4Locators.VALIDATION_SUMMARY_ERRORS).nth(elementNumber - 1)
                .textContent().equals(errorMessage);
    }

    public String getOffsetDodAutoFinalizeR2T4InfoText() {
        page.locator(InstitutionR2T4Locators.OFFSET_DOD_AUTO_FINALIZE_R2T4_INFO_ICON).hover();
        return getText(InstitutionR2T4Locators.INFO_TIP_POPUP);
    }

    public boolean isDateUsedIopOptionsValid() {
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        boolean validOptions = isVisible(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, InstitutionR2T4Locators.DATE_OF_DETERMINATION))
                && isVisible(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, InstitutionR2T4Locators.DATE_OF_WITHDRAWAL))
                && page.locator(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTIONS).count() == 2;
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        return validOptions;
    }

    public int getDateUsedIopOptionsCount() {
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        int optionsCount = page.locator(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTIONS).count();
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        return optionsCount;
    }

    public boolean isDateOfWithdrawalDefault() {
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, InstitutionR2T4Locators.DATE_OF_WITHDRAWAL));
        return isVisible(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE) &&
                getText(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE).equals(InstitutionR2T4Locators.AFTER_WDD);
    }

    public boolean isDateOfDeterminationDefault() {
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, InstitutionR2T4Locators.DATE_OF_DETERMINATION));
        return isVisible(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE) &&
                getText(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE).equals(InstitutionR2T4Locators.AFTER_DOD);
    }

    public boolean isIOPDateOfWithdrawalOptionsValid() {
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, InstitutionR2T4Locators.DATE_OF_WITHDRAWAL));
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        boolean validOptions = isVisible(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, InstitutionR2T4Locators.AFTER_WDD))
                && isVisible(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, InstitutionR2T4Locators.EQUAL_OR_AFTER_WDD))
                && page.locator(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS).count() == 2;
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        return validOptions;
    }

    public boolean isIOPDateOfDeterminationOptionsValid() {
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, InstitutionR2T4Locators.DATE_OF_DETERMINATION));
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        boolean validOptions = isVisible(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, InstitutionR2T4Locators.AFTER_DOD))
                && isVisible(String.format(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, InstitutionR2T4Locators.EQUAL_OR_AFTER_DOD))
                && page.locator(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS).count() == 2;
        click(InstitutionR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        return validOptions;
    }
}
