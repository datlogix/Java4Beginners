package com.makerspace.eetools.cli;

import com.makerspace.eetools.Resistors;

/**
 * Command-line front end:
 *     ./mvnw -q compile exec:java -Dexec.args="series 100 220 330"
 * or, after ./mvnw package:
 *     java -jar target/eetools-1.0.jar series 100 220 330
 */
public class EeToolsCli {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: eetools series|parallel|divider|colour|rc ... values");
            return;
        }
        // TODO: a command for every tool, with values like 4k7 (your SI parser from Module 13,
        //       now as a class in this package), and friendly error messages
        if (args[0].equals("series")) {
            double[] values = new double[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                values[i - 1] = Double.parseDouble(args[i]);
            }
            System.out.println(Resistors.series(values) + " ohms");
        }
    }
}
