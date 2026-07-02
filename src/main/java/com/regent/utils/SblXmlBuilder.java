package com.regent.utils;

import com.regent.config.ConfigReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Builds an SBL XML file for a Regent student import.
 *
 * SBL option → course layout (matches original SblOption.java):
 *   PELL     : 3 before (4 units) + 2 previous (6 units) + 1 actual (12 units)
 *   DEFAULT  : 4 before (3 units) + 4 previous (3 units) + 4 actual (3 units)
 *   NON_TERM : 1 before (12 units) + 1 previous (12 units) + 1 actual (12 units)
 *
 * Output: target/files/<lastname>_sbl.xml  (matches original target.path default)
 */
public class SblXmlBuilder {

    // xs:dateTime requires a 'T' separator, not a space
    private static final DateTimeFormatter DT_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
    private static final DateTimeFormatter MMDDYYYY = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final String OUTPUT_DIR = "target/files";

    public enum SblOption { PELL, DEFAULT, NON_TERM }

    public static String build(StudentUser student, String sblOption, String termType,
                               java.util.Map<String, String> enterpriseConfig) {
        SblOption option = SblOption.valueOf(sblOption.toUpperCase());
        return build(student, option, enterpriseConfig);
    }

    public static String build(StudentUser student, SblOption option,
                               java.util.Map<String, String> enterpriseConfig) {
        int[] layout = courseLayout(option);
        int beforeCount   = layout[0];
        int previousCount = layout[1];
        int actualCount   = layout[2];

        try {
            Files.createDirectories(Paths.get(OUTPUT_DIR));
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();

            Element students = doc.createElement("students");
            doc.appendChild(students);
            students.setAttribute("sblCreatedDate", LocalDateTime.now().format(DT_FMT));
            students.setAttribute("xmlns", "http://www.regenteducation.com/re8/StudentBatchLoad");

            Element studentEl = doc.createElement("student");
            students.appendChild(studentEl);
            studentEl.setAttribute("socialSecurityNumber",  student.getSsn());
            studentEl.setAttribute("birthDate",             student.getDob());
            studentEl.setAttribute("lastName",              student.getLastName());
            studentEl.setAttribute("firstName",             student.getFirstName());
            studentEl.setAttribute("middleName",            student.getMiddleName());
            studentEl.setAttribute("externalCampusId",      student.getExternalCampusId());
            studentEl.setAttribute("externalSiteId",        student.getExternalSiteId());
            studentEl.setAttribute("matriculated",          student.getMatriculated());
            studentEl.setAttribute("externalStudentId1",    student.getExternalStudentId());
            studentEl.setAttribute("externalStudentId2",    student.getExternalStudentId2());

            Element acadInfo = doc.createElement("academicInformation");
            studentEl.appendChild(acadInfo);
            acadInfo.setAttribute("externalProgramId", enterpriseConfig.getOrDefault("programId", ""));
            acadInfo.setAttribute("programStartDate",  enterpriseConfig.getOrDefault("programStartDate", ""));
            acadInfo.setAttribute("programComplete",   "false");

            Element demo = doc.createElement("demographic");
            studentEl.appendChild(demo);
            demo.setAttribute("ethnicity",              "Other");
            demo.setAttribute("race",                   "White");
            demo.setAttribute("gender",                 "Male");
            demo.setAttribute("maritalStatus",          "Single");
            demo.setAttribute("externalHousingStatusId","OffCampus");
            demo.setAttribute("citizenshipStatus",      "USCitizen");
            demo.setAttribute("residencyStatus",        "InState");
            demo.setAttribute("stateOfLegalResidence",  "NJ");
            demo.setAttribute("residencyDate",          "1994-01-01");
            demo.setAttribute("driversLicense",         "RK0969874");
            demo.setAttribute("admittedDate",           "2013-01-01");
            demo.setAttribute("veteranStatus",          "Blank");
            demo.setAttribute("disabilityStatus",       "No");

            Element testScore = doc.createElement("userDefinedTestScore");
            studentEl.appendChild(testScore);
            testScore.setAttribute("testName",  "SAT");
            testScore.setAttribute("testScore", "1120");

            addAddress(doc, studentEl, "Original", "02 WALNUT ROAD", "2nd Line Street", "ACWORTH", "NH", "03601");

            Element phone = doc.createElement("phone");
            studentEl.appendChild(phone);
            phone.setAttribute("type",   "Home");
            phone.setAttribute("number", student.getPhoneNumber());

            Element email = doc.createElement("email");
            studentEl.appendChild(email);
            email.setAttribute("email", student.getEmail());
            email.setAttribute("type",  "Home");

            addParentspouse(doc, studentEl, student);

            int beforeUnits   = 12 / Math.max(beforeCount, 1);
            int previousUnits = 12 / Math.max(previousCount, 1);
            int actualUnits   = 12 / Math.max(actualCount, 1);

            String programId = enterpriseConfig.getOrDefault("programId", "");
            for (int i = 0; i < beforeCount; i++) {
                studentEl.appendChild(courseData(doc,
                        enterpriseConfig.getOrDefault("termBeforeId", ""),
                        enterpriseConfig.getOrDefault("termBeforeStartDate", ""),
                        enterpriseConfig.getOrDefault("termBeforeEndDate", ""),
                        String.valueOf(beforeUnits), true, programId));
            }
            for (int i = 0; i < previousCount; i++) {
                studentEl.appendChild(courseData(doc,
                        enterpriseConfig.getOrDefault("termPreviousId", ""),
                        enterpriseConfig.getOrDefault("termPreviousStartDate", ""),
                        enterpriseConfig.getOrDefault("termPreviousEndDate", ""),
                        String.valueOf(previousUnits), true, programId));
            }
            for (int i = 0; i < actualCount; i++) {
                studentEl.appendChild(courseData(doc,
                        enterpriseConfig.getOrDefault("termActualId", ""),
                        enterpriseConfig.getOrDefault("termActualStartDate", ""),
                        enterpriseConfig.getOrDefault("termActualEndDate", ""),
                        String.valueOf(actualUnits), false, programId));
            }

            String filePath = OUTPUT_DIR + "/" + student.getSblFileName();
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(doc), new StreamResult(new File(filePath)));
            return filePath;

        } catch (Exception e) {
            throw new RuntimeException("Failed to build SBL XML for " + student.getLastName(), e);
        }
    }

    /** date.to.set (MM/dd/yyyy) reformatted to yyyy-MM-dd, falling back to today if unset. */
    private static String currentTestDate() {
        String dateToSet = ConfigReader.getDateToSet();
        if (dateToSet == null || dateToSet.isEmpty()) {
            return LocalDate.now().toString();
        }
        return LocalDate.parse(dateToSet, MMDDYYYY).toString();
    }

    /** Matches AddressSbl field order/defaults from the reference SblBuilder; country is always "US". */
    private static void addAddress(Document doc, Element parent, String type, String line1,
                                    String line2, String city, String stateProvince, String postalCode) {
        Element addr = doc.createElement("address");
        parent.appendChild(addr);
        addr.setAttribute("type",          type);
        addr.setAttribute("line1",         line1);
        addr.setAttribute("line2",         line2);
        addr.setAttribute("city",          city);
        addr.setAttribute("stateProvince", stateProvince);
        addr.setAttribute("postalCode",    postalCode);
        addr.setAttribute("country",       "US");
    }

    /**
     * Matches ParentspouseSbl's default (parameterized) constructor: a required element for
     * dependent-student SAI/eligibility calculation — omitting it results in zero awards on
     * packaging even though the SBL import itself completes successfully.
     */
    private static void addParentspouse(Document doc, Element studentEl, StudentUser student) {
        Element parent = doc.createElement("parentspouse");
        studentEl.appendChild(parent);
        String firstName = "F" + student.getFirstName();
        String lastName  = student.getLastName();
        parent.setAttribute("type",                 "Father");
        parent.setAttribute("firstName",             firstName);
        parent.setAttribute("lastName",              lastName);
        parent.setAttribute("dateOfBirth",           "1965-11-07");
        parent.setAttribute("socialSecurityNumber",  "222334444");
        parent.setAttribute("citizenshipStatus",     "USCitizen");
        parent.setAttribute("plusBorrower",          "false");
        parent.setAttribute("ferpa",                 "true");

        addAddress(doc, parent, "Original", "02 WALNUT ROAD", "2nd Line Street", "ACWORTH", "NH", "03601");

        Element phone = doc.createElement("phone");
        parent.appendChild(phone);
        phone.setAttribute("type",   "Home");
        phone.setAttribute("number", "1234567890");

        Element email = doc.createElement("email");
        parent.appendChild(email);
        email.setAttribute("email", firstName + "." + lastName + "@mailinator.com");
        email.setAttribute("type",  "Home");
    }

    private static int[] courseLayout(SblOption option) {
        switch (option) {
            case PELL:    return new int[]{3, 2, 1};
            case NON_TERM: return new int[]{1, 1, 1};
            default:      return new int[]{4, 4, 4}; // DEFAULT
        }
    }

    private static Element courseData(Document doc, String termId,
                                       String startDate, String endDate,
                                       String units, boolean completed, String programId) {
        Element cd = doc.createElement("courseData");
        String uid = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        cd.setAttribute("courseId",               "CRS-" + uid);
        cd.setAttribute("courseName",             "Course " + uid);
        cd.setAttribute("externalCourseId",       "EXT-" + uid);
        cd.setAttribute("externalTermId",         termId);
        cd.setAttribute("courseStartDate",        startDate);
        cd.setAttribute("courseEndDate",          endDate);
        cd.setAttribute("enrolledDate",           startDate);
        cd.setAttribute("registeredUnits",        units);
        cd.setAttribute("lastAttendedDate",       completed ? endDate : currentTestDate());
        cd.setAttribute("completed",              String.valueOf(completed));
        cd.setAttribute("attendance",             completed ? "true" : "false");
        cd.setAttribute("countTowardProgression", "true");
        cd.setAttribute("programApplicable",      "true");
        cd.setAttribute("externalProgramId",      programId);
        if (completed) {
            cd.setAttribute("letterGrade",   "B");
            cd.setAttribute("qualityPoints", "24.0");
        }
        return cd;
    }
}
