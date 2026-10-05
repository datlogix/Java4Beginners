package com.makerspace.wardwatch.model;

import java.time.LocalDateTime;

public class HeartRate extends Measurement {
    private final int bpm;

    public HeartRate(LocalDateTime time, int bpm) {
        super(time);
        if (bpm < 20 || bpm > 250) {
            throw new IllegalArgumentException("A heart rate of " + bpm + " isn't possible.");
        }
        this.bpm = bpm;
    }

    public int getBpm() { return bpm; }

    @Override public String name() { return "Heart rate"; }
    @Override public String valueText() { return bpm + " bpm"; }
    @Override public boolean isAbnormal() { return bpm < 60 || bpm > 100; }

    @Override
    public int ewsPoints() {
        return 0;   // TODO: the bands in the capstone README
    }
}
