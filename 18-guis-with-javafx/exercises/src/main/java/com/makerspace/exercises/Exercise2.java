package com.makerspace.exercises;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Exercise 2, PART B: the calculator's window. When CalculatorModel passes all
 * its tests, build the window: a display Label at the top (right-aligned, a big
 * font) and a GridPane of Buttons:
 *
 *     7 8 9 /
 *     4 5 6 x
 *     1 2 3 -
 *     0 . = +
 *     C
 *
 * Every button calls model.press(its text), then shows model.display().
 * Stretch: let the keyboard work too (scene.setOnKeyTyped).
 *
 * Run it with:  ./mvnw javafx:run -Dmain=Exercise2
 */
public class Exercise2 extends Application {

    @Override
    public void start(Stage stage) {
        CalculatorModel model = new CalculatorModel();
        Label screen = new Label(model.display());
        screen.setFont(Font.font(32));
        screen.setMaxWidth(Double.MAX_VALUE);
        screen.setAlignment(Pos.CENTER_RIGHT);
        // TODO: the buttons, in a GridPane, in the centre of the BorderPane
        BorderPane root = new BorderPane();
        root.setTop(screen);
        stage.setTitle("Calculator");
        stage.setScene(new Scene(root, 300, 360));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
