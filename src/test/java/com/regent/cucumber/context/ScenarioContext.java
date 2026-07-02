package com.regent.cucumber.context;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.regent.config.ConfigReader;
import com.regent.pages.AcademicPlanPage;
import com.regent.pages.ImportProcessPage;
import com.regent.pages.LeftNavPage;
import com.regent.pages.LoginPage;
import com.regent.pages.ProcessLogPage;
import com.regent.utils.StudentUser;

public class ScenarioContext {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    private LoginPage loginPage;
    private LeftNavPage leftNavPage;
    private ImportProcessPage importProcessPage;
    private ProcessLogPage processLogPage;
    private AcademicPlanPage academicPlanPage;

    private StudentUser currentStudent;
    private java.util.Map<String, String> enterpriseConfig = new java.util.HashMap<>();

    public void initBrowser() {
        playwright = Playwright.create();

        com.microsoft.playwright.BrowserType browserType;
        switch (ConfigReader.getBrowser().toLowerCase()) {
            case "firefox": browserType = playwright.firefox(); break;
            case "webkit":  browserType = playwright.webkit();  break;
            default:        browserType = playwright.chromium();
        }

        com.microsoft.playwright.BrowserType.LaunchOptions launchOptions =
                new com.microsoft.playwright.BrowserType.LaunchOptions()
                        .setHeadless(ConfigReader.isHeadless());

        browser = browserType.launch(launchOptions);
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.setDefaultTimeout(ConfigReader.getTimeout());

        loginPage         = new LoginPage(page);
        leftNavPage       = new LeftNavPage(page);
        importProcessPage = new ImportProcessPage(page);
        processLogPage    = new ProcessLogPage(page);
        academicPlanPage  = new AcademicPlanPage(page);
    }

    public void closeBrowser() {
        if (page != null)          page.close();
        if (browserContext != null) browserContext.close();
        if (browser != null)       browser.close();
        if (playwright != null)    playwright.close();
    }

    public Page getPage()                           { return page; }
    public LoginPage getLoginPage()                 { return loginPage; }
    public LeftNavPage getLeftNavPage()             { return leftNavPage; }
    public ImportProcessPage getImportProcessPage() { return importProcessPage; }
    public ProcessLogPage getProcessLogPage()       { return processLogPage; }
    public AcademicPlanPage getAcademicPlanPage()   { return academicPlanPage; }

    public StudentUser getCurrentStudent()              { return currentStudent; }
    public void setCurrentStudent(StudentUser student)  { this.currentStudent = student; }

    public java.util.Map<String, String> getEnterpriseConfig()                        { return enterpriseConfig; }
    public void setEnterpriseConfig(java.util.Map<String, String> config)             { this.enterpriseConfig = config; }
    public String getEnterpriseValue(String key)                                      { return enterpriseConfig.getOrDefault(key, ""); }
}
