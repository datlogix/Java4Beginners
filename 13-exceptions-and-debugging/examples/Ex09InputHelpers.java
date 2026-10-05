// Example 9: reusable, crash-proof input methods. Copy these into any program.
// Run it with:  java Ex09InputHelpers.java

import java.util.Scanner;

public class Ex09InputHelpers {

    static final Scanner IN = new Scanner(System.in);

    /** Asks until the user types a whole number from min to max. */
    static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String text = IN.nextLine().trim();
            try {
                int value = Integer.parseInt(text);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("  Please type a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  '" + text + "' isn't a whole number.");
            }
        }
    }

    /** Asks until the user types a decimal number from min to max. */
    static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String text = IN.nextLine().trim();
            try {
                double value = Double.parseDouble(text);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("  Please type a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  '" + text + "' isn't a number.");
            }
        }
    }

    /** Asks until the user types something that isn't blank. */
    static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = IN.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("  Please type something.");
        }
    }

    public static void main(String[] args) {
        String name = readNonEmpty("Patient name: ");
        int age = readInt("Age: ", 0, 120);
        double temp = readDouble("Temperature (C): ", 30, 45);
        System.out.printf("%s, %d, %.1f C%n", name, age, temp);
    }
}
