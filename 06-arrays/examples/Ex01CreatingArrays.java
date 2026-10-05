// Example 1: an array holds a fixed number of values of ONE type, in order.
// Run it with:  java Ex01CreatingArrays.java

import java.util.Arrays;

public class Ex01CreatingArrays {
    public static void main(String[] args) {
        // Way 1: list the values in braces
        int[] marks = {72, 85, 64, 90, 58};
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};
        double[] prices = {4.50, 12.00, 0.99};

        // Way 2: say how many, and fill them in later
        int[] scores = new int[4];          // four ints, all starting at 0
        String[] names = new String[3];     // three Strings, all starting as null
        boolean[] passed = new boolean[3];  // three booleans, all starting as false

        System.out.println("marks has " + marks.length + " values");     // length: no brackets!
        System.out.println("days has " + days.length + " values");
        System.out.println("prices has " + prices.length + " values");

        // Printing an array directly shows a strange code, not the values:
        System.out.println(marks);
        // Arrays.toString gives a readable version:
        System.out.println(Arrays.toString(marks));
        System.out.println(Arrays.toString(scores));
        System.out.println(Arrays.toString(names));
        System.out.println(Arrays.toString(passed));
    }
}
