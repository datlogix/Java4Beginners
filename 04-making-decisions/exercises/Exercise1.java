// Exercise 1: Electricity bill calculator.
//
// A power company bills customers in BANDS: each band of units has its own
// price, and you pay each band's price only for the units that fall in it.
//
//                        DOMESTIC     COMMERCIAL
//     First 100 units    GHS 1.00     GHS 2.00   per unit
//     Units 101-200      GHS 2.50     GHS 4.50   per unit
//     Units 201-500      GHS 4.00     GHS 6.00   per unit
//     Above 500 units    GHS 6.00     GHS 7.00   per unit
//
// Example: a DOMESTIC customer who uses 350 units pays
//     100 x 1.00  +  100 x 2.50  +  150 x 4.00  =  GHS 950.00
//
// Sample run:
//
//     Customer name? Akosua Darko
//     Previous meter reading? 12040
//     Current meter reading? 12390
//     Connection type (domestic/commercial)? Domestic
//
//     ===== ELECTRICITY BILL =====
//     Customer:     Akosua Darko
//     Connection:   DOMESTIC
//     Units used:   350
//     Amount due:   GHS 950.00
//     ============================
//
// Rules:
//   1. Units used = current reading - previous reading.
//   2. If the current reading is LOWER than the previous one, print
//      "Error: the current reading can't be lower than the previous one."
//      and print no bill.
//   3. Accept "domestic" or "commercial" in any capitals. Anything else
//      prints "Error: unknown connection type." and no bill.
//   4. Use final constants for all eight prices, so a price change only
//      needs one edit.
//   5. Check your program with 0, 100, 150, 200, 500 and 600 units for
//      both types. Work out at least three of the answers on paper first.
//      (600 domestic units cost GHS 2150.00.)
//
// Run it with:  java Exercise1.java

import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final double DOMESTIC_BAND_1 = 1.00;
        // TODO: the other seven price constants

        System.out.print("Customer name? ");
        String name = in.nextLine().trim();
        // TODO: read the previous reading, the current reading and the type

        // TODO: check the readings and the connection type

        // TODO: work out the amount, band by band
        //   Hint: if units > 500, the bill is the full cost of bands 1-3
        //   (100, 100 and 300 units) plus (units - 500) at the band 4 price.

        // TODO: print the bill
    }
}
