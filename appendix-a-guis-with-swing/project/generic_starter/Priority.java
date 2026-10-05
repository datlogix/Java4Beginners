// Appendix A Project, Track A: Study Planner
// Author: YOUR NAME

import java.awt.Color;

/** How urgent a task is, with a colour for the window to show it in. */
public enum Priority {
    HIGH(new Color(200, 40, 40)),
    MEDIUM(new Color(210, 130, 0)),
    LOW(new Color(40, 130, 60));

    private final Color colour;

    Priority(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }
}
