package com.makerspace.planner;

import java.time.LocalDate;

/** One thing to do. A record: to "change" a task (mark it done), make a new one. */
public record Task(String title, String subject, LocalDate due, Priority priority, boolean done) {

    public Task {
        // TODO: reject a blank title or subject, a comma in either (CSV!), and a null date or priority
    }

    public Task markedDone() {
        return new Task(title, subject, due, priority, true);
    }

    public boolean isOverdue(LocalDate today) {
        return !done && due.isBefore(today);
    }

    public String toCsv() {
        return title + "," + subject + "," + due + "," + priority + "," + done;
    }

    public static Task fromCsv(String line) {
        // TODO (Module 14)
        throw new IllegalArgumentException("fromCsv not written yet");
    }
}
