package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.AddCostLocators;

/**
 * Ported from regent.pages.AddCostPopUp. Skipped addCostForCurrentPaymentPeriod/
 * addCostForPreviousPaymentPeriod/addCostForPaymentPeriodRepackage — those relied on the
 * source's EnterpriseBuilder test-data model (getCourseDataActual()/getCourseDataPrevious())
 * to name the payment period, which has no equivalent here; use addPaymentPeriod(String) with
 * the period name from the step definition instead.
 */
public class AddCostPopUp extends BasePage {

    public AddCostPopUp(Page page) {
        super(page);
    }

    public void clickAddAdjustment() {
        click(AddCostLocators.ADD_ADJUSTMENT_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void addCostAdjustmentName(String adjustmentName) {
        fill(AddCostLocators.COST_ADJUST_NAME, adjustmentName);
    }

    public void addAdjustmentAmount(String amount) {
        fill(AddCostLocators.ADJUST_AMOUNT, amount);
    }

    public void setDirectCost(boolean check) {
        if (check) {
            page.locator(AddCostLocators.DIRECT_COST_CHECKBOX).check();
        } else {
            page.locator(AddCostLocators.DIRECT_COST_CHECKBOX).uncheck();
        }
    }

    public void selectApplyAmount(String applyOption) {
        click(AddCostLocators.APPLY_AMOUNT_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(AddCostLocators.APPLY_AMOUNT_OPTION, applyOption));
    }

    public void selectCostCategory(String categoryOption) {
        click(AddCostLocators.FEDERAL_COST_CATEGORY_DROPDOWN);
        waitForAjaxRequestToBeFinished();
        click(String.format(AddCostLocators.FEDERAL_COST_CATEGORY_OPTION, categoryOption));
    }

    public void addPaymentPeriod(String paymentPeriod) {
        click(String.format(AddCostLocators.SELECT_LAST_PAYMENT_PERIOD, paymentPeriod));
        waitForAjaxRequestToBeFinished();
    }

    public void addNote(String note) {
        fill(AddCostLocators.ADJUST_NOTES, note);
    }

    public void saveCostAdjust() {
        click(AddCostLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadingMaskToClear();
    }

    public void clickRepackage() {
        click(AddCostLocators.REPACKAGE_BUTTON);
        waitForAjaxRequestToBeFinished();
        waitForLoadingMaskToClear();
    }

    private void waitForLoadingMaskToClear() {
        if (isVisibleNow(AddCostLocators.LOADING_MASK)) {
            page.locator(AddCostLocators.LOADING_MASK).waitFor(
                    new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        }
    }

    public void closeAddCostAdjust() {
        click(AddCostLocators.CLOSE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public boolean isCostPresentForPeriod(String paymentPeriodName) {
        return isVisible(String.format(AddCostLocators.COST_GRID, paymentPeriodName));
    }
}
