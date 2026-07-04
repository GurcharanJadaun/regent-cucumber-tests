package com.regent.locators;

/** Locators ported from regent.pages.GenerateCommunicationsPage in the reference project. */
public interface GenerateCommunicationsLocators {
    String COMMUNICATION_TEMPLATE_DROPDOWN = "span[aria-owns='IOProcess_CommunicationId_listbox']";
    String COMMUNICATION_TEMPLATE_OPTION   = "//div[@id='IOProcess_CommunicationId-list']//li[text()='%s']";
    String GENERATE_BUTTON                 = "//button[normalize-space()='Generate']";
    // 4/25/2025 KLS: in 6.10.1.0 the Generate button was renamed to Execute.
    String EXECUTE_BUTTON                  = "//button[normalize-space()='Execute']";
    String LOADMASK                        = "//div[@class='loadmask-msg']/div[contains(text(),'Executing...')]";
}
