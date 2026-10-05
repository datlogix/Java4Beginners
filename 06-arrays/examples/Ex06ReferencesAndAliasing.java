// Example 6: the aliasing gotcha. An array variable holds a REFERENCE
// (the address of the array), not the values themselves.
// Run it with:  java Ex06ReferencesAndAliasing.java

import java.util.Arrays;

public class Ex06ReferencesAndAliasing {
    public static void main(String[] args) {
        int[] original = {1, 2, 3};
        int[] alias = original;         // NOT a copy: both names point at the SAME array

        alias[0] = 99;
        System.out.println("original: " + Arrays.toString(original));   // [99, 2, 3] !
        System.out.println("alias:    " + Arrays.toString(alias));

        // To get an independent copy, copy it:
        int[] realCopy = Arrays.copyOf(original, original.length);
        realCopy[1] = 0;
        System.out.println("original: " + Arrays.toString(original));   // unchanged
        System.out.println("realCopy: " + Arrays.toString(realCopy));

        // Primitives are different: an int variable holds the value itself.
        int x = 5;
        int y = x;          // copies the value 5
        y = 6;
        System.out.println("x = " + x + ", y = " + y);                  // x is still 5
    }
}
