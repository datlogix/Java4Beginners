// Exercise 1: A toolkit of methods, with built-in checks.
//
// main() below is already finished. It calls each of your methods with
// carefully chosen values and prints PASS or FAIL. Your job is to write the
// BODY of each method so that every check passes.
//
// Right now each method returns a placeholder value, so most checks FAIL.
// Work through the methods one at a time: write one, run the file, and watch
// its checks turn to PASS before you move on to the next.
//
// Don't change main() or check(). (If you think a check is wrong, ask!)
//
// Run it with:  java Exercise1.java

public class Exercise1 {

    /** Returns true if n is even. */
    static boolean isEven(int n) {
        return false;   // TODO
    }

    /** Returns the largest of three numbers. */
    static int maxOfThree(int a, int b, int c) {
        return 0;       // TODO
    }

    /** Returns n! = 1 x 2 x ... x n. 0! is 1. Use long: 20! is huge. */
    static long factorial(int n) {
        return 0;       // TODO
    }

    /** Returns how many digits n has. 0 has 1 digit; ignore any minus sign. */
    static int countDigits(int n) {
        return 0;       // TODO   (hint: keep dividing by 10)
    }

    /** Returns the greatest common divisor of a and b (both positive).
     *  Euclid's method: while b isn't 0, replace (a, b) with (b, a % b).
     *  When b is 0, the answer is a. */
    static int gcd(int a, int b) {
        return 0;       // TODO
    }

    /** Returns true if text reads the same backwards, ignoring capitals.
     *  ("Level" and "racecar" are palindromes; "Java" isn't.) */
    static boolean isPalindrome(String text) {
        return false;   // TODO
    }

    /** Returns the total resistance of resistors in SERIES: R1 + R2 + ... */
    static double seriesResistance(double[] resistors) {
        return 0;       // TODO
    }

    /** Returns the total resistance of resistors in PARALLEL:
     *  1 / (1/R1 + 1/R2 + ...) */
    static double parallelResistance(double[] resistors) {
        return 0;       // TODO
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
        check("isEven(4)", isEven(4), true);
        check("isEven(7)", isEven(7), false);
        check("isEven(0)", isEven(0), true);
        check("isEven(-6)", isEven(-6), true);
        check("maxOfThree(3, 9, 5)", maxOfThree(3, 9, 5), 9);
        check("maxOfThree(9, 3, 5)", maxOfThree(9, 3, 5), 9);
        check("maxOfThree(3, 5, 9)", maxOfThree(3, 5, 9), 9);
        check("maxOfThree(-1, -8, -3)", maxOfThree(-1, -8, -3), -1);
        check("factorial(0)", factorial(0), 1L);
        check("factorial(5)", factorial(5), 120L);
        check("factorial(20)", factorial(20), 2432902008176640000L);
        check("countDigits(7)", countDigits(7), 1);
        check("countDigits(0)", countDigits(0), 1);
        check("countDigits(2026)", countDigits(2026), 4);
        check("countDigits(-350)", countDigits(-350), 3);
        check("gcd(12, 18)", gcd(12, 18), 6);
        check("gcd(17, 5)", gcd(17, 5), 1);
        check("gcd(100, 75)", gcd(100, 75), 25);
        check("isPalindrome(\"Level\")", isPalindrome("Level"), true);
        check("isPalindrome(\"racecar\")", isPalindrome("racecar"), true);
        check("isPalindrome(\"Java\")", isPalindrome("Java"), false);
        check("isPalindrome(\"\")", isPalindrome(""), true);
        check("seriesResistance({100, 220, 330})", seriesResistance(new double[]{100, 220, 330}), 650.0);
        check("parallelResistance({100, 100})", parallelResistance(new double[]{100, 100}), 50.0);
        check("parallelResistance({60, 30, 20}) rounded",
                Math.round(parallelResistance(new double[]{60, 30, 20}) * 100) / 100.0, 10.0);
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
