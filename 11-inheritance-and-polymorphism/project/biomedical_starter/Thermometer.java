import java.time.LocalDate;

public class Thermometer extends MedicalDevice {
    private Double latest;     // a Double (not double) so it can be null: "no reading yet"

    public Thermometer(String serial, String ward, LocalDate lastCalibrated) {
        super(serial, ward, lastCalibrated);
    }

    /** Records a temperature in degrees C (rejects anything outside 30-45). */
    public void record(double celsius) {
        // TODO
    }

    @Override
    public String deviceType() {
        return "Thermometer";
    }

    @Override
    public int calibrationIntervalDays() {
        return 365;
    }

    @Override
    public String latestReading() {
        return latest == null ? "no readings" : String.format("%.1f C", latest);
    }

    @Override
    public boolean isAlarming() {
        // TODO: alarm at 38.0 C or above, or below 35.0 C
        return false;
    }
}
