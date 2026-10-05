// Module 10 Project, Track B: Series Circuit Analyser
// Author: YOUR NAME

/** A resistor with a label (like "R1"), a resistance in ohms and a power rating in watts. */
public class Resistor {
    private final String label;
    private final double ohms;
    private final double powerRating;

    public Resistor(String label, double ohms, double powerRating) {
        // TODO: reject an empty label, ohms <= 0, or powerRating <= 0
        this.label = label;
        this.ohms = ohms;
        this.powerRating = powerRating;
    }

    /** Most hobby resistors are rated at a quarter of a watt. */
    public Resistor(String label, double ohms) {
        this(label, ohms, 0.25);
    }

    public String getLabel() { return label; }
    public double getOhms() { return ohms; }
    public double getPowerRating() { return powerRating; }

    @Override
    public String toString() {
        // TODO: e.g.  "R1: 4700 ohms (0.25 W)"
        return label;
    }
}
