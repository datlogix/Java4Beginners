// Example 7: parallel arrays. Values at the same index belong together.
// Run it with:  java Ex07ParallelArrays.java

public class Ex07ParallelArrays {
    public static void main(String[] args) {
        String[] students = {"Akua", "Kwesi", "Nana", "Selorm"};
        int[] marks = {78, 64, 91, 55};

        System.out.printf("%-10s %5s %s%n", "Name", "Mark", "Bar");
        for (int i = 0; i < students.length; i++) {
            System.out.printf("%-10s %5d %s%n", students[i], marks[i], "#".repeat(marks[i] / 10));
        }

        // Find the top student: the index of the best mark gives the name too
        int best = 0;
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > marks[best]) {
                best = i;
            }
        }
        System.out.println("Top student: " + students[best]);
        // Parallel arrays work, but they're fragile: sort one and they no longer
        // line up. Module 10 shows a much better way (a class with both fields).
    }
}
