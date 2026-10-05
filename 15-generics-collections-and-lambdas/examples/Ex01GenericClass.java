// Example 1: a generic class works with ANY type, chosen when you use it.
// Run it with:  java Ex01GenericClass.java

import java.util.ArrayList;
import java.util.List;

public class Ex01GenericClass {

    // T is a TYPE PARAMETER: a placeholder for "whatever type you put in".
    static class Box<T> {
        private T contents;

        void put(T item) { contents = item; }
        T get() { return contents; }
        boolean isEmpty() { return contents == null; }
    }

    // Two type parameters
    static class Pair<A, B> {
        private final A first;
        private final B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        A getFirst() { return first; }
        B getSecond() { return second; }

        @Override
        public String toString() { return "(" + first + ", " + second + ")"; }
    }

    public static void main(String[] args) {
        Box<String> words = new Box<>();
        words.put("Akwaaba");
        String w = words.get();          // no cast needed: Java KNOWS it's a String
        System.out.println(w.toUpperCase());
        // words.put(42);                 // error: incompatible types: int cannot be converted to String

        Box<Integer> numbers = new Box<>();
        numbers.put(42);
        System.out.println(numbers.get() + 1);

        Pair<String, Double> reading = new Pair<>("TMP-1", 31.4);
        System.out.println(reading + ": " + reading.getSecond() * 2);

        List<Pair<String, Integer>> scores = new ArrayList<>();
        scores.add(new Pair<>("Ama", 88));
        scores.add(new Pair<>("Kojo", 92));
        System.out.println(scores);
        // You've used generic classes since Module 7: ArrayList<String>, HashMap<String, Integer>...
    }
}
