// Exercise 2: A calculator, with the logic kept separate from the window.
//
// PART A: the model. CalculatorModel holds the calculator's state and does
// the arithmetic. It knows NOTHING about Swing: it receives key presses as
// Strings ("7", ".", "+", "=", "C"...) and has a display() method. That means
// it can be tested without a window:
//
//     java Exercise2.java test
//
// Make every check pass. Rules (like a simple pocket calculator):
//   - Digits build up the number on the display: 1, 2 -> "12". A leading
//     zero is replaced: 0 then 7 -> "7".
//   - "." adds a decimal point, but only one per number.
//   - An operator (+ - x /) remembers the number and the operator. The next
//     digit starts a NEW number.
//   - Operators work left to right, immediately: 2 + 3 x 4 = gives 20 (not 14).
//     Pressing a second operator works out the first: 2 + 3 + shows "5".
//   - "=" works out the result. Whole results show without ".0" ("15", not
//     "15.0"); others show up to 10 significant decimals with no trailing zeros.
//   - Dividing by zero shows "Error" until "C" is pressed.
//   - "C" clears everything and shows "0".
//
// PART B: the window. buildUi() lays out a display and a GridLayout of buttons:
//     7 8 9 /
//     4 5 6 x
//     1 2 3 -
//     0 . = +
//     C
// Every button calls model.press(its text), then shows model.display().
//
// Run the window with:  java Exercise2.java

import java.awt.*;
import java.math.BigDecimal;
import javax.swing.*;

public class Exercise2 {

    static class CalculatorModel {
        // TODO: fields for the display text, the stored number, the pending operator,
        //       and whether the next digit starts a new number

        void press(String key) {
            // TODO
        }

        String display() {
            return "0";   // TODO
        }

        /** Formats a result: 15.0 -> "15", 0.75 -> "0.75", 1/3 -> "0.3333333333". */
        static String format(double value) {
            BigDecimal d = new BigDecimal(value).setScale(10, java.math.RoundingMode.HALF_UP).stripTrailingZeros();
            return d.scale() <= 0 ? d.toBigInteger().toString() : d.toPlainString();
        }
    }

    static JPanel buildUi() {
        CalculatorModel model = new CalculatorModel();
        JPanel panel = new JPanel(new BorderLayout());
        // TODO: the display (a JLabel, right-aligned, big font) and the buttons
        panel.add(new JLabel(model.display(), SwingConstants.RIGHT), BorderLayout.NORTH);
        return panel;
    }

    // ---------------- Checks (don't change these) ----------------

    static int passed = 0, failed = 0;

    static void check(String keys, String expected) {
        CalculatorModel m = new CalculatorModel();
        for (String key : keys.split(" ")) {
            m.press(key);
        }
        if (m.display().equals(expected)) {
            passed++;
            System.out.println("PASS  " + keys + "  ->  " + expected);
        } else {
            failed++;
            System.out.println("FAIL  " + keys + "  ->  got " + m.display() + ", expected " + expected);
        }
    }

    static void runChecks() {
        check("C", "0");
        check("1 2", "12");
        check("0 7", "7");
        check("1 2 + 3 =", "15");
        check("1 2 + 3", "3");
        check("2 + 3 x 4 =", "20");
        check("2 + 3 +", "5");
        check("9 - 1 2 =", "-3");
        check("0 . 5 + 0 . 2 5 =", "0.75");
        check("1 . . 5", "1.5");
        check("1 / 3 =", "0.3333333333");
        check("7 / 0 =", "Error");
        check("7 / 0 = 5", "Error");
        check("7 / 0 = C 5", "5");
        check("4 x 2 . 5 =", "10");
        check("8 = ", "8");
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            runChecks();
            return;
        }
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Calculator");
            window.add(buildUi());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
