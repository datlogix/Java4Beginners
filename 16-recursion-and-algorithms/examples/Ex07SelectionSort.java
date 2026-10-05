// Example 7: selection sort. Find the smallest, put it first; find the next
// smallest, put it second; and so on.
// Run it with:  java Ex07SelectionSort.java

import java.util.Arrays;

public class Ex07SelectionSort {

    static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < a.length; j++) {     // find the smallest in the unsorted part
                if (a[j] < a[smallest]) {
                    smallest = j;
                }
            }
            int temp = a[i];                             // swap it into place
            a[i] = a[smallest];
            a[smallest] = temp;
            System.out.println("  after pass " + (i + 1) + ": " + Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        int[] a = {29, 10, 14, 37, 13, 5};
        System.out.println("Start:  " + Arrays.toString(a));
        selectionSort(a);
        System.out.println("Sorted: " + Arrays.toString(a));
    }
}
