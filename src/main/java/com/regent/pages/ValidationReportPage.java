package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.regent.locators.ValidationReportLocators;

import java.util.ArrayList;
import java.util.List;

public class ValidationReportPage extends BasePage {

    public ValidationReportPage(Page page) {
        super(page);
    }

    /** Returns text of every error record row matching the given student last name. */
    public List<String> getValidationMessage(String lastName) {
        waitForAjaxRequestToBeFinished();
        getText(ValidationReportLocators.REPORT_HEADING); // wait for jQuery, matches reference intent
        String selector = String.format(ValidationReportLocators.ERROR_RECORD_BY_TEXT, lastName);
        List<String> messages = new ArrayList<>();
        for (Locator element : page.locator(selector).all()) {
            messages.add(element.textContent().trim());
        }
        return messages;
    }

    public String getMainMessage(String externalStudentId) {
        waitForPageLoad();
        String selector = String.format(ValidationReportLocators.MAIN_MESSAGE_BY_TEXT, externalStudentId);
        return getText(selector);
    }

    public String getCourseDataMessage(String externalStudentId) {
        waitForPageLoad();
        String selector = String.format(ValidationReportLocators.ERROR_RECORD_BY_TEXT, externalStudentId);
        return getText(selector);
    }
}
