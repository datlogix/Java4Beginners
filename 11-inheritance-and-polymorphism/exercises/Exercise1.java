// Exercise 1: A family of shapes.
//
// Write an ABSTRACT class Shape and four subclasses, so that every check in
// main() passes. Shape must have:
//   - a private final String name, set through the constructor, with a getter
//   - abstract methods  double area()  and  double perimeter()
//   - a normal method  String describe()  returning, e.g.,
//         "rectangle: area 12.00, perimeter 14.00"
//     (it calls area() and perimeter(): that's the polymorphism!)
//
// Subclasses:
//   Rectangle(width, height)        name "rectangle"
//   Square(side)                    name "square". A Square IS a Rectangle:
//                                   extend Rectangle and call super(side, side),
//                                   then fix the name (hint: give Rectangle a
//                                   protected constructor that takes a name too)
//   Circle(radius)                  name "circle"
//   Triangle(a, b, c)               name "triangle". Area by Heron's formula:
//                                   s = (a + b + c) / 2,  area = sqrt(s(s-a)(s-b)(s-c))
//
// Every constructor rejects lengths <= 0 with IllegalArgumentException, and
// Triangle also rejects sides that can't make a triangle (any side >= the
// sum of the other two).
//
// Finally, write the static method largest(List<Shape>) at the bottom.
//
// Run it with:  java Exercise1.java

import java.util.List;

public class Exercise1 {

    // TODO: abstract static class Shape { ... }

    // TODO: static class Rectangle extends Shape { ... }

    // TODO: static class Square extends Rectangle { ... }

    // TODO: static class Circle extends Shape { ... }

    // TODO: static class Triangle extends Shape { ... }

    /** Returns the shape with the biggest area (null for an empty list). */
    // TODO: static Shape largest(List<Shape> shapes) { ... }

    // ---------------- When your classes exist, remove the /* and */ below ----------------

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

    static boolean refuses(Runnable action) {
        try {
            action.run();
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static void main(String[] args) {
        /*
        Shape r = new Rectangle(4, 3);
        Shape s = new Square(5);
        Shape c = new Circle(1);
        Shape t = new Triangle(3, 4, 5);
        check("rectangle describe", r.describe(), "rectangle: area 12.00, perimeter 14.00");
        check("square describe", s.describe(), "square: area 25.00, perimeter 20.00");
        check("circle describe", c.describe(), "circle: area 3.14, perimeter 6.28");
        check("triangle describe", t.describe(), "triangle: area 6.00, perimeter 12.00");
        check("a Square is a Rectangle", s instanceof Rectangle, true);
        check("a Circle is a Shape", c instanceof Shape, true);
        check("zero width refused", refuses(() -> new Rectangle(0, 3)), true);
        check("negative radius refused", refuses(() -> new Circle(-2)), true);
        check("impossible triangle refused", refuses(() -> new Triangle(1, 2, 10)), true);
        check("flat triangle refused", refuses(() -> new Triangle(1, 2, 3)), true);
        List<Shape> shapes = List.of(r, s, c, t);
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.area();
        }
        check("total area", Math.round(total * 100) / 100.0, 46.14);
        check("largest is the square", largest(shapes).getName(), "square");
        check("largest of nothing", largest(List.of()), null);
        */
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
