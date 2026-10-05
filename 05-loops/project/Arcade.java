// Module 5 Project: The Games Arcade
// Author: YOUR NAME
//
// Follow the TODOs. Get each game working before starting the next.
// Run it with:  java Arcade.java

import java.util.Random;
import java.util.Scanner;

public class Arcade {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        int gamesPlayed = 0;
        String choice;

        System.out.println("*** WELCOME TO THE JAVA ARCADE ***");

        do {
            System.out.println();
            System.out.println("1) Guess the Number");
            System.out.println("2) Dice Duel");
            System.out.println("3) Times Table Challenge");
            System.out.println("4) Quit");
            System.out.print("Choose a game: ");
            choice = in.nextLine().trim();

            if (choice.equals("1")) {
                // ===== GUESS THE NUMBER =====
                int secret = random.nextInt(100) + 1;
                System.out.println("I'm thinking of a number from 1 to 100.");
                // TODO: loop until the player guesses it, saying "Too high" or
                //       "Too low" each time, and counting the guesses.
                //       At the end, print how many guesses it took.
                gamesPlayed++;

            } else if (choice.equals("2")) {
                // ===== DICE DUEL =====
                // TODO: the player and the computer take turns rolling a dice
                //       (press Enter to roll). First to a total of 20 or more wins.
                gamesPlayed++;

            } else if (choice.equals("3")) {
                // ===== TIMES TABLE CHALLENGE =====
                // TODO: ask 5 random times-table questions (numbers 2 to 12),
                //       keep score, and print the score at the end.
                gamesPlayed++;

            } else if (!choice.equals("4")) {
                System.out.println("Please choose 1, 2, 3 or 4.");
            }
        } while (!choice.equals("4"));

        System.out.println("Thanks for playing! Games played: " + gamesPlayed);
    }
}
