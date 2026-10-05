// Example 3: naming rules, constants with final, and var.
// Run it with:  java Ex03NamingAndConstants.java

public class Ex03NamingAndConstants {
    public static void main(String[] args) {
        // Java style: variables use camelCase. Start lower case, capitalise each new word.
        int numberOfStudents = 42;
        double averageMark = 67.5;
        boolean isLoggedIn = true;

        // Constants: final means "this can never change". Style: UPPER_SNAKE_CASE.
        final double VAT_RATE = 0.15;
        final int SECONDS_PER_HOUR = 3600;

        double price = 200.0;
        System.out.println("VAT on " + price + " cedis: " + (price * VAT_RATE));
        System.out.println("A day has " + (24 * SECONDS_PER_HOUR) + " seconds.");
        // VAT_RATE = 0.2;     // remove the // to see: cannot assign a value to final variable

        // var: let Java work out the type from the value (it's still fixed!)
        var town = "Kumasi";        // Java decides this is a String
        var count = 3;              // ... and this is an int
        System.out.println(town + " " + count);
        // count = "three";    // still an error: count is an int forever

        System.out.println(numberOfStudents + " " + averageMark + " " + isLoggedIn);

        // These names are NOT allowed:
        // int 2ndPlace;       starts with a digit
        // int my score;       contains a space
        // int class;          'class' is a reserved word
    }
}
