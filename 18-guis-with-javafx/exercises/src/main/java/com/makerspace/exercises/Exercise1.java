package com.makerspace.exercises;

import java.util.function.DoubleUnaryOperator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Exercise 1: A unit converter window.
 *
 * Build this window:
 *
 *     +-----------------------------------------------+
 *     | Convert: [ kilometres -> miles      v ]       |
 *     | Value:   [ 42.195      ]  [Convert] [Swap]    |
 *     | 42.195 kilometres = 26.219 miles              |
 *     +-----------------------------------------------+
 *
 * Requirements:
 *   1. A ComboBox<Conversion> listing at least FIVE conversions, taken from the
 *      CONVERSIONS array (add at least three more, each with its reverse).
 *   2. Typing a number and pressing Convert, OR pressing Enter in the field,
 *      shows the result to 3 decimal places in the result label.
 *   3. Bad input (letters, an empty box) shows a friendly message in RED in the
 *      result label. It must never crash, or print a stack trace.
 *   4. Choosing a different conversion converts again straight away
 *      (combo.setOnAction).
 *   5. Swap puts the RESULT into the input field and switches the combo box to
 *      the reverse conversion: the one whose fromUnit and toUnit are the other
 *      way round.
 *   6. The Convert button is disabled while the text field is empty
 *      (a binding: Example 6).
 *
 * Run it with:  ./mvnw javafx:run      (Windows: mvnw javafx:run)
 */
public class Exercise1 extends Application {

    /** A conversion: its name, its units, and how to convert. */
    record Conversion(String name, String fromUnit, String toUnit, DoubleUnaryOperator convert) {
        @Override
        public String toString() {
            return name;            // a ComboBox shows each item's toString()
        }
    }

    static final Conversion[] CONVERSIONS = {
        new Conversion("kilometres -> miles", "kilometres", "miles", km -> km * 0.621371),
        new Conversion("miles -> kilometres", "miles", "kilometres", mi -> mi / 0.621371),
        // TODO: at least three more conversions (and their reverses)
    };

    @Override
    public void start(Stage stage) {
        // TODO: the combo box, the text field, the two buttons, the result label,
        //       the event handlers and the binding. (Ex04TextInput is a good model.)
        VBox root = new VBox(new Label("TODO"));
        stage.setTitle("Unit converter");
        stage.setScene(new Scene(root, 460, 140));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
