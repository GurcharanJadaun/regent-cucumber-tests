package com.regent.locators;

/** Ported from regent.pages.student.StudentBreaksPopUp. */
public interface StudentBreaksLocators {
    String ADD_STUDENT_BREAK_BUTTON = "//button[@rem-trigger-event='studentBreakAddClick']";
    String REPACKAGE_BUTTON         = "//button[@rem-trigger-event='studentBreakRepackageClick']";
    String CLOSE_BUTTON             = "//button[@rem-trigger-event='cancelStudentBreakWizardClick']";

    // Native <select> — use Playwright's selectOption(), not click-based interaction
    String TYPE_SELECT = "#StudentBreak_studentBreakTypeCode";

    String BREAK_REASON     = "#StudentBreak_reason";
    String BREAK_ID         = "#StudentBreak_ExternalId";
    String APPROVED_CHECKBOX = "#StudentBreak_approvedYN";
    String BREAK_START_DATE = "#StudentBreak_startDate";
    String BREAK_END_DATE   = "#StudentBreak_endDate";
    String BREAK_NOTE       = "#StudentBreak_note";
    String SAVE_BUTTON      = "//div[@data-rem-widgetname='StudentBreakAdd']//button[@type='submit']";
}
