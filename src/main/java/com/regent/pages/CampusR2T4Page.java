package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.regent.locators.CampusR2T4Locators;

public class CampusR2T4Page extends BasePage {

    public CampusR2T4Page(Page page) {
        super(page);
    }

    public void clickEditButton() {
        click(CampusR2T4Locators.EDIT_BUTTON);
    }

    public void saveEdits() {
        click(CampusR2T4Locators.SAVE_BUTTON);
    }

    public void clickCancelButton() {
        click(CampusR2T4Locators.CANCEL_BUTTON);
    }

    public void setPotentialR2T4IsRequiredOption(String option) {
        click(CampusR2T4Locators.POTENTIAL_R2T4_REQUIRED_DROPDOWN);
        click(String.format(CampusR2T4Locators.POTENTIAL_R2T4_REQUIRED_OPTION, option));
        waitForAjaxRequestToBeFinished();
    }

    public boolean isOffsetDodNeedR2T4InputDisabled() {
        String classAttr = page.locator(CampusR2T4Locators.OFFSET_DOD_NEED_R2T4_INPUT).getAttribute("class");
        return classAttr != null && classAttr.contains("disabled");
    }

    public boolean isOffsetDodAutoFinalizeR2T4InputDisabled() {
        String classAttr = page.locator(CampusR2T4Locators.OFFSET_DOD_AUTO_FINALIZE_R2T4_INPUT).getAttribute("class");
        return classAttr != null && classAttr.contains("disabled");
    }

    public void setOffsetDodNeedR2T4(String offset) {
        fill(CampusR2T4Locators.OFFSET_DOD_NEED_R2T4_INPUT, offset);
    }

    public void setOffsetDodAutoFinalizeR2T4(String offset) {
        fill(CampusR2T4Locators.OFFSET_DOD_AUTO_FINALIZE_R2T4_INPUT, offset);
    }

    public boolean isValidationError(String errorMessage) {
        for (Locator element : page.locator(CampusR2T4Locators.VALIDATION_SUMMARY_ERRORS).all()) {
            if (element.textContent().trim().equals(errorMessage)) return true;
        }
        return false;
    }

    public boolean isValidationFailedErrorDisplayed() {
        return isVisible(CampusR2T4Locators.VALIDATION_FAILED_ERROR);
    }

    public boolean isValidationError(String errorMessage, int elementNumber) {
        return page.locator(CampusR2T4Locators.VALIDATION_SUMMARY_ERRORS).nth(elementNumber - 1)
                .textContent().trim().equals(errorMessage);
    }

    public String getOffsetDodAutoFinalizeR2T4InfoText() {
        page.locator(CampusR2T4Locators.OFFSET_DOD_AUTO_FINALIZE_INFO_ICON).hover();
        return getText(CampusR2T4Locators.INFO_TIP_POPUP);
    }

    public boolean isInheritOffsetDODNeedR2T4Checkbox() {
        return isVisibleNow(CampusR2T4Locators.INHERIT_OFFSET_DOD_NEED_R2T4_CHECKBOX);
    }

    public boolean isInheritoffsetDODAutoFinalizeR2T4Checkbox() {
        return isVisibleNow(CampusR2T4Locators.INHERIT_OFFSET_DOD_AUTO_FINALIZE_R2T4_CHECKBOX);
    }

    public String getOffsetDODNeedR2T4() {
        return getText(CampusR2T4Locators.OFFSET_DOD_NEED_R2T4_VALUE);
    }

    public String getOffsetDODAutoFinalizeR2T4() {
        return getText(CampusR2T4Locators.OFFSET_DOD_AUTO_FINALIZE_R2T4_VALUE);
    }

    public boolean isDateUsedIopOptionsValid() {
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        boolean validOptions = isVisibleNow(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.DATE_OF_DETERMINATION))
                && isVisibleNow(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.DATE_OF_WITHDRAWAL))
                && isVisibleNow(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.INHERIT))
                && page.locator(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTIONS).count() == 3;
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        return validOptions;
    }

    public int getDateUsedIopOptionsCount() {
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        int optionsCount = page.locator(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTIONS).count();
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        return optionsCount;
    }

    public boolean isDateOfWithdrawalDefault() {
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.DATE_OF_WITHDRAWAL));
        return getText(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE).equals(CampusR2T4Locators.AFTER_WDD);
    }

    public boolean isDateOfDeterminationDefault() {
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.DATE_OF_DETERMINATION));
        return getText(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE).equals(CampusR2T4Locators.AFTER_DOD);
    }

    public boolean isDateIOPInheritDefault() {
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.INHERIT));
        return getText(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE).equals(CampusR2T4Locators.INHERIT);
    }

    public boolean isIOPDateOfWithdrawalOptionsValid() {
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.DATE_OF_WITHDRAWAL));
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        boolean validOptions = isVisibleNow(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, CampusR2T4Locators.AFTER_WDD))
                && isVisibleNow(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, CampusR2T4Locators.EQUAL_OR_AFTER_WDD))
                && page.locator(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS).count() == 2;
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        return validOptions;
    }

    public boolean isIOPDateOfDeterminationOptionsValid() {
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.DATE_OF_DETERMINATION));
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        boolean validOptions = isVisibleNow(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, CampusR2T4Locators.AFTER_DOD))
                && isVisibleNow(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, CampusR2T4Locators.EQUAL_OR_AFTER_DOD))
                && page.locator(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS).count() == 2;
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        return validOptions;
    }

    public boolean isIOPInheritOptionsValid() {
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DROPDOWN);
        click(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_OPTION, CampusR2T4Locators.INHERIT));
        click(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_DROPDOWN);
        return isVisibleNow(String.format(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, CampusR2T4Locators.INHERIT))
                && page.locator(CampusR2T4Locators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS).count() == 1;
    }
}
