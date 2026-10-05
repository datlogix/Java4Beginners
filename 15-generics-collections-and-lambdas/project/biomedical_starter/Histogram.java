import java.util.Map;
import java.util.TreeMap;

/**
 * Counts how often each category appears, and draws a text bar chart.
 * Generic: a Histogram<String> of departments, a Histogram<Integer> of
 * triage levels, a Histogram<Integer> of age groups...
 */
public class Histogram<T extends Comparable<T>> {
    private final Map<T, Integer> counts = new TreeMap<>();

    public void add(T category) {
        // TODO
    }

    public int count(T category) {
        return counts.getOrDefault(category, 0);
    }

    public int total() {
        return 0;   // TODO
    }

    /** One line per category:  "Dental        12 ############"  (scaled so the longest bar is 40). */
    public String chart() {
        return "TODO";
    }
}
