package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.HeaderLocators;

import java.util.List;
import java.util.stream.Collectors;

/** Ported from regent.pages.Header — top-of-page search/logout bar shown after login. */
public class Header extends BasePage {

    public Header(Page page) {
        super(page);
    }

    public void typeSearchValue(String value) {
        fill(HeaderLocators.SEARCH_INPUT, value);
    }

    /** Query method for a step def to assert every dropdown result contains the typed value. */
    public List<String> getSearchResultTexts() {
        return page.locator(HeaderLocators.SEARCH_RESULTS).allTextContents().stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void searchForUser(String value) {
        fill(HeaderLocators.SEARCH_INPUT, value);
        click(HeaderLocators.SUBMIT_SEARCH);
        waitForPageLoad();
    }

    public void submitSearch() {
        click(HeaderLocators.SUBMIT_SEARCH);
        waitForPageLoad();
    }

    public void clickLogoutLink() {
        page.locator(HeaderLocators.LOGOUT_LINK).hover();
        click(HeaderLocators.LOGOUT_LINK);
        waitForPageLoad();
    }

    public String getVersionNumber() {
        page.locator(HeaderLocators.INFO_ICON).hover();
        String text = getText(HeaderLocators.INFO_POPUP);
        return text.split("Version: ")[1].split(" Release")[0];
    }

    public String getFullVersion() {
        page.locator(HeaderLocators.INFO_ICON).hover();
        String text = getText(HeaderLocators.INFO_POPUP);
        return text.split("Version: ")[1];
    }

    public String getRemVersion() {
        page.locator(HeaderLocators.INFO_ICON).hover();
        String text = getText(HeaderLocators.INFO_POPUP);
        return text.split("Version: ")[1].split(" ")[0].split("[.]")[3].trim();
    }

    public String getUserId() {
        return getText(HeaderLocators.CHANGE_PASSWORD_LINK);
    }
}
