package com.regent.locators;

/** Locators ported from regent.pages.student.CommunicationDetailsPopUp in the reference project. */
public interface CommunicationDetailsLocators {
    String CLOSE_BUTTON   = "(//div[contains(@class,'k-window') and contains(string(),'Communication Details')]//a[@aria-label='Close'])[last()]";
    String DETAILS_IFRAME = "//body/div[contains(@class,'k-window')][last()]//iframe[@title='Communication Details']";

    // Content inside the Communication Details iframe
    String COMMUNICATION_LOGO      = "//body[contains(string(),'%s')]//img[contains(@src,'%s')]";
    String STUDENT_FIRST_LAST_NAME = "//table[1]//tr[1]/td[1]";
    String STUDENT_STREET_ADDRESS  = "//table[1]//tr[2]/td[1]";
    String STUDENT_CITY_STATE_ZIP  = "//table[1]//tr[3]/td[1]";
    String COMMUNICATION_DATE      = "//table[1]//tr[1]/td[2]";
    String STUDENT_SITE            = "//table[1]//tr[2]/td[2]";
    String STUDENT_EXTERNAL_ID     = "//table[1]//tr[3]/td[2]";
    String COMMUNICATION_BODY      = "//body";
    String AWARD_TABLE_ROW_DATA    = "(//table//table[contains(string(),'Awards')])[%s]//tr[%s]/td";
    String PARENT_REGISTRATION_LINK = "//a[contains(string(),'parents')]";
    String DETAILS_LINK            = "//a[contains(string(),'%s')]";
}
