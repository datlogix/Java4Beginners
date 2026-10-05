// Example 4: reading what the user types with a Scanner.
// Run it with:  java Ex04Input.java

import java.util.Scanner;    // Scanner lives in the java.util package

public class Ex04Input {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);   // create ONE Scanner for the keyboard

        System.out.print("What's your name? ");
        String name = in.nextLine();           // reads a whole line of text

        System.out.print("How old are you? ");
        int age = in.nextInt();                // reads a whole number

        System.out.print("What's your height in metres? ");
        double height = in.nextDouble();       // reads a decimal number

        System.out.println("Nice to meet you, " + name + ".");
        System.out.println("Next year you'll be " + (age + 1) + ".");
        System.out.println("You're " + (height * 100) + " cm tall.");
    }
}
