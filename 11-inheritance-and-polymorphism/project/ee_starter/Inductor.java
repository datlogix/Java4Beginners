/** An inductor: X_L = 2 pi f L. Real coils also have a small winding resistance. */
public class Inductor extends Component {
    private final double henries;
    private final double windingResistance;

    public Inductor(String label, double henries, double windingResistance) {
        super(label);
        // TODO: validate (henries > 0, windingResistance >= 0)
        this.henries = henries;
        this.windingResistance = windingResistance;
    }

    public Inductor(String label, double henries) {
        this(label, henries, 0);
    }

    public double getHenries() { return henries; }

    @Override
    public double resistance() {
        return 0;   // TODO
    }

    @Override
    public double reactance(double frequency) {
        return 0;   // TODO
    }

    @Override
    public String valueText() {
        return si(henries) + "H";
    }
}
