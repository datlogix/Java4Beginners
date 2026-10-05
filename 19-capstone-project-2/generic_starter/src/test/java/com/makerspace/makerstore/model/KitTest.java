package com.makerspace.makerstore.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class KitTest {

    @Test
    void componentValueIsPriceTimesQuantity() {
        Component leds = new Component("C01", "LED pack", "Components", 15.5, 4, 2);
        assertEquals(62.0, leds.value(), 1e-9);
    }

    @Test
    void itemsNeedAnId() {
        assertThrows(IllegalArgumentException.class, () -> new Tool("", "Drill", "Tools", 300, "good"));
    }

    // TODO: tests for Kit.value() and Kit.allParts() with kits inside kits (they fail until
    //       you write the recursion), and for everything else in the model
}
