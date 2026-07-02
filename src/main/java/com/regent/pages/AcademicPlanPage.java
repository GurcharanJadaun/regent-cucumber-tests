package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.AcademicPlanLocators;
import org.testng.Assert;

public class AcademicPlanPage extends BasePage {

    private static final int MAX_REFRESH = 400;

    public AcademicPlanPage(Page page) {
        super(page);
    }

    // ── Map / packaging ───────────────────────────────────────────────────────

    public void clickMapButton() {
        click(AcademicPlanLocators.MAP_BUTTON);
        page.waitForTimeout(1000);
    }

    /** Click "Basic Packaging" in the Map popup (no log-level override). */
    public void runBasicPackaging() {
        click(AcademicPlanLocators.BASIC_PACKAGING_BTN);
        page.waitForTimeout(1000);
    }

    /** Wait up to ~2 minutes for the Finish button to become enabled, then click it. */
    public void clickFinish() {
        for (int i = 0; i <= 60; i++) {
            page.waitForTimeout(2000);
            Locator finishBtn = page.locator(AcademicPlanLocators.FINISH_BUTTON);
            if (finishBtn.count() > 0 && finishBtn.isEnabled()) {
                finishBtn.click();
                waitForPageLoad();
                return;
            }
        }
        Assert.fail("Finish button never became enabled after packaging");
    }

    /**
     * After clicking Finish, poll the Academic Plan page until the plan appears
     * (up to ~20 minutes, matching original Selenium wait).
     */
    public void waitForAcademicPlanAppear() {
        for (int i = 0; i < MAX_REFRESH; i++) {
            // If the "No Current Academic Plan" message is gone, we're done
            if (!isVisible(AcademicPlanLocators.NO_PLAN_MESSAGE)) return;

            try {
                page.locator(AcademicPlanLocators.REFRESH_BUTTON).click();
            } catch (Exception ignored) {}
            page.waitForTimeout(3000);
        }
        Assert.fail("Academic Plan did not appear after packaging (timeout ~20 min)");
    }

    // ── Award verification ────────────────────────────────────────────────────

    public boolean isAwardPresent(String awardName) {
        String selector = String.format(AcademicPlanLocators.AWARD_ROW_BY_NAME, awardName);
        return isVisible(selector);
    }

    public boolean isPellGrantAwarded()      { return isAwardPresent(AcademicPlanLocators.PELL_GRANT); }
    public boolean isDlSubsidizedAwarded()   { return isAwardPresent(AcademicPlanLocators.DL_SUBSIDIZED); }
    public boolean isDlUnsubsidizedAwarded() { return isAwardPresent(AcademicPlanLocators.DL_UNSUBSIDIZED); }
}
