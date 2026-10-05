// Example 4: Fibonacci, the naive way (very slow) and with MEMOISATION
// (remembering answers already worked out).
// Run it with:  java Ex04Fibonacci.java

import java.util.HashMap;
import java.util.Map;

public class Ex04Fibonacci {

    static long calls = 0;

    /** 0, 1, 1, 2, 3, 5, 8, 13...  each number is the sum of the two before it. */
    static long fibSlow(int n) {
        calls++;
        if (n < 2) {
            return n;
        }
        return fibSlow(n - 1) + fibSlow(n - 2);      // TWO recursive calls: the work doubles each level
    }

    static final Map<Integer, Long> memo = new HashMap<>();

    static long fibFast(int n) {
        calls++;
        if (n < 2) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);                       // worked this out before: reuse it
        }
        long answer = fibFast(n - 1) + fibFast(n - 2);
        memo.put(n, answer);
        return answer;
    }

    public static void main(String[] args) {
        for (int n : new int[]{10, 20, 30, 40}) {
            calls = 0;
            long start = System.nanoTime();
            long answer = fibSlow(n);
            double ms = (System.nanoTime() - start) / 1e6;
            System.out.printf("fibSlow(%d) = %,12d %,13d calls %8.1f ms%n", n, answer, calls, ms);
        }
        for (int n : new int[]{40, 90}) {
            calls = 0;
            memo.clear();
            System.out.printf("fibFast(%d) = %,d   %,d calls%n", n, fibFast(n), calls);
        }
    }
}
