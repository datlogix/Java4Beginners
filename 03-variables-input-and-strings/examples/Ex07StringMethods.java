// Example 7: things a String can do. Strings never change: each method
// gives you a NEW String and leaves the original alone.
// Run it with:  java Ex07StringMethods.java

public class Ex07StringMethods {
    public static void main(String[] args) {
        String messy = "   Hello, Java World!   ";
        String text = messy.trim();                   // remove spaces at both ends

        System.out.println("[" + messy + "]");
        System.out.println("[" + text + "]");
        System.out.println(text.length());            // 18
        System.out.println(text.toUpperCase());       // HELLO, JAVA WORLD!
        System.out.println(text.toLowerCase());       // hello, java world!
        System.out.println(text.contains("Java"));    // true
        System.out.println(text.startsWith("Hello")); // true
        System.out.println(text.endsWith("?"));       // false
        System.out.println(text.indexOf("Java"));     // 7: where it starts (counting from 0)
        System.out.println(text.indexOf("Python"));   // -1: not found
        System.out.println(text.replace("World", "Learners"));
        System.out.println(text.isEmpty());           // false

        // Strings are immutable: calling a method doesn't change the variable.
        String shout = "quiet";
        shout.toUpperCase();                          // the answer is thrown away!
        System.out.println(shout);                    // still "quiet"
        shout = shout.toUpperCase();                  // keep the answer
        System.out.println(shout);                    // "QUIET"

        // Comparing text: .equals and .equalsIgnoreCase
        System.out.println("yes".equals("YES"));            // false
        System.out.println("yes".equalsIgnoreCase("YES"));  // true
    }
}
