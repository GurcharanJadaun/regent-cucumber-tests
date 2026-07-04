package com.regent.locators;

public interface AcademicPlanLocators {
    // Map / packaging buttons on the Academic Plan page
    String MAP_BUTTON            = "//button[@rem-trigger-event='modifyAcademicPlanClick']";
    String REFRESH_BUTTON        = "//button[@rem-trigger-event='refreshAcademicPlanClick']";
    String NO_PLAN_MESSAGE       = "//div[@class='academicPlanPartial']/h6[contains(string(),'No Current Academic Plan')]";

    // Award grid — current AY section. Each award is its own row, not a grid cell.
    String AWARD_ROW_BY_NAME     = "//div[contains(@class,'rem-academicYear-details') and contains(@class,'current')]//div[contains(@class,'rem-enrollment-row') and contains(@class,'rem-award-status-valid') and contains(string(),'%s')]";

    // Reveals a manually-added award not shown by default. One "Hidden" link exists per payment
    // period in the current AY (all identical when a full-year award is hidden across all of
    // them), so callers use .first() rather than a specific-term match.
    String HIDDEN_AWARD_LINK     = "//div[contains(@class,'rem-academicYear-details') and contains(@class,'current')]//a[@data-rem-id='showHiddenAwards']";

    // Packaging popup
    String BASIC_PACKAGING_BTN   = "id=btnBasicPackaging";
    String FINISH_BUTTON         = "//button[contains(string(),'Finish')]";
    String PACKAGING_BLOCKED_MSG = "//span[string()='Packaging is being blocked due to:']";

    // Award names as they appear in the grid
    String PELL_GRANT       = "Pell Grant";
    String DL_SUBSIDIZED    = "Direct Subsidized Loan";
    String DL_UNSUBSIDIZED  = "Direct Unsubsidized Loan";

    // Other plan-level action buttons
    String PROGRAM_MANAGEMENT_BTN = "//button[@rem-trigger-event='programChangeClick']";
    String STUDENT_BREAKS_BTN     = "//button[@rem-trigger-event='addStudentBreakClick']";

    // Header (Program Name / Program Start Date etc.) — value follows the label after a colon
    String HEADER_ITEM             = "//div[@class='academicPlanPartial']/div[@class='rem-header']/div/div[contains(string(),'%s')]";
    String HEADER_PROGRAM_NAME     = "//div[@class='academicPlanPartial']/div/div[@class='rem-header-col']/div[contains(string(),'Program Name')]";
    String HEADER_PROGRAM_START_DATE = "//div[@class='academicPlanPartial']/div/div[@class='rem-header-col']/div[contains(string(),'Program Start Date') and position()=1]";

    // Program Change box (rendered as a table as of app 7.0)
    String PROGRAM_CHANGE_HEADER          = "//div[@class='paymentPeriodSection' and contains(string(),'%s')]//div[@class='rem-program-change-header']";
    String PROGRAM_CHANGE_PROGRAM_NAME    = PROGRAM_CHANGE_HEADER + "//tr[1]/td[1]";
    String PROGRAM_CHANGE_START           = PROGRAM_CHANGE_HEADER + "//tr[1]/td[2]";
    String PROGRAM_CHANGE_ANTICIPATED_START = PROGRAM_CHANGE_HEADER + "//tr[1]/td[3]";
    String PROGRAM_CHANGE_COMPLETED       = PROGRAM_CHANGE_HEADER + "//tr[1]/td[4]";
    String PROGRAM_CHANGE_END             = PROGRAM_CHANGE_HEADER + "//tr[2]/td[4]";
    String PROGRAM_CHANGE_LOCATION        = PROGRAM_CHANGE_HEADER + "//tr[2]/td[1]";
    String PROGRAM_CHANGE_TRANSFERS       = "//div[@class='paymentPeriodSection' and contains(string(),'%s')]//div[@class='rem-program-change-header']//div[@class='rem-program-transfer-header']/div[contains(text(),'%s Transfers')]";
    String INTERNAL_TRANSFERS             = "//div[@class='rem-program-transfer-header']/div[contains(string(),'Internal Transfers')]";

    // Academic Year block — %s is "Academic Year <n>"; ported as (...)[last()] like the source
    // (source relies on the last matching block since AY numbers can repeat across programs)
    String ACADEMIC_YEAR_SECTION            = "(//div[@class='rem-academicYear' and contains(string(),'Academic Year %s')])[last()]";
    String ACADEMIC_YEAR_DATE_RANGE         = ACADEMIC_YEAR_SECTION + "/div[@class='rem-academicYear-title']/span[2]";
    String ACADEMIC_YEAR_UNITS_ROW          = ACADEMIC_YEAR_SECTION + "//tr[2]/td";
    String ACADEMIC_YEAR_WEEKS_UNITS_HEADINGS = ACADEMIC_YEAR_SECTION + "//tr[1]/td";
    String ACADEMIC_YEAR_ANTICIPATED_UNITS  = ACADEMIC_YEAR_SECTION + "//tr[2]/td[2]";
    String ACADEMIC_YEAR_REGISTERED_UNITS   = ACADEMIC_YEAR_SECTION + "//tr[2]/td[3]";
    String ACADEMIC_YEAR_ATTENDED_UNITS     = ACADEMIC_YEAR_SECTION + "//tr[2]/td[4]";
    String ACADEMIC_YEAR_CENSUS_UNITS       = ACADEMIC_YEAR_SECTION + "//tr[2]/td[5]";
    String ACADEMIC_YEAR_WEEKS_ROW          = ACADEMIC_YEAR_SECTION + "//tr[3]/td";
    String ACADEMIC_YEAR_ANTICIPATED_WEEKS  = ACADEMIC_YEAR_SECTION + "//tr[3]/td[2]";
    String ACADEMIC_YEAR_REGISTERED_WEEKS   = ACADEMIC_YEAR_SECTION + "//tr[3]/td[3]";
    String ACADEMIC_YEAR_ATTENDED_WEEKS     = ACADEMIC_YEAR_SECTION + "//tr[3]/td[4]";
    String ACADEMIC_YEAR_CENSUS_WEEKS       = ACADEMIC_YEAR_SECTION + "//tr[3]/td[5]";
    String ACADEMIC_YEAR_TOTAL_ESTIMATED    = ACADEMIC_YEAR_SECTION + "/div[@class='rem-academicYear-label'][1]/span[1]";
    String ACADEMIC_YEAR_TOTAL_OFFERED      = ACADEMIC_YEAR_SECTION + "/div[@class='rem-academicYear-label'][1]/span[2]";
    String ACADEMIC_YEAR_TOTAL_ACCEPTED     = ACADEMIC_YEAR_SECTION + "/div[@class='rem-academicYear-label'][1]/span[3]";
    String ACADEMIC_YEAR_TOTAL_COA          = ACADEMIC_YEAR_SECTION + "/div[@class='rem-academicYear-label'][1]/span[4]";

    // Payment Period grid columns — %s is the payment period name/date text
    String PAYMENT_PERIOD_SECTION      = "(//div[@class='paymentPeriodSection' and contains(string(),'%s')]/div[contains(@class,'rem-enrollment-row')]//div[@class='rem-grid-col'])";
    String PAYMENT_PERIOD_NAME         = PAYMENT_PERIOD_SECTION + "[1]";
    String PAYMENT_PERIOD_START_DATE   = PAYMENT_PERIOD_SECTION + "[2]";
    String PAYMENT_PERIOD_END_DATE     = PAYMENT_PERIOD_SECTION + "[3]";
    String PAYMENT_PERIOD_GRADE_LEVEL  = PAYMENT_PERIOD_SECTION + "[4]";
    String PAYMENT_PERIOD_ENROLLMENT_LEVEL = PAYMENT_PERIOD_SECTION + "[7]";

    // Payment period icons (auto-withdrawn / modular / historic / 180-day)
    String AUTOWITHDRAWN_ICON = "//div[@class='paymentPeriodSection']//div[@class='rem-grid-col' and contains(string(),'%s')]/a[contains(@title,'auto-withdrawn')]/span[contains(@class,'rem-customicon-aw')]";
    String MODULE_ICON        = "//div[@class='paymentPeriodSection']//div[@class='rem-grid-col' and contains(string(),'%s')]/a[contains(@title,'modular courses')]/span[contains(@class,'rem-customicon-module')]";
    String HISTORIC_ICON      = "//div[@class='paymentPeriodSection']//div[@class='rem-grid-col' and contains(string(),'%s')]/a[contains(@title,'Historic')]/span[contains(@class,'rem-customicon-historical')]";
    String I180_ICON          = "//div[@class='paymentPeriodSection']//div[@class='rem-grid-col' and contains(string(),'%s')]/a[contains(@title,'180-Day')]/span[contains(@class,'rem-customicon-180')]";
    String TERM_WITH_MODULAR_COURSES = "//div[contains(@class,'rem-enrollment-row') and contains(string(),'%s')]//a[@title='This indicates a term that has modular courses.']";

    // Payment period collapse/expand + no-awards
    String COLLAPSED_PAYMENT_PERIOD_SECTIONS = "//div[@class='paymentPeriodSection']//div[@class='rem-grid-col' and contains(string(),'Payment Period')]/span[contains(@class,'k-plus')]";
    String NO_AWARDS_IN_PAYMENT_PERIOD       = "//div[@class='paymentPeriodSection' and contains(string(),'%s')]//div[@class='rem-enrollment-awards-no-award' and contains(string(),'No Awards')]";
    String NO_AWARDS_MESSAGE_FOR_PP          = "//div[contains(@class,'rem-enrollment-row') and contains(string(),'%s')]//div[@class='rem-enrollment-awards-no-award']";
    String NO_AWARDS_MESSAGE                 = "//div[@class='rem-academicYear-details current']//div[@class='rem-enrollment-awards-no-award']";
    String ACADEMIC_YEAR_BLOCK               = "//div[@class='rem-academicYear-details ' and contains(@style,'display: none')]/preceding-sibling::div";
    String ACADEMIC_YEAR_EXPAND_COLLAPSE     = "//div[@class='rem-academicYear-title' and contains(string(),'Academic Year %s')]/span[contains(@class,'k-%s')]";

    // Award rows within a term/payment period (%s term, %s awardName) — column order matches the
    // reference's AWARD_DISBURSEMENTS_XPATH family
    String AWARD_ROW_COLS_BASE  = "//div[@class='rem-enrollment-row' and contains(string(),'%s')]//div[contains(@class,'rem-enrollment-row rem-award-status-')][contains(string(),\"%s\")]/div[@class='rem-grid-col']";
    String AWARD_AWARD_YEAR     = AWARD_ROW_COLS_BASE + "[2]";
    String AWARD_AMOUNT         = AWARD_ROW_COLS_BASE + "[3]";
    String AWARD_STATUS         = AWARD_ROW_COLS_BASE + "[4]";
    String AWARD_PAID_AMOUNT    = AWARD_ROW_COLS_BASE + "[5]";
    String AWARD_ISIR_TRANS_NUM = AWARD_ROW_COLS_BASE + "[6]";
    String MANUAL_AWARD_ICON    = AWARD_ROW_COLS_BASE + "[1]/span[contains(@class,'rem-customicon-manual')]";
    String AWARD_AMOUNT_LINK    = AWARD_ROW_COLS_BASE + "/a[@data-rem-id='hlDisbDetails']";
    String LAST_PAYMENT_PERIOD_AWARD = "//div[@class='paymentPeriodSection']/div[@class='rem-enrollment-row' and contains(string(),'%s')]//div[contains(@class,'rem-award-status-valid')][position()=last()]/div[1]";
    String LAST_AWARD_IN_PAYMENT_PERIOD = "//div[@class='paymentPeriodSection']/div[@class='rem-enrollment-row' and contains(string(),'%s')]//div[contains(@class,'rem-award-status-')][position()=last()]/div[1]";
    String HIDDEN_AWARD_LINK_FOR_TERM = "//div[@class='rem-enrollment-row' and contains(string(),'%s')]//div[@class='rem-enrollment-row rem-enrollment-row-subtitle rem-enrollment-awards-header']/span/a[@data-rem-id='showHiddenAwards' and contains(string(),'Hidden')]";

    // Academic Year container (used for "term is in this AY" / free-text lookups)
    String ACADEMIC_YEAR_CONTAINER      = "//div[@data-rem-id='academicYearContainer' and contains(string(),'%s')]";
    String ACADEMIC_YEAR_CONTAINERS     = "//div[@data-rem-id='academicYearContainer']";
    String TERM_IN_ACADEMIC_YEAR        = "//div[@data-rem-id='academicYearContainer' and contains(string(),'%s')]//div[contains(@class,'rem-enrollment-row')]//div[@class='rem-grid-col' and contains(string(),'%s')]";
    String ACADEMIC_YEAR_ACCEPTED_AWARD = "//div[@class='rem-academicYear-label']/span[@class='rem-academicYear-field'][last()]";

    // Student breaks
    String BREAK_COLUMNS  = "//div[@data-rem-id='academicYearContainer' and contains(string(),'%s')]//div[@class='rem-break-header']/div[@class='rem-break-col']";
    String STUDENT_BREAK  = "//div[@class='rem-break-header']/div[@class='rem-break-col' and contains(string(),'%s')]";

    // Payment period count
    String PAYMENT_PERIOD_SECTION_ALL = "//div[@class='paymentPeriodSection']";
}
