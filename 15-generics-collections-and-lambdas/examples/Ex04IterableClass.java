// Example 4: make your own class work in a for-each loop by implementing Iterable.
// Run it with:  java Ex04IterableClass.java

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ex04IterableClass {

    static class Playlist implements Iterable<String> {
        private final List<String> songs = new ArrayList<>();

        void add(String song) { songs.add(song); }

        @Override
        public Iterator<String> iterator() {
            return songs.iterator();      // hand out the list's own iterator
        }
    }

    /** Counts down from a number, without storing anything. */
    static class Countdown implements Iterable<Integer> {
        private final int from;
        Countdown(int from) { this.from = from; }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {        // an "anonymous class": a class with no name
                private int next = from;
                public boolean hasNext() { return next > 0; }
                public Integer next() { return next--; }
            };
        }
    }

    public static void main(String[] args) {
        Playlist p = new Playlist();
        p.add("Ye Ye Ye");
        p.add("Second Sermon");
        for (String song : p) {              // works because Playlist is Iterable
            System.out.println("Now playing: " + song);
        }
        for (int n : new Countdown(5)) {
            System.out.print(n + " ");
        }
        System.out.println("Liftoff!");
    }
}
