package com.makerspace.wardwatch.model;

import java.util.ArrayList;
import java.util.List;

/** A patient's stay: who they are, where they are, and every measurement taken. */
public class PatientRecord {
    private final Patient patient;
    private final int bed;
    private final List<Measurement> measurements = new ArrayList<>();

    public PatientRecord(Patient patient, int bed) {
        this.patient = patient;
        this.bed = bed;
    }

    public Patient getPatient() { return patient; }
    public int getBed() { return bed; }

    public void add(Measurement m) {
        measurements.add(m);
    }

    public List<Measurement> getMeasurements() {
        return new ArrayList<>(measurements);
    }

    /** The most recent measurement of one kind (e.g. HeartRate.class), or null if there isn't one. */
    public <M extends Measurement> M latest(Class<M> kind) {
        return null;   // TODO (a stream: filter with kind.isInstance, max by time, then kind.cast)
    }

    /** The early-warning score: the sum of the points of the LATEST measurement of each kind. */
    public int earlyWarningScore() {
        return 0;   // TODO
    }
}
