// Example 8: collections inside collections. A map whose values are lists.
// Run it with:  java Ex08NestedCollections.java

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Ex08NestedCollections {
    public static void main(String[] args) {
        // Each student has a LIST of marks
        Map<String, List<Integer>> marks = new TreeMap<>();
        marks.put("Akua", new ArrayList<>(List.of(70, 82, 91)));
        marks.put("Kofi", new ArrayList<>(List.of(55, 61)));

        // Add a mark to an existing student
        marks.get("Kofi").add(74);

        // Add a mark for a student who might not exist yet
        String name = "Esi";
        if (!marks.containsKey(name)) {
            marks.put(name, new ArrayList<>());
        }
        marks.get(name).add(88);

        for (Map.Entry<String, List<Integer>> entry : marks.entrySet()) {
            int total = 0;
            for (int m : entry.getValue()) {
                total += m;
            }
            double average = total / (double) entry.getValue().size();
            System.out.printf("%-5s %-14s average %.1f%n", entry.getKey(), entry.getValue(), average);
        }

        // A list of maps works too: each map is one record
        List<Map<String, String>> books = new ArrayList<>();
        books.add(Map.of("title", "Things Fall Apart", "author", "Chinua Achebe"));
        books.add(Map.of("title", "Homegoing", "author", "Yaa Gyasi"));
        for (Map<String, String> book : books) {
            System.out.println(book.get("title") + " by " + book.get("author"));
        }
    }
}
