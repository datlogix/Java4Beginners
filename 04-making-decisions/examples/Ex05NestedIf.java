// Example 5: an if inside another if.
// Run it with:  java Ex05NestedIf.java

import java.util.Scanner;

public class Ex05NestedIf {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Do you have a ticket? (yes/no) ");
        String ticket = in.nextLine().trim();

        if (ticket.equalsIgnoreCase("yes")) {
            System.out.print("How old are you? ");
            int age = Integer.parseInt(in.nextLine().trim());
            if (age >= 16) {
                System.out.println("Enjoy the film!");
            } else {
                System.out.println("Sorry, this film is for ages 16 and over.");
            }
        } else {
            System.out.println("Please buy a ticket first.");
        }
    }
}
