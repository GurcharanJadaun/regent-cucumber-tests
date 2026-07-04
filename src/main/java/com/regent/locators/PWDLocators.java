package com.regent.locators;

/** Ported from regent.pages.PWDPopUp (Return of Title IV / PWD worksheet popup). */
public interface PWDLocators {
    String STUDENT_NAME = "//div[@data-rem-widgetname='R2T4ViewPWD']//div[@class='details-label-short' and ./label[@for='R2T4StudentModel_StudentName']]/following-sibling::div[@class='details-field']";
    String DATE_FORM_COMPLETED = "(//div[@data-rem-id='r2t4_DateFormCompleted_field'])[2]";
    String DATE_SCHOOL_DETERMINED_WITHDREW = "//div[@class='twelve columns' and contains(string(),\"Date of school’s determination that student withdrew:\")]/div[@class='details-field']";

    String EDIT_BUTTON     = "//button[@rem-trigger-event='editPWDTopClick']";
    String CANCEL_BUTTON   = "//button[@rem-trigger-event='cancelPWDTopClick']";
    String FINALIZE_BUTTON = "//button[@rem-trigger-event='R2T4PWDFinalizeTopClick']";
    String REVERSE_BUTTON  = "//button[@rem-trigger-event='SetR2T4PWDReversedTopClick']";
    String SAVE_BUTTON     = "(//button[contains(string(),'Save')])[1]";

    String BOX1_VALUE = "//div[contains(string(),'Box 1')]/span";
    String BOX2_VALUE = "//div[contains(string(),'Box 2')]/span";

    // PWD grid, keyed by row text (fund/disbursement identifier)
    String NET_AMOUNT_ACCEPTED_INPUT_STEP1 = "//div[@data-rem-id='pwdTable']//tr[contains(string(),'%s')]//span[contains(@class,'r2t4PwdAmountValue')]//input[position()=1]";
    String NET_AMOUNT_ACCEPTED_INPUT_STEP2 = "//span[contains(@class,'r2t4PwdAmountValue')]//input[position()=2]";
    String NET_AMOUNT_ACCEPTED_CELL        = "//table/tbody/tr[contains(string(),'%s')]/td[@class='rem-r2t4pwdgrid-cell' and position()=9]";
    String PWD_TABLE_CELL_AT_POSITION      = "//table/tbody/tr[contains(string(),'%s')]/td[@class='rem-r2t4pwdgrid-cell'][%s]";
    String PWD_TABLE_MANUAL_ICON_AT_POSITION = "//table/tbody/tr[contains(string(),'%s')]/td[@class='rem-r2t4pwdgrid-cell' and position()=%s]/span[contains(@class,'rem-customicon-manual')]";
    String DO_NOT_OFFER_CHECKBOX           = "//table/tbody/tr[contains(string(),'%s')]/td[@class='rem-r2t4pwdgrid-cell' and position()=11]/input[@class='check-box']";

    // Kendo dropdown: Return Amount Reason (stable id-based pattern, not positional [last()])
    String RETURN_AMOUNT_REASON_DROPDOWN = "span[aria-owns='R2T4Model_PwdModel_PWDList_0__PwdDisbursementList_0__R2T4PWDReasonCode_listbox']";
    String RETURN_AMOUNT_REASON_OPTION   = "//ul[@id='R2T4Model_PwdModel_PWDList_0__PwdDisbursementList_0__R2T4PWDReasonCode_listbox']/li[contains(text(),'%s')]";

    String ACTUAL_CANCEL_DATE_INPUT = "//input[contains(@name,'ActualCancelDate') and position()=1]";
    String DATE_PWD_RESPONSE_INPUT  = "//input[contains(@name,'DatePWDResponse') and position()=1]";
    String NET_ACCEPTED_AMOUNT_INPUT = "//input[contains(@name,'AmountAccepted') and position()=1]";
    String DO_NOT_OFFER_CHECKBOX_INPUT = "//input[contains(@name,'DoNotOfferPWD') and position()=1]";
    String DO_NOT_OFFER_CHANGE_WARNING = "//div[@data-rem-id='DoNotOfferOrReasonChangedWarning' and contains(string(),'%s')]";
    String DO_NOT_OFFER_CHANGE_MESSAGE = "\"Do Not Offer\" changed. Please review and make any necessary updates to the Actual Cancel Date, Date PWD Response, and Net Amount Accepted.";

    String ADD_COMMENT_BUTTON  = "(//button[@rem-trigger-event='AddPWDCommentButtonClick'])[last()]";
    String COMMENT_TEXTAREA    = "//textarea[@id='Comment']";
    String SAVE_COMMENT_BUTTON = "//button[@rem-trigger-event='SavePWDCommentButtonClick']";
    String COMMENTS_HEADING    = "//h5[contains(string(),'Comments')]";

    String LOADING_INDICATOR = "//div[@class='loadmask-msg']";
    String MORE_THAN_180_DAYS_INDICATOR = "//a[@class='rem-info-hint' and @title='Any changes made more than 180 days after the Date of Determination must be processed outside of the PWD worksheet.']";
    String SECTION_IV_HEADING = "//h6[normalize-space()='IV. Authorizations and Notifications']";
}
