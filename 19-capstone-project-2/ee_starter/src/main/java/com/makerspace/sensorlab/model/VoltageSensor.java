package com.makerspace.sensorlab.model;

/** Measures a voltage rail directly: the raw value is already in volts (0-30 V). */
public class VoltageSensor extends Sensor {

    public VoltageSensor(String id, String label, double lowLimit, double highLimit) {
        super(id, label, lowLimit, highLimit);
    }

    @Override
    public String unit() {
        return "V";
    }

    @Override
    protected double convert(double raw) {
        if (raw < 0 || raw > 30) {
            throw new IllegalArgumentException("A voltage sensor reads 0-30 V, not " + raw);
        }
        return raw;
    }
}
