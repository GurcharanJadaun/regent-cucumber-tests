package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.DetailsAddressLocators;

public class DetailsAddressPopup extends BasePage {

    public DetailsAddressPopup(Page page) {
        super(page);
    }

    public void selectAddressType(String typeCode) {
        selectField(DetailsAddressLocators.ADDRESS_TYPE_KEY, typeCode);
    }

    public void enterLine1(String line) {
        fill(String.format(DetailsAddressLocators.TEXT_INPUT, DetailsAddressLocators.LINE1_KEY), line);
    }

    public void enterLine2(String line) {
        fill(String.format(DetailsAddressLocators.TEXT_INPUT, DetailsAddressLocators.LINE2_KEY), line);
    }

    public void enterCity(String city) {
        fill(String.format(DetailsAddressLocators.TEXT_INPUT, DetailsAddressLocators.CITY_KEY), city);
    }

    public void selectStateType(String state) {
        selectField(DetailsAddressLocators.STATE_PROVINCE_KEY, state);
    }

    public void enterPostalCode(String postalCode) {
        fill(String.format(DetailsAddressLocators.TEXT_INPUT, DetailsAddressLocators.POSTAL_CODE_KEY), postalCode);
    }

    public void enterCounty(String county) {
        fill(String.format(DetailsAddressLocators.TEXT_INPUT, DetailsAddressLocators.COUNTY_KEY), county);
    }

    public void selectNation(String nation) {
        selectField(DetailsAddressLocators.COUNTRY_KEY, nation);
    }

    public void enterAddressNotes(String notes) {
        fill(String.format(DetailsAddressLocators.TEXTAREA_INPUT, DetailsAddressLocators.NOTES_KEY), notes);
    }

    public void enterAddressFields(String line1, String line2, String city, String state, String postalCode, String county, String nation) {
        enterLine1(line1);
        enterLine2(line2);
        enterCity(city);
        selectStateType(state);
        enterPostalCode(postalCode);
        enterCounty(county);
        selectNation(nation);
    }

    public void clickSave() {
        click(DetailsAddressLocators.SAVE_BUTTON);
    }

    public void clickDelete() {
        click(DetailsAddressLocators.DELETE_BUTTON);
    }

    /** Delete triggers a native confirm() dialog; auto-accept it before clicking. */
    public void clickDeleteAcceptAlert() {
        page.onceDialog(dialog -> dialog.accept());
        click(DetailsAddressLocators.DELETE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    private void selectField(String fieldKey, String optionText) {
        click(String.format(DetailsAddressLocators.DROPDOWN_TRIGGER, fieldKey));
        click(String.format(DetailsAddressLocators.DROPDOWN_OPTION, fieldKey, optionText));
    }
}
