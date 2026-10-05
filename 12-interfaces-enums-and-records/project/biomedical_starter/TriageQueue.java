import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Patients waiting to be seen, ordered by urgency and then by arrival time. */
public class TriageQueue {
    private final TriageRule rule;
    private final List<Patient> waiting = new ArrayList<>();
    private final Map<Patient, TriageLevel> levels = new HashMap<>();

    public TriageQueue(TriageRule rule) {
        this.rule = rule;
    }

    /** Assesses the patient with the rule, and adds them to the queue. */
    public TriageLevel admit(Patient p) {
        // TODO: refuse a duplicate ID; store the level the rule gives
        return TriageLevel.GREEN;
    }

    public TriageLevel levelOf(Patient p) {
        return levels.get(p);
    }

    /** The order patients should be seen in: most urgent level first, then earliest arrival. */
    public Comparator<Patient> priority() {
        return null;   // TODO (hint: Comparator.comparing(this::levelOf) uses the enum's order)
    }

    /** Removes and returns the next patient to see. Throws IllegalStateException if nobody is waiting. */
    public Patient next() {
        return null;   // TODO
    }

    /** Every waiting patient, in priority order (a NEW list). */
    public List<Patient> inOrder() {
        return new ArrayList<>();   // TODO
    }

    /** Patients who have waited longer than their level allows. */
    public List<Patient> overdue(LocalTime now) {
        // TODO: Duration.between(p.arrival(), now).toMinutes() gives minutes waited
        return new ArrayList<>();
    }

    /** How many patients are waiting at each level. EnumMap keeps the enum's order. */
    public Map<TriageLevel, Integer> countByLevel() {
        Map<TriageLevel, Integer> counts = new EnumMap<>(TriageLevel.class);
        // TODO
        return counts;
    }
}
