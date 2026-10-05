// Example 3: a sentinel loop keeps going until the user types a special
// "stop" value (the sentinel). Here, the sentinel is "done".
// Run it with:  java Ex03SentinelLoop.java

import java.util.Scanner;

public class Ex03SentinelLoop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double total = 0;
        int count = 0;

        System.out.println("Enter the price of each item. Type done to finish.");
        System.out.print("Price: ");
        String entry = in.nextLine().trim();

        while (!entry.equalsIgnoreCase("done")) {
            total += Double.parseDouble(entry);
            count++;
            System.out.print("Price: ");
            entry = in.nextLine().trim();
        }

        System.out.printf("%d items, total GHS %.2f%n", count, total);
    }
}
