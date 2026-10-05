// Example 4: Comparable gives a class a NATURAL order, so Collections.sort,
// TreeSet, TreeMap and Collections.max know how to order its objects.
// Run it with:  java Ex04Comparable.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Ex04Comparable {

    static class Student implements Comparable<Student> {
        private final String id;
        private final String name;
        private final double gpa;

        Student(String id, String name, double gpa) {
            this.id = id;
            this.name = name;
            this.gpa = gpa;
        }

        double getGpa() { return gpa; }

        // compareTo returns: negative if this comes first, positive if other
        // comes first, 0 if they're equal in this order.
        @Override
        public int compareTo(Student other) {
            return id.compareTo(other.id);       // natural order: by ID
        }

        @Override
        public String toString() {
            return id + " " + name + " (" + gpa + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println("apple".compareTo("banana"));   // negative: apple comes first
        System.out.println(Integer.compare(7, 3));         // positive: 7 comes after 3

        List<Student> students = new ArrayList<>(List.of(
                new Student("S03", "Nana", 3.6),
                new Student("S01", "Akua", 3.9),
                new Student("S02", "Kwesi", 2.8)));

        Collections.sort(students);                        // uses compareTo
        System.out.println(students);
        System.out.println("Last by ID: " + Collections.max(students));
        System.out.println(new TreeSet<>(students).first());
    }
}
