// Example 3: else if chains. Java checks each condition in order and runs
// the FIRST block whose condition is true, then skips the rest.
// Run it with:  java Ex03ElseIfChain.java

import java.util.Scanner;

public class Ex03ElseIfChain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Exam mark (0-100)? ");
        int mark = Integer.parseInt(in.nextLine().trim());

        String grade;
        if (mark >= 80) {
            grade = "A";
        } else if (mark >= 70) {        // we only get here if mark < 80
            grade = "B";
        } else if (mark >= 60) {
            grade = "C";
        } else if (mark >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println("Grade: " + grade);

        // ORDER MATTERS. This version is wrong: try it with 95.
        if (mark >= 50) {
            System.out.println("Wrong version says: D");
        } else if (mark >= 80) {
            System.out.println("Wrong version says: A   (this line can never run!)");
        }
    }
}
