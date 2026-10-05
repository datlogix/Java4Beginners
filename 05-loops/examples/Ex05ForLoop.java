// Example 5: a for loop puts the start, the condition and the step on ONE line.
//     for (start; condition; step) { ... }
// Run it with:  java Ex05ForLoop.java

public class Ex05ForLoop {
    public static void main(String[] args) {
        // Count from 1 to 5
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Count from 0 up to, but not including, 5 (the most common form)
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Steps of 10
        for (int i = 0; i <= 100; i += 10) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Backwards
        for (int i = 10; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println("Liftoff!");

        // A table of squares, lined up with printf
        for (int n = 1; n <= 5; n++) {
            System.out.printf("%2d squared is %3d%n", n, n * n);
        }
        // System.out.println(n);   // error: n only exists inside its loop
    }
}
