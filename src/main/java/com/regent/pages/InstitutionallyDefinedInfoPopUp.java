package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.InstitutionallyDefinedInfoLocators;

/**
 * Ported from regent.pages.student.InstitutionallyDefinedInfoPopUp. Source methods return
 * DetailsPage; that page object doesn't exist on this side yet, so these just perform the
 * save/action and return void — the caller instantiates DetailsPage itself once needed.
 */
public class InstitutionallyDefinedInfoPopUp extends BasePage {

    public InstitutionallyDefinedInfoPopUp(Page page) {
        super(page);
    }

    /** Use for UDFs without effective dates. */
    public void addInstitutionallyDefinedInformation(String udfName, String value) {
        click(InstitutionallyDefinedInfoLocators.UDF_NAME_TRIGGER);
        click(String.format(InstitutionallyDefinedInfoLocators.UDF_NAME_OPTION, udfName));
        fill(InstitutionallyDefinedInfoLocators.UDF_VALUE, value);
        click(InstitutionallyDefinedInfoLocators.INPUT_UDF_DATA_SAVE_BUTTON);
    }

    /** Use for UDFs with effective dates. */
    public void addInstitutionallyDefinedInformation(String udfName, String value, String extUDFId, String startDate, String endDate) {
        click(InstitutionallyDefinedInfoLocators.UDF_NAME_TRIGGER);
        click(String.format(InstitutionallyDefinedInfoLocators.UDF_NAME_OPTION, udfName));
        fill(InstitutionallyDefinedInfoLocators.UDF_VALUE, value);
        fill(InstitutionallyDefinedInfoLocators.EXTERNAL_UDF_ID, extUDFId);
        fill(InstitutionallyDefinedInfoLocators.EFFECTIVE_START_DATE, startDate);
        fill(InstitutionallyDefinedInfoLocators.EFFECTIVE_END_DATE, endDate);
        click(InstitutionallyDefinedInfoLocators.INPUT_UDF_DATA_SAVE_BUTTON);
    }

    /** Edit a UDF value only (no effective dates); pass "" to clear. */
    public void editInstitutionallyDefinedInformation(String value) {
        if (value.isEmpty()) {
            page.locator(InstitutionallyDefinedInfoLocators.UDF_VALUE).fill("");
        } else {
            fill(InstitutionallyDefinedInfoLocators.UDF_VALUE, value);
        }
        page.waitForTimeout(10000);
        click(InstitutionallyDefinedInfoLocators.INPUT_UDF_DATA_SAVE_BUTTON);
    }

    /** Edit a UDF's value plus its effective Start & End dates. */
    public void editInstitutionallyDefinedInformation(String value, String startDate, String endDate) {
        fill(InstitutionallyDefinedInfoLocators.UDF_VALUE, value);
        fill(InstitutionallyDefinedInfoLocators.EFFECTIVE_START_DATE, startDate);
        fill(InstitutionallyDefinedInfoLocators.EFFECTIVE_END_DATE, endDate);
        click(InstitutionallyDefinedInfoLocators.INPUT_UDF_DATA_SAVE_BUTTON);
    }

    /** Edit only a UDF's effective Start & End dates, leaving its value unchanged. */
    public void editInstitutionallyDefinedInformation(String startDate, String endDate) {
        fill(InstitutionallyDefinedInfoLocators.EFFECTIVE_START_DATE, startDate);
        fill(InstitutionallyDefinedInfoLocators.EFFECTIVE_END_DATE, endDate);
        click(InstitutionallyDefinedInfoLocators.INPUT_UDF_DATA_SAVE_BUTTON);
    }
}
