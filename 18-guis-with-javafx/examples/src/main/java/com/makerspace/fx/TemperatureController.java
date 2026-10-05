package com.makerspace.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The controller for temperature.fxml (Example 13). Fields marked @FXML are
 * filled in automatically with the controls whose fx:id has the same name, and
 * the convert() method is called by the button's onAction="#convert".
 */
public class TemperatureController {

    @FXML
    private TextField celsius;

    @FXML
    private Label result;

    @FXML
    private void convert() {
        try {
            double c = Double.parseDouble(celsius.getText().trim());
            result.getStyleClass().setAll("label", "good");
            result.setText(String.format("%.1f C = %.1f F", c, c * 9 / 5 + 32));
        } catch (NumberFormatException e) {
            result.getStyleClass().setAll("label", "bad");
            result.setText("That isn't a number");
        }
    }
}
