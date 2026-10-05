// Example 5: doubles are stored in binary, so most decimals are a tiny bit off.
// Run it with:  java Ex05DoubleSurprises.java

public class Ex05DoubleSurprises {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);              // 0.30000000000000004
        System.out.println(0.1 + 0.2 == 0.3);       // false!
        System.out.println(1.1 * 3);                // 3.3000000000000003

        // Rounding for display: Math.round gives the nearest whole number,
        // so multiply first to keep two decimal places.
        double price = 19.999;
        System.out.println(Math.round(price));                // 20
        System.out.println(Math.round(price * 100) / 100.0);  // 20.0
        System.out.println(Math.round(2.4567 * 100) / 100.0); // 2.46

        // For money in a real system, count in pesewas (an int), not cedis.
        int pesewas = 1050 + 299;
        System.out.println("Total: " + (pesewas / 100) + " cedis " + (pesewas % 100) + " pesewas");
    }
}
