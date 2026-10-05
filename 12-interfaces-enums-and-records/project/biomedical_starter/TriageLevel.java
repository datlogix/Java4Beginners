// Module 12 Project, Track C: Emergency Triage
// Author: YOUR NAME

/** Triage categories, most urgent first, with the longest a patient should wait. */
public enum TriageLevel {
    RED("Emergency", 0),
    ORANGE("Very urgent", 10),
    YELLOW("Urgent", 60),
    GREEN("Routine", 240);

    private final String description;
    private final int maxWaitMinutes;

    TriageLevel(String description, int maxWaitMinutes) {
        this.description = description;
        this.maxWaitMinutes = maxWaitMinutes;
    }

    public String getDescription() { return description; }
    public int getMaxWaitMinutes() { return maxWaitMinutes; }
}
