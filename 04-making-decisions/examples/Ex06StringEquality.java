// Example 6: the == trap with Strings. Type "yes" when asked.
// Run it with:  java Ex06StringEquality.java

import java.util.Scanner;

public class Ex06StringEquality {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Do you like Java? (type yes) ");
        String answer = in.nextLine();

        // == asks "are these the SAME OBJECT in memory?", not "is the text the same?"
        if (answer == "yes") {
            System.out.println("== says: same");
        } else {
            System.out.println("== says: different   <- even though you typed yes!");
        }

        // .equals asks "do these contain the same characters?" That's what you want.
        if (answer.equals("yes")) {
            System.out.println(".equals says: same");
        }

        // .equalsIgnoreCase also accepts YES, Yes, yEs...
        if (answer.trim().equalsIgnoreCase("yes")) {
            System.out.println(".equalsIgnoreCase (after trim) says: same");
        }
    }
}
