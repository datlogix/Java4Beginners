// Example 6: throwing your own exceptions, to refuse bad input clearly.
// Run it with:  java Ex06ThrowingExceptions.java

public class Ex06ThrowingExceptions {

    /** Returns the current through a resistor, I = V / R. */
    static double current(double volts, double ohms) {
        if (ohms <= 0) {
            throw new IllegalArgumentException("Resistance must be positive, not " + ohms);
        }
        return volts / ohms;
    }

    static class Battery {
        private int charge = 100;

        void use(int amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount can't be negative: " + amount);
            }
            if (amount > charge) {
                throw new IllegalStateException("Only " + charge + "% left; can't use " + amount + "%");
            }
            charge -= amount;
        }
    }

    public static void main(String[] args) {
        System.out.println(current(12, 470));
        try {
            System.out.println(current(12, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Refused: " + e.getMessage());
        }

        Battery b = new Battery();
        b.use(70);
        try {
            b.use(50);
        } catch (IllegalStateException e) {
            System.out.println("Refused: " + e.getMessage());
        }
        // IllegalArgumentException: the ARGUMENT is bad.
        // IllegalStateException:    the argument is fine, but the OBJECT can't do it right now.
    }
}
