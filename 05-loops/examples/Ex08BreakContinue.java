// Example 8: break leaves a loop immediately; continue skips to the next round.
// Run it with:  java Ex08BreakContinue.java

public class Ex08BreakContinue {
    public static void main(String[] args) {
        // continue: skip the multiples of 3
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                continue;       // jump straight to the next i
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // break: find the first number over 1000 that divides by 17 and by 23
        int found = 0;
        for (int n = 1001; n < 2000; n++) {
            if (n % 17 == 0 && n % 23 == 0) {
                found = n;
                break;          // stop looking: we have our answer
            }
        }
        System.out.println("First multiple of 17 and 23 over 1000: " + found);

        // while (true) with break: a loop whose exit is in the middle
        int bacteria = 1;
        int minutes = 0;
        while (true) {
            if (bacteria > 1_000_000) {
                break;
            }
            bacteria *= 2;      // doubles every 20 minutes
            minutes += 20;
        }
        System.out.println("Over a million bacteria after " + minutes + " minutes (" + bacteria + ").");
    }
}
