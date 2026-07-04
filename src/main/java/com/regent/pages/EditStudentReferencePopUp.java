package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.EditStudentReferenceLocators;

/** Ported from regent.pages.student.EditStudentReferencePopUp. */
public class EditStudentReferencePopUp extends BasePage {

    public EditStudentReferencePopUp(Page page) {
        super(page);
    }

    public void clickFERPARadioButtonAndSave() {
        click(EditStudentReferenceLocators.FERPA_RADIO_BUTTON);
        click(EditStudentReferenceLocators.SUBMIT_BUTTON);
    }
}
