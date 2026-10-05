// Example 8: methods that take and return arrays and lists.
// Run it with:  java Ex08ArraysAndListsInMethods.java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex08ArraysAndListsInMethods {

    static double average(int[] values) {
        if (values.length == 0) {
            return 0;
        }
        int total = 0;
        for (int v : values) {
            total += v;
        }
        return (double) total / values.length;
    }

    static int[] squares(int n) {                   // returns a NEW array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (i + 1) * (i + 1);
        }
        return result;
    }

    static List<String> longWords(List<String> words, int minLength) {
        List<String> result = new ArrayList<>();
        for (String w : words) {
            if (w.length() >= minLength) {
                result.add(w);
            }
        }
        return result;                              // the original list is unchanged
    }

    public static void main(String[] args) {
        int[] marks = {72, 85, 64, 90};
        System.out.println("Average: " + average(marks));
        System.out.println("Squares: " + Arrays.toString(squares(6)));

        List<String> words = List.of("Java", "is", "a", "programming", "language");
        System.out.println("Long words: " + longWords(words, 4));
    }
}
