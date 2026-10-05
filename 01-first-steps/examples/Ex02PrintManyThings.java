// Example 2: println, print, joining text with +, and escape sequences.
// Run it with:  java Ex02PrintManyThings.java

public class Ex02PrintManyThings {
    public static void main(String[] args) {
        // println prints, then moves to a new line.
        System.out.println("I am " + 17 + " years old");
        System.out.println("2 + 3 = " + (2 + 3));    // brackets: add FIRST, then join
        System.out.println("2 + 3 = " + 2 + 3);      // no brackets: joins "2" then "3"
        System.out.println(7 * 6);                   // a calculation on its own
        System.out.println();                        // an empty line

        // print does NOT move to a new line afterwards.
        System.out.print("Loading");
        System.out.print("...");
        System.out.println("done!");

        // Escape sequences: a backslash gives the next character a special meaning.
        System.out.println("Line one\nLine two");               // \n  new line
        System.out.println("Name:\tAma");                       // \t  tab
        System.out.println("She said \"hi\" to me.");           // \"  a quote mark
        System.out.println("A backslash looks like this: \\");  // \\  a backslash
    }
}
