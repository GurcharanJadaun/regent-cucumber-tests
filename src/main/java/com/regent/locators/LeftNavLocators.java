package com.regent.locators;

public interface LeftNavLocators {
    // Top-level panels: only match the collapsed state (exact class), same as the
    // reference smoke-suite-automation LeftSideMenu — used for click-to-expand only.
    String PROCESSES_PANEL  = "//li[@class='k-item k-state-default']/span[contains(text(),'Processes')]";
    String STUDENTS_PANEL   = "//li[@class='k-item k-state-default k-first']/span[contains(text(),'Students')]";

    // Stable accordion container present regardless of Kendo expand/collapse state
    String NAV_ACCORDION    = "#westAccordion";

    // Generic menu item link (use with String.format for the link text)
    String MENU_ITEM_LINK   = "//a[string()='%s']";

    // Student profile sub-tabs. This app keeps multiple student profiles open as background
    // tabs simultaneously ("Close All Tabs" in the header), so each previously-viewed student
    // leaves its own hidden left-nav tab-strip in the DOM — duplicating these data-rem-id nodes.
    // A stale, invisible duplicate can even carry a leftover k-state-active class from when its
    // (now-backgrounded) tab was last active, so it isn't safe to just pick .first(); filter by
    // :visible to always target the current foreground student's own tab-strip.
    String ACADEMIC_PLAN_TAB   = "li[data-rem-id='AcademicPlan'] span.k-link:visible";
    String DETAILS_TAB         = "li[data-rem-id='Details'] span.k-link:visible";
    String DOCUMENTS_TAB       = "li[data-rem-id='Documents'] span.k-link:visible";
    String AWARDS_TAB          = "li[data-rem-id='Awards'] span.k-link:visible";
    String COMMUNICATIONS_TAB  = "li[data-rem-id='Communications'] span.k-link:visible";
    String ISIR_TAB            = "li[data-rem-id='Isir'] span.k-link:visible";
    String LOANS_TAB           = "li[data-rem-id='Loans'] span.k-link:visible";
    String COURSES_TAB         = "li[data-rem-id='Courses'] span.k-link:visible";
    String TASKS_TAB           = "li[data-rem-id='Tasks'] span.k-link:visible";
    String ACTIVITY_TAB        = "li[data-rem-id='Activity'] span.k-link:visible";
    String HISTORY_TAB         = "li[data-rem-id='History'] span.k-link:visible";
    String SUMMARY_TAB         = "li[data-rem-id='Summary'] span.k-link:visible";
    String COD_TAB             = "li[data-rem-id='COD'] span.k-link:visible";
    String ADVISOR_ASSIST_TAB  = "li[data-rem-id='AdvisorAssist'] span.k-link:visible";

    // Student-profile-wide action buttons (StudentBasePage in the reference project)
    String ADD_TO_WATCH_LIST_BUTTON      = "//button[@rem-trigger-event='addToStudentWatchListClick']";
    String REMOVE_FROM_WATCH_LIST_BUTTON = "//button[normalize-space()='Remove From Student Watch List']";
    String REFRESH_BUTTON                = "//div[@class='k-content k-state-active'][contains(@style,'block')]//div[@class='k-content k-state-active']//button[contains(string(),'Refresh')][@class='k-button r-entity-menu']";
    String STUDENT_TAB_CLOSE             = "//span[@class='k-link' and contains(@data-content-url,'tab=Summary')]/span[contains(@class,'close')]";

    // Admin/Home/Reports top-level panels
    String ADMIN_PANEL       = "//*[@data-rem-id='adminMenuPanel']";
    String HOME_PANEL        = "//li[@data-rem-id='homeMenuPanel']";
    String REPORTS_PANEL     = "//li[@data-rem-id='reportsMenuPanel']";
    String STANDARD_REPORTS_PANEL = "//div[@id='reportsTree']//span[text()='Standard Reports']";
    String REPORT_LINK       = "//div[@id='reportsTree']//a[text()='%s']";
    String HOME_TASK_GROUP   = "//div[@id='homeTree']//div[contains(string(),'%s')]/following-sibling::ul/li/div[contains(string(),'%s')]/a";
    String WATCH_LIST_ITEM   = "//*[contains(@id,'studentWatchList')]//a[text()='%s']";

    // Administration tree
    String ENTERPRISE_LINK   = "//div[@id='administrationTree']/ul/li[contains(@class,'k-item')]/div/a[contains(@href,'Enterprise') and contains(text(),'%s')]";
    String INSTITUTION_LINK  = "//div[@id='administrationTree']//ul[@class='k-group k-treeview-lines']/li[@role='treeitem']/ul/li/div/a";
    String CAMPUS_ITEM       = "//div[@id='administrationTree']//li[@role='treeitem']//ul//ul//li";
    String EXPAND_CAMPUS     = "//div[@id='administrationTree']//li[@role='treeitem']//ul//ul//li[contains(string(),'%s')]//span[contains(@class,'k-i-expand')]";
    String SITES_LINK           = "//div[@id='administrationTree']//li//a[text()='Sites']";
    String PROGRAM_SETUP_LINK   = "//div[@id='administrationTree']//li//a[text()='Program Setup']";
    String TERM_SETUP_LINK      = "//div[@id='administrationTree']//li//a[text()='Term Setup']";
    String COST_SETUP_LINK      = "//div[@id='administrationTree']//li//a[text()='Cost Setup']";
    String FUND_SETUP_LINK      = "//div[@id='administrationTree']//li//a[text()='Fund Setup']";
    String TASK_MANAGEMENT_LINK = "//div[@id='administrationTree']//li//a[text()='Task Management']";
    String TASK_SETUP_LINK      = "//div[@id='administrationTree']//li//a[text()='Task Setup']";
    String DOCUMENT_SETUP_LINK  = "//div[@id='administrationTree']//li//a[text()='Document Setup']";
}
