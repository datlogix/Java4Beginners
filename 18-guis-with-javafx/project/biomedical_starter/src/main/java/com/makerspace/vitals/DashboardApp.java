package com.makerspace.vitals;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Enter vital signs, see their status at a glance, and watch the trend.
 * Run it with:  ./mvnw javafx:run      (Windows: mvnw javafx:run)
 */
public class DashboardApp extends Application {

    private final ObservableList<Reading> readings = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        NumberAxis x = new NumberAxis();
        x.setLabel("Reading number");
        NumberAxis y = new NumberAxis(40, 160, 20);
        y.setLabel("Heart rate (bpm)");
        LineChart<Number, Number> chart = new LineChart<>(x, y);
        chart.setTitle("Heart rate");
        XYChart.Series<Number, Number> heartRate = new XYChart.Series<>();
        heartRate.setName("HR");
        chart.getData().add(heartRate);
        // TODO: a form (heart rate, SpO2, temperature) with a Record button that adds a Reading
        //       at LocalTime.now() to readings AND a point to the chart; three big status labels
        //       coloured green or red for the latest reading; a history ListView or TableView;
        //       Save and Load (CSV, with a FileChooser) from a menu

        BorderPane root = new BorderPane(chart);
        root.setTop(new Label("TODO: the form and the status labels"));
        root.setPadding(new Insets(10));
        stage.setTitle("Vitals dashboard");
        stage.setScene(new Scene(root, 820, 560));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
