package com.regent.locators;

/** Ported from regent.pages.student.StatePage. */
public interface StateLocators {
    // Row keyed by Federal Award Year (col 1) and Fund (col 5)
    String STATE_AWARDS_GRID_ROW_CELLS = "//div[@data-rem-id='stateAwardsGrid']//table[@class='k-selectable']//tr[(./td[position()=1 and text()='%s']) and (./td[position()=5 and text()='%s'])]/td";
}
