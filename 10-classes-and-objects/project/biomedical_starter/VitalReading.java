// Module 10 Project, Track C: Ward Monitor
// Author: YOUR NAME

import java.util.ArrayList;
import java.util.List;

/** One set of vital signs, taken at one time. */
public class VitalReading {
    private final String time;          // e.g. "08:00"
    private final int heartRate;        // beats per minute
    private final int spo2;             // blood oxygen saturation, %
    private final double temperature;   // degrees Celsius

    public VitalReading(String time, int heartRate, int spo2, double temperature) {
        // TODO: reject impossible values: heart rate outside 20-250, SpO2 outside 50-100,
        //       temperature outside 30.0-45.0
        this.time = time;
        this.heartRate = heartRate;
        this.spo2 = spo2;
        this.temperature = temperature;
    }

    public String getTime() { return time; }
    public int getHeartRate() { return heartRate; }
    public int getSpo2() { return spo2; }
    public double getTemperature() { return temperature; }

    /** Returns a list of reasons this reading is abnormal (empty if it's fine):
     *  heart rate below 60 or above 100, SpO2 below 94, temperature 38.0 or more. */
    public List<String> problems() {
        List<String> found = new ArrayList<>();
        // TODO
        return found;
    }

    @Override
    public String toString() {
        // TODO: e.g.  "08:00  HR 72  SpO2 98%  37.1 C"
        return time;
    }
}
