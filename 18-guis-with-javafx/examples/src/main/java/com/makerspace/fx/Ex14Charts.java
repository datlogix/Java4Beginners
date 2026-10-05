package com.makerspace.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Example 14: JavaFX has charts built in: line, bar, pie, scatter, area...
 * You give a chart its data as a list of points; it draws the axes, labels and legend.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex14Charts
 */
public class Ex14Charts extends Application {

    @Override
    public void start(Stage stage) {
        // A line chart: heart rate over a shift, for two patients
        NumberAxis hours = new NumberAxis(6, 22, 2);
        hours.setLabel("Time (hour)");
        NumberAxis bpm = new NumberAxis(50, 130, 10);
        bpm.setLabel("Heart rate (bpm)");
        LineChart<Number, Number> line = new LineChart<>(hours, bpm);
        line.setTitle("Heart rate");
        int[][] readings = {{72, 75, 71, 74, 70, 73, 72, 76, 74}, {88, 92, 97, 101, 106, 110, 114, 117, 121}};
        String[] patients = {"P001", "P002"};
        for (int p = 0; p < 2; p++) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(patients[p]);
            for (int i = 0; i < readings[p].length; i++) {
                series.getData().add(new XYChart.Data<>(6 + i * 2, readings[p][i]));
            }
            line.getData().add(series);
        }

        // A bar chart: revenue by category
        CategoryAxis categories = new CategoryAxis();
        NumberAxis cedis = new NumberAxis();
        cedis.setLabel("GHS");
        BarChart<String, Number> bar = new BarChart<>(categories, cedis);
        bar.setTitle("Revenue by category");
        bar.setLegendVisible(false);
        XYChart.Series<String, Number> revenue = new XYChart.Series<>();
        String[] names = {"Boards", "Components", "Motors", "Sensors", "Tools"};
        double[] values = {12405, 6580, 5096, 3496, 3780};
        for (int i = 0; i < names.length; i++) {
            revenue.getData().add(new XYChart.Data<>(names[i], values[i]));
        }
        bar.getData().add(revenue);

        // A pie chart
        PieChart pie = new PieChart();
        pie.setTitle("Students by programme");
        pie.getData().addAll(new PieChart.Data("EEE", 407), new PieChart.Data("BME", 375),
                new PieChart.Data("CSC", 429), new PieChart.Data("MEC", 394), new PieChart.Data("CIV", 395));

        GridPane root = new GridPane();
        root.add(line, 0, 0, 2, 1);
        root.add(bar, 0, 1);
        root.add(pie, 1, 1);
        stage.setTitle("Charts");
        stage.setScene(new Scene(root, 900, 700));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
