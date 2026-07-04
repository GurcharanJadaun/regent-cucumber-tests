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
  # Root-cause notes (REMQA47):
  #   Server OS clock is ~107 days ahead (~October 2026).
  #   FAY 2025-26 Direct Loan period ends June 30 2026 — already expired from server's view.
  #   Fix: config.properties date.to.set=06/15/2026 (before June 17 AY cutoff, before June 30 loan period end).
  #   WARNING: never combine server.date=true with date.to.set.

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

  @happy-path
  Scenario: Awarding without required document
    Given a new student is generated
    When I import the student via SBL with option "PELL" and term "SEMESTER"
    And I wait for the SBL import to complete
    When I import the ISIR of type "ISIR_PELL"
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
    When I import the ISIR of type "ISIR_PELL"
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
    When I import the ISIR of type "ISIR_PELL"
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
  @RS-T730
  Scenario: Document reverts to a non-Satisfied status after award of fund
    Given a new student is generated
    When I import the student via SBL with option "PELL" and term "SEMESTER"
    And I wait for the SBL import to complete
    When I import the ISIR of type "ISIR_PELL"
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
    When I import the ISIR of type "ISIR_PELL"
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
    When I import the ISIR of type "ISIR_PELL_DEPENDENT"
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
