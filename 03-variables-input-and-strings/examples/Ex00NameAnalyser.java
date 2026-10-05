// Example 0: the hook. Java asks you two questions, then tells you things
// about yourself you probably never worked out.
// Run it with:  java Ex00NameAnalyser.java

import java.util.Scanner;

public class Ex00NameAnalyser {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("What's your first name? ");
        String name = in.nextLine().trim();
        System.out.print("What year were you born? ");
        int birthYear = Integer.parseInt(in.nextLine().trim());

        int thisYear = java.time.Year.now().getValue();
        int age = thisYear - birthYear;
        long secondsAlive = (long) age * 365 * 24 * 60 * 60;

        System.out.println();
        System.out.println("Hello, " + name.toUpperCase() + "!");
        System.out.println("Your name has " + name.length() + " letters. It starts with '"
                + name.charAt(0) + "' and ends with '" + name.charAt(name.length() - 1) + "'.");
        System.out.println("Backwards, it's " + new StringBuilder(name).reverse() + ".");
        System.out.printf("You're about %d, so you've been alive for roughly %,d seconds.%n",
                age, secondsAlive);
        System.out.printf("In the year 2050 you'll be %d.%n", 2050 - birthYear);
    }
}
