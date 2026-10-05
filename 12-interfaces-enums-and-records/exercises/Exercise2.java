// Exercise 2: Sorting and selecting students.
//
// 1. Make the Student record implement Comparable<Student>, so that its
//    natural order is by id.
// 2. Fill in the three Comparator constants, using Comparator.comparing...
//    and method references (Student::gpa) rather than writing compare() by hand.
// 3. Write select(), which returns a NEW list of the students that pass the
//    test. StudentTest is our own functional interface, so main() can pass
//    lambdas like  s -> s.level() == 200.
// 4. Write topN(), which returns the n students with the best GPA (best
//    first), without changing the list it was given.
//
// Run it with:  java Exercise2.java

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exercise2 {

    record Student(String id, String name, String programme, int level, double gpa) {
        // TODO: implements Comparable<Student> (add it to the record line), and compareTo
    }

    /** A yes/no question about a student. */
    interface StudentTest {
        boolean test(Student s);
    }

    static final Comparator<Student> BY_GPA_BEST_FIRST = null;                 // TODO
    static final Comparator<Student> BY_NAME = null;                           // TODO
    static final Comparator<Student> BY_PROGRAMME_THEN_GPA_BEST_FIRST = null;  // TODO

    static List<Student> select(List<Student> students, StudentTest test) {
        return new ArrayList<>();   // TODO
    }

    static List<Student> topN(List<Student> students, int n) {
        return new ArrayList<>();   // TODO
    }

    // ---------------- Don't change anything below this line ----------------

    static int passed = 0, failed = 0;

    static void check(String description, Object actual, Object expected) {
        if (String.valueOf(actual).equals(String.valueOf(expected))) {
            passed++;
            System.out.println("PASS  " + description);
        } else {
            failed++;
            System.out.println("FAIL  " + description + ": got " + actual + ", expected " + expected);
        }
    }

    static List<String> names(List<Student> students) {
        List<String> result = new ArrayList<>();
        for (Student s : students) {
            result.add(s.name());
        }
        return result;
    }

    public static void main(String[] args) {
        List<Student> all = List.of(
                new Student("S004", "Kwesi", "EEE", 200, 3.10),
                new Student("S001", "Akua", "BME", 300, 3.85),
                new Student("S006", "Yaa", "EEE", 300, 3.85),
                new Student("S002", "Nii", "CSC", 100, 2.40),
                new Student("S005", "Esi", "BME", 200, 3.55),
                new Student("S003", "Kofi", "CSC", 200, 1.95));

        List<Student> copy = new ArrayList<>(all);
        try {
            copy.sort(null);          // null means "use the natural order (compareTo)"
            check("natural order is by id", names(copy), "[Akua, Nii, Kofi, Kwesi, Esi, Yaa]");
        } catch (ClassCastException e) {
            check("natural order is by id", "Student isn't Comparable yet", "[Akua, Nii, Kofi, Kwesi, Esi, Yaa]");
        }
        if (BY_GPA_BEST_FIRST != null && BY_NAME != null && BY_PROGRAMME_THEN_GPA_BEST_FIRST != null) {
            copy.sort(BY_NAME);
            check("by name", names(copy), "[Akua, Esi, Kofi, Kwesi, Nii, Yaa]");
            copy.sort(BY_GPA_BEST_FIRST.thenComparing(BY_NAME));
            check("by GPA best first, then name", names(copy), "[Akua, Yaa, Esi, Kwesi, Nii, Kofi]");
            copy.sort(BY_PROGRAMME_THEN_GPA_BEST_FIRST);
            check("by programme then GPA", names(copy), "[Akua, Esi, Nii, Kofi, Yaa, Kwesi]");
        } else {
            check("comparators written", false, true);
        }
        check("level 200 students", names(select(all, s -> s.level() == 200)), "[Kwesi, Esi, Kofi]");
        check("on probation (GPA < 2.0)", names(select(all, s -> s.gpa() < 2.0)), "[Kofi]");
        check("EEE with GPA 3+", names(select(all, s -> s.programme().equals("EEE") && s.gpa() >= 3)),
                "[Kwesi, Yaa]");
        check("top 3", topN(all, 3).size(), 3);
        List<Student> best = topN(all, 1);
        check("top 1 has GPA 3.85", best.isEmpty() ? "nothing" : best.get(0).gpa(), 3.85);
        check("topN doesn't change the list", all.get(0).name(), "Kwesi");
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
