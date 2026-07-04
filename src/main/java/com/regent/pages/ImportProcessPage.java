package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.ImportProcessLocators;
import com.regent.utils.IsirFileBuilder;
import com.regent.utils.SblXmlBuilder;
import com.regent.utils.StudentUser;

import java.nio.file.Paths;
import java.util.Map;

public class ImportProcessPage extends BasePage {

    public ImportProcessPage(Page page) {
        super(page);
    }

    public void importSbl(StudentUser student, String sblOption, String termType,
                          Map<String, String> enterpriseConfig) {
        selectImportType(ImportProcessLocators.IMPORT_SBL);
        String filePath = SblXmlBuilder.build(student, sblOption, termType, enterpriseConfig);
        uploadFileAndSubmit(filePath);
    }

    public void importIsir(StudentUser student, String isirType, String federalSchoolCode) {
        selectImportType(ImportProcessLocators.IMPORT_ISIR);
        String filePath = IsirFileBuilder.build(student, isirType, federalSchoolCode);
        uploadFileAndSubmit(filePath);
    }

    /** Generic process-type selection, exposed for import flows beyond SBL/ISIR (e.g. State Grants). */
    public void chooseImportProcess(String importType) {
        selectImportType(importType);
    }

    /**
     * "Import State Grants" reveals a second Kendo dropdown for the state/province; both the
     * Wisconsin State Grant and Ohio OCOG import flows in the source select it the same way
     * before uploading their respective files.
     */
    public void selectStateProvince(String stateProvince) {
        click(ImportProcessLocators.STATE_PROVINCE_DROPDOWN);
        click(String.format(ImportProcessLocators.STATE_PROVINCE_OPTION, stateProvince));
        waitForAjaxRequestToBeFinished();
    }

    /**
     * Shown when an imported ISIR has no matching student yet: ticks "Create Student" and
     * cascades through Enterprise -> Institution -> Campus, each dropdown populating the next.
     */
    public void createStudentFromIsir(String enterprise, String institution, String campus) {
        click(ImportProcessLocators.CREATE_STUDENT_CHECKBOX);
        click(ImportProcessLocators.ENTERPRISE_DROPDOWN);
        click(String.format(ImportProcessLocators.CASCADING_DROPDOWN_OPTION, enterprise));
        click(ImportProcessLocators.INSTITUTION_DROPDOWN);
        click(String.format(ImportProcessLocators.CASCADING_DROPDOWN_OPTION, institution));
        click(ImportProcessLocators.CAMPUS_DROPDOWN);
        click(String.format(ImportProcessLocators.CASCADING_DROPDOWN_OPTION, campus));
    }

    /** Uploads an already-built file and submits — exposed for import flows built outside this class. */
    public void uploadFile(String filePath) {
        uploadFileAndSubmit(filePath);
    }

    private void selectImportType(String importType) {
        click(ImportProcessLocators.PROCESS_TYPE_DROPDOWN);
        String optionSelector = String.format(ImportProcessLocators.PROCESS_TYPE_OPTION, importType);
        click(optionSelector);
        page.waitForTimeout(500);
    }

    private void uploadFileAndSubmit(String filePath) {
        page.locator(ImportProcessLocators.FILE_UPLOAD_INPUT)
                .setInputFiles(Paths.get(filePath));

        page.locator(ImportProcessLocators.UPLOAD_DONE)
                .waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(timeout));

        click(ImportProcessLocators.SUBMIT_BUTTON);

        // The submit triggers an async loadmask overlay; navigating away before it clears
        // races the actual import registration, so the process never shows up in the log.
        page.locator(ImportProcessLocators.LOADMASK)
                .waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN)
                        .setTimeout(timeout));
        waitForPageLoad();
    }
}
