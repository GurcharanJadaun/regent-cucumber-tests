package com.regent.locators;

public interface ImportProcessLocators {
    // Process type dropdown (Kendo UI)
    String PROCESS_TYPE_DROPDOWN = "//span[@aria-owns='IOProcess_IOProcessTypeId_listbox']//span[@class='k-input']";
    String PROCESS_TYPE_OPTION   = "//ul[@id='IOProcess_IOProcessTypeId_listbox']/li[text()='%s']";

    // File upload (hidden input behind the Kendo Upload widget)
    String FILE_UPLOAD_INPUT = "//div[@class='k-button k-upload-button']//input[@data-rem-id='importUpload']";

    // Submit / status
    String SUBMIT_BUTTON     = "//button[@rem-trigger-event='submit']";
    String UPLOAD_DONE       = "//strong[@class='k-upload-status k-upload-status-total' and contains(string(),'Done')]";
    String LOADMASK          = "//div[@class='loadmask']";

    // Import process names (as they appear in the dropdown)
    String IMPORT_SBL  = "Import SBL";
    String IMPORT_ISIR = "Import ISIR";
    String IMPORT_STATE_GRANTS = "Import State Grants";

    // State/Province dropdown (Kendo UI) — shown after choosing "Import State Grants"
    // (used by both Wisconsin State Grant and Ohio OCOG imports in the source)
    String STATE_PROVINCE_DROPDOWN = "span[aria-owns='StateProvinceCode_listbox']";
    String STATE_PROVINCE_OPTION   = "#StateProvinceCode_listbox li:visible:text-is('%s')";

    // "Create Student" checkbox + cascading Enterprise/Institution/Campus dropdowns shown
    // when the imported ISIR has no matching student yet
    String CREATE_STUDENT_CHECKBOX = "#IOProcess_CreateStudentFlag";
    String ENTERPRISE_DROPDOWN     = "span[aria-owns='EnterpriseId_listbox']";
    String INSTITUTION_DROPDOWN    = "span[aria-owns='InstitutionId_listbox']";
    String CAMPUS_DROPDOWN         = "span[aria-owns='IOProcess_LocationId_listbox']";
    // The source targeted all three of these cascading dropdowns' shared flyout with one fragile
    // positional "(...)[last()]" XPath; filtering by :visible text on the open listbox instead.
    String CASCADING_DROPDOWN_OPTION = "ul li:visible:text-is('%s')";
}
