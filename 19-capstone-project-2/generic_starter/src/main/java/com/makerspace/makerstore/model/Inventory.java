package com.makerspace.makerstore.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Everything the makerspace holds, its members, and the loans between them. */
public class Inventory {
    public static final int LOAN_DAYS = 14;

    private final Map<String, Item> items = new LinkedHashMap<>();
    private final Map<String, String> members = new LinkedHashMap<>();   // TODO: a Member class instead?
    private final List<Loan> loans = new ArrayList<>();

    public void add(Item item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("There's already an item " + item.getId());
        }
        items.put(item.getId(), item);
    }

    public Item find(String id) throws ItemNotFoundException {
        Item item = items.get(id);
        if (item == null) {
            throw new ItemNotFoundException(id);
        }
        return item;
    }

    public List<Item> all() {
        return new ArrayList<>(items.values());
    }

    public void addMember(String id, String name) {
        members.put(id, name);
    }

    /** Lends a tool for LOAN_DAYS days. */
    public Loan lend(String toolId, String memberId, LocalDate today) throws MakerStoreException {
        // TODO: find the tool (it must BE a Tool), check it isn't already on loan and the
        //       member exists, then record and return the Loan
        throw new MakerStoreException("lend isn't written yet");
    }

    public void returnTool(String toolId, LocalDate today) throws MakerStoreException {
        // TODO
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }

    public List<Loan> overdue(LocalDate today) {
        return new ArrayList<>();   // TODO (a stream)
    }

    public List<Component> lowStock() {
        return new ArrayList<>();   // TODO (a stream, with instanceof or a filter on the class)
    }

    // TODO: search(text), issue(componentId, amount), members' borrowing history...
}
