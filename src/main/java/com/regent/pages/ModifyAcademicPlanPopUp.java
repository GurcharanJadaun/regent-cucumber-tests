package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.ModifyAcademicPlanLocators;

/**
 * Ported from regent.pages.ModifyAcademicPlanPopUp — the modify-plan wizard's advanced options
 * (log level override, packaging philosophy, enrollment level override, disbursement/unit
 * adjustments). Kept separate from AcademicPlanPage, which only covers the basic Map/packaging
 * flow (clickMapButton/runBasicPackaging/clickFinish/waitForAcademicPlanAppear).
 *
 * Methods that returned a next-page object in the source (finishMap/cancelMap -> AcademicPlanPage)
 * are void here, matching this project's convention (see AddCostPopUp) of letting the step
 * definition instantiate the next page object itself.
 *
 * Skipped downloadAnalysisContext() — wrapped Utils.blobDownload(), a Selenium-specific browser
 * download-directory scrape with no Playwright equivalent available in BasePage; Playwright's
 * own page.waitForDownload() API would need wiring at the step-definition/test-fixture level.
 * Skipped changeUnits(period, units) — used Selenium Keys.chord(CONTROL, "a") + BACK_SPACE on a
 * raw WebElement to select-all-and-clear a Kendo numeric input; port via BasePage.fill() once a
 * concrete scenario needs it, since the two-input click-then-type sequence doesn't map cleanly to
 * fill() without seeing the widget live.
 */
public class ModifyAcademicPlanPopUp extends BasePage {

    public ModifyAcademicPlanPopUp(Page page) {
        super(page);
    }

    private void waitIfBlocked() {
        int refreshCount = 0;
        while (isVisibleNow(ModifyAcademicPlanLocators.PACKAGING_ERROR_MESSAGE) && refreshCount < 120) {
            click(ModifyAcademicPlanLocators.REFRESH_WINDOW_BUTTON);
            refreshCount++;
        }
    }

    private void waitForLoadingMaskToClear() {
        if (isVisibleNow(ModifyAcademicPlanLocators.LOADING_MASK)) {
            page.locator(ModifyAcademicPlanLocators.LOADING_MASK).waitFor(
                    new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        }
    }

    private void setPackagingOptions(boolean useEnrollment, boolean useCaptureContext, String enrollmentLevelText) {
        waitForLoadingMaskToClear();
        waitIfBlocked();
        if (useCaptureContext) {
            click(ModifyAcademicPlanLocators.OVERRIDE_LOG_LEVEL_DROPDOWN);
            click(ModifyAcademicPlanLocators.LOG_TRACE_LEVEL_VERBOSE);
            page.locator(ModifyAcademicPlanLocators.CAPTURE_CONTEXT_CHECKBOX).check();
        }
        if (useEnrollment) {
            click(ModifyAcademicPlanLocators.ANTICIPATED_ENROLLMENT_LEVEL_SELECT);
            click(String.format(ModifyAcademicPlanLocators.ANTICIPATED_ENROLLMENT_LEVEL_OPTION, enrollmentLevelText));
        }
        click(ModifyAcademicPlanLocators.BASIC_PACKAGING_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadingMaskToClear();
    }

    public void chooseLogLevelAndRunBasicPackaging() {
        setPackagingOptions(false, false, "Full Time");
    }

    public void choosePackagingPhilosophyAndRunBasicPackaging(String packagingPhilosophyType, String planPackagingPhilosophy) {
        waitIfBlocked();
        if (!packagingPhilosophyType.isEmpty()) {
            click(ModifyAcademicPlanLocators.PACKAGING_PHILOSOPHY_TYPE_SELECT);
            click(String.format(ModifyAcademicPlanLocators.PACKAGING_PHILOSOPHY_TYPE_OPTION, packagingPhilosophyType));
        }
        if (!planPackagingPhilosophy.isEmpty()) {
            click(ModifyAcademicPlanLocators.PLAN_PACKAGING_PHILOSOPHY_SELECT);
            click(String.format(ModifyAcademicPlanLocators.PLAN_PACKAGING_PHILOSOPHY_OPTION, planPackagingPhilosophy));
        }
        click(ModifyAcademicPlanLocators.BASIC_PACKAGING_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** Waits up to ~2 minutes for the Finish button to become enabled, then clicks it. */
    public void finishMap() {
        for (int i = 0; i <= 60; i++) {
            page.waitForTimeout(2000);
            if (isVisibleNow(ModifyAcademicPlanLocators.FINISH_BUTTON)
                    && page.locator(ModifyAcademicPlanLocators.FINISH_BUTTON).isEnabled()) {
                break;
            }
        }
        click(ModifyAcademicPlanLocators.FINISH_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void runMapWithEnrollmentLevel(String enrollmentLevelText) {
        setPackagingOptions(true, false, enrollmentLevelText);
    }

    public void runMapAndCaptureContext() {
        setPackagingOptions(false, true, "Full Time");
    }

    /** This one gives an error BEFORE any fund is even selected. */
    public boolean isBlockingError(String expectedErrorMessage) {
        String selector = String.format(ModifyAcademicPlanLocators.STEP1_BLOCKING_ERROR_MESSAGE,
                "Packaging is being blocked due to:", expectedErrorMessage);
        return isVisible(selector);
    }

    public void cancelMap() {
        click(ModifyAcademicPlanLocators.CANCEL_BUTTON);
    }

    public String getValidationError() {
        StringBuilder errorMessage = new StringBuilder();
        for (Locator error : page.locator(ModifyAcademicPlanLocators.VALIDATION_ERRORS).all()) {
            errorMessage.append(error.textContent());
        }
        return errorMessage.toString();
    }

    public void clickBasicPackaging() {
        for (int i = 0; i <= 60; i++) {
            if (isVisibleNow(ModifyAcademicPlanLocators.BASIC_PACKAGING_BUTTON)
                    && page.locator(ModifyAcademicPlanLocators.BASIC_PACKAGING_BUTTON).isEnabled()) {
                break;
            }
            page.waitForTimeout(1000);
        }
        click(ModifyAcademicPlanLocators.BASIC_PACKAGING_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    // ── Advanced Packaging ─────────────────────────────────────────────────

    public void clickAdvancedPackaging() {
        click(ModifyAcademicPlanLocators.ADVANCED_PACKAGING_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void selectFayOverride(String period, String overrideFAY) {
        click(String.format(ModifyAcademicPlanLocators.OVERRIDE_FAY_DROPDOWN, period));
        click(String.format(ModifyAcademicPlanLocators.OVERRIDE_FAY_OPTION, overrideFAY));
    }

    public void clickStep2Continue() {
        page.locator(ModifyAcademicPlanLocators.STEP2_CONTINUE_BUTTON).evaluate("el => el.click()");
        waitForAjaxRequestToBeFinished();
    }

    // ── ISIR Info Table ──────────────────────────────────────────────────

    private String isirInfoCell(String isirYear, int column) {
        String rowSelector = String.format(ModifyAcademicPlanLocators.ISIR_INFO_GRID_ROW_CELLS, isirYear);
        return getText(String.format("(%s)[%d]", rowSelector, column));
    }

    public String getIsirInfoGridDependency(String isirYear) {
        return isirInfoCell(isirYear, 2);
    }

    public String getIsirInfoGridSAI(String isirYear) {
        return isirInfoCell(isirYear, 4);
    }

    public String getIsirInfoGridCorrectStatus(String isirYear) {
        return isirInfoCell(isirYear, 6);
    }

    public String getIsirInfoGridRecordPackageOption(String isirYear) {
        return isirInfoCell(isirYear, 7);
    }
}
