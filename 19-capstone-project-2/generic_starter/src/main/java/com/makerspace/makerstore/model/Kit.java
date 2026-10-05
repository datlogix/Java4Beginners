package com.makerspace.makerstore.model;

import java.util.ArrayList;
import java.util.List;

/** A project kit: a bundle of other items, which may include OTHER KITS. */
public class Kit extends Item {
    private final List<Item> contents = new ArrayList<>();

    public Kit(String id, String name, String category) {
        super(id, name, category);
    }

    public void add(Item item) {
        // TODO: refuse to add a kit to itself (and, stretch: to any kit inside it)
        contents.add(item);
    }

    public List<Item> getContents() {
        return new ArrayList<>(contents);
    }

    /** RECURSIVE: the value of everything inside, including inside nested kits. */
    @Override
    public double value() {
        return 0;   // TODO
    }

    /** RECURSIVE: every non-kit item inside, at any depth (the full parts list). */
    public List<Item> allParts() {
        return new ArrayList<>();   // TODO
    }
}
