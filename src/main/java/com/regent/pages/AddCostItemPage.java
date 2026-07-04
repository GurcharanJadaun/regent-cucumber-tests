package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddCostItemLocators;

/** Ported from regent.pages.AddCostItemPage. The source's CostItemData model isn't available
 * here, so addItemAndSave takes its fields directly instead of a data-object parameter. */
public class AddCostItemPage extends BasePage {

    public AddCostItemPage(Page page) {
        super(page);
    }

    public void addItemAndSave(String costItemName, String costFrequency, String rounding, double costAmount,
                                String rateStart, String roundingLevel, boolean directCost, boolean billedBySchool,
                                String externalCostId, boolean allStates, boolean dependent, boolean independentWith,
                                boolean independentWithout, boolean blank, boolean onCampus, boolean withParent,
                                boolean offCampus, boolean inState, boolean outOfState, boolean inDistrict,
                                boolean unknown, boolean outOfDistrict) {
        fill(AddCostItemLocators.COST_NAME, costItemName);
        click(AddCostItemLocators.COST_FREQUENCY_DROPDOWN);
        click(String.format(AddCostItemLocators.COST_FREQUENCY_OPTION, costFrequency));
        click(AddCostItemLocators.ROUNDING_DROPDOWN);
        click(String.format(AddCostItemLocators.ROUNDING_OPTION, rounding));
        fill(AddCostItemLocators.COST, String.valueOf(costAmount));
        fill(AddCostItemLocators.RATE_START, rateStart);
        click(AddCostItemLocators.ROUNDING_LEVEL_DROPDOWN);
        click(String.format(AddCostItemLocators.ROUNDING_LEVEL_OPTION, roundingLevel));
        setChecked(AddCostItemLocators.DIRECT_COST_CHECKBOX, directCost);
        setChecked(AddCostItemLocators.BILLED_BY_SCHOOL_CHECKBOX, billedBySchool);
        fill(AddCostItemLocators.EXTERNAL_COST_ID, externalCostId);
        setChecked(AddCostItemLocators.ALL_STATES_CHECKBOX, allStates);
        setChecked(AddCostItemLocators.DEPENDENT_CHECKBOX, dependent);
        setChecked(AddCostItemLocators.INDEPENDENT_WITH_CHECKBOX, independentWith);
        setChecked(AddCostItemLocators.INDEPENDENT_WITHOUT_CHECKBOX, independentWithout);
        setChecked(AddCostItemLocators.BLANK_CHECKBOX, blank);
        setChecked(AddCostItemLocators.ON_CAMPUS_CHECKBOX, onCampus);
        setChecked(AddCostItemLocators.WITH_PARENT_CHECKBOX, withParent);
        setChecked(AddCostItemLocators.OFF_CAMPUS_CHECKBOX, offCampus);
        setChecked(AddCostItemLocators.IN_STATE_CHECKBOX, inState);
        setChecked(AddCostItemLocators.OUT_OF_STATE_CHECKBOX, outOfState);
        setChecked(AddCostItemLocators.IN_DISTRICT_CHECKBOX, inDistrict);
        setChecked(AddCostItemLocators.UNKNOWN_CHECKBOX, unknown);
        setChecked(AddCostItemLocators.OUT_OF_DISTRICT_CHECKBOX, outOfDistrict);
        click(AddCostItemLocators.SAVE_BUTTON);
    }

    /** Delete triggers a native confirm() dialog server-side; auto-accept it before clicking. */
    public void clickDeleteButton() {
        page.onceDialog(dialog -> dialog.accept());
        click(AddCostItemLocators.DELETE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    private void setChecked(String selector, boolean checked) {
        if (checked) {
            page.locator(selector).check();
        } else {
            page.locator(selector).uncheck();
        }
    }
}
