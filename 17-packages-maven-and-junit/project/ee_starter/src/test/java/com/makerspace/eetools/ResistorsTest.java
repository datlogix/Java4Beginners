package com.makerspace.eetools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ResistorsTest {

    @Test
    void seriesAddsUp() {
        assertEquals(650.0, Resistors.series(100, 220, 330), 1e-9);
    }

    @Test
    void negativeResistanceIsRefused() {
        assertThrows(IllegalArgumentException.class, () -> Resistors.series(100, -5));
    }

    // TODO: at least 20 tests altogether, covering every public method in every class
}
