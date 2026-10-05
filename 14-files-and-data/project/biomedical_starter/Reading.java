// Module 14 Project, Track C: Patient Vitals Log
// Author: YOUR NAME

import java.util.ArrayList;
import java.util.List;

/** One row of the vitals log. */
public record Reading(String patientId, String time, int heartRate, int spo2, double temperature) {

    /** Reasons this reading is abnormal: HR < 60 or > 100, SpO2 < 94, temperature >= 38.0. */
    public List<String> problems() {
        List<String> found = new ArrayList<>();
        // TODO
        return found;
    }

    /** Builds a Reading from one CSV line, or throws IllegalArgumentException. */
    public static Reading fromCsv(String line) {
        // TODO
        throw new IllegalArgumentException("fromCsv not written yet");
    }
}
