import java.util.ArrayList;
import java.util.List;

/** A bank account with a PIN, a daily withdrawal limit and a transaction log. */
public class Account {
    public static final int MAX_PIN_ATTEMPTS = 3;
    public static final double DAILY_LIMIT = 2000;

    private final String number;
    private final String owner;
    private final String pin;
    private double balance;
    private int failedAttempts;
    private double withdrawnToday;
    private final List<String> log = new ArrayList<>();

    public Account(String number, String owner, String pin, double balance) {
        // TODO: the PIN must be exactly 4 digits; the balance can't be negative
        this.number = number;
        this.owner = owner;
        this.pin = pin;
        this.balance = balance;
    }

    public String getNumber() { return number; }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }

    public boolean isLocked() {
        return failedAttempts >= MAX_PIN_ATTEMPTS;
    }

    /** Checks a PIN. A correct PIN resets the failure count. */
    public void checkPin(String attempt) throws InvalidPinException {
        // TODO: if the account is locked, throw AccountLockedException (write that class:
        //       unchecked, extends RuntimeException). If the PIN is wrong, count the failure
        //       and throw InvalidPinException with the attempts left.
    }

    public void deposit(double amount) {
        // TODO: reject amounts <= 0 (IllegalArgumentException); log it
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        // TODO: reject amounts <= 0 or not a multiple of 10 (ATMs only give notes!);
        //       throw InsufficientFundsException; throw DailyLimitExceededException
        //       (write it: checked, with the amount still allowed today); log it
    }

    /** A COPY of the transaction log. */
    public List<String> getLog() {
        return new ArrayList<>(log);
    }
}
