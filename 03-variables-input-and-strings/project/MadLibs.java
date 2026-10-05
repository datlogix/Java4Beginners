// Module 3 Project: Mad Libs Story Generator
// Author: YOUR NAME
//
// Follow the TODOs in order. Run the program after EACH one.
// Run it with:  java MadLibs.java

import java.util.Scanner;

public class MadLibs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // --- Title ---
        System.out.println("=".repeat(40));
        System.out.println("          MAD LIBS: THE BIG DAY");
        System.out.println("=".repeat(40));
        System.out.println("Answer each question. Don't think too hard!");
        System.out.println();

        // --- Collect the words ---
        System.out.print("A name: ");
        String name = in.nextLine().trim();
        System.out.print("An adjective (a describing word, like 'smelly'): ");
        String adjective = in.nextLine().trim();
        // TODO: ask for at least SIX more words: include at least one noun,
        //       one verb, one place, and TWO numbers (read as text, then
        //       convert with Integer.parseInt)

        // --- Do some calculations with the numbers ---
        // TODO: use your two numbers in at least one calculation that appears
        //       in the story (e.g. a total, a difference, or a price with VAT)

        // --- Tell the story ---
        System.out.println();
        System.out.println("-".repeat(40));
        System.out.println("Once upon a time, " + name + " woke up feeling very " + adjective + ".");
        // TODO: write at least FIVE more sentences that use every word,
        //       with at least two printf lines and one String method
        //       (e.g. toUpperCase for something shouted)
        System.out.println("-".repeat(40));
        System.out.println("THE END");
    }
}
