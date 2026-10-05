// Module 17 Project, Track B: eetools
// Author: YOUR NAME
package com.makerspace.eetools;

/** Resistor calculations. Every method is static, and none of them prints anything. */
public final class Resistors {

    private Resistors() {
    }

    /** R = R1 + R2 + ...  Throws IllegalArgumentException for no values, or any value <= 0. */
    public static double series(double... ohms) {
        check(ohms);
        double total = 0;
        for (double r : ohms) {
            total += r;
        }
        return total;
    }

    /** 1/R = 1/R1 + 1/R2 + ...  Same rules as series. */
    public static double parallel(double... ohms) {
        return 0;   // TODO
    }

    /** The output of an unloaded voltage divider: Vout = Vin x R2 / (R1 + R2). */
    public static double dividerOutput(double vin, double r1, double r2) {
        return 0;   // TODO
    }

    private static void check(double[] ohms) {
        if (ohms.length == 0) {
            throw new IllegalArgumentException("Give at least one resistance.");
        }
        for (double r : ohms) {
            if (r <= 0) {
                throw new IllegalArgumentException("Resistances must be positive: " + r);
            }
        }
    }
}
