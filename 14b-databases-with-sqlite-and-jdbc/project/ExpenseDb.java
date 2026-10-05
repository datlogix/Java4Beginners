// Module 14b Project, Track A: Expense Tracker on a Database
// Author: YOUR NAME
//
// Run it from the module folder with:   java -cp "lib/*" project/ExpenseDb.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseDb {
    static final String URL = "jdbc:sqlite:expenses.db";
    static final Path CSV = Path.of("project", "data", "expenses.csv");

    record Expense(int id, String date, String category, String description, double amount) { }

    /** ALL the SQL lives here. */
    static class ExpenseRepository implements AutoCloseable {
        private final Connection db;

        ExpenseRepository(String url) throws SQLException {
            db = DriverManager.getConnection(url);
            try (Statement s = db.createStatement()) {
                s.execute("""
                        CREATE TABLE IF NOT EXISTS expenses (
                            id          INTEGER PRIMARY KEY AUTOINCREMENT,
                            date        TEXT NOT NULL,            -- YYYY-MM-DD sorts correctly as text
                            category    TEXT NOT NULL,
                            description TEXT NOT NULL,
                            amount      REAL NOT NULL CHECK (amount > 0)
                        )""");
            }
        }

        /** Imports the CSV in one transaction; returns a message for each bad line. */
        List<String> importCsv(Path csv) throws SQLException, IOException {
            List<String> problems = new ArrayList<>();
            // TODO: skip the header; validate each line (Module 14); insert the good ones
            return problems;
        }

        Expense add(String date, String category, String description, double amount) throws SQLException {
            // TODO (Statement.RETURN_GENERATED_KEYS, as in Ex09Repository)
            return null;
        }

        boolean delete(int id) throws SQLException {
            return false;   // TODO
        }

        /** One month ("2026-09"), in date order. Hint: WHERE date LIKE ? with "2026-09%". */
        List<Expense> month(String yearMonth) throws SQLException {
            return new ArrayList<>();   // TODO
        }

        // TODO: totalsByCategory(), monthlyTotals(), biggest(n), search(text), count()

        @Override
        public void close() throws SQLException {
            db.close();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try (ExpenseRepository repo = new ExpenseRepository(URL)) {
            // TODO: if the table is empty, offer to import the CSV; then a menu:
            //       add, delete, list a month, totals by category, monthly totals,
            //       the five biggest expenses, search descriptions, quit
            System.out.println("Connected to " + URL);
        } catch (SQLException e) {
            System.out.println("Database problem: " + e.getMessage());
        }
    }
}
