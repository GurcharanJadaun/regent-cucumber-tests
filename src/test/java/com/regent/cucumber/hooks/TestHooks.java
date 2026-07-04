package com.regent.cucumber.hooks;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.regent.cucumber.context.ScenarioContext;
import com.regent.cucumber.reporting.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestHooks {

    private final ScenarioContext ctx;

    public TestHooks(ScenarioContext ctx) {
        this.ctx = ctx;
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println(">> Starting scenario: " + scenario.getName());
        // The Extent test itself is created by ExtentReportPlugin (TestCaseStarted event), which
        // is the only place Cucumber exposes per-step text/results for the report.
        ctx.initBrowser();
    }

    @After
    public void tearDown(Scenario scenario) {
        ExtentTest test = ExtentManager.getTest();
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ctx.getPage()
                        .screenshot(new com.microsoft.playwright.Page.ScreenshotOptions()
                                .setFullPage(true));
                scenario.attach(screenshot, "image/png", "failure-screenshot");
                attachScreenshotToExtent(test, scenario, screenshot);
            } catch (Exception ignored) {}

            if (test != null) test.fail("Scenario failed: " + scenario.getName());
        } else if (test != null) {
            test.pass("Scenario passed: " + scenario.getName());
        }
        ctx.closeBrowser();
        System.out.println("<< Finished scenario: " + scenario.getName()
                + " [" + scenario.getStatus() + "]");
    }

    /**
     * Extent's HTML report references screenshots by a path relative to the report file itself
     * (target/extent-reports/ExtentReport.html), so the file must live under a "screenshots"
     * subfolder there — writing straight to Cucumber's own attach() bytes isn't visible to Extent.
     */
    private void attachScreenshotToExtent(ExtentTest test, Scenario scenario, byte[] screenshot) {
        if (test == null) return;
        try {
            Path dir = Paths.get(ExtentManager.getReportDir(), "screenshots");
            Files.createDirectories(dir);
            String fileName = scenario.getName().replaceAll("[^a-zA-Z0-9]+", "_")
                    + "_" + scenario.getLine() + ".png";
            Path screenshotFile = dir.resolve(fileName);
            try (FileOutputStream fos = new FileOutputStream(screenshotFile.toFile())) {
                fos.write(screenshot);
            }
            test.fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath("screenshots/" + fileName).build());
        } catch (Exception ignored) {}
    }
}
