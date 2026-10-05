// Module 18 Project, Track C: Vitals Dashboard
// Author: YOUR NAME
// Thresholds are simplified for teaching. This is not a clinical tool.
package com.makerspace.vitals;

import java.time.LocalTime;

/** One set of vital signs. (Your Module 13 validation belongs here.) */
public record Reading(LocalTime time, int heartRate, int spo2, double temperature) {

    public Reading {
        // TODO: reject impossible values
    }

    public boolean heartRateNormal() { return heartRate >= 60 && heartRate <= 100; }
    public boolean spo2Normal() { return spo2 >= 94; }
    public boolean temperatureNormal() { return temperature < 38.0 && temperature >= 35.0; }

    public boolean allNormal() {
        return heartRateNormal() && spo2Normal() && temperatureNormal();
    }
}
