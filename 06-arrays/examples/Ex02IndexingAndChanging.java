// Example 2: reading and changing individual values by their index.
// Indexes start at 0, so the last index is length - 1.
// Run it with:  java Ex02IndexingAndChanging.java

import java.util.Arrays;

public class Ex02IndexingAndChanging {
    public static void main(String[] args) {
        String[] planets = {"Mercury", "Venus", "Earth", "Mars"};
        //                      0         1        2       3

        System.out.println("First: " + planets[0]);
        System.out.println("Third: " + planets[2]);
        System.out.println("Last:  " + planets[planets.length - 1]);

        planets[3] = "MARS";                // replace a value
        System.out.println(Arrays.toString(planets));

        int[] counts = new int[3];
        counts[0] = 5;
        counts[1] += 2;                     // 0 + 2
        counts[2]++;                        // 0 + 1
        System.out.println(Arrays.toString(counts));

        // An array's size is fixed when it's created. There's no "add".
        // Asking for an index that doesn't exist crashes the program:
        System.out.println("About to ask for planets[4]...");
        System.out.println(planets[4]);     // ArrayIndexOutOfBoundsException
    }
}
