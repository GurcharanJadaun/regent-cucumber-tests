package com.regent.locators;

/** Ported from regent.pages.AddParentPopUp — Add Parent/Spouse popup. */
public interface AddParentLocators {
    String CONTACT_TYPE_DROPDOWN = "span[aria-owns='StudentParentSpouse_ParentSpouse_ParentSpouseTypeCode_listbox']";
    String SELECT_REFERENCE1 = "//ul[contains(@id,'ParentSpouseTypeCode_listbox')]/li[string()='Reference1']";
    // Keyed by field-id suffix, e.g. "FirstName", "MiddleName", "LastName", "SocialSecurityNumber"
    String REFERENCE_INFORMATION_FIELD = "//input[contains(@id,'%s')]";
    String SAVE_BUTTON = "//button[@class='k-button' and contains(string(), 'Save')]";

    String FERPA_CHECKBOX = "#StudentParentSpouse_ParentSpouse_Ferpa";
    String DATE_OF_BIRTH_INPUT = "#StudentParentSpouse_ParentSpouse_DateOfBirth";
    String DETAILS_NOTE_INPUT = "#StudentParentSpouse_ParentSpouse_Note";

    // Keyed by rem-trigger-event, e.g. "addParentSpouseAddress", "addParentSpousePhone", "addParentSpouseEmail"
    String ADD_BUTTON = "//button[@rem-trigger-event='%s']";

    // Keyed by listbox id suffix, e.g. "ParentSpouseTypeCode_listbox", "CitizenshipStatusCode_listbox"
    String PARENT_DETAILS_DROPDOWN = "//span[contains(@aria-owns,'%s')]";
    // First %s = listbox id suffix, second %s = option text
    String PARENT_DETAILS_LIST_ITEM = "//ul[contains(@id,'%s')]/li[string()='%s']";

    // Keyed by field-id suffix, e.g. "FirstName", "MiddleName", "LastName", "DateOfBirth", "SocialSecurityNumber", "ParentSpouse_Note"
    String PARENT_DETAIL_INPUT = "//input[contains(@id,'%s')]";
    String PARENT_DETAIL_TEXTAREA_INPUT = "//textarea[contains(@id,'%s')]";
}
