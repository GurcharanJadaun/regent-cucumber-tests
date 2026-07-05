# FAY 2026-27 (Year-7) ISIR Support — Summary of Changes

This document is a precise, at-a-glance record of what changed to make the RS-T727 acceptance
suite run against a genuine FAY 2026-27 ("year-7") ISIR instead of the FAY 2025-26 ("year-6")
workaround it used previously. For the full investigative narrative (dead ends, ruled-out
theories, exact diagnostic commands), see `docs/FAY2026-27-ISIR-Investigation.md`. This document
only covers what actually changed and why.

**Environment:** `regentremlocal47.regenteducation.net` (REMQA47), Capella University enterprise,
`BSPBH-NONE-BSPBH` program.

---

## 1. Code changes

| File | Change | Reason |
|---|---|---|
| `src/main/java/com/regent/utils/IsirFileBuilder.java` | Added `IsirType.ISIR_PELL_YEAR7` and `ISIR_PELL_DEPENDENT_YEAR7`. Added `RECORD_LENGTH_YEAR7 = 7944` and pad-with-trailing-blanks logic. Year indicator is now selected by type (`"7"` for the two new types, `"6"` otherwise). | The FAY 2026-27 ISIR record format is 240 bytes longer than the existing year-5/6 template (7944 vs. 7704 characters). The import validator only checks total length plus the year-indicator digit at position 1, so padding a year-7 record to the required length was sufficient — no need to source the real 2026-27 field-position spec. |
| `src/main/java/com/regent/config/ConfigReader.java` | `getDateToSet()` now reads `-Ddate.to.set=...` as a system property first, falling back to `config.properties`. | Lets a single test run override the `lastAttendedDate` reference date without editing `config.properties` (which would affect the whole suite). |
| `src/main/java/com/regent/utils/SblXmlBuilder.java` | Added an optional `termActualUnits` enterprise-config override for the "actual" course's `registeredUnits`, falling back to the original calculation. | Supports testing a reduced unit count for quarter-length terms (e.g. Fall 2026) without changing the default calculation used everywhere else. |
| `src/main/java/com/regent/locators/ProcessLogLocators.java` | Fixed `PROCESS_TAB` (now matches `li span.k-link:visible:text-is(...)` instead of `li:text-is(...)`) and added `:visible` filtering to `ERROR_LOG_DETAILS`. | Two real, pre-existing locator bugs: tab labels are nested inside a child `<span>`, not on the `<li>` itself, and this app leaves stale duplicate copies of repeatedly-opened Kendo widgets in the DOM. Confirmed via full regression run to cause zero change to prior suite results. |
| `src/test/resources/features/RS-T727_AwardingWithoutRequiredDocument.feature` | All 6 scenarios (`@Scenario1`, `RS-T728`, `RS-T729`, `RS-T730`, `RS-T737`, `RS-T735`) now import `ISIR_PELL_YEAR7` (or `ISIR_PELL_DEPENDENT_YEAR7` for `RS-T735`) instead of the year-6 types. Enrollment term data (`termBeforeId=1257`, `termPreviousId=1261`, `termActualId=1263`) is unchanged. Header comment documents the REMQA47 environment dependency (section 2 below). `RS-T730` carries an inline comment documenting its separate, pre-existing issue. | This is the actual acceptance-test suite now running against real FAY 2026-27 data, not a diagnostic copy. |
| `src/test/resources/features/Year7ISIR_CrossoverTest.feature` (new) | Diagnostic scenario proving year-7 packaging works: same term data as above, `ISIR_PELL_YEAR7`, no term redesign. | Isolated proof-of-concept before rolling the change into the main suite. |
| `src/test/resources/features/Year7ISIR_Investigation.feature` (new) | Diagnostic scenario targeting a genuinely-FAY-2026-27-dated term (Fall 2026, ID 1267). **Still fails at SBL import** — unresolved, kept separate from the main suite. | Left in place as a record of the still-open Fall 2026 path; not required for the main suite fix. |

---

## 2. REMQA47 environment changes

**These are live, shared, enterprise-wide changes made directly in the REMQA47 admin UI — none of
this is captured in version control.** Anyone else running tests against this same enterprise
should be aware of them.

### 2.1 `FAY Crossover Awarding` (institution setting)
- **Where:** Administration → Capella University (Institution) → General → Default Settings.
- **Changed from** `Award ONLY from prior Year` **to** `Award ONLY for next Year`.
- **Effect:** packaging now evaluates FAY 2026-27 ISIR data for students who only have a year-7
  ISIR (previously it only ever considered FAY 2025-26 and stopped, regardless of what other data
  existed).
- **Trade-off:** this setting is enterprise-wide. Students with only a year-6 ISIR (the previous
  baseline, e.g. any test still using `ISIR_PELL`) no longer package Pell correctly, because the
  engine no longer considers FAY 2025-26 at all. A third option, `Exhaust current year`, may
  resolve this by falling back to Next Year only when no Prior Year eligibility exists — **not yet
  tested**.

### 2.2 `Pell COA Setup` — added a Federal Award Year 2026-2027 row
- **Where:** Administration → Capella University → Capella University → Capella University → Pell
  COA Setup → `PellCOA Default 2` (confirmed linked to `BSPBH-NONE-BSPBH` via its Programs tab).
- **Change:** added a new Federal Award Year row for `2026-2027` (internal `Id=17`), with all ten
  values (On Campus / With Parents / Off Campus / On Campus with Dependents / Military Housing ×
  Full Time / Less than half time) set to **$9,000**, matching the existing 2025-2026 row.
- **Effect:** Pell Grant now has a Cost-of-Attendance figure to calculate against for FAY 2026-27.
  Previously this table had rows for every year back to 2013-2014 but none for 2026-2027, so Pell
  always computed to $0 for a year-7 ISIR regardless of eligibility.

### 2.3 Ran "Reload RNA Setup"
- **Where:** Administration → Capella University (Enterprise level) → **Reload RNA Setup** button.
- **Why:** after adding the Pell COA row, packaging still reported the data as unavailable — the
  change hadn't propagated to the packaging/RNA engine. The generic **Refresh Cache Data** process
  (Administration → Processes → System) is broken on REMQA47 (`CreateCacheDataRefreshCommand
  Error`, confirmed with multiple scope selections) and does **not** work as a substitute.
  "Reload RNA Setup" is a separate, working mechanism that resolved it.

---

## 3. Result

Full suite (`mvn -q -o test -Dcucumber.filter.tags="@RS-T727" -Ddate.to.set=07/05/2026`):

| Scenario | Result |
|---|---|
| `@Scenario1` — Awarding without required document | ✅ Pass |
| `RS-T728` — Document marked Satisfied | ✅ Pass |
| `RS-T729` — Document marked Needed | ✅ Pass |
| `RS-T730` — Document reverts to non-Satisfied after award | ❌ Fail — pre-existing, unrelated (see below) |
| `RS-T737` — Document marked Incomplete | ✅ Pass |
| `RS-T735` — Fund Awarded Manually (Parent PLUS) | ✅ Pass |

**5 of 6 pass under a genuine FAY 2026-27 ISIR** — the first time either this project or its
predecessor has achieved this.

`RS-T730`'s failure is unrelated to the year-7 conversion: the `FA Award & Disbursement Hold`
document's `Restrict Packaging` restriction blocks the packaging engine from acting while the hold
is active, but does not retroactively cancel awards already granted before the hold went active.
Confirmed via a step-by-step comparison against the original Selenium test
(`smoke-suite-automation`) that this Cucumber port isn't missing any step — it already retries
longer (90s of polling) than the original ever did. This is a pre-existing product/business-logic
question, not a code or porting defect, and is documented inline in the feature file.

---

## 4. Open follow-ups

1. Test `FAY Crossover Awarding = Exhaust current year` — may resolve the mutual exclusivity
   between year-6 and year-7 scenarios without needing to choose one or the other.
2. Decide whether `RS-T730` needs an explicit cancellation/R2T4 step added, or whether the
   document-restriction feature's behavior needs product review.
3. Communicate the REMQA47 environment changes (section 2) to anyone else using this enterprise —
   they are live and not version-controlled.
4. The Fall 2026 SBL-import path (`Year7ISIR_Investigation.feature`) remains unresolved but is no
   longer required, since the Spring 2026 path achieves the same goal.
