/**
 * The standard two-input gates. Each enum value carries its own behaviour,
 * as a LogicGate lambda, and its Boolean expression.
 */
public enum GateType implements LogicGate {
    AND((a, b) -> a && b, "A.B"),
    OR((a, b) -> a || b, "A+B");
    // TODO: XOR, NAND, NOR and XNOR

    private final LogicGate rule;
    private final String expression;

    GateType(LogicGate rule, String expression) {
        this.rule = rule;
        this.expression = expression;
    }

    @Override
    public boolean apply(boolean a, boolean b) {
        return rule.apply(a, b);
    }

    public String getExpression() {
        return expression;
    }

    /** Returns this gate's truth table as text, with 0s and 1s:
     *   A B | A.B
     *   0 0 |  0
     *   ...                                                        */
    public String truthTable() {
        return "TODO";
    }
}
