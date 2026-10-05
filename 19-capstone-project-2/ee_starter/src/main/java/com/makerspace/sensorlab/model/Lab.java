package com.makerspace.sensorlab.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** The bench: its sensors, and every reading taken. */
public class Lab {
    private final Map<String, Sensor> sensors = new LinkedHashMap<>();
    private final List<Reading> readings = new ArrayList<>();

    public void addSensor(Sensor sensor) {
        if (sensors.containsKey(sensor.getId())) {
            throw new IllegalArgumentException("There's already a sensor " + sensor.getId());
        }
        sensors.put(sensor.getId(), sensor);
    }

    public Sensor sensor(String id) throws SensorNotFoundException {
        Sensor s = sensors.get(id);
        if (s == null) {
            throw new SensorNotFoundException(id);
        }
        return s;
    }

    public List<Sensor> getSensors() {
        return new ArrayList<>(sensors.values());
    }

    /** Converts a raw value with the right sensor, stores the Reading, and returns it. */
    public Reading record(String sensorId, LocalDateTime time, double raw) throws SensorNotFoundException {
        Sensor s = sensor(sensorId);
        double value = s.valueOf(raw);
        Reading r = new Reading(time, sensorId, value, s.isAlarm(value));
        readings.add(r);
        return r;
    }

    public List<Reading> getReadings() {
        return new ArrayList<>(readings);
    }

    public List<Reading> readingsFor(String sensorId) {
        return new ArrayList<>();   // TODO (a stream)
    }

    public List<Reading> alarms() {
        return new ArrayList<>();   // TODO
    }

    // TODO: sessions (a Session class holding a name and its readings), clearing old readings...
}
