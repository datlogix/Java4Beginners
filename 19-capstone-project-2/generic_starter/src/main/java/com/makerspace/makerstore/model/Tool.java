package com.makerspace.makerstore.model;

/** A tool that members borrow and return. */
public class Tool extends Item {
    private final double price;
    private String condition;
    private boolean onLoan;

    public Tool(String id, String name, String category, double price, String condition) {
        super(id, name, category);
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative.");
        }
        this.price = price;
        this.condition = condition;
    }

    @Override
    public double value() {
        return price;
    }

    public String getCondition() { return condition; }
    public boolean isOnLoan() { return onLoan; }

    void setOnLoan(boolean onLoan) {        // package-private: only Inventory lends tools
        this.onLoan = onLoan;
    }

    // TODO: a way to record the condition when a tool comes back
}
