// Exercise 2: University grade calculator.
//
// Many universities turn a percentage mark into a letter grade and a
// grade point, using a table like this one:
//
//     Mark      Grade   Grade point   Remark
//     80-100    A       4.0           Excellent
//     75-79     B+      3.5           Very good
//     70-74     B       3.0           Good
//     65-69     C+      2.5           Fairly good
//     60-64     C       2.0           Average
//     55-59     D+      1.5           Below average
//     50-54     D       1.0           Pass
//     0-49      F       0.0           Fail
//
// Sample runs:
//
//     Course code? eee227
//     Mark (0-100)? 77
//     EEE227: 77 -> B+ (3.5) Very good
//
//     Course code? bet104
//     Mark (0-100)? 105
//     Error: a mark must be between 0 and 100.
//
// Rules:
//   1. Reject marks below 0 or above 100 with the error message, and print
//      nothing else.
//   2. Use an else-if chain to choose the GRADE from the mark.
//   3. Then use a switch EXPRESSION on the grade (a String) to choose the
//      grade point, and another for the remark. (Yes, you could do it all in
//      the chain. The point is to practise switch.)
//   4. Print the course code in capitals, and the grade point with one
//      decimal place (printf "%.1f").
//   5. Finally: if the grade is D or D+, also print
//      "You passed, but consider a resit to raise your average."
//      Use ONE if with || to do this.
//
// Run it with:  java Exercise2.java

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Course code? ");
        String course = in.nextLine().trim().toUpperCase();
        System.out.print("Mark (0-100)? ");
        int mark = Integer.parseInt(in.nextLine().trim());

        // TODO: reject impossible marks

        // TODO: choose the grade with an else-if chain

        // TODO: choose the grade point and the remark with switch expressions

        // TODO: print the result line, and the resit advice when it applies
    }
}
