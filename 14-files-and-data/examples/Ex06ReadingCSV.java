// Example 6: reading a CSV file (comma-separated values) into objects.
// Bad lines are reported and skipped, not allowed to crash the program.
// Run it from the examples folder with:  java Ex06ReadingCSV.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ex06ReadingCSV {

    record Student(String id, String name, String programme, double gpa) { }

    static List<Student> load(Path file) throws IOException {
        List<Student> students = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        for (int i = 1; i < lines.size(); i++) {          // start at 1: line 0 is the header
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] f = line.split(",");
            try {
                if (f.length != 4) {
                    throw new IllegalArgumentException("expected 4 fields, found " + f.length);
                }
                students.add(new Student(f[0].trim(), f[1].trim(), f[2].trim(), Double.parseDouble(f[3].trim())));
            } catch (IllegalArgumentException e) {        // includes NumberFormatException
                System.out.println("  Skipping line " + (i + 1) + " (" + line + "): " + e.getMessage());
            }
        }
        return students;
    }

    public static void main(String[] args) {
        try {
            List<Student> students = load(Path.of("data", "students.csv"));
            System.out.println("Loaded " + students.size() + " students:");
            for (Student s : students) {
                System.out.printf("  %-5s %-18s %-4s %.2f%n", s.id(), s.name(), s.programme(), s.gpa());
            }
        } catch (IOException e) {
            System.out.println("Couldn't read the file: " + e.getMessage());
        }
    }
}
