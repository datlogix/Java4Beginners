import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final String name;
    private final List<BankAccount> accounts = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public BankAccount open(String owner) {
        String number = String.format("ACC-%03d", accounts.size() + 1);
        BankAccount account = new BankAccount(number, owner);
        accounts.add(account);
        return account;
    }

    public double totalDeposits() {
        double total = 0;
        for (BankAccount a : accounts) {
            total += a.getBalance();
        }
        return total;
    }

    public void printStatement() {
        System.out.println("=== " + name + " ===");
        for (BankAccount a : accounts) {
            System.out.println(a);
        }
        System.out.printf("Total deposits: GHS %.2f%n", totalDeposits());
    }
}
