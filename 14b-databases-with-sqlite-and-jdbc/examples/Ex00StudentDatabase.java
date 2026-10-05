// Example 0: the hook. Build a database of 2,000 students in a file, then ask
// it five questions in SQL, the language of databases. Each answer is one line.
// Run it from the module folder with:   java -cp "lib/*" examples/Ex00StudentDatabase.java

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Random;

public class Ex00StudentDatabase {
    public static void main(String[] args) throws Exception {
        Files.deleteIfExists(Path.of("school.db"));
        String[] first = {"Ama", "Kofi", "Esi", "Kwame", "Akosua", "Yaw", "Efua", "Kojo", "Abena", "Nii", "Adwoa", "Selorm"};
        String[] last = {"Mensah", "Owusu", "Boateng", "Asante", "Darko", "Quaye", "Appiah", "Tetteh", "Ofori", "Addo"};
        String[] programmes = {"EEE", "BME", "CSC", "MEC", "CIV"};
        Random random = new Random(14);

        long start = System.nanoTime();
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:school.db")) {
            try (Statement s = db.createStatement()) {
                s.execute("CREATE TABLE students (id TEXT PRIMARY KEY, name TEXT, programme TEXT, level INTEGER, gpa REAL)");
            }
            db.setAutoCommit(false);
            try (PreparedStatement insert = db.prepareStatement("INSERT INTO students VALUES (?, ?, ?, ?, ?)")) {
                for (int i = 1; i <= 2000; i++) {
                    insert.setString(1, String.format("S%04d", i));
                    insert.setString(2, first[random.nextInt(first.length)] + " " + last[random.nextInt(last.length)]);
                    insert.setString(3, programmes[random.nextInt(programmes.length)]);
                    insert.setInt(4, (random.nextInt(4) + 1) * 100);
                    insert.setDouble(5, Math.round((1.5 + random.nextDouble() * 2.5) * 100) / 100.0);
                    insert.executeUpdate();
                }
            }
            db.commit();
            System.out.printf("Built school.db with 2,000 students in %d ms.%n%n",
                    (System.nanoTime() - start) / 1_000_000);

            ask(db, "How many students in each programme?",
                    "SELECT programme, COUNT(*) FROM students GROUP BY programme ORDER BY programme");
            ask(db, "Average GPA in each level?",
                    "SELECT level, ROUND(AVG(gpa), 2) FROM students GROUP BY level ORDER BY level");
            ask(db, "The top five students?",
                    "SELECT id, name, programme, gpa FROM students ORDER BY gpa DESC, id LIMIT 5");
            ask(db, "How many are on probation (GPA below 2.0)?",
                    "SELECT COUNT(*) FROM students WHERE gpa < 2.0");
            ask(db, "Which EEE level-400 students have a GPA of 3.9 or more?",
                    "SELECT id, name, gpa FROM students WHERE programme = 'EEE' AND level = 400 AND gpa >= 3.9");
        }
    }

    /** Runs a query and prints every row of the answer. */
    static void ask(Connection db, String question, String sql) throws SQLException {
        System.out.println(question);
        System.out.println("  SQL: " + sql);
        try (Statement s = db.createStatement(); ResultSet rows = s.executeQuery(sql)) {
            int columns = rows.getMetaData().getColumnCount();
            while (rows.next()) {
                StringBuilder line = new StringBuilder("   ");
                for (int c = 1; c <= columns; c++) {
                    line.append(" ").append(rows.getString(c));
                }
                System.out.println(line);
            }
        }
        System.out.println();
    }
}
