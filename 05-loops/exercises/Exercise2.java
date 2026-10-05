// Exercise 2: Lab readings statistics.
//
// A lab technician types in a series of readings (decimal numbers), one per
// line, and types "end" when finished. Your program prints statistics.
//
// Sample run:
//
//     Enter readings, one per line. Type end to finish.
//     Reading 1: 4.8
//     Reading 2: 5.1
//     Reading 3: -2
//       Readings can't be negative. Ignored.
//     Reading 3: 4.95
//     Reading 4: 5.3
//     Reading 5: end
//
//     Readings:  4
//     Total:     20.15
//     Average:   5.04
//     Lowest:    4.80
//     Highest:   5.30
//     Range:     0.50
//     Above 5.0: 2
//
// Rules:
//   1. Use a sentinel loop that stops on "end" (any capitals).
//   2. Negative readings print the warning and are NOT counted. Notice the
//      reading number doesn't go up for an ignored reading.
//   3. Keep a count, a running total, the lowest and the highest, and a count
//      of readings above 5.0, all inside the loop. (Don't store the readings:
//      you can't yet, and you don't need to.)
//   4. Print every decimal with 2 places. Range = highest - lowest.
//   5. If the first thing typed is "end", print "No readings entered." instead
//      of the statistics (and don't divide by zero!).
//
// Run it with:  java Exercise2.java

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int count = 0;
        double total = 0;
        // TODO: variables for the lowest, the highest, and the count above 5.0
        //       (Hint: start lowest at Double.MAX_VALUE and highest at -Double.MAX_VALUE)

        System.out.println("Enter readings, one per line. Type end to finish.");
        // TODO: the sentinel loop

        // TODO: print the statistics, or "No readings entered."
    }
}
