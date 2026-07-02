package com.regent.cucumber.hooks;

import com.regent.cucumber.context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class TestHooks {

    private final ScenarioContext ctx;

    public TestHooks(ScenarioContext ctx) {
        this.ctx = ctx;
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println(">> Starting scenario: " + scenario.getName());
        ctx.initBrowser();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ctx.getPage()
                        .screenshot(new com.microsoft.playwright.Page.ScreenshotOptions()
                                .setFullPage(true));
                scenario.attach(screenshot, "image/png", "failure-screenshot");
            } catch (Exception ignored) {}
        }
        ctx.closeBrowser();
        System.out.println("<< Finished scenario: " + scenario.getName()
                + " [" + scenario.getStatus() + "]");
    }
}
