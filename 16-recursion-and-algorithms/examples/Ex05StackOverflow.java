// Example 5: BROKEN ON PURPOSE. Recursion with no base case never stops,
// until the call stack runs out of room.
// Run it with:  java Ex05StackOverflow.java

public class Ex05StackOverflow {

    static int depth = 0;

    static void forever() {
        depth++;
        forever();          // no base case!
    }

    public static void main(String[] args) {
        try {
            forever();
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError after " + String.format("%,d", depth) + " calls.");
        }
        // Without the try/catch, you'd see a stack trace thousands of lines long,
        // all saying "at Ex05StackOverflow.forever(Ex05StackOverflow.java:11)".
    }
}
