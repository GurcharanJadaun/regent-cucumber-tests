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

    public enum IsirType { ISIR_PELL, ISIR }

    private static final String OUTPUT_DIR = "target/files";

    // Templates for AY 2025-26 (year indicator 6 uses the _5 variant)
    private static final String TEMPLATE_PELL    = "YRP_ISIR_5.txt";
    private static final String TEMPLATE_DEFAULT = "isir_template_5.txt";

    public static String build(StudentUser student, String isirType, String federalSchoolCode) {
        IsirType type = IsirType.valueOf(isirType.toUpperCase());
        return build(student, type, federalSchoolCode);
    }

    public static String build(StudentUser student, IsirType type, String federalSchoolCode) {
        String templateName = (type == IsirType.ISIR_PELL) ? TEMPLATE_PELL : TEMPLATE_DEFAULT;
        boolean isPell = (type == IsirType.ISIR_PELL);
        return buildFromTemplate(student, templateName, isPell, federalSchoolCode);
    }

    private static String buildFromTemplate(StudentUser student,
                                             String templateName,
                                             boolean applyPellOverrides,
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

            // Pell-specific overrides (setIsirDataForPell equivalent)
            if (applyPellOverrides) {
                insert(buf, 187, 187, "B");   // saiformula = "B" (Simplified Independent)
                insert(buf, 4658, 4659, "  "); // clear nsdlPellSequenceNumber1
                insert(buf, 4663, 4668, "      "); // clear nsd1SAI1
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
