// Example 10: putting it together. A small phone book with a menu.
// Run it with:  java Ex10PhoneBook.java

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Ex10PhoneBook {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, String> book = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        book.put("Ama Serwaa", "024 555 0101");
        book.put("Kojo Antwi", "020 555 0199");

        String choice;
        do {
            System.out.println();
            System.out.println("(a)dd  (f)ind  (d)elete  (l)ist  (q)uit");
            System.out.print("> ");
            choice = in.nextLine().trim().toLowerCase();

            switch (choice) {
                case "a" -> {
                    System.out.print("Name: ");
                    String name = in.nextLine().trim();
                    System.out.print("Number: ");
                    book.put(name, in.nextLine().trim());
                    System.out.println("Saved.");
                }
                case "f" -> {
                    System.out.print("Name: ");
                    String name = in.nextLine().trim();
                    System.out.println(book.getOrDefault(name, "Not found."));
                }
                case "d" -> {
                    System.out.print("Name: ");
                    String name = in.nextLine().trim();
                    System.out.println(book.remove(name) != null ? "Deleted." : "Not found.");
                }
                case "l" -> {
                    for (Map.Entry<String, String> e : book.entrySet()) {
                        System.out.printf("  %-15s %s%n", e.getKey(), e.getValue());
                    }
                    System.out.println("  (" + book.size() + " contacts)");
                }
                case "q" -> System.out.println("Bye.");
                default -> System.out.println("Unknown choice.");
            }
        } while (!choice.equals("q"));
    }
}
