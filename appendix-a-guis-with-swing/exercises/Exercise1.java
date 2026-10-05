// Exercise 1: A unit converter window.
//
// Build this window:
//
//     +-----------------------------------------------+
//     | Convert: [ kilometres -> miles      v ]       |
//     | Value:   [ 42.195      ]  [Convert] [Swap]    |
//     | 42.195 kilometres = 26.219 miles              |
//     +-----------------------------------------------+
//
// Requirements:
//   1. A JComboBox listing at least FIVE conversions. Store each one as a
//      Conversion record (given below) in an array, and show their names.
//   2. Typing a number and pressing Convert, OR pressing Enter in the field,
//      shows the result to 3 decimal places in the result label.
//   3. Bad input (letters, an empty box) shows a friendly message in RED in the
//      result label. It must never crash, or print a stack trace.
//   4. Changing the conversion in the combo box converts again straight away
//      (combo.addActionListener).
//   5. "Swap" puts the RESULT into the input field and switches to the reverse
//      conversion: the one whose fromUnit and toUnit are the other way round.
//      (So every conversion in the list needs its reverse in the list too.)
//   6. Build the window inside buildUi(), and start it with SwingUtilities.invokeLater.
//
// Run it with:  java Exercise1.java

import java.awt.*;
import java.util.function.DoubleUnaryOperator;
import javax.swing.*;

public class Exercise1 {

    /** A conversion: its name, its units, and how to convert. */
    record Conversion(String name, String fromUnit, String toUnit, DoubleUnaryOperator convert) {
        @Override
        public String toString() {
            return name;            // JComboBox shows each item's toString()
        }
    }

    static final Conversion[] CONVERSIONS = {
        new Conversion("kilometres -> miles", "kilometres", "miles", km -> km * 0.621371),
        new Conversion("miles -> kilometres", "miles", "kilometres", mi -> mi / 0.621371),
        // TODO: at least three more conversions (and their reverses)
    };

    static JPanel buildUi() {
        JPanel panel = new JPanel();
        // TODO: the combo box, the text field, the two buttons, the result label,
        //       and the event listeners. (Ex04TextInput is a good model.)
        panel.add(new JLabel("TODO"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Unit converter");
            window.add(buildUi());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
