// Example 7: transactions. Several changes that must ALL happen, or NONE:
// moving money between accounts is the classic case. Transactions also make
// big batches of inserts dramatically faster.
// Run it from the module folder with:   java -cp "lib/*" examples/Ex07Transactions.java

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class Ex07Transactions {

    static void transfer(Connection db, String from, String to, double amount) throws SQLException {
        db.setAutoCommit(false);                       // start a transaction
        try (PreparedStatement take = db.prepareStatement(
                     "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?");
             PreparedStatement give = db.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?")) {
            take.setDouble(1, amount);
            take.setString(2, from);
            take.setDouble(3, amount);
            if (take.executeUpdate() != 1) {
                throw new SQLException("Insufficient funds in " + from);
            }
            give.setDouble(1, amount);
            give.setString(2, to);
            if (give.executeUpdate() != 1) {
                throw new SQLException("No such account: " + to);
            }
            db.commit();                               // both changes become permanent together
            System.out.println("Moved GHS " + amount + " from " + from + " to " + to);
        } catch (SQLException e) {
            db.rollback();                             // undo EVERYTHING since the transaction began
            System.out.println("Transfer cancelled (" + e.getMessage() + "); nothing changed.");
        } finally {
            db.setAutoCommit(true);
        }
    }

    static void show(Connection db) throws SQLException {
        try (Statement s = db.createStatement(); ResultSet r = s.executeQuery("SELECT id, balance FROM accounts")) {
            while (r.next()) {
                System.out.printf("    %s: GHS %.2f%n", r.getString(1), r.getDouble(2));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            try (Statement s = db.createStatement()) {
                s.execute("CREATE TABLE accounts (id TEXT PRIMARY KEY, balance REAL)");
                s.execute("INSERT INTO accounts VALUES ('AMA', 500), ('KOJO', 100)");
            }
            transfer(db, "AMA", "KOJO", 200);
            transfer(db, "KOJO", "AMA", 1000);      // not enough money
            transfer(db, "AMA", "NOBODY", 50);      // money taken, then no one to give it to: rolled back
            show(db);
        }

        // Speed: 5,000 inserts into a FILE, one transaction each versus one transaction for all.
        Files.deleteIfExists(Path.of("speed.db"));
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:speed.db")) {
            try (Statement s = db.createStatement()) {
                s.execute("CREATE TABLE readings (n INTEGER, value REAL)");
            }
            try (PreparedStatement insert = db.prepareStatement("INSERT INTO readings VALUES (?, ?)")) {
                long t = System.nanoTime();
                for (int i = 0; i < 5_000; i++) {
                    insert.setInt(1, i);
                    insert.setDouble(2, Math.random());
                    insert.executeUpdate();            // each one is its own transaction
                }
                System.out.printf("%nOne at a time:      %5d ms%n", (System.nanoTime() - t) / 1_000_000);

                t = System.nanoTime();
                db.setAutoCommit(false);
                for (int i = 0; i < 5_000; i++) {
                    insert.setInt(1, i);
                    insert.setDouble(2, Math.random());
                    insert.executeUpdate();
                }
                db.commit();
                System.out.printf("In one transaction: %5d ms%n", (System.nanoTime() - t) / 1_000_000);
            }
        }
    }
}
