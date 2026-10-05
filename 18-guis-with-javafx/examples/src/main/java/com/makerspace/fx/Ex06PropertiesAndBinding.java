package com.makerspace.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Example 6: properties and binding, JavaFX's superpower. A property is a value
 * that tells you when it changes; a binding keeps one property in step with
 * another automatically, so you don't write the "update the label" code yourself.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex06PropertiesAndBinding
 */
public class Ex06PropertiesAndBinding extends Application {

    @Override
    public void start(Stage stage) {
        Slider size = new Slider(10, 100, 40);
        Circle circle = new Circle(40, Color.CORNFLOWERBLUE);
        Label sizeLabel = new Label();

        // 1. A BINDING: the circle's radius always equals the slider's value
        circle.radiusProperty().bind(size.valueProperty());

        // 2. A binding with a conversion: the label's text always shows the value
        sizeLabel.textProperty().bind(size.valueProperty().asString("Radius: %.0f pixels"));

        // 3. A LISTENER: run some code whenever a property changes
        size.valueProperty().addListener((property, oldValue, newValue) -> {
            circle.setFill(newValue.doubleValue() > 70 ? Color.TOMATO : Color.CORNFLOWERBLUE);
        });

        // 4. A button that's only enabled when the text field isn't empty
        TextField name = new TextField();
        name.setPromptText("Type your name to enable the button");
        Button hello = new Button("Say hello");
        hello.disableProperty().bind(name.textProperty().isEmpty());
        Label greeting = new Label();
        hello.setOnAction(e -> greeting.setText("Akwaaba, " + name.getText() + "!"));

        VBox root = new VBox(12, size, sizeLabel, circle, name, hello, greeting);
        root.setPadding(new Insets(14));
        stage.setTitle("Properties and binding");
        stage.setScene(new Scene(root, 360, 420));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
