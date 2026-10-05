public class Resistor extends Component {
    private final double ohms;

    public Resistor(String label, double ohms) {
        super(label);
        // TODO: reject ohms <= 0
        this.ohms = ohms;
    }

    @Override
    public double resistance() {
        return ohms;
    }

    @Override
    public double reactance(double frequency) {
        return 0;     // a pure resistor has no reactance
    }

    @Override
    public String valueText() {
        return si(ohms) + "Ohm";
    }
}
