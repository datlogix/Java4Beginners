// Exercise 2: Predict, then check. And fix the type errors.
//
// PART A: predict.
// For each line in Part A below, write your prediction in the comment
// at the end of the line BEFORE you run anything. Then run the program and
// compare. For every line you got wrong, add a short note explaining what
// Java actually did and why (e.g. "int division throws away the .5").
//
// PART B: fix.
// Part B has four lines that the compiler REJECTS. Uncomment them one at a
// time (delete the // at the start), run, and read the error message. Then
// fix the line by changing its TYPE or its VALUE, so that it compiles and
// prints something sensible. Write the error message you saw in a comment
// next to each fixed line.
//
// Run it with:  java Exercise2.java

public class Exercise2 {
    public static void main(String[] args) {
        // ---------- PART A: predict ----------
        System.out.println(17 / 5);                // prediction:
        System.out.println(17 % 5);                // prediction:
        System.out.println(17 / 5.0);              // prediction:
        System.out.println(2 + 3 * 4 - 1);         // prediction:
        System.out.println("Score: " + 4 + 5);     // prediction:
        System.out.println(4 + 5 + " points");     // prediction:
        System.out.println((int) 9.99);            // prediction:
        System.out.println('a' + 'b');             // prediction:
        System.out.println(10 > 3 && 3 > 10);      // prediction:
        System.out.println(Math.pow(3, 2) + 1);    // prediction:

        // ---------- PART B: fix ----------
        // int price = 4.50;
        // String room = 101;
        // boolean finished = "true";
        // int year = "2026";
    }
}
