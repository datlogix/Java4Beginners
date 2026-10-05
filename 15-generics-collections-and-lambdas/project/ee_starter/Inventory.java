import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * A store of any kind of StockItem. Generic, with a BOUND: T must be a StockItem,
 * so the methods here can call quantity(), unitPrice() and stockValue() on it.
 */
public class Inventory<T extends StockItem> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        // TODO: refuse a duplicate part number
    }

    public Optional<T> find(String partNo) {
        return Optional.empty();   // TODO (a stream with filter and findFirst)
    }

    public List<T> where(Predicate<T> test) {
        return new ArrayList<>();   // TODO
    }

    public double totalValue() {
        return 0;   // TODO
    }

    public List<T> reorderList() {
        return new ArrayList<>();   // TODO: items needing reorder, lowest quantity first
    }

    public List<T> all() {
        return new ArrayList<>(items);
    }
}
