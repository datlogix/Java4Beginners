// Example 3: different exceptions, different catch blocks.
// Run it with:  java Ex03MultipleCatches.java  (try 2, 9, x and 0)

import java.util.Scanner;

public class Ex03MultipleCatches {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] boxes = {40, 25, 10, 5};

        for (int attempt = 0; attempt < 4; attempt++) {
            System.out.print("Share box number (0-3) between how many people? Box: ");
            try {
                int box = Integer.parseInt(in.nextLine().trim());
                System.out.print("People: ");
                int people = Integer.parseInt(in.nextLine().trim());
                System.out.println("Each gets " + boxes[box] / people);
            } catch (NumberFormatException e) {
                System.out.println("  Please type whole numbers.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("  There's no such box. Boxes are 0 to " + (boxes.length - 1) + ".");
            } catch (ArithmeticException e) {
                System.out.println("  Nobody to share with!");
            }
        }

        // One catch for several types: separate them with |
        try {
            Object thing = "not a number";
            Integer n = (Integer) thing;
        } catch (ClassCastException | NullPointerException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }
    }
}
