package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.config.ConfigReader;
import com.regent.locators.LoginLocators;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    public void navigate() {
        page.navigate(ConfigReader.getBaseUrl());
        waitForPageLoad();
    }

    public void enterUsername(String username) {
        fill(LoginLocators.USERNAME_FIELD, username);
    }

    public void enterPassword(String password) {
        fill(LoginLocators.PASSWORD_FIELD, password);
    }

    public void clickLoginButton() {
        click(LoginLocators.LOGIN_BUTTON);
        waitForPageLoad();
    }

    public void loginAs(String username, String password) {
        navigate();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void loginAsAdmin() {
        loginAs(ConfigReader.getAdminUsername(), ConfigReader.getAdminPassword());
    }

    public boolean isLoginErrorDisplayed() {
        return isVisible(LoginLocators.LOGIN_ERROR_MSG);
    }
}
