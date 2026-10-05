/** A way of deciding how urgent a patient is. Different rules can be swapped in. */
@FunctionalInterface
public interface TriageRule {
    TriageLevel assess(VitalSigns vitals);
}
