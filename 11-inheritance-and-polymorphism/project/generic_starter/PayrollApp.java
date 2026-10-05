// Run it with:
//     javac -d out *.java
//     java -cp out PayrollApp

import java.util.Scanner;

public class PayrollApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        Payroll payroll = new Payroll("Department of Electrical Engineering");
        payroll.hire(new Programmer("E001", "Abena Mensah", "Accra", "abena@uni.example", "024 555 0101", 4000));
        payroll.hire(new Professor("E002", "Kwame Asante", "Kumasi", "kwame@uni.example", "020 555 0199", 12000));
        // TODO: create the AssistantProfessor and AssociateProfessor classes, and hire one of each

        String choice;
        do {
            System.out.println();
            System.out.println("1) Summary  2) Pay slip  3) Highest paid  4) Hire  5) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> System.out.println(payroll.summary());
                    case "2" -> System.out.println(payroll.find(ask("Employee ID: ")).payslip());
                    case "3" -> System.out.println(payroll.highestPaid());
                    case "4" -> {
                        // TODO: ask which kind of employee, then their details, and hire them
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
