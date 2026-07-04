package com.regent.locators;

public interface AvailableR2T4sLocators {
    String AVAILABLE_R2T4_ROW = "(//div[@data-rem-id='studentR2T4Grid']//table)[2]//tr[contains(string(),'%s')]";
    String SELECT_BUTTON      = "(//div[@data-rem-id='studentR2T4Grid']//table)[2]//tr[contains(string(),'%s')]/td/a";
    String CANCEL_BUTTON      = "//button[@rem-trigger-event='cancelStudentR2T4ModalFormClick']";
}
