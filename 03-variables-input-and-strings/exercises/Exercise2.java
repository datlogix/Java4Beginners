// Exercise 2: Username and initials generator.
//
// Ask for the user's full name (first name, a space, last name) and birth year,
// then print their initials, a username and a school email address.
//
// Sample run:
//
//     Full name? kwame  Asante
//     Birth year? 2006
//
//     Initials:  K.A.
//     Username:  kwaasante06
//     Email:     kwame.asante@students.example.edu.gh
//     Your name is 11 characters long (not counting the space).
//
// Rules:
//   - Initials are capital letters followed by full stops, whatever case the
//     user typed.
//   - The username is: the first THREE letters of the first name, then the
//     whole last name, then the last two digits of the birth year
//     (birthYear % 100, printed with two digits: 2005 -> "05"; try "%02d"
//     with String.format). All lower case.
//   - The email is first.last, all lower case, @students.example.edu.gh
//   - Use trim() so extra spaces before or after the name don't break anything.
//     (Two spaces BETWEEN the names, as in the sample, is a stretch goal:
//     hint, trim the last name too.)
//
// You'll need: trim, indexOf, substring, charAt, toUpperCase, toLowerCase,
// length, and String.format. You may assume the first name has at least three
// letters. Module 4 shows you how to check for names that don't.
//
// Run it with:  java Exercise2.java

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Full name? ");
        String fullName = in.nextLine().trim();
        System.out.print("Birth year? ");
        int birthYear = Integer.parseInt(in.nextLine().trim());

        // TODO: find the space and split the name into first and last

        // TODO: build the initials, the username and the email

        // TODO: print the four result lines (with a blank line before them)
    }
}
