package com.makerspace.wardwatch.model;

/** Who a patient is. A record: these details don't change during a stay. */
public record Patient(String id, String name, int age) {
    public Patient {
        if (id == null || id.isBlank() || name == null || name.isBlank()) {
            throw new IllegalArgumentException("A patient needs an ID and a name.");
        }
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age out of range: " + age);
        }
    }
}
