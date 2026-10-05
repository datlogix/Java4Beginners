package com.makerspace.inventory;

/** One stock item. Gson turns records like this into JSON and back. */
public record Item(String code, String name, int quantity, double price) {
}
