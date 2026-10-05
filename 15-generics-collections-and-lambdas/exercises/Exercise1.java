// Exercise 1: Your own generic toolkit.
//
// Write these GENERIC methods (no streams allowed in this exercise: use
// loops, so you see what streams do for you in Exercise 2):
//
//   filter(items, test)      a NEW list of the items that pass the test
//   map(items, f)            a NEW list of f applied to each item
//   smallest(items)          the smallest item, for any Comparable type
//                            (throw IllegalArgumentException for an empty list)
//   countBy(items, key)      a Map from each key to how many items have it
//                            (e.g. count words by their first letter)
//   firstMatch(items, test)  an Optional: the first item that passes, or
//                            Optional.empty() if none does
//
// And the generic record Pair<A, B> needs a method swap() that returns a
// Pair<B, A> with the two values the other way round.
//
// Each method's signature is given. Replace the body.
//
// Run it with:  java Exercise1.java

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercise1 {

    record Pair<A, B>(A first, B second) {
        Pair<B, A> swap() {
            return null;   // TODO
        }
    }

    static <T> List<T> filter(List<T> items, Predicate<T> test) {
        return new ArrayList<>();   // TODO
    }

    static <T, R> List<R> map(List<T> items, Function<T, R> f) {
        return new ArrayList<>();   // TODO
    }

    static <T extends Comparable<T>> T smallest(List<T> items) {
        return null;   // TODO
    }

    static <T, K> Map<K, Integer> countBy(List<T> items, Function<T, K> key) {
        Map<K, Integer> counts = new TreeMap<>();
        // TODO
        return counts;
    }

    static <T> Optional<T> firstMatch(List<T> items, Predicate<T> test) {
        return Optional.empty();   // TODO
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

    public static void main(String[] args) {
        List<Integer> numbers = List.of(15, 4, 42, 8, 23, 16);
        List<String> words = List.of("mango", "apple", "avocado", "pawpaw", "melon", "banana");

        check("filter evens", filter(numbers, n -> n % 2 == 0), "[4, 42, 8, 16]");
        check("filter long words", filter(words, w -> w.length() > 5), "[avocado, pawpaw, banana]");
        check("filter keeps the original", numbers.size(), 6);
        check("map squares", map(numbers, n -> n * n), "[225, 16, 1764, 64, 529, 256]");
        check("map lengths", map(words, String::length), "[5, 5, 7, 6, 5, 6]");
        check("map to a new type", map(List.of(1.5, 2.25), d -> "GHS " + d), "[GHS 1.5, GHS 2.25]");
        check("smallest number", smallest(numbers), 4);
        check("smallest word", smallest(words), "apple");
        boolean refused = false;
        try {
            smallest(new ArrayList<Integer>());
        } catch (IllegalArgumentException e) {
            refused = true;
        }
        check("smallest of nothing refused", refused, true);
        check("countBy first letter", countBy(words, w -> w.charAt(0)), "{a=2, b=1, m=2, p=1}");
        check("countBy even/odd", countBy(numbers, n -> n % 2 == 0 ? "even" : "odd"), "{even=4, odd=2}");
        check("firstMatch found", firstMatch(numbers, n -> n > 20), "Optional[42]");
        check("firstMatch none", firstMatch(words, w -> w.startsWith("z")), "Optional.empty");
        Pair<String, Integer> p = new Pair<>("Ama", 88);
        check("swap", p.swap(), "Pair[first=88, second=Ama]");
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
