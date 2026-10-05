// Example 2: a table is like a CSV file with RULES. Each column has a type,
// and constraints stop bad data getting in at all.
// Run it from the module folder with:   java -cp "lib/*" examples/Ex02CreateTable.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex02CreateTable {
    public static void main(String[] args) throws SQLException {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:shop.db");
             Statement sql = db.createStatement()) {

            sql.execute("DROP TABLE IF EXISTS products");     // start fresh each run
            sql.execute("""
                    CREATE TABLE products (
                        code      TEXT PRIMARY KEY,                -- unique: no two products share a code
                        name      TEXT NOT NULL,                   -- must be given
                        category  TEXT NOT NULL DEFAULT 'Other',
                        price     REAL NOT NULL CHECK (price >= 0), -- the database refuses negatives
                        quantity  INTEGER NOT NULL DEFAULT 0
                    )
                    """);
            System.out.println("Created the products table in shop.db.");

            sql.execute("INSERT INTO products (code, name, category, price, quantity) "
                    + "VALUES ('A01', 'Arduino Uno', 'Boards', 180.0, 12)");
            System.out.println("Added one product.");

            try {
                sql.execute("INSERT INTO products (code, name, price) VALUES ('A01', 'Duplicate', 5.0)");
            } catch (SQLException e) {
                System.out.println("Refused a duplicate code: " + e.getMessage());
            }
            try {
                sql.execute("INSERT INTO products (code, name, price) VALUES ('X99', 'Free money', -10)");
            } catch (SQLException e) {
                System.out.println("Refused a negative price: " + e.getMessage());
            }
        }
    }
}
