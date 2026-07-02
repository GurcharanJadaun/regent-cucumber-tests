package com.regent.cucumber.steps;

import com.regent.config.ConfigReader;
import com.regent.cucumber.context.ScenarioContext;
import com.regent.pages.AcademicPlanPage;
import com.regent.pages.ImportProcessPage;
import com.regent.pages.LeftNavPage;
import com.regent.pages.LoginPage;
import com.regent.pages.ProcessLogPage;
import com.regent.utils.StudentUser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RST727Steps {

    private final ScenarioContext ctx;
    private final LoginPage loginPage;
    private final LeftNavPage leftNavPage;
    private final ImportProcessPage importProcessPage;
    private final ProcessLogPage processLogPage;
    private final AcademicPlanPage academicPlanPage;

    public RST727Steps(ScenarioContext ctx) {
        this.ctx               = ctx;
        this.loginPage         = ctx.getLoginPage();
        this.leftNavPage       = ctx.getLeftNavPage();
        this.importProcessPage = ctx.getImportProcessPage();
        this.processLogPage    = ctx.getProcessLogPage();
        this.academicPlanPage  = ctx.getAcademicPlanPage();
    }

    @Given("I am logged in as admin")
    public void iAmLoggedInAsAdmin() {
        loginPage.loginAsAdmin();
        Assert.assertTrue(leftNavPage.isNavMenuVisible(),
                "Navigation menu not visible after login");
    }

    @Given("the enterprise is configured as:")
    public void theEnterpriseIsConfiguredAs(DataTable dataTable) {
        Map<String, String> config = new HashMap<>(dataTable.asMap(String.class, String.class));
        // Supplement IDs with date defaults from config.properties (dates are not in the feature file)
        config.putIfAbsent("programStartDate",       ConfigReader.getProgramStartDate());
        config.putIfAbsent("termBeforeStartDate",    ConfigReader.getTermBeforeStartDate());
        config.putIfAbsent("termBeforeEndDate",      ConfigReader.getTermBeforeEndDate());
        config.putIfAbsent("termPreviousStartDate",  ConfigReader.getTermPreviousStartDate());
        config.putIfAbsent("termPreviousEndDate",    ConfigReader.getTermPreviousEndDate());
        config.putIfAbsent("termActualStartDate",    ConfigReader.getTermActualStartDate());
        config.putIfAbsent("termActualEndDate",      ConfigReader.getTermActualEndDate());
        ctx.setEnterpriseConfig(config);
    }

    @Given("a new student is generated")
    public void aNewStudentIsGenerated() {
        Map<String, String> cfg = ctx.getEnterpriseConfig();
        StudentUser student = new StudentUser(
                cfg.getOrDefault("campusId", ""),
                cfg.getOrDefault("siteId", ""));
        ctx.setCurrentStudent(student);
        System.out.println("Generated: " + student);
    }

    @When("I import the student via SBL with option {string} and term {string}")
    public void iImportStudentViaSbl(String sblOption, String termType) {
        leftNavPage.clickImportProcess();
        importProcessPage.importSbl(ctx.getCurrentStudent(), sblOption, termType, ctx.getEnterpriseConfig());
    }

    @And("I wait for the SBL import to complete")
    public void iWaitForSblImportToComplete() {
        leftNavPage.clickProcessLog();
        processLogPage.waitForSblComplete(ctx.getCurrentStudent());
    }

    @When("I import the ISIR of type {string}")
    public void iImportIsirOfType(String isirType) {
        leftNavPage.clickImportProcess();
        importProcessPage.importIsir(ctx.getCurrentStudent(), isirType,
                ctx.getEnterpriseValue("federalSchoolCode"));
    }

    @And("I wait for the ISIR import to complete")
    public void iWaitForIsirImportToComplete() {
        leftNavPage.clickProcessLog();
        processLogPage.waitForIsirComplete(ctx.getCurrentStudent());
    }

    @When("I navigate to the student's Academic Plan")
    public void iNavigateToStudentAcademicPlan() {
        processLogPage.navigateToStudent(ctx.getCurrentStudent());
        leftNavPage.clickAcademicPlanTab();
    }

    @When("I run basic packaging")
    public void iRunBasicPackaging() {
        academicPlanPage.clickMapButton();
        academicPlanPage.runBasicPackaging();
        academicPlanPage.clickFinish();
        academicPlanPage.waitForAcademicPlanAppear();
    }

    @Then("the following awards should be present:")
    public void theFollowingAwardsShouldBePresent(DataTable dataTable) {
        List<String> awards = dataTable.asList();
        for (String award : awards) {
            Assert.assertTrue(academicPlanPage.isAwardPresent(award.trim()),
                    "Expected award not found on Academic Plan: " + award);
        }
    }

    @And("the following awards should NOT be present:")
    public void theFollowingAwardsShouldNotBePresent(DataTable dataTable) {
        List<String> awards = dataTable.asList();
        for (String award : awards) {
            Assert.assertFalse(academicPlanPage.isAwardPresent(award.trim()),
                    "Award was unexpectedly found on Academic Plan: " + award);
        }
    }
}
