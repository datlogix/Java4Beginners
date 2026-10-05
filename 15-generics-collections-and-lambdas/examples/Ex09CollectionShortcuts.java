// Example 9: lambdas built into the collections themselves.
// Run it with:  java Ex09CollectionShortcuts.java

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Ex09CollectionShortcuts {
    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>(List.of("buy solder", "test motor", "buy wire", "write report"));

        tasks.removeIf(t -> t.startsWith("buy"));          // Module 7's tricky loop, in one line
        System.out.println(tasks);

        tasks.replaceAll(String::toUpperCase);            // change every item in place
        tasks.forEach(t -> System.out.println("- " + t));

        // computeIfAbsent: "get the list for this key, creating it first if needed"
        Map<String, List<String>> byLetter = new TreeMap<>();
        for (String name : List.of("Ama", "Kojo", "Akua", "Kwesi", "Yaw")) {
            byLetter.computeIfAbsent(name.substring(0, 1), k -> new ArrayList<>()).add(name);
        }
        System.out.println(byLetter);

        // merge: the counting pattern from Module 7
        Map<String, Integer> votes = new TreeMap<>();
        for (String v : List.of("yes", "no", "yes", "yes")) {
            votes.merge(v, 1, Integer::sum);
        }
        votes.forEach((answer, count) -> System.out.println(answer + ": " + count));
    }
}
