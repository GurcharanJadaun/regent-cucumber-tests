package com.regent.locators;

/** Ported from regent.pages.student.CoursesPage. */
public interface CoursesLocators {
    String LAST_PAGE_BUTTON = "//div[@data-rem-id='active-grid']//a[@aria-label='Go to the last page' and not (contains(@class,'disable'))]";
    String LAST_PAYMENT_PERIOD = "//div[@data-rem-id='active-grid']//tr[contains(string(),'%s')][1]";
    String EDIT_BUTTON = "//button[@rem-trigger-event='editCourseDataGeneralClick']";
    String REGISTERED_UNITS_INPUT = "#CourseData_CourseDataOverride_registeredUnits";
    String SUBMIT_BUTTON = "//div[@class='buttonbar']//button[@type='submit']";
    String COMMENTS_FIELD = "#CourseData_CourseDataOverride_comments";
    String LAST_ATTENDED_DATE_FIELD = "#CourseData_CourseDataOverride_lastAttendedDate";
    String END_DATE_INPUT = "#CourseData_CourseDataOverride_endDate";
    String START_DATE_INPUT = "#CourseData_CourseDataOverride_startDate";
    String COURSE_DATA_WITH_NAME = "//tr//td[4][contains(string(),'%s')]";
    String EXTERNAL_MODULE_ID = "//div[@class='row' and contains(string(),'External Module ID')]/div[@class='details-field']";
    String START_DATE_FILTER = "//th[@data-field='startDate']";
    String NEXT_PAGE_BUTTON = "//div[@class='k-content k-state-active']//a[@title='Go to the next page' and @class='k-link k-pager-nav']";

    String COURSE_GRID_ROW_CELLS = "//div[@data-rem-widgetname='CourseDataGrid']/div[contains(@class,'content')]/table//tr[contains(string(),'%s')]/td";
    String COURSE_GRID_ROW_MANUAL = "//div[@data-rem-widgetname='CourseDataGrid']/div[contains(@class,'content')]/table//tr[contains(string(),'%s')]/td/span[@title='This Course Data was manually overridden']";
    String ACTIVE_COURSE_GRID_ROW = "//div[@data-rem-id='active-grid' and @data-rem-widgetname='CourseDataGrid']/div[contains(@class,'content')]/table//tr[contains(string(),'%s')]";
    String COURSE_GRID_ROW_CHECKBOX = "//div[@data-rem-widgetname='CourseDataGrid']/div[contains(@class,'content')]/table//tr[contains(string(),'%s')]/td[1]/input[@class='chkbx']";
    String WITHDRAWAL_DATE_INPUT = "//*[@name='CourseData.CourseDataOverride.withdrawalDate']";
    String REFRESH_BUTTON = "//button[@rem-trigger-event='refreshCorse']";

    // Kendo dropdowns edited repeatedly (Has Attended / Completed / Program Applicable) — filter
    // by :visible, not positional [last()], per the app-wide stale-duplicate-listbox issue.
    String HAS_ATTENDED_SELECT = "span[aria-owns='CourseData_CourseDataOverride_attendanceYN_listbox']";
    String HAS_ATTENDED_ITEM = "#CourseData_CourseDataOverride_attendanceYN_listbox li:visible:text-is('%s')";
    String COMPLETED_SELECT = "span[aria-owns='CourseData_CourseDataOverride_completedYN_listbox']";
    String COMPLETED_ITEM = "#CourseData_CourseDataOverride_completedYN_listbox li:visible:text-is('%s')";
    String PROGRAM_APPLICABLE_SELECT = "span[aria-owns='CourseData_CourseDataOverride_programApplicableYN_listbox']";
    String PROGRAM_APPLICABLE_ITEM = "#CourseData_CourseDataOverride_programApplicableYN_listbox li:visible:text-is('%s')";

    String COURSE_FILTER = "//div[@data-rem-id='active-grid' and @data-rem-widgetname='CourseDataGrid']//th[@data-field='%s']/a[@title='Filter']";
    String COURSE_FILTER_INPUT = "//form[@title='Show items with value that:' and @aria-hidden='false']//input[@title='Value']";
    String COURSE_FILTER_BUTTON = "//form[@title='Show items with value that:' and @aria-hidden='false']//button[text()='Filter']";
    String DELETE_BUTTON = "//button[normalize-space()='Delete']";
    String VIEW_ALL_RADIO = "#Courses_ViewAll";
    String ACTIVE_COURSES_TAB = "//span[normalize-space()='Active Courses']";
    String CLEAR_ALL_OVERRIDES_BUTTON = "//button[normalize-space()='Clear All Override Values']";
    String QUALITY_POINTS_INPUT = "#CourseData_CourseDataOverride_qualityPoints";
    String LETTER_GRADE_INPUT = "#CourseData_CourseDataOverride_letterGrade";

    // View Details
    String COURSE_DETAILS_TEXT_ITEM = "//div[@data-rem-widgetname='CourseDataViewGeneral']//div[label[(text()='%s')]]/following-sibling::div[@class='details-field']";
    String COURSE_DETAILS_ITEM_MANUAL_OVERRIDE = "//div[@data-rem-widgetname='CourseDataViewGeneral']//div[label[(text()='%s')]]/following-sibling::div[@class='details-field']/span[@title='this value was overridden manually']";
    String COURSE_DETAILS_CHECKBOX_ITEM = "//div[@data-rem-widgetname='CourseDataViewGeneral']//div[label[(text()='%s')]]/following-sibling::div[@class='details-field']/input[1]";
    String COMMENT_TEXT_AREA = "//textarea[contains(@id,'_comments')]";

    // Deleted Courses
    String DELETED_COURSES_TAB = "//span[normalize-space()='Deleted Courses']";
    String DELETED_COURSE_GRID_ROW = "//div[@data-rem-id='deleted-grid' and @data-rem-widgetname='CourseDataGrid']/div[contains(@class,'content')]/table//tr[contains(string(),'%s')]";
    String DELETED_COURSE_GRID_ROW_CHECKBOX = "//div[@data-rem-id='deleted-grid' and @data-rem-widgetname='CourseDataGrid']/div[contains(@class,'content')]/table//tr[contains(string(),'%s')]/td[1]/input[@class='chkbx']";
    String RESTORE_BUTTON = "//button[normalize-space()='Restore']";
}
