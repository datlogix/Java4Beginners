// Example 9: documenting a method with Javadoc, and checking it works.
// Run it with:  java Ex09JavadocAndChecks.java

public class Ex09JavadocAndChecks {

    /**
     * Works out the body mass index (BMI) from weight and height.
     *
     * @param weightKg the person's weight in kilograms (must be positive)
     * @param heightM the person's height in metres (must be positive)
     * @return the BMI, which is weight divided by height squared
     */
    static double bmi(double weightKg, double heightM) {
        return weightKg / (heightM * heightM);
    }

    /**
     * Describes a BMI using the WHO adult categories.
     *
     * @param bmi a body mass index
     * @return "underweight", "healthy", "overweight" or "obese"
     */
    static String bmiCategory(double bmi) {
        if (bmi < 18.5) {
            return "underweight";
        } else if (bmi < 25) {
            return "healthy";
        } else if (bmi < 30) {
            return "overweight";
        }
        return "obese";
    }

    /** Prints PASS or FAIL depending on whether actual equals expected.
     *  (Object means "any kind of value": Module 11 explains it.) */
    static void check(String description, Object actual, Object expected) {
        String result = actual.equals(expected) ? "PASS" : "FAIL";
        System.out.println(result + ": " + description + " (got " + actual + ", expected " + expected + ")");
    }

    public static void main(String[] args) {
        check("BMI of 70 kg, 1.75 m, rounded", Math.round(bmi(70, 1.75) * 10) / 10.0, 22.9);
        check("18.4 is underweight", bmiCategory(18.4), "underweight");
        check("18.5 is healthy (boundary)", bmiCategory(18.5), "healthy");
        check("25.0 is overweight (boundary)", bmiCategory(25.0), "overweight");
        check("31 is obese", bmiCategory(31), "obese");
        // Hover over bmi( in VS Code: the Javadoc comment pops up as help!
    }
}
