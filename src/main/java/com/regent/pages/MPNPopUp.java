package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.MPNLocators;

/** Ported from regent.pages.MPNPopUp. Returns void; callers construct LoansPage(page) themselves
 * to navigate back, same object graph the source builds via `new LoansPage()`. */
public class MPNPopUp extends BasePage {

    public MPNPopUp(Page page) {
        super(page);
    }

    public void addMpn(String mpnId, String expirationDate, boolean linkIndicator, boolean eMpnIndicator, String status) {
        click(MPNLocators.ADD_MPN_BUTTON);
        fill(MPNLocators.MPN_ID_INPUT, mpnId);
        fill(MPNLocators.EXPIRATION_DATE_INPUT, expirationDate);

        click(MPNLocators.MPN_LINK_INDICATOR_SELECT);
        click(String.format(MPNLocators.MPN_LINK_INDICATOR_OPTION, linkIndicator ? "True" : "False"));

        click(MPNLocators.EMPN_INDICATOR_SELECT);
        click(String.format(MPNLocators.EMPN_INDICATOR_OPTION, eMpnIndicator ? "True" : "False"));

        click(MPNLocators.MPN_STATUS_SELECT);
        click(String.format(MPNLocators.MPN_STATUS_OPTION, status));

        click(MPNLocators.SAVE_BUTTON);
        click(String.format(MPNLocators.SELECT_MPN_ROW, mpnId));
    }

    public void selectMpn(String mpnId) {
        click(String.format(MPNLocators.SELECT_MPN_ROW, mpnId));
    }
}
