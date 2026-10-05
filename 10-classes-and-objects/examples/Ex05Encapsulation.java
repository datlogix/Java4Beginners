// Example 5: encapsulation. Make fields private, and let methods guard them,
// so an object can never get into a nonsense state.
// Run it with:  java Ex05Encapsulation.java

public class Ex05Encapsulation {

    static class BankAccount {
        private final String owner;     // final: set once, in the constructor, never changed
        private double balance;         // private: only BankAccount's own code can touch it

        BankAccount(String owner, double openingBalance) {
            if (owner == null || owner.isBlank()) {
                throw new IllegalArgumentException("An account needs an owner.");
            }
            if (openingBalance < 0) {
                throw new IllegalArgumentException("Opening balance can't be negative: " + openingBalance);
            }
            this.owner = owner;
            this.balance = openingBalance;
        }

        // A "getter": read-only access to a private field
        double getBalance() {
            return balance;
        }

        String getOwner() {
            return owner;
        }

        // No setBalance! The only ways to change the balance are these, and they check.
        void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit must be positive: " + amount);
            }
            balance += amount;
        }

        void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal must be positive: " + amount);
            }
            if (amount > balance) {
                throw new IllegalArgumentException("Insufficient funds: balance is " + balance);
            }
            balance -= amount;
        }

        @Override
        public String toString() {
            return String.format("%s: GHS %.2f", owner, balance);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("Akosua", 100);
        account.deposit(50);
        account.withdraw(30);
        System.out.println(account);

        // account.balance = 1_000_000;    // error: balance has private access

        try {                              // try/catch: Module 13 explains it fully
            account.withdraw(500);
        } catch (IllegalArgumentException e) {
            System.out.println("Refused: " + e.getMessage());
        }
        System.out.println(account);       // unchanged: the object protected itself

        BankAccount broken = new BankAccount("", 10);   // crashes: no owner
    }
}
