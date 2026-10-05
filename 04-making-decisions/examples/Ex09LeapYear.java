// Example 9: a famous condition, written three ways, plus the ternary operator.
// A year is a leap year if it divides by 4, EXCEPT century years,
// which must also divide by 400. (2000 was a leap year; 1900 wasn't.)
// Run it with:  java Ex09LeapYear.java

import java.util.Scanner;

public class Ex09LeapYear {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Year? ");
        int year = Integer.parseInt(in.nextLine().trim());

        // Way 1: an else-if chain, one rule at a time.
        boolean leap;
        if (year % 400 == 0) {
            leap = true;
        } else if (year % 100 == 0) {
            leap = false;
        } else if (year % 4 == 0) {
            leap = true;
        } else {
            leap = false;
        }
        System.out.println("Way 1: " + leap);

        // Way 2: one boolean expression. Brackets make the meaning clear.
        boolean leap2 = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        System.out.println("Way 2: " + leap2);

        // The ternary operator:  condition ? valueIfTrue : valueIfFalse
        int daysInFebruary = leap2 ? 29 : 28;
        String message = leap2 ? "is a leap year" : "is not a leap year";
        System.out.println(year + " " + message + "; February has " + daysInFebruary + " days.");
    }
}
