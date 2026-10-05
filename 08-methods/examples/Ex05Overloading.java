// Example 5: overloading. Several methods can share a name if their
// parameter lists are different. Java picks the one that matches.
// Run it with:  java Ex05Overloading.java

public class Ex05Overloading {

    static double area(double radius) {                 // a circle
        return Math.PI * radius * radius;
    }

    static double area(double width, double height) {   // a rectangle
        return width * height;
    }

    static String repeat(String text, int times) {
        return text.repeat(times);
    }

    static String repeat(char c, int times) {
        return String.valueOf(c).repeat(times);
    }

    public static void main(String[] args) {
        System.out.printf("Circle, radius 2:     %.2f%n", area(2));
        System.out.printf("Rectangle, 3 by 4:    %.2f%n", area(3, 4));
        System.out.println(repeat("ab", 3));
        System.out.println(repeat('*', 5));

        // You've been using overloading since Module 1: println has versions for
        // String, int, double, char, boolean... That's why all of these work.
        System.out.println(42);
        System.out.println(4.2);
        System.out.println('c');
        System.out.println(true);
    }
}
