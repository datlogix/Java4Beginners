// Example 8: converting between types ("casting").
// Run it with:  java Ex08Casting.java

public class Ex08Casting {
    public static void main(String[] args) {
        // Widening: int to double is safe, so Java does it automatically.
        int marks = 7;
        double asDouble = marks;
        System.out.println(asDouble);              // 7.0

        // Narrowing: double to int could lose information, so you must ask.
        double temperature = 36.9;
        int whole = (int) temperature;             // chops off the decimal part
        System.out.println(whole);                 // 36, NOT 37

        // Casting to get a decimal answer from int division.
        int total = 7, count = 2;
        System.out.println(total / count);             // 3
        System.out.println((double) total / count);    // 3.5

        // Text to numbers, and numbers to text.
        int fromText = Integer.parseInt("42");
        double priceFromText = Double.parseDouble("19.99");
        String backToText = String.valueOf(fromText);
        System.out.println(fromText + 1);              // 43: a real number now
        System.out.println(priceFromText * 2);         // 39.98
        System.out.println(backToText + 1);            // 421: a String, so + joins

        // Overflow: an int that goes past its limit wraps around to negative.
        int big = Integer.MAX_VALUE;
        System.out.println(big + 1);                   // -2147483648
        System.out.println((long) big + 1);            // 2147483648: use long for big numbers
    }
}
