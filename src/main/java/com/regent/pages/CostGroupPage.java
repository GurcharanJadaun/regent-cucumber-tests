package com.regent.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.regent.locators.CostGroupLocators;

import java.util.HashMap;

/** Ported from regent.pages.CostGroupPage — Cost Group / Cost Items grid. */
public class CostGroupPage extends BasePage {

    private HashMap<String, Integer> costItemsGridColumns;

    public CostGroupPage(Page page) {
        super(page);
    }

    public CostGroupPage filterCostItemByName(String costItemName) {
        click(CostGroupLocators.NAME_FILTER);
        fill(CostGroupLocators.FILTER_INPUT, costItemName);
        click(CostGroupLocators.FILTER_BUTTON);
        return this;
    }

    public void clickViewDetails(String costItemName) {
        click(String.format(CostGroupLocators.VIEW_DETAILS_BUTTON, costItemName));
    }

    /** Builds a column-name -> 1-based position lookup from the grid header row, once, on first use. */
    private void createCostItemGridHeadingLookUp() {
        if (costItemsGridColumns != null) {
            return;
        }
        costItemsGridColumns = new HashMap<>();
        Locator headings = page.locator(CostGroupLocators.COST_ITEM_GRID_HEADER_COLUMNS);
        int totalCells = headings.count();
        for (int idx = 0; idx < totalCells; idx++) {
            String heading = headings.nth(idx).getAttribute("data-title");
            if (heading == null) {
                continue;
            }
            costItemsGridColumns.put(heading, idx + 1);
        }
    }

    private int getCostItemGridColumnPosition(String gridColumnName) {
        createCostItemGridHeadingLookUp();
        return costItemsGridColumns.get(gridColumnName);
    }

    private String getCellText(String rowName, String columnName) {
        int columnPosition = getCostItemGridColumnPosition(columnName);
        Locator cells = page.locator(String.format(CostGroupLocators.COST_ITEM_GRID_CELLS, rowName));
        return cells.nth(columnPosition - 1).textContent().trim();
    }

    public String getCostItemName(String rowName) {
        return getCellText(rowName, "Name");
    }

    public String getCostItemCostFrequency(String rowName) {
        return getCellText(rowName, "Cost Frequency");
    }

    public String getCostItemCost(String rowName) {
        return getCellText(rowName, "Cost");
    }

    public String getCostItemCostPer(String rowName) {
        return getCellText(rowName, "Cost Per");
    }

    public String getCostItemRateStart(String rowName) {
        return getCellText(rowName, "Rate Start");
    }

    public String getCostItemDistribution(String rowName) {
        return getCellText(rowName, "Distribution");
    }

    public String getCostItemRoundingMethod(String rowName) {
        return getCellText(rowName, "Rounding Method");
    }

    public String getCostItemRoundingLevel(String rowName) {
        return getCellText(rowName, "Rounding Level");
    }
}
