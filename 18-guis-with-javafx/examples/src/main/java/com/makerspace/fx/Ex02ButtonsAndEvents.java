package com.makerspace.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Example 2: buttons and events. Event-driven programming: instead of asking
 * questions in order, the program WAITS, and reacts to whatever the user does.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex02ButtonsAndEvents
 */
public class Ex02ButtonsAndEvents extends Application {

    private int count = 0;

    @Override
    public void start(Stage stage) {
        Label display = new Label("0");
        display.setFont(new Font(48));
        display.setMinWidth(120);
        display.setAlignment(Pos.CENTER);

        Button minus = new Button("-");
        Button plus = new Button("+");
        Button reset = new Button("Reset");

        // setOnAction: "when this button is clicked, run this lambda"
        plus.setOnAction(e -> {
            count++;
            display.setText(String.valueOf(count));
        });
        minus.setOnAction(e -> {
            count--;
            display.setText(String.valueOf(count));
        });
        reset.setOnAction(e -> {
            count = 0;
            display.setText("0");
            System.out.println("Reset clicked (events can print to the terminal too)");
        });

        HBox row = new HBox(10, minus, display, plus, reset);   // 10 pixels between them
        row.setAlignment(Pos.CENTER);
        row.setPadding(new Insets(20));

        stage.setTitle("Clicker");
        stage.setScene(new Scene(row));       // no size given: the window fits its contents
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
