package com.makerspace.fx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Example 13: FXML and CSS. The window's LAYOUT is described in an XML file,
 * temperature.fxml (which Scene Builder can edit visually), its LOOK in a style
 * sheet, app.css, and its BEHAVIOUR in a controller class,
 * TemperatureController.java. All three files are in this project.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex13FxmlAndCss
 */
public class Ex13FxmlAndCss extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("temperature.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        stage.setTitle("FXML and CSS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
