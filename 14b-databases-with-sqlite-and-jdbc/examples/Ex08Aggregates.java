// Example 8: let the DATABASE do the arithmetic: COUNT, SUM, AVG, MIN, MAX,
// GROUP BY and HAVING. Like Module 15's Collectors, but in SQL.
// Run Ex00StudentDatabase.java first, then:   java -cp "lib/*" examples/Ex08Aggregates.java

import java.sql.*;

public class Ex08Aggregates {
    public static void main(String[] args) throws SQLException {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:school.db");
             Statement s = db.createStatement()) {

            try (ResultSet r = s.executeQuery(
                    "SELECT COUNT(*), ROUND(AVG(gpa), 3), MIN(gpa), MAX(gpa) FROM students")) {
                r.next();
                System.out.printf("%d students; GPA average %.3f, lowest %.2f, highest %.2f%n%n",
                        r.getInt(1), r.getDouble(2), r.getDouble(3), r.getDouble(4));
            }

            System.out.println("Programme  Students  Average  Honours (3.5+)");
            try (ResultSet r = s.executeQuery("""
                    SELECT programme,
                           COUNT(*)                                  AS students,
                           ROUND(AVG(gpa), 2)                        AS average,
                           SUM(CASE WHEN gpa >= 3.5 THEN 1 ELSE 0 END) AS honours
                    FROM students
                    GROUP BY programme
                    ORDER BY average DESC
                    """)) {
                while (r.next()) {
                    System.out.printf("%-10s %8d %8.2f %8d%n", r.getString("programme"), r.getInt("students"),
                            r.getDouble("average"), r.getInt("honours"));
                }
            }

            // HAVING filters GROUPS (WHERE filters rows before grouping)
            System.out.println("\nSurnames shared by more than 210 students:");
            try (ResultSet r = s.executeQuery("""
                    SELECT SUBSTR(name, INSTR(name, ' ') + 1) AS surname, COUNT(*) AS n
                    FROM students GROUP BY surname HAVING n > 210 ORDER BY n DESC
                    """)) {
                while (r.next()) {
                    System.out.println("  " + r.getString("surname") + ": " + r.getInt("n"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Problem: " + e.getMessage() + " (did you run Ex00StudentDatabase first?)");
        }
    }
}
