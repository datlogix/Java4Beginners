package com.makerspace.wardwatch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The hospital as a TREE: the hospital contains wards, wards contain bays,
 * and bays contain beds. A bed is a Location with no children, and it may hold
 * a patient ID. (The Composite pattern again, as in Module 16's resistor networks.)
 */
public class Location {
    private final String name;
    private final List<Location> children = new ArrayList<>();
    private String patientId;          // only used by beds: null means empty

    public Location(String name) {
        this.name = name;
    }

    public Location add(Location child) {
        children.add(child);
        return this;
    }

    public String getName() { return name; }
    public boolean isBed() { return children.isEmpty(); }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    /** RECURSIVE: the path to the bed holding this patient, e.g.
     *  "Korle Bu > Ward 3B > Bay 2 > Bed 7", or null if they aren't anywhere. */
    public String find(String patientId) {
        return null;   // TODO
    }

    /** RECURSIVE: how many empty beds there are in this location, at any depth. */
    public int freeBeds() {
        return 0;   // TODO
    }
}
