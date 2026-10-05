// Example 4: java.util.Collections does for lists what Arrays does for arrays.
// Run it with:  java Ex04CollectionsClass.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex04CollectionsClass {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>(List.of(42, 7, 19, 3, 88, 21, 7));
        System.out.println("Scores:    " + scores);

        Collections.sort(scores);
        System.out.println("Sorted:    " + scores);
        Collections.reverse(scores);
        System.out.println("Reversed:  " + scores);
        System.out.println("Max:       " + Collections.max(scores));
        System.out.println("Min:       " + Collections.min(scores));
        System.out.println("Sevens:    " + Collections.frequency(scores, 7));
        Collections.shuffle(scores);
        System.out.println("Shuffled:  " + scores);

        // List.of makes a FIXED list. Good for constants; it can't be changed.
        List<String> colours = List.of("red", "amber", "green");
        System.out.println(colours);
        // colours.add("blue");   // UnsupportedOperationException

        // To get a list you CAN change, copy it into an ArrayList (as we did above).
        ArrayList<String> mine = new ArrayList<>(colours);
        mine.add("blue");
        System.out.println(mine);
    }
}
