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
