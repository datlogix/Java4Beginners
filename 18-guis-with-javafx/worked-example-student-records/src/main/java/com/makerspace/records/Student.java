package com.makerspace.records;

import java.util.List;

/**
 * One student. The MODEL: no JavaFX and no SQL in here, just the data and its rules.
 * A record can't be changed: to "edit" a student, make a new Student with the same id.
 */
public record Student(String id, String name, String programme, int level, double gpa) {

    public static final List<String> PROGRAMMES = List.of("BME", "CIV", "CSC", "EEE", "MEC");
    public static final List<Integer> LEVELS = List.of(100, 200, 300, 400);

    public Student {
        if (id == null || !id.matches("S\\d{3,4}")) {
            throw new IllegalArgumentException("The ID must be S followed by 3 or 4 digits, like S001");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name can't be empty");
        }
        if (!PROGRAMMES.contains(programme)) {
            throw new IllegalArgumentException("Choose a programme");
        }
        if (!LEVELS.contains(level)) {
            throw new IllegalArgumentException("The level must be 100, 200, 300 or 400");
        }
        if (gpa < 0 || gpa > 4) {
            throw new IllegalArgumentException("The GPA must be between 0 and 4");
        }
        name = name.trim();
    }
}
