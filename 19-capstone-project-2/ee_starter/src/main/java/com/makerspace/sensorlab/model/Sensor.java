package com.makerspace.sensorlab.model;

import java.util.Objects;

/**
 * A sensor on the bench. Every sensor turns a RAW value from the logger into a
 * real value in its own unit, its own way (that's the polymorphism), then adds
 * a calibration offset. A value outside the alarm limits raises an alarm.
 */
public abstract class Sensor {
    private final String id;
    private final String label;
    private final double lowLimit;
    private final double highLimit;
    private double calibrationOffset;

    protected Sensor(String id, String label, double lowLimit, double highLimit) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("A sensor needs an ID.");
        }
        if (lowLimit >= highLimit) {
            throw new IllegalArgumentException("The low limit must be below the high limit.");
        }
        this.id = id;
        this.label = label;
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
    }

    public String getId() { return id; }
    public String getLabel() { return label; }
    public double getLowLimit() { return lowLimit; }
    public double getHighLimit() { return highLimit; }
    public double getCalibrationOffset() { return calibrationOffset; }
    public void setCalibrationOffset(double offset) { this.calibrationOffset = offset; }

    /** The unit of the real value, e.g. "V". */
    public abstract String unit();

    /** Turns a raw logger value into a real value (BEFORE calibration). Throws
     *  IllegalArgumentException for a raw value this kind of sensor can't produce. */
    protected abstract double convert(double raw);

    /** The calibrated real value for a raw reading. */
    public final double valueOf(double raw) {
        return convert(raw) + calibrationOffset;
    }

    public boolean isAlarm(double value) {
        return value < lowLimit || value > highLimit;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Sensor s && id.equals(s.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%-4s %-22s limits %.2f to %.2f %s", id, label, lowLimit, highLimit, unit());
    }
}
