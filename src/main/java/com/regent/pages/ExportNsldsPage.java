package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ExportNsldsLocators;

/**
 * Ported from regent.pages.ExportNsldsPage. The source pulled enterpriseId/institutionId/OPE ID
 * from EnterpriseBuilder test-data config; those are supplied as explicit parameters here instead
 * since that config layer isn't part of this batch. Returns void — the source's Dashboard return
 * type is out of scope; callers construct Dashboard themselves once ported.
 */
public class ExportNsldsPage extends BasePage {

    public ExportNsldsPage(Page page) {
        super(page);
    }

    public void executeExportNslds(String enterpriseId, String institutionId, String federalSchoolCode,
                                    String fileType, String population, String registeredUnits,
                                    String daysBeforeAYStart, String daysBeforeDisbursement, String monitorDate) {
        selectDropdownValue("EnterpriseId", enterpriseId);
        selectDropdownValue("InstitutionId", institutionId);
        selectDropdownValue("IOProcess_OPEId", federalSchoolCode);
        selectDropdownValue("IOProcess_NSLDSExportTypeCode", fileType);
        selectDropdownValue("IOProcess_NSLDSStudentPopulationCode", population);
        selectDropdownValue("IOProcess_StudentsRegisteredUnits", registeredUnits);
        if (daysBeforeAYStart != null && !daysBeforeAYStart.isEmpty()) {
            fill(ExportNsldsLocators.DAYS_BEFORE_ACADEMIC_YEAR_START, daysBeforeAYStart);
        }
        if (daysBeforeDisbursement != null && !daysBeforeDisbursement.isEmpty()) {
            fill(ExportNsldsLocators.DAYS_BEFORE_SCHEDULED_DISBURSEMENT, daysBeforeDisbursement);
        }
        fill(ExportNsldsLocators.BEGIN_MONITORING_DATE, monitorDate);
        click(ExportNsldsLocators.EXPORT_BUTTON);
    }

    private void selectDropdownValue(String dropdownId, String optionText) {
        click(String.format(ExportNsldsLocators.DROPDOWN_SELECT, dropdownId));
        click(String.format(ExportNsldsLocators.OPTION_ITEM, optionText));
    }
}
