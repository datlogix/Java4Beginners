// Example 6: searching. Linear search checks every item; binary search
// halves a SORTED array each step. Count the comparisons.
// Run it with:  java Ex06LinearVsBinarySearch.java

public class Ex06LinearVsBinarySearch {

    static int comparisons;

    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            comparisons++;
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(int[] a, int target) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                low = mid + 1;            // it must be in the upper half
            } else {
                high = mid - 1;           // it must be in the lower half
            }
        }
        return -1;
    }

    /** The same algorithm, written recursively. */
    static int binarySearch(int[] a, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (a[mid] == target) {
            return mid;
        }
        return a[mid] < target ? binarySearch(a, target, mid + 1, high) : binarySearch(a, target, low, mid - 1);
    }

    public static void main(String[] args) {
        for (int size : new int[]{1_000, 1_000_000, 100_000_000}) {
            int[] a = new int[size];
            for (int i = 0; i < size; i++) {
                a[i] = i * 2;             // sorted even numbers
            }
            int target = a[size - 1];     // the worst case for linear search: the last item
            comparisons = 0;
            linearSearch(a, target);
            int linear = comparisons;
            comparisons = 0;
            binarySearch(a, target);
            System.out.printf("%,12d items: linear %,12d comparisons, binary %2d%n", size, linear, comparisons);
        }
        int[] small = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println("Recursive search for 23: index " + binarySearch(small, 23, 0, small.length - 1));
    }
}
