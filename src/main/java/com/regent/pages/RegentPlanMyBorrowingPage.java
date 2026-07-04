package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.RegentPlanMyBorrowingLocators;

public class RegentPlanMyBorrowingPage extends BasePage {

    public RegentPlanMyBorrowingPage(Page page) {
        super(page);
    }

    public void editMyBorrowing() {
        click(RegentPlanMyBorrowingLocators.EDIT_MY_BORROWING_BUTTON);
    }

    public void saveMyBorrowing() {
        click(RegentPlanMyBorrowingLocators.SAVE_MY_BORROWING_BUTTON);
    }

    public void editChangeMyBorrowingAmount(boolean check) {
        if (check) {
            page.locator(RegentPlanMyBorrowingLocators.CHANGE_MY_BORROWING_AMOUNT_CHECKBOX).check();
        } else {
            page.locator(RegentPlanMyBorrowingLocators.CHANGE_MY_BORROWING_AMOUNT_CHECKBOX).uncheck();
        }
    }
}
