package com.makerspace.colourcode;

/** The model: decoding and encoding colour codes. No JavaFX here, so it can be unit tested. */
public final class ColourCode {

    private ColourCode() {
    }

    /** The resistance in ohms shown by two digit bands and a multiplier band. */
    public static double ohms(BandColour first, BandColour second, BandColour multiplier) {
        return (first.getDigit() * 10 + second.getDigit()) * multiplier.getMultiplier();
    }

    /** "4.7 kOhm +/-5%" and the like (your Module 12 describe()). */
    public static String describe(double ohms, double tolerance) {
        return ohms + " Ohm +/-" + tolerance + "%";   // TODO
    }

    /** The three bands (two digits and a multiplier) for a value such as 4700 or 0.33,
     *  or throws IllegalArgumentException if it can't be shown with two digits. */
    public static BandColour[] encode(double ohms) {
        // TODO
        return new BandColour[]{BandColour.BLACK, BandColour.BLACK, BandColour.BLACK};
    }
}
