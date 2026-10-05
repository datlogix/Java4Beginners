// Module 4 Project: Choose Your Own Adventure
// Author: YOUR NAME
//
// Follow the TODOs. Run the program after each one, and try EVERY path.
// Run it with:  java Adventure.java

import java.util.Scanner;

public class Adventure {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // --- Introduction ---
        System.out.println("=== THE LAST BUS TO TAMALE ===");
        System.out.print("What's your name, traveller? ");
        String name = in.nextLine().trim();
        int coins = 20;     // the player's money: some choices cost coins

        System.out.println();
        System.out.println(name + ", it's 9 pm at the bus station. The last bus to Tamale");
        System.out.println("leaves in ten minutes, and you've lost your ticket.");
        System.out.println("You have " + coins + " cedis in your pocket.");

        // --- Choice 1 ---
        System.out.println();
        System.out.println("Do you (a) search the waiting room, or (b) talk to the driver?");
        System.out.print("> ");
        String choice = in.nextLine().trim().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("Under a bench you find... a ticket! But is it yours?");
            // TODO: a second choice on this path (keep it, or hand it in?)
            //       with at least two endings

        } else if (choice.equals("b")) {
            System.out.println("The driver frowns. \"A new ticket is 15 cedis.\"");
            // TODO: a second choice on this path that uses the coins variable:
            //       e.g. pay (coins -= 15), bargain, or sing for your fare

        } else {
            System.out.println("You hesitate too long. The bus leaves without you.");
            System.out.println("THE END (try again, and type a or b!)");
        }

        // TODO: make sure EVERY path ends with "THE END", and at least one
        //       ending depends on how many coins the player has left
    }
}
