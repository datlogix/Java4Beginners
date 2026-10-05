// Module 11 Project, Track B: AC Circuit Components
// Author: YOUR NAME

/**
 * A circuit component in an AC circuit. Every component has a resistance R
 * (ohms) and a reactance X (ohms) that may depend on the frequency f (hertz).
 * Its impedance magnitude is |Z| = sqrt(R^2 + X^2).
 */
public abstract class Component {
    private final String label;

    protected Component(String label) {
        // TODO: reject an empty label
        this.label = label;
    }

    public String getLabel() { return label; }

    /** Resistance in ohms. */
    public abstract double resistance();

    /** Reactance in ohms at frequency f: positive for inductors, negative for capacitors. */
    public abstract double reactance(double frequency);

    /** Impedance magnitude in ohms at frequency f. */
    public double impedance(double frequency) {
        return 0;   // TODO
    }

    /** A short description of the component's value, e.g. "4.7 kOhm", "10 mH", "100 nF". */
    public abstract String valueText();

    @Override
    public String toString() {
        return label + " " + valueText();
    }

    /** Formats a value with an SI prefix: 4700 -> "4.7 k", 0.01 -> "10 m", 1e-7 -> "100 n". */
    public static String si(double value) {
        // TODO: choose the prefix (G, M, k, none, m, u, n, p) from the size of the value
        return String.valueOf(value);
    }
}
