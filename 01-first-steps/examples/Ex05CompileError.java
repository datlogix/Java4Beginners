// Example 5: BROKEN ON PURPOSE. A compile-time error.
//
// 1. Run this file and read the error message. Note the line number.
// 2. Notice that NOT EVEN the first println runs: the compiler checks the
//    whole file before anything is allowed to run.
// 3. Fix the mistake and run it again.

public class Ex05CompileError {
    public static void main(String[] args) {
        System.out.println("Line one");
        System.out.println("Line two is missing something")
        System.out.println("Line three");
    }
}
