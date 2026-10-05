// Example 3: the Collections Framework: List, Set, Queue, Deque and Map,
// and the classes that implement them.
// Run it with:  java Ex03ChoosingACollection.java

import java.util.*;

public class Ex03ChoosingACollection {
    public static void main(String[] args) {
        // LIST: ordered, allows duplicates, access by index
        List<String> arrivals = new ArrayList<>(List.of("Ama", "Kojo", "Ama"));
        System.out.println("List:          " + arrivals);

        // SET: no duplicates
        Set<String> unique = new LinkedHashSet<>(arrivals);
        System.out.println("Set:           " + unique);

        // QUEUE: first in, first out (like people waiting at a counter)
        Queue<String> counter = new ArrayDeque<>();
        counter.offer("first customer");
        counter.offer("second customer");
        counter.offer("third customer");
        System.out.println("Queue serves:  " + counter.poll() + ", then " + counter.poll());

        // DEQUE used as a STACK: last in, first out (like plates)
        Deque<String> plates = new ArrayDeque<>();
        plates.push("bottom plate");
        plates.push("middle plate");
        plates.push("top plate");
        System.out.println("Stack gives:   " + plates.pop());

        // PRIORITY QUEUE: always gives the SMALLEST item first (or by your Comparator)
        PriorityQueue<Integer> urgent = new PriorityQueue<>(List.of(40, 5, 23, 1, 17));
        System.out.print("PriorityQueue: ");
        while (!urgent.isEmpty()) {
            System.out.print(urgent.poll() + " ");
        }
        System.out.println();

        // A priority queue with a Comparator: longest word first
        PriorityQueue<String> longest = new PriorityQueue<>(Comparator.comparingInt(String::length).reversed());
        longest.addAll(List.of("ox", "elephant", "cat", "giraffe"));
        System.out.println("Longest first: " + longest.poll());

        // MAP: key -> value
        Map<String, Integer> stock = new TreeMap<>(Map.of("LED", 120, "servo", 40));
        System.out.println("Map:           " + stock);
    }
}
