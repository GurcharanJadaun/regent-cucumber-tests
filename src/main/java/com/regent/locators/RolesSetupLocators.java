package com.regent.locators;

public interface RolesSetupLocators {
    String ROLE_GRID_ROLE_NAME = "//div[@data-rem-widgetname='RoleGrid']/div[contains(@class,'k-grid-content')]//tr/td[text()='%s']";
    String ROLE_TABS           = "//span[@class='k-link' and ./span[text()='%s']]";

    String EDIT_BUTTON_ROLE_USERS = "//button[@rem-trigger-event='editRoleUsersClick']";
    String SAVE_BUTTON_ROLE_USERS = "//button[normalize-space()='Save']";

    String ADD_AVAILABLE_USER = "//div[@data-rem-id='availableUsersGrid']/div[contains(@class,'k-grid-content')]//tr/td[text()='%s']/following-sibling::td/a";
}
