package com.makerspace.makerstore.analysis;

import com.makerspace.makerstore.model.Inventory;
import java.util.Map;

/** Numbers about the inventory, worked out with streams (Module 15). No printing here. */
public class Statistics {

    /** The total value of everything, in GHS. */
    public static double totalValue(Inventory inventory) {
        return 0;   // TODO
    }

    /** The value of each category, in alphabetical order. */
    public static Map<String, Double> valueByCategory(Inventory inventory) {
        return Map.of();   // TODO
    }

    // TODO: at least three more statistics (most borrowed tools, loans per week, ...)
}
