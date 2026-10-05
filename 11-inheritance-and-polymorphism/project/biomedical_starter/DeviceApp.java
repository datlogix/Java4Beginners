// Run it with:
//     javac -d out *.java
//     java -cp out DeviceApp
//
// Thresholds are simplified for teaching. This is not a clinical tool.

import java.time.LocalDate;
import java.util.Scanner;

public class DeviceApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        DeviceRegistry registry = new DeviceRegistry();
        Thermometer t1 = new Thermometer("TH-001", "Ward 3B", today.minusDays(400));
        t1.record(38.6);
        registry.register(t1);
        // TODO: write PulseOximeter, BloodPressureMonitor and InfusionPump, and register
        //       at least one of each, with readings

        String choice;
        do {
            System.out.println();
            System.out.println("1) Dashboard  2) Alarms  3) Calibration due  4) Record a reading  5) Calibrate  6) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> System.out.println(registry.dashboard(today));
                    case "2" -> {
                        // TODO
                    }
                    case "3" -> {
                        // TODO
                    }
                    case "4" -> {
                        // TODO: find the device, then use instanceof to ask for the right values
                    }
                    case "5" -> {
                        // TODO
                    }
                    case "6" -> System.out.println("Goodbye.");
                    default -> System.out.println("Please choose 1-6.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("6"));
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
