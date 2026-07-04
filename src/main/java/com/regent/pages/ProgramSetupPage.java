package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.regent.locators.ProgramSetupLocators;

/**
 * Ported from regent.pages.ProgramSetupPage (which extended the reference's Filter helper —
 * filterBy* methods here are the subset ProgramSetupPage actually used, inlined directly since
 * this project doesn't have a shared Filter mixin).
 *
 * Skipped: addTermsIfAbsent(Enterprise)/addTermsForProgram(Enterprise) — both keyed off the
 * source's Enterprise test-data model (getCourseDataActual()/Before()/Previous()) to name terms;
 * ported the reusable single-term worker as addTermIfAbsent(String) for callers to loop over.
 * clickAddProgramButton() dropped its "return new AddProgramPage()" (page object not in scope
 * for this batch) and just performs the click.
 */
public class ProgramSetupPage extends BasePage {

    public ProgramSetupPage(Page page) {
        super(page);
    }

    // ── Programs grid ──────────────────────────────────────────────────────

    public String getExternalId() {
        return getText(String.format(ProgramSetupLocators.PROGRAM_ROW, "9"));
    }

    public String getProgramName() {
        return getText(String.format(ProgramSetupLocators.PROGRAM_ROW, "1"));
    }

    public String getCalendarType() {
        return getText(ProgramSetupLocators.CALENDAR_TYPE);
    }

    public void clickTermsEnrollmentLevelsButton() {
        click(ProgramSetupLocators.TERMS_ENROLLMENT_LEVELS_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickR2T4Tab() {
        click(ProgramSetupLocators.R2T4_TAB);
        waitForAjaxRequestToBeFinished();
    }

    public void clickOnFirstProgram() {
        click(ProgramSetupLocators.FIRST_PROGRAM);
        waitForAjaxRequestToBeFinished();
    }

    // ── Grid filters ───────────────────────────────────────────────────────

    public void filterByUnitType(String filter) {
        click(String.format(ProgramSetupLocators.TYPE_FILTER, "Unit"));
        fill(ProgramSetupLocators.FILTER_INPUT, filter);
        click(ProgramSetupLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void filterByExternalId(String filter) {
        click(String.format(ProgramSetupLocators.TYPE_FILTER, "External ID"));
        click(ProgramSetupLocators.CLEAR_BUTTON);
        click(String.format(ProgramSetupLocators.TYPE_FILTER, "External ID"));
        fill(ProgramSetupLocators.FILTER_INPUT, filter);
        click(ProgramSetupLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void filterByPeriodType(String filter) {
        click(String.format(ProgramSetupLocators.TYPE_FILTER, "Period Type"));
        fill(ProgramSetupLocators.FILTER_INPUT, filter);
        click(ProgramSetupLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void filterByCalendarType(String filter) {
        click(ProgramSetupLocators.CALENDAR_TYPE_FILTER);
        fill(ProgramSetupLocators.FILTER_INPUT, filter);
        click(ProgramSetupLocators.FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    // ── Terms / Enrollment Levels ────────────────────────────────────────

    public void clickEditTermsButton() {
        click(ProgramSetupLocators.EDIT_TERMS_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    /** Filters the terms grid to the given term and adds it if the Add button appears (i.e. it's absent). */
    public void addTermIfAbsent(String term) {
        click(ProgramSetupLocators.TERMS_FILTER);
        waitForAjaxRequestToBeFinished();
        fill(ProgramSetupLocators.TERM_INPUT, term);
        click(ProgramSetupLocators.SUBMIT_FILTER_BUTTON);
        waitForAjaxRequestToBeFinished();
        if (isVisibleNow(ProgramSetupLocators.ADD_TERM_BUTTON)) {
            click(ProgramSetupLocators.ADD_TERM_BUTTON);
        }
        waitForAjaxRequestToBeFinished();
    }

    public void clickSave() {
        click(ProgramSetupLocators.SAVE_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clickAddProgramButton() {
        click(ProgramSetupLocators.ADD_PROGRAM_BUTTON);
    }

    public void clickTermEditButton() {
        click(ProgramSetupLocators.EDIT_TERMS_BUTTON);
    }

    /** Filters the terms grid to a term and clicks its row link — one call per term to add. */
    public void addTermForProgram(String term) {
        click(ProgramSetupLocators.TERM_FILTER);
        fill(ProgramSetupLocators.FILTER_INPUT, term);
        click(ProgramSetupLocators.FILTER_BUTTON);
        click(String.format(ProgramSetupLocators.TERM_ROW, term));
    }

    // ── Program add/edit/delete ──────────────────────────────────────────

    /**
     * The source used acceptAllertAfterClick() around the delete click (a native confirm() alert).
     * Playwright dismisses dialogs via a page-level event handler registered before the click,
     * not via a return value, so this registers a one-shot auto-accept handler first.
     */
    public void deleteProgram(String programName) {
        click(ProgramSetupLocators.NAME_FILTER);
        fill(ProgramSetupLocators.FILTER_INPUT, programName);
        click(ProgramSetupLocators.FILTER_BUTTON);
        String rowSelector = String.format(ProgramSetupLocators.PROGRAM_ROW_BY_NAME, programName);
        if (isVisibleNow(rowSelector)) {
            click(rowSelector);
            clickDeleteButton();
        }
    }

    public void clickDeleteButton() {
        page.onceDialog(dialog -> dialog.accept());
        click(ProgramSetupLocators.DELETE_BUTTON);
    }

    public boolean isAutoIncludeHeaderOptionPresent() {
        return isVisible(ProgramSetupLocators.AUTO_INCLUDE_HEADER_FOR_AY_FUNDS);
    }

    public String getAutoIncludeHeaderOptionDescription() {
        page.locator(ProgramSetupLocators.AUTO_INCLUDE_HEADER_INFO).hover();
        waitForAjaxRequestToBeFinished();
        return getText(ProgramSetupLocators.AUTO_INCLUDE_HEADER_DESC_POPUP);
    }

    public void changeCalendarPlanTypeTo(String calendarPlan) {
        click(ProgramSetupLocators.CALENDAR_TYPE_SELECT_BUTTON);
        click(String.format(ProgramSetupLocators.CALENDAR_TYPE_SELECT_OPTION, calendarPlan));
    }

    public void clickEditProgramButton() {
        click(ProgramSetupLocators.EDIT_PROGRAM_BUTTON);
    }

    public String getAutoIncludeTrailerValue() {
        return getText(ProgramSetupLocators.AUTO_INCLUDE_HEADER_INPUT);
    }

    public String getName() {
        return getText(ProgramSetupLocators.PROGRAM_NAME);
    }

    // ── R2T4 tab ───────────────────────────────────────────────────────────

    public String getProjectingNonTermR2T4() {
        return getText(ProgramSetupLocators.METHOD_FOR_PROJECTING_NONTERM_R2T4PP);
    }

    public void clickEditR2T4() {
        click(ProgramSetupLocators.EDIT_R2T4_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void setProjectingNontermR2T4PP(String codeValueText) {
        click(ProgramSetupLocators.R2T4_METHOD_SELECTION);
        waitForAjaxRequestToBeFinished();
        click(String.format(ProgramSetupLocators.R2T4_METHOD_OPTION, codeValueText));
    }

    public void clickSaveR2T4() {
        click(ProgramSetupLocators.SAVE_R2T4_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void setDaysInR2T4When0Units(String days) {
        waitForAjaxRequestToBeFinished();
        if (!days.isEmpty()) {
            fill(ProgramSetupLocators.DAYS_IN_R2T4_WHEN_0_UNITS, days);
        }
    }

    public void clickCancelButton() {
        click(ProgramSetupLocators.CANCEL_BUTTON);
    }

    public void setPotentialR2T4IsRequiredOption(String option) {
        click(ProgramSetupLocators.POTENTIAL_R2T4_IS_REQUIRED_SELECT);
        click(String.format(ProgramSetupLocators.POTENTIAL_R2T4_IS_REQUIRED_OPTION, option));
        waitForAjaxRequestToBeFinished();
    }

    public boolean isOffsetDodNeedR2T4InputDisabled() {
        String classAttr = page.locator(ProgramSetupLocators.OFFSET_DOD_NEED_R2T4).getAttribute("class");
        return classAttr != null && classAttr.contains("disabled");
    }

    public boolean isOffsetDodAutoFinalizeR2T4InputDisabled() {
        String classAttr = page.locator(ProgramSetupLocators.OFFSET_DOD_AUTO_FINALIZE_R2T4).getAttribute("class");
        return classAttr != null && classAttr.contains("disabled");
    }

    public void setOffsetDodNeedR2T4(String offset) {
        fill(ProgramSetupLocators.OFFSET_DOD_NEED_R2T4, offset);
    }

    public void setOffsetDodAutoFinalizeR2T4(String offset) {
        fill(ProgramSetupLocators.OFFSET_DOD_AUTO_FINALIZE_R2T4, offset);
    }

    public boolean isValidationError(String errorMessage) {
        return page.locator(ProgramSetupLocators.VALIDATION_SUMMARY_ERRORS)
                .filter(new Locator.FilterOptions().setHasText(errorMessage))
                .count() > 0;
    }

    public String getOffsetDodAutoFinalizeR2T4InfoText() {
        page.locator(ProgramSetupLocators.OFFSET_DOD_AUTO_FINALIZE_R2T4_INFO_ICON).hover();
        return getText(ProgramSetupLocators.INFO_TIP_POPUP);
    }

    public boolean isInheritOffsetDODNeedR2T4Checkbox() {
        return isVisibleNow(ProgramSetupLocators.INHERIT_OFFSET_DOD_NEED_R2T4_CHECKBOX);
    }

    public boolean isInheritoffsetDODAutoFinalizeR2T4Checkbox() {
        return isVisibleNow(ProgramSetupLocators.INHERIT_OFFSET_DOD_AUTO_FINALIZE_R2T4_CHECKBOX);
    }

    public String getOffsetDODNeedR2T4() {
        return getText(ProgramSetupLocators.OFFSET_FROM_DOD_NEED_R2T4_VALUE);
    }

    public String getOffsetDODAutoFinalizeR2T4() {
        return getText(ProgramSetupLocators.OFFSET_FROM_DOD_TO_AUTO_FINALIZE_R2T4_VALUE);
    }

    // ── Date Used to Identify IOP ────────────────────────────────────────

    private static final String INHERIT = "Inherit";
    private static final String DATE_OF_DETERMINATION = "Date of Determination";
    private static final String DATE_OF_WITHDRAWAL = "Date of Withdrawal";
    private static final String AFTER_WDD = "Include disbursement as IOP if the disbursement release date is after the WDD";
    private static final String EQUAL_OR_AFTER_WDD = "Include disbursement as IOP if the disbursement release date equal to or after the WDD";
    private static final String AFTER_DOD = "Include disbursement as IOP if the disbursement release date is after the DOD";
    private static final String EQUAL_OR_AFTER_DOD = "Include disbursement as IOP if the disbursement release date equal to or after the DOD";

    public boolean isDateUsedIopOptionsValid() {
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        boolean validOptions = isVisibleNow(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, DATE_OF_DETERMINATION))
                && isVisibleNow(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, DATE_OF_WITHDRAWAL))
                && isVisibleNow(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, INHERIT))
                && (page.locator(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTIONS).count() == 3);
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        return validOptions;
    }

    public int getDateUsedIopOptionsCount() {
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        int optionsCount = page.locator(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTIONS).count();
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        return optionsCount;
    }

    public boolean isDateOfWithdrawalDefault() {
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        click(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, DATE_OF_WITHDRAWAL));
        return getText(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE).contains(AFTER_WDD);
    }

    public boolean isDateOfDeterminationDefault() {
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        click(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, DATE_OF_DETERMINATION));
        return getText(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE).contains(AFTER_DOD);
    }

    public boolean isDateIOPInheritDefault() {
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        click(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, INHERIT));
        return getText(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_VALUE).contains(INHERIT);
    }

    public boolean isIOPDateOfWithdrawalOptionsValid() {
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        click(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, DATE_OF_WITHDRAWAL));
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_SELECT);
        boolean validOptions = isVisibleNow(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, AFTER_WDD))
                && isVisibleNow(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, EQUAL_OR_AFTER_WDD))
                && (page.locator(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS).count() == 2);
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_SELECT);
        return validOptions;
    }

    public boolean isIOPDateOfDeterminationOptionsValid() {
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        click(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, DATE_OF_DETERMINATION));
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_SELECT);
        boolean validOptions = isVisibleNow(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, AFTER_DOD))
                && isVisibleNow(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, EQUAL_OR_AFTER_DOD))
                && (page.locator(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS).count() == 2);
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_SELECT);
        return validOptions;
    }

    public boolean isIOPInheritOptionsValid() {
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_SELECT);
        click(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_OPTION, INHERIT));
        click(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_SELECT);
        return isVisibleNow(String.format(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTION, INHERIT))
                && (page.locator(ProgramSetupLocators.DATE_USED_TO_IDENTIFY_IOP_DISBURSEMENT_OPTIONS).count() == 1);
    }

    // ── SEI Capella ──────────────────────────────────────────────────────

    public void setFSAEligibleEndDate(String targetDate) {
        fill(ProgramSetupLocators.FSA_ELIGIBLE_END_DATE_TEXTBOX, targetDate);
    }

    public void clickSaveProgram() {
        click(ProgramSetupLocators.SAVE_PROGRAM_BUTTON);
        waitForAjaxRequestToBeFinished();
    }

    public void clearFSAEligibleEndDate() {
        page.locator(ProgramSetupLocators.FSA_ELIGIBLE_END_DATE_TEXTBOX).clear();
    }

    public String getFsaEndDate() {
        return getText(ProgramSetupLocators.FSA_END_DATE);
    }
}
