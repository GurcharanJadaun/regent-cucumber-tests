package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddResourceLocators;

/**
 * Ported from regent.pages.AddResourcePopUp. Source's EnterpriseBuilder-derived payment period
 * names (getCourseDataPrevious/Actual) aren't available on this side, so callers pass the payment
 * period name directly (same approach as AddTermPage). The two Kendo option dropdowns (Source,
 * Type) get reopened for every resource added in a test, so options are targeted by :visible text
 * match instead of the source's positional clickOnElementNumber(2)/[last()] workarounds.
 */
public class AddResourcePopUp extends BasePage {

    public AddResourcePopUp(Page page) {
        super(page);
    }

    public void addFirstResource(String previousPaymentPeriodName) {
        addResourceByAmount("Gift From Friend", "1000", "Private", "Grant", previousPaymentPeriodName, "Resource Add");
    }

    public void addSecondResource(String actualPaymentPeriodName) {
        addResourceByAmount("Gift From Other Friend", "500", "Private", "Grant", actualPaymentPeriodName, "Resource Add");
    }

    public boolean isResourceForActualPeriodPresent(String actualPaymentPeriodName) {
        return isResourcePresentForPeriod(actualPaymentPeriodName);
    }

    public boolean isResourceForPreviousPeriodPresent(String previousPaymentPeriodName) {
        return isResourcePresentForPeriod(previousPaymentPeriodName);
    }

    private boolean isResourcePresentForPeriod(String paymentPeriodName) {
        String selector = String.format(AddResourceLocators.RESOURCES_GRID_ROW, paymentPeriodName);
        return isVisible(selector);
    }

    public void addResourceByAmount(String resName, String amount, String resource, String resourceType, String paymentPeriod, String notes) {
        click(AddResourceLocators.ADD_ADJUSTMENT_BUTTON);
        fill(AddResourceLocators.RESOURCE_NAME, resName);
        fill(AddResourceLocators.RESOURCE_AMOUNT, amount);
        click(AddResourceLocators.SOURCE_TRIGGER);
        click(String.format(AddResourceLocators.SOURCE_OPTION, resource));
        click(AddResourceLocators.TYPE_TRIGGER);
        click(String.format(AddResourceLocators.TYPE_OPTION, resourceType));
        click(AddResourceLocators.PAYMENT_PERIOD_TRIGGER);
        click(String.format(AddResourceLocators.RESOURCE_PAYMENT_PERIOD_OPTION, paymentPeriod));
        fill(AddResourceLocators.RESOURCE_NOTES, notes);
        click(AddResourceLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
        page.locator(AddResourceLocators.LOADMASK).waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN)
                .setTimeout(timeout));
    }

    public void repackageAndClose() {
        click(AddResourceLocators.REPACKAGE_BUTTON);
        waitForAjaxRequestToBeFinished();
        click(AddResourceLocators.CLOSE_BUTTON);
    }

    public void clickCloseButton() {
        click(AddResourceLocators.CLOSE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
