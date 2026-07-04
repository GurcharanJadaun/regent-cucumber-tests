package com.regent.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Builds an ISIR file from a fixed-width template for a given student and ISIR type.
 *
 * ISIR type → template (mirrors original IsirBuilder constants + runtime branch):
 *   ISIR_PELL : YRP_ISIR.txt → at runtime for year indicator 5/6 → YRP_ISIR_5.txt
 *   ISIR      : isir_template.txt → at runtime for year indicator 5/6 → isir_template_5.txt
 *
 * Since RS-T727 always runs against AY 2025-26 (year indicator 6), both resolve to their
 * _5.txt variant. We load those directly.
 *
 * 2025-format positions (setIsirPositionDataFor2025) — 1-indexed, converted to 0-indexed:
 *   year indicator : pos   1      → idx   0      (len  1)
 *   first name     : pos 243-277  → idx 242-276  (len 35)
 *   middle name    : pos 278-292  → idx 277-291  (len 15)
 *   last name      : pos 293-327  → idx 292-326  (len 35)
 *   DOB            : pos 338-345  → idx 337-344  (len  8, YYYYMMDD)
 *   SSN            : pos 346-354  → idx 345-353  (len  9)
 *   school code    : pos 875-880  → idx 874-879  (len  6)
 *   saiformula     : pos 188      → idx 187      (len  1, "B" for Pell)
 *   nsd1SAI1       : pos 4664-4669→ idx 4663-4668(len  6, cleared for Pell)
 *   nsdlPellSeq1   : pos 4659-4660→ idx 4658-4659(len  2, cleared for Pell)
 *
 * Federal school code source: EnterpriseBuilder.getEnterprise().getFederalSchoolCode()
 * in the original; here read from enterprise.federal.school.code in config.properties.
 *
 * Output path: target/files/<lastname>_isir.txt  (matches original target.path default)
 */
public class IsirFileBuilder {

    public enum IsirType { ISIR_PELL, ISIR, ISIR_PELL_DEPENDENT }

    private static final String OUTPUT_DIR = "target/files";

    // Templates for AY 2025-26 (year indicator 6 uses the _5 variant)
    private static final String TEMPLATE_PELL    = "YRP_ISIR_5.txt";
    private static final String TEMPLATE_DEFAULT = "isir_template_5.txt";

    public static String build(StudentUser student, String isirType, String federalSchoolCode) {
        IsirType type = IsirType.valueOf(isirType.toUpperCase());
        return build(student, type, federalSchoolCode);
    }

    public static String build(StudentUser student, IsirType type, String federalSchoolCode) {
        boolean isPell = (type == IsirType.ISIR_PELL || type == IsirType.ISIR_PELL_DEPENDENT);
        String templateName = isPell ? TEMPLATE_PELL : TEMPLATE_DEFAULT;
        boolean isDependent = (type == IsirType.ISIR_PELL_DEPENDENT);
        return buildFromTemplate(student, templateName, isPell, isDependent, federalSchoolCode);
    }

    private static String buildFromTemplate(StudentUser student,
                                             String templateName,
                                             boolean applyPellOverrides,
                                             boolean isDependent,
                                             String federalSchoolCode) {
        try {
            Files.createDirectories(Paths.get(OUTPUT_DIR));

            String template;
            try (InputStream is = IsirFileBuilder.class.getClassLoader()
                    .getResourceAsStream(templateName)) {
                if (is == null) throw new RuntimeException("Template not found on classpath: " + templateName);
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, StandardCharsets.ISO_8859_1))) {
                    template = br.readLine();
                }
            }
            if (template == null || template.isEmpty())
                throw new RuntimeException("Template is empty: " + templateName);

            StringBuilder buf = new StringBuilder(template);

            // Year indicator → "6" for AY 2025-26
            insert(buf, 0, 0, "6");

            // Student identity fields (2025 positions)
            insert(buf, 242, 276, padRight(student.getFirstName(),  35));
            insert(buf, 277, 291, padRight(student.getMiddleName(), 15));
            insert(buf, 292, 326, padRight(student.getLastName(),   35));
            insert(buf, 337, 344, student.getDobIsirFormat());   // YYYYMMDD
            insert(buf, 345, 353, student.getSsn());             // 9 digits

            // Federal school code (from enterprise config in feature file)
            insert(buf, 874, 879, padRight(federalSchoolCode, 6));

            // Dependency status (setdependencyStatus("D") equivalent) — "D" (Dependent) is
            // required for a Parent PLUS loan to be eligible for the student at all.
            if (isDependent) {
                insert(buf, 111, 111, "D");
            }

            // Pell-specific overrides (setIsirDataForPell equivalent).
            // The template's baked-in NSLDS Pell payment history (3 blocks) represents prior
            // Pell disbursements at another school; leaving it in place makes the packaging
            // engine treat this as an award-year-limit conflict and award $0. All three
            // blocks — plus the standalone EFC field — must be blanked, not just block 1.
            if (applyPellOverrides) {
                insert(buf, 187, 187, "B");     // saiformula = "B" (Simplified Independent)
                insert(buf, 3366, 3371, "");    // nsdlEFC1

                // NSLDS Pell payment history block 1
                insert(buf, 4658, 4659, "");    // nsdlPellSequenceNumber1
                insert(buf, 4660, 4662, "");    // nsdlPellVerificationFlag1
                insert(buf, 4663, 4668, "");    // nsd1SAI1
                insert(buf, 4669, 4676, "");    // nsdlPellSchoolCode1
                insert(buf, 4677, 4678, "");    // nsdlPellTransactionNumber1
                insert(buf, 4679, 4686, "");    // nsdlPellLastUpdateDate1
                insert(buf, 4687, 4692, "");    // nsdlPellScheduledAmount1
                insert(buf, 4693, 4698, "");    // nsdlPellAmountPaidToDate1
                insert(buf, 4699, 4705, "");    // nsdlPellPercentScheduledAwardUsedByAwardYear1
                insert(buf, 4706, 4711, "");    // nsdlPellAwardAmount1

                // NSLDS Pell payment history block 2
                insert(buf, 4733, 4734, "");    // nsdlPellSequenceNumber2
                insert(buf, 4735, 4737, "");    // nsdlPellVerificationFlag2
                insert(buf, 4744, 4751, "");    // nsdlPellSchoolCode2
                insert(buf, 4752, 4753, "");    // nsdlPellTransactionNumber2
                insert(buf, 4754, 4761, "");    // nsdlPellLastUpdateDate2
                insert(buf, 4762, 4767, "");    // nsdlPellScheduledAmount2
                insert(buf, 4768, 4773, "");    // nsdlPellAmountPaidToDate2
                insert(buf, 4774, 4780, "");    // nsdlPellPercentScheduledAwardUsedByAwardYear2
                insert(buf, 4781, 4786, "");    // nsdlPellAwardAmount2

                // NSLDS Pell payment history block 3
                insert(buf, 4808, 4809, "");    // nsdlPellSequenceNumber3
                insert(buf, 4810, 4812, "");    // nsdlPellVerificationFlag3
                insert(buf, 4819, 4826, "");    // nsdlPellSchoolCode3
                insert(buf, 4827, 4828, "");    // nsdlPellTransactionNumber3
                insert(buf, 4829, 4836, "");    // nsdlPellLastUpdateDate3
                insert(buf, 4837, 4842, "");    // nsdlPellScheduledAmount3
                insert(buf, 4843, 4848, "");    // nsdlPellAmountPaidToDate3
                insert(buf, 4849, 4855, "");    // nsdlPellPercentScheduledAwardUsedByAwardYear3
                insert(buf, 4856, 4861, "");    // nsdlPellAwardAmount3
            }

            String filePath = OUTPUT_DIR + "/" + student.getIsirFileName();
            try (FileWriter fw = new FileWriter(filePath, StandardCharsets.ISO_8859_1)) {
                fw.write(buf.toString());
            }
            return filePath;

        } catch (Exception e) {
            throw new RuntimeException("Failed to build ISIR for " + student.getLastName(), e);
        }
    }

    private static void insert(StringBuilder buf, int start, int end, String value) {
        int len = end - start + 1;
        String padded = padRight(value, len).substring(0, len);
        buf.replace(start, end + 1, padded);
    }

    private static String padRight(String s, int width) {
        if (s == null) s = "";
        if (s.length() >= width) return s.substring(0, width);
        return s + " ".repeat(width - s.length());
    }
}
