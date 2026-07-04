package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.ActivityLocators;

/** Ported from regent.pages.student.ActivityPage. */
public class ActivityPage extends BasePage {

    public ActivityPage(Page page) {
        super(page);
    }

    public boolean isActivityDescription(String activityDescription) {
        return isVisible(String.format(ActivityLocators.ACTIVITY_LOG_ITEM_BY_TEXT, activityDescription));
    }

    public String getActivityDescription(String activityDescription) {
        isVisible(ActivityLocators.ACTIVE_LOG_GRID_FIRST_ROW);
        return getText(String.format(ActivityLocators.ACTIVITY_DESCRIPTION_BY_TEXT, activityDescription));
    }

    public String getActivityDescription(int activityRow) {
        click(ActivityLocators.REFRESH_ACTIVITY_LOG_BUTTON);
        return getText(String.format(ActivityLocators.ACTIVITY_ROW_DESCRIPTION_BY_ROW, activityRow));
    }

    public String getActivityDateCreated(int activityRow) {
        String text = getText(String.format(ActivityLocators.ACTIVITY_ROW_DATE_CREATED_BY_ROW, activityRow));
        return text.substring(0, 10);
    }

    public void clickLinkInActivity(String activity) {
        click(String.format(ActivityLocators.LINK_IN_ACTIVITY_DESCRIPTION_BY_TEXT, activity));
        waitForAjaxRequestToBeFinished();
    }

    public void filterOnActivityType(String activityType) {
        click(String.format(ActivityLocators.ACTIVITY_LOG_FILTER_BY_COLUMN, ActivityLocators.ACTIVITY_TYPE_FILTER_LABEL));
        fill(ActivityLocators.FILTER_INPUT, activityType);
        click(ActivityLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void filterOnActivityTypeDescription(String activity) {
        click(String.format(ActivityLocators.ACTIVITY_LOG_FILTER_BY_COLUMN, ActivityLocators.ACTIVITY_TYPE_DESCRIPTION_FILTER_LABEL));
        fill(ActivityLocators.FILTER_INPUT, activity);
        click(ActivityLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** Grid cell for a row matched by text, cellIndex is 1-based (matches td[n] semantics). */
    private String getRowCellText(String rowKey, int cellIndex) {
        String rowSelector = String.format(ActivityLocators.ACTIVITY_LOG_ROW_CELLS_BY_TEXT, rowKey);
        return page.locator(rowSelector).nth(cellIndex - 1).textContent().trim();
    }

    public String getActivitySource(String rowKey) {
        return getRowCellText(rowKey, 2);
    }

    public String getActivityType(String rowKey) {
        return getRowCellText(rowKey, 3);
    }

    public String getActivityUserId(String rowKey) {
        return getRowCellText(rowKey, 4);
    }

    public String getActivityType(int activityRow) {
        return getText(String.format(ActivityLocators.ACTIVITY_GRID_CELL, activityRow, 3));
    }

    public String getActivityRemarksNotes(int activityRow) {
        return getText(String.format(ActivityLocators.ACTIVITY_ROW_REMARKS_NOTES_BY_ROW, activityRow));
    }
}
