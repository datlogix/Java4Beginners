// Example 10: a race between sorting algorithms on bigger and bigger arrays.
// Watch how the times GROW: that growth rate is what Big-O describes.
// Run it with:  java Ex10SortingRace.java

import java.util.Arrays;
import java.util.Random;

public class Ex10SortingRace {

    static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int current = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > current) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = current;
        }
    }

    static void mergeSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
        int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
        mergeSort(left);
        mergeSort(right);
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            a[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) a[k++] = left[i++];
        while (j < right.length) a[k++] = right[j++];
    }

    static double time(Runnable job) {
        long start = System.nanoTime();
        job.run();
        return (System.nanoTime() - start) / 1e6;
    }

    public static void main(String[] args) {
        Random random = new Random(1);
        // Warm-up: Java speeds code up as it runs it (the "JIT compiler"), so
        // run each sort a few times first, or the first timings are unfair.
        for (int i = 0; i < 5; i++) {
            int[] w = random.ints(5_000, 0, 1_000_000).toArray();
            insertionSort(w.clone());
            mergeSort(w.clone());
            Arrays.sort(w.clone());
        }
        System.out.printf("%10s %14s %12s %14s%n", "size", "insertion ms", "merge ms", "Arrays.sort ms");
        for (int size : new int[]{2_000, 4_000, 8_000, 16_000, 32_000, 64_000}) {
            int[] data = random.ints(size, 0, 1_000_000).toArray();
            int[] a = data.clone(), b = data.clone(), c = data.clone();
            double t1 = time(() -> insertionSort(a));
            double t2 = time(() -> mergeSort(b));
            double t3 = time(() -> Arrays.sort(c));
            System.out.printf("%,10d %14.1f %12.1f %14.1f%n", size, t1, t2, t3);
        }
        System.out.println("Doubling the size roughly QUADRUPLES insertion sort's time (O(n^2)),");
        System.out.println("but only slightly more than doubles merge sort's (O(n log n)).");
    }
}
