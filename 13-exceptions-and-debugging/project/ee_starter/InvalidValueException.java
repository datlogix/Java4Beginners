// Module 13 Project, Track B: Safe Circuit Calculator
// Author: YOUR NAME

/** Thrown when text can't be read as a component value, like "4.7q" or "". */
public class InvalidValueException extends Exception {
    private final String text;

    public InvalidValueException(String text, String reason) {
        super("'" + text + "' isn't a valid value: " + reason);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
