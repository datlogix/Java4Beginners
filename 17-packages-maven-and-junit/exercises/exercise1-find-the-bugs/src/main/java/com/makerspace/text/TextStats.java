package com.makerspace.text;

import java.util.Arrays;

/**
 * Text statistics. This class has FOUR bugs. Don't hunt for them by reading
 * the code: write TESTS (in src/test/java/com/makerspace/text/TextStatsTest.java)
 * that check what each method's Javadoc PROMISES, until your tests catch all four.
 * Then fix the bugs, and watch your tests turn green.
 */
public class TextStats {

    /** The number of words, where words are separated by one or more spaces.
     *  An empty or blank text has 0 words. */
    public static int wordCount(String text) {
        return text.trim().split(" +").length;
    }

    /** The longest word. If several words are equally long, the FIRST of them. */
    public static String longestWord(String text) {
        String best = "";
        for (String word : text.trim().split(" +")) {
            if (word.length() >= best.length()) {
                best = word;
            }
        }
        return best;
    }

    /** The number of vowels (a, e, i, o, u), in upper or lower case. */
    public static int vowelCount(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    /** Each word with a capital first letter and the rest lower case:
     *  "hello WORLD" -> "Hello World". Spacing is tidied to single spaces. */
    public static String capitalise(String text) {
        StringBuilder result = new StringBuilder();
        for (String word : text.trim().split(" +")) {
            if (word.isEmpty()) {
                continue;
            }
            if (result.length() > 0) {
                result.append(' ');
            }
            result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /** True if a and b use exactly the same letters, ignoring capitals and spaces:
     *  "Listen" and "Silent" are anagrams; so are "dormitory" and "dirty room". */
    public static boolean isAnagram(String a, String b) {
        char[] x = a.toLowerCase().toCharArray();
        char[] y = b.toLowerCase().toCharArray();
        Arrays.sort(x);
        Arrays.sort(y);
        return Arrays.equals(x, y);
    }

    /** True if text reads the same backwards, ignoring capitals: "Level", "noon". */
    public static boolean isPalindrome(String text) {
        String t = text.toLowerCase();
        return new StringBuilder(t).reverse().toString().equals(t);
    }

    public static void main(String[] args) {
        String text = args.length > 0 ? String.join(" ", args) : "The quick brown fox";
        System.out.println(wordCount(text) + " words, longest: " + longestWord(text));
    }
}
