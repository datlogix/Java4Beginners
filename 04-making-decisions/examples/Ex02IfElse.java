// Example 2: if ... else chooses between exactly two paths.
// Run it with:  java Ex02IfElse.java

import java.util.Scanner;

public class Ex02IfElse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a whole number: ");
        int number = Integer.parseInt(in.nextLine().trim());

        if (number % 2 == 0) {
            System.out.println(number + " is even.");
        } else {
            System.out.println(number + " is odd.");
        }

        System.out.print("Your age? ");
        int age = Integer.parseInt(in.nextLine().trim());
        if (age >= 18) {
            System.out.println("You can register to vote in Ghana.");
        } else {
            System.out.println("You can register in " + (18 - age) + " year(s).");
        }
    }
}
