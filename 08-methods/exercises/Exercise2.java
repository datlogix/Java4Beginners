// Exercise 2: Restructure a long program into methods.
//
// main() below works, but it's one long block with the same code repeated.
// Restructure it so that main() is SHORT and reads like a table of contents,
// using these methods (write each one, then replace the matching code in main
// with a call to it):
//
//     static void printDivider(int width)
//         prints a line of '=' signs of that width
//     static void printHeading(String title, int width)
//         prints the title centred between two dividers
//     static double average(int[] values)
//     static int countAtLeast(int[] values, int threshold)
//         how many values are >= threshold
//     static String grade(double average)
//         "Distinction" (70+), "Credit" (60+), "Pass" (50+) or "Fail"
//     static void printStudent(String name, int[] marks)
//         prints one student's line, using average() and grade()
//
// Rules:
//   1. The program's OUTPUT must be exactly the same before and after.
//      Run it now and save a copy of the output to compare against.
//   2. When you're finished, main() should be no more than about 10 lines,
//      and no method should contain code copied from another.
//   3. Give every method a one-line Javadoc comment (/** ... */).
//
// Run it with:  java Exercise2.java

public class Exercise2 {
    public static void main(String[] args) {
        String[] names = {"Abla", "Kwesi", "Naa"};
        int[][] marks = {
            {72, 81, 65, 90},
            {55, 48, 62, 51},
            {38, 52, 44, 61},
        };
        int width = 40;

        System.out.println("=".repeat(width));
        String title = "END OF TERM REPORT";
        int padding = (width - title.length()) / 2;
        System.out.println(" ".repeat(padding) + title);
        System.out.println("=".repeat(width));

        // Student 0
        int total = 0;
        for (int m : marks[0]) {
            total += m;
        }
        double avg = (double) total / marks[0].length;
        String g;
        if (avg >= 70) {
            g = "Distinction";
        } else if (avg >= 60) {
            g = "Credit";
        } else if (avg >= 50) {
            g = "Pass";
        } else {
            g = "Fail";
        }
        int passes = 0;
        for (int m : marks[0]) {
            if (m >= 50) {
                passes++;
            }
        }
        System.out.printf("%-8s avg %5.1f  %-11s (%d/%d papers passed)%n", names[0], avg, g, passes, marks[0].length);

        // Student 1
        total = 0;
        for (int m : marks[1]) {
            total += m;
        }
        avg = (double) total / marks[1].length;
        if (avg >= 70) {
            g = "Distinction";
        } else if (avg >= 60) {
            g = "Credit";
        } else if (avg >= 50) {
            g = "Pass";
        } else {
            g = "Fail";
        }
        passes = 0;
        for (int m : marks[1]) {
            if (m >= 50) {
                passes++;
            }
        }
        System.out.printf("%-8s avg %5.1f  %-11s (%d/%d papers passed)%n", names[1], avg, g, passes, marks[1].length);

        // Student 2
        total = 0;
        for (int m : marks[2]) {
            total += m;
        }
        avg = (double) total / marks[2].length;
        if (avg >= 70) {
            g = "Distinction";
        } else if (avg >= 60) {
            g = "Credit";
        } else if (avg >= 50) {
            g = "Pass";
        } else {
            g = "Fail";
        }
        passes = 0;
        for (int m : marks[2]) {
            if (m >= 50) {
                passes++;
            }
        }
        System.out.printf("%-8s avg %5.1f  %-11s (%d/%d papers passed)%n", names[2], avg, g, passes, marks[2].length);

        System.out.println("=".repeat(width));
    }
}
