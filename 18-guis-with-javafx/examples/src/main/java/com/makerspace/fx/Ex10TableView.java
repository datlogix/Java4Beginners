package com.makerspace.fx;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Example 10: a TableView of records, with sorting (click a column heading),
 * a live search box, adding and deleting. The table shows an ObservableList:
 * a list that tells the table whenever it changes.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex10TableView
 */
public class Ex10TableView extends Application {

    record Student(String id, String name, String programme, int level, double gpa) { }

    @Override
    public void start(Stage stage) {
        ObservableList<Student> students = FXCollections.observableArrayList(
                new Student("S001", "Akua Mensah", "BME", 300, 3.85),
                new Student("S002", "Kwesi Appiah", "EEE", 200, 3.10),
                new Student("S003", "Nana Yeboah", "CSC", 100, 2.40),
                new Student("S004", "Esi Arthur", "BME", 200, 3.55),
                new Student("S005", "Kofi Boateng", "CSC", 200, 1.95),
                new Student("S006", "Yaa Owusu", "EEE", 300, 3.85));

        // Each column says how to get ITS value out of a row
        TableColumn<Student, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().id()));
        TableColumn<Student, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().name()));
        name.setPrefWidth(160);
        TableColumn<Student, String> programme = new TableColumn<>("Programme");
        programme.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().programme()));
        TableColumn<Student, Number> level = new TableColumn<>("Level");
        level.setCellValueFactory(row -> new SimpleIntegerProperty(row.getValue().level()));
        TableColumn<Student, Number> gpa = new TableColumn<>("GPA");
        gpa.setCellValueFactory(row -> new SimpleDoubleProperty(row.getValue().gpa()));

        // Search: a FilteredList shows only the rows that pass a test (a Predicate, Module 15)
        FilteredList<Student> filtered = new FilteredList<>(students, s -> true);
        TextField search = new TextField();
        search.setPromptText("Search by name or programme");
        search.setPrefColumnCount(20);
        search.textProperty().addListener((p, o, text) -> filtered.setPredicate(s ->
                s.name().toLowerCase().contains(text.toLowerCase())
                || s.programme().equalsIgnoreCase(text.trim())
                || text.isBlank()));

        // Sorting: a SortedList follows whatever column heading the user clicks
        SortedList<Student> sorted = new SortedList<>(filtered);
        TableView<Student> table = new TableView<>(sorted);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.getColumns().addAll(id, name, programme, level, gpa);

        Label count = new Label();
        count.textProperty().bind(javafx.beans.binding.Bindings.size(filtered).asString("%d shown"));

        Button delete = new Button("Delete selected");
        delete.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        delete.setOnAction(e -> {
            Student s = table.getSelectionModel().getSelectedItem();
            Alert sure = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + s.name() + "?", ButtonType.YES, ButtonType.NO);
            if (sure.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                students.remove(s);              // change the LIST; the table updates itself
            }
        });
        Button add = new Button("Add a student");
        add.setOnAction(e -> students.add(new Student(String.format("S%03d", students.size() + 1),
                "New Student", "EEE", 100, 0.0)));

        HBox top = new HBox(10, search, count);
        HBox bottom = new HBox(10, add, delete);
        BorderPane root = new BorderPane(table, top, null, bottom, null);
        root.setPadding(new Insets(10));
        BorderPane.setMargin(table, new Insets(10, 0, 10, 0));
        stage.setTitle("Students");
        stage.setScene(new Scene(root, 560, 380));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
