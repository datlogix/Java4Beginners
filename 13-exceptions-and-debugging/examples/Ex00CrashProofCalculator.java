// Example 0: the hook. The same calculator, twice.
// Round 1 crashes the moment you type something unexpected.
// Round 2 survives ANYTHING you throw at it.
// Run it with:  java Ex00CrashProofCalculator.java
// Try typing: ten, then 7 / 0, then 8 % 3, then 2 ^ 10, then just Enter.

import java.util.Scanner;

public class Ex00CrashProofCalculator {

    static final Scanner IN = new Scanner(System.in);

    static double calculate(String line) {
        String[] parts = line.trim().split(" +");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Type a sum like: 12 * 4");
        }
        double a = Double.parseDouble(parts[0]);
        double b = Double.parseDouble(parts[2]);
        return switch (parts[1]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("You can't divide by zero.");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + parts[1]);
        };
    }

    public static void main(String[] args) {
        System.out.println("=== ROUND 1: no safety net ===");
        System.out.print("Sum: ");
        String line = IN.nextLine();
        if (!line.isBlank()) {
            try {
                System.out.println("= " + calculate(line));
            } catch (RuntimeException e) {
                // Round 1 "crashes": we print what Java would have printed, then carry on to Round 2.
                System.out.println("CRASH! " + e);
                System.out.println("(Without a try/catch, the program would end here.)");
            }
        }

        System.out.println();
        System.out.println("=== ROUND 2: crash-proof (type quit to stop) ===");
        while (true) {
            System.out.print("Sum: ");
            line = IN.nextLine();
            if (line.trim().equalsIgnoreCase("quit")) {
                break;
            }
            try {
                System.out.println("= " + calculate(line));
            } catch (NumberFormatException e) {
                System.out.println("Those don't look like numbers. Try: 12 * 4");
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Goodbye. Nothing crashed.");
    }
}
