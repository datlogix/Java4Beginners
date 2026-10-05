// Example 3: interfaces can also contain default methods (with a body, which
// classes may override) and static helper methods.
// Run it with:  java Ex03DefaultAndStaticMethods.java

public class Ex03DefaultAndStaticMethods {

    interface Sensor {
        double read();                       // abstract: every sensor must say how
        String unit();

        default String describe() {          // default: every sensor gets this for free
            return String.format("%.2f %s", read(), unit());
        }

        default boolean isAbove(double limit) {
            return read() > limit;
        }

        static double celsiusToFahrenheit(double c) {    // static: called on the interface
            return c * 9 / 5 + 32;
        }
    }

    static class Thermometer implements Sensor {
        public double read() { return 31.4; }
        public String unit() { return "C"; }
    }

    static class LightSensor implements Sensor {
        public double read() { return 742; }
        public String unit() { return "lux"; }
        @Override
        public String describe() {           // overriding a default is allowed
            return "Light level: " + Sensor.super.describe();
        }
    }

    public static void main(String[] args) {
        Sensor[] sensors = {new Thermometer(), new LightSensor()};
        for (Sensor s : sensors) {
            System.out.println(s.describe() + (s.isAbove(30) ? "  (above 30)" : ""));
        }
        System.out.println("31.4 C = " + Sensor.celsiusToFahrenheit(31.4) + " F");
    }
}
