package com.makerspace.planner;

import java.nio.file.Path;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The study planner window.
 * Run it with:  ./mvnw javafx:run      (Windows: mvnw javafx:run)
 */
public class PlannerApp extends Application {

    private final TaskStore store = new TaskStore(Path.of("tasks.csv"));
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        TableView<Task> table = new TableView<>(tasks);
        TableColumn<Task, String> title = new TableColumn<>("Task");
        title.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().title()));
        title.setPrefWidth(220);
        table.getColumns().add(title);
        // TODO: columns for subject, due date, priority and done; load the tasks from the store
        //       (show a dialog if loading fails); a form to add a task (title, subject, a DatePicker,
        //       a Priority ComboBox); Mark done and Delete buttons; a filter (All / To do / Overdue);
        //       a progress chart; and SAVE after every change.

        BorderPane root = new BorderPane(table);
        root.setTop(new Label("TODO: the form"));
        root.setPadding(new Insets(10));
        stage.setTitle("Study planner");
        stage.setScene(new Scene(root, 820, 520));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
