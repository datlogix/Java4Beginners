package com.makerspace.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Example 1: the smallest JavaFX program.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex01FirstWindow
 */
public class Ex01FirstWindow extends Application {

    @Override
    public void start(Stage stage) {               // JavaFX calls this, and hands you the window
        Label label = new Label("Akwaaba! This is a JavaFX window.");
        label.setFont(new Font(22));

        StackPane root = new StackPane(label);     // a layout pane: centres what's in it
        Scene scene = new Scene(root, 480, 120);   // the contents of the window

        stage.setTitle("My first window");         // the title bar
        stage.setScene(scene);
        stage.show();                              // windows start hidden!
    }

    public static void main(String[] args) {
        launch(args);                              // starts JavaFX, which then calls start()
    }
}
