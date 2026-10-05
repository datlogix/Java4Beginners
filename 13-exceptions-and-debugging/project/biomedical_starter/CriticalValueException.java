/** A reading that's possible, but dangerous: it needs to be confirmed and escalated. */
public class CriticalValueException extends Exception {
    private final String vital;
    private final double value;

    public CriticalValueException(String vital, double value, String advice) {
        super("CRITICAL " + vital + ": " + value + ". " + advice);
        this.vital = vital;
        this.value = value;
    }

    public String getVital() { return vital; }
    public double getValue() { return value; }
}
