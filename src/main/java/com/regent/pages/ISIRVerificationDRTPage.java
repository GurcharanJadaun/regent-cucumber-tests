package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ISIRVerificationDRTLocators;

/** Ported from regent.pages.reports.ISIRVerificationDRTPage (SSRS ReportViewer report, not Kendo). */
public class ISIRVerificationDRTPage extends BasePage {

    public ISIRVerificationDRTPage(Page page) {
        super(page);
        waitForAjaxRequestToBeFinished();
        waitForReportParametersReady();
    }

    /** Report parameter panel renders asynchronously after the viewer frame loads. */
    private void waitForReportParametersReady() {
        for (int i = 0; i < 240; i++) {
            com.microsoft.playwright.Locator dropdown = page.locator(ISIRVerificationDRTLocators.ENTERPRISE_DROPDOWN);
            if (dropdown.count() > 0 && dropdown.isVisible() && dropdown.isEnabled()) return;
            page.waitForTimeout(1000);
        }
    }

    /** Polls the "Loading..." indicator away — SSRS re-renders the report asynchronously. */
    private void waitForReportReady() {
        for (int i = 0; i < 120; i++) {
            if (!isVisibleNow(ISIRVerificationDRTLocators.LOADING_INDICATOR)) return;
            page.waitForTimeout(1000);
        }
    }

    public void setEnterprise(String enterpriseName) {
        waitForAjaxRequestToBeFinished();
        page.locator(ISIRVerificationDRTLocators.ENTERPRISE_DROPDOWN).selectOption(enterpriseName);
        waitForAjaxRequestToBeFinished();
    }

    public void setInstitution(String institutionName) {
        click(ISIRVerificationDRTLocators.INSTITUTION_INPUT);
        click(ISIRVerificationDRTLocators.INSTITUTION_CHECKBOX);
        click(ISIRVerificationDRTLocators.ENTERPRISE_LABEL);
        waitForAjaxRequestToBeFinished();
    }

    public void viewReport() {
        click(ISIRVerificationDRTLocators.VIEW_REPORT_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForReportReady();
    }

    public void setCampus(String campusId) {
        click(ISIRVerificationDRTLocators.CAMPUS_DROPDOWN);
        String checkboxSelector = String.format(ISIRVerificationDRTLocators.CAMPUS_OPTION_CB, campusId);
        if (!page.locator(checkboxSelector).isChecked()) {
            click(checkboxSelector);
            waitForAjaxRequestToBeFinished();
        }
        click(ISIRVerificationDRTLocators.ENTERPRISE_LABEL);
        waitForAjaxRequestToBeFinished();
    }

    public void setFederalAwardYear(String federalAwardYear) {
        page.locator(ISIRVerificationDRTLocators.FEDERAL_AWARD_YEAR_SELECT).selectOption(federalAwardYear);
        waitForAjaxRequestToBeFinished();
        waitForReportReady();
        waitForReportParametersReady();
    }

    public void setSelectedForVerification(boolean selected) {
        waitForAjaxRequestToBeFinished();
        click(ISIRVerificationDRTLocators.SELECTED_FOR_VERIFICATION_DROPDOWN);
        uncheck(ISIRVerificationDRTLocators.SELECTED_FOR_VERIFICATION_ALL_CB);
        click(selected ? ISIRVerificationDRTLocators.SELECTED_FOR_VERIFICATION_Y_CB
                        : ISIRVerificationDRTLocators.SELECTED_FOR_VERIFICATION_N_CB);
        click(ISIRVerificationDRTLocators.ENTERPRISE_LABEL);
        waitForAjaxRequestToBeFinished();
    }

    public void setVerificationGroup(String verificationGroup) {
        String group = verificationGroup.toUpperCase();
        click(ISIRVerificationDRTLocators.VERIFICATION_TRACKING_GROUP_DROPDOWN);
        uncheck(ISIRVerificationDRTLocators.GROUP_SELECT_ALL_CB);

        String targetCheckbox;
        switch (group) {
            case "V1": targetCheckbox = ISIRVerificationDRTLocators.GROUP_V1_CB; break;
            case "V2": targetCheckbox = ISIRVerificationDRTLocators.GROUP_V2_CB; break;
            case "V3": targetCheckbox = ISIRVerificationDRTLocators.GROUP_V3_CB; break;
            case "V4": targetCheckbox = ISIRVerificationDRTLocators.GROUP_V4_CB; break;
            case "V5": targetCheckbox = ISIRVerificationDRTLocators.GROUP_V5_CB; break;
            case "V6": targetCheckbox = ISIRVerificationDRTLocators.GROUP_V6_CB; break;
            case "NONE": targetCheckbox = ISIRVerificationDRTLocators.GROUP_NONE_CB; break;
            default: throw new IllegalArgumentException("Unknown verification group: " + verificationGroup);
        }
        check(targetCheckbox);
        click(ISIRVerificationDRTLocators.ENTERPRISE_LABEL);
        waitForAjaxRequestToBeFinished();
    }

    public boolean isStudentFound(String studentKey) {
        fill(ISIRVerificationDRTLocators.FIND_TEXT_INPUT, studentKey);
        click(ISIRVerificationDRTLocators.FIND_LINK);
        waitForAjaxRequestToBeFinished();
        waitForReportReady();
        String selector = String.format(ISIRVerificationDRTLocators.REPORT_ROW, studentKey);
        return isVisible(selector);
    }

    private void check(String selector) {
        if (!page.locator(selector).isChecked()) click(selector);
    }

    private void uncheck(String selector) {
        if (page.locator(selector).isChecked()) click(selector);
    }
}
