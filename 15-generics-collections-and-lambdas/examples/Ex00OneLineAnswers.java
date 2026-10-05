// Example 0: the hook. A month of sales in a makerspace shop, and six
// questions about it, each answered by ONE Java statement.
// Run it with:  java Ex00OneLineAnswers.java

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Ex00OneLineAnswers {

    record Sale(String product, String category, double price, int units) {
        double revenue() { return price * units; }
    }

    public static void main(String[] args) {
        List<Sale> sales = List.of(
                new Sale("Arduino Uno", "Boards", 180.0, 14), new Sale("ESP32", "Boards", 95.0, 31),
                new Sale("Raspberry Pi 5", "Boards", 1150.0, 4), new Sale("micro:bit", "Boards", 260.0, 9),
                new Sale("LED pack", "Components", 15.5, 120), new Sale("Resistor kit", "Components", 45.0, 38),
                new Sale("Capacitor kit", "Components", 55.0, 22), new Sale("Jumper wires", "Components", 12.0, 150),
                new Sale("Servo motor", "Motors", 45.0, 40), new Sale("DC gear motor", "Motors", 38.0, 52),
                new Sale("Stepper motor", "Motors", 120.0, 11), new Sale("Ultrasonic sensor", "Sensors", 25.0, 64),
                new Sale("Soil moisture sensor", "Sensors", 18.0, 47), new Sale("Temperature sensor", "Sensors", 30.0, 35),
                new Sale("Soldering iron", "Tools", 210.0, 8), new Sale("Multimeter", "Tools", 175.0, 12));

        // 1. Total revenue
        double total = sales.stream().mapToDouble(Sale::revenue).sum();
        System.out.printf("1. Total revenue: GHS %,.2f%n", total);

        // 2. The three best-selling products by revenue
        List<String> top3 = sales.stream().sorted(Comparator.comparingDouble(Sale::revenue).reversed())
                .limit(3).map(Sale::product).toList();
        System.out.println("2. Top three by revenue: " + top3);

        // 3. Revenue for each category
        Map<String, Double> byCategory = sales.stream()
                .collect(Collectors.groupingBy(Sale::category, TreeMap::new, Collectors.summingDouble(Sale::revenue)));
        System.out.println("3. Revenue by category: " + byCategory);

        // 4. Everything under GHS 30, cheapest first
        System.out.println("4. Pocket-money items: " + sales.stream().filter(s -> s.price() < 30)
                .sorted(Comparator.comparingDouble(Sale::price)).map(Sale::product).toList());

        // 5. How many units were sold altogether?
        System.out.println("5. Units sold: " + sales.stream().mapToInt(Sale::units).sum());

        // 6. The average price in each category
        Map<String, Double> avgPrice = sales.stream()
                .collect(Collectors.groupingBy(Sale::category, TreeMap::new, Collectors.averagingDouble(Sale::price)));
        avgPrice.forEach((cat, avg) -> System.out.printf("6. Average %s price: GHS %.2f%n", cat, avg));
    }
}
