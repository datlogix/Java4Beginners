import java.time.LocalTime;

/** A patient waiting in the emergency department. */
public record Patient(String id, String name, int age, LocalTime arrival, VitalSigns vitals) {
    // TODO: a compact constructor that validates id, name and age (0-120)
}
