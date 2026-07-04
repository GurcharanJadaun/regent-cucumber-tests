package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.UsersLocators;

public class UsersPage extends BasePage {

    public UsersPage(Page page) {
        super(page);
    }

    public void clickOnPageNumber(int number) {
        click(String.format(UsersLocators.PAGE_NUMBER, number));
    }
}
