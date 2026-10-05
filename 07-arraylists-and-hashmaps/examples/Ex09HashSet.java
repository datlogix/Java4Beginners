// Example 9: a HashSet holds UNIQUE values, with no order and no duplicates.
// Run it with:  java Ex09HashSet.java

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Ex09HashSet {
    public static void main(String[] args) {
        Set<String> visited = new HashSet<>();
        System.out.println(visited.add("Accra"));      // true: added
        System.out.println(visited.add("Kumasi"));     // true
        System.out.println(visited.add("Accra"));      // false: already there, not added again
        System.out.println(visited + ", size " + visited.size());
        System.out.println("Been to Tamale? " + visited.contains("Tamale"));

        // Removing duplicates from a list in one line
        List<Integer> rolls = List.of(3, 6, 3, 1, 6, 6, 2);
        Set<Integer> different = new TreeSet<>(rolls);     // TreeSet keeps them sorted
        System.out.println(rolls + " -> " + different);

        // Set operations
        Set<String> python = new TreeSet<>(List.of("Ama", "Kofi", "Esi", "Yaw"));
        Set<String> java = new TreeSet<>(List.of("Kofi", "Yaw", "Kwame"));

        Set<String> both = new TreeSet<>(python);
        both.retainAll(java);                  // intersection: in both
        Set<String> either = new TreeSet<>(python);
        either.addAll(java);                   // union: in either
        Set<String> pythonOnly = new TreeSet<>(python);
        pythonOnly.removeAll(java);            // difference: in python but not java

        System.out.println("Both courses:   " + both);
        System.out.println("Either course:  " + either);
        System.out.println("Python only:    " + pythonOnly);
    }
}
