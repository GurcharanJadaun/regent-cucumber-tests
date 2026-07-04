package com.regent.cucumber.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/** Single ExtentReports instance for the whole suite; flushed once after all scenarios finish. */
public final class ExtentManager {

    private static final String REPORT_DIR = "target/extent-reports";
    private static final ExtentReports EXTENT = create();
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

    private ExtentManager() {}

    private static ExtentReports create() {
        ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_DIR + "/ExtentReport.html");
        spark.config().setDocumentTitle("Regent Cucumber Test Report");
        spark.config().setReportName("RS-T727 Suite Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }

    public static ExtentTest startTest(String name) {
        ExtentTest test = EXTENT.createTest(name);
        CURRENT_TEST.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return CURRENT_TEST.get();
    }

    public static String getReportDir() {
        return REPORT_DIR;
    }

    public static void flush() {
        EXTENT.flush();
    }
}
