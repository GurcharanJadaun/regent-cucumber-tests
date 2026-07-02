package com.regent.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class StudentUser {

    private static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter YYYYMMDD    = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String ssn;
    private final String birthDate;        // YYYY-MM-DD
    private final String birthDateIsir;    // YYYYMMDD
    private final String externalStudentId;
    private final String externalStudentId2;
    private final String externalCampusId;
    private final String externalSiteId;
    private final String email;
    private final String phoneNumber;

    public StudentUser(String externalCampusId, String externalSiteId) {
        this.firstName        = "AUTO" + randomAlpha(8).toUpperCase();
        this.lastName         = "AUTO" + randomAlpha(8).toUpperCase();
        this.middleName       = randomAlpha(1).toUpperCase();
        this.ssn              = randomNumeric(9);
        LocalDate dob         = LocalDate.now().minusYears(22);
        this.birthDate        = dob.format(YYYY_MM_DD);
        this.birthDateIsir    = dob.format(YYYYMMDD);
        this.externalStudentId  = randomAlpha(2).toUpperCase() + randomNumeric(9);
        this.externalStudentId2 = randomAlpha(1).toUpperCase() + randomNumeric(9);
        this.externalCampusId = externalCampusId;
        this.externalSiteId   = externalSiteId;
        this.email            = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@mailinator.com";
        this.phoneNumber      = randomNumeric(10);
    }

    // ── Accessors ────────────────────────────────────────────────────────────────

    public String getFirstName()           { return firstName; }
    public String getLastName()            { return lastName; }
    public String getMiddleName()          { return middleName; }
    public String getSsn()                 { return ssn; }
    public String getDob()                 { return birthDate; }
    public String getDobIsirFormat()       { return birthDateIsir; }
    public String getExternalStudentId()   { return externalStudentId; }
    public String getExternalStudentId2()  { return externalStudentId2; }
    public String getExternalCampusId()    { return externalCampusId; }
    public String getExternalSiteId()      { return externalSiteId; }
    public String getEmail()               { return email; }
    public String getPhoneNumber()         { return phoneNumber; }
    public String getMatriculated()        { return "true"; }
    public String getFullName()            { return firstName + " " + middleName + " " + lastName; }

    /** Filename base used in SBL/ISIR import log rows (matches original logic). */
    public String getSblFileName()  { return lastName.toLowerCase() + "_sbl.xml"; }
    public String getIsirFileName() { return lastName.toLowerCase() + "_isir.txt"; }

    /** Last name padded to 35 chars for ISIR 2025 format */
    public String getLastNameForIsir() {
        return padRight(lastName, 35);
    }

    /** First name padded to 35 chars for ISIR 2025 format */
    public String getFirstNameForIsir() {
        return padRight(firstName, 35);
    }

    /** Middle name padded to 15 chars for ISIR 2025 format */
    public String getMiddleNameForIsir() {
        return padRight(middleName, 15);
    }

    // ── Helpers ──────────────────────────────────────────────────────────────────

    private static String randomAlpha(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + ThreadLocalRandom.current().nextInt(26)));
        }
        return sb.toString();
    }

    private static String randomNumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ThreadLocalRandom.current().nextInt(10));
        }
        return sb.toString();
    }

    private static String padRight(String s, int width) {
        if (s.length() >= width) return s.substring(0, width);
        return s + " ".repeat(width - s.length());
    }

    @Override
    public String toString() {
        return String.format("StudentUser[name=%s %s, ssn=%s]", firstName, lastName, ssn);
    }
}
