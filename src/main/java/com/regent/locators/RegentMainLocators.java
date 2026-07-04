package com.regent.locators;

public interface RegentMainLocators {
    String USERNAME_FIELD = "#UserName";
    String PASSWORD_FIELD = "#Password";
    String LOGIN_BUTTON    = "#LogOnBtn";

    // https privacy interstitial page (seen on some environments before the login form)
    String INTERSTITIAL_WARNING = "//h1[text()='Your connection is not private']";
    String INTERSTITIAL_ADVANCED_BUTTON = "//button[@id='details-button']";
    String INTERSTITIAL_PROCEED_LINK    = "//a[@id='proceed-link']";
}
