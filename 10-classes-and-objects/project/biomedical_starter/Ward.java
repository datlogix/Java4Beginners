import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A hospital ward with a fixed number of beds. */
public class Ward {
    private final String name;
    private final int beds;
    private final Map<String, Patient> patients = new HashMap<>();   // patient ID -> Patient

    public Ward(String name, int beds) {
        this.name = name;
        this.beds = beds;
    }

    /** Admits a patient. Refuses if the ward is full, the bed number is outside
     *  1 to beds, the bed is taken, or the ID is already on the ward. */
    public void admit(Patient patient) {
        // TODO
    }

    public void discharge(String patientId) {
        // TODO: throw IllegalArgumentException if there's no such patient
    }

    public void record(String patientId, VitalReading reading) {
        // TODO
    }

    /** Returns the patients whose latest reading has problems. */
    public List<Patient> alerts() {
        return new ArrayList<>();   // TODO
    }

    /** Returns a multi-line report: every patient, bed, latest reading, and any alerts. */
    public String report() {
        return name;   // TODO
    }
}
