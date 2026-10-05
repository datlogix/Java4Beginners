package com.makerspace.wardwatch.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** A ward with a fixed number of beds, and the patients in them. */
public class Ward {
    public static final int ALERT_SCORE = 5;

    private final String name;
    private final int beds;
    private final Map<String, PatientRecord> patients = new LinkedHashMap<>();

    public Ward(String name, int beds) {
        this.name = name;
        this.beds = beds;
    }

    public String getName() { return name; }

    public void admit(Patient patient, int bed) throws WardWatchException {
        // TODO: refuse a bed number outside 1..beds, a bed that's taken (write a
        //       BedOccupiedException), or a patient who's already on the ward
        patients.put(patient.id(), new PatientRecord(patient, bed));
    }

    public PatientRecord record(String patientId) throws PatientNotFoundException {
        PatientRecord r = patients.get(patientId);
        if (r == null) {
            throw new PatientNotFoundException(patientId);
        }
        return r;
    }

    public List<PatientRecord> getPatients() {
        return new ArrayList<>(patients.values());
    }

    /** Patients whose early-warning score is ALERT_SCORE or more, highest first. */
    public List<PatientRecord> alerts() {
        return new ArrayList<>();   // TODO
    }

    // TODO: discharge, free beds, a ward overview sorted by score...
}
