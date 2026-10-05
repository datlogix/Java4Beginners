package com.makerspace.fx;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * Example 7: ready-made dialogs. showAndWait() waits for an answer, and gives
 * back an Optional (Module 15): empty if the user cancelled.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex07Dialogs
 */
public class Ex07Dialogs extends Application {

    @Override
    public void start(Stage stage) {
        // A message with an OK button
        new Alert(Alert.AlertType.INFORMATION, "Welcome to the dialog tour!").showAndWait();

        // A question with a text box
        TextInputDialog ask = new TextInputDialog();
        ask.setHeaderText("What's your name?");
        String name = ask.showAndWait().filter(s -> !s.isBlank()).orElse("friend");

        // Yes or no
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you like Java, " + name + "?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> answer = confirm.showAndWait();
        String reply = answer.isPresent() && answer.get() == ButtonType.YES ? "Excellent!" : "You will by Module 19.";

        // A choice from a list
        ChoiceDialog<String> music = new ChoiceDialog<>("Highlife", "Highlife", "Afrobeats", "Gospel");
        music.setHeaderText("Favourite music?");
        String choice = music.showAndWait().orElse("silence");

        new Alert(Alert.AlertType.INFORMATION, reply + "\nEnjoy some " + choice + ".").showAndWait();
        Alert error = new Alert(Alert.AlertType.ERROR, "This is what an error looks like.");
        error.setHeaderText("Oops");
        error.showAndWait();
        Platform.exit();             // no main window in this example, so end the program here
    }

    public static void main(String[] args) {
        launch(args);
    }
}
