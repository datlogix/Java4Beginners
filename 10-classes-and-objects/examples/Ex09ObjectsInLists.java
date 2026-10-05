// Example 9: a list of objects, searched, filtered and sorted.
// Compare this with the parallel arrays of Module 6: each name and price now
// stays with its own object, whatever order the list is in.
// Run it with:  java Ex09ObjectsInLists.java

import java.util.ArrayList;
import java.util.List;

public class Ex09ObjectsInLists {

    static class Product {
        private final String name;
        private final double price;
        private int stock;

        Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        String getName() { return name; }
        double getPrice() { return price; }
        int getStock() { return stock; }
        boolean isLow() { return stock < 10; }
        double stockValue() { return price * stock; }

        @Override
        public String toString() {
            return String.format("%-12s GHS %6.2f  x%3d", name, price, stock);
        }
    }

    public static void main(String[] args) {
        List<Product> shop = new ArrayList<>();
        shop.add(new Product("Arduino Uno", 180.00, 12));
        shop.add(new Product("LED pack", 15.50, 4));
        shop.add(new Product("Breadboard", 25.00, 30));
        shop.add(new Product("Servo motor", 45.00, 7));

        double total = 0;
        for (Product p : shop) {
            System.out.println(p);
            total += p.stockValue();
        }
        System.out.printf("Total stock value: GHS %.2f%n", total);

        System.out.println("Running low:");
        for (Product p : shop) {
            if (p.isLow()) {
                System.out.println("  " + p.getName());
            }
        }

        // Sorting by a field: a lambda says how to compare two Products (Module 12)
        shop.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        System.out.println("Cheapest first: " + shop.get(0).getName());
    }
}
