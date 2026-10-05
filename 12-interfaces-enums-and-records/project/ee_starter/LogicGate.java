// Module 12 Project, Track B: Logic Gate Simulator
// Author: YOUR NAME

/** A two-input logic gate: a functional interface, so a lambda can be one. */
@FunctionalInterface
public interface LogicGate {
    boolean apply(boolean a, boolean b);
}
