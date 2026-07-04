package com.regent.locators;

public interface AddResourceLocators {
    String ADD_ADJUSTMENT_BUTTON = "//button[@rem-trigger-event='ExternalResourceAdjustmentAddClick']";
    String RESOURCE_NAME = "#ExternalResourceAdjustment_Name";
    String RESOURCE_AMOUNT = "#ExternalResourceAdjustment_Amount";

    String SOURCE_TRIGGER = "span[aria-owns='ExternalResourceAdjustment_FundSourceCode_listbox']";
    String PRIVATE_SOURCE_OPTION = "//li[contains(string(),'Private')]";
    // Repeatedly opened per resource add — filter by :visible rather than the source's [last()].
    String SOURCE_OPTION = "#ExternalResourceAdjustment_FundSourceCode_listbox li:visible:text-is('%s')";

    String TYPE_TRIGGER = "span[aria-owns='ExternalResourceAdjustment_FundTypeCode_listbox']";
    String GRANT_TYPE_OPTION = "//li[contains(string(),'Grant')]";
    String TYPE_OPTION = "#ExternalResourceAdjustment_FundTypeCode_listbox li:visible:text-is('%s')";

    String RESOURCE_NOTES = "#ExternalResourceAdjustment_Note";

    String PAYMENT_PERIOD_TRIGGER = "span[aria-owns='ExternalResourceAdjustment_PaymentPeriodId_listbox']";
    String RESOURCE_PAYMENT_PERIOD_OPTION = "#ExternalResourceAdjustment_PaymentPeriodId_listbox li:visible:text-is('%s')";

    String SAVE_BUTTON = "//button[contains(string(),'Save')]";
    String RESOURCES_GRID_ROW = "//div[@data-rem-widgetname='AdjustResourceGrid']//tr[contains(string(),'%s')]";
    String REPACKAGE_BUTTON = "//button[@rem-trigger-event='externalResourceAdjustmentRepackageClick']";
    String CLOSE_BUTTON = "//button[contains(string(),'Close')]";
    String LOADMASK = "//div[@class='loadmask']";
}
