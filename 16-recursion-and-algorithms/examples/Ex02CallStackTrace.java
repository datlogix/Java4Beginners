// Example 2: watching the call stack grow and shrink. Each call is indented
// by its depth, so you can see every call waiting for the one below it.
// Run it with:  java Ex02CallStackTrace.java

public class Ex02CallStackTrace {

    static long factorial(int n, int depth) {
        String indent = "    ".repeat(depth);
        System.out.println(indent + "factorial(" + n + ") called");
        if (n == 0) {
            System.out.println(indent + "base case: returning 1");
            return 1;
        }
        long smaller = factorial(n - 1, depth + 1);      // this call WAITS here until the inner one returns
        long result = n * smaller;
        System.out.println(indent + "factorial(" + n + ") returns " + n + " x " + smaller + " = " + result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Answer: " + factorial(4, 0));
    }
}
