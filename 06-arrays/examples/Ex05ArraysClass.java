// Example 5: java.util.Arrays has ready-made tools for arrays.
// Run it with:  java Ex05ArraysClass.java

import java.util.Arrays;

public class Ex05ArraysClass {
    public static void main(String[] args) {
        int[] numbers = {42, 7, 19, 3, 88, 21};

        System.out.println(Arrays.toString(numbers));

        int[] copy = Arrays.copyOf(numbers, numbers.length);   // a real, separate copy
        Arrays.sort(copy);                                       // sorts in place, smallest first
        System.out.println("Sorted copy: " + Arrays.toString(copy));
        System.out.println("Original:    " + Arrays.toString(numbers));

        int[] bigger = Arrays.copyOf(numbers, 8);                // a copy with room for 2 more
        System.out.println("Bigger copy: " + Arrays.toString(bigger));

        String[] names = {"Yaw", "Ama", "Kojo", "Efua"};
        Arrays.sort(names);                                      // alphabetical
        System.out.println(Arrays.toString(names));

        char[] row = new char[10];
        Arrays.fill(row, '-');                                   // set every value
        System.out.println(new String(row));

        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println(a == b);                              // false: two different arrays
        System.out.println(Arrays.equals(a, b));                 // true: same values
    }
}
