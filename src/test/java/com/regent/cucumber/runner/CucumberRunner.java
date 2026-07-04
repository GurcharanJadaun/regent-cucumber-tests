package com.regent.cucumber.runner;

import com.regent.cucumber.reporting.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;

@CucumberOptions(
        features  = "src/test/resources/features",
        glue      = {"com.regent.cucumber.steps", "com.regent.cucumber.hooks"},
        plugin    = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "com.regent.cucumber.reporting.ExtentReportPlugin"
        },
        tags      = "not @ignore"
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @AfterClass(alwaysRun = true)
    public void writeExtentReport() {
        ExtentManager.flush();
    }
}
