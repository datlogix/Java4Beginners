// Exercise 1: A library database, with CRUD methods and automatic checks.
//
// Write each method below so that every check in main() passes. Every method
// takes the Connection to use. main() runs them against an IN-MEMORY database
// (jdbc:sqlite::memory:), so each run starts empty and leaves no files behind.
//
// Rules:
//   - Use a PreparedStatement with ? placeholders for EVERY value that comes
//     from a parameter. No joining text into SQL.
//   - Open every Statement, PreparedStatement and ResultSet in
//     try-with-resources.
//   - The table:  books (isbn TEXT PRIMARY KEY, title TEXT NOT NULL,
//                        author TEXT NOT NULL, year INTEGER, on_loan INTEGER NOT NULL DEFAULT 0)
//     (SQLite has no true/false type: use 0 and 1.)
//
// Run it from the module folder with:   java -cp "lib/*" exercises/Exercise1.java

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise1 {

    static void createTable(Connection db) throws SQLException {
        // TODO
    }

    /** Adds a book. Returns false (instead of crashing) if the ISBN is already there.
     *  Hint: catch the SQLException from the PRIMARY KEY, or check with a SELECT first. */
    static boolean addBook(Connection db, String isbn, String title, String author, int year) throws SQLException {
        return false;   // TODO
    }

    /** The titles by an author (exact name, any capitals), in alphabetical order.
     *  Hint: WHERE LOWER(author) = LOWER(?) */
    static List<String> titlesBy(Connection db, String author) throws SQLException {
        return new ArrayList<>();   // TODO
    }

    /** Lends a book: true if it existed and was available, false otherwise.
     *  Do it in ONE UPDATE: ... WHERE isbn = ? AND on_loan = 0 */
    static boolean lend(Connection db, String isbn) throws SQLException {
        return false;   // TODO
    }

    /** Returns a book: true if it existed and was on loan. */
    static boolean returnBook(Connection db, String isbn) throws SQLException {
        return false;   // TODO
    }

    /** How many books are NOT on loan. */
    static int countAvailable(Connection db) throws SQLException {
        return 0;   // TODO
    }

    /** The titles of books published before a year, oldest first. */
    static List<String> olderThan(Connection db, int year) throws SQLException {
        return new ArrayList<>();   // TODO
    }

    /** Deletes a book, but only if it's not on loan. True if it was deleted. */
    static boolean delete(Connection db, String isbn) throws SQLException {
        return false;   // TODO
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

    public static void main(String[] args) throws SQLException {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            createTable(db);
            check("add Things Fall Apart", addBook(db, "978-0385474542", "Things Fall Apart", "Chinua Achebe", 1958), true);
            check("add Arrow of God", addBook(db, "978-0385014809", "Arrow of God", "Chinua Achebe", 1964), true);
            check("add Homegoing", addBook(db, "978-1101971062", "Homegoing", "Yaa Gyasi", 2016), true);
            check("add The Beautyful Ones", addBook(db, "978-0435905408", "The Beautyful Ones Are Not Yet Born", "Ayi Kwei Armah", 1968), true);
            check("duplicate ISBN refused", addBook(db, "978-1101971062", "Copy", "Someone", 2020), false);
            check("titles by Achebe (any capitals)", titlesBy(db, "chinua ACHEBE"), "[Arrow of God, Things Fall Apart]");
            check("titles by nobody", titlesBy(db, "Nobody"), "[]");
            check("4 available", countAvailable(db), 4);
            check("lend Homegoing", lend(db, "978-1101971062"), true);
            check("can't lend it twice", lend(db, "978-1101971062"), false);
            check("can't lend a missing book", lend(db, "000"), false);
            check("3 available", countAvailable(db), 3);
            check("can't delete a book on loan", delete(db, "978-1101971062"), false);
            check("return Homegoing", returnBook(db, "978-1101971062"), true);
            check("can't return it twice", returnBook(db, "978-1101971062"), false);
            check("older than 1965", olderThan(db, 1965), "[Things Fall Apart, Arrow of God]");
            check("delete Arrow of God", delete(db, "978-0385014809"), true);
            check("3 left, all available", countAvailable(db), 3);
            check("a sneaky author name finds nothing", titlesBy(db, "x' OR '1'='1"), "[]");
        }
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
