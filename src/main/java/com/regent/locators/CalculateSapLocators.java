package com.regent.locators;

/** Ported from regent.pages.CalculateSapPopUp — Calculate SAP popup + Appeal Review tab. */
public interface CalculateSapLocators {
    String REVIEW_PERIOD_DROPDOWN = "span[aria-owns='reviewPeriodId_listbox']";
    String PERIOD_ITEM = "//li[contains(text(),'%s')]";

    String ADD_SAP_BUTTON = "//button[@rem-trigger-event='studentSAPRecordAddClick']";
    String SAP_DATE = "#studentSAPRecord_SAPDate";
    String SAP_REVIEW_PERIOD_GPA = "//input[@id='studentSAPRecord_SapReviewPeriodGPA']/parent::span/input[1]";
    String CUMULATIVE_GPA = "//input[@id='studentSAPRecord_CumulativeGPA']/parent::span/input[1]";

    String SELECT_SAP_STATUS_DROPDOWN = "span[aria-owns='studentSAPRecord_ResultSAPStatusCode_listbox']";
    // Reused across multiple SAP record edits, so Kendo can leave stale hidden copies — filter :visible.
    String SAP_STATUS_OPTION = "#studentSAPRecord_ResultSAPStatusCode_listbox li.k-item:visible:text-is('%s')";

    String SAVE_BUTTON = "//button[@name='Save']";
    String SAP_RECORD_GRID_ITEM = "//div[@data-rem-widgetname='SAPRecordGrid']//tr[contains(string(),'%s')]";
    String CLOSE_POPUP_BUTTON = "//a[@aria-label='Close']";
    String RUN_SAP_BUTTON = "//button[contains(string(),'Run SAP')][1]";
    String SAP_RECORD_GRID_TITLE = "//div[@data-rem-widgetname='SAPRecordGrid']/div[@class='k-grid-header']//th[@data-title='%s']";
    String SAP_RECORD_GRID_ROW_CELLS = "//div[@data-rem-widgetname='SAPRecordGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[@role='gridcell']";
    String SAP_RECORD_GRID_RESULT_PP_BASED_POLICY = "//div[@data-rem-widgetname='SAPRecordGrid']/div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s')]/td[@role='gridcell'][6]//span[contains(@title,'PP-based')]";
    String LOADMASK = "//div[@class='loadmask']";
    String INPUT_NOTES = "//textarea[@id='studentSAPRecord_Notes']";

    // Appeal Review tab
    String APPEAL_REVIEW_TAB = "//span[normalize-space()='Appeal Review']";
    String EDIT_APPEAL_REVIEW_BUTTON = "//button[@rem-trigger-event='editSAPWizardAppealClick']";
    String DOCUMENT_DATE_INPUT = "#studentSAPAppeal_DocumentDate";

    String DECISION_MAKER_DROPDOWN = "span[aria-owns='decisionMakerUserIdString_listbox']";
    String DECISION_MAKER_OPTION = "#decisionMakerUserIdString_listbox li:visible:text-is('%s')";

    String APPEAL_STATUS_DROPDOWN = "span[aria-owns='studentSAPAppeal_SapAppealStatusCode_listbox']";
    String APPEAL_STATUS_OPTION = "#studentSAPAppeal_SapAppealStatusCode_listbox li:visible:text-is('%s')";

    String APPEAL_SAP_STATUS_DROPDOWN = "span[aria-owns='studentSAPAppeal_AppealSAPStatusCode_listbox']";
    String APPEAL_SAP_STATUS_OPTION = "#studentSAPAppeal_AppealSAPStatusCode_listbox li:visible:text-is('%s')";

    String SAVE_SAP_APPEAL_BUTTON = "//button[normalize-space()='Save']";
}
