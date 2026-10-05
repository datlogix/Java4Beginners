import java.util.ArrayList;
import java.util.List;

/** The E12 series of standard resistor values: 10 12 15 18 22 27 33 39 47 56 68 82, times powers of ten. */
public class StandardValues {
    public static final double[] E12 = {10, 12, 15, 18, 22, 27, 33, 39, 47, 56, 68, 82};

    /** Every E12 value from 1 ohm up to 10 Mohm, in increasing order. */
    public static List<Double> e12() {
        List<Double> values = new ArrayList<>();
        // TODO
        return values;
    }

    /** The standard value closest to target, found by BINARY SEARCH (values is sorted). */
    public static double nearest(List<Double> values, double target) {
        // TODO
        return values.isEmpty() ? target : values.get(0);
    }
}
