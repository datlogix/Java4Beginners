// Example 1: the two parts of every recursive method: a BASE CASE that stops,
// and a RECURSIVE CASE that calls the method on a SMALLER problem.
// Run it with:  java Ex01CountdownAndFactorial.java

public class Ex01CountdownAndFactorial {

    static void countdown(int n) {
        if (n == 0) {                    // base case
            System.out.println("Liftoff!");
            return;
        }
        System.out.print(n + " ");
        countdown(n - 1);                // recursive case: a smaller countdown
    }

    /** n! = n x (n-1)!   and   0! = 1 */
    static long factorial(int n) {
        if (n == 0) {
            return 1;                     // base case
        }
        return n * factorial(n - 1);      // recursive case
    }

    /** The sum of the digits of n:  sumDigits(4071) = 1 + sumDigits(407) */
    static int sumDigits(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + sumDigits(n / 10);
    }

    /** a to the power n, for n >= 0 */
    static double power(double a, int n) {
        if (n == 0) {
            return 1;
        }
        return a * power(a, n - 1);
    }

    public static void main(String[] args) {
        countdown(5);
        System.out.println("5! = " + factorial(5));
        System.out.println("20! = " + factorial(20));
        System.out.println("sumDigits(4071) = " + sumDigits(4071));
        System.out.println("2^10 = " + power(2, 10));
    }
}
