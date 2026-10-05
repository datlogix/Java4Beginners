package com.makerspace.sensorlab.model;

public class SensorNotFoundException extends SensorLabException {
    public SensorNotFoundException(String id) {
        super("No sensor has the ID " + id);
    }
}
