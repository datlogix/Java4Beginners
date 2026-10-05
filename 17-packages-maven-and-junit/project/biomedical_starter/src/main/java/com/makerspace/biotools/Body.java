// Module 17 Project, Track C: biotools
// Author: YOUR NAME
package com.makerspace.biotools;

/**
 * Body-size calculations. Every method is static, and none of them prints anything.
 * These are standard formulas, simplified for teaching. This is not a clinical tool.
 */
public final class Body {

    private Body() {
    }

    /** Body mass index: weight / height squared. Rejects weights outside 0.5-400 kg
     *  and heights outside 0.3-2.5 m with IllegalArgumentException. */
    public static double bmi(double weightKg, double heightM) {
        checkWeight(weightKg);
        checkHeight(heightM);
        return weightKg / (heightM * heightM);
    }

    /** The WHO adult category: "underweight" (< 18.5), "healthy" (< 25),
     *  "overweight" (< 30) or "obese". */
    public static String bmiCategory(double bmi) {
        return "";   // TODO
    }

    /** Body surface area in square metres, by the Mosteller formula:
     *  BSA = sqrt(height in cm x weight in kg / 3600). */
    public static double bsaMosteller(double weightKg, double heightM) {
        return 0;   // TODO
    }

    static void checkWeight(double kg) {
        if (kg < 0.5 || kg > 400) {
            throw new IllegalArgumentException("Weight out of range: " + kg + " kg");
        }
    }

    static void checkHeight(double m) {
        if (m < 0.3 || m > 2.5) {
            throw new IllegalArgumentException("Height out of range: " + m + " m");
        }
    }
}
