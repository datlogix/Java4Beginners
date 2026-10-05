// Example 2: generic METHODS, and bounded types. "T extends Comparable<T>"
// means "any type T, as long as T objects can be compared with each other".
// Run it with:  java Ex02GenericMethods.java

import java.util.List;

public class Ex02GenericMethods {

    /** Returns the largest item in a list of anything comparable. */
    static <T extends Comparable<T>> T max(List<T> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("max of an empty list");
        }
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) {
                best = item;
            }
        }
        return best;
    }

    /** Swaps two elements of an array of anything. */
    static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /** Adds up a list of any kind of number: Integer, Double, Long... */
    static double sum(List<? extends Number> numbers) {     // ? means "some type that extends Number"
        double total = 0;
        for (Number n : numbers) {
            total += n.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(max(List.of(3, 41, 7, 19)));               // Integers
        System.out.println(max(List.of("mango", "apple", "pawpaw")));  // Strings: alphabetically last
        System.out.println(max(List.of(2.5, 9.75, 1.0)));             // Doubles

        String[] planets = {"Mercury", "Venus", "Earth"};
        swap(planets, 0, 2);
        System.out.println(String.join(", ", planets));

        System.out.println(sum(List.of(1, 2, 3)));
        System.out.println(sum(List.of(1.5, 2.25)));
        // max(List.of(new Object(), new Object()));   // error: Object isn't Comparable
    }
}
