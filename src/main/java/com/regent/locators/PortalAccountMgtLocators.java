package com.regent.locators;

/** Locators ported from regent.pages.PortalAccountMgtPage. */
public interface PortalAccountMgtLocators {

    String EDIT_BUTTON = "//div[@data-rem-widgetname='InstitutionPortalAccountMgtView']//button[contains(string(),'Edit')]";
    String SAVE_BUTTON = "//div[@data-rem-widgetname='InstitutionPortalAccountMgtEdit']//button[contains(string(),'Save')]";

    // Each of these dropdowns can be reopened across repeated Edit/Save cycles on this page, so
    // (as with DocumentsLocators' status dropdown) filter by :visible to avoid landing on a stale
    // hidden Kendo duplicate left behind by a previous edit.
    String FIRST_AND_LAST_NAME_DROPDOWN = "span[aria-owns='NonSsoSettings_FirstAndLastName_listbox']:visible";
    String FIRST_AND_LAST_NAME_OPTION   = "#NonSsoSettings_FirstAndLastName_listbox li:visible:text-is('%s')";

    String DISPLAY_SCHOOL_NAME_DROPDOWN = "span[aria-owns='NonSsoSettings_DisplaySchoolName_listbox']:visible";
    String DISPLAY_SCHOOL_NAME_OPTION   = "#NonSsoSettings_DisplaySchoolName_listbox li:visible:text-is('%s')";

    String SCHOOL_NAME_DISPLAY_DROPDOWN = "span[aria-owns='NonSsoSettings_SchoolNameDisplayValueTypeCode_listbox']:visible";
    String SCHOOL_NAME_DISPLAY_OPTION   = "#NonSsoSettings_SchoolNameDisplayValueTypeCode_listbox li:visible:text-is('%s')";

    String EXTERNAL_ID1_DROPDOWN = "span[aria-owns='NonSsoSettings_ExternalId1_listbox']:visible";
    String EXTERNAL_ID1_OPTION   = "#NonSsoSettings_ExternalId1_listbox li:visible:text-is('%s')";

    String EXTERNAL_ID2_DROPDOWN = "span[aria-owns='NonSsoSettings_ExternalId2_listbox']:visible";
    String EXTERNAL_ID2_OPTION   = "#NonSsoSettings_ExternalId2_listbox li:visible:text-is('%s')";

    String EMAIL_ADDRESS_DROPDOWN = "span[aria-owns='NonSsoSettings_EmailAddress_listbox']:visible";
    String EMAIL_ADDRESS_OPTION   = "#NonSsoSettings_EmailAddress_listbox li:visible:text-is('%s')";
}
