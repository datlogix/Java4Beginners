/**
 * Reads values written the way engineers write them:
 *   "470"   -> 470        "4.7k"  -> 4700       "4k7"  -> 4700
 *   "2.2M"  -> 2200000    "100n"  -> 1e-7       "10u"  -> 1e-5
 *   "33m"   -> 0.033      "1p5"   -> 1.5e-12
 * Prefixes: p n u m k M G. Note m (milli) and M (mega) are different!
 * An optional unit at the end is ignored: "4.7kOhm", "100nF", "12V".
 */
public class SIParser {

    public static double parse(String text) throws InvalidValueException {
        // TODO:
        //  1. Reject null or blank text.
        //  2. Strip a known unit from the end (Ohm, ohm, F, H, V, A, W).
        //  3. Find the prefix letter, if any. If it's at the END ("4.7k"), the number is
        //     before it. If it's in the MIDDLE ("4k7"), it acts as the decimal point.
        //  4. Parse the number with Double.parseDouble, and turn its
        //     NumberFormatException into an InvalidValueException.
        //  5. Reject negative values and zero.
        return Double.parseDouble(text);
    }

    /** Formats a value with a prefix: 4700 -> "4.7k". (Your Module 11 si() helper.) */
    public static String format(double value) {
        // TODO
        return String.valueOf(value);
    }
}
