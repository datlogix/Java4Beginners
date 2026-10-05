// Exercise 1: Resistor colour codes with an enum.
//
// A 4-band resistor shows its value as colours: two DIGIT bands, a MULTIPLIER
// band, and a TOLERANCE band. Yellow-violet-red-gold means 4, 7, x100, +/-5%:
// 4700 ohms, +/-5%.
//
//     Colour   Digit  Multiplier  Tolerance
//     BLACK      0        1           -
//     BROWN      1        10         1%
//     RED        2        100        2%
//     ORANGE     3        1k          -
//     YELLOW     4        10k         -
//     GREEN      5        100k      0.5%
//     BLUE       6        1M       0.25%
//     VIOLET     7        10M       0.1%
//     GREY       8         -       0.05%
//     WHITE      9         -          -
//     GOLD       -        0.1        5%
//     SILVER     -        0.01      10%
//
// 1. Complete the enum BandColour: give each value a digit (-1 where there is
//    none), a multiplier (0 where there is none) and a tolerance (0 where there
//    is none), with a constructor and getters.
// 2. Complete the record ResistorValue's describe() method, which formats the
//    value with k or M where it helps:  "4.7 kOhm +/-5%", "220 Ohm +/-1%",
//    "1 MOhm +/-10%"  (use the helper trim() to drop a ".0").
// 3. Complete decode(), which takes four colour NAMES in any capitals
//    ("yellow", "Violet"...), turns them into BandColours with valueOf, and
//    returns a ResistorValue. It throws IllegalArgumentException (with a clear
//    message) if a colour can't be used in that band: e.g. GOLD as a digit, or
//    WHITE as a multiplier, or BLACK as a tolerance.
//    (BandColour.valueOf("PURPLE") throws IllegalArgumentException by itself.)
//
// Run it with:  java Exercise1.java

public class Exercise1 {

    enum BandColour {
        BLACK, BROWN, RED;       // TODO: all twelve colours, each with (digit, multiplier, tolerance)

        // TODO: the fields, the constructor and the getters
    }

    record ResistorValue(double ohms, double tolerancePercent) {
        String describe() {
            return "TODO";
        }

        private static String trim(double value) {
            String text = String.valueOf(Math.round(value * 100) / 100.0);
            return text.endsWith(".0") ? text.substring(0, text.length() - 2) : text;
        }
    }

    static ResistorValue decode(String band1, String band2, String multiplier, String tolerance) {
        return new ResistorValue(0, 0);   // TODO
    }

    // ---------------- Don't change anything below this line ----------------

    static int passed = 0, failed = 0;

    static void check(String description, Object actual, Object expected) {
        if (String.valueOf(actual).equals(String.valueOf(expected))) {
            passed++;
            System.out.println("PASS  " + description);
        } else {
            failed++;
            System.out.println("FAIL  " + description + ": got " + actual + ", expected " + expected);
        }
    }

    static boolean refuses(Runnable action) {
        try {
            action.run();
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static void main(String[] args) {
        check("twelve colours", BandColour.values().length, 12);
        check("yellow violet red gold", decode("yellow", "violet", "red", "gold").describe(), "4.7 kOhm +/-5%");
        check("red red brown brown", decode("RED", "Red", "brown", "brown").describe(), "220 Ohm +/-1%");
        check("brown black green silver", decode("brown", "black", "green", "silver").describe(), "1 MOhm +/-10%");
        check("brown black black gold", decode("brown", "black", "black", "gold").describe(), "10 Ohm +/-5%");
        check("orange orange gold gold", decode("orange", "orange", "gold", "gold").describe(), "3.3 Ohm +/-5%");
        check("blue grey orange red", decode("blue", "grey", "orange", "red").describe(), "68 kOhm +/-2%");
        check("ohms value", decode("yellow", "violet", "red", "gold").ohms(), 4700.0);
        check("records compare by value",
                decode("red", "red", "brown", "brown").equals(new ResistorValue(220, 1)), true);
        check("gold as a digit refused", refuses(() -> decode("gold", "red", "red", "gold")), true);
        check("white as a multiplier refused", refuses(() -> decode("red", "red", "white", "gold")), true);
        check("black as a tolerance refused", refuses(() -> decode("red", "red", "red", "black")), true);
        check("unknown colour refused", refuses(() -> decode("purple", "red", "red", "gold")), true);
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
