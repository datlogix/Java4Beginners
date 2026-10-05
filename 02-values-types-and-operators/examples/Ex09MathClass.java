// Example 9: the Math class has ready-made maths methods.
// Run it with:  java Ex09MathClass.java

public class Ex09MathClass {
    public static void main(String[] args) {
        System.out.println(Math.sqrt(144));        // 12.0     square root
        System.out.println(Math.pow(2, 10));       // 1024.0   2 to the power 10
        System.out.println(Math.abs(-7));          // 7        distance from zero
        System.out.println(Math.max(4, 9));        // 9
        System.out.println(Math.min(4, 9));        // 4
        System.out.println(Math.round(2.5));       // 3        nearest whole number
        System.out.println(Math.floor(2.9));       // 2.0      round down
        System.out.println(Math.ceil(2.1));        // 3.0      round up
        System.out.println(Math.PI);               // 3.141592653589793

        // The area of a circle with radius 5: pi times r squared
        System.out.println(Math.PI * Math.pow(5, 2));

        // Ohm's law: current = voltage / resistance (12 V across 470 ohms)
        System.out.println("Current: " + (12.0 / 470) + " A");

        // The hypotenuse of a 3-4-5 triangle
        System.out.println(Math.sqrt(3 * 3 + 4 * 4));   // 5.0
    }
}
