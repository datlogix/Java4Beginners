// Example 8: turning text into an array with split, and back with join.
// Run it with:  java Ex08SplitAndJoin.java

import java.util.Arrays;

public class Ex08SplitAndJoin {
    public static void main(String[] args) {
        String sentence = "the quick brown fox jumps";
        String[] words = sentence.split(" ");
        System.out.println(words.length + " words: " + Arrays.toString(words));

        String csvLine = "Kofi,19,Kumasi,1.75";
        String[] fields = csvLine.split(",");
        String name = fields[0];
        int age = Integer.parseInt(fields[1]);
        double height = Double.parseDouble(fields[3]);
        System.out.println(name + " (" + age + ") from " + fields[2] + ", " + height + " m");

        // " +" means "one or more spaces", so extra spaces don't make empty words
        String messy = "too    many   spaces";
        System.out.println(Arrays.toString(messy.split(" ")));
        System.out.println(Arrays.toString(messy.split(" +")));

        // join goes the other way
        System.out.println(String.join(" - ", words));

        // toCharArray gives every character
        char[] letters = "Java".toCharArray();
        System.out.println(Arrays.toString(letters));
    }
}
