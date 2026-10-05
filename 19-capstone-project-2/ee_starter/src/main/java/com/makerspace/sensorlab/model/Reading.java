package com.makerspace.sensorlab.model;

import java.time.LocalDateTime;

/** One calibrated reading from one sensor. */
public record Reading(LocalDateTime time, String sensorId, double value, boolean alarm) {
}
