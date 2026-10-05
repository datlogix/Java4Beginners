import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** A merge sort that sorts ANY kind of item, in any order you give it. */
public class Sorting {

    /** Returns a NEW sorted list; the original is unchanged. Base case: 0 or 1 items. */
    public static <T> List<T> mergeSort(List<T> items, Comparator<T> order) {
        // TODO: split in half, mergeSort each half, merge them with order.compare(a, b)
        return new ArrayList<>(items);
    }
}
