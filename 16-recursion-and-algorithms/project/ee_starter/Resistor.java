/** A single resistor: the base case of every recursive calculation. */
public record Resistor(double ohms) implements Network {

    public Resistor {
        if (ohms <= 0) {
            throw new IllegalArgumentException("Resistance must be positive: " + ohms);
        }
    }

    @Override
    public double resistance() {
        return ohms;
    }

    @Override
    public String describe() {
        return ohms == Math.rint(ohms) ? String.valueOf((long) ohms) : String.valueOf(ohms);
    }
}
