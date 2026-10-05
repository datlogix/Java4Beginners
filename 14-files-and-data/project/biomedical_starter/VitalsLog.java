import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Every reading in a log file, grouped by patient. */
public class VitalsLog {
    private final Map<String, List<Reading>> byPatient = new TreeMap<>();
    private final List<String> problems = new ArrayList<>();

    /** Reads the file, skipping broken rows (and remembering why). */
    public void load(Path file) throws IOException {
        // TODO
    }

    public List<String> getProblems() {
        return new ArrayList<>(problems);
    }

    public Map<String, List<Reading>> getByPatient() {
        return byPatient;   // TODO (stretch): return a read-only view with Collections.unmodifiableMap
    }

    /** Writes one line per abnormal reading: patient,time,reasons */
    public void writeAlerts(Path file) throws IOException {
        // TODO
    }
}
