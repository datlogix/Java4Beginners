package com.makerspace.makerstore.model;

public class ItemNotFoundException extends MakerStoreException {
    public ItemNotFoundException(String id) {
        super("No item has the ID " + id);
    }
}
