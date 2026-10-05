// Example 8: your own exception classes, so callers can catch exactly the
// problem they care about, and get the details they need.
// Run it with:  java Ex08CustomExceptions.java

public class Ex08CustomExceptions {

    // A checked exception: extends Exception. Callers MUST deal with it.
    static class InsufficientFundsException extends Exception {
        private final double shortBy;

        InsufficientFundsException(double shortBy) {
            super(String.format("Insufficient funds: short by GHS %.2f", shortBy));
            this.shortBy = shortBy;
        }

        double getShortBy() {
            return shortBy;
        }
    }

    // An unchecked exception: extends RuntimeException. For programming mistakes.
    static class AccountFrozenException extends RuntimeException {
        AccountFrozenException(String number) {
            super("Account " + number + " is frozen");
        }
    }

    static class Account {
        private final String number;
        private double balance;
        private boolean frozen;

        Account(String number, double balance) {
            this.number = number;
            this.balance = balance;
        }

        void freeze() { frozen = true; }

        void withdraw(double amount) throws InsufficientFundsException {
            if (frozen) {
                throw new AccountFrozenException(number);
            }
            if (amount > balance) {
                throw new InsufficientFundsException(amount - balance);
            }
            balance -= amount;
        }
    }

    public static void main(String[] args) {
        Account acc = new Account("ACC-7", 100);
        double[] attempts = {30, 120, 50};
        for (double amount : attempts) {
            try {
                acc.withdraw(amount);
                System.out.println("Withdrew " + amount);
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage() + " (top up at least " + e.getShortBy() + ")");
            }
        }

        acc.freeze();
        try {
            acc.withdraw(1);
        } catch (InsufficientFundsException e) {
            System.out.println("not this one");
        } catch (AccountFrozenException e) {
            System.out.println("Refused: " + e.getMessage());
        }
    }
}
