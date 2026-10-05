// Run it with:
//     javac -d out *.java
//     java -cp out VitalsApp
//
// Ranges are simplified for teaching. This is not a clinical tool.

import java.util.Scanner;

public class VitalsApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== VITALS ENTRY ===");
        String patient = ask("Patient ID: ");

        // TODO: for each vital, keep asking until the value is accepted:
        //   - ImplausibleReadingException: show the message and ask again
        //   - CriticalValueException: show the message, ask "Confirm this reading? (y/n)";
        //     if confirmed, accept it AND add it to a list of escalations; if not, ask again
        // TODO: then print a summary of the readings, and any escalations
        // TODO: then the dose calculator from the README (DoseCalculator,
        //       MaximumDoseExceededException)
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
