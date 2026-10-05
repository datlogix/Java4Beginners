// Example 7: every class secretly extends Object, and inherits toString,
// equals and hashCode. Override them to make your objects behave well.
// Run it with:  java Ex07ObjectClass.java

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Ex07ObjectClass {

    static class PointNoEquals {
        final int x, y;
        PointNoEquals(int x, int y) { this.x = x; this.y = y; }
    }

    static class Point {
        final int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;                         // the very same object
            if (!(other instanceof Point)) return false;            // not a Point at all
            Point p = (Point) other;
            return x == p.x && y == p.y;                            // same data?
        }

        @Override
        public int hashCode() {               // equal objects MUST have equal hash codes
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        PointNoEquals a = new PointNoEquals(1, 2), b = new PointNoEquals(1, 2);
        System.out.println(a.equals(b));                // false: Object's equals is just ==

        Point p = new Point(1, 2), q = new Point(1, 2);
        System.out.println(p == q);                     // false: different objects
        System.out.println(p.equals(q));                // true: same data
        System.out.println(p.hashCode() == q.hashCode());

        // HashSet and HashMap rely on equals AND hashCode to spot duplicates:
        Set<Point> visited = new HashSet<>();
        visited.add(p);
        visited.add(q);                                 // recognised as a duplicate
        System.out.println(visited);                    // [(1, 2)]

        Object anything = p;                            // an Object variable can hold ANY object
        System.out.println(anything);                   // still prints with Point's toString
    }
}
