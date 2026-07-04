package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.PortalAccountMgtLocators;

/** Ported from regent.pages.PortalAccountMgtPage. */
public class PortalAccountMgtPage extends BasePage {

    public PortalAccountMgtPage(Page page) {
        super(page);
    }

    public void clickEdit() {
        click(PortalAccountMgtLocators.EDIT_BUTTON);
    }

    public void clickSave() {
        click(PortalAccountMgtLocators.SAVE_BUTTON);
    }

    public void selectFirstAndLastName(boolean value) {
        selectYesNo(PortalAccountMgtLocators.FIRST_AND_LAST_NAME_DROPDOWN,
                PortalAccountMgtLocators.FIRST_AND_LAST_NAME_OPTION, value);
    }

    public void selectDisplaySchoolName(boolean value) {
        selectYesNo(PortalAccountMgtLocators.DISPLAY_SCHOOL_NAME_DROPDOWN,
                PortalAccountMgtLocators.DISPLAY_SCHOOL_NAME_OPTION, value);
    }

    public void selectSchoolNameDisplayValue(String value) {
        click(PortalAccountMgtLocators.SCHOOL_NAME_DISPLAY_DROPDOWN);
        click(String.format(PortalAccountMgtLocators.SCHOOL_NAME_DISPLAY_OPTION, value));
    }

    public void selectExternalId1(boolean value) {
        selectYesNo(PortalAccountMgtLocators.EXTERNAL_ID1_DROPDOWN,
                PortalAccountMgtLocators.EXTERNAL_ID1_OPTION, value);
    }

    public void selectExternalId2(boolean value) {
        selectYesNo(PortalAccountMgtLocators.EXTERNAL_ID2_DROPDOWN,
                PortalAccountMgtLocators.EXTERNAL_ID2_OPTION, value);
    }

    public void selectEmailAddress(boolean value) {
        selectYesNo(PortalAccountMgtLocators.EMAIL_ADDRESS_DROPDOWN,
                PortalAccountMgtLocators.EMAIL_ADDRESS_OPTION, value);
    }

    private void selectYesNo(String dropdownSelector, String optionSelectorTemplate, boolean value) {
        click(dropdownSelector);
        click(String.format(optionSelectorTemplate, value ? "Yes" : "No"));
    }
}
