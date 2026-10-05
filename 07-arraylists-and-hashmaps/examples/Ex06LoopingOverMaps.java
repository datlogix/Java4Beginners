// Example 6: visiting every entry in a map, and the three kinds of map.
// Run it with:  java Ex06LoopingOverMaps.java

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ex06LoopingOverMaps {
    public static void main(String[] args) {
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Yaa", 21);
        ages.put("Kwabena", 19);
        ages.put("Adwoa", 23);
        ages.put("Kwaku", 20);

        // keySet(): every key
        for (String name : ages.keySet()) {
            System.out.println(name + " is " + ages.get(name));
        }

        // values(): every value
        int total = 0;
        for (int age : ages.values()) {
            total += age;
        }
        System.out.println("Average age: " + total / (double) ages.size());

        // entrySet(): every key AND value together, the most efficient way
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // HashMap keeps NO particular order. Two alternatives:
        Map<String, Integer> sorted = new TreeMap<>(ages);        // keys in sorted order
        System.out.println("TreeMap:       " + sorted);

        Map<String, Integer> inOrder = new LinkedHashMap<>();     // keys in the order you added them
        inOrder.put("first", 1);
        inOrder.put("second", 2);
        inOrder.put("third", 3);
        System.out.println("LinkedHashMap: " + inOrder);
        System.out.println("HashMap:       " + ages);
    }
}
