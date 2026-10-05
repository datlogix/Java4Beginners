// Example 9: merge sort. Split the array in half, sort each half (recursively!),
// then MERGE the two sorted halves. Much faster on big arrays.
// Run it with:  java Ex09MergeSort.java

import java.util.Arrays;

public class Ex09MergeSort {

    static int[] mergeSort(int[] a, int depth) {
        System.out.println("  ".repeat(depth) + "sort " + Arrays.toString(a));
        if (a.length <= 1) {
            return a;                                                   // base case: already sorted
        }
        int mid = a.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(a, 0, mid), depth + 1);
        int[] right = mergeSort(Arrays.copyOfRange(a, mid, a.length), depth + 1);
        int[] merged = merge(left, right);
        System.out.println("  ".repeat(depth) + "merged " + Arrays.toString(merged));
        return merged;
    }

    /** Combines two SORTED arrays into one sorted array. */
    static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];          // take the smaller front item
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];              // copy whatever is left over
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] sorted = mergeSort(new int[]{29, 10, 14, 37, 13, 5}, 0);
        System.out.println("Sorted: " + Arrays.toString(sorted));
    }
}
