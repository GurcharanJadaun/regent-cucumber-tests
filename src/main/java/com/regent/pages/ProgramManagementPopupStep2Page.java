package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.regent.locators.ProgramManagementPopupStep2Locators;

/** Ported from regent.pages.student.ProgramManagementPopupStep2Page. */
public class ProgramManagementPopupStep2Page extends BasePage {

    public ProgramManagementPopupStep2Page(Page page) {
        super(page);
    }

    /** Waits up to ~3 minutes for the Finish button to become enabled, then clicks it. */
    public void clickFinish() {
        Locator finishButton = page.locator(ProgramManagementPopupStep2Locators.FINISH_BUTTON);
        for (int i = 0; i <= 180; i++) {
            page.waitForTimeout(1000);
            if (finishButton.isVisible() && finishButton.isEnabled()) break;
        }
        finishButton.click();
        waitForAjaxRequestToBeFinished();
    }
}
