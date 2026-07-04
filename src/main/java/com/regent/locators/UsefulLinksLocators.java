package com.regent.locators;

/** Locators ported from regent.pages.UsefulLinksPage. */
public interface UsefulLinksLocators {

    String USEFUL_LINKS_URLS        = "//div[@data-rem-id='usefulLinksGrid']//table//tr/td[3]";
    String USEFUL_LINKS_TEXTS       = "//div[@data-rem-id='usefulLinksGrid']//table//tr/td[2]";
    String USEFUL_LINKS_DESCRIPTION = "//div[@data-rem-id='usefulLinksGrid']//table//tr/td[4]";
    String EDIT_BUTTON              = "//div[@data-rem-widgetname='InstitutionPortalUsefulLinksView']//button";
    String SAVE_BUTTON              = "//form[contains(@action,'UpdateUsefulLinkstTab')]//button[@type='submit']";
    String CANCEL_BUTTON            = "//form[contains(@action,'UpdateUsefulLinkstTab')]//button[@type='button'][normalize-space()='Cancel']";
    String FEDERAL_STUDENT_AID_ROW  = "//table[@role='grid']//td[contains(text(),'Student Aid')]";
    String TABLE_TOP                = "//table[@role='grid']//tr[2]//td[3]";
    String TABLE_BOTTOM             = "//table[@role='grid']//tr[5]//td[3]";
    String TABLE_TOP_ROW            = "//div[@data-rem-id='usefulLinksGrid']/table/tbody/tr[1]";
    String TABLE_FOURTH_ROW         = "//div[@data-rem-id='usefulLinksGrid']/table/tbody/tr[4]";
    String TABLE_NEXT_TO_LAST_ROW   = "//div[@data-rem-id='usefulLinksGrid']/table/tbody/tr[last()-1]";
    String TABLE_BOTTOM_ROW         = "//div[@data-rem-id='usefulLinksGrid']/table/tbody/tr[last()]";
    String USEFUL_LINK_TABLE_ROWS   = "//div[@data-rem-id='usefulLinksGrid']/div[contains(@class,'content')]//tr";

    // %s is a 1-based row number; used with :nth-match() since these are plain XPath rows
    String USEFUL_LINKS_TEXT_BY_ROW = "(//div[@data-rem-id='usefulLinksGrid']//table//tr/td[2])[%s]";
    String USEFUL_LINKS_URL_BY_ROW  = "(//div[@data-rem-id='usefulLinksGrid']//table//tr/td[3])[%s]";
    String USEFUL_LINKS_DESC_BY_ROW = "(//div[@data-rem-id='usefulLinksGrid']//table//tr/td[4])[%s]";
}
