// Module 16 Project, Track B: Resistor Networks and Standard Values
// Author: YOUR NAME

/**
 * A resistor network: either a single resistor, or several smaller networks
 * joined in series or in parallel, nested to any depth. Each kind works out its
 * own resistance and description, and the composite kinds do it RECURSIVELY by
 * asking their parts.
 */
public interface Network {
    double resistance();
    String describe();
}
