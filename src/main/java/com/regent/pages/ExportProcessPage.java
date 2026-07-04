package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ExportProcessLocators;

import java.util.List;

/**
 * Ported from regent.pages.ExportProcessPage. Source extended Filter for filterBy(); this
 * composes FilterPage instead since Playwright pages here favor composition over inheritance
 * (see FilterPage).
 */
public class ExportProcessPage extends BasePage {

    private final FilterPage filterPage;

    public ExportProcessPage(Page page) {
        super(page);
        this.filterPage = new FilterPage(page);
    }

    public void chooseProcess(String exportOptionLabel) {
        click(ExportProcessLocators.SELECT_PROCESS_DROPDOWN);
        click(String.format(ExportProcessLocators.PROCESS_OPTION, exportOptionLabel));
    }

    public void chooseExportESTProcess() {
        chooseProcess(ExportProcessLocators.EXPORT_EST);
    }

    public void chooseExportNslds() {
        chooseProcess(ExportProcessLocators.EXPORT_NSLDS_FILES);
    }

    public void selectExportIsirOptionsAndExport(String federalSchoolCode) {
        click(String.format(ExportProcessLocators.ADD_SCHOOL_BUTTON, federalSchoolCode));
        click(ExportProcessLocators.EXPORT_BUTTON);
    }

    public void selectExportIsirOptionsAndExport(String federalAwardYear, String schoolCode) {
        click(ExportProcessLocators.ISIR_FEDERAL_AWARD_YEAR_DROPDOWN);
        click(String.format(ExportProcessLocators.ISIR_FEDERAL_AWARD_YEAR_OPTION, federalAwardYear));
        click(String.format(ExportProcessLocators.ADD_SCHOOL_BUTTON, schoolCode));
        click(ExportProcessLocators.EXPORT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    private void selectDistrict(String fieldId, String value) {
        click(String.format(ExportProcessLocators.DISTRICT_SELECT, fieldId));
        click(String.format(ExportProcessLocators.DISTRICT_ITEM, value));
    }

    public void selectExportCodOptionsAndExport(String externalStudentId, String studentLastName,
                                                 String currentFayYear, String enterprise, String institution,
                                                 String campus, String loanType, boolean dri) {
        selectDistrict("FederalAwardYearId", currentFayYear);

        if (dri) {
            click(ExportProcessLocators.DISBURSEMENT_RECORDS_CHECKBOX);
        } else {
            page.locator(ExportProcessLocators.ORIGINATIONS_CHECKBOX).check();
        }
        selectDistrict("EnterpriseId", enterprise);
        selectDistrict("InstitutionId", institution);
        selectDistrict("LocationId", campus);
        filterPage.filterBy("External ID 1", externalStudentId);
        click(String.format(ExportProcessLocators.STUDENT_ROW_ADD_LINK, studentLastName));
        click(String.format(ExportProcessLocators.FUND_TYPE_ADD_LINK, loanType));
        click(ExportProcessLocators.EXPORT_BUTTON);
    }

    /** Ported from performCOD()/exportFundsToCod() — exports one or more funds to COD for a student. */
    public void exportFundsToCod(String externalStudentId, String studentLastName, String campus,
                                  String federalAwardYear, boolean dri, boolean negativeTransOnly,
                                  List<String> fundTypeList, String enterprise, String institution) {
        chooseProcess(ExportProcessLocators.EXPORT_COD);
        waitForAjaxRequestToBeFinished();
        if (federalAwardYear != null && !federalAwardYear.isEmpty()) {
            selectDistrict("FederalAwardYearId", federalAwardYear);
        }
        if (dri) {
            click(ExportProcessLocators.DISBURSEMENT_RECORDS_CHECKBOX);
        } else {
            page.locator(ExportProcessLocators.ORIGINATIONS_CHECKBOX).check();
        }
        if (negativeTransOnly) {
            page.locator(ExportProcessLocators.NEGATIVE_TRANSACTIONS_ONLY_CHECKBOX).check();
        }
        selectDistrict("EnterpriseId", enterprise);
        selectDistrict("InstitutionId", institution);
        selectDistrict("LocationId", campus);
        filterPage.filterBy("External ID 1", externalStudentId);
        click(String.format(ExportProcessLocators.STUDENT_ROW_ADD_LINK, studentLastName));

        for (String fundToExport : fundTypeList) {
            click(String.format(ExportProcessLocators.FUND_TYPE_ADD_LINK, fundToExport));
        }

        click(ExportProcessLocators.EXPORT_BUTTON_CURRENT);
    }
}
