package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.DocumentsLocators;

import java.nio.file.Paths;
import java.util.List;

/**
 * Ported from regent.pages.student.DocumentsPage — only the add-document and
 * change-status flows needed by the SEIAwardUsingDocumentTest scenarios.
 */
public class DocumentsPage extends BasePage {

    public DocumentsPage(Page page) {
        super(page);
    }

    /**
     * On a student's first-ever visit to Documents right after an SBL/ISIR import, the server
     * computes applicable document requirements before the tab's content (including the Add
     * Document button) renders — observed to take well over the default 30s action timeout for
     * freshly-imported students, though instant for existing ones. Poll well beyond that before
     * the first interaction instead of relying on a single readiness check.
     */
    private void waitForDocumentsTabReady() {
        for (int i = 0; i < 30; i++) {
            if (page.locator(DocumentsLocators.ADD_DOCUMENT_BUTTON).count() > 0) return;
            page.waitForTimeout(3000);
        }
    }

    /**
     * Adds a document requirement by name. Matches fillFormsAndAddDocument() for documents
     * scoped to "Student" (no Academic Year / Federal Award Year picker); documentUseTypeCode
     * and documentId are real native &lt;select&gt; elements.
     */
    public void addDocument(String documentName) {
        waitForDocumentsTabReady();
        click(DocumentsLocators.ADD_DOCUMENT_BUTTON);
        click(DocumentsLocators.DOCUMENT_REQUIREMENT_LINK);
        page.locator(DocumentsLocators.DOCUMENT_USE_TYPE_SELECT).selectOption("All");
        page.locator(DocumentsLocators.DOCUMENT_ID_SELECT).selectOption(
                new com.microsoft.playwright.options.SelectOption().setLabel(documentName));
        waitForAjaxRequestToBeFinished();
        click(DocumentsLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnDocument(String documentName) {
        String selector = String.format(DocumentsLocators.DOCUMENT_ROW_BY_NAME, documentName);
        click(selector);
    }

    public void clickEditButton() {
        click(DocumentsLocators.EDIT_BUTTON);
    }

    public void changeDocumentStatus(String status) {
        click(DocumentsLocators.DOCUMENT_STATUS_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        String optionSelector = String.format(DocumentsLocators.DOCUMENT_STATUS_OPTION, status);
        click(optionSelector);
    }

    public void clickSaveButton() {
        click(DocumentsLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** Convenience: open an existing document, edit it, and set its status. */
    public void setStatusForDocument(String documentName, String status) {
        clickOnDocument(documentName);
        clickEditButton();
        changeDocumentStatus(status);
        clickSaveButton();
    }

    // ── Queries ──────────────────────────────────────────────────────────────

    public boolean isDocumentStatus(String status, String documentName) {
        String selector = String.format(DocumentsLocators.DOCUMENT_ROW_WITH_STATUS, status, documentName);
        return isVisible(selector);
    }

    public boolean isDocumentPresent(String documentName) {
        String selector = String.format(DocumentsLocators.DOCUMENT_ROW_BY_NAME, documentName);
        return page.locator(selector).count() > 0;
    }

    public String getDocumentGridStatus(String documentName) {
        return getDocumentGridCell(documentName, 11);
    }

    public String getDocumentGridGeneralScope(String documentName) {
        return getDocumentGridCell(documentName, 12);
    }

    public String getDocumentGridScopeValue(String documentName) {
        return getDocumentGridCell(documentName, 13);
    }

    private String getDocumentGridCell(String documentName, int cellIndex) {
        String rowSelector = String.format(DocumentsLocators.DOCUMENT_GRID_ROW_CELLS, documentName);
        return page.locator(rowSelector).nth(cellIndex - 1).textContent().trim();
    }

    public String getReason(String documentName) {
        String selector = String.format(DocumentsLocators.DOCUMENT_REASON_CELL, documentName);
        return getText(selector);
    }

    public boolean isPaperClipDisplayed(String documentName) {
        String selector = String.format(DocumentsLocators.PAPER_CLIP_ICON, documentName);
        return isVisible(selector);
    }

    public boolean isSubmittedDocument(String fileName) {
        String selector = String.format(DocumentsLocators.SUBMITTED_DOCUMENT_ROW, fileName);
        return isVisible(selector);
    }

    public boolean isSubmittedDocumentDownloadable(String fileName) {
        String selector = String.format(DocumentsLocators.SUBMITTED_DOCUMENT_LINK, fileName);
        return isVisible(selector);
    }

    public boolean verifyStudentSignature(String signatureIndicator) {
        click(DocumentsLocators.SIGNATURE_TAB);
        String selector = String.format(DocumentsLocators.STUDENT_SIGNATURE_INDICATOR, signatureIndicator);
        return isVisible(selector);
    }

    public boolean isGeneralOfficeUseOnly() {
        return isVisible(DocumentsLocators.OFFICE_USE_ONLY_DETAILS_FIELD);
    }

    private String getDetailsFieldValue(String label) {
        String selector = String.format(DocumentsLocators.DETAILS_FIELD_VALUE_BY_LABEL, label);
        return getText(selector);
    }

    public String getGeneralCreatedDate()                    { return getDetailsFieldValue("Created Date"); }
    public String getGeneralDocumentRequirement()             { return getDetailsFieldValue("Document Requirement"); }
    public String getGeneralDocumentExternalId()              { return getDetailsFieldValue("Document External Id"); }
    public String getGeneralDocumentRequirementExternalId()   { return getDetailsFieldValue("Document Requirement External Id"); }
    public String getGeneralActivitySource()                  { return getDetailsFieldValue("Activity Source"); }
    public String getGeneralActivityType()                    { return getDetailsFieldValue("Activity Type"); }
    public String getGeneralLetterCorrespondenceDate()        { return getDetailsFieldValue("Letter Correspondence Date"); }
    public String getGeneralEmailCorrespondenceDate()         { return getDetailsFieldValue("Email Correspondence Date"); }
    public String getGeneralReceivedOn()                      { return getDetailsFieldValue("Received On"); }
    public String getGeneralReviewedOn()                      { return getDetailsFieldValue("Reviewed On"); }
    public String getGeneralStatusChangedDate()               { return getDetailsFieldValue("Status Changed Date"); }
    public String getGeneralDueDate()                         { return getDetailsFieldValue("Due Date"); }
    public String getGeneralExpirationDate()                  { return getDetailsFieldValue("Expiration Date"); }
    public String getGeneralMessage()                         { return getDetailsFieldValue("Message"); }
    public String getGeneralReason()                          { return getDetailsFieldValue("Reason"); }
    public String getGeneralNotes()                           { return getDetailsFieldValue("Notes"); }
    public String getGeneralAssignedBy()                      { return getDetailsFieldValue("Assigned By"); }
    public String getGeneralDocumentUse()                     { return getDetailsFieldValue("Document Use"); }
    public String getGeneralScopeValue()                      { return getDetailsFieldValue("Scope Value"); }
    public String getGeneralIsirScope()                       { return getDetailsFieldValue("ISIR Scope"); }
    public String getGeneralStatus()                          { return getDetailsFieldValue("Status:"); }

    /** "Scope:" label matches both the summary field and its value line; source takes the 2nd match. */
    public String getGeneralScope() {
        String selector = String.format(DocumentsLocators.DETAILS_FIELD_VALUE_BY_LABEL, "Scope:");
        return page.locator(selector).nth(1).textContent().trim();
    }

    public String getActivitySource(String rowKey) {
        return getText(String.format(DocumentsLocators.AUDIT_LOG_ACTIVITY_SOURCE, rowKey));
    }

    public String getUserId(String rowKey) {
        return getText(String.format(DocumentsLocators.AUDIT_LOG_USER_ID, rowKey));
    }

    public String getActivityDescription(String rowKey) {
        return getText(String.format(DocumentsLocators.AUDIT_LOG_ACTIVITY_DESC, rowKey));
    }

    public void clickAuditLogTab() {
        click(DocumentsLocators.AUDIT_LOG_TAB);
        waitForAjaxRequestToBeFinished();
    }

    // ── Add-document scope pickers (Academic Year / Federal Award Year) ────────

    /** After picking a document, some requirements additionally scope by AY or FAY; others don't. */
    private void selectScopeYearIfPresent() {
        if (!isVisibleNow(DocumentsLocators.SCOPE_NAME)) return;
        String scope = getText(DocumentsLocators.SCOPE_NAME);
        if (scope.equals("Academic Year")) {
            click(DocumentsLocators.ACADEMIC_YEAR_DROPDOWN);
            click(String.format(DocumentsLocators.ACADEMIC_YEAR_OPTION, "2"));
        } else if (scope.equals("Federal Award Year")) {
            selectFederalAwardYear(getCurrentFederalAwardYears());
        }
    }

    /** Ported from StringUtils.getCurrentFayYears() usage: "<year>-<year+1>" straddling June 30 cutover. */
    private String getCurrentFederalAwardYears() {
        java.time.LocalDate now = java.time.LocalDate.now();
        int startYear = now.getMonthValue() >= 7 ? now.getYear() : now.getYear() - 1;
        return startYear + "-" + (startYear + 1);
    }

    public void selectFederalAwardYear(String federalAwardYear) {
        click(DocumentsLocators.FEDERAL_AWARD_YEAR_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(DocumentsLocators.FEDERAL_AWARD_YEAR_OPTION, federalAwardYear));
        waitForAjaxRequestToBeFinished();
    }

    /** Selects a required document without saving — used when scope pickers / attachments follow. */
    public void selectRequiredDocument(String documentName) {
        waitForAjaxRequestToBeFinished();
        click(DocumentsLocators.DOCUMENT_REQUIREMENT_LINK);
        waitForAjaxRequestToBeFinished();
        page.locator(DocumentsLocators.DOCUMENT_ID_SELECT).selectOption(
                new com.microsoft.playwright.options.SelectOption().setLabel(documentName));
        waitForAjaxRequestToBeFinished();
    }

    /** Adds a document and attaches a file from src/test/resources in the same form submission. */
    public void fillFormsAndAddDocument(String attachment, String documentName) {
        click(DocumentsLocators.DOCUMENT_REQUIREMENT_LINK);
        page.locator(DocumentsLocators.DOCUMENT_USE_TYPE_SELECT).selectOption("All");
        page.locator(DocumentsLocators.DOCUMENT_ID_SELECT).selectOption(
                new com.microsoft.playwright.options.SelectOption().setLabel(documentName));
        waitForAjaxRequestToBeFinished();
        selectScopeYearIfPresent();
        page.locator(DocumentsLocators.SELECT_SUBMITTED_DOCUMENTS_INPUT)
                .setInputFiles(Paths.get("src/test/resources/" + attachment));
        waitForLoadmaskGone();
        clickSaveButton();
    }

    public void attachSubmittedDocument(String documentFileName) {
        clickEditButton();
        page.locator(DocumentsLocators.SELECT_SUBMITTED_DOCUMENTS_INPUT)
                .setInputFiles(Paths.get("src/test/resources/" + documentFileName));
        waitForLoadmaskGone();
        clickSaveButton();
    }

    private void waitForLoadmaskGone() {
        page.locator(DocumentsLocators.LOADMASK).waitFor(
                new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN)
                        .setTimeout(timeout));
    }

    // ── Deletion ─────────────────────────────────────────────────────────────

    public void deleteDocument(String documentName) {
        String checkboxSelector = String.format(DocumentsLocators.DOCUMENT_ROW_CHECKBOX, documentName);
        click(checkboxSelector);
        click(DocumentsLocators.DELETE_BUTTON);
        waitForLoadmaskGone();
    }

    public void deleteDocumentIfExists(String documentName) {
        if (isDocumentPresent(documentName)) {
            deleteDocument(documentName);
        }
    }

    public void deleteAllDocuments() {
        click(DocumentsLocators.SELECT_ALL_DOCUMENTS_CHECKBOX);
        click(DocumentsLocators.DELETE_BUTTON);
        waitForLoadmaskGone();
    }

    // ── Bulk / row-level status change (StatusCode Kendo dropdown + grid checkboxes) ─────────

    public void changeAllDocumentsStatus(String status) {
        waitForAjaxRequestToBeFinished();
        if (page.locator(DocumentsLocators.DOCUMENT_ROWS).count() == 0) return;
        click(DocumentsLocators.SELECT_ALL_DOCUMENTS_CHECKBOX);
        applyBulkStatus(status);
    }

    public void changeDocumentStatusViaGrid(String documentName, String status) {
        String checkboxSelector = String.format(DocumentsLocators.DOCUMENT_ROW_CHECKBOX, documentName);
        click(checkboxSelector);
        applyBulkStatus(status);
    }

    /** Use when documentName alone doesn't uniquely identify the row (needs a second row-key column). */
    public void changeDocumentStatusViaGrid(String documentName, String rowKey, String status) {
        String checkboxSelector = String.format(DocumentsLocators.DOCUMENT_ROW_CELL_CHECKBOX, documentName, rowKey);
        click(checkboxSelector);
        applyBulkStatus(status);
    }

    public void changeDocumentsStatusViaGrid(List<String> documentNames, String status) {
        for (String documentName : documentNames) {
            String checkboxSelector = String.format(DocumentsLocators.DOCUMENT_ROW_CHECKBOX, documentName);
            click(checkboxSelector);
        }
        applyBulkStatus(status);
    }

    private void applyBulkStatus(String status) {
        click(DocumentsLocators.BULK_STATUS_DROPDOWN);
        click(String.format(DocumentsLocators.BULK_STATUS_OPTION, status));
        click(DocumentsLocators.UPDATE_DOCUMENT_STATUS_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    // ── Grid filters & refresh ───────────────────────────────────────────────

    public void refreshDocumentGrid() {
        click(DocumentsLocators.DOCUMENT_GRID_REFRESH_BUTTON);
    }

    public void filterOnScope(String scopeValue) {
        applyColumnFilter("Scope Value", scopeValue);
    }

    public void filterOnName(String documentName) {
        applyColumnFilter("Name", documentName);
    }

    public void filterOnStatus(String status) {
        applyColumnFilter("Status", status);
    }

    private void applyColumnFilter(String columnTitle, String value) {
        click(String.format(DocumentsLocators.SCOPE_COLUMN_FILTER_TRIGGER, columnTitle));
        fill(DocumentsLocators.COLUMN_FILTER_VALUE_INPUT, value);
        click(DocumentsLocators.COLUMN_FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** Edits a document to add a status-change reason (e.g. rejection note). */
    public void enterReason(String reason) {
        clickEditButton();
        fill(DocumentsLocators.INPUT_REASON, reason);
        clickSaveButton();
    }
}
