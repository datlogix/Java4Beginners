package com.makerspace.exercises;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Exercise 2, PART A: the calculator's model. It holds the calculator's state
 * and does the arithmetic, and knows NOTHING about JavaFX: it receives key
 * presses as Strings ("7", ".", "+", "=", "C"...) and has a display() method.
 * That means it can be tested without a window. The tests are already written,
 * in src/test/java/com/makerspace/exercises/CalculatorModelTest.java:
 *
 *     ./mvnw test
 *
 * Make every test pass. Rules (like a simple pocket calculator):
 *   - Digits build up the number on the display: 1, 2 -> "12". A leading
 *     zero is replaced: 0 then 7 -> "7".
 *   - "." adds a decimal point, but only one per number.
 *   - An operator (+ - x /) remembers the number and the operator. The next
 *     digit starts a NEW number.
 *   - Operators work left to right, immediately: 2 + 3 x 4 = gives 20 (not 14).
 *     Pressing a second operator works out the first: 2 + 3 + shows "5".
 *   - "=" works out the result, shown with format() below.
 *   - Dividing by zero shows "Error" until "C" is pressed.
 *   - "C" clears everything and shows "0".
 */
public class CalculatorModel {
    // TODO: fields for the display text, the stored number, the pending operator,
    //       and whether the next digit starts a new number

    public void press(String key) {
        // TODO
    }

    public String display() {
        return "0";   // TODO
    }

    /** Formats a result: 15.0 -> "15", 0.75 -> "0.75", 1/3 -> "0.3333333333". (Given.) */
    static String format(double value) {
        BigDecimal d = new BigDecimal(value).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
        return d.scale() <= 0 ? d.toBigInteger().toString() : d.toPlainString();
    }
}
