// Module 14b Project, Track C: Patient Vitals on a Database
// Author: YOUR NAME
// Thresholds are simplified for teaching. This is not a clinical tool.
//
// Run it from the module folder with:   java -cp "lib/*" project/VitalsDb.java

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VitalsDb {
    static final String URL = "jdbc:sqlite:ward.db";
    static final Path CSV = Path.of("project", "data", "vitals_log.csv");

    /** ALL the SQL lives here. */
    static class VitalsRepository implements AutoCloseable {
        private final Connection db;

        VitalsRepository(String url) throws SQLException {
            db = DriverManager.getConnection(url);
            try (Statement s = db.createStatement()) {
                s.execute("DROP TABLE IF EXISTS readings");
                s.execute("""
                        CREATE TABLE readings (
                            patient_id  TEXT NOT NULL,
                            time        TEXT NOT NULL,
                            heart_rate  INTEGER NOT NULL CHECK (heart_rate BETWEEN 20 AND 250),
                            spo2        INTEGER NOT NULL CHECK (spo2 BETWEEN 50 AND 100),
                            temperature REAL NOT NULL CHECK (temperature BETWEEN 30 AND 45),
                            PRIMARY KEY (patient_id, time)
                        )""");
            }
        }

        /** Imports the log in one transaction; returns a message for each bad row.
         *  (Notice the CHECK constraints: the database refuses impossible values too.) */
        List<String> importCsv(Path csv) throws SQLException, IOException {
            List<String> problems = new ArrayList<>();
            // TODO
            return problems;
        }

        /** One line per patient: readings, average HR, lowest SpO2, highest temperature,
         *  number of abnormal readings. ONE query with GROUP BY patient_id.
         *  Abnormal: heart_rate < 60 OR heart_rate > 100 OR spo2 < 94 OR temperature >= 38.0
         *  Hint: SUM(CASE WHEN ... THEN 1 ELSE 0 END) counts the abnormal ones. */
        List<String> summary() throws SQLException {
            return new ArrayList<>();   // TODO
        }

        /** Every abnormal reading for one patient (a PreparedStatement), in time order. */
        List<String> alertsFor(String patientId) throws SQLException {
            return new ArrayList<>();   // TODO
        }

        @Override
        public void close() throws SQLException {
            db.close();
        }
    }

    public static void main(String[] args) {
        try (VitalsRepository repo = new VitalsRepository(URL)) {
            repo.importCsv(CSV).forEach(p -> System.out.println("Skipped " + p));
            repo.summary().forEach(System.out::println);
            // TODO: ask for a patient ID and print their alerts; export alerts to output/alerts.csv
        } catch (SQLException | IOException e) {
            System.out.println("Problem: " + e.getMessage());
        }
    }
}
