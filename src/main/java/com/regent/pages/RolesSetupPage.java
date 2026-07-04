package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.RolesSetupLocators;

public class RolesSetupPage extends BasePage {

    public RolesSetupPage(Page page) {
        super(page);
    }

    public void clickOnRoleName(String roleName) {
        click(String.format(RolesSetupLocators.ROLE_GRID_ROLE_NAME, roleName));
        waitForAjaxRequestToBeFinished();
    }

    public void clickUsersTab() {
        click(String.format(RolesSetupLocators.ROLE_TABS, "Users"));
        waitForAjaxRequestToBeFinished();
    }

    public void clickEditUsers() {
        click(RolesSetupLocators.EDIT_BUTTON_ROLE_USERS);
        waitForAjaxRequestToBeFinished();
    }

    public void addAvailableUser(String user) {
        click(String.format(RolesSetupLocators.ADD_AVAILABLE_USER, user));
        waitForAjaxRequestToBeFinished();
    }

    public void clickSaveUsers() {
        click(RolesSetupLocators.SAVE_BUTTON_ROLE_USERS);
        waitForAjaxRequestToBeFinished();
    }
}
