// Module 13 Project, Track C: Vitals Entry Validator
// Author: YOUR NAME

/** A reading that can't be physically possible: probably a typing mistake. */
public class ImplausibleReadingException extends Exception {
    public ImplausibleReadingException(String vital, double value) {
        super(vital + " of " + value + " isn't physically possible. Please re-check and re-enter it.");
    }
}
