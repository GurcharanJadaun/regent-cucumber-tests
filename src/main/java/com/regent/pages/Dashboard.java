package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.DashboardLocators;

public class Dashboard extends BasePage {

    public Dashboard(Page page) {
        super(page);
    }

    public String getToastMessage() {
        return getText(DashboardLocators.TOAST_BOX);
    }
}
