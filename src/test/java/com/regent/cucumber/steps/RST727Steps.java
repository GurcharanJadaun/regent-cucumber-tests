package com.regent.cucumber.steps;

import com.regent.config.ConfigReader;
import com.regent.cucumber.context.ScenarioContext;
import com.regent.locators.AddAwardLocators;
import com.regent.pages.AcademicPlanPage;
import com.regent.pages.AddAwardPage;
import com.regent.pages.AwardsPage;
import com.regent.pages.DocumentsPage;
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
    private final DocumentsPage documentsPage;
    private final AwardsPage awardsPage;
    private final AddAwardPage addAwardPage;

    public RST727Steps(ScenarioContext ctx) {
        this.ctx               = ctx;
        this.loginPage         = ctx.getLoginPage();
        this.leftNavPage       = ctx.getLeftNavPage();
        this.importProcessPage = ctx.getImportProcessPage();
        this.processLogPage    = ctx.getProcessLogPage();
        this.academicPlanPage  = ctx.getAcademicPlanPage();
        this.documentsPage     = ctx.getDocumentsPage();
        this.awardsPage        = ctx.getAwardsPage();
        this.addAwardPage      = ctx.getAddAwardPage();
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

    @And("I refresh the Academic Plan")
    public void iRefreshTheAcademicPlan() {
        academicPlanPage.refreshAcademicPlan();
    }

    /** First visit to Documents in a scenario: from Process Log, via Students tab + view-user link. */
    @When("I navigate to the student's Documents")
    public void iNavigateToStudentDocuments() {
        processLogPage.navigateToStudent(ctx.getCurrentStudent());
        leftNavPage.clickDocumentsTab();
    }

    /** Subsequent visit to Documents within the same scenario (already inside the student profile). */
    @When("I return to the student's Documents")
    public void iReturnToStudentDocuments() {
        leftNavPage.clickDocumentsTab();
    }

    @When("I return to the student's Academic Plan")
    public void iReturnToStudentAcademicPlan() {
        leftNavPage.clickAcademicPlanTab();
    }

    @When("I add a required document {string}")
    public void iAddARequiredDocument(String documentName) {
        documentsPage.addDocument(documentName);
    }

    @When("I mark the document {string} as {string}")
    public void iMarkTheDocumentAs(String documentName, String status) {
        documentsPage.setStatusForDocument(documentName, status);
    }

    @When("I navigate to the student's Awards")
    public void iNavigateToStudentAwards() {
        leftNavPage.clickAwardsTab();
    }

    @When("I add a manual award for the Parent PLUS fund")
    public void iAddAManualAwardForTheParentPlusFund() {
        awardsPage.clickAddAward();
        addAwardPage.chooseOptionsOnStep1(AddAwardLocators.PARENT_PLUS_FUND);
        addAwardPage.chooseOptionsOnStep2();
        addAwardPage.clickContinueButtonOnStep3();
        addAwardPage.clickContinueButtonOnStep4();
        addAwardPage.finishAddAwardProcess();
    }

    @And("I reveal hidden awards")
    public void iRevealHiddenAwards() {
        academicPlanPage.clickFirstHiddenAwardLink();
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
            Assert.assertTrue(academicPlanPage.isAwardAbsentEventually(award.trim(), 90),
                    "Award was unexpectedly found on Academic Plan: " + award);
        }
    }
}
