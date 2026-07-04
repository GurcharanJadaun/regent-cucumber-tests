package com.regent.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.regent.locators.CommunicationDetailsLocators;

import java.util.ArrayList;
import java.util.List;

/**
 * Ported from regent.pages.student.CommunicationDetailsPopUp. Content lives inside an iframe;
 * Playwright's FrameLocator (auto-waiting, no explicit switchTo/defaultContent bookkeeping)
 * replaces the source's manual iframe-switch calls.
 */
public class CommunicationDetailsPopUp extends BasePage {

    public CommunicationDetailsPopUp(Page page) {
        super(page);
    }

    private FrameLocator detailsFrame() {
        return page.frameLocator(CommunicationDetailsLocators.DETAILS_IFRAME);
    }

    public boolean isLogoDisplayed(String externalId, String logoName) {
        page.waitForTimeout(3000);
        String selector = String.format(CommunicationDetailsLocators.COMMUNICATION_LOGO, externalId, logoName);
        return detailsFrame().locator(selector).isVisible();
    }

    public String getCommunicationText() {
        return detailsFrame().locator(CommunicationDetailsLocators.COMMUNICATION_BODY).textContent().trim();
    }

    public void closeCommunicationDetails() {
        click(CommunicationDetailsLocators.CLOSE_BUTTON);
    }

    public String getParentRegistrationLink() {
        return detailsFrame().locator(CommunicationDetailsLocators.PARENT_REGISTRATION_LINK).getAttribute("href");
    }

    public String getFirstLastName() {
        return detailsFrame().locator(CommunicationDetailsLocators.STUDENT_FIRST_LAST_NAME).textContent().trim();
    }

    public String getStreetAddress() {
        return detailsFrame().locator(CommunicationDetailsLocators.STUDENT_STREET_ADDRESS).textContent().trim();
    }

    public String getCityStateZip() {
        return detailsFrame().locator(CommunicationDetailsLocators.STUDENT_CITY_STATE_ZIP).textContent().trim();
    }

    public String getCommunicationDate() {
        return detailsFrame().locator(CommunicationDetailsLocators.COMMUNICATION_DATE).textContent().trim();
    }

    public String getLocation() {
        String text = detailsFrame().locator(CommunicationDetailsLocators.STUDENT_SITE).textContent();
        return text.split(":")[1].trim();
    }

    public boolean verifyLocation(String location) {
        return detailsFrame().locator(CommunicationDetailsLocators.STUDENT_SITE).textContent().contains(location);
    }

    public String getStudentId() {
        String text = detailsFrame().locator(CommunicationDetailsLocators.STUDENT_EXTERNAL_ID).textContent();
        return text.split(":")[1].trim();
    }

    public boolean verifyStudentId(String externalId) {
        return detailsFrame().locator(CommunicationDetailsLocators.STUDENT_EXTERNAL_ID).textContent().contains(externalId);
    }

    public List<String> getAwardsTable1RowData(int rowNumber) {
        String selector = String.format(CommunicationDetailsLocators.AWARD_TABLE_ROW_DATA, 1, rowNumber);
        List<String> rowData = new ArrayList<>();
        int count = detailsFrame().locator(selector).count();
        for (int i = 0; i < count; i++) {
            rowData.add(detailsFrame().locator(selector).nth(i).textContent().trim());
        }
        return rowData;
    }

    public String getLinkHref(String linkText, int index) {
        String selector = String.format(CommunicationDetailsLocators.DETAILS_LINK, linkText);
        return detailsFrame().locator(selector).nth(index - 1).getAttribute("href");
    }
}
