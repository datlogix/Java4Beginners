// Example 0: the hook. Count every word in a piece of text, find the
// most common ones, and list the words that appear only once.
// Run it with:  java Ex00WordFrequency.java
// Then paste your own text (a song, a speech, an essay) into TEXT and run it again.

import java.util.*;

public class Ex00WordFrequency {
    static final String TEXT = """
        The robot rolled into the lab at midnight. The lab was dark, and the
        robot was tired, but the robot had a job to do. It found the battery on
        the bench, it found the screwdriver in the drawer, and it found the
        engineer asleep at the desk. The robot did not wake the engineer. It
        fixed the broken arm by itself, plugged in the battery, and rolled back
        to the corner of the lab to wait for the morning.
        """;

    public static void main(String[] args) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : TEXT.toLowerCase().split("[^a-z']+")) {
            if (!word.isEmpty()) {
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(counts.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        int totalWords = 0;
        for (int c : counts.values()) {
            totalWords += c;
        }
        System.out.println(totalWords + " words, " + counts.size() + " different ones.");
        System.out.println();
        System.out.println("Top 8:");
        for (int i = 0; i < 8; i++) {
            Map.Entry<String, Integer> e = entries.get(i);
            System.out.printf("  %-10s %2d %s%n", e.getKey(), e.getValue(), "*".repeat(e.getValue()));
        }

        Set<String> once = new TreeSet<>();
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            if (e.getValue() == 1) {
                once.add(e.getKey());
            }
        }
        System.out.println();
        System.out.println("Used only once: " + once);
    }
}
