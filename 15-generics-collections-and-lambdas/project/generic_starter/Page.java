import java.util.ArrayList;
import java.util.List;

/**
 * Splits ANY list into pages of a fixed size, so long results can be shown a
 * few at a time. A generic class: Page<Movie>, Page<String>, Page<anything>.
 */
public class Page<T> {
    private final List<T> items;
    private final int pageSize;

    public Page(List<T> items, int pageSize) {
        // TODO: reject a page size below 1
        this.items = new ArrayList<>(items);
        this.pageSize = pageSize;
    }

    /** How many pages there are (an empty list has 0 pages). */
    public int pageCount() {
        return 0;   // TODO
    }

    /** The items on page number (1 is the first page). Throws IllegalArgumentException
     *  for a page that doesn't exist. */
    public List<T> page(int number) {
        return new ArrayList<>();   // TODO: items.subList(from, to) is useful
    }
}
