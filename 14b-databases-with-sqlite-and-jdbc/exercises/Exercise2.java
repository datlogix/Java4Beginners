// Exercise 2: From a CSV file to a database, and reports in SQL.
//
// exercises/data/marks.csv is the Module 14 marks file: id,name,ca,exam,
// with some broken lines. This time:
//
//   1. importMarks(): create the table
//          results (id TEXT PRIMARY KEY, name TEXT NOT NULL, ca INTEGER NOT NULL,
//                   exam INTEGER NOT NULL, total INTEGER NOT NULL)
//      and insert every GOOD line in ONE transaction (setAutoCommit(false), then
//      commit). Skip bad lines exactly as in Module 14 (4 fields, whole numbers,
//      CA 0-40, exam 0-60), print "Skipped line N: reason" for each, and
//      return the number of rows imported.
//   2. Write the four query methods. Each is ONE SQL query: let the database
//      do the work (ORDER BY, LIMIT, AVG, COUNT, GROUP BY, CASE).
//   3. searchByName() uses LIKE with a ? placeholder, so a malicious search
//      can't break out of the query.
//
// The checks in main() tell you when you're right.
//
// Run it from the module folder with:   java -cp "lib/*" exercises/Exercise2.java

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Exercise2 {
    static final Path CSV = Path.of("exercises", "data", "marks.csv");

    static int importMarks(Connection db, Path csv) throws SQLException, IOException {
        return 0;   // TODO
    }

    /** The class average total, rounded to 1 decimal place (in SQL: ROUND(AVG(total), 1)). */
    static double average(Connection db) throws SQLException {
        return 0;   // TODO
    }

    /** The names of the top n students by total, best first. */
    static List<String> top(Connection db, int n) throws SQLException {
        return new ArrayList<>();   // TODO
    }

    /** How many students got each grade (A 80+, B 70+, C 60+, D 50+, F), in grade order,
     *  leaving out grades nobody got. Hint: a CASE expression, then GROUP BY. */
    static Map<String, Integer> gradeCounts(Connection db) throws SQLException {
        return new LinkedHashMap<>();   // TODO
    }

    /** The names containing text, any capitals, alphabetically. */
    static List<String> searchByName(Connection db, String text) throws SQLException {
        return new ArrayList<>();   // TODO
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

    public static void main(String[] args) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            check("imported 6 good rows", importMarks(db, CSV), 6);
            check("class average", average(db), 74.2);
            check("top 3", top(db, 3), "[Efua Sackey, Abena Boateng, Akosua Badu]");
            check("grade counts", gradeCounts(db), "{A=3, B=1, C=1, F=1}");
            check("search 'ab'", searchByName(db, "ab"), "[Abena Boateng]");
            check("search 'MENSAH'", searchByName(db, "MENSAH"), "[Adwoa Mensah]");
            check("search for an attack", searchByName(db, "' OR '1'='1"), "[]");
        }
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
