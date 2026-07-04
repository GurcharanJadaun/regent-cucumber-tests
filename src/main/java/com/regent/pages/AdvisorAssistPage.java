package com.regent.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.regent.locators.AdvisorAssistLocators;

/** Ported from regent.pages.student.AdvisorAssistPage. */
public class AdvisorAssistPage extends BasePage {

    public AdvisorAssistPage(Page page) {
        super(page);
    }

    /** Returns a FrameLocator scoped to the embedded Advisor Assist portal iframe. */
    public FrameLocator switchToPortalIframe() {
        page.waitForTimeout(7000);
        FrameLocator frame = page.frameLocator(AdvisorAssistLocators.PORTAL_IFRAME);
        frame.locator(AdvisorAssistLocators.LOADING_SPINNER).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(timeout));
        return frame;
    }

    /** No-op placeholder for source's driver.switchTo().defaultContent() — Playwright FrameLocators
     * don't require explicitly switching back to the main frame; kept for call-site parity. */
    public void switchToAdvisorAssist() {
        // Intentionally empty: page-level locators already operate outside any FrameLocator scope.
    }
}
