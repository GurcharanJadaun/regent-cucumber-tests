package com.regent.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        // Different feature suites target different environments (e.g. RS-T727 on REMQA47,
        // RS-31699 on REMQA1) — select the file via -Dconfig.file=... at the mvn invocation
        // level, defaulting to the original REMQA47 config so existing runs are unaffected.
        String configFile = System.getProperty("config.file", "config.properties");
        try (InputStream is = ConfigReader.class.getClassLoader()
                .getResourceAsStream(configFile)) {
            if (is == null) throw new RuntimeException(configFile + " not found on classpath");
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + configFile, e);
        }
    }

    public static String getBrowser()       { return props.getProperty("browser", "chromium"); }
    public static boolean isHeadless()      { return Boolean.parseBoolean(props.getProperty("headless", "false")); }
    public static int getTimeout()          { return Integer.parseInt(props.getProperty("timeout", "30000")); }
    public static String getBaseUrl()       { return props.getProperty("base.url"); }
    public static String getAdminUsername() { return props.getProperty("admin.username"); }
    public static String getAdminPassword() { return props.getProperty("admin.password"); }
    public static String getDateToSet()     { return props.getProperty("date.to.set", ""); }
    public static String getSshUsername()   { return props.getProperty("ssh.username", ""); }
    public static String getSshPassword()   { return props.getProperty("ssh.password", ""); }

    public static String getExternalCampusId()   { return props.getProperty("enterprise.external.campus.id", ""); }
    public static String getExternalSiteId()     { return props.getProperty("enterprise.external.site.id", ""); }
    public static String getExternalProgramId()  { return props.getProperty("enterprise.external.program.id", ""); }
    public static String getFederalSchoolCode()  { return props.getProperty("enterprise.federal.school.code", ""); }
    public static String getProgramStartDate()   { return props.getProperty("enterprise.program.start.date", "2024-09-01"); }

    public static String getTermBeforeId()         { return props.getProperty("enterprise.term.before.id", ""); }
    public static String getTermBeforeStartDate()  { return props.getProperty("enterprise.term.before.start.date", ""); }
    public static String getTermBeforeEndDate()    { return props.getProperty("enterprise.term.before.end.date", ""); }
    public static String getTermPreviousId()       { return props.getProperty("enterprise.term.previous.id", ""); }
    public static String getTermPreviousStartDate(){ return props.getProperty("enterprise.term.previous.start.date", ""); }
    public static String getTermPreviousEndDate()  { return props.getProperty("enterprise.term.previous.end.date", ""); }
    public static String getTermActualId()         { return props.getProperty("enterprise.term.actual.id", ""); }
    public static String getTermActualStartDate()  { return props.getProperty("enterprise.term.actual.start.date", ""); }
    public static String getTermActualEndDate()    { return props.getProperty("enterprise.term.actual.end.date", ""); }
}
