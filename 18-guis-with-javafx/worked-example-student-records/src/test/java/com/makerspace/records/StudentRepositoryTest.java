package com.makerspace.records;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the database code. They use an IN-MEMORY database, so every test
 * starts empty, nothing is left on disk, and no window ever opens.
 */
class StudentRepositoryTest {

    private StudentRepository repo;

    @BeforeEach
    void setUp() throws SQLException {
        repo = new StudentRepository("jdbc:sqlite::memory:");
        repo.addAll(List.of(
                new Student("S001", "Akua Mensah", "BME", 300, 3.8),
                new Student("S002", "Kwesi Appiah", "EEE", 200, 3.0),
                new Student("S003", "Adwoa Mensah", "EEE", 100, 3.4)));
    }

    @AfterEach
    void tearDown() throws SQLException {
        repo.close();
    }

    @Test
    void addedStudentsCanBeFound() throws SQLException {
        assertEquals(3, repo.count());
        assertEquals("Kwesi Appiah", repo.find("S002").name());
        assertNull(repo.find("S999"));
    }

    @Test
    void aTakenIdIsRefused() throws SQLException {
        assertFalse(repo.add(new Student("S001", "Somebody Else", "CSC", 100, 2.0)));
        assertEquals("Akua Mensah", repo.find("S001").name());
    }

    @Test
    void searchMatchesPartOfANameInAnyCase() throws SQLException {
        assertEquals(List.of("S001", "S003"), ids(repo.search("mensah")));
    }

    @Test
    void searchMatchesAnIdOrAProgrammeExactly() throws SQLException {
        assertEquals(List.of("S002"), ids(repo.search("s002")));
        assertEquals(List.of("S002", "S003"), ids(repo.search("EEE")));
    }

    @Test
    void blankSearchFindsEveryone() throws SQLException {
        assertEquals(3, repo.search("  ").size());
    }

    @Test
    void sqlInjectionFindsNothing() throws SQLException {
        assertEquals(List.of(), repo.search("x' OR '1'='1"));
        assertEquals(3, repo.count());   // and nothing was harmed
    }

    @Test
    void updateChangesTheDetails() throws SQLException {
        assertTrue(repo.update(new Student("S002", "Kwesi Appiah", "EEE", 300, 3.5)));
        assertEquals(300, repo.find("S002").level());
        assertFalse(repo.update(new Student("S999", "Nobody", "CSC", 100, 1.0)));
    }

    @Test
    void deleteRemovesOnlyThatStudent() throws SQLException {
        assertTrue(repo.delete("S002"));
        assertFalse(repo.delete("S002"));
        assertEquals(2, repo.count());
    }

    @Test
    void averagesAreGroupedByProgramme() throws SQLException {
        Map<String, Double> averages = repo.averageGpaByProgramme();
        assertEquals(List.of("BME", "EEE"), List.copyOf(averages.keySet()));
        assertEquals(3.8, averages.get("BME"), 1e-9);
        assertEquals(3.2, averages.get("EEE"), 1e-9);
    }

    @Test
    void addAllSkipsTakenIds() throws SQLException {
        repo.addAll(List.of(new Student("S010", "New One", "CSC", 100, 2.5),
                            new Student("S001", "Duplicate", "CSC", 100, 2.5)));
        assertEquals(4, repo.count());                          // only S010 was new
        assertEquals("Akua Mensah", repo.find("S001").name());  // S001 is unchanged
    }

    @Test
    void theRecordRefusesBadValues() {
        assertThrows(IllegalArgumentException.class, () -> new Student("001", "A", "CSC", 100, 3));
        assertThrows(IllegalArgumentException.class, () -> new Student("S001", " ", "CSC", 100, 3));
        assertThrows(IllegalArgumentException.class, () -> new Student("S001", "A", "LAW", 100, 3));
        assertThrows(IllegalArgumentException.class, () -> new Student("S001", "A", "CSC", 150, 3));
        assertThrows(IllegalArgumentException.class, () -> new Student("S001", "A", "CSC", 100, 4.5));
    }

    private static List<String> ids(List<Student> students) {
        return students.stream().map(Student::id).toList();
    }
}
