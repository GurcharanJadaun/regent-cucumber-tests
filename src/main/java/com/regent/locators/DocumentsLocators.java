package com.regent.locators;

/** Locators ported from regent.pages.student.DocumentsPage in the reference project. */
public interface DocumentsLocators {

    String ADD_DOCUMENT_BUTTON = "//button[@rem-trigger-event='documentRequirementAddClick']";
    String SAVE_BUTTON         = "//form[contains(@action,'StudentDocument')]//button[1]";
    String EDIT_BUTTON         = "//div[@class='r-clear-float']//div[@class='twelve columns']//button[2]";
    String LOADMASK            = "//div[@class='loadmask']";

    // Add-document form (native <select> elements)
    String DOCUMENT_REQUIREMENT_LINK   = "//div[@data-rem-id='document-name']/a";
    String DOCUMENT_USE_TYPE_SELECT    = "#DocumentRequirement_documentUseTypeCode";
    String DOCUMENT_ID_SELECT          = "#DocumentRequirement_documentId";

    // Existing-document grid row / status change. Kendo re-initializes this widget on every edit
    // without removing the previous instance, so the aria-owns id and its _listbox both end up
    // duplicated in the DOM within a single scenario, and the newest copy is not reliably last in
    // document order (a stale hidden copy can outrank it), so positional [last()] xpath is not
    // safe across repeated status changes in one scenario (e.g. RS-T730). Filter by Playwright's
    // :visible CSS pseudo-class instead to always land on the currently-open instance.
    String DOCUMENT_ROW_BY_NAME        = "//div[@data-rem-widgetname='DocumentRequirementGridView']//tr[contains(string(),'%s')]";
    String DOCUMENT_STATUS_DROPDOWN    = "span[aria-owns='DocumentRequirement_documentRequirementStatusCode_listbox']:visible";
    String DOCUMENT_STATUS_OPTION      = "#DocumentRequirement_documentRequirementStatusCode_listbox li:visible:text-is('%s')";

    // Row lookup variants
    String DOCUMENT_ROW_WITH_STATUS      = "//div[@data-rem-widgetname='DocumentRequirementGridView']//tr[contains(string(),'%s')][contains(string(),'%s')]";
    String DOCUMENT_ROWS                 = "//div[@data-rem-widgetname='DocumentRequirementGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr";
    String DOCUMENT_GRID_ROW_CELLS       = "//div[@data-rem-widgetname='DocumentRequirementGridView']//tr[contains(string(),'%s')]/td";
    String DOCUMENT_REASON_CELL          = "//div[@data-rem-widgetname='DocumentRequirementGridView']//tr[contains(string(),'%s')]/td[9]";
    String DOCUMENT_ROW_CHECKBOX         = "//div[@data-rem-widgetname='DocumentRequirementGridView']//tr[contains(string(),'%s')]/td/input[@data-rem-id='documentCheckBoxColumn']";
    String DOCUMENT_ROW_CELL_CHECKBOX    = "//div[@data-rem-widgetname='DocumentRequirementGridView']//tr[contains(string(),'%s') and contains(string(),'%s')]/td/input[@data-rem-id='documentCheckBoxColumn']";
    String SELECT_ALL_DOCUMENTS_CHECKBOX = "//input[@data-rem-id='chSelAll']";
    String DELETE_BUTTON                 = "//button[.='Delete']";

    // Bulk status-change controls (StatusCode Kendo dropdown). Same repeated-edit staleness risk
    // as DOCUMENT_STATUS_DROPDOWN above, so this also uses the :visible-filtered pattern rather
    // than the source's positional/animation-container xpath.
    String BULK_STATUS_DROPDOWN     = "span[aria-owns='StatusCode_listbox']:visible";
    String BULK_STATUS_OPTION       = "#StatusCode_listbox li:visible:text-is('%s')";
    String UPDATE_DOCUMENT_STATUS_BUTTON = "//button[@rem-trigger-event='updateDocumentsStatusesClick']";

    // Add-document form: scope-dependent Academic Year / Federal Award Year pickers
    String SCOPE_NAME                  = "//div[@data-rem-id='documentScopeSection']/div[@class='row' and contains(string(),'Scope')]//div[@class='details-field']";
    String ACADEMIC_YEAR_DROPDOWN      = "span[aria-owns='DocumentRequirement_academicYearId_listbox']:visible";
    String ACADEMIC_YEAR_OPTION        = "#DocumentRequirement_academicYearId_listbox li:visible:text-is('%s')";
    String FEDERAL_AWARD_YEAR_DROPDOWN = "span[aria-owns='DocumentRequirement_federalAwardYearId_listbox']:visible";
    String FEDERAL_AWARD_YEAR_OPTION   = "#DocumentRequirement_federalAwardYearId_listbox li:visible:text-is('%s')";

    // Submitted-document upload / attachments
    String SELECT_SUBMITTED_DOCUMENTS_INPUT = "#import";
    String INPUT_REASON                     = "#DocumentRequirement_message";
    String PAPER_CLIP_ICON                  = "//div[@data-rem-widgetname='DocumentRequirementGrid']//tr[contains(string(),'%s')]//span[@class='k-sprite icon rem-icon-attach']";
    String DOCUMENT_GRID_REFRESH_BUTTON     = "//button[@rem-trigger-event='refreshDocumentsGridClick' and contains(text(),'Refresh')]";
    String SUBMITTED_DOCUMENT_ROW           = "//div[@class='rem-files-list']//tr[contains(string(),'%s')]";
    String SUBMITTED_DOCUMENT_LINK          = "//div[@class='rem-files-list']//a[normalize-space()='%s']";

    // Grid column filters
    String SCOPE_COLUMN_FILTER_TRIGGER = "//div[@data-rem-widgetname='DocumentRequirementGrid']//th[@data-title='%s']/a[@title='Filter']";
    String COLUMN_FILTER_VALUE_INPUT   = "//form[@title='Show items with value that:' and @aria-hidden='false']//input[@title='Value']";
    String COLUMN_FILTER_BUTTON        = "//form[@title='Show items with value that:' and @aria-hidden='false']//button[text()='Filter']";

    // Document details ("General" tab) fields
    String DETAILS_FIELD_VALUE_BY_LABEL   = "//div[@data-rem-widgetname='DocumentRequirementViewGeneral']//div[@class='details-label' and contains(string(),'%s')]/following-sibling::div";
    String OFFICE_USE_ONLY_DETAILS_FIELD  = "//div[@data-rem-widgetname='DocumentRequirementViewGeneral']//div[@class='details-field' and normalize-space()='Office Use Only']";

    // Signature tab
    String SIGNATURE_TAB                = "//span[@class='k-link']/span[contains(text(),'Signature')]";
    String STUDENT_SIGNATURE_INDICATOR  = "//div[@class='row' and contains(string(),'Student Signature Indicator')]//div[@class='details-field' and contains(string(),'%s')]";

    // Audit Log tab
    String AUDIT_LOG_TAB          = "//span[@class='k-link']/span[contains(text(),'Audit Log')]";
    String AUDIT_LOG_ACTIVITY_SOURCE = "//div[@data-rem-id='DocumentActivitiesGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[2]";
    String AUDIT_LOG_USER_ID         = "//div[@data-rem-id='DocumentActivitiesGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[4]";
    String AUDIT_LOG_ACTIVITY_DESC   = "//div[@data-rem-id='DocumentActivitiesGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[5]";
}
