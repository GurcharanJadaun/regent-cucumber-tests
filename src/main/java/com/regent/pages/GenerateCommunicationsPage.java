package com.regent.pages;

import com.regent.locators.GenerateCommunicationsLocators;
import com.microsoft.playwright.Page;

/** Ported from regent.pages.GenerateCommunicationsPage. */
public class GenerateCommunicationsPage extends BasePage {

    public GenerateCommunicationsPage(Page page) {
        super(page);
    }

    /**
     * Generating a communication can take up to ~1 hour for large batches (e.g. Com13), so this
     * polls the loadmask instead of a fixed wait — matches the reference's ~1hr max wait.
     */
    public void generateCommunication(String communicationTemplateName) {
        click(GenerateCommunicationsLocators.COMMUNICATION_TEMPLATE_DROPDOWN);
        String optionSelector = String.format(GenerateCommunicationsLocators.COMMUNICATION_TEMPLATE_OPTION, communicationTemplateName);
        click(optionSelector);

        // The Generate button was replaced with Execute in app 6.10.1.0; support both.
        if (isVisibleNow(GenerateCommunicationsLocators.EXECUTE_BUTTON)) {
            click(GenerateCommunicationsLocators.EXECUTE_BUTTON);
        } else {
            click(GenerateCommunicationsLocators.GENERATE_BUTTON);
        }

        for (int i = 0; i < 1800; i++) {
            page.waitForTimeout(2000);
            if (!isVisibleNow(GenerateCommunicationsLocators.LOADMASK)) break;
        }
        page.waitForTimeout(2000);
    }
}
