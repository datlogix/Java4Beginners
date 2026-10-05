// Run it with:
//     javac -d out *.java
//     java -cp out WardApp
//
// Thresholds are simplified for teaching. This is not a clinical tool.

import java.util.Scanner;

public class WardApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        Ward ward = new Ward("Ward 3B", 6);
        ward.admit(new Patient("P001", "Ama Owusu", 34, 1));
        ward.admit(new Patient("P002", "Kwame Asare", 71, 2));
        ward.record("P001", new VitalReading("08:00", 72, 98, 37.1));
        ward.record("P002", new VitalReading("08:00", 112, 92, 38.4));
        // TODO: another patient or two, with readings

        String choice;
        do {
            System.out.println();
            System.out.println("1) Report  2) Alerts  3) Admit  4) Record vitals  5) Discharge  6) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> System.out.println(ward.report());
                    case "2" -> {
                        // TODO: list each patient needing attention, with the reasons
                    }
                    case "3" -> {
                        // TODO
                    }
                    case "4" -> {
                        // TODO
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
