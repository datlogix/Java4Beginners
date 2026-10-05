// Exercise 1: Loop patterns.
//
// Ask for a size n (keep asking until it's between 1 and 9), then print
// the three patterns below. Sample run with n = 4:
//
//     Size (1-9)? 12
//     Size (1-9)? 4
//
//     Times table for 4:
//      1 x 4 =  4
//      2 x 4 =  8
//      ...
//     10 x 4 = 40
//
//     Number staircase:
//     1
//     1 2
//     1 2 3
//     1 2 3 4
//
//     Hollow square:
//     # # # #
//     #     #
//     #     #
//     # # # #
//
// Rules:
//   1. Use a while loop for the input check.
//   2. Use a for loop for the times table (always 10 lines), lined up with printf.
//   3. Use NESTED for loops for the staircase and the square. No repeat()!
//      (Each "cell" of the square is "# " or two spaces. The left and right
//      columns, and the top and bottom rows, are #; everything else is blank.)
//   4. Stretch: print the staircase upside down as well.
//
// Run it with:  java Exercise1.java

import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Size (1-9)? ");
        int n = Integer.parseInt(in.nextLine().trim());
        // TODO: keep asking while n is out of range

        System.out.println();
        System.out.println("Times table for " + n + ":");
        // TODO: the times table

        System.out.println();
        System.out.println("Number staircase:");
        // TODO: the staircase (nested loops)

        System.out.println();
        System.out.println("Hollow square:");
        // TODO: the hollow square (nested loops, and an if inside)
    }
}
