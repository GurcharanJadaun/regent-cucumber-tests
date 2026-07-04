package com.regent.locators;

/** Ported from regent.pages.PortalDocumentsPage. */
public interface PortalDocumentsLocators {
    String DISPLAY_UPLOAD_LABEL_VALUE = "//div[@data-rem-widgetname='InstitutionPortalDocumentsView']//div[@class='row' and .//label[@for='Documents_DisplayUploadDocuments']]//div[@class='details-field']";
    String EDIT_DISPLAY_UPLOAD_LABEL_VALUE = "//div[label[contains(text(),'Display \"Upload\" for Documents in \"Received\" Status')]]//following-sibling::div/span";

    String DISPLAY_WAIVED_DOCUMENTS_LABEL_VALUE = "//div[label[contains(text(),'Display \"Waived\" Documents')]]//following-sibling::div";
    String EDIT_DISPLAY_WAIVED_DOCUMENTS_VALUE  = "//div[label[contains(text(),'Display \"Waived\" Documents')]]//following-sibling::div/span";

    String EDIT_BUTTON = "//div[contains(@data-rem-widgetname, 'Documents')]//button[contains(@class,'edit_button')]";
    String SAVE_BUTTON = "//div[contains(@data-rem-widgetname, 'Documents')]//button[@type='submit']";
}
