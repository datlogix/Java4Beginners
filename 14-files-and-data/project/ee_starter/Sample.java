// Module 14 Project, Track B: Bench Data Logger Analyser
// Author: YOUR NAME

/** One logged sample: time (s), voltage (V), current (A). */
public record Sample(double timeS, double volts, double amps) {

    public double watts() {
        return volts * amps;
    }

    /** Builds a Sample from one CSV line, or throws IllegalArgumentException. */
    public static Sample fromCsv(String line) {
        // TODO
        throw new IllegalArgumentException("fromCsv not written yet");
    }

    public String toCsv() {
        // TODO: time,voltage,current,power (power to 3 decimal places)
        return "";
    }
}
