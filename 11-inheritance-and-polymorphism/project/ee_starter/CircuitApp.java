// Run it with:
//     javac -d out *.java
//     java -cp out CircuitApp

import java.util.Scanner;

public class CircuitApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        SeriesCircuit circuit = new SeriesCircuit(10);
        circuit.add(new Resistor("R1", 100));
        circuit.add(new Inductor("L1", 0.01, 2.5));
        // TODO: write the Capacitor class (X_C = -1 / (2 pi f C)) and add a 100 nF capacitor

        String choice;
        do {
            System.out.println();
            System.out.println("1) List parts  2) Analyse at a frequency  3) Sweep  4) Resonance  5) Add part  6) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> {
                        // TODO
                    }
                    case "2" -> {
                        // TODO: ask for f, print R, X, |Z|, I and the phase
                    }
                    case "3" -> System.out.println(circuit.sweep(new double[]{100, 1000, 5000, 10_000, 50_000}));
                    case "4" -> System.out.printf("Resonance at %.1f Hz%n", circuit.resonantFrequency());
                    case "5" -> {
                        // TODO: ask for the kind (R, L or C), a label and a value
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
