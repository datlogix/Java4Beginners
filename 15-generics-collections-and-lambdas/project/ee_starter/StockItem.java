// Module 15 Project, Track B: Parts Inventory Query Engine
// Author: YOUR NAME

/** Anything that can be held in stock. */
public interface StockItem {
    String partNo();
    int quantity();
    int reorderLevel();
    double unitPrice();

    default double stockValue() {
        return quantity() * unitPrice();
    }

    default boolean needsReorder() {
        return quantity() <= reorderLevel();
    }
}
