// Example 2: keep asking until the answer is valid.
// Run it with:  java Ex02InputValidation.java  (try typing 0, 15 and -3 first)

import java.util.Scanner;

public class Ex02InputValidation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Rate the course from 1 to 10: ");
        int rating = Integer.parseInt(in.nextLine().trim());

        while (rating < 1 || rating > 10) {
            System.out.print("That's not between 1 and 10. Try again: ");
            rating = Integer.parseInt(in.nextLine().trim());
        }

        System.out.println("Thanks! You rated it " + rating + "/10.");
    }
}
