package com.regent.locators;

public interface IsirLocators {
    String STUDENT_ACTIVE_ISIR_ROW = "//div[@data-rem-widgetname='StudentIsirGridView']//div[contains(@class,'k-grid-content')]//tr[contains(string(), 'Active')][1]";
    String MAKE_ACTIVE_BUTTON = "//div[@data-rem-widgetname='StudentIsirGridView']//div[contains(@class,'k-grid-content')]//tr[contains(string(), '%s')]/td[1]/a[text()='Make Active']";
    String GRID_ACTIVE_ISIR_ROW = "//div[@data-rem-widgetname='StudentIsirGridView']//div[contains(@class,'k-grid-content')]//tr[contains(string(),'%s') and .//strong[text()='Active']]";
    String ISIR_DETAIL_SECTION = "//ul[@data-rem-id='isirSections']/li/span[text()='%s']";

    String STUDENT_INFO_ORIGINAL_VALUE = "//div[@class='k-content ISIRReocrdData'][%s]//tr[%s]/td[4]";
    String STUDENT_INFO_CORRECTED_VALUE = "//div[@class='k-content ISIRReocrdData'][%s]//tr[%s]/td[5]";
    String STUDENT_INFO_CORRECTED_VALUE_FONT = "//div[@class='k-content ISIRReocrdData'][%s]//tr[%s]/td[5]/font";
    String STUDENT_INFO_SYMBOL_VALUE = "//div[@class='k-content ISIRReocrdData'][%s]//tr[%s]/td[1]";

    String CORRECT_ISIR_BUTTON = "//div[@data-rem-id='isirDetail']//button[contains(text(),'Correct')]";

    String EMAIL_INPUT = "#studentsEmailAddress";
    // Row containing the email label; label text varies across ISIR record years ("E-mail" vs "Email Address")
    String EMAIL_TEST_FIELD = "(//tr[contains(string(),'%s')])[1]";

    String CONTINUE_BUTTON = "//div[@class='three columns text-right']//button[@type='submit'][normalize-space()='Continue']";
    String FINISH_BUTTON = "(//button[contains(string(),'Finish')])[1]";

    // Kendo dropdown: correction wizard status/options widgets
    String VERIFICATION_STATUS_TRIGGER = "span[aria-owns='VerificationStatusId_listbox']";
    String VERIFICATION_STATUS_VERIFIED_OPTION = "//ul[@id='VerificationStatusId_listbox']/li[text()='Verified']";

    String CORRECTION_TRIGGER = "span[aria-owns='SubmitCorrectionsToCPS_listbox']";
    String CORRECTION_OPTION = "//ul[@id='SubmitCorrectionsToCPS_listbox']/li[text()='%s']";

    String PACKAGE_OPTION_TRIGGER = "span[aria-owns='IsirRecordPackageOptionId_listbox']";
    String PACKAGE_OPTION_OPTION = "//ul[@id='IsirRecordPackageOptionId_listbox']/li[text()='%s']";

    String STUDENT_INFORMATION_SECTION = "//ul[@data-rem-id='isirSections']/li[contains(string(),'Student Information')]";
    String CORRECT_WIZARD_SECTION = "//ul[@data-rem-id='isirSections']/li[contains(string(),'%s')]";

    String ISIR_GRID_ROW_CELLS = "//div[@data-rem-widgetname='StudentIsirGrid']/div[contains(@class,'content')]/table//tr[contains(string(),'%s')]/td";
    String ISIR_GRID_ROW_CELLS_2 = "//div[@data-rem-widgetname='StudentIsirGrid']/div[contains(@class,'content')]/table//tr[contains(string(),'%s') and contains(string(),'%s')]/td";
    String ISIR_GRID_FILTER = "//div[@data-rem-widgetname='StudentIsirGrid']//th[@data-field='%s']/a[@title='Filter']";
    String ISIR_FILTER_INPUT = "//form[@title='Show items with value that:' and @aria-hidden='false']//input[@title='Value']";
    String ACTIVE_FILTER_INPUT = "//form[@title='Show items with value that:' and @aria-hidden='false']//label[contains(string(),'Active')]/input[@type='radio']";
    String ISIR_FILTER_BUTTON = "//form[@title='Show items with value that:' and @aria-hidden='false']//button[text()='Filter']";

    String STUDENTS_FIRST_NAME_INPUT = "#studentsFirstName";
    String STUDENTS_MIDDLE_INITIAL_INPUT = "#middleInitial";
    String STUDENTS_LAST_NAME_INPUT = "#studentsLastName";
    String STUDENTS_PERMANENT_ADDRESS_INPUT = "#permanentMailingAddress";
    String STUDENTS_PERMANENT_CITY_INPUT = "#studentsPermanentCity";
    String STUDENTS_DOB_INPUT = "#studentsDateofBirth";
    String STUDENTS_PHONE_NUM_INPUT = "#studentsPermanentPhoneNumber";
    String STUDENTS_EMAIL_INPUT = "#studentsEmailAddress";
    String STUDENTS_DRIVERS_LICENSE_NUM = "#studentsDriversLicenseNumber";

    // Parental Information
    String PARENT2_LAST_NAME_INPUT = "#mothersStepmothersLastName";
    String PARENT_DISLOCATED_WORKER_TRIGGER = "span[aria-owns='_parentDislocatedWorker_listbox']";
    String PARENT_DISLOCATED_WORKER_OPTION = "//ul[@id='_parentDislocatedWorker_listbox']/li[text()='%s']";
    String PARENTS_GROSS_INCOME_INPUT = "#parentsAdjustedGrossIncomeFromIRSForm";
    String PARENTS_INCOME_TAX_PAID_INPUT = "#parentsUSIncomeTaxPaid";

    // Signatures Section
    String PROFESSIONAL_JUDGEMENT_TRIGGER = "span[aria-owns='_professionalJudgment_listbox']";
    String PROFESSIONAL_JUDGEMENT_OPTION = "//ul[@id='_professionalJudgment_listbox']/li[text()='%s']";
    String DEPENDENCY_OVERRIDE_TRIGGER = "span[aria-owns='_dependencyOverrideIndicator_listbox']";
    String DEPENDENCY_OVERRIDE_OPTION = "//ul[@id='_dependencyOverrideIndicator_listbox']/li[text()='%s']";

    // Corrections Step2
    String RESULTS_RECALC_ROW_CELLS = "//thead[./tr[@class='rem-correctionwiz-row-title']]/tr[contains(string(),'%s')]/td";
}
