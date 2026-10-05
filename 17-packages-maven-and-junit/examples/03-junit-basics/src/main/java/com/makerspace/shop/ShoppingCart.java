package com.makerspace.shop;

import java.util.LinkedHashMap;
import java.util.Map;

/** A shopping cart: items and quantities, a bulk discount, and VAT. */
public class ShoppingCart {
    public static final double VAT_RATE = 0.15;

    private final Map<String, Double> prices = new LinkedHashMap<>();
    private final Map<String, Integer> quantities = new LinkedHashMap<>();

    public void add(String item, double unitPrice, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive: " + quantity);
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Price can't be negative: " + unitPrice);
        }
        prices.put(item, unitPrice);
        quantities.merge(item, quantity, Integer::sum);
    }

    public void remove(String item) {
        if (quantities.remove(item) == null) {
            throw new IllegalArgumentException("Not in the cart: " + item);
        }
        prices.remove(item);
    }

    public int itemCount() {
        int count = 0;
        for (int q : quantities.values()) {
            count += q;
        }
        return count;
    }

    public boolean isEmpty() {
        return quantities.isEmpty();
    }

    public double subtotal() {
        double total = 0;
        for (String item : quantities.keySet()) {
            total += prices.get(item) * quantities.get(item);
        }
        return total;
    }

    /** Bulk discount: 5% off from GHS 500, 10% off from GHS 1000. */
    public static double discountRate(double subtotal) {
        if (subtotal >= 1000) {
            return 0.10;
        } else if (subtotal >= 500) {
            return 0.05;
        }
        return 0;
    }

    /** The amount to pay: subtotal, less the discount, plus VAT on what's left. */
    public double total() {
        double afterDiscount = subtotal() * (1 - discountRate(subtotal()));
        return Math.round(afterDiscount * (1 + VAT_RATE) * 100) / 100.0;
    }
}
