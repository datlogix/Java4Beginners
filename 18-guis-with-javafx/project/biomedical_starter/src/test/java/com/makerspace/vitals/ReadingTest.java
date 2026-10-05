package com.makerspace.vitals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

/** The model can be tested without a window. Add tests for validation and every boundary. */
class ReadingTest {

    @Test
    void normalReading() {
        assertTrue(new Reading(LocalTime.NOON, 72, 98, 36.8).allNormal());
    }

    @Test
    void feverIsNotNormal() {
        assertFalse(new Reading(LocalTime.NOON, 72, 98, 38.4).temperatureNormal());
    }
}
