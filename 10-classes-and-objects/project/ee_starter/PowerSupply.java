/** A DC power supply with a fixed voltage and a maximum current. */
public class PowerSupply {
    private final double voltage;
    private final double maxCurrent;

    public PowerSupply(double voltage, double maxCurrent) {
        // TODO: validate (both must be positive)
        this.voltage = voltage;
        this.maxCurrent = maxCurrent;
    }

    public double getVoltage() { return voltage; }
    public double getMaxCurrent() { return maxCurrent; }

    public boolean canSupply(double current) {
        return false;   // TODO
    }

    @Override
    public String toString() {
        return voltage + " V supply (max " + maxCurrent + " A)";
    }
}
