package com.regent.cucumber.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestStepFinished;

/**
 * Registered via @CucumberOptions(plugin = {...}) — this is the only place Cucumber exposes
 * individual step text/results (TestHooks' @Before/@After only see the scenario as a whole).
 * TestHooks still owns the screenshot-on-failure attachment and the final pass/fail marker.
 */
public class ExtentReportPlugin implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class,
                event -> ExtentManager.startTest(event.getTestCase().getName()));
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        publisher.registerHandlerFor(TestRunFinished.class, event -> ExtentManager.flush());
    }

    private void onTestStepFinished(TestStepFinished event) {
        ExtentTest test = ExtentManager.getTest();
        // Only Gherkin steps have step text; @Before/@After hook steps don't and are skipped.
        if (test == null || !(event.getTestStep() instanceof PickleStepTestStep)) return;

        PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
        String stepText = step.getStep().getKeyword() + step.getStep().getText();
        Status status = mapStatus(event.getResult().getStatus());

        if (status == Status.FAIL) {
            Throwable error = event.getResult().getError();
            String detail = error != null ? error.getMessage() : "Step failed";
            test.fail(stepText + " — " + detail);
        } else {
            test.log(status, stepText);
        }
    }

    private Status mapStatus(io.cucumber.plugin.event.Status cucumberStatus) {
        switch (cucumberStatus) {
            case PASSED:  return Status.PASS;
            case FAILED:  return Status.FAIL;
            case SKIPPED: return Status.SKIP;
            case PENDING:
            case UNDEFINED:
                return Status.WARNING;
            default: return Status.INFO;
        }
    }
}
