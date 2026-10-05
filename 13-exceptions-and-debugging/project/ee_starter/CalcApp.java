// Run it with:
//     javac -d out *.java
//     java -cp out CalcApp

import java.util.Scanner;

public class CalcApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        String choice;
        do {
            System.out.println();
            System.out.println("1) Ohm's law  2) Power  3) LED resistor  4) Self-test  5) Quit");
            choice = ask("Choose: ");
            switch (choice) {
                case "1" -> {
                    // TODO: ask which two of V, I, R are known (values like 4k7), and work out the third
                }
                case "2" -> {
                    // TODO: P = V x I, and the power in a resistor P = I^2 R
                }
                case "3" -> {
                    // TODO: see the README: the LED resistor calculator, with the nearest
                    //       E12 value and a ComponentOverloadException check
                }
                case "4" -> {
                    // TODO: check SIParser.parse on every example in its comment, and on
                    //       bad inputs that must throw InvalidValueException
                }
                case "5" -> System.out.println("Goodbye.");
                default -> System.out.println("Please choose 1-5.");
            }
        } while (!choice.equals("5"));
    }

    /** Asks until the user types a value SIParser accepts. */
    private static double askValue(String prompt) {
        while (true) {
            try {
                return SIParser.parse(ask(prompt));
            } catch (InvalidValueException e) {
                System.out.println("  " + e.getMessage());
            }
        }
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
