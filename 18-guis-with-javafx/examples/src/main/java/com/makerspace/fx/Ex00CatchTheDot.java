package com.makerspace.fx;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Example 0: the hook. Click the dot as many times as you can in 20 seconds.
 * Every catch makes it smaller, and it moves faster.
 * Run it with:  ./mvnw javafx:run      (Windows: mvnw javafx:run)
 */
public class Ex00CatchTheDot extends Application {

    private static final double WIDTH = 640, HEIGHT = 480;
    private final Random random = new Random();
    private final Circle dot = new Circle(40, Color.web("#FFBE28"));
    private final Label scoreLabel = new Label();
    private final Label timeLabel = new Label();
    private int score = 0, best = 0, secondsLeft = 20;
    private Timeline mover, clock;

    @Override
    public void start(Stage stage) {
        Pane board = new Pane(dot, scoreLabel, timeLabel);
        board.setStyle("-fx-background-color: #14263A;");
        for (Label l : new Label[]{scoreLabel, timeLabel}) {
            l.setFont(Font.font("SansSerif", 22));
            l.setTextFill(Color.WHITE);
            l.setLayoutY(12);
        }
        scoreLabel.setLayoutX(20);
        timeLabel.setLayoutX(WIDTH - 120);

        // The dot is an OBJECT in the window, so it can be clicked directly.
        dot.setOnMouseClicked(e -> {
            score++;
            dot.setRadius(Math.max(10, dot.getRadius() - 2));
            mover.setRate(mover.getRate() * 1.04);     // speed up a little
            jump();
            updateLabels();
        });

        // Every 900 ms the dot jumps; every second the clock ticks down.
        mover = new Timeline(new KeyFrame(Duration.millis(900), e -> jump()));
        mover.setCycleCount(Timeline.INDEFINITE);
        clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondsLeft--;
            updateLabels();
            if (secondsLeft == 0) {
                gameOver();
            }
        }));
        clock.setCycleCount(Timeline.INDEFINITE);

        jump();
        updateLabels();
        stage.setTitle("Catch the dot!");
        stage.setScene(new Scene(board, WIDTH, HEIGHT));
        stage.show();
        mover.play();
        clock.play();
    }

    private void jump() {
        double r = dot.getRadius();
        dot.setCenterX(r + random.nextDouble() * (WIDTH - 2 * r));
        dot.setCenterY(60 + r + random.nextDouble() * (HEIGHT - 60 - 2 * r));
    }

    private void updateLabels() {
        scoreLabel.setText("Score: " + score);
        timeLabel.setText("Time: " + secondsLeft);
    }

    private void gameOver() {
        mover.stop();
        clock.stop();
        best = Math.max(best, score);
        Alert again = new Alert(Alert.AlertType.CONFIRMATION,
                "You caught the dot " + score + " times!\nBest so far: " + best + "\n\nPlay again?",
                ButtonType.YES, ButtonType.NO);
        again.setHeaderText("Time's up");
        // A dialog can't wait for an answer while an animation is running, so show it "later".
        Platform.runLater(() -> {
            if (again.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                score = 0;
                secondsLeft = 20;
                dot.setRadius(40);
                mover.setRate(1);
                updateLabels();
                mover.play();
                clock.play();
            } else {
                Platform.exit();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
