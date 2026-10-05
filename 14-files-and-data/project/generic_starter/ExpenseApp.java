// Run it from the generic_starter folder with:
//     javac -d out *.java
//     java -cp out ExpenseApp

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        ExpenseStore store = new ExpenseStore(Path.of("data", "expenses.csv"));
        List<Expense> expenses = new ArrayList<>();
        try {
            expenses = store.load();
            System.out.println("Loaded " + expenses.size() + " expenses.");
            for (String problem : store.getProblems()) {
                System.out.println("  Skipped " + problem);
            }
        } catch (IOException e) {
            System.out.println("Couldn't read the expenses file: " + e.getMessage());
        }

        String choice;
        do {
            System.out.println();
            System.out.println("1) Add  2) List a month  3) Summary by category  4) Monthly totals");
            System.out.println("5) Delete  6) Export a month's report  7) Quit");
            choice = ask("Choose: ");
            // TODO: each option. After every change (add, delete), SAVE straight away,
            //       so nothing is lost if the program is closed.
        } while (!choice.equals("7"));
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
