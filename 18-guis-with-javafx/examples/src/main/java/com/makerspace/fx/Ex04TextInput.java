package com.makerspace.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Example 4: reading what the user types, and reporting problems IN the window.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex04TextInput
 */
public class Ex04TextInput extends Application {

    @Override
    public void start(Stage stage) {
        TextField celsius = new TextField("37");
        celsius.setPrefColumnCount(8);
        celsius.setPromptText("degrees C");        // grey hint text when empty
        Button convert = new Button("Convert");
        Label result = new Label(" ");
        result.setFont(Font.font(null, 18));

        Runnable doConvert = () -> {
            try {
                double c = Double.parseDouble(celsius.getText().trim());   // getText: what's typed
                result.setTextFill(Color.DARKGREEN);
                result.setText(String.format("%.1f C = %.1f F", c, c * 9 / 5 + 32));
            } catch (NumberFormatException e) {
                result.setTextFill(Color.RED);
                result.setText("'" + celsius.getText() + "' isn't a number");
            }
        };
        convert.setOnAction(e -> doConvert.run());
        celsius.setOnAction(e -> doConvert.run());        // pressing Enter in the field

        TextArea notes = new TextArea("A TextArea holds several lines.\nType notes here.");
        notes.setWrapText(true);
        notes.setPrefRowCount(5);                         // it scrolls by itself when it's full

        HBox row = new HBox(8, new Label("Celsius:"), celsius, convert);
        row.setAlignment(Pos.CENTER_LEFT);
        VBox root = new VBox(10, row, result, notes);
        root.setPadding(new Insets(12));

        stage.setTitle("Temperature converter");
        stage.setScene(new Scene(root, 420, 260));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
