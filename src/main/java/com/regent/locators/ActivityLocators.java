package com.regent.locators;

public interface ActivityLocators {
    String ACTIVITY_LOG_ITEM_BY_TEXT = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr/td[position()=5 and contains(string(),'%s')]";
    String ACTIVITY_DESCRIPTION_BY_TEXT = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr[contains(string(),'%s')]/td[5]";
    String ACTIVITY_ROW_DESCRIPTION_BY_ROW = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr[%s]/td[5]";
    String ACTIVITY_ROW_DATE_CREATED_BY_ROW = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr[%s]/td[1]";

    // Use for getting a cell by row number (1st %s) and column number (2nd %s)
    String ACTIVITY_GRID_CELL = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr[%s]/td[%s]";

    String ACTIVITY_ROW_REMARKS_NOTES_BY_ROW = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr[%s]/td[6]";

    String REFRESH_ACTIVITY_LOG_BUTTON = "//button[@rem-trigger-event='refreshActivityLogClick']";
    String ACTIVE_LOG_GRID_FIRST_ROW   = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr[1]";

    String LINK_IN_ACTIVITY_DESCRIPTION_BY_TEXT = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr[contains(string(),'%s')][1]/td[5]/a";

    // Filter icon per column; :not stale-hidden concern not applicable here (filter popup is singleton)
    String ACTIVITY_LOG_FILTER_BY_COLUMN = "//div[@data-rem-id='StudentActivitiesGrid']//th[contains(@data-title,'%s')]/a[contains(@class,'k-grid-filter')]";

    String FILTER_INPUT  = "(//form[@title='Show items with value that:'])[last()]//input[@title='Value']";
    String FILTER_BUTTON = "(//form[@title='Show items with value that:'])[last()]//button[text()='Filter']";

    String ACTIVITY_LOG_ROW_CELLS_BY_TEXT = "//div[@data-rem-id='StudentActivitiesGrid']/div[contains(@class,'k-grid-content')]/table[@role='grid']//tr[contains(string(),'%s')][1]/td";

    String ACTIVITY_TYPE_FILTER_LABEL              = "Activity Type";
    String ACTIVITY_TYPE_DESCRIPTION_FILTER_LABEL  = "Activity/Description";
}
