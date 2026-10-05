// Example 1: connecting to a database. With SQLite, the whole database is one
// ordinary file; connecting to a file that doesn't exist creates it.
// Run it from the module folder with:   java -cp "lib/*" examples/Ex01Connecting.java

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex01Connecting {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:first.db";      // "jdbc:sqlite:" + the file name

        // A Connection is a resource, like a file reader: open it in try-with-resources
        // so it's ALWAYS closed, even if something goes wrong.
        try (Connection db = DriverManager.getConnection(url)) {
            DatabaseMetaData info = db.getMetaData();
            System.out.println("Connected to " + info.getDatabaseProductName()
                    + " " + info.getDatabaseProductVersion());
            System.out.println("Using the driver " + info.getDriverName() + " " + info.getDriverVersion());
            System.out.println("The database file is first.db, in " + System.getProperty("user.dir"));
        } catch (SQLException e) {
            // Every JDBC method can throw SQLException: a CHECKED exception (Module 13).
            System.out.println("Couldn't connect: " + e.getMessage());
        }

        // jdbc:sqlite::memory: makes a database that lives only in memory,
        // and disappears when the connection closes. Handy for tests.
        try (Connection temp = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            System.out.println("An in-memory database works too: " + !temp.isClosed());
        } catch (SQLException e) {
            System.out.println("Couldn't connect: " + e.getMessage());
        }
    }
}
