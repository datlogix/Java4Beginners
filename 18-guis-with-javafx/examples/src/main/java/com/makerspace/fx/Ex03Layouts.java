package com.makerspace.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Example 3: layout panes decide where things go, and how they stretch when the
 * window is resized. Resize this window and watch.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex03Layouts
 */
public class Ex03Layouts extends Application {

    @Override
    public void start(Stage stage) {
        // HBox: a row
        HBox toolbar = new HBox(8);
        for (String name : new String[]{"New", "Open", "Save", "Print"}) {
            toolbar.getChildren().add(new Button(name));
        }

        // GridPane: rows and columns. add(node, column, row)
        GridPane keypad = new GridPane();
        keypad.setHgap(6);
        keypad.setVgap(6);
        String[] keys = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "*", "0", "#"};
        for (int i = 0; i < keys.length; i++) {
            Button key = new Button(keys[i]);
            key.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);   // allowed to grow...
            GridPane.setHgrow(key, Priority.ALWAYS);              // ...and told to
            GridPane.setVgrow(key, Priority.ALWAYS);
            keypad.add(key, i % 3, i / 3);
        }

        // VBox: a column
        VBox options = new VBox(8, new Label("Colours"), new CheckBox("Red"), new CheckBox("Green"), new CheckBox("Blue"));

        // BorderPane: TOP, BOTTOM, LEFT, RIGHT and CENTER. The centre gets all the spare space.
        Label title = new Label("BorderPane: top");
        title.setFont(Font.font(null, 18));
        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(keypad);
        root.setRight(options);
        root.setLeft(new Label("left"));
        root.setBottom(toolbar);
        BorderPane.setAlignment(title, Pos.CENTER);
        for (var region : new javafx.scene.layout.Region[]{keypad, options, toolbar}) {
            BorderPane.setMargin(region, new Insets(8));
        }
        root.setPadding(new Insets(10));

        stage.setTitle("Layouts");
        stage.setScene(new Scene(root, 560, 420));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
