// Example 5: abstract classes and methods. "Shape" is an idea, not a real
// shape, so you can't create one. But every real shape MUST provide area().
// Run it with:  java Ex05AbstractClasses.java

import java.util.List;

public class Ex05AbstractClasses {

    abstract static class Shape {
        private final String name;

        Shape(String name) {
            this.name = name;
        }

        abstract double area();           // no body: each subclass must write one
        abstract double perimeter();

        // A normal method can USE the abstract ones
        String describe() {
            return String.format("%-10s area %7.2f   perimeter %6.2f", name, area(), perimeter());
        }
    }

    static class Rectangle extends Shape {
        private final double width, height;
        Rectangle(double width, double height) {
            super("rectangle");
            this.width = width;
            this.height = height;
        }
        @Override double area() { return width * height; }
        @Override double perimeter() { return 2 * (width + height); }
    }

    static class Circle extends Shape {
        private final double radius;
        Circle(double radius) {
            super("circle");
            this.radius = radius;
        }
        @Override double area() { return Math.PI * radius * radius; }
        @Override double perimeter() { return 2 * Math.PI * radius; }
    }

    static class Triangle extends Shape {
        private final double a, b, c;
        Triangle(double a, double b, double c) {
            super("triangle");
            this.a = a; this.b = b; this.c = c;
        }
        @Override double perimeter() { return a + b + c; }
        @Override double area() {                  // Heron's formula
            double s = perimeter() / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }
    }

    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Rectangle(4, 3), new Circle(2), new Triangle(3, 4, 5));
        double total = 0;
        for (Shape s : shapes) {
            System.out.println(s.describe());
            total += s.area();
        }
        System.out.printf("Total area: %.2f%n", total);
        // Shape mystery = new Shape("blob");   // error: Shape is abstract; cannot be instantiated
    }
}
