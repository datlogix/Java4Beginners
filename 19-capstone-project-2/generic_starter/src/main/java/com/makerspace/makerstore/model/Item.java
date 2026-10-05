package com.makerspace.makerstore.model;

import java.util.Objects;

/** Anything the makerspace holds. Two items are equal if they have the same ID. */
public abstract class Item {
    private final String id;
    private final String name;
    private final String category;

    protected Item(String id, String name, String category) {
        if (id == null || id.isBlank() || name == null || name.isBlank()) {
            throw new IllegalArgumentException("An item needs an ID and a name.");
        }
        this.id = id;
        this.name = name;
        this.category = category == null ? "" : category;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }

    /** The item's total value in GHS. */
    public abstract double value();

    @Override
    public boolean equals(Object other) {
        return other instanceof Item item && id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%-6s %-24s %-12s GHS %8.2f", id, name, category, value());
    }
}
