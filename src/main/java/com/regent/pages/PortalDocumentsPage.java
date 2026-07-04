package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.PortalDocumentsLocators;

/**
 * Ported from regent.pages.PortalDocumentsPage. Source extended PortalConfigurationPage for tab
 * navigation, out of scope here (belongs on a PortalConfigurationPage port, not created in this batch).
 * Note: this is a distinct portal-configuration "Documents" page from com.regent.pages.DocumentsPage
 * (student-profile Documents tab) — different app area, different widget names.
 */
public class PortalDocumentsPage extends BasePage {

    public PortalDocumentsPage(Page page) {
        super(page);
    }

    public String getDisplayUploadLabelValue() {
        return getText(PortalDocumentsLocators.DISPLAY_UPLOAD_LABEL_VALUE);
    }

    public String getWaivedDocumentsValue() {
        return getText(PortalDocumentsLocators.DISPLAY_WAIVED_DOCUMENTS_LABEL_VALUE);
    }

    public void setDisplayUploadLabelValue(String value) {
        click(PortalDocumentsLocators.EDIT_BUTTON);
        fill(PortalDocumentsLocators.EDIT_DISPLAY_UPLOAD_LABEL_VALUE, value);
        click(PortalDocumentsLocators.SAVE_BUTTON);
    }

    public void setDisplayWaivedDocumentsValue(String value) {
        click(PortalDocumentsLocators.EDIT_BUTTON);
        fill(PortalDocumentsLocators.EDIT_DISPLAY_WAIVED_DOCUMENTS_VALUE, value);
        click(PortalDocumentsLocators.SAVE_BUTTON);
    }
}
