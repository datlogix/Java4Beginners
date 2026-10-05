// Exercise 1: Think recursively.
//
// Write every method below RECURSIVELY: no for or while loops anywhere!
// For each one, first write down (in a comment above it):
//     Base case:      the smallest version, answered directly
//     Recursive case: how a bigger version uses a smaller one
//
// Run it with:  java Exercise1.java

public class Exercise1 {

    /** 1 + 2 + ... + n   (sumTo(0) is 0) */
    static int sumTo(int n) {
        return 0;   // TODO
    }

    /** How many times c appears in s. */
    static int countChar(String s, char c) {
        return 0;   // TODO
    }

    /** n written in binary: toBinary(6) is "110", toBinary(0) is "0".
     *  Hint: the last binary digit of n is n % 2; the others are toBinary(n / 2). */
    static String toBinary(int n) {
        return "";   // TODO
    }

    /** The greatest common divisor: gcd(a, 0) = a, otherwise gcd(b, a % b). */
    static int gcd(int a, int b) {
        return 0;   // TODO
    }

    /** The largest value in a[from], a[from + 1], ... a[a.length - 1]. */
    static int maxFrom(int[] a, int from) {
        return 0;   // TODO
    }

    /** The digital root: keep adding up the digits until one digit is left.
     *  digitalRoot(4071) -> 4+0+7+1 = 12 -> 1+2 = 3.
     *  (You may write a recursive sumDigits helper too.) */
    static int digitalRoot(int n) {
        return 0;   // TODO
    }

    /** True if s reads the same backwards, IGNORING capitals and anything that
     *  isn't a letter: "Never odd or even!" is a palindrome.
     *  Hint: if the first character isn't a letter, skip it; same for the last. */
    static boolean isPalindrome(String s) {
        return false;   // TODO
    }

    /** The number of different paths from the top-left to the bottom-right of a
     *  grid of rows x cols squares, moving only right or down.
     *  paths(1, n) and paths(n, 1) are 1. Otherwise: paths from the square below,
     *  plus paths from the square to the right. */
    static long paths(int rows, int cols) {
        return 0;   // TODO
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

    public static void main(String[] args) {
        check("sumTo(0)", sumTo(0), 0);
        check("sumTo(100)", sumTo(100), 5050);
        check("countChar banana a", countChar("banana", 'a'), 3);
        check("countChar empty", countChar("", 'x'), 0);
        check("toBinary(0)", toBinary(0), "0");
        check("toBinary(6)", toBinary(6), "110");
        check("toBinary(255)", toBinary(255), "11111111");
        check("gcd(48, 18)", gcd(48, 18), 6);
        check("gcd(17, 5)", gcd(17, 5), 1);
        check("maxFrom", maxFrom(new int[]{4, 19, 7, 23, 2}, 0), 23);
        check("maxFrom one item", maxFrom(new int[]{-5}, 0), -5);
        check("digitalRoot(4071)", digitalRoot(4071), 3);
        check("digitalRoot(7)", digitalRoot(7), 7);
        check("palindrome racecar", isPalindrome("racecar"), true);
        check("palindrome sentence", isPalindrome("Never odd or even!"), true);
        check("not a palindrome", isPalindrome("Java"), false);
        check("paths(2, 2)", paths(2, 2), 2);
        check("paths(3, 3)", paths(3, 3), 6);
        check("paths(5, 5)", paths(5, 5), 70);
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
        System.out.println("(Try paths(17, 17). Why is it so slow? Ex04Fibonacci has the cure.)");
    }
}
