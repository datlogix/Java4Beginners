// Exercise 1: A Fraction class.
//
// Design a class for fractions like 3/4, so that Java can do exact
// fraction arithmetic (no double fuzziness!).
//
// main() is already written: it creates Fractions and checks the results.
// Fill in the Fraction class until every check says PASS.
//
// Requirements:
//   - Two PRIVATE, FINAL int fields: numerator and denominator.
//   - The constructor throws an IllegalArgumentException if the denominator is 0.
//   - Fractions are always stored in LOWEST TERMS, with any minus sign on the
//     numerator: new Fraction(6, -8) stores -3/4. (Divide both by their gcd;
//     you wrote gcd in Module 8.)
//   - Getters for both fields.
//   - plus(Fraction other) and times(Fraction other) return a NEW Fraction.
//     They don't change this one or the other one. (a/b + c/d = (ad + bc)/bd)
//   - toDouble() returns the value as a double.
//   - isEqualTo(Fraction other) returns true if the two are the same value.
//   - toString() gives "3/4", or just "2" if the denominator is 1.
//
// Run it with:  java Exercise1.java

public class Exercise1 {

    static class Fraction {
        // TODO: the fields

        Fraction(int numerator, int denominator) {
            // TODO: check, simplify, store
        }

        int getNumerator() {
            return 0;   // TODO
        }

        int getDenominator() {
            return 1;   // TODO
        }

        Fraction plus(Fraction other) {
            return this;   // TODO
        }

        Fraction times(Fraction other) {
            return this;   // TODO
        }

        double toDouble() {
            return 0;   // TODO
        }

        boolean isEqualTo(Fraction other) {
            return false;   // TODO
        }

        private static int gcd(int a, int b) {
            // TODO: Euclid's method (make a and b positive first with Math.abs)
            return 1;
        }

        @Override
        public String toString() {
            return "?";   // TODO
        }
    }

    // ---------------- Don't change anything below this line ----------------

    static int passed = 0, failed = 0;

    static void check(String description, Object actual, Object expected) {
        if (actual.equals(expected)) {
            passed++;
            System.out.println("PASS  " + description);
        } else {
            failed++;
            System.out.println("FAIL  " + description + ": got " + actual + ", expected " + expected);
        }
    }

    public static void main(String[] args) {
        Fraction half = new Fraction(1, 2);
        Fraction quarter = new Fraction(1, 4);
        check("1/2 prints as 1/2", half.toString(), "1/2");
        check("6/8 is stored as 3/4", new Fraction(6, 8).toString(), "3/4");
        check("6/-8 is stored as -3/4", new Fraction(6, -8).toString(), "-3/4");
        check("-2/-4 is stored as 1/2", new Fraction(-2, -4).toString(), "1/2");
        check("4/2 prints as 2", new Fraction(4, 2).toString(), "2");
        check("0/5 prints as 0", new Fraction(0, 5).toString(), "0");
        check("getNumerator of 3/4", new Fraction(3, 4).getNumerator(), 3);
        check("getDenominator of 3/4", new Fraction(3, 4).getDenominator(), 4);
        check("1/2 + 1/4 = 3/4", half.plus(quarter).toString(), "3/4");
        check("1/2 + 1/2 = 1", half.plus(half).toString(), "1");
        check("1/2 x 1/4 = 1/8", half.times(quarter).toString(), "1/8");
        check("2/3 x 3/4 = 1/2", new Fraction(2, 3).times(new Fraction(3, 4)).toString(), "1/2");
        check("plus doesn't change the original", half.toString(), "1/2");
        check("3/4 as a double", new Fraction(3, 4).toDouble(), 0.75);
        check("1/3 isEqualTo 2/6", new Fraction(1, 3).isEqualTo(new Fraction(2, 6)), true);
        check("1/3 isEqualTo 1/4 is false", new Fraction(1, 3).isEqualTo(quarter), false);
        boolean refused = false;
        try {
            new Fraction(1, 0);
        } catch (IllegalArgumentException e) {
            refused = true;
        }
        check("denominator 0 is refused", refused, true);
        // The exact-arithmetic payoff: 1/10 + 2/10 really IS 3/10.
        check("1/10 + 2/10 = 3/10 exactly", new Fraction(1, 10).plus(new Fraction(2, 10)).toString(), "3/10");
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
