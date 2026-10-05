// Example 5: a HashMap stores KEY -> VALUE pairs. You look values up by key.
// Run it with:  java Ex05HashMapBasics.java

import java.util.HashMap;

public class Ex05HashMapBasics {
    public static void main(String[] args) {
        HashMap<String, String> capitals = new HashMap<>();     // keys: Strings, values: Strings
        capitals.put("Ghana", "Accra");
        capitals.put("Nigeria", "Abuja");
        capitals.put("Kenya", "Nairobi");
        capitals.put("Togo", "Lome");
        System.out.println(capitals);

        System.out.println("Capital of Kenya: " + capitals.get("Kenya"));
        System.out.println("Capital of Mali:  " + capitals.get("Mali"));     // null: no such key
        System.out.println("Capital of Mali:  " + capitals.getOrDefault("Mali", "(unknown)"));

        System.out.println("Know Ghana? " + capitals.containsKey("Ghana"));
        System.out.println("Is Accra a capital? " + capitals.containsValue("Accra"));

        capitals.put("Togo", "LOME");    // a key can only appear ONCE: put replaces the old value
        capitals.remove("Nigeria");
        System.out.println(capitals + " (" + capitals.size() + " entries)");

        // Keys and values can be different types
        HashMap<String, Integer> stock = new HashMap<>();
        stock.put("resistor", 250);
        stock.put("LED", 80);
        stock.put("LED", stock.get("LED") - 5);    // sell 5 LEDs
        System.out.println(stock);
    }
}
