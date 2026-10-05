import java.util.ArrayList;
import java.util.List;

/** Calculations over a list of samples. */
public class Analyser {
    private final List<Sample> samples;

    public Analyser(List<Sample> samples) {
        if (samples.isEmpty()) {
            throw new IllegalArgumentException("No samples to analyse.");
        }
        this.samples = new ArrayList<>(samples);
    }

    public double minVolts() { return 0; }      // TODO
    public double maxVolts() { return 0; }      // TODO
    public double averageAmps() { return 0; }   // TODO
    public double peakWatts() { return 0; }     // TODO

    /** Energy in watt-hours, by the trapezium rule: for each pair of neighbouring
     *  samples, (P1 + P2) / 2 x (t2 - t1) gives joules; divide the total by 3600. */
    public double energyWh() {
        return 0;   // TODO
    }

    /** Samples where the current is above the limit. */
    public List<Sample> overCurrent(double limitAmps) {
        return new ArrayList<>();   // TODO
    }
}
