package com.makerspace.wardwatch.analysis;

import com.makerspace.wardwatch.model.Ward;
import java.util.Map;

/** Numbers about the ward. No printing here. */
public class Statistics {

    /** How many patients have each early-warning score, in order. */
    public static Map<Integer, Long> scoreCounts(Ward ward) {
        return Map.of();   // TODO
    }

    // TODO: at least four more statistics (average heart rate on the ward, abnormal
    //       readings per vital sign, the patient with the most abnormal readings...)
}
