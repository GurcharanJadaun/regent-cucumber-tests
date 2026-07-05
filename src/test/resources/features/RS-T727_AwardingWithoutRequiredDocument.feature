@RS-T727
Feature: RS-T727 Awarding Without Required Document
  As a financial aid administrator
  I want to package a freshly created student
  So that the correct financial aid awards are applied even though required documents are not yet satisfied

  # Ported 1:1 from D:\Work\smoke-suite-automation\src\test\java\org\regent\seiCapellaSOW\SEIAwardUsingDocumentTest.java
  # — every scenario below corresponds to one @Test method in that class.
  # #awardingWithoutRequiredDocument() (REGENT TC_ID RS-T171 / SEI TC_ID RS-T727) is the only scenario
  # tagged RS-T727 in that project; "Without required document" is implicit there since the student's
  # documents are left at their default post-import status (never marked Satisfied), yet packaging awards.
  #
  # Converted to year-7 (FAY 2026-27) — see docs/FAY2026-27-ISIR-Investigation.md for the full
  # investigation record. Enrollment stays on the existing Spring 2026 (1263) term unchanged; only
  # the ISIR type changed (ISIR_PELL -> ISIR_PELL_YEAR7, ISIR_PELL_DEPENDENT -> ISIR_PELL_DEPENDENT_YEAR7).
  #
  # IMPORTANT — this suite now depends on a live, shared, NOT version-controlled REMQA47 setting:
  #   Institution -> General -> Default Settings -> FAY Crossover Awarding = "Award ONLY for next Year"
  # Without it, packaging never considers FAY 2026-27 data for a year-7-only ISIR and every award
  # assertion below fails with zero awards. This also means a year-6 ISIR (Prior Year FAY 2025-26)
  # will NO LONGER package here while this setting is in effect — the two are mutually exclusive
  # under the current REMQA47 config (a third option, "Exhaust current year", may resolve this but
  # was not yet confirmed as of this conversion). Also required: a Federal Award Year 2026-2027 row
  # in the "PellCOA Default 2" Pell COA Setup record (else Pell computes to $0 regardless of ISIR
  # data), and that change must be propagated via Administration -> Capella University -> "Reload
  # RNA Setup" (the generic "Refresh Cache Data" process is broken on REMQA47 and will not work).
  #
  # date.to.set (config.properties, currently 06/15/2026) is unaffected by any of the above — it
  # only sets lastAttendedDate on the in-progress course and just needs to stay >= Spring 2026's
  # 04/13/2026 start date, which it already does.

  Background:
    Given I am logged in as admin
    And the enterprise is configured as:
      | campusId          | CAP01            |
      | siteId            | CU               |
      | programId         | BSPBH-NONE-BSPBH |
      | federalSchoolCode | 032673           |
      | termBeforeId      | 1257             |
      | termPreviousId    | 1261             |
      | termActualId      | 1263             |

  @happy-path @Scenario1
  Scenario: Awarding without required document
    Given a new student is generated
    When I import the student via SBL with option "PELL" and term "SEMESTER"
    And I wait for the SBL import to complete
    When I import the ISIR of type "ISIR_PELL_YEAR7"
    And I wait for the ISIR import to complete
    When I navigate to the student's Academic Plan
    And I run basic packaging
    Then the following awards should be present:
      | Pell Grant               |
      | Direct Subsidized Loan   |
      | Direct Unsubsidized Loan |

  # REGENT TC_ID RS-T172, SEI TC_ID RS-T728
  @RS-T728
  Scenario: Awarding with document marked satisfied (Student scope)
    Given a new student is generated
    When I import the student via SBL with option "PELL" and term "SEMESTER"
    And I wait for the SBL import to complete
    When I import the ISIR of type "ISIR_PELL_YEAR7"
    And I wait for the ISIR import to complete
    When I navigate to the student's Academic Plan
    And I run basic packaging
    When I return to the student's Documents
    And I add a required document "FA Award & Disbursement Hold"
    And I mark the document "FA Award & Disbursement Hold" as "Satisfied"
    When I return to the student's Academic Plan
    And I run basic packaging
    Then the following awards should be present:
      | Pell Grant               |
      | Direct Subsidized Loan   |
      | Direct Unsubsidized Loan |

  # REGENT TC_ID RS-T173, SEI TC_ID RS-T729
  @RS-T729
  Scenario: Awarding with document marked as Needed (Student scope)
    Given a new student is generated
    When I import the student via SBL with option "PELL" and term "SEMESTER"
    And I wait for the SBL import to complete
    When I import the ISIR of type "ISIR_PELL_YEAR7"
    And I wait for the ISIR import to complete
    When I navigate to the student's Documents
    And I add a required document "FA Award & Disbursement Hold"
    When I return to the student's Academic Plan
    And I run basic packaging
    Then the following awards should NOT be present:
      | Pell Grant               |
      | Direct Subsidized Loan   |
      | Direct Unsubsidized Loan |

  # REGENT TC_ID RS-T174, SEI TC_ID RS-T730
  #
  # POTENTIAL BUG — this scenario currently fails and is believed to reflect a real product/
  # environment issue, not a test defect. Full details in docs/FAY2026-27-ISIR-Investigation.md.
  #
  # Failure: after the document reverts to Unsatisfied and packaging is re-run, all three awards
  # (Pell Grant, DL Subsidized, DL Unsubsidized) remain visible and unchanged — Pell Grant even
  # shows status ACCEPTED. isAwardAbsentEventually() polls with repeated refreshes for up to 90s
  # and never sees them disappear.
  #
  # Root cause (confirmed live on REMQA47): the "FA Award & Disbursement Hold" document is
  # configured with Doc Restricts = "Restrict Packaging, Restrict Positive Disbursements". This
  # appears to block the packaging engine from running/modifying awards while the hold is active —
  # it does NOT retroactively cancel awards already created before the hold went active. Re-running
  # "Basic Packaging" with the hold active is effectively a no-op for this student, so the awards
  # from the last successful (pre-hold) packaging run simply persist. The Awards tab exposes a
  # manual "Perform R2T4" action, suggesting retroactive award adjustment requires an explicit
  # action distinct from routine packaging — one this scenario (and the app's own document-hold
  # feature) never triggers.
  #
  # Ruled out:
  #   - Not a year-7 conversion regression — this failure predates the year-7 work in this file.
  #   - Not a missing step in this Cucumber port — compared verbatim against the original Selenium
  #     test (smoke-suite-automation's studentScopeRevertToNonSatisfiedAfterAwardedOfFund()): both
  #     use the identical mark-Unsatisfied -> repackage -> wait 10s -> refresh -> assert sequence.
  #     This port's isAwardAbsentEventually() actually retries far longer (90s of polling) than the
  #     original's single immediate check, so extra waiting is not the fix either.
  #
  # Open question for product/business sign-off: is "Restrict Packaging" ever expected to
  # retroactively revoke prior awards, or does reverting this document correctly require an
  # explicit cancellation/R2T4 step that this scenario is missing by design?
  @RS-T730
  Scenario: Document reverts to a non-Satisfied status after award of fund
    Given a new student is generated
    When I import the student via SBL with option "PELL" and term "SEMESTER"
    And I wait for the SBL import to complete
    When I import the ISIR of type "ISIR_PELL_YEAR7"
    And I wait for the ISIR import to complete
    When I navigate to the student's Documents
    And I add a required document "FA Award & Disbursement Hold"
    And I mark the document "FA Award & Disbursement Hold" as "Satisfied"
    When I return to the student's Academic Plan
    And I run basic packaging
    Then the following awards should be present:
      | Pell Grant               |
      | Direct Subsidized Loan   |
      | Direct Unsubsidized Loan |
    When I return to the student's Documents
    And I mark the document "FA Award & Disbursement Hold" as "Unsatisfied"
    When I return to the student's Academic Plan
    And I run basic packaging
    And I refresh the Academic Plan
    Then the following awards should NOT be present:
      | Pell Grant               |
      | Direct Subsidized Loan   |
      | Direct Unsubsidized Loan |

  # REGENT TC_ID RS-T181, SEI TC_ID RS-T737
  @RS-T737
  Scenario: Awarding with document marked Incomplete (Student scope)
    Given a new student is generated
    When I import the student via SBL with option "PELL" and term "SEMESTER"
    And I wait for the SBL import to complete
    When I import the ISIR of type "ISIR_PELL_YEAR7"
    And I wait for the ISIR import to complete
    When I navigate to the student's Documents
    And I add a required document "FA Award & Disbursement Hold"
    And I mark the document "FA Award & Disbursement Hold" as "Incomplete"
    When I return to the student's Academic Plan
    And I run basic packaging
    Then the following awards should NOT be present:
      | Pell Grant               |
      | Direct Subsidized Loan   |
      | Direct Unsubsidized Loan |

  # REGENT TC_ID RS-T179, SEI TC_ID RS-T735
  @RS-T735
  Scenario: Fund Awarded Manually
    Given a new student is generated
    When I import the student via SBL with option "PELL" and term "SEMESTER"
    And I wait for the SBL import to complete
    When I import the ISIR of type "ISIR_PELL_DEPENDENT_YEAR7"
    And I wait for the ISIR import to complete
    When I navigate to the student's Documents
    And I add a required document "FA Award & Disbursement Hold"
    And I mark the document "FA Award & Disbursement Hold" as "Satisfied"
    When I return to the student's Academic Plan
    And I run basic packaging
    Then the following awards should be present:
      | Pell Grant               |
      | Direct Subsidized Loan   |
      | Direct Unsubsidized Loan |
    When I navigate to the student's Awards
    And I add a manual award for the Parent PLUS fund
    When I return to the student's Academic Plan
    And I run basic packaging
    And I reveal hidden awards
    Then the following awards should be present:
      | Parent PLUS |
