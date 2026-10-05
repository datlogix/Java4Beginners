// Example 10: a well-structured program. main reads like a table of
// contents, and each method does ONE job that its name describes.
// Run it with:  java Ex10TableOfContents.java

import java.util.Scanner;

public class Ex10TableOfContents {

    static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcome();
        int count = askInt("How many temperatures? ", 1, 10);
        double[] temps = readTemperatures(count);
        printReport(temps);
    }

    static void printWelcome() {
        System.out.println("=== Temperature Report ===");
    }

    /** Asks until the user types a whole number from low to high. */
    static int askInt(String prompt, int low, int high) {
        while (true) {
            System.out.print(prompt);
            int value = Integer.parseInt(IN.nextLine().trim());
            if (value >= low && value <= high) {
                return value;
            }
            System.out.println("Please type a number from " + low + " to " + high + ".");
        }
    }

    static double[] readTemperatures(int count) {
        double[] temps = new double[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Temperature " + (i + 1) + ": ");
            temps[i] = Double.parseDouble(IN.nextLine().trim());
        }
        return temps;
    }

    static void printReport(double[] temps) {
        System.out.printf("Average: %.1f C%n", average(temps));
        System.out.printf("Highest: %.1f C%n", max(temps));
    }

    static double average(double[] values) {
        double total = 0;
        for (double v : values) {
            total += v;
        }
        return total / values.length;
    }

    static double max(double[] values) {
        double best = values[0];
        for (double v : values) {
            best = Math.max(best, v);
        }
        return best;
    }
}
