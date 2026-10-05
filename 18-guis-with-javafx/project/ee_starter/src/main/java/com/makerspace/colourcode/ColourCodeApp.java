package com.makerspace.colourcode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Choose four colours and see the value; or type a value and see the colours.
 * Run it with:  ./mvnw javafx:run      (Windows: mvnw javafx:run)
 */
public class ColourCodeApp extends Application {

    @Override
    public void start(Stage stage) {
        ResistorView picture = new ResistorView();
        // TODO: four ComboBox<BandColour>, each listing only the colours allowed in that band;
        //       a big result label that updates whenever a combo box changes (and redraws
        //       the resistor); a "value -> colours" text field (4k7, 220, 1M) that sets the
        //       combo boxes; and the E12 check from the README
        VBox root = new VBox(12, picture, new Label("TODO: the band choosers and the result"));
        root.setPadding(new Insets(12));
        stage.setTitle("Resistor colour code");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
