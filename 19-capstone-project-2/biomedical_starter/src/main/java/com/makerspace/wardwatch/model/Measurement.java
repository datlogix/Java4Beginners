package com.makerspace.wardwatch.model;

import java.time.LocalDateTime;

/**
 * One observation of one vital sign. Each kind validates its own values,
 * decides what counts as abnormal, and scores itself for the early-warning
 * score (the bands are in the capstone README).
 */
public abstract class Measurement {
    private final LocalDateTime time;

    protected Measurement(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("A measurement needs a time.");
        }
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    /** e.g. "Heart rate". */
    public abstract String name();

    /** The value as text, with its unit, e.g. "88 bpm" or "120/80 mmHg". */
    public abstract String valueText();

    public abstract boolean isAbnormal();

    /** Early-warning points, 0 to 3. */
    public abstract int ewsPoints();

    @Override
    public String toString() {
        return String.format("%s  %-14s %-12s%s", time, name(), valueText(), isAbnormal() ? "  ABNORMAL" : "");
    }
}
