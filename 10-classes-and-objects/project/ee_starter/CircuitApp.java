// Run it with:
//     javac -d out *.java
//     java -cp out CircuitApp

import java.util.Scanner;

public class CircuitApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        Circuit circuit = new Circuit(new PowerSupply(12, 0.5));
        circuit.add(new Resistor("R1", 100));
        circuit.add(new Resistor("R2", 220));
        // TODO: one or two more resistors

        String choice;
        do {
            System.out.println();
            System.out.println("1) Analyse  2) Add resistor  3) Remove resistor  4) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> System.out.println(circuit.analyse());
                    case "2" -> {
                        // TODO: ask for a label, ohms and power rating, and add it
                        //       (Double.parseDouble on bad input throws NumberFormatException,
                        //        which IS an IllegalArgumentException, so it's caught below)
                    }
                    case "3" -> {
                        // TODO
                    }
                    case "4" -> System.out.println("Goodbye.");
                    default -> System.out.println("Please choose 1-4.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("4"));
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
