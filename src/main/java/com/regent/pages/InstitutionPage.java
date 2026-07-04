package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.InstitutionLocators;

/**
 * Ported from regent.pages.InstitutionPage. Source methods returning PortalConfigurationPage /
 * RegentPlanConfigurationPage / InstitutionR2T4Page / DetailsPage are ported as void — those page
 * objects don't exist on this side yet, so navigation just performs the click; the caller
 * instantiates the destination page object itself.
 */
public class InstitutionPage extends BasePage {

    public InstitutionPage(Page page) {
        super(page);
    }

    public void enableYearRoundPell(boolean condition) {
        click(InstitutionLocators.EDIT_INSTITUTION_BUTTON);
        waitForAjaxRequestToBeFinished();
        if (condition) {
            page.locator(InstitutionLocators.ENABLE_YEAR_ROUND_PELL_CHECKBOX).check();
        } else {
            page.locator(InstitutionLocators.ENABLE_YEAR_ROUND_PELL_CHECKBOX).uncheck();
        }
        click(InstitutionLocators.SUBMIT_BUTTON);
    }

    public boolean isYearRoundPellEnabled() {
        return page.locator(InstitutionLocators.YEAR_ROUND_PELL_CHECKBOX).isChecked();
    }

    public void setRecalculationOptionToFreeze(String freezeOption) {
        click(InstitutionLocators.EDIT_INSTITUTION_BUTTON);
        click(InstitutionLocators.COA_RECALCULATION_TRIGGER);
        click(String.format(InstitutionLocators.COA_RECALCULATION_OPTION, freezeOption));
        click(InstitutionLocators.SUBMIT_BUTTON);
    }

    public void setSplitWeeksAcrossPaymentPeriod(boolean option) {
        click(InstitutionLocators.EDIT_INSTITUTION_BUTTON);
        waitForAjaxRequestToBeFinished();
        click(InstitutionLocators.SPLIT_WEEKS_ACROSS_PP_TRIGGER);
        click(String.format(InstitutionLocators.SPLIT_WEEKS_ACROSS_PP_OPTION, option ? "Yes" : "No"));
        click(InstitutionLocators.SUBMIT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void setNonTermRecalculationOption(boolean option) {
        click(InstitutionLocators.EDIT_INSTITUTION_BUTTON);
        click(InstitutionLocators.COA_NON_TERM_RECALCULATION_TRIGGER);
        click(String.format(InstitutionLocators.COA_NON_TERM_RECALCULATION_OPTION, option ? "Yes" : "No"));
        click(InstitutionLocators.SUBMIT_BUTTON);
    }

    public void setPackageToNetOption(boolean option) {
        click(InstitutionLocators.EDIT_INSTITUTION_BUTTON);
        click(InstitutionLocators.PACKAGE_TO_NET_TRIGGER);
        click(String.format(InstitutionLocators.PACKAGE_TO_NET_OPTION, option ? "Yes" : "No"));
        click(InstitutionLocators.SUBMIT_BUTTON);
    }

    public void clickOnPortalPage() {
        click(InstitutionLocators.PORTAL_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnRegentPlanTab() {
        click(InstitutionLocators.REGENT_PLAN_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnR2T4Page() {
        click(InstitutionLocators.R2T4_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public String getOpeid() {
        return getText(InstitutionLocators.OPEID);
    }

    // ── User Defined Fields ──────────────────────────────────────────────────

    public void clickUserDefinedFieldsTab() {
        click(InstitutionLocators.USER_DEFINED_FIELDS_TAB);
    }

    public void clickEditUserDefinedFieldsButton() {
        click(InstitutionLocators.EDIT_USER_DEFINED_FIELDS_BUTTON);
    }

    public void clickAddNewFieldButton() {
        click(InstitutionLocators.ADD_NEW_FIELD_BUTTON);
    }

    public void enterFieldNameInput(String fieldName) {
        fill(InstitutionLocators.FIELD_NAME_INPUT, fieldName);
    }

    public void enterFieldNotesInput(String fieldNotes) {
        fill(InstitutionLocators.FIELD_NOTES_INPUT, fieldNotes);
    }

    public void inputAllowEffectiveDatesCheckbox(boolean check) {
        if (check) {
            page.locator(InstitutionLocators.ALLOW_EFFECTIVE_DATES_CHECKBOX).check();
        } else {
            page.locator(InstitutionLocators.ALLOW_EFFECTIVE_DATES_CHECKBOX).uncheck();
        }
    }

    public void clickSaveUserDefinedFieldButton() {
        click(InstitutionLocators.SAVE_USER_DEFINED_FIELD_BUTTON);
    }

    public boolean isUserDefinedFieldNameDefined(String udfName) {
        return isVisible(String.format(InstitutionLocators.USER_DEFINED_FIELD_NAME, udfName));
    }

    /** Publish raises a native confirm dialog; dismiss it by accepting. */
    public void publishPortalConfiguration() {
        page.onceDialog(dialog -> dialog.accept());
        click(InstitutionLocators.PUBLISH_PORTAL_CONFIGURATION_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
