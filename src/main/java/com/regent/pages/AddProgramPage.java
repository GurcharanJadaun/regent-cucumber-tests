package com.regent.pages;

import com.microsoft.playwright.Page;
import com.regent.locators.AddProgramLocators;

public class AddProgramPage extends BasePage {

    public AddProgramPage(Page page) {
        super(page);
    }

    /**
     * Mirrors the source's single createProgram() flow: fills every field on the Add Program
     * form and saves. Note the default-enrollment-level dropdown is only opened (clicked), never
     * given a selected option here — same as the source, which never picks from that list either.
     */
    public void createProgram(String programName, String programType, String programDisplayName,
                               String externalProgramId, String cipCode, String programNumberOfYears,
                               String programNumberOfUnits, String programNumberOfWeeks,
                               boolean inheritCpuTypes, String academicCalendarType, String pellFormula,
                               String pellCoa, String unitsForYear2, String unitsForYear3,
                               String unitsForYear4, String unitsForYear5) {
        fill(AddProgramLocators.PROGRAM_NAME, programName);
        click(AddProgramLocators.PROGRAM_TYPE_DROPDOWN);
        click(String.format(AddProgramLocators.PROGRAM_TYPE_OPTION, programType));
        click(AddProgramLocators.DEFAULT_ENROLL_LEVEL_DROPDOWN);
        fill(AddProgramLocators.PROGRAM_DISPLAY_NAME, programDisplayName);
        fill(AddProgramLocators.PROGRAM_EXTERNAL_ID, externalProgramId);
        fill(AddProgramLocators.CIP_CODE, cipCode);
        click(AddProgramLocators.NUMBER_OF_YEARS_DROPDOWN);
        click(String.format(AddProgramLocators.NUMBER_OF_YEARS_OPTION, programNumberOfYears));
        fill(AddProgramLocators.NUMBER_OF_UNITS, programNumberOfUnits);
        fill(AddProgramLocators.NUMBER_OF_WEEKS, programNumberOfWeeks);
        setCpuTypesCheckbox(inheritCpuTypes);
        click(AddProgramLocators.ACADEMIC_CALENDAR_TYPE_DROPDOWN);
        click(AddProgramLocators.ACADEMIC_CALENDAR_TYPE_OPTION);
        click(AddProgramLocators.PELL_FORMULA_DROPDOWN);
        click(AddProgramLocators.PELL_FORMULA_OPTION);
        fill(AddProgramLocators.PELL_COA, pellCoa);
        fill(AddProgramLocators.UNITS_FOR_YEAR2, unitsForYear2);
        fill(AddProgramLocators.UNITS_FOR_YEAR3, unitsForYear3);
        fill(AddProgramLocators.UNITS_FOR_YEAR4, unitsForYear4);
        fill(AddProgramLocators.UNITS_FOR_YEAR5, unitsForYear5);
        click(AddProgramLocators.SAVE_BUTTON);
    }

    private void setCpuTypesCheckbox(boolean check) {
        boolean checked = page.locator(AddProgramLocators.CPU_TYPES_CHECKBOX).isChecked();
        if (checked != check) {
            click(AddProgramLocators.CPU_TYPES_CHECKBOX);
        }
    }
}
