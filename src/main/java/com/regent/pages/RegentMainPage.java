package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.RegentMainLocators;

/**
 * Ported from regent.pages.RegentMainPage — the post-login landing/shell page. Distinct from
 * LoginPage: LoginPage owns navigation + the primary login flow used by most tests, this covers
 * the same credential form plus the rarer https-interstitial bypass some environments show first.
 */
public class RegentMainPage extends BasePage {

    public RegentMainPage(Page page) {
        super(page);
    }

    public void enterCredentialsAndSubmit(String username, String password) {
        fill(RegentMainLocators.USERNAME_FIELD, username);
        fill(RegentMainLocators.PASSWORD_FIELD, password);
        clickSubmitLoginButton();
    }

    /** Bypasses the "Your connection is not private" interstitial (if shown) before logging in. */
    public void submitCredentialsAfterInterstitial(String username, String password) {
        if (isVisible(RegentMainLocators.INTERSTITIAL_WARNING)) {
            click(RegentMainLocators.INTERSTITIAL_ADVANCED_BUTTON);
            click(RegentMainLocators.INTERSTITIAL_PROCEED_LINK);
        }
        enterCredentialsAndSubmit(username, password);
    }

    public boolean isLoginInputVisible() {
        return isVisible(RegentMainLocators.USERNAME_FIELD);
    }

    public void clickSubmitLoginButton() {
        click(RegentMainLocators.LOGIN_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
