package com.makerspace.records;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The STORAGE: every line of SQL in the program lives in this class (Module 14b).
 * The window only ever sees Student records, lists and maps, never a ResultSet.
 *
 * It holds one Connection open for as long as the program runs, so it implements
 * AutoCloseable: the window closes it when the program ends, and the tests open
 * it in try-with-resources.
 */
public class StudentRepository implements AutoCloseable {

    private final Connection db;

    /** Opens (or creates) the database, e.g. "jdbc:sqlite:students.db",
     *  or "jdbc:sqlite::memory:" for a throwaway one in the tests. */
    public StudentRepository(String url) throws SQLException {
        db = DriverManager.getConnection(url);
        createTable();
    }

    private void createTable() throws SQLException {
        try (Statement statement = db.createStatement()) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS students (
                        id        TEXT PRIMARY KEY,
                        name      TEXT NOT NULL,
                        programme TEXT NOT NULL,
                        level     INTEGER NOT NULL CHECK (level IN (100, 200, 300, 400)),
                        gpa       REAL NOT NULL CHECK (gpa BETWEEN 0 AND 4)
                    )""");
        }
    }

    // ---------- Create ----------

    /** Adds a student. Returns false if that ID is already taken. */
    public boolean add(Student s) throws SQLException {
        if (find(s.id()) != null) {
            return false;
        }
        try (PreparedStatement insert = db.prepareStatement(
                "INSERT INTO students (id, name, programme, level, gpa) VALUES (?, ?, ?, ?, ?)")) {
            insert.setString(1, s.id());
            insert.setString(2, s.name());
            insert.setString(3, s.programme());
            insert.setInt(4, s.level());
            insert.setDouble(5, s.gpa());
            insert.executeUpdate();
            return true;
        }
    }

    /** Adds many students in ONE transaction: all of them, or (if anything fails) none. */
    public void addAll(List<Student> students) throws SQLException {
        db.setAutoCommit(false);
        try {
            for (Student s : students) {
                add(s);
            }
            db.commit();
        } catch (SQLException e) {
            db.rollback();
            throw e;
        } finally {
            db.setAutoCommit(true);
        }
    }

    // ---------- Read ----------

    /** The student with this ID, or null if there isn't one. */
    public Student find(String id) throws SQLException {
        try (PreparedStatement query = db.prepareStatement("SELECT * FROM students WHERE id = ?")) {
            query.setString(1, id);
            List<Student> found = readAll(query);
            return found.isEmpty() ? null : found.get(0);
        }
    }

    /** Students whose name contains the text (any capitals), or whose ID or programme
     *  is exactly the text. Blank text matches everyone. Sorted by ID. */
    public List<Student> search(String text) throws SQLException {
        String t = text == null ? "" : text.trim();
        try (PreparedStatement query = db.prepareStatement("""
                SELECT * FROM students
                WHERE ? = ''
                   OR LOWER(name) LIKE '%' || LOWER(?) || '%'
                   OR UPPER(id) = UPPER(?)
                   OR UPPER(programme) = UPPER(?)
                ORDER BY id""")) {
            for (int i = 1; i <= 4; i++) {
                query.setString(i, t);   // the same text in all four places, always as DATA
            }
            return readAll(query);
        }
    }

    /** The average GPA in each programme, in programme order (for the chart). */
    public Map<String, Double> averageGpaByProgramme() throws SQLException {
        Map<String, Double> averages = new LinkedHashMap<>();
        try (PreparedStatement query = db.prepareStatement(
                "SELECT programme, AVG(gpa) AS average FROM students GROUP BY programme ORDER BY programme");
             ResultSet rows = query.executeQuery()) {
            while (rows.next()) {
                averages.put(rows.getString("programme"), rows.getDouble("average"));
            }
        }
        return averages;
    }

    public int count() throws SQLException {
        try (PreparedStatement query = db.prepareStatement("SELECT COUNT(*) FROM students");
             ResultSet rows = query.executeQuery()) {
            rows.next();
            return rows.getInt(1);
        }
    }

    private static List<Student> readAll(PreparedStatement query) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (ResultSet rows = query.executeQuery()) {
            while (rows.next()) {
                students.add(new Student(
                        rows.getString("id"),
                        rows.getString("name"),
                        rows.getString("programme"),
                        rows.getInt("level"),
                        rows.getDouble("gpa")));
            }
        }
        return students;
    }

    // ---------- Update ----------

    /** Saves new details for an existing student. Returns false if there's no such ID. */
    public boolean update(Student s) throws SQLException {
        try (PreparedStatement change = db.prepareStatement(
                "UPDATE students SET name = ?, programme = ?, level = ?, gpa = ? WHERE id = ?")) {
            change.setString(1, s.name());
            change.setString(2, s.programme());
            change.setInt(3, s.level());
            change.setDouble(4, s.gpa());
            change.setString(5, s.id());
            return change.executeUpdate() == 1;
        }
    }

    // ---------- Delete ----------

    /** Deletes a student. Returns false if there's no such ID. */
    public boolean delete(String id) throws SQLException {
        try (PreparedStatement remove = db.prepareStatement("DELETE FROM students WHERE id = ?")) {
            remove.setString(1, id);
            return remove.executeUpdate() == 1;
        }
    }

    @Override
    public void close() throws SQLException {
        db.close();
    }
}
