/**
 * Checks each vital sign as it's typed in, in two stages:
 *   1. Is it physically POSSIBLE?  If not: ImplausibleReadingException.
 *   2. Is it CRITICAL?             If so:  CriticalValueException.
 * Each method returns the value if it's fine.
 */
public class VitalsValidator {

    /** Heart rate, beats per minute. Possible: 20-250. Critical: below 40 or above 140. */
    public static int heartRate(String text) throws ImplausibleReadingException, CriticalValueException {
        // TODO: parse (a NumberFormatException means it isn't a number: turn it into
        //       ImplausibleReadingException), then the two checks
        return Integer.parseInt(text.trim());
    }

    /** Temperature, C. Possible: 30.0-45.0. Critical: below 35.0 or 40.0 and above. */
    public static double temperature(String text) throws ImplausibleReadingException, CriticalValueException {
        return Double.parseDouble(text.trim());   // TODO
    }

    /** SpO2, %. Possible: 50-100. Critical: below 90. */
    public static int spo2(String text) throws ImplausibleReadingException, CriticalValueException {
        return Integer.parseInt(text.trim());   // TODO
    }

    /**
     * Blood pressure written as "systolic/diastolic", e.g. "120/80".
     * Possible: systolic 50-260, diastolic 30-160, and systolic > diastolic.
     * Critical: systolic 180 or above, or below 90.
     * Returns {systolic, diastolic}.
     */
    public static int[] bloodPressure(String text) throws ImplausibleReadingException, CriticalValueException {
        // TODO: split at "/", and handle a missing "/" or extra parts
        return new int[]{0, 0};
    }
}
