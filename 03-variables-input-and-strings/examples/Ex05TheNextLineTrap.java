// Example 5: the most famous Scanner trap, and two fixes.
// Run it, type your age, press Enter... and watch it skip the name question!
// Run it with:  java Ex05TheNextLineTrap.java

import java.util.Scanner;

public class Ex05TheNextLineTrap {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Age: ");
        int age = in.nextInt();         // reads "19" but LEAVES the Enter key behind
        System.out.print("Name: ");
        String name = in.nextLine();    // reads the leftover Enter: an empty line!
        System.out.println("[" + name + "] is " + age);

        // Fix 1: throw away the rest of the line after nextInt().
        System.out.print("Age again: ");
        age = in.nextInt();
        in.nextLine();                  // swallow the leftover Enter
        System.out.print("Name again: ");
        name = in.nextLine();
        System.out.println("[" + name + "] is " + age);

        // Fix 2 (the one this course uses): ALWAYS read whole lines, then convert.
        System.out.print("Age one more time: ");
        age = Integer.parseInt(in.nextLine().trim());
        System.out.print("Name one more time: ");
        name = in.nextLine();
        System.out.println("[" + name + "] is " + age);
    }
}
