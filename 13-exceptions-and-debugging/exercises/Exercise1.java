// Exercise 1: Crash-proof input helpers, with automatic checks.
//
// Write four input methods that NEVER crash, whatever the user types. Each one
// takes the Scanner to read from as its first parameter. That makes them
// testable: main() below feeds them pretend keyboard input from a String, so you
// can check them without typing anything.
//
//   readInt(in, prompt, min, max)
//       asks until the answer is a whole number from min to max; returns it
//   readDouble(in, prompt, min, max)
//       the same for decimal numbers
//   readYesNo(in, prompt)
//       accepts y, yes, n, no (any capitals); returns true for yes
//   readDate(in, prompt)
//       accepts a date written as YYYY-MM-DD and returns a LocalDate.
//       LocalDate.parse(text) does the work, and throws a
//       DateTimeParseException for text like "tomorrow" or "2026-02-30".
//
// After each bad answer, print a helpful message (like "  'abc' isn't a whole
// number.") and ask again.
//
// When all the checks pass, try the helpers for real: run with
//     java Exercise1.java live
//
// Run the checks with:  java Exercise1.java

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Exercise1 {

    static int readInt(Scanner in, String prompt, int min, int max) {
        return 0;   // TODO
    }

    static double readDouble(Scanner in, String prompt, double min, double max) {
        return 0;   // TODO
    }

    static boolean readYesNo(Scanner in, String prompt) {
        return false;   // TODO
    }

    static LocalDate readDate(Scanner in, String prompt) {
        return LocalDate.now();   // TODO
    }

    // ---------------- Don't change anything below this line ----------------

    static int passed = 0, failed = 0;

    static void check(String description, Object actual, Object expected) {
        if (String.valueOf(actual).equals(String.valueOf(expected))) {
            passed++;
            System.out.println("PASS  " + description);
        } else {
            failed++;
            System.out.println("FAIL  " + description + ": got " + actual + ", expected " + expected);
        }
    }

    /** A Scanner that reads the given lines as if they were typed. */
    static Scanner typed(String... lines) {
        return new Scanner(String.join("\n", lines) + "\n");
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("live")) {
            Scanner keyboard = new Scanner(System.in);
            int lab = readInt(keyboard, "Lab number (1-12): ", 1, 12);
            double hours = readDouble(keyboard, "Hours (0.5-8): ", 0.5, 8);
            LocalDate day = readDate(keyboard, "Date (YYYY-MM-DD): ");
            boolean scope = readYesNo(keyboard, "Need an oscilloscope? ");
            System.out.printf("Booked lab %d for %.1f h on %s%s.%n", lab, hours, day, scope ? ", with a scope" : "");
            return;
        }
        System.out.println("(The prompts and messages below are your methods talking to the pretend user.)");
        check("readInt: good answer", readInt(typed("7"), "> ", 1, 10), 7);
        check("readInt: skips words and out-of-range", readInt(typed("seven", "", "70", "-1", " 3 "), "> ", 1, 10), 3);
        check("readInt: accepts the limits", readInt(typed("10"), "> ", 1, 10), 10);
        check("readDouble: decimals", readDouble(typed("x", "2.5"), "> ", 0, 5), 2.5);
        check("readDouble: rejects out of range", readDouble(typed("5.01", "4.99"), "> ", 0, 5), 4.99);
        check("readYesNo: Y", readYesNo(typed("Y"), "> "), true);
        check("readYesNo: maybe, then no", readYesNo(typed("maybe", "No"), "> "), false);
        check("readDate: good date", readDate(typed("2026-10-04"), "> "), "2026-10-04");
        check("readDate: skips impossible dates", readDate(typed("tomorrow", "2026-02-30", "2026-03-01"), "> "),
                "2026-03-01");
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
