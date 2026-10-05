package com.makerspace.makerstore.model;

/** A consumable part, such as LEDs or resistors, kept in quantity. */
public class Component extends Item {
    private final double unitPrice;
    private int quantity;
    private final int reorderLevel;

    public Component(String id, String name, String category, double unitPrice, int quantity, int reorderLevel) {
        super(id, name, category);
        // TODO: validate (no negative price, quantity or reorder level)
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    @Override
    public double value() {
        return unitPrice * quantity;
    }

    public int getQuantity() { return quantity; }
    public int getReorderLevel() { return reorderLevel; }

    public boolean isLow() {
        return quantity <= reorderLevel;
    }

    // TODO: issue(int amount), which throws OutOfStockException (write it!) if there aren't enough
}
