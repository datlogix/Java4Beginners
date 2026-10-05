import java.util.List;

/** Networks in series: R = R1 + R2 + ... */
public class Series implements Network {
    private final List<Network> parts;

    public Series(Network... parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("A series group needs at least two parts.");
        }
        this.parts = List.of(parts);
    }

    @Override
    public double resistance() {
        return 0;   // TODO: add up each part's resistance() (each part may itself be a group!)
    }

    /** e.g. "100 + 220 + (330 || 470)": brackets round any part that's a group. */
    @Override
    public String describe() {
        return "TODO";
    }
}
