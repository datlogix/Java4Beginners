package com.makerspace.biotools.cli;

import com.makerspace.biotools.Body;

/**
 * Command-line front end:
 *     ./mvnw -q compile exec:java -Dexec.args="bmi 70 1.75"
 * or, after ./mvnw package:
 *     java -jar target/biotools-1.0.jar bmi 70 1.75
 */
public class BioToolsCli {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: biotools bmi|bsa|dose|glucose|zones ... values");
            return;
        }
        // TODO: a command for every tool, with friendly error messages
        if (args[0].equals("bmi")) {
            double bmi = Body.bmi(Double.parseDouble(args[1]), Double.parseDouble(args[2]));
            System.out.printf("BMI %.1f (%s)%n", bmi, Body.bmiCategory(bmi));
        }
    }
}
