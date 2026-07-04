package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.CODLocators;

/** Ported from regent.pages.student.CODPage. */
public class CODPage extends BasePage {

    public CODPage(Page page) {
        super(page);
    }

    private String cellText(String rowCellsLocatorTemplate, String rowKey, int cellNumber) {
        String selector = String.format(rowCellsLocatorTemplate, rowKey);
        return page.locator(selector).nth(cellNumber - 1).textContent().trim();
    }

    public void selectAwardResponse(String awardId) {
        click(String.format(CODLocators.AWARD_RESPONSE_GRID_ROW, awardId));
        waitForAjaxRequestToBeFinished();
    }

    public void selectAwardLevelResponse(String rowKey) {
        click(String.format(CODLocators.AWARD_LEVEL_MASTER_GRID_ROW, rowKey));
        waitForAjaxRequestToBeFinished();
    }

    public String getAwardResponseGridFay(String awardId) {
        return cellText(CODLocators.AWARD_RESPONSE_GRID_ROW_CELLS, awardId, 3);
    }

    public String getAwardResponseGridFund(String awardId) {
        return cellText(CODLocators.AWARD_RESPONSE_GRID_ROW_CELLS, awardId, 6);
    }

    public String getAwardResponseGridAwardAmount(String awardId) {
        return cellText(CODLocators.AWARD_RESPONSE_GRID_ROW_CELLS, awardId, 8);
    }

    public String getAwardResponseGridPaidAmount(String awardId) {
        return cellText(CODLocators.AWARD_RESPONSE_GRID_ROW_CELLS, awardId, 9);
    }

    public String getAwardResponseGridCodStatus(String awardId) {
        return cellText(CODLocators.AWARD_RESPONSE_GRID_ROW_CELLS, awardId, 10);
    }

    // Award Details (selected)

    public String getAwardDetailsResponseGridCodProcess(String responseType) {
        return cellText(CODLocators.AWARD_RESPONSE_DETAILS_GRID_ROW_CELLS, responseType, 1);
    }

    public String getAwardDetailsResponseGridCodFund(String responseType) {
        return cellText(CODLocators.AWARD_RESPONSE_DETAILS_GRID_ROW_CELLS, responseType, 5);
    }

    public String getAwardDetailsResponseGridCodStatus(String responseType) {
        return cellText(CODLocators.AWARD_RESPONSE_DETAILS_GRID_ROW_CELLS, responseType, 8);
    }

    public String getAwardDetailsResponseGridResponseType(String responseType) {
        return cellText(CODLocators.AWARD_RESPONSE_DETAILS_GRID_ROW_CELLS, responseType, 9);
    }

    public String getAwardDetailsResponseGridAwardAmount(String responseType) {
        return cellText(CODLocators.AWARD_RESPONSE_DETAILS_GRID_ROW_CELLS, responseType, 10);
    }

    // Student Level

    public String getStudentResponseGridProcess(String responseType) {
        return cellText(CODLocators.STUDENT_RESPONSE_GRID_ROW_CELLS, responseType, 1);
    }

    public String getStudentResponseGridStatus(String responseType) {
        return cellText(CODLocators.STUDENT_RESPONSE_GRID_ROW_CELLS, responseType, 8);
    }

    public String getStudentResponseGridResponseType(String responseType) {
        return cellText(CODLocators.STUDENT_RESPONSE_GRID_ROW_CELLS, responseType, 9);
    }
}
