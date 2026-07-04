package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.PortalFinancialAidLocators;

/**
 * Ported from regent.pages.PortalFinancialAidPage. Source extended PortalConfigurationPage for
 * tab navigation, which is out of scope for this page object (belongs on a PortalConfigurationPage
 * port, not created here).
 */
public class PortalFinancialAidPage extends BasePage {

    public PortalFinancialAidPage(Page page) {
        super(page);
    }

    public void editFinancialAid() {
        click(PortalFinancialAidLocators.EDIT_BUTTON);
    }

    public void saveChanges() {
        click(PortalFinancialAidLocators.SAVE_BUTTON);
    }

    public void editDisplayOnDashboardCheckbox(boolean check) {
        setCheckbox(PortalFinancialAidLocators.DISPLAY_ON_DASHBOARD_CHECKBOX, check);
    }

    public void editDisplayDirectCostsCheckbox(boolean check) {
        setCheckbox(PortalFinancialAidLocators.DISPLAY_DIRECT_COSTS_CHECKBOX, check);
    }

    public void editDisplayCostOfAttendanceCheckbox(boolean check) {
        setCheckbox(PortalFinancialAidLocators.DISPLAY_COST_OF_ATTENDANCE_CHECKBOX, check);
    }

    private void setCheckbox(String selector, boolean check) {
        com.microsoft.playwright.Locator box = page.locator(selector);
        if (box.isChecked() != check) box.click();
    }

    private void selectDropdown(String listboxId, String value) {
        click(String.format(PortalFinancialAidLocators.DROPDOWN_TRIGGER, listboxId));
        click(String.format(PortalFinancialAidLocators.DROPDOWN_OPTION, listboxId, value));
    }

    public void editDisplayStatusAwards(String displayDeclinedAwards, String displayCancelledAwards, String displayEstimatedAwards) {
        click(PortalFinancialAidLocators.EDIT_BUTTON);
        selectDropdown(PortalFinancialAidLocators.DISPLAY_DECLINED_AWARDS_LISTBOX, displayDeclinedAwards);
        selectDropdown(PortalFinancialAidLocators.DISPLAY_CANCELLED_AWARDS_LISTBOX, displayCancelledAwards);
        selectDropdown(PortalFinancialAidLocators.DISPLAY_ESTIMATED_AWARDS_LISTBOX, displayEstimatedAwards);
        click(PortalFinancialAidLocators.SAVE_BUTTON);
    }

    public String getHideAwardCostWhenOverlapExists() {
        return getText(String.format(PortalFinancialAidLocators.FA_DETAILED_FIELD_VALUE_BY_LABEL,
                "Hide Awards and Costs when Overlap Exists"));
    }

    public void editHideAwardsCostsWhenOverlapExists(String hideOverlapAwards) {
        click(PortalFinancialAidLocators.EDIT_BUTTON);
        selectDropdown(PortalFinancialAidLocators.HIDE_LOANS_WHEN_OVERLAP_EXISTS_LISTBOX, hideOverlapAwards);
        click(PortalFinancialAidLocators.SAVE_BUTTON);
    }

    public String getOverlapMessage() {
        return getText(String.format(PortalFinancialAidLocators.FA_DETAILED_FIELD_VALUE_BY_LABEL, "Overlap Message"));
    }
}
