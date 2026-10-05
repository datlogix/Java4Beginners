package com.makerspace.sensorlab.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SensorTest {

    @Test
    void voltageSensorUsesTheRawValueAndTheOffset() {
        VoltageSensor v = new VoltageSensor("V5", "5 V rail", 4.75, 5.25);
        v.setCalibrationOffset(-0.02);
        assertEquals(4.98, v.valueOf(5.0), 1e-9);
    }

    @Test
    void outOfLimitsIsAnAlarm() {
        assertTrue(new VoltageSensor("V5", "5 V rail", 4.75, 5.25).isAlarm(5.4));
    }

    @Test
    void limitsMustBeInOrder() {
        assertThrows(IllegalArgumentException.class, () -> new VoltageSensor("V", "x", 5, 4));
    }

    // TODO: TemperatureSensor (fails until you write convert), CurrentSensor, Lab...
}
