// Example 7: what happens to arrays and lists passed to a method?
// Java always passes a COPY of the variable. For an array, that's a copy
// of the REFERENCE, so the method can change the array's contents.
// Run it with:  java Ex07PassByValue.java

import java.util.ArrayList;
import java.util.Arrays;

public class Ex07PassByValue {

    static void doubleAll(int[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] *= 2;                 // changes the SAME array main has
        }
    }

    static void replaceArray(int[] values) {
        values = new int[]{0, 0, 0};        // only changes the local copy of the reference
    }

    static void addGreeting(ArrayList<String> list) {
        list.add("hello");                  // lists behave the same way as arrays
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        doubleAll(numbers);
        System.out.println(Arrays.toString(numbers));      // [2, 4, 6]: changed!

        replaceArray(numbers);
        System.out.println(Arrays.toString(numbers));      // [2, 4, 6]: NOT replaced

        ArrayList<String> words = new ArrayList<>();
        addGreeting(words);
        System.out.println(words);                         // [hello]
    }
}
