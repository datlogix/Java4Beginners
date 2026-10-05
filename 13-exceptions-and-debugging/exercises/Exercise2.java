// Exercise 2: Debugging challenge.
//
// This program has FOUR bugs. It never crashes; it just gives wrong answers.
// With these marks, the correct output is:
//
//     Marks: [72, 45, 88, 50, 61, 94, 39]
//     Average: 64.14
//     Highest: 94
//     Lowest: 39
//     Passed (50 or more): 5
//
// Your job: find and fix every bug USING THE VS CODE DEBUGGER, not by staring
// at the code. For each bug:
//   1. Set a breakpoint (click to the left of a line number: a red dot appears).
//   2. Click "Debug" (just above main), or press F5.
//   3. When it stops, look at the VARIABLES panel. Step through with F10 (step
//      over) and F11 (step into a method), watching the values change.
//   4. When a value goes wrong, you've found the bug's line.
//
// Then, in a comment above each fix, write:
//     BUG n: line __. What was wrong. Which variable showed it in the debugger.
//
// Run it with:  java Exercise2.java   (or debug it in VS Code)

import java.util.Arrays;

public class Exercise2 {

    static double average(int[] marks) {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return total / marks.length;
    }

    static int highest(int[] marks) {
        int best = marks[0];
        for (int m : marks) {
            if (m < best) {
                best = m;
            }
        }
        return best;
    }

    static int lowest(int[] marks) {
        int worst = marks[0];
        for (int i = 1; i < marks.length - 1; i++) {
            if (marks[i] < worst) {
                worst = marks[i];
            }
        }
        return worst;
    }

    static int countPassed(int[] marks) {
        int count = 0;
        for (int m : marks) {
            if (m > 50) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] marks = {72, 45, 88, 50, 61, 94, 39};
        System.out.println("Marks: " + Arrays.toString(marks));
        System.out.printf("Average: %.2f%n", average(marks));
        System.out.println("Highest: " + highest(marks));
        System.out.println("Lowest: " + lowest(marks));
        System.out.println("Passed (50 or more): " + countPassed(marks));
    }
}
