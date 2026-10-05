// Module 2 Project: Conversion Cheat Sheet
// Author: YOUR NAME
//
// Follow the TODOs in order. Run the program after EACH section.
// Run it with:  java CheatSheet.java
//
// Remember: Java does every calculation. Never type in an answer you
// worked out yourself.

public class CheatSheet {
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("           MY CONVERSION CHEAT SHEET");
        System.out.println("=".repeat(50));

        // --- Temperature ---
        // Formula: F = C * 9 / 5 + 32
        // Careful: C * 9 / 5 with whole numbers uses int division. Use 9.0.
        System.out.println("TEMPERATURE (Celsius -> Fahrenheit)");
        System.out.println("   0 C  =  " + (0 * 9.0 / 5 + 32) + " F    water freezes");
        // TODO: add at least two more temperatures (try 37 and 100)

        System.out.println();

        // --- Distance ---
        // Formula: miles = km * 0.621371
        // TODO: print a heading, then at least three conversions,
        //       rounded to 2 decimal places with Math.round(x * 100) / 100.0

        // --- Time ---
        // Formula: hours = minutes / 60, remaining minutes = minutes % 60
        // TODO: print a heading, then at least three conversions,
        //       e.g. "200 minutes = 3 h 20 min"

        // --- Your choice ---
        // TODO: a fourth section with at least three conversions,
        //       and a comment giving its formula
    }
}
