// Module 15 Project, Track C: Clinic Flow Analyser
// Author: YOUR NAME

import java.time.LocalTime;

/** One patient visit. Triage 1 is the most urgent, 5 the least. */
public record Visit(String visitId, String patientId, String department, int triage,
                    LocalTime arrival, int waitMinutes, int age) {

    /** The target wait for each triage level, in minutes: 1 -> 0, 2 -> 10, 3 -> 60, 4 -> 120, 5 -> 240. */
    public int targetMinutes() {
        return 0;   // TODO (a switch expression)
    }

    public boolean seenOnTime() {
        return waitMinutes <= targetMinutes();
    }

    public static Visit fromCsv(String line) {
        String[] f = line.split(",");
        if (f.length != 7) {
            throw new IllegalArgumentException("expected 7 fields, found " + f.length);
        }
        return new Visit(f[0], f[1], f[2], Integer.parseInt(f[3]), LocalTime.parse(f[4]),
                Integer.parseInt(f[5]), Integer.parseInt(f[6]));
    }
}
