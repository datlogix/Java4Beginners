// Example 7: accumulator patterns: total, count, largest, smallest.
// Run it with:  java Ex07Accumulators.java

import java.util.Random;

public class Ex07Accumulators {
    public static void main(String[] args) {
        Random random = new Random(42);   // 42 is a "seed": same numbers every run

        int total = 0;                    // a running total starts at 0
        int evens = 0;                    // a counter starts at 0
        int largest = Integer.MIN_VALUE;  // so the first number is always bigger
        int smallest = Integer.MAX_VALUE; // so the first number is always smaller

        System.out.print("Ten random marks:");
        for (int i = 0; i < 10; i++) {
            int mark = random.nextInt(101);           // 0 to 100
            System.out.print(" " + mark);

            total += mark;
            if (mark % 2 == 0) {
                evens++;
            }
            largest = Math.max(largest, mark);
            smallest = Math.min(smallest, mark);
        }
        System.out.println();
        System.out.println("Total: " + total);
        System.out.println("Average: " + total / 10.0);
        System.out.println("Even marks: " + evens);
        System.out.println("Highest: " + largest + ", lowest: " + smallest);

        // A product starts at 1, not 0. 10! = 10 x 9 x 8 x ... x 1
        long factorial = 1;
        for (int n = 1; n <= 10; n++) {
            factorial *= n;
        }
        System.out.println("10! = " + factorial);
    }
}
