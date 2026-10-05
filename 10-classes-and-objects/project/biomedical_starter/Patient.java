import java.util.ArrayList;
import java.util.List;

/** A patient on the ward, with their history of vital readings. */
public class Patient {
    private final String id;
    private final String name;
    private final int age;
    private final int bed;
    private final List<VitalReading> readings = new ArrayList<>();

    public Patient(String id, String name, int age, int bed) {
        // TODO: validate (age 0-120; empty ID or name refused)
        this.id = id;
        this.name = name;
        this.age = age;
        this.bed = bed;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getBed() { return bed; }

    public void addReading(VitalReading reading) {
        // TODO
    }

    /** Returns the most recent reading, or null if there are none. */
    public VitalReading latest() {
        return null;   // TODO
    }

    public double averageHeartRate() {
        return 0;   // TODO (0 if there are no readings)
    }

    /** True if the latest reading has any problems. */
    public boolean needsAttention() {
        return false;   // TODO
    }

    @Override
    public String toString() {
        // TODO: e.g.  "P001 Ama Owusu, 34, bed 3"
        return name;
    }
}
