// Module 14b Project, Track B: Bench Data Logger on a Database
// Author: YOUR NAME
//
// Run it from the module folder with:   java -cp "lib/*" project/BenchDb.java

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BenchDb {
    static final String URL = "jdbc:sqlite:bench.db";
    static final Path CSV = Path.of("project", "data", "bench_log.csv");
    static final double CURRENT_LIMIT = 1.5;

    /** ALL the SQL lives here. */
    static class SampleRepository implements AutoCloseable {
        private final Connection db;

        SampleRepository(String url) throws SQLException {
            db = DriverManager.getConnection(url);
            try (Statement s = db.createStatement()) {
                s.execute("DROP TABLE IF EXISTS samples");    // rebuilt from the CSV every run
                s.execute("CREATE TABLE samples (time_s REAL PRIMARY KEY, volts REAL NOT NULL, amps REAL NOT NULL)");
            }
        }

        /** Imports the log in one transaction; returns a message for each bad row. */
        List<String> importCsv(Path csv) throws SQLException, IOException {
            List<String> problems = new ArrayList<>();
            // TODO
            return problems;
        }

        /** Count, min and max voltage, average current, peak power: ONE query.
         *  (Power is volts * amps, worked out in SQL.) */
        String summary() throws SQLException {
            return "TODO";
        }

        /** Every sample above the current limit, as "260 s: 1.930 A". */
        List<String> overCurrent(double limit) throws SQLException {
            return new ArrayList<>();   // TODO
        }

        /** Energy in Wh by the trapezium rule. Either read the rows in time order and add up
         *  in Java, or (stretch) do it all in SQL with the window function LAG(...) OVER (ORDER BY time_s). */
        double energyWh() throws SQLException {
            return 0;   // TODO
        }

        @Override
        public void close() throws SQLException {
            db.close();
        }
    }

    public static void main(String[] args) {
        try (SampleRepository repo = new SampleRepository(URL)) {
            repo.importCsv(CSV).forEach(p -> System.out.println("Skipped " + p));
            System.out.println(repo.summary());
            System.out.printf("Energy delivered: %.3f Wh%n", repo.energyWh());
            System.out.println("Over-current: " + repo.overCurrent(CURRENT_LIMIT));
        } catch (SQLException | IOException e) {
            System.out.println("Problem: " + e.getMessage());
        }
    }
}
