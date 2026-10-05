package com.makerspace.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Example 9: two ways to draw.
 *   LEFT:  shapes as OBJECTS in the scene graph. Each one stays there, and can be
 *          changed, moved or clicked later. (Click them!)
 *   RIGHT: a Canvas, which you PAINT on, like Module 1's Graphics2D. To change
 *          the picture, clear it and paint again.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex09ShapesAndCanvas
 */
public class Ex09ShapesAndCanvas extends Application {

    @Override
    public void start(Stage stage) {
        // ---- Shapes in the scene graph ----
        Circle sun = new Circle(70, 70, 45, Color.GOLD);
        Rectangle box = new Rectangle(30, 150, 120, 80);
        box.setFill(Color.STEELBLUE);
        box.setArcWidth(20);
        box.setArcHeight(20);
        Polygon star = new Polygon();
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? 45 : 18, angle = Math.toRadians(-90 + i * 36);
            star.getPoints().addAll(210 + r * Math.cos(angle), 90 + r * Math.sin(angle));
        }
        star.setFill(Color.CRIMSON);
        Text caption = new Text(20, 280, "Click a shape");
        caption.setFont(Font.font(16));
        for (var shape : new javafx.scene.shape.Shape[]{sun, box, star}) {
            shape.setOnMouseClicked(e -> shape.setFill(Color.hsb(Math.random() * 360, 0.8, 0.9)));
        }
        Pane shapes = new Pane(sun, box, star, caption);
        shapes.setPrefSize(280, 300);

        // ---- A Canvas you paint on ----
        Canvas canvas = new Canvas(320, 260);
        Slider frequency = new Slider(1, 10, 2);
        frequency.valueProperty().addListener((p, o, n) -> drawWave(canvas.getGraphicsContext2D(), n.doubleValue()));
        drawWave(canvas.getGraphicsContext2D(), frequency.getValue());
        VBox right = new VBox(8, canvas, new Label("Frequency"), frequency);

        HBox root = new HBox(20, shapes, right);
        root.setPadding(new Insets(12));
        stage.setTitle("Shapes and a canvas");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Paints y = sin(frequency x) across the whole canvas. */
    static void drawWave(GraphicsContext pen, double frequency) {
        double w = pen.getCanvas().getWidth(), h = pen.getCanvas().getHeight(), mid = h / 2;
        pen.setFill(Color.WHITE);
        pen.fillRect(0, 0, w, h);                       // clear by painting over everything
        pen.setStroke(Color.LIGHTGRAY);
        pen.strokeLine(0, mid, w, mid);
        pen.setStroke(Color.web("#1E5AC8"));
        pen.setLineWidth(2.5);
        pen.beginPath();
        for (int x = 0; x <= w; x += 2) {
            double y = mid - Math.sin(x / w * 2 * Math.PI * frequency) * h * 0.4;
            if (x == 0) {
                pen.moveTo(x, y);
            } else {
                pen.lineTo(x, y);
            }
        }
        pen.stroke();
        pen.setFill(Color.BLACK);
        pen.fillText(String.format("y = sin(%.1f x)", frequency), 10, 20);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
