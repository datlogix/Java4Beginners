// Example 9: putting it together. A repository class keeps ALL the SQL in one
// place, so the rest of the program works with ordinary objects (records) and
// never sees a Connection or a ResultSet. This is how real applications do it.
// Run it from the module folder with:   java -cp "lib/*" examples/Ex09Repository.java

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Ex09Repository {

    record Contact(int id, String name, String phone) { }

    /** Every database operation for contacts. Nothing else in the program writes SQL. */
    static class ContactRepository implements AutoCloseable {
        private final Connection db;

        ContactRepository(String url) throws SQLException {
            db = DriverManager.getConnection(url);
            try (Statement s = db.createStatement()) {
                s.execute("CREATE TABLE IF NOT EXISTS contacts ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, phone TEXT NOT NULL)");
            }
        }

        /** CREATE: adds a contact and returns it with its new ID. */
        Contact add(String name, String phone) throws SQLException {
            try (PreparedStatement p = db.prepareStatement(
                    "INSERT INTO contacts (name, phone) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                p.setString(1, name);
                p.setString(2, phone);
                p.executeUpdate();
                try (ResultSet keys = p.getGeneratedKeys()) {
                    keys.next();
                    return new Contact(keys.getInt(1), name, phone);
                }
            }
        }

        /** READ: every contact whose name contains text (ignoring capitals). */
        List<Contact> search(String text) throws SQLException {
            List<Contact> found = new ArrayList<>();
            try (PreparedStatement p = db.prepareStatement(
                    "SELECT id, name, phone FROM contacts WHERE name LIKE ? ORDER BY name")) {
                p.setString(1, "%" + text + "%");        // % means "anything" in a LIKE pattern
                try (ResultSet r = p.executeQuery()) {
                    while (r.next()) {
                        found.add(new Contact(r.getInt("id"), r.getString("name"), r.getString("phone")));
                    }
                }
            }
            return found;
        }

        Optional<Contact> find(int id) throws SQLException {
            try (PreparedStatement p = db.prepareStatement("SELECT id, name, phone FROM contacts WHERE id = ?")) {
                p.setInt(1, id);
                try (ResultSet r = p.executeQuery()) {
                    return r.next() ? Optional.of(new Contact(r.getInt(1), r.getString(2), r.getString(3)))
                                    : Optional.empty();
                }
            }
        }

        /** UPDATE: returns true if the contact existed. */
        boolean changePhone(int id, String phone) throws SQLException {
            try (PreparedStatement p = db.prepareStatement("UPDATE contacts SET phone = ? WHERE id = ?")) {
                p.setString(1, phone);
                p.setInt(2, id);
                return p.executeUpdate() == 1;
            }
        }

        /** DELETE: returns true if the contact existed. */
        boolean delete(int id) throws SQLException {
            try (PreparedStatement p = db.prepareStatement("DELETE FROM contacts WHERE id = ?")) {
                p.setInt(1, id);
                return p.executeUpdate() == 1;
            }
        }

        @Override
        public void close() throws SQLException {    // AutoCloseable: works in try-with-resources
            db.close();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try (ContactRepository contacts = new ContactRepository("jdbc:sqlite:contacts.db")) {
            while (true) {
                System.out.print("\n(a)dd  (s)earch  (p)hone change  (d)elete  (q)uit > ");
                String choice = in.nextLine().trim().toLowerCase();
                if (choice.equals("q")) {
                    break;
                }
                try {
                    switch (choice) {
                        case "a" -> {
                            System.out.print("Name: ");
                            String name = in.nextLine().trim();
                            System.out.print("Phone: ");
                            System.out.println("Saved " + contacts.add(name, in.nextLine().trim()));
                        }
                        case "s" -> {
                            System.out.print("Search for: ");
                            contacts.search(in.nextLine().trim()).forEach(c -> System.out.println("  " + c));
                        }
                        case "p" -> {
                            System.out.print("ID: ");
                            int id = Integer.parseInt(in.nextLine().trim());
                            System.out.print("New phone: ");
                            System.out.println(contacts.changePhone(id, in.nextLine().trim()) ? "Updated." : "No such ID.");
                        }
                        case "d" -> {
                            System.out.print("ID: ");
                            int id = Integer.parseInt(in.nextLine().trim());
                            System.out.println(contacts.delete(id) ? "Deleted." : "No such ID.");
                        }
                        default -> System.out.println("Please choose a, s, p, d or q.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("IDs are whole numbers.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database problem: " + e.getMessage());
        }
    }
}
