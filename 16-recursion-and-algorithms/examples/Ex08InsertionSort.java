// Example 8: insertion sort. Take each item in turn and slide it left into its
// place among the items already sorted, like sorting a hand of cards.
// Run it with:  java Ex08InsertionSort.java

import java.util.Arrays;

public class Ex08InsertionSort {

    static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int current = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > current) {     // shift bigger items one place right
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = current;                    // drop the item into the gap
            System.out.println("  after inserting " + current + ": " + Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        int[] a = {29, 10, 14, 37, 13, 5};
        System.out.println("Start:  " + Arrays.toString(a));
        insertionSort(a);
        System.out.println("Sorted: " + Arrays.toString(a));
    }
}
