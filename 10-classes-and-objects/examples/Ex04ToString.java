// Example 4: toString() decides what an object looks like when it's printed.
// Run it with:  java Ex04ToString.java

import java.util.ArrayList;

public class Ex04ToString {

    static class Plain {
        int value = 42;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override                          // "I'm replacing the standard version" (Module 11)
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Plain());           // something like Ex04ToString$Plain@1b6d3586

        Point p = new Point(3, 4);
        System.out.println(p);                     // (3, 4): println calls toString() for you
        System.out.println("Point: " + p);         // so does joining with +

        ArrayList<Point> path = new ArrayList<>();
        path.add(new Point(0, 0));
        path.add(new Point(1, 2));
        System.out.println(path);                  // [(0, 0), (1, 2)]
    }
}
