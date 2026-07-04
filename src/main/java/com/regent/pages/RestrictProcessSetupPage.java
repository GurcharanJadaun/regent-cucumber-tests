package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.RestrictProcessSetupLocators;

/** Ported from regent.pages.RestrictProcessSetupPage. */
public class RestrictProcessSetupPage extends BasePage {

    public RestrictProcessSetupPage(Page page) {
        super(page);
    }

    public void editRestrictProcess(boolean restrictPositiveDisbursements,
                                     boolean restrictPositiveAndNegativeDisbursements,
                                     boolean restrictPackaging,
                                     String fundName) {
        click(RestrictProcessSetupLocators.EDIT_BUTTON);
        waitForAjaxRequestToBeFinished();

        if (restrictPositiveDisbursements) {
            click(String.format(RestrictProcessSetupLocators.AVAILABLE_PROCESS_ADD_LINK, "Restrict Positive Disbursements"));
        }
        if (restrictPositiveAndNegativeDisbursements) {
            click(String.format(RestrictProcessSetupLocators.AVAILABLE_PROCESS_ADD_LINK, "Restrict Positive and Negative Disbursements"));
        }
        if (restrictPackaging) {
            click(String.format(RestrictProcessSetupLocators.AVAILABLE_PROCESS_ADD_LINK, "Restrict Packaging"));
        }
        if (!fundName.isEmpty()) {
            page.locator(RestrictProcessSetupLocators.SELECT_ALL_FUNDS_CHECKBOX).uncheck();
            waitForAjaxRequestToBeFinished();
            click(RestrictProcessSetupLocators.AVAILABLE_FUND_NAME_FILTER);
            fill(RestrictProcessSetupLocators.FILTER_INPUT, fundName);
            click(RestrictProcessSetupLocators.FILTER_SUBMIT);
            waitForAjaxRequestToBeFinished();
            click(String.format(RestrictProcessSetupLocators.AVAILABLE_FUND_ADD_LINK, fundName));
            waitForAjaxRequestToBeFinished();
        }
        click(RestrictProcessSetupLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
