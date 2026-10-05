// Example 0: the hook. Roll two dice ONE MILLION times, count every total,
// and draw a bar chart of the results, in about a tenth of a second.
// Run it with:  java Ex00DiceMillion.java

import java.util.Random;

public class Ex00DiceMillion {
    public static void main(String[] args) {
        Random random = new Random();
        int rolls = 1_000_000;
        int[] counts = new int[13];          // counts[7] = how many times we rolled a 7

        long start = System.nanoTime();
        for (int i = 0; i < rolls; i++) {
            int total = (random.nextInt(6) + 1) + (random.nextInt(6) + 1);
            counts[total]++;
        }
        long millis = (System.nanoTime() - start) / 1_000_000;

        System.out.printf("Rolled two dice %,d times in %d ms.%n%n", rolls, millis);
        for (int total = 2; total <= 12; total++) {
            int stars = counts[total] / 4000;
            System.out.printf("%2d | %-42s %6.2f%%%n", total, "*".repeat(stars), counts[total] * 100.0 / rolls);
        }
    }
}
