// Example 6: a Comparator is an order that lives OUTSIDE the class, so you
// can have as many orders as you like. Lambdas make them one-liners.
// Run it with:  java Ex06Comparator.java

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ex06Comparator {

    static class Phone {
        final String model;
        final double price;
        final int storageGb;

        Phone(String model, double price, int storageGb) {
            this.model = model;
            this.price = price;
            this.storageGb = storageGb;
        }

        String getModel() { return model; }
        double getPrice() { return price; }
        int getStorageGb() { return storageGb; }

        @Override
        public String toString() {
            return String.format("%-14s GHS %7.2f  %4d GB", model, price, storageGb);
        }
    }

    public static void main(String[] args) {
        List<Phone> phones = new ArrayList<>(List.of(
                new Phone("Tecno Spark", 1450, 128),
                new Phone("Samsung A15", 2300, 128),
                new Phone("Infinix Hot", 1600, 256),
                new Phone("iPhone 13", 7800, 128),
                new Phone("Itel A70", 950, 64)));

        // 1. A lambda that compares two phones
        phones.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        System.out.println("Cheapest first:  " + phones.get(0).getModel());

        // 2. The same, built from a "key": Comparator.comparing...(what to compare by)
        phones.sort(Comparator.comparingDouble(Phone::getPrice).reversed());
        System.out.println("Dearest first:   " + phones.get(0).getModel());

        // 3. Several keys: storage (most first), then price (cheapest first)
        phones.sort(Comparator.comparingInt(Phone::getStorageGb).reversed()
                .thenComparingDouble(Phone::getPrice));
        phones.forEach(System.out::println);

        // Phone::getPrice is a METHOD REFERENCE: shorthand for p -> p.getPrice()
    }
}
