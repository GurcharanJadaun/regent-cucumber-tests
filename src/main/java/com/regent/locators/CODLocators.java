package com.regent.locators;

/** Ported from regent.pages.student.CODPage. */
public interface CODLocators {
    String AWARD_RESPONSE_GRID_ROW = "//div[@data-rem-id='awardLevelCODResponsesGrid']//tr[./td[position()=2 and text()='%s']]";
    String AWARD_RESPONSE_GRID_ROW_CELLS = "//div[@data-rem-id='awardLevelCODResponsesGrid']//tr[./td[position()=2 and text()='%s']]/td";
    String AWARD_LEVEL_MASTER_GRID_ROW = "//div[@data-rem-id='awardLevelCODResponsesGrid']//tr[@class='k-master-row' and contains(string(),'%s')]";
    String AWARD_LEVEL_MASTER_GRID_ROW_CELLS = "//div[@data-rem-id='awardLevelCODResponsesGrid']//tr[@class='k-master-row' and contains(string(),'%s')]/td";

    String AWARD_RESPONSE_DETAILS_GRID_ROW_CELLS = "//div[@data-rem-id='awardLevelCODResponsesGrid']//tr[@class='k-detail-row']//tr[contains(string(),'%s')]/td";
    String STUDENT_RESPONSE_GRID_ROW = "//div[@data-rem-id='studentLevelCODResponsesGrid']//tr[contains(string(),'%s')]";
    String STUDENT_RESPONSE_GRID_ROW_CELLS = "//div[@data-rem-id='studentLevelCODResponsesGrid']//tr[contains(string(),'%s')]/td";
}
