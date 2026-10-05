// Example 3: inserting rows with a PreparedStatement. The ? marks are
// placeholders: the values are sent separately from the SQL, so they can
// never be mistaken for SQL code (Example 6 shows why that matters).
// Run it from the module folder with:   java -cp "lib/*" examples/Ex03Insert.java

import java.sql.*;

public class Ex03Insert {

    record Product(String code, String name, String category, double price, int quantity) { }

    public static void main(String[] args) throws SQLException {
        Product[] stock = {
            new Product("A01", "Arduino Uno", "Boards", 180.0, 12),
            new Product("E02", "ESP32", "Boards", 95.0, 31),
            new Product("L05", "LED pack", "Components", 15.5, 40),
            new Product("S02", "Servo motor", "Motors", 45.0, 7),
            new Product("U01", "Ultrasonic sensor", "Sensors", 25.0, 64),
        };

        try (Connection db = DriverManager.getConnection("jdbc:sqlite:shop.db")) {
            try (Statement s = db.createStatement()) {
                s.execute("DROP TABLE IF EXISTS products");
                s.execute("CREATE TABLE products (code TEXT PRIMARY KEY, name TEXT NOT NULL, "
                        + "category TEXT NOT NULL, price REAL NOT NULL, quantity INTEGER NOT NULL)");
            }

            String sql = "INSERT INTO products (code, name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insert = db.prepareStatement(sql)) {
                for (Product p : stock) {
                    insert.setString(1, p.code());        // placeholders are numbered from 1
                    insert.setString(2, p.name());
                    insert.setString(3, p.category());
                    insert.setDouble(4, p.price());
                    insert.setInt(5, p.quantity());
                    int rows = insert.executeUpdate();     // how many rows changed
                    System.out.println("Inserted " + p.name() + " (" + rows + " row)");
                }
            }
        }
        System.out.println("Run Ex04Query.java next, to read them back.");
    }
}
