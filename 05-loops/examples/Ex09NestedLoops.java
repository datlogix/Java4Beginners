// Example 9: a loop inside a loop. The inner loop runs completely,
// every time the outer loop goes round once.
// Run it with:  java Ex09NestedLoops.java

public class Ex09NestedLoops {
    public static void main(String[] args) {
        // A multiplication table: rows from the outer loop, columns from the inner
        System.out.print("   x");
        for (int col = 1; col <= 6; col++) {
            System.out.printf("%4d", col);
        }
        System.out.println();
        for (int row = 1; row <= 6; row++) {
            System.out.printf("%4d", row);
            for (int col = 1; col <= 6; col++) {
                System.out.printf("%4d", row * col);
            }
            System.out.println();
        }
        System.out.println();

        // A triangle: row r has r stars
        for (int r = 1; r <= 5; r++) {
            for (int s = 0; s < r; s++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();

        // A pyramid: spaces first, then stars
        int height = 5;
        for (int r = 1; r <= height; r++) {
            System.out.println(" ".repeat(height - r) + "*".repeat(2 * r - 1));
        }
    }
}
