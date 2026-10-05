// Exercise 2: Cinema seat booking.
//
// A small cinema has 5 rows (A-E) of 8 seats. Store the seats in a
// boolean[][] where true means "taken". Some seats are already taken
// (see the starter code). Then let the user book seats until they type done.
//
// Sample run:
//
//        1 2 3 4 5 6 7 8
//     A  . . X X . . . .
//     B  . . . . . . . .
//     C  X X . . . . X X
//     D  . . . . . . . .
//     E  . . . . . . . .
//     Seat to book (e.g. C4), or done: c4
//     Booked C4.
//     Seat to book (e.g. C4), or done: A3
//     Sorry, A3 is already taken.
//     Seat to book (e.g. C4), or done: F9
//     There's no seat F9.
//     Seat to book (e.g. C4), or done: done
//
//        1 2 3 4 5 6 7 8
//     A  . . X X . . . .
//     B  . . . . . . . .
//     C  X X . X . . X X
//     D  . . . . . . . .
//     E  . . . . . . . .
//     You booked 1 seat(s). 33 seats are still free.
//
// Hints:
//   - "c4".toUpperCase().charAt(0) - 'A' turns the letter into a row index (C -> 2).
//   - Integer.parseInt(text.substring(1)) - 1 turns "4" into a column index (3).
//   - Check that the row and column are inside the grid BEFORE you use them,
//     or you'll get an ArrayIndexOutOfBoundsException.
//   - You'll print the map twice. (Module 8 will show you how to write the
//     printing code once and use it twice. For now, it's OK to repeat it.)
//
// Run it with:  java Exercise2.java

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean[][] taken = new boolean[5][8];
        taken[0][2] = true;
        taken[0][3] = true;
        taken[2][0] = true;
        taken[2][1] = true;
        taken[2][6] = true;
        taken[2][7] = true;

        int booked = 0;

        // TODO: print the map

        // TODO: the booking loop

        // TODO: print the map again, then the summary
    }
}
