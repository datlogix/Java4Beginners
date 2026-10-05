// Example 9: records. A one-line class for plain, unchangeable data.
// Java writes the constructor, getters, equals, hashCode and toString for you.
// Run it with:  java Ex09Records.java

import java.util.HashSet;
import java.util.Set;

public class Ex09Records {

    record Point(int x, int y) { }

    record Reading(String sensor, double value, String unit) {
        // A "compact constructor" can check the values
        Reading {
            if (sensor == null || sensor.isBlank()) {
                throw new IllegalArgumentException("A reading needs a sensor name.");
            }
        }

        // Records can have extra methods too
        String describe() {
            return sensor + ": " + value + " " + unit;
        }
    }

    public static void main(String[] args) {
        Point p = new Point(3, 4);
        System.out.println(p);                          // Point[x=3, y=4]
        System.out.println(p.x() + ", " + p.y());       // getters are called x() and y()
        System.out.println(p.equals(new Point(3, 4)));  // true: equals compares the data

        Set<Point> set = new HashSet<>();
        set.add(new Point(1, 1));
        set.add(new Point(1, 1));
        System.out.println(set.size());                 // 1: hashCode works too

        Reading r = new Reading("TMP-1", 31.4, "C");
        System.out.println(r.describe());
        // r.value = 40;        // error: records are immutable (their fields are final)

        Reading moved = new Reading(r.sensor(), 32.0, r.unit());   // "change" = make a new one
        System.out.println(moved);
    }
}
