package com.makerspace.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Example 5: a drop-down list, radio buttons, check boxes and a slider.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex05ChoiceControls
 */
public class Ex05ChoiceControls extends Application {

    @Override
    public void start(Stage stage) {
        ComboBox<String> room = new ComboBox<>();
        room.getItems().addAll("Electronics lab", "Robotics lab", "3D printing room");
        room.getSelectionModel().selectFirst();

        RadioButton morning = new RadioButton("Morning");
        RadioButton afternoon = new RadioButton("Afternoon");
        ToggleGroup session = new ToggleGroup();         // only one radio button in a group can be on
        morning.setToggleGroup(session);
        afternoon.setToggleGroup(session);
        morning.setSelected(true);

        CheckBox scope = new CheckBox("Oscilloscope");
        CheckBox solder = new CheckBox("Soldering station");

        Slider people = new Slider(1, 10, 2);
        people.setMajorTickUnit(1);
        people.setMinorTickCount(0);
        people.setSnapToTicks(true);
        people.setShowTickMarks(true);
        people.setShowTickLabels(true);

        Label summary = new Label();
        Button book = new Button("Book it");
        book.setOnAction(e -> summary.setText("Booked: " + room.getValue()
                + ", " + (morning.isSelected() ? "morning" : "afternoon")
                + ", " + (int) people.getValue() + " people"
                + (scope.isSelected() ? ", oscilloscope" : "")
                + (solder.isSelected() ? ", soldering station" : "")));

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(12);
        form.setPadding(new Insets(14));
        form.addRow(0, new Label("Room:"), room);
        form.addRow(1, new Label("Session:"), new HBox(12, morning, afternoon));
        form.addRow(2, new Label("Equipment:"), new HBox(12, scope, solder));
        form.addRow(3, new Label("People:"), people);
        form.addRow(4, book, summary);

        stage.setTitle("Lab booking");
        stage.setScene(new Scene(form, 600, 260));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
