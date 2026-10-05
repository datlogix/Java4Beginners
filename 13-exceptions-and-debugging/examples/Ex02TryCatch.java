// Example 2: try ... catch. Run the risky code in "try"; if it throws,
// jump to the matching "catch" instead of crashing.
// Run it with:  java Ex02TryCatch.java  (type abc, then 12)

import java.util.Scanner;

public class Ex02TryCatch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("How old are you? ");
        String text = in.nextLine();
        try {
            int age = Integer.parseInt(text.trim());      // might throw
            System.out.println("Next year you'll be " + (age + 1));   // skipped if it threw
        } catch (NumberFormatException e) {
            System.out.println("'" + text + "' isn't a whole number.");
            System.out.println("Java's message was: " + e.getMessage());
        }
        System.out.println("The program carries on either way.");

        // A loop + try/catch = keep asking until it works
        int number;
        while (true) {
            System.out.print("Type a whole number: ");
            try {
                number = Integer.parseInt(in.nextLine().trim());
                break;                                       // only reached if parseInt succeeded
            } catch (NumberFormatException e) {
                System.out.println("  Not a whole number. Try again.");
            }
        }
        System.out.println("Thanks: " + number + " squared is " + number * number);
    }
}
