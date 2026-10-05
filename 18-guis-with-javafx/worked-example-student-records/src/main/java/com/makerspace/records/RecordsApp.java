package com.makerspace.records;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The VIEW: a window onto the student database. It shows what the repository
 * returns, and passes the user's actions to it. It contains no SQL at all.
 *
 * Run it with:  ./mvnw javafx:run      (Windows: mvnw javafx:run)
 * The data is kept in students.db, in the folder you run it from.
 */
public class RecordsApp extends Application {

    private StudentRepository repository;
    private final ObservableList<Student> shown = FXCollections.observableArrayList();

    // The form
    private final TextField idField = new TextField();
    private final TextField nameField = new TextField();
    private final ComboBox<String> programmeBox = new ComboBox<>(FXCollections.observableArrayList(Student.PROGRAMMES));
    private final ComboBox<Integer> levelBox = new ComboBox<>(FXCollections.observableArrayList(Student.LEVELS));
    private final TextField gpaField = new TextField();
    private final TextField searchField = new TextField();
    private final Label status = new Label(" ");

    private final TableView<Student> table = new TableView<>(shown);
    private final XYChart.Series<String, Number> averages = new XYChart.Series<>();

    @Override
    public void start(Stage stage) {
        // Open the database FIRST. If that fails, there's nothing to show.
        try {
            repository = new StudentRepository("jdbc:sqlite:students.db");
            if (repository.count() == 0) {
                repository.addAll(sampleStudents());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Couldn't open students.db:\n" + e.getMessage()).showAndWait();
            return;   // no window: the program ends
        }

        BorderPane root = new BorderPane();
        root.setTop(buildSearchBar());
        root.setCenter(buildTable());
        root.setRight(buildForm());
        root.setBottom(buildChart());
        root.setPadding(new Insets(12));
        BorderPane.setMargin(table, new Insets(10, 10, 10, 0));

        refresh();
        stage.setTitle("Student records");
        stage.setScene(new Scene(root, 940, 640));
        stage.show();
    }

    /** Called by JavaFX when the window closes: close the database. */
    @Override
    public void stop() throws SQLException {
        if (repository != null) {
            repository.close();
        }
    }

    // ---------- Building the window ----------

    private HBox buildSearchBar() {
        Label title = new Label("Student records");
        title.setFont(Font.font("SansSerif", 22));
        searchField.setPromptText("Search: a name, an ID, or a programme");
        searchField.setPrefColumnCount(26);
        // Search again every time the text changes (a listener: Example 6)
        searchField.textProperty().addListener((obs, oldText, newText) -> refresh());
        HBox bar = new HBox(16, title, searchField);
        HBox.setHgrow(searchField, Priority.ALWAYS);
        return bar;
    }

    private TableView<Student> buildTable() {
        TableColumn<Student, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().id()));
        TableColumn<Student, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().name()));
        name.setPrefWidth(190);
        TableColumn<Student, String> programme = new TableColumn<>("Programme");
        programme.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().programme()));
        TableColumn<Student, Integer> level = new TableColumn<>("Level");
        level.setCellValueFactory(row -> new SimpleObjectProperty<>(row.getValue().level()));
        TableColumn<Student, String> gpa = new TableColumn<>("GPA");
        gpa.setCellValueFactory(row -> new SimpleStringProperty(String.format("%.2f", row.getValue().gpa())));
        table.getColumns().addAll(List.of(id, name, programme, level, gpa));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        // Choosing a row copies it into the form, ready to edit
        table.getSelectionModel().selectedItemProperty().addListener((obs, old, s) -> {
            if (s != null) {
                idField.setText(s.id());
                nameField.setText(s.name());
                programmeBox.setValue(s.programme());
                levelBox.setValue(s.level());
                gpaField.setText(String.valueOf(s.gpa()));
            }
        });
        return table;
    }

    private VBox buildForm() {
        idField.setPromptText("S001");
        gpaField.setPromptText("0 to 4");
        programmeBox.setPromptText("choose");
        levelBox.setPromptText("choose");

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.addRow(0, new Label("ID"), idField);
        grid.addRow(1, new Label("Name"), nameField);
        grid.addRow(2, new Label("Programme"), programmeBox);
        grid.addRow(3, new Label("Level"), levelBox);
        grid.addRow(4, new Label("GPA"), gpaField);

        Button add = new Button("Add");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button clear = new Button("Clear");
        add.setOnAction(e -> addStudent());
        update.setOnAction(e -> updateStudent());
        delete.setOnAction(e -> deleteStudent());
        clear.setOnAction(e -> clearForm());

        // Bindings (Example 6): Add needs an ID and a name; Update and Delete need a chosen row
        add.disableProperty().bind(idField.textProperty().isEmpty().or(nameField.textProperty().isEmpty()));
        update.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        delete.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());

        status.setWrapText(true);
        status.setMaxWidth(260);
        VBox form = new VBox(12, grid, new HBox(8, add, update, delete, clear), status);
        form.setPadding(new Insets(10, 0, 10, 0));
        return form;
    }

    private BarChart<String, Number> buildChart() {
        NumberAxis gpaAxis = new NumberAxis(0, 4, 0.5);
        gpaAxis.setLabel("Average GPA");
        BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), gpaAxis);
        chart.setTitle("Average GPA by programme");
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.setPrefHeight(220);
        chart.getData().add(averages);
        return chart;
    }

    // ---------- Actions: each one asks the repository, then refreshes ----------

    private void addStudent() {
        Student s = studentFromForm();
        if (s == null) {
            return;
        }
        try {
            if (repository.add(s)) {
                showOk("Added " + s.name() + ".");
                clearForm();
                refresh();
            } else {
                showProblem(s.id() + " is already taken. Choose the row to update it instead.");
            }
        } catch (SQLException e) {
            showProblem("The database refused: " + e.getMessage());
        }
    }

    private void updateStudent() {
        Student s = studentFromForm();
        if (s == null) {
            return;
        }
        try {
            if (repository.update(s)) {
                showOk("Saved " + s.name() + ".");
                refresh();
            } else {
                showProblem("There's no student " + s.id() + " to update. Use Add for a new student.");
            }
        } catch (SQLException e) {
            showProblem("The database refused: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        Student s = table.getSelectionModel().getSelectedItem();
        Alert sure = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + s.name() + " (" + s.id() + ")?");
        if (sure.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }
        try {
            repository.delete(s.id());
            showOk("Deleted " + s.name() + ".");
            clearForm();
            refresh();
        } catch (SQLException e) {
            showProblem("The database refused: " + e.getMessage());
        }
    }

    /** Reloads the table and the chart from the database. */
    private void refresh() {
        try {
            shown.setAll(repository.search(searchField.getText()));
            averages.getData().clear();
            for (Map.Entry<String, Double> e : repository.averageGpaByProgramme().entrySet()) {
                averages.getData().add(new XYChart.Data<>(e.getKey(), e.getValue()));
            }
        } catch (SQLException e) {
            showProblem("Couldn't read the database: " + e.getMessage());
        }
    }

    // ---------- Helpers ----------

    /** The form's contents as a Student, or null (with a message) if something's wrong. */
    private Student studentFromForm() {
        try {
            double gpa = Double.parseDouble(gpaField.getText().trim());
            Integer level = levelBox.getValue();
            return new Student(idField.getText().trim().toUpperCase(), nameField.getText(),
                    programmeBox.getValue(), level == null ? 0 : level, gpa);
        } catch (NumberFormatException e) {
            showProblem("The GPA must be a number, like 3.25");
        } catch (IllegalArgumentException e) {
            showProblem(e.getMessage());   // the Student record's own rules
        }
        return null;
    }

    private void clearForm() {
        table.getSelectionModel().clearSelection();
        idField.clear();
        nameField.clear();
        programmeBox.setValue(null);
        levelBox.setValue(null);
        gpaField.clear();
    }

    private void showOk(String message) {
        status.setTextFill(Color.DARKGREEN);
        status.setText(message);
    }

    private void showProblem(String message) {
        status.setTextFill(Color.FIREBRICK);
        status.setText(message);
    }

    private static List<Student> sampleStudents() {
        return List.of(
                new Student("S001", "Akua Mensah", "BME", 300, 3.85),
                new Student("S002", "Kwesi Appiah", "EEE", 200, 3.10),
                new Student("S003", "Abena Boateng", "CSC", 400, 3.62),
                new Student("S004", "Yaw Owusu", "MEC", 100, 2.45),
                new Student("S005", "Efua Sackey", "EEE", 300, 3.91),
                new Student("S006", "Kojo Asante", "CIV", 200, 2.88),
                new Student("S007", "Adwoa Mensah", "CSC", 100, 3.05),
                new Student("S008", "Nii Armah", "BME", 400, 2.70),
                new Student("S009", "Esi Darko", "CIV", 300, 3.40),
                new Student("S010", "Kofi Badu", "MEC", 200, 3.15));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
