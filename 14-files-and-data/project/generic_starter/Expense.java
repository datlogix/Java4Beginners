// Module 14 Project, Track A: Expense Tracker
// Author: YOUR NAME

import java.time.LocalDate;

/** One expense. A record, saved as one CSV line: date,category,description,amount */
public record Expense(LocalDate date, String category, String description, double amount) {

    public Expense {
        // TODO: reject a null date, an empty category or description, a description
        //       containing a comma (it would break the CSV!), and an amount <= 0
    }

    public String toCsv() {
        return date + "," + category + "," + description + "," + amount;
    }

    /** Builds an Expense from one CSV line. Throws IllegalArgumentException
     *  (with a clear message) if the line is broken. */
    public static Expense fromCsv(String line) {
        // TODO: split, check there are 4 fields, LocalDate.parse the date
        //       (DateTimeParseException is unchecked: turn it into an
        //       IllegalArgumentException), Double.parseDouble the amount
        throw new IllegalArgumentException("fromCsv not written yet");
    }
}
