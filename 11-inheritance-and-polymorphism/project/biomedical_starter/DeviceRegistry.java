import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Every device in the hospital. */
public class DeviceRegistry {
    private final List<MedicalDevice> devices = new ArrayList<>();

    public void register(MedicalDevice d) {
        // TODO: refuse a duplicate serial number
    }

    public MedicalDevice find(String serial) {
        // TODO: return it, or throw IllegalArgumentException
        return null;
    }

    public List<MedicalDevice> alarms() {
        return new ArrayList<>();   // TODO
    }

    public List<MedicalDevice> dueForCalibration(LocalDate today) {
        return new ArrayList<>();   // TODO
    }

    public List<MedicalDevice> onWard(String ward) {
        return new ArrayList<>();   // TODO
    }

    /** A multi-line dashboard: every device, its latest reading, and its status. */
    public String dashboard(LocalDate today) {
        return "TODO";
    }
}
