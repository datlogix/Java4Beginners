// Example 5: changing and removing rows with UPDATE and DELETE.
// ALWAYS give them a WHERE clause: without one, they change EVERY row.
// Run Ex03Insert.java first, then:   java -cp "lib/*" examples/Ex05UpdateDelete.java

import java.sql.*;

public class Ex05UpdateDelete {
    public static void main(String[] args) throws SQLException {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:shop.db")) {

            // Sell three servo motors
            try (PreparedStatement sell = db.prepareStatement(
                    "UPDATE products SET quantity = quantity - ? WHERE code = ? AND quantity >= ?")) {
                sell.setInt(1, 3);
                sell.setString(2, "S02");
                sell.setInt(3, 3);
                int changed = sell.executeUpdate();
                System.out.println(changed == 1 ? "Sold 3 servo motors." : "Couldn't sell: not enough stock, or no such code.");
            }

            // A 10% price rise for every board
            try (PreparedStatement rise = db.prepareStatement("UPDATE products SET price = ROUND(price * 1.10, 2) WHERE category = ?")) {
                rise.setString(1, "Boards");
                System.out.println("Raised the price of " + rise.executeUpdate() + " board(s).");
            }

            // Delete a product. executeUpdate tells you whether anything was actually deleted.
            try (PreparedStatement delete = db.prepareStatement("DELETE FROM products WHERE code = ?")) {
                for (String code : new String[]{"U01", "U01"}) {
                    delete.setString(1, code);
                    int gone = delete.executeUpdate();
                    System.out.println(gone == 1 ? "Deleted " + code : "Nothing to delete: " + code + " isn't there");
                }
            }

            try (Statement s = db.createStatement();
                 ResultSet rows = s.executeQuery("SELECT code, name, price, quantity FROM products")) {
                while (rows.next()) {
                    System.out.printf("  %-4s %-14s %8.2f  x%d%n", rows.getString(1), rows.getString(2),
                            rows.getDouble(3), rows.getInt(4));
                }
            }
        }
    }
}
