// Example 4: return gives a value BACK to whoever called the method.
// Run it with:  java Ex04ReturnValues.java

public class Ex04ReturnValues {

    // The type before the name (double) is the type of value it returns.
    static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    static int largest(int a, int b, int c) {
        int biggest = a;
        if (b > biggest) {
            biggest = b;
        }
        if (c > biggest) {
            biggest = c;
        }
        return biggest;
    }

    // A method that answers a yes/no question returns a boolean
    static boolean isPrime(int n) {
        if (n < 2) {
            return false;               // return ends the method immediately
        }
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }

    // void means "returns nothing": it just does something
    static void report(String label, double value) {
        System.out.printf("%-12s %.1f%n", label, value);
    }

    public static void main(String[] args) {
        double f = celsiusToFahrenheit(37);       // the call is replaced by its result
        report("37 C in F:", f);
        report("100 C in F:", celsiusToFahrenheit(100));

        System.out.println("Largest of 4, 19, 7: " + largest(4, 19, 7));
        System.out.println("Is 97 prime? " + isPrime(97));

        System.out.print("Primes under 50:");
        for (int n = 1; n < 50; n++) {
            if (isPrime(n)) {                     // use the result directly in a condition
                System.out.print(" " + n);
            }
        }
        System.out.println();

        celsiusToFahrenheit(20);                  // legal, but the answer is thrown away!
    }
}
