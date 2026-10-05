// Example 6: BROKEN ON PURPOSE. A runtime error (an "exception").
//
// 1. Run this file. It compiles fine, starts running, then crashes.
// 2. Notice that the first println DID run. Java only discovers the
//    problem when it reaches that line.
// 3. Read the stack trace: what went wrong, and on which line?
// 4. Fix it (divide by something that isn't zero) and run it again.

public class Ex06RuntimeError {
    public static void main(String[] args) {
        System.out.println("Sharing 10 sweets between 0 friends...");
        System.out.println("Each friend gets " + (10 / 0));
        System.out.println("Finished.");
    }
}
