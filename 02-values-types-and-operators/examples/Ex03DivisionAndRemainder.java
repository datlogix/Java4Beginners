// Example 3: integer division and % are a team. Together they split a
// number into "how many whole groups" and "how many left over".
// Run it with:  java Ex03DivisionAndRemainder.java

public class Ex03DivisionAndRemainder {
    public static void main(String[] args) {
        // 200 minutes, as hours and minutes
        System.out.println("200 minutes = " + (200 / 60) + " h " + (200 % 60) + " min");

        // 1000 seconds, as minutes and seconds
        System.out.println("1000 seconds = " + (1000 / 60) + " min " + (1000 % 60) + " s");

        // 23 eggs into boxes of 6
        System.out.println("23 eggs = " + (23 / 6) + " full boxes and " + (23 % 6) + " spare eggs");

        // The last digit of a number, and the number without it
        System.out.println("Last digit of 4071: " + (4071 % 10));
        System.out.println("4071 without its last digit: " + (4071 / 10));

        // Even or odd? Even numbers leave remainder 0 when divided by 2.
        System.out.println("17 % 2 = " + (17 % 2) + "  so 17 is odd");
    }
}
