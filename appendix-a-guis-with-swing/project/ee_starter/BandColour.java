// Appendix A Project, Track B: Resistor Colour Code Calculator
// Author: YOUR NAME

import java.awt.Color;

/** The resistor colour code (your Module 12 enum), now with a real Color for drawing. */
public enum BandColour {
    BLACK(0, 1, 0, new Color(20, 20, 20)),
    BROWN(1, 10, 1, new Color(120, 60, 20)),
    RED(2, 100, 2, new Color(210, 30, 30)),
    ORANGE(3, 1e3, 0, new Color(255, 130, 0)),
    YELLOW(4, 1e4, 0, new Color(255, 220, 0)),
    GREEN(5, 1e5, 0.5, new Color(30, 150, 50)),
    BLUE(6, 1e6, 0.25, new Color(30, 70, 220)),
    VIOLET(7, 1e7, 0.1, new Color(140, 50, 190)),
    GREY(8, 0, 0.05, new Color(130, 130, 130)),
    WHITE(9, 0, 0, new Color(250, 250, 250)),
    GOLD(-1, 0.1, 5, new Color(200, 160, 40)),
    SILVER(-1, 0.01, 10, new Color(190, 190, 200));

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
