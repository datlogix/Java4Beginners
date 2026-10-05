// Example 8: enums can have fields, constructors and methods, so each value
// carries its own data.
// Run it with:  java Ex08EnumsWithFields.java

public class Ex08EnumsWithFields {

    enum Planet {
        MERCURY(3.303e23, 2.4397e6),
        EARTH(5.976e24, 6.37814e6),
        MARS(6.421e23, 3.3972e6),
        JUPITER(1.9e27, 7.1492e7);

        private static final double G = 6.67300E-11;
        private final double mass;      // kg
        private final double radius;    // m

        Planet(double mass, double radius) {      // an enum's constructor is private
            this.mass = mass;
            this.radius = radius;
        }

        double surfaceGravity() {
            return G * mass / (radius * radius);
        }

        double weightOf(double earthKg) {
            return earthKg * surfaceGravity() / EARTH.surfaceGravity();
        }
    }

    enum Grade {
        A(80, 4.0), B_PLUS(75, 3.5), B(70, 3.0), C_PLUS(65, 2.5), C(60, 2.0), D_PLUS(55, 1.5), D(50, 1.0), F(0, 0.0);

        final int minimumMark;
        final double point;

        Grade(int minimumMark, double point) {
            this.minimumMark = minimumMark;
            this.point = point;
        }

        static Grade forMark(int mark) {
            for (Grade g : values()) {           // values are in the order listed: A first
                if (mark >= g.minimumMark) {
                    return g;
                }
            }
            throw new IllegalArgumentException("Bad mark: " + mark);
        }
    }

    public static void main(String[] args) {
        for (Planet p : Planet.values()) {
            System.out.printf("A 60 kg person weighs %6.1f kg on %s%n", p.weightOf(60), p);
        }
        for (int mark : new int[]{91, 77, 63, 42}) {
            Grade g = Grade.forMark(mark);
            System.out.println(mark + " -> " + g + " (" + g.point + ")");
        }
    }
}
