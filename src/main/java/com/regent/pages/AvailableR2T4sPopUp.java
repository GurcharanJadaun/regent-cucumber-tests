package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AvailableR2T4sLocators;

public class AvailableR2T4sPopUp extends BasePage {

    public AvailableR2T4sPopUp(Page page) {
        super(page);
    }

    public boolean isR2T4Available(String dateOfWithdrawal) {
        return isVisibleNow(String.format(AvailableR2T4sLocators.AVAILABLE_R2T4_ROW, dateOfWithdrawal));
    }

    public void selectAvailableR2T4(String dateOfWithdrawal) {
        click(String.format(AvailableR2T4sLocators.SELECT_BUTTON, dateOfWithdrawal));
    }

    public void clickCancel() {
        click(AvailableR2T4sLocators.CANCEL_BUTTON);
    }
}
