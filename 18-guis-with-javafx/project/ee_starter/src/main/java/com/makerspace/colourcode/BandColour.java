// Module 18 Project, Track B: Resistor Colour Code Calculator
// Author: YOUR NAME
package com.makerspace.colourcode;

import javafx.scene.paint.Color;

/** The resistor colour code (your Module 12 enum), now with a real Color for drawing. */
public enum BandColour {
    BLACK(0, 1, 0, Color.rgb(20, 20, 20)),
    BROWN(1, 10, 1, Color.rgb(120, 60, 20)),
    RED(2, 100, 2, Color.rgb(210, 30, 30)),
    ORANGE(3, 1e3, 0, Color.rgb(255, 130, 0)),
    YELLOW(4, 1e4, 0, Color.rgb(255, 220, 0)),
    GREEN(5, 1e5, 0.5, Color.rgb(30, 150, 50)),
    BLUE(6, 1e6, 0.25, Color.rgb(30, 70, 220)),
    VIOLET(7, 1e7, 0.1, Color.rgb(140, 50, 190)),
    GREY(8, 0, 0.05, Color.rgb(130, 130, 130)),
    WHITE(9, 0, 0, Color.rgb(250, 250, 250)),
    GOLD(-1, 0.1, 5, Color.rgb(200, 160, 40)),
    SILVER(-1, 0.01, 10, Color.rgb(190, 190, 200));

    private final int digit;
    private final double multiplier;
    private final double tolerance;
    private final Color colour;

    BandColour(int digit, double multiplier, double tolerance, Color colour) {
        this.digit = digit;
        this.multiplier = multiplier;
        this.tolerance = tolerance;
        this.colour = colour;
    }

    public int getDigit() { return digit; }
    public double getMultiplier() { return multiplier; }
    public double getTolerance() { return tolerance; }
    public Color getColour() { return colour; }

    public boolean canBeDigit() { return digit >= 0; }
    public boolean canBeMultiplier() { return multiplier > 0; }
    public boolean canBeTolerance() { return tolerance > 0; }
}
