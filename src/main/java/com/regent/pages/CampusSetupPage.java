package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.CampusSetupLocators;

/** Ported from regent.pages.CampusSetupPage. clickOnR2T4Tab() (navigates to CampusR2T4Page) is
 * not ported — that page object is out of scope for this batch. */
public class CampusSetupPage extends BasePage {

    public CampusSetupPage(Page page) {
        super(page);
    }

    public String getExternalCampusId() {
        return getText(CampusSetupLocators.EXTERNAL_ID);
    }

    public String getFederalSchoolId() {
        return getText(CampusSetupLocators.FEDERAL_SCHOOL_ID);
    }

    public void clickEdit() {
        click(CampusSetupLocators.EDIT_CAMPUS_BUTTON);
    }

    public void setDelayFirstDisbursement30Days(String value) {
        click(CampusSetupLocators.DELAY_FIRST_DISBURSEMENT_30_DAYS_DROPDOWN);
        click(String.format(CampusSetupLocators.DELAY_FIRST_DISBURSEMENT_30_DAYS_OPTION, value));
    }

    public void saveChanges() {
        click(CampusSetupLocators.SAVE_BUTTON);
    }
}
