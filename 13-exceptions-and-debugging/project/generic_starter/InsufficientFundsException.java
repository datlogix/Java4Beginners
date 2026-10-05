// Module 13 Project, Track A: ATM Simulator
// Author: YOUR NAME

/** Thrown when a withdrawal is bigger than the balance. Checked: callers must handle it. */
public class InsufficientFundsException extends Exception {
    private final double shortBy;

    public InsufficientFundsException(double shortBy) {
        super(String.format("Insufficient funds: you are GHS %.2f short.", shortBy));
        this.shortBy = shortBy;
    }

    public double getShortBy() {
        return shortBy;
    }
}
