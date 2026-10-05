// Exercise 2: Ten questions, ten streams.
//
// Answer each question below with ONE stream pipeline (a single statement,
// which may be split over several lines). Replace each "null" or "0" with
// your stream. No loops allowed!
//
// Useful pieces:  filter, map, mapToInt, mapToDouble, sorted, limit, distinct,
// count, sum, average, max, anyMatch, toList(), Collectors.groupingBy,
// Collectors.counting, Collectors.summingDouble, Collectors.joining,
// Comparator.comparing...
//
// Run it with:  java Exercise2.java

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Exercise2 {

    record Order(int id, String customer, String region, String product, int quantity, double unitPrice,
                 LocalDate date) {
        double total() {
            return quantity * unitPrice;
        }
    }

    static final List<Order> ORDERS = List.of(
            new Order(1, "Ama", "Greater Accra", "Notebook", 10, 4.50, LocalDate.of(2026, 9, 1)),
            new Order(2, "Kojo", "Ashanti", "Calculator", 1, 85.00, LocalDate.of(2026, 9, 2)),
            new Order(3, "Esi", "Central", "Pen", 25, 1.50, LocalDate.of(2026, 9, 2)),
            new Order(4, "Ama", "Greater Accra", "Calculator", 2, 85.00, LocalDate.of(2026, 9, 5)),
            new Order(5, "Yaw", "Ashanti", "Notebook", 4, 4.50, LocalDate.of(2026, 9, 9)),
            new Order(6, "Abena", "Volta", "Backpack", 1, 120.00, LocalDate.of(2026, 9, 12)),
            new Order(7, "Kojo", "Ashanti", "Pen", 50, 1.50, LocalDate.of(2026, 9, 15)),
            new Order(8, "Efua", "Central", "Notebook", 8, 4.50, LocalDate.of(2026, 9, 20)),
            new Order(9, "Esi", "Central", "Backpack", 2, 120.00, LocalDate.of(2026, 9, 21)),
            new Order(10, "Nii", "Greater Accra", "Pen", 10, 1.50, LocalDate.of(2026, 9, 28)));

    public static void main(String[] args) {
        // Q1. How many orders were for more than 5 items?
        long q1 = 0;
        // Q2. The total value of every order, added up.
        double q2 = 0;
        // Q3. The ids of the orders from Ashanti, in a list.
        List<Integer> q3 = null;
        // Q4. Every DIFFERENT customer name, in alphabetical order.
        List<String> q4 = null;
        // Q5. The product of the single most valuable order.
        String q5 = null;
        // Q6. Did anyone order more than 40 of something? (true/false)
        boolean q6 = false;
        // Q7. The number of orders in each region (a TreeMap, so it's in alphabetical order).
        Map<String, Long> q7 = null;
        // Q8. The total amount spent by each customer (a TreeMap).
        Map<String, Double> q8 = null;
        // Q9. The ids of the three most valuable orders, most valuable first.
        List<Integer> q9 = null;
        // Q10. The products ordered in the first week (1-7 September), as one String
        //      separated by ", " with no repeats, e.g. "Calculator, Notebook, Pen".
        String q10 = null;

        check("Q1", q1, 5);
        check("Q2", q2, 841.5);
        check("Q3", q3, "[2, 5, 7]");
        check("Q4", q4, "[Abena, Ama, Efua, Esi, Kojo, Nii, Yaw]");
        check("Q5", q5, "Backpack");
        check("Q6", q6, true);
        check("Q7", q7, "{Ashanti=3, Central=3, Greater Accra=3, Volta=1}");
        check("Q8", q8, "{Abena=120.0, Ama=215.0, Efua=36.0, Esi=277.5, Kojo=160.0, Nii=15.0, Yaw=18.0}");
        check("Q9", q9, "[9, 4, 6]");
        check("Q10", q10, "Calculator, Notebook, Pen");
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }

    // ---------------- Don't change anything below this line ----------------

    static int passed = 0, failed = 0;

    static void check(String description, Object actual, Object expected) {
        if (String.valueOf(actual).equals(String.valueOf(expected))) {
            passed++;
            System.out.println("PASS  " + description);
        } else {
            failed++;
            System.out.println("FAIL  " + description + ": got " + actual + ", expected " + expected);
        }
    }
}
