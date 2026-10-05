import java.util.ArrayList;
import java.util.List;

/** Resistors connected in series across a power supply. */
public class Circuit {
    private final PowerSupply supply;
    private final List<Resistor> resistors = new ArrayList<>();

    public Circuit(PowerSupply supply) {
        this.supply = supply;
    }

    public void add(Resistor resistor) {
        // TODO: refuse a duplicate label
    }

    public void remove(String label) {
        // TODO: throw IllegalArgumentException if no resistor has that label
    }

    public double totalResistance() {
        return 0;   // TODO
    }

    /** I = V / R_total. Throws IllegalArgumentException if there are no resistors. */
    public double current() {
        return 0;   // TODO
    }

    /** V = I x R for one resistor. */
    public double voltageAcross(String label) {
        return 0;   // TODO
    }

    /** P = I squared x R for one resistor. */
    public double powerIn(String label) {
        return 0;   // TODO
    }

    /** Returns the resistors dissipating more power than their rating. */
    public List<Resistor> overloaded() {
        return new ArrayList<>();   // TODO
    }

    /** Returns a multi-line report, with warnings for overloads and too much current. */
    public String analyse() {
        return "TODO";
    }

    private Resistor find(String label) {
        // TODO: return the resistor with this label, or throw IllegalArgumentException
        return null;
    }
}
