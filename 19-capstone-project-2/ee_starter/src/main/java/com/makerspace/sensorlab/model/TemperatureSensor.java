package com.makerspace.sensorlab.model;

/** An LM35 temperature probe: the raw value is millivolts, 10 mV per degree C. */
public class TemperatureSensor extends Sensor {

    public TemperatureSensor(String id, String label, double lowLimit, double highLimit) {
        super(id, label, lowLimit, highLimit);
    }

    @Override
    public String unit() {
        return "C";
    }

    @Override
    protected double convert(double raw) {
        return 0;   // TODO: reject raw values outside 0-1500 mV; degrees = raw / 10
    }
}
