// Example 11: the computer guesses YOUR number, and never needs more than 7 tries.
// Think of a number from 1 to 100, then run:  java Ex11ComputerGuesses.java

import java.util.Scanner;

public class Ex11ComputerGuesses {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int low = 1, high = 100, tries = 0;
        System.out.println("Think of a number from 1 to 100. Answer h (too high), l (too low) or c (correct).");

        while (low <= high) {
            int guess = (low + high) / 2;       // always guess the middle
            tries++;
            System.out.print("Is it " + guess + "? ");
            String answer = in.nextLine().trim().toLowerCase();

            if (answer.equals("c")) {
                System.out.println("Got it in " + tries + " tries!");
                return;                         // leave main: the program ends
            } else if (answer.equals("h")) {
                high = guess - 1;               // it's below the guess
            } else if (answer.equals("l")) {
                low = guess + 1;                // it's above the guess
            } else {
                System.out.println("Please type h, l or c.");
                tries--;
            }
        }
        System.out.println("Hmm, your answers don't add up. Did you change your number?");
    }
}
