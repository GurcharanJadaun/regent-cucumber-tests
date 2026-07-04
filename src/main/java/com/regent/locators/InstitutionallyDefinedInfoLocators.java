package com.regent.locators;

public interface InstitutionallyDefinedInfoLocators {
    String UDF_NAME_TRIGGER = "span[aria-owns='StudentAdditionalInfo_Code_listbox']";
    String UDF_NAME_OPTION = "//ul[@id='StudentAdditionalInfo_Code_listbox']/li[@role='option' and contains(string(),'%s')]";
    String UDF_VALUE = "#StudentAdditionalInfo_Value";
    String EXTERNAL_UDF_ID = "#StudentAdditionalInfo_ExternalUDFId";
    String EFFECTIVE_START_DATE = "#StudentAdditionalInfo_EffectiveStartDate";
    String EFFECTIVE_END_DATE = "#StudentAdditionalInfo_EffectiveEndDate";
    String INPUT_UDF_DATA_SAVE_BUTTON = "//div[contains(@class,'addStudentAddtInfo')]//button[contains(string(),'Save')]";
}
