// Module 18 Project, Track A: Study Planner
// Author: YOUR NAME
package com.makerspace.planner;

/** How urgent a task is, with a colour (a CSS colour name) for the window to show it in. */
public enum Priority {
    HIGH("#C82828"),
    MEDIUM("#D28200"),
    LOW("#28823C");

    private final String colour;

    Priority(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
