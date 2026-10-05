package com.makerspace.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Example 11: animation. An AnimationTimer runs handle() about 60 times a second;
 * each time, move every ball a little. Click to add a ball.
 * Run it with:  ./mvnw javafx:run -Dmain=Ex11Animation
 */
public class Ex11Animation extends Application {

    /** A ball is a Circle (it's IN the scene) plus a velocity. */
    static class Ball {
        final Circle circle;
        double dx, dy;

        Ball(double x, double y, Random r) {
            circle = new Circle(x, y, 10 + r.nextInt(16), Color.hsb(r.nextDouble() * 360, 0.8, 0.95));
            dx = r.nextDouble() * 8 - 4;
            dy = r.nextDouble() * 8 - 4;
        }

        void move(double width, double height) {
            dy += 0.25;                                          // gravity
            circle.setCenterX(circle.getCenterX() + dx);
            circle.setCenterY(circle.getCenterY() + dy);
            double r = circle.getRadius();
            if (circle.getCenterX() < r || circle.getCenterX() > width - r) {
                dx = -dx;                                        // bounce off the sides
                circle.setCenterX(Math.max(r, Math.min(circle.getCenterX(), width - r)));
            }
            if (circle.getCenterY() > height - r) {
                dy = -dy * 0.85;                                 // bounce off the floor, losing energy
                circle.setCenterY(height - r);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Random random = new Random();
        Pane pit = new Pane();
        pit.setStyle("-fx-background-color: #F5F5FA;");
        Label count = new Label();
        pit.getChildren().add(count);
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Ball b = new Ball(100 + i * 70, 60, random);
            balls.add(b);
            pit.getChildren().add(b.circle);
        }
        pit.setOnMouseClicked(e -> {
            Ball b = new Ball(e.getX(), e.getY(), random);
            balls.add(b);
            pit.getChildren().add(b.circle);
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {        // runs every frame, on the JavaFX thread
                for (Ball b : balls) {
                    b.move(pit.getWidth(), pit.getHeight());
                }
                count.setText(balls.size() + " balls. Click to add one.");
            }
        }.start();
        // NEVER use Thread.sleep in JavaFX event code: the window would freeze.

        stage.setTitle("Bouncing balls");
        stage.setScene(new Scene(pit, 640, 420));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
