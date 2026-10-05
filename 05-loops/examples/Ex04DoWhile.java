// Example 4: do ... while runs its block FIRST and checks the condition
// AFTERWARDS, so the block always runs at least once. Perfect for menus.
// Run it with:  java Ex04DoWhile.java

import java.util.Scanner;

public class Ex04DoWhile {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String choice;

        do {
            System.out.println();
            System.out.println("1) Say hello");
            System.out.println("2) Tell me the time");
            System.out.println("3) Quit");
            System.out.print("Choose: ");
            choice = in.nextLine().trim();

            switch (choice) {
                case "1" -> System.out.println("Hello!");
                case "2" -> System.out.println("It's " + java.time.LocalTime.now().withNano(0));
                case "3" -> System.out.println("Goodbye.");
                default -> System.out.println("Please type 1, 2 or 3.");
            }
        } while (!choice.equals("3"));     // note the semicolon after the condition
    }
}
