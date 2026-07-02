package com.regent.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features  = "src/test/resources/features",
        glue      = {"com.regent.cucumber.steps", "com.regent.cucumber.hooks"},
        plugin    = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json"
        },
        tags      = "not @ignore"
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
