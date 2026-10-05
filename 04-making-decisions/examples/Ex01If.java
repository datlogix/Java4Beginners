// Example 1: if runs a block of code only when a condition is true.
// Run it with:  java Ex01If.java  (try it with 30, then with 20)

import java.util.Scanner;

public class Ex01If {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Temperature in Accra today (C)? ");
        int temperature = Integer.parseInt(in.nextLine().trim());

        if (temperature > 28) {
            System.out.println("It's hot. Drink plenty of water.");
            System.out.println("(This line is inside the if too.)");
        }

        System.out.println("This line always runs: it's after the if's closing brace.");
    }
}
