/** One set of observations, taken at the triage desk. */
public record VitalSigns(int heartRate, int respiratoryRate, int systolicBp,
                         double temperature, int spo2, boolean alert) {

    public VitalSigns {
        // TODO: reject impossible values (e.g. heart rate outside 20-250, SpO2 outside 50-100)
    }
}
