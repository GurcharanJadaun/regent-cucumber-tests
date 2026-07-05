package com.regent.locators;

public interface ProcessLogLocators {
    // Grid row: find by filename substring, status is td[2]
    String STATUS_BY_FILENAME = "(//tr[contains(string(),'%s')])[1]/td[2]";

    // Refresh button inside the visible process log panel
    String REFRESH_BUTTON = "//div[contains(@style,'display: block; ')]//button[@class='k-button r-entity-menu']";

    // Tabs at bottom of process log (Students, Details, etc.). This grid is opened/closed
    // repeatedly across a scenario (or across scenarios in one run), so — same as every other
    // repeatedly-instantiated Kendo widget in this app — a stale hidden copy of the tab strip
    // or the Error Log panel can outrank the current one; filter by :visible rather than
    // picking the first/only DOM match.
    String PROCESS_TAB = "ul.k-tabstrip-items.k-reset li span.k-link:visible:text-is('%s')";

    // Error details shown on the "Error Log" tab after clicking a failed process's row
    String ERROR_LOG_DETAILS = "div[data-rem-widgetname='ProcessViewErrorLog']:visible .details-field";

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

    // First-row status lookups for specific process types (exports/packaging/rollup), matching
    // the "grab the newest row of this type" pattern used for import status lookups above.
    String EXPORT_COD_STATUS            = "(//tr[contains(string(),'Export COD')])[1]/td[2]";
    String EXPORT_NSLDS_STATUS          = "(//tr[contains(string(),'Export NSLDS')])[1]/td[2]";
    String EXPORT_ISIR_CORRECTION_STATUS = "(//tr[contains(string(),'Export ISIR Correction')])[1]/td[2]";
    String EXPORT_EST_EXPECTED_STATUS   = "(//tr[contains(string(),'Export Student Transactions') and contains(string(),'EST_Expected')])[1]/td[2]";
    String EXPORT_EST_DRI_STATUS        = "(//tr[contains(string(),'Export Student Transactions') and contains(string(),'EST_DRI')])[1]/td[2]";
    String EXPORT_EST_ACTUAL_STATUS     = "(//tr[contains(string(),'Export Student Transactions') and contains(string(),'EST_Actual')])[1]/td[2]";
    String EXPORT_EST_STATUS            = "(//tr[contains(string(),'Export Student Transactions')])[1]/td[2]";
    String RNA_ANALYSIS_STATUS          = "(//tr[contains(string(),'RNA Analysis')])[1]/td[2]";
    String CE_ROLLUP_STATUS             = "(//tr[contains(string(),'Process CE Rollup')])[1]/td[2]";
    // Generic "first row matching this process-type substring" status lookup (e.g. Communications)
    String PROCESS_ITEM_STATUS_BY_TYPE  = "(//tr[contains(string(),'%s')])[1]/td[2]";
    String PROCESS_ITEM_FILENAME_BY_TYPE = "//div[@data-rem-widgetname='ProcessGrid']//tr[contains(string(),'%s')][1]/td[8]";

    // Details tab: label/value pairs (Total records, Processed Records, Unprocessed records)
    String DETAILS_FIELD_VALUE_BY_LABEL = "//div[@data-rem-id='detailsView']//div[@class='details-label-short' and normalize-space()='%s']/following-sibling::div";

    // Students tab: filter by External Id, grid rows/cells, view links, invalid-record markers
    String EXTERNAL_ID_FILTER          = "//div[@data-rem-widgetname='ProcessViewStudents']//th[@data-field='ExternalId1']/a[@title='Filter']";
    String GRID_FILTER_VALUE_INPUT     = "(//form[@title='Show items with value that:'])[last()]//input[@title='Value']";
    String GRID_FILTER_SUBMIT_BUTTON   = "(//form[@title='Show items with value that:'])[last()]//button[text()='Filter']";
    String STUDENT_ROW_BY_TEXT         = "//tr[contains(string(),'%s')]";
    String INVALID_STUDENT_RECORD      = "//tr[contains(string(),'%s')]//td[contains(text(),'This record is not valid for processing')]";
    String SBL_STUDENT_GRID_CELLS      = "//div[@data-rem-id='SBLgrid']//tr[contains(string(),'%s')]//td[@role='gridcell']";
    String STUDENT_COMMUNICATION_STATUS = "//div[@data-rem-widgetname='ProcessViewStudents']//div[contains(@class,'grid-content')]//tr[contains(string(),'%s')]/td[7]";

    // Selection Parameters tab
    String EST_PROCESS_TYPE = "//div[@class='six columns']/div[@class='row' and contains(string(),'EST Type:')]/div[@class='details-field']";

    // Grid presence check
    String PROCESS_GRID = "#Process_Grid";
}
