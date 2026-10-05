package com.makerspace.wardwatch.model;

public class PatientNotFoundException extends WardWatchException {
    public PatientNotFoundException(String id) {
        super("No patient on the ward has the ID " + id);
    }
}
