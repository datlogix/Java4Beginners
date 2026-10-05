package com.makerspace.wardwatch.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class MeasurementTest {
    private static final LocalDateTime NOW = LocalDateTime.of(2026, 10, 4, 8, 0);

    @Test
    void normalHeartRate() {
        assertFalse(new HeartRate(NOW, 72).isAbnormal());
    }

    @Test
    void fastHeartRateIsAbnormal() {
        assertTrue(new HeartRate(NOW, 118).isAbnormal());
    }

    @Test
    void impossibleHeartRateIsRefused() {
        assertThrows(IllegalArgumentException.class, () -> new HeartRate(NOW, 400));
    }

    // TODO: ewsPoints for every band (a @ParameterizedTest!), the other measurements,
    //       PatientRecord.earlyWarningScore, Ward, and Location's recursion
}
