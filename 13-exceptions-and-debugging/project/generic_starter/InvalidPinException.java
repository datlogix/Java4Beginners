/** Thrown when the PIN is wrong. Tells the caller how many attempts are left. */
public class InvalidPinException extends Exception {
    private final int attemptsLeft;

    public InvalidPinException(int attemptsLeft) {
        super("Wrong PIN. " + attemptsLeft + " attempt(s) left.");
        this.attemptsLeft = attemptsLeft;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }
}
