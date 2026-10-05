// Example 8: objects that contain other objects (composition).
// A Course HAS a list of Students.
// Run it with:  java Ex08ObjectsTogether.java

import java.util.ArrayList;
import java.util.List;

public class Ex08ObjectsTogether {

    static class Student {
        private final String id;
        private final String name;
        private final int mark;

        Student(String id, String name, int mark) {
            if (mark < 0 || mark > 100) {
                throw new IllegalArgumentException("Mark must be 0-100: " + mark);
            }
            this.id = id;
            this.name = name;
            this.mark = mark;
        }

        String getId() { return id; }
        String getName() { return name; }
        int getMark() { return mark; }

        @Override
        public String toString() {
            return name + " (" + id + "): " + mark;
        }
    }

    static class Course {
        private final String code;
        private final List<Student> students = new ArrayList<>();

        Course(String code) {
            this.code = code;
        }

        void enrol(Student s) {
            if (find(s.getId()) != null) {
                throw new IllegalArgumentException(s.getId() + " is already enrolled.");
            }
            students.add(s);
        }

        Student find(String id) {
            for (Student s : students) {
                if (s.getId().equals(id)) {
                    return s;
                }
            }
            return null;                    // "not found"
        }

        double average() {
            if (students.isEmpty()) {
                return 0;
            }
            int total = 0;
            for (Student s : students) {
                total += s.getMark();
            }
            return (double) total / students.size();
        }

        Student top() {
            Student best = null;
            for (Student s : students) {
                if (best == null || s.getMark() > best.getMark()) {
                    best = s;
                }
            }
            return best;
        }

        @Override
        public String toString() {
            return String.format("%s: %d students, average %.1f", code, students.size(), average());
        }
    }

    public static void main(String[] args) {
        Course course = new Course("EEE227");
        course.enrol(new Student("S01", "Akua", 78));
        course.enrol(new Student("S02", "Kwesi", 64));
        course.enrol(new Student("S03", "Nana", 91));

        System.out.println(course);
        System.out.println("Top: " + course.top());
        System.out.println("Find S02: " + course.find("S02"));
        System.out.println("Find S99: " + course.find("S99"));
    }
}
