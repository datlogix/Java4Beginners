// Example 8: the modern switch, with arrows. No break needed, no fall-through,
// several values per case, and it can GIVE BACK a value.
// Run it with:  java Ex08SwitchExpressions.java

import java.util.Scanner;

public class Ex08SwitchExpressions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Month number (1-12)? ");
        int month = Integer.parseInt(in.nextLine().trim());

        // An arrow switch used as a statement:
        switch (month) {
            case 12, 1, 2 -> System.out.println("Harmattan season.");
            case 4, 5, 6, 7 -> System.out.println("Major rainy season (in the south).");
            default -> System.out.println("Some other part of the year.");
        }

        // A switch EXPRESSION: the whole switch works out to a value.
        int days = switch (month) {
            case 4, 6, 9, 11 -> 30;
            case 2 -> 28;                  // ignoring leap years for now
            default -> 31;
        };
        System.out.println("That month has " + days + " days.");

        String name = switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "not a month";
        };
        System.out.println("Month " + month + " is " + name + ".");
    }
}
