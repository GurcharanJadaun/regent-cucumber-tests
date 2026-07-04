package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.BatchPackageLocators;

/** Ported from regent.pages.BatchPackagePage. */
public class BatchPackagePage extends BasePage {

    public BatchPackagePage(Page page) {
        super(page);
    }

    private void clickAvailableItem(String gridId, String itemText) {
        click(String.format(BatchPackageLocators.AVAILABLE_TABLE_ITEM_ADD, gridId, itemText));
    }

    private boolean isAvailableItemVisible(String gridId, String itemText) {
        return isVisibleNow(String.format(BatchPackageLocators.AVAILABLE_TABLE_ITEM_ADD, gridId, itemText));
    }

    private void filterAvailableGrid(String gridId, String value) {
        click(String.format(BatchPackageLocators.AVAILABLE_FILTER, gridId));
        fill(BatchPackageLocators.FILTER_INPUT, "");
        fill(BatchPackageLocators.FILTER_INPUT, value);
        click(BatchPackageLocators.FILTER_SUBMIT);
        waitForAjaxRequestToBeFinished();
    }

    public void selectEnterprise(String enterpriseItem) {
        clickAvailableItem(BatchPackageLocators.ENTERPRISES_GRID, enterpriseItem);
    }

    public void selectInstitution(String institution) {
        clickAvailableItem(BatchPackageLocators.INSTITUTIONS_GRID, institution);
    }

    public void selectCampus(String campus) {
        if (!isAvailableItemVisible(BatchPackageLocators.LOCATIONS_GRID, campus)) {
            filterAvailableGrid(BatchPackageLocators.LOCATIONS_GRID, campus);
        }
        clickAvailableItem(BatchPackageLocators.LOCATIONS_GRID, campus);
    }

    public void selectSite(String site) {
        clickAvailableItem(BatchPackageLocators.SITES_GRID, site);
    }

    public void selectAllStudents(boolean check) {
        if (page.locator(BatchPackageLocators.SELECT_ALL_STUDENTS_CHECKBOX).isChecked() != check) {
            click(BatchPackageLocators.SELECT_ALL_STUDENTS_CHECKBOX);
        }
    }

    public void selectProgramType(String programType) {
        clickAvailableItem(BatchPackageLocators.PROGRAM_TYPES_GRID, programType);
    }

    public void selectProgram(String program) {
        if (!isAvailableItemVisible(BatchPackageLocators.PROGRAMS_GRID, program)) {
            filterAvailableGrid(BatchPackageLocators.PROGRAMS_GRID, program);
        }
        clickAvailableItem(BatchPackageLocators.PROGRAMS_GRID, program);
    }

    /** Chooses enterprise/institution/campus/site, then filters students down to one by external ID. */
    public void chooseBatchPackageOptions(String enterprise, String institution, String campus, String siteName, String externalStudentId) {
        click(String.format(BatchPackageLocators.LIST_ELEMENT, enterprise));
        click(String.format(BatchPackageLocators.LIST_ELEMENT, institution));
        click(String.format(BatchPackageLocators.LIST_ELEMENT, campus));
        click(String.format(BatchPackageLocators.LIST_ELEMENT, siteName));

        waitForAjaxRequestToBeFinished();
        click(BatchPackageLocators.EXTERNAL_ID_1_FILTER);
        waitForAjaxRequestToBeFinished();
        if (!isVisibleNow(BatchPackageLocators.EXTERNAL_ID_1_FILTER_INPUT)) {
            click(BatchPackageLocators.EXTERNAL_ID_1_FILTER);
        }
        click(BatchPackageLocators.EXTERNAL_ID_1_FILTER_INPUT);
        fill(BatchPackageLocators.EXTERNAL_ID_1_FILTER_INPUT, externalStudentId);
        click(BatchPackageLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();

        click(String.format(BatchPackageLocators.LIST_ELEMENT, externalStudentId));
        click(BatchPackageLocators.SELECT_ALL_PROGRAM_TYPES_CHECKBOX);
        click(BatchPackageLocators.SELECT_ALL_PROGRAMS_CHECKBOX);
    }

    public void executeBatchPackaging() {
        page.mouse().wheel(0, 5000);
        click(BatchPackageLocators.EXECUTE_BUTTON);
        waitForAjaxRequestToBeFinished();
        page.locator(BatchPackageLocators.EXECUTE_BUTTON).waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN));
        page.locator(BatchPackageLocators.TOAST).waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN));
    }
}
