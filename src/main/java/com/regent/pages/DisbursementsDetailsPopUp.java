package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.DisbursementsDetailsLocators;

/** Ported from regent.pages.student.DisbursementsDetailsPopUp. */
public class DisbursementsDetailsPopUp extends BasePage {

    public DisbursementsDetailsPopUp(Page page) {
        super(page);
    }

    private String cell(String disbursementNum, int column) {
        String rowSelector = String.format(DisbursementsDetailsLocators.DISBURSEMENT_ROW_CELLS, disbursementNum);
        return getText(String.format("(%s)[%d]", rowSelector, column));
    }

    public String getPaymentPeriod(String disbursementNum) {
        return cell(disbursementNum, 1);
    }

    public String getDisbursementNumber(String disbursementNum) {
        return cell(disbursementNum, 2);
    }

    public String getGrossAmount(String disbursementNum) {
        return cell(disbursementNum, 3);
    }

    public String getNetAmount(String disbursementNum) {
        return cell(disbursementNum, 4);
    }

    public String getPaidAmount(String disbursementNum) {
        return cell(disbursementNum, 5);
    }

    public String getScheduledDate(String disbursementNum) {
        return cell(disbursementNum, 6);
    }

    public String getAdjReleaseDate(String disbursementNum) {
        return cell(disbursementNum, 7);
    }

    public String getReleaseDate(String disbursementNum) {
        return cell(disbursementNum, 8);
    }

    public String getCpsTransNumber(String disbursementNum) {
        return cell(disbursementNum, 9);
    }

    public String getDisbursementStatus(String disbursementNum) {
        return cell(disbursementNum, 10);
    }

    public String getR2t4Refund(String disbursementNum) {
        return cell(disbursementNum, 11);
    }

    public String getR2t4Pwd(String disbursementNum) {
        return cell(disbursementNum, 12);
    }

    public String getAcpaEl(String disbursementNum) {
        return cell(disbursementNum, 15);
    }

    public String getAcpaUnits(String disbursementNum) {
        return cell(disbursementNum, 16);
    }

    public void closeDisbursementPopup() {
        click(DisbursementsDetailsLocators.CLOSE_POPUP_BUTTON);
        waitForAjaxRequestToBeFinished();
    }
}
