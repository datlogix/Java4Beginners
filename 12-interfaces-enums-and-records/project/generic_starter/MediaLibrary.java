import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A collection of Playable items, with star ratings. */
public class MediaLibrary {
    public static final Comparator<Playable> BY_TITLE = null;            // TODO (ignore capitals)
    public static final Comparator<Playable> LONGEST_FIRST = null;       // TODO
    public static final Comparator<Playable> BY_GENRE_THEN_TITLE = null; // TODO

    private final List<Playable> items = new ArrayList<>();
    private final Map<Playable, Integer> ratings = new HashMap<>();      // records make great keys!

    public void add(Playable item) {
        // TODO: refuse an item that's already in the library (records have equals!)
    }

    /** Rates the item with this title (any capitals) from 1 to 5 stars. */
    public void rate(String title, int stars) {
        // TODO: validate; throw IllegalArgumentException if no item has that title
    }

    /** Stars for this item, or 0 if it hasn't been rated. */
    public int starsFor(Playable item) {
        return ratings.getOrDefault(item, 0);
    }

    /** Returns a NEW list of every item, in the given order. */
    public List<Playable> sorted(Comparator<Playable> order) {
        return new ArrayList<>();   // TODO
    }

    public List<Playable> inGenre(Genre genre) {
        return new ArrayList<>();   // TODO
    }

    /** The n best-rated items, best first (ties broken by title). */
    public List<Playable> topRated(int n) {
        return new ArrayList<>();   // TODO
    }

    public int totalSeconds() {
        return 0;   // TODO
    }
}
