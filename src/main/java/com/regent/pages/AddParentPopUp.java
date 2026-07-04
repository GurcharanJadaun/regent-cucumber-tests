package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddParentLocators;

/** Ported from regent.pages.AddParentPopUp — Add Parent/Spouse popup. */
public class AddParentPopUp extends BasePage {

    public AddParentPopUp(Page page) {
        super(page);
    }

    public void selectReferenceAndFillInformationAndSubmit(String firstName, String middleName, String lastName, String ssn) {
        click(AddParentLocators.CONTACT_TYPE_DROPDOWN);
        click(AddParentLocators.SELECT_REFERENCE1);
        fill(String.format(AddParentLocators.REFERENCE_INFORMATION_FIELD, "FirstName"), firstName);
        fill(String.format(AddParentLocators.REFERENCE_INFORMATION_FIELD, "MiddleName"), middleName);
        fill(String.format(AddParentLocators.REFERENCE_INFORMATION_FIELD, "LastName"), lastName);
        fill(String.format(AddParentLocators.REFERENCE_INFORMATION_FIELD, "SocialSecurityNumber"), ssn);
        click(AddParentLocators.SAVE_BUTTON);
    }

    public void clickSave() {
        click(AddParentLocators.SAVE_BUTTON);
    }

    public AddParentPopUp selectContactType(String contactType) {
        click(String.format(AddParentLocators.PARENT_DETAILS_DROPDOWN, "ParentSpouseTypeCode_listbox"));
        click(String.format(AddParentLocators.PARENT_DETAILS_LIST_ITEM, "ParentSpouseTypeCode_listbox", contactType));
        return this;
    }

    public AddParentPopUp enterIdentityFields(String firstName, String middleName, String lastName, String dateOfBirth, String ssn, String citizenshipStatus) {
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "FirstName"), firstName);
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "MiddleName"), middleName);
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "LastName"), lastName);
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "DateOfBirth"), dateOfBirth);
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "SocialSecurityNumber"), ssn);
        click(String.format(AddParentLocators.PARENT_DETAILS_DROPDOWN, "CitizenshipStatusCode_listbox"));
        click(String.format(AddParentLocators.PARENT_DETAILS_LIST_ITEM, "CitizenshipStatusCode_listbox", citizenshipStatus));
        return this;
    }

    public AddParentPopUp enterFirstName(String name) {
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "FirstName"), name);
        return this;
    }

    public AddParentPopUp enterMiddleName(String name) {
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "MiddleName"), name);
        return this;
    }

    public AddParentPopUp enterLastName(String name) {
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "LastName"), name);
        return this;
    }

    public AddParentPopUp enterDateOfBirth(String birthDate) {
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "DateOfBirth"), birthDate);
        return this;
    }

    public AddParentPopUp enterFerpa(boolean checked) {
        if (checked) {
            page.locator(AddParentLocators.FERPA_CHECKBOX).check();
        } else {
            page.locator(AddParentLocators.FERPA_CHECKBOX).uncheck();
        }
        return this;
    }

    public AddParentPopUp enterNote(String note) {
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "ParentSpouse_Note"), note);
        return this;
    }

    public AddParentPopUp enterSsn(String ssn) {
        fill(String.format(AddParentLocators.PARENT_DETAIL_INPUT, "SocialSecurityNumber"), ssn);
        return this;
    }

    public AddParentPopUp selectCitizenShipStatus(String status) {
        click(String.format(AddParentLocators.PARENT_DETAILS_DROPDOWN, "CitizenshipStatusCode_listbox"));
        click(String.format(AddParentLocators.PARENT_DETAILS_LIST_ITEM, "CitizenshipStatusCode_listbox", status));
        return this;
    }

    public void addAddressDetails() {
        click(String.format(AddParentLocators.ADD_BUTTON, "addParentSpouseAddress"));
    }

    public void addPhoneDetails() {
        click(String.format(AddParentLocators.ADD_BUTTON, "addParentSpousePhone"));
    }

    public void addEmailDetails() {
        click(String.format(AddParentLocators.ADD_BUTTON, "addParentSpouseEmail"));
    }
}
