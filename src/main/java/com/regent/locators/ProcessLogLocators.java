package com.regent.locators;

public interface ProcessLogLocators {
    // Grid row: find by filename substring, status is td[2]
    String STATUS_BY_FILENAME = "(//tr[contains(string(),'%s')])[1]/td[2]";

    // Refresh button inside the visible process log panel
    String REFRESH_BUTTON = "//div[contains(@style,'display: block; ')]//button[@class='k-button r-entity-menu']";

    // Tabs at bottom of process log (Students, Details, etc.)
    String PROCESS_TAB = "//ul[@class='k-tabstrip-items k-reset']//li[string()='%s']";

    // Error details shown on the "Error Log" tab after clicking a failed process's row
    String ERROR_LOG_DETAILS = "//div[@data-rem-widgetname='ProcessViewErrorLog']//div[@class='details-field']";

    // View student link in the Students tab grid
    String VIEW_STUDENT_LINK = "//tr[contains(string(),'%s')]/td/a";

    // CE Rollup: the Process Id of the CE Rollup row immediately preceding the given import's row,
    // and the Status column of whichever row shows that Process Id (columns: Type,Status,Started,Finished,Id,Progress,User,FileName)
    String CE_ROLLUP_PROCESS_ID_BY_TAG = "((//tr[contains(string(),'%s')])[1]/preceding::tr[contains(string(),'Process CE Rollup')])[1]/td[5]";
    String STATUS_BY_PROCESS_ID        = "//tr/td[position()=5 and contains(string(),'%s')]/preceding-sibling::td[3]";

    // Process status values
    String COMPLETE   = "COMPLETE";
    String PROCESSING = "PROCESSING";
    String ERROR      = "ERROR";

    // Grid is system-wide (tens of thousands of rows, 20/page) — without sorting by newest
    // Process Id, a freshly submitted row can be pushed off page 1 by unrelated background
    // jobs (RNA Analysis, CE Rollup, etc.) and never be found by STATUS_BY_FILENAME.
    String PROCESS_ID_DESC_SORT   = "//th[@data-title='Process Id' and @data-dir='desc']";
    String PROCESS_ID_SORT_LINK   = "//th[@data-title='Process Id']/a[@class='k-link']";
    String STARTED_ON_SORT_LINK   = "//th[@data-title='Started On' and @data-dir='desc']/a[@class='k-link']";
}
