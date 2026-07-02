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
}
