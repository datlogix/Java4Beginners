// Example 4: reading rows with SELECT. A ResultSet is a cursor: next() moves
// to the next row (false when there are no more), and getString / getInt /
// getDouble read that row's columns.
// Run Ex03Insert.java first, then:   java -cp "lib/*" examples/Ex04Query.java

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ex04Query {

    record Product(String code, String name, String category, double price, int quantity) { }

    public static void main(String[] args) throws SQLException {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:shop.db")) {

            // Every row, every column
            try (Statement s = db.createStatement();
                 ResultSet rows = s.executeQuery("SELECT code, name, price, quantity FROM products ORDER BY name")) {
                while (rows.next()) {
                    System.out.printf("%-4s %-18s GHS %7.2f  x%d%n", rows.getString("code"),
                            rows.getString("name"), rows.getDouble("price"), rows.getInt("quantity"));
                }
            }

            // A query with a parameter, read into a list of records
            List<Product> cheap = new ArrayList<>();
            String sql = "SELECT * FROM products WHERE price < ? ORDER BY price DESC";
            try (PreparedStatement query = db.prepareStatement(sql)) {
                query.setDouble(1, 50.0);
                try (ResultSet rows = query.executeQuery()) {
                    while (rows.next()) {
                        cheap.add(new Product(rows.getString("code"), rows.getString("name"),
                                rows.getString("category"), rows.getDouble("price"), rows.getInt("quantity")));
                    }
                }
            }
            System.out.println("\nUnder GHS 50: " + cheap);

            // Looking up ONE row by its key: check whether next() found anything
            try (PreparedStatement find = db.prepareStatement("SELECT name FROM products WHERE code = ?")) {
                for (String code : new String[]{"S02", "Z99"}) {
                    find.setString(1, code);
                    try (ResultSet row = find.executeQuery()) {
                        System.out.println(code + ": " + (row.next() ? row.getString("name") : "no such product"));
                    }
                }
            }
        }
    }
}
