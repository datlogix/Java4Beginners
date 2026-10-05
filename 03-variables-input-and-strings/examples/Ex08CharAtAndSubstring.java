// Example 8: getting characters and pieces out of a String.
// Positions (indexes) start at 0, not 1.
// Run it with:  java Ex08CharAtAndSubstring.java

public class Ex08CharAtAndSubstring {
    public static void main(String[] args) {
        String word = "ENGINEER";
        //             01234567   <- the index of each character

        System.out.println(word.charAt(0));                    // E: the first character
        System.out.println(word.charAt(3));                    // I
        System.out.println(word.charAt(word.length() - 1));    // R: the last character
        // word.charAt(8) would crash: StringIndexOutOfBoundsException

        // substring(start, end): from start, up to but NOT including end
        System.out.println(word.substring(0, 3));              // ENG
        System.out.println(word.substring(3, 6));              // INE
        System.out.println(word.substring(5));                 // EER: from 5 to the end

        // A classic: split a full name at the space
        String fullName = "Abena Owusu";
        int space = fullName.indexOf(" ");
        String first = fullName.substring(0, space);
        String last = fullName.substring(space + 1);
        System.out.println("First: " + first + ", last: " + last);
        System.out.println("Initials: " + first.charAt(0) + last.charAt(0));
    }
}
