// Example 7: the counting pattern. A map from "thing" to "how many".
// Run it with:  java Ex07Counting.java

import java.util.Map;
import java.util.TreeMap;

public class Ex07Counting {
    public static void main(String[] args) {
        String[] votes = {"Mango", "Banana", "Mango", "Pawpaw", "Mango", "Banana", "Orange"};

        Map<String, Integer> tally = new TreeMap<>();
        for (String vote : votes) {
            // If we've seen it, add 1; if not, start at 0 and add 1.
            tally.put(vote, tally.getOrDefault(vote, 0) + 1);
        }
        System.out.println(tally);

        // The same thing with merge(): "put 1, or ADD 1 to what's there"
        Map<Character, Integer> letters = new TreeMap<>();
        for (char c : "engineering".toCharArray()) {
            letters.merge(c, 1, Integer::sum);
        }
        System.out.println(letters);

        // Find the most common
        String winner = null;
        int best = 0;
        for (Map.Entry<String, Integer> e : tally.entrySet()) {
            if (e.getValue() > best) {
                best = e.getValue();
                winner = e.getKey();
            }
        }
        System.out.println("Favourite fruit: " + winner + " with " + best + " votes");
    }
}
