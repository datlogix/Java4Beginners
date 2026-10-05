// Run it with:
//     javac -d out *.java
//     java -cp out LogicApp

import java.util.Scanner;

public class LogicApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        String choice;
        do {
            System.out.println();
            System.out.println("1) Truth tables  2) Try a gate  3) Add two numbers  4) Self-test  5) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> {
                        for (GateType g : GateType.values()) {
                            System.out.println(g + " (" + g.getExpression() + ")");
                            System.out.println(g.truthTable());
                        }
                    }
                    case "2" -> {
                        // TODO: ask for a gate name (GateType.valueOf) and two bits (0/1)
                    }
                    case "3" -> {
                        // TODO: write FullAdder and RippleCarryAdder (see the README), then
                        //       ask for two numbers from 0 to 15 and show the binary addition
                    }
                    case "4" -> {
                        // TODO: check the 4-bit adder against Java's + for ALL 256 pairs
                    }
                    case "5" -> System.out.println("Goodbye.");
                    default -> System.out.println("Please choose 1-5.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("5"));
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
