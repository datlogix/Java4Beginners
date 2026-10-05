// Exercise 1: A neat profile card.
//
// Ask the user four questions, then print a profile card where every line
// is exactly the same width, using printf.
//
// Sample run (what the user types is after each question mark):
//
//     Name? Esi Mensah
//     Age? 19
//     Home town? Cape Coast
//     Height in metres? 1.64
//
//     +------------------------------+
//     | Name:      Esi Mensah        |
//     | Age:       19 (228 months)   |
//     | Town:      CAPE COAST        |
//     | Height:    164 cm            |
//     +------------------------------+
//
// Rules:
//   1. Read every answer with in.nextLine(), and convert numbers with
//      Integer.parseInt / Double.parseDouble (the Fix 2 pattern from Example 5).
//   2. The town is printed in capitals.
//   3. The height is printed in whole centimetres (1.64 m -> 164 cm).
//      Careful: (int) (1.15 * 100) is 114, not 115! Use Math.round instead.
//   4. Every line between the borders is built with ONE printf that uses a
//      width, e.g. "| %-10s %-17s |%n". Work out the widths so the right-hand
//      | lines up. (The border is 32 characters wide.)
//   5. Store the border in a variable so you only type it once.
//
// Run it with:  java Exercise1.java

import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Name? ");
        String name = in.nextLine().trim();
        // TODO: ask for the age, the home town and the height

        // TODO: calculate the age in months and the height in whole centimetres

        String border = "+" + "-".repeat(30) + "+";
        System.out.println();
        System.out.println(border);
        System.out.printf("| %-10s %-17s |%n", "Name:", name);
        // TODO: print the Age, Town and Height lines
        System.out.println(border);
    }
}
