// Example 6: looping over the characters of a String.
// Run it with:  java Ex06ForOverString.java

import java.util.Scanner;

public class Ex06ForOverString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Type a word or sentence: ");
        String text = in.nextLine();

        // Visit every index from 0 to length() - 1
        int vowels = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            }
        }
        System.out.println("Vowels: " + vowels);

        // Build a reversed copy, one character at a time, from the end
        String reversed = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed = reversed + text.charAt(i);
        }
        System.out.println("Reversed: " + reversed);

        // Spell it out with dashes between the letters
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            if (i < text.length() - 1) {
                System.out.print("-");
            }
        }
        System.out.println();
    }
}
