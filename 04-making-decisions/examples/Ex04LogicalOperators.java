// Example 4: combining conditions with && (and), || (or) and ! (not).
// Run it with:  java Ex04LogicalOperators.java

import java.util.Scanner;

public class Ex04LogicalOperators {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Heart rate (beats per minute)? ");
        int heartRate = Integer.parseInt(in.nextLine().trim());

        // && : BOTH must be true. Use it for "between" checks.
        if (heartRate >= 60 && heartRate <= 100) {
            System.out.println("Normal resting heart rate.");
        }

        // || : AT LEAST ONE must be true.
        if (heartRate < 60 || heartRate > 100) {
            System.out.println("Outside the normal resting range.");
        }

        // ! : flips true to false and false to true.
        boolean isNormal = heartRate >= 60 && heartRate <= 100;
        if (!isNormal) {
            System.out.println("Consider measuring again after resting for 5 minutes.");
        }

        // Short-circuiting: Java stops as soon as it knows the answer.
        // If count is 0, the division is never attempted, so no crash.
        int total = 120, count = 0;
        if (count != 0 && total / count > 50) {
            System.out.println("High average.");
        } else {
            System.out.println("No average to report (count is " + count + ").");
        }
    }
}
