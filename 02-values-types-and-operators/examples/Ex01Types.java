// Example 1: Java's basic types. Every value has a type, and so does every variable.
// Run it with:  java Ex01Types.java

public class Ex01Types {
    public static void main(String[] args) {
        int wholeNumber = 42;              // whole numbers, about -2.1 to +2.1 billion
        long bigNumber = 8_000_000_000L;   // much bigger whole numbers (note the L)
        double decimal = 3.75;             // numbers with a decimal point
        boolean isRaining = false;         // true or false, nothing else
        char letter = 'J';                 // ONE character, in single quotes
        String text = "Java";              // text, in double quotes (capital S!)

        System.out.println("int:     " + wholeNumber);
        System.out.println("long:    " + bigNumber);
        System.out.println("double:  " + decimal);
        System.out.println("boolean: " + isRaining);
        System.out.println("char:    " + letter);
        System.out.println("String:  " + text);

        // The underscores in 8_000_000_000 are only there to help humans read it.
        System.out.println("Largest int:  " + Integer.MAX_VALUE);
        System.out.println("Largest long: " + Long.MAX_VALUE);
    }
}
