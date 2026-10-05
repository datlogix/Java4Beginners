// Run it with:
//     javac -d out *.java
//     java -cp out TriageApp
//
// The scoring rules are simplified for teaching. This is not a clinical tool.

import java.time.LocalTime;
import java.util.Scanner;

public class TriageApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO: write ScoreTriage (see the README) and use it instead of this placeholder rule
        TriageRule rule = vitals -> vitals.alert() ? TriageLevel.GREEN : TriageLevel.RED;
        TriageQueue queue = new TriageQueue(rule);
        queue.admit(new Patient("P01", "Kofi Boateng", 45, LocalTime.of(9, 5),
                new VitalSigns(128, 24, 95, 38.9, 91, true)));
        queue.admit(new Patient("P02", "Ama Serwaa", 29, LocalTime.of(9, 20),
                new VitalSigns(76, 14, 122, 36.8, 99, true)));
        // TODO: at least four more patients, at a range of levels

        String choice;
        do {
            System.out.println();
            System.out.println("1) Queue  2) See next patient  3) Overdue  4) Counts  5) Admit  6) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> {
                        // TODO: print each patient with their level and how long they've waited
                    }
                    case "2" -> System.out.println("Next: " + queue.next());
                    case "3" -> {
                        // TODO
                    }
                    case "4" -> System.out.println(queue.countByLevel());
                    case "5" -> {
                        // TODO: ask for the details and vitals, admit, and show the level given
                    }
                    case "6" -> System.out.println("Goodbye.");
                    default -> System.out.println("Please choose 1-6.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("6"));
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
