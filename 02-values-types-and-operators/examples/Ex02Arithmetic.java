// Example 2: the arithmetic operators.
// Run it with:  java Ex02Arithmetic.java

public class Ex02Arithmetic {
    public static void main(String[] args) {
        System.out.println("7 + 2 = " + (7 + 2));
        System.out.println("7 - 2 = " + (7 - 2));
        System.out.println("7 * 2 = " + (7 * 2));
        System.out.println("7 / 2 = " + (7 / 2) + "    (both ints: the decimal part is thrown away)");
        System.out.println("7 % 2 = " + (7 % 2) + "    (the remainder)");
        System.out.println("7.0 / 2 = " + (7.0 / 2) + "  (one double: a double answer)");
        System.out.println("-7 / 2 = " + (-7 / 2) + "   (int division chops towards zero)");
    }
}
