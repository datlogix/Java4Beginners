// Example 10: a program with a BUG, but no crash. It just gives the wrong
// answer. Use the VS Code debugger to find it (the README walks you through).
//
// The average of 70, 85 and 90 should be 81.67. This program says 87.50.
// Run it with:  java Ex10BuggyAverage.java

public class Ex10BuggyAverage {

    static double average(int[] values) {
        int total = 0;
        for (int i = 1; i < values.length; i++) {
            total += values[i];
        }
        return (double) total / (values.length - 1);
    }

    public static void main(String[] args) {
        int[] marks = {70, 85, 90};
        double avg = average(marks);
        System.out.printf("Average: %.2f%n", avg);
    }
}
