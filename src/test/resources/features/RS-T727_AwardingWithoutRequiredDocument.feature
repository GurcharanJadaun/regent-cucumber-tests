@RS-T727
Feature: RS-T727 Awarding Without Required Document
  As a financial aid administrator
  I want to package a freshly created student
  So that the correct financial aid awards are applied even though required documents are not yet satisfied

  # Ported 1:1 from D:\Work\smoke-suite-automation\src\test\java\org\regent\seiCapellaSOW\SEIAwardUsingDocumentTest.java
  # #awardingWithoutRequiredDocument() (REGENT TC_ID RS-T171 / SEI TC_ID RS-T727) — the only scenario
  # tagged RS-T727 in that project. "Without required document" is implicit: the student's documents
  # are left at their default post-import status (never marked Satisfied), yet packaging still awards.
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
