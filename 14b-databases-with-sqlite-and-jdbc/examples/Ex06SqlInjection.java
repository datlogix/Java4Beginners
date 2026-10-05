// Example 6: SQL injection, and why PreparedStatement stops it.
// A login check built by JOINING TEXT lets a user rewrite the SQL itself.
// Run it from the module folder with:   java -cp "lib/*" examples/Ex06SqlInjection.java

import java.sql.*;

public class Ex06SqlInjection {

    /** UNSAFE: the user's text becomes part of the SQL code. Never do this. */
    static boolean unsafeLogin(Connection db, String user, String password) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE name = '" + user + "' AND password = '" + password + "'";
        System.out.println("    the database runs: " + sql);
        try (Statement s = db.createStatement(); ResultSet r = s.executeQuery(sql)) {
            return r.next() && r.getInt(1) > 0;
        }
    }

    /** SAFE: the SQL is fixed; the user's text is only ever DATA. */
    static boolean safeLogin(Connection db, String user, String password) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE name = ? AND password = ?";
        try (PreparedStatement p = db.prepareStatement(sql)) {
            p.setString(1, user);
            p.setString(2, password);
            try (ResultSet r = p.executeQuery()) {
                return r.next() && r.getInt(1) > 0;
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite::memory:");
             Statement s = db.createStatement()) {
            s.execute("CREATE TABLE users (name TEXT, password TEXT)");
            s.execute("INSERT INTO users VALUES ('admin', 'Kente#2026')");
            // (Real systems store a HASH of the password, never the password itself.)

            String user = "admin";
            String attack = "anything' OR '1'='1";

            System.out.println("Unsafe login, correct password:  " + unsafeLogin(db, user, "Kente#2026"));
            System.out.println("Unsafe login, wrong password:    " + unsafeLogin(db, user, "guess"));
            System.out.println("Unsafe login, the attack:        " + unsafeLogin(db, user, attack));
            System.out.println("  ...the attacker is IN, without knowing the password!");
            System.out.println();
            System.out.println("Safe login, correct password:    " + safeLogin(db, user, "Kente#2026"));
            System.out.println("Safe login, the attack:          " + safeLogin(db, user, attack));
            System.out.println("  ...the attack is just a strange password, and it's wrong.");
        }
    }
}
