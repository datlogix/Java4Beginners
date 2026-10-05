package com.makerspace.fx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Example 12: putting it together. A to-do list whose DATA (the model) is kept
 * separate from the WINDOW (the view). TaskList knows nothing about JavaFX, so
 * it could be unit tested (Module 17). Tasks are saved to todo.txt after every change.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex12ToDoList
 */
public class Ex12ToDoList extends Application {

    static final Path FILE = Path.of("todo.txt");

    /** The model: just data and rules. No JavaFX in here. */
    static class TaskList {
        private final List<String> tasks = new ArrayList<>();

        void add(String task) {
            if (task == null || task.isBlank()) {
                throw new IllegalArgumentException("A task can't be blank.");
            }
            tasks.add(task.trim());
        }

        void remove(int index) { tasks.remove(index); }
        List<String> all() { return new ArrayList<>(tasks); }

        void load() throws IOException {
            if (Files.exists(FILE)) {
                tasks.addAll(Files.readAllLines(FILE));
            }
        }

        void save() throws IOException {
            Files.write(FILE, tasks);
        }
    }

    private final TaskList model = new TaskList();

    @Override
    public void start(Stage stage) {
        try {
            model.load();
        } catch (IOException e) {
            error("Couldn't load " + FILE + ": " + e.getMessage());
        }
        ObservableList<String> shown = FXCollections.observableArrayList(model.all());   // what the view displays
        ListView<String> list = new ListView<>(shown);
        list.setStyle("-fx-font-size: 16;");

        TextField input = new TextField();
        input.setPromptText("A new task");
        HBox.setHgrow(input, Priority.ALWAYS);
        Button add = new Button("Add");
        Button done = new Button("Done (remove)");
        done.disableProperty().bind(list.getSelectionModel().selectedItemProperty().isNull());

        Runnable addTask = () -> {
            try {
                model.add(input.getText());              // the MODEL checks the rules
                shown.add(input.getText().trim());       // then the view shows it
                input.clear();
                model.save();
            } catch (IllegalArgumentException | IOException ex) {
                error(ex.getMessage());
            }
        };
        add.setOnAction(e -> addTask.run());
        input.setOnAction(e -> addTask.run());
        done.setOnAction(e -> {
            int i = list.getSelectionModel().getSelectedIndex();
            model.remove(i);
            shown.remove(i);
            try {
                model.save();
            } catch (IOException ex) {
                error("Couldn't save: " + ex.getMessage());
            }
        });

        BorderPane root = new BorderPane(list, new HBox(8, input, add), null, done, null);
        root.setPadding(new Insets(10));
        BorderPane.setMargin(list, new Insets(10, 0, 10, 0));
        stage.setTitle("To-do list");
        stage.setScene(new Scene(root, 460, 380));
        stage.show();
    }

    private static void error(String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
