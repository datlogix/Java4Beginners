// Exercise 2: Bubble sort, binary search on Strings, and Big-O by experiment.
//
// PART A: bubble sort.
//   Write bubbleSort: repeatedly walk through the array, swapping neighbours
//   that are in the wrong order, until a whole pass makes no swaps.
//   Count every COMPARISON in the static variable comparisons.
//
// PART B: binary search on Strings.
//   Write binarySearch for a sorted String[]: compare with compareTo, which is
//   negative, zero or positive (Module 12). Return the index, or -1.
//   Count comparisons here too.
//
// PART C: experiment.
//   main() sorts random arrays of 1,000, 2,000 and 4,000 numbers, and searches
//   sorted word lists of 1,000, 2,000 and 4,000 words, printing the number of
//   comparisons. When your methods work, run it and answer in comments at the
//   bottom of this file:
//     1. When the array size DOUBLES, roughly what happens to bubble sort's
//        comparisons? So what is its Big-O?
//     2. When the word list DOUBLES, what happens to binary search's comparisons?
//        So what is its Big-O?
//     3. Bubble sort stops early if a pass makes no swaps. How many comparisons
//        does it make on an array that's ALREADY sorted? (Try it!)
//
// Run it with:  java Exercise2.java

import java.util.Arrays;
import java.util.Random;

public class Exercise2 {

    static long comparisons = 0;

    static void bubbleSort(int[] a) {
        // TODO
    }

    static int binarySearch(String[] sorted, String target) {
        return -1;   // TODO
    }

    // ---------------- Don't change anything below this line ----------------

    public static void main(String[] args) {
        int[] small = {5, 1, 4, 2, 8, 3};
        bubbleSort(small);
        System.out.println((Arrays.toString(small).equals("[1, 2, 3, 4, 5, 8]") ? "PASS" : "FAIL")
                + "  bubbleSort " + Arrays.toString(small));
        String[] words = {"accra", "bolga", "cape coast", "ho", "koforidua", "kumasi", "sunyani", "tamale", "wa"};
        System.out.println((binarySearch(words, "kumasi") == 5 ? "PASS" : "FAIL") + "  find kumasi");
        System.out.println((binarySearch(words, "accra") == 0 ? "PASS" : "FAIL") + "  find the first word");
        System.out.println((binarySearch(words, "wa") == 8 ? "PASS" : "FAIL") + "  find the last word");
        System.out.println((binarySearch(words, "lagos") == -1 ? "PASS" : "FAIL") + "  lagos isn't there");

        Random random = new Random(16);
        System.out.println();
        for (int size : new int[]{1_000, 2_000, 4_000}) {
            int[] a = random.ints(size, 0, 100_000).toArray();
            comparisons = 0;
            bubbleSort(a);
            System.out.printf("bubble sort, %,6d numbers: %,12d comparisons%n", size, comparisons);
        }
        for (int size : new int[]{1_000, 2_000, 4_000}) {
            String[] list = new String[size];
            for (int i = 0; i < size; i++) {
                list[i] = String.format("word%06d", i);
            }
            comparisons = 0;
            binarySearch(list, list[size - 1]);
            System.out.printf("binary search, %,6d words: %,3d comparisons%n", size, comparisons);
        }
    }
}

// Answers to Part C:
// 1.
// 2.
// 3.
