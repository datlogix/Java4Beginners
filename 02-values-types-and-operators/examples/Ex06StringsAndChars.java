// Example 6: Strings and chars, and what + does with them.
// Run it with:  java Ex06StringsAndChars.java

public class Ex06StringsAndChars {
    public static void main(String[] args) {
        // + with a String on either side JOINS (concatenates).
        System.out.println("Java" + "Script");     // JavaScript
        System.out.println("Room " + 101);         // Room 101

        // Java works left to right, so the order matters.
        System.out.println(1 + 2 + "3");           // 33  (1 + 2 = 3, then "3" joined)
        System.out.println("1" + 2 + 3);           // 123 (a String from the start)
        System.out.println("1" + (2 + 3));         // 15

        // repeat() makes copies, length() counts characters.
        System.out.println("=".repeat(30));
        System.out.println("Ghana".length());      // 5

        // A char is really a number: its position in the Unicode table.
        char grade = 'A';
        System.out.println(grade);                 // A
        System.out.println(grade + 1);             // 66: char + int is an int
        System.out.println((char) (grade + 1));    // B: turn it back into a char
        System.out.println("Grade: " + grade);     // with a String, it's joined as a letter
    }
}
