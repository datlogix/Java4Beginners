package com.makerspace.planner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/** The model can be tested without a window. Add tests for fromCsv, validation and TaskStore. */
class TaskTest {

    @Test
    void aTaskPastItsDueDateIsOverdue() {
        Task t = new Task("Lab report", "EEE227", LocalDate.of(2026, 10, 1), Priority.HIGH, false);
        assertTrue(t.isOverdue(LocalDate.of(2026, 10, 5)));
    }

    @Test
    void aDoneTaskIsNeverOverdue() {
        Task t = new Task("Lab report", "EEE227", LocalDate.of(2026, 10, 1), Priority.HIGH, false).markedDone();
        assertFalse(t.isOverdue(LocalDate.of(2026, 10, 5)));
    }
}
