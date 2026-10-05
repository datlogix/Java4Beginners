package com.makerspace.sensorlab.analysis;

import com.makerspace.sensorlab.model.Reading;
import java.util.List;

/** Calculations over readings. No printing here. */
public class Statistics {

    public static double mean(List<Reading> readings) {
        return 0;   // TODO (a stream; 0 for no readings)
    }

    /** The moving average: element i is the mean of readings i-window+1 to i. */
    public static List<Double> movingAverage(List<Reading> readings, int window) {
        return List.of();   // TODO
    }

    /** RECURSIVE: the length of the longest run of consecutive alarm readings,
     *  starting from index i, given the length of the current run so far. */
    public static int longestAlarmRun(List<Reading> readings, int i, int current) {
        return 0;   // TODO
    }

    // TODO: min, max, standard deviation, power and energy for a voltage/current pair...
}
