// One public class per file, and the file is named after the class.

public class BankAccount {
    private final String number;
    private final String owner;
    private double balance;

    public BankAccount(String number, String owner) {
        this.number = number;
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive.");
        }
        balance += amount;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): GHS %.2f", number, owner, balance);
    }
}
