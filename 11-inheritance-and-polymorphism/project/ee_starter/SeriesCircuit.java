import java.util.ArrayList;
import java.util.List;

/** Components in series across an AC supply of a given RMS voltage. */
public class SeriesCircuit {
    private final double supplyVolts;
    private final List<Component> parts = new ArrayList<>();

    public SeriesCircuit(double supplyVolts) {
        this.supplyVolts = supplyVolts;
    }

    public void add(Component c) {
        // TODO: refuse a duplicate label
    }

    public double totalResistance() { return 0; }                // TODO
    public double totalReactance(double f) { return 0; }        // TODO
    public double impedance(double f) { return 0; }             // TODO: sqrt(R^2 + X^2)
    public double current(double f) { return 0; }               // TODO: I = V / |Z|
    public double phaseDegrees(double f) { return 0; }          // TODO: atan(X / R), in degrees

    /** The frequency where X_L and X_C cancel: f0 = 1 / (2 pi sqrt(L C)).
     *  Throws IllegalStateException if the circuit has no inductor or no capacitor. */
    public double resonantFrequency() {
        // TODO: total L = sum of inductances; total C in series: 1/C = 1/C1 + 1/C2 + ...
        //       (use instanceof to find the inductors and capacitors)
        return 0;
    }

    /** Returns a table of |Z|, current and phase at each frequency. */
    public String sweep(double[] frequencies) {
        return "TODO";
    }
}
