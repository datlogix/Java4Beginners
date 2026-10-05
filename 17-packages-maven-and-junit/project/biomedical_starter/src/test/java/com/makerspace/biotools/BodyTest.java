package com.makerspace.biotools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BodyTest {

    @Test
    void bmiOfAnAverageAdult() {
        assertEquals(22.86, Body.bmi(70, 1.75), 0.01);
    }

    @Test
    void impossibleHeightIsRefused() {
        assertThrows(IllegalArgumentException.class, () -> Body.bmi(70, 17.5));
    }

    // TODO: at least 20 tests altogether, covering every public method in every class
}
