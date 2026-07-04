package com.regent.locators;

/** Ported from regent.pages.MPNPopUp. */
public interface MPNLocators {
    String ADD_MPN_BUTTON        = "//button[contains(string(),'Add MPN')]";
    String MPN_ID_INPUT          = "//input[@name='MPN.MasterPromissoryNoteIdentification']";
    String EXPIRATION_DATE_INPUT = "//input[@name='MPN.MpnExpirationDate']";

    // Native <select> elements — kept for reference, not used directly (Kendo widgets below front them)
    String MPN_LINK_INDICATOR_INPUT = "//select[@name='MPN.MpnLinkIndicator']";
    String EMPN_INDICATOR_INPUT     = "//select[@name='MPN.EmpnIndicator']";
    String STATUS_INPUT             = "//select[@name='MPN.MpnStatusCode']";

    String SAVE_BUTTON  = "//button[contains(string(),'Save')]";
    String CLOSE_BUTTON = "//button[contains(string(),'Close')]";

    // Kendo dropdowns
    String MPN_LINK_INDICATOR_SELECT = "span[aria-owns='MPN_MpnLinkIndicator_listbox']";
    String MPN_LINK_INDICATOR_OPTION = "#MPN_MpnLinkIndicator_listbox li:visible:text-is('%s')";
    String EMPN_INDICATOR_SELECT     = "span[aria-owns='MPN_EmpnIndicator_listbox']";
    String EMPN_INDICATOR_OPTION     = "#MPN_EmpnIndicator_listbox li:visible:text-is('%s')";
    String MPN_STATUS_SELECT         = "span[aria-owns='MPN_MpnStatusCode_listbox']";
    String MPN_STATUS_OPTION         = "#MPN_MpnStatusCode_listbox li:visible:text-is('%s')";

    String SELECT_MPN_ROW = "//div[@data-rem-id='mpnGrid']/div/table[@role='grid']/tbody[@role='rowgroup']/tr[contains(string(),'%s')]/td/a[contains(string(),'Select')]";
}
