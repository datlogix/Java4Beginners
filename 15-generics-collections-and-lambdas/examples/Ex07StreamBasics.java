// Example 7: streams. A stream is a PIPELINE: a source of items, some steps
// that filter or transform them, and a final step that produces a result.
// Run it with:  java Ex07StreamBasics.java

import java.util.Comparator;
import java.util.List;

public class Ex07StreamBasics {
    public static void main(String[] args) {
        List<Integer> marks = List.of(72, 45, 88, 50, 61, 94, 39, 77);

        // filter keeps the items that pass a test
        List<Integer> passed = marks.stream().filter(m -> m >= 50).toList();
        System.out.println("Passed: " + passed);

        // map changes every item into something else
        List<String> grades = marks.stream().map(m -> m >= 70 ? "A/B" : m >= 50 ? "C/D" : "F").toList();
        System.out.println("Grades: " + grades);

        // A longer pipeline: read it top to bottom, like a recipe
        List<Integer> topThree = marks.stream()
                .filter(m -> m >= 50)          // keep passes
                .sorted(Comparator.reverseOrder())   // biggest first
                .limit(3)                      // the first three
                .toList();                     // collect into a list
        System.out.println("Top three passes: " + topThree);

        // Terminal operations that give a single answer
        System.out.println("How many over 60? " + marks.stream().filter(m -> m > 60).count());
        System.out.println("Total: " + marks.stream().mapToInt(Integer::intValue).sum());
        System.out.println("Average: " + marks.stream().mapToInt(Integer::intValue).average().orElse(0));
        System.out.println("Highest: " + marks.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println("Anyone over 90? " + marks.stream().anyMatch(m -> m > 90));
        System.out.println("Everyone over 30? " + marks.stream().allMatch(m -> m > 30));

        // Streams don't change the original list
        System.out.println("Original: " + marks);

        // Streams of text
        List<String> words = List.of("Accra", "kumasi", "Tamale", "ho", "Cape Coast");
        System.out.println(words.stream().map(String::toUpperCase).filter(w -> w.length() > 2)
                .sorted().toList());
        System.out.println(String.join(" | ", words.stream().map(w -> w.substring(0, 2)).toList()));
    }
}
