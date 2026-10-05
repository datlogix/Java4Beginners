package com.makerspace.fx;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Example 8: a menu bar, keyboard shortcuts and file choosers: a tiny text editor.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex08MenusAndFiles
 */
public class Ex08MenusAndFiles extends Application {

    @Override
    public void start(Stage stage) {
        TextArea text = new TextArea();
        text.setFont(Font.font("Monospaced", 14));

        MenuItem open = new MenuItem("Open...");
        open.setAccelerator(KeyCombination.keyCombination("Shortcut+O"));   // Ctrl+O, or Cmd+O on a Mac
        open.setOnAction(e -> {
            File file = new FileChooser().showOpenDialog(stage);
            if (file != null) {                                              // null: the user cancelled
                try {
                    text.setText(Files.readString(file.toPath()));
                    stage.setTitle("Tiny editor - " + file.getName());
                } catch (IOException ex) {
                    new Alert(Alert.AlertType.ERROR, "Couldn't open it: " + ex.getMessage()).showAndWait();
                }
            }
        });
        MenuItem save = new MenuItem("Save as...");
        save.setAccelerator(KeyCombination.keyCombination("Shortcut+Shift+S"));
        save.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
            File file = chooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    Files.writeString(file.toPath(), text.getText());
                } catch (IOException ex) {
                    new Alert(Alert.AlertType.ERROR, "Couldn't save it: " + ex.getMessage()).showAndWait();
                }
            }
        });
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> stage.close());
        Menu file = new Menu("File");
        file.getItems().addAll(open, save, new SeparatorMenuItem(), quit);

        MenuItem count = new MenuItem("Word count");
        count.setOnAction(e -> {
            String t = text.getText().trim();
            int words = t.isEmpty() ? 0 : t.split("\\s+").length;
            new Alert(Alert.AlertType.INFORMATION, words + " words, " + t.length() + " characters").showAndWait();
        });
        CheckMenuItem wrap = new CheckMenuItem("Wrap lines");
        text.wrapTextProperty().bind(wrap.selectedProperty());       // a binding (Example 6)
        Menu tools = new Menu("Tools");
        tools.getItems().addAll(count, wrap);

        BorderPane root = new BorderPane(text);
        root.setTop(new MenuBar(file, tools));
        stage.setTitle("Tiny editor");
        stage.setScene(new Scene(root, 640, 420));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
