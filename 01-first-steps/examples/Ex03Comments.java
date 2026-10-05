// Example 3: comments are notes for humans. Java ignores them completely.
// Run it with:  java Ex03Comments.java

/*
 * A block comment can span
 * as many lines as you like.
 */

/**
 * A comment starting with slash-star-star is a Javadoc comment.
 * Tools turn these into web pages of documentation (Module 8).
 */
public class Ex03Comments {
    public static void main(String[] args) {
        // Greet first, so the user knows the program started.
        System.out.println("Welcome!");

        System.out.println("This line runs.");   // comments can go at the end of a line too
        // System.out.println("This line is switched off (commented out).");

        System.out.println("Try removing the // in front of the line above and running again.");
    }
}
