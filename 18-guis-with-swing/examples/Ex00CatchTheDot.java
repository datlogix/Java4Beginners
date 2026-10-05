// Example 0: the hook. A game! Click the dot as many times as you can in
// 20 seconds. Every catch makes it smaller, and it moves faster.
// Run it with:  java Ex00CatchTheDot.java

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

public class Ex00CatchTheDot extends JPanel {

    private final Random random = new Random();
    private int dotX = 300, dotY = 220, radius = 40;
    private int score = 0, best = 0, secondsLeft = 20;
    private final Timer mover;
    private final Timer clock;

    Ex00CatchTheDot() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(new Color(20, 30, 50));

        // Every so often, the dot jumps somewhere new
        mover = new Timer(900, e -> {
            jump();
            repaint();
        });

        // Once a second, the clock ticks down
        clock = new Timer(1000, e -> {
            secondsLeft--;
            if (secondsLeft == 0) {
                gameOver();
            }
            repaint();
        });

        // When the mouse is pressed, check whether it hit the dot
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (secondsLeft == 0) {
                    return;
                }
                double distance = Math.hypot(e.getX() - dotX, e.getY() - dotY);
                if (distance <= radius) {
                    score++;
                    radius = Math.max(10, radius - 2);
                    mover.setDelay(Math.max(350, mover.getDelay() - 30));
                    jump();
                    repaint();
                }
            }
        });

        mover.start();
        clock.start();
    }

    private void jump() {
        dotX = radius + random.nextInt(getPreferredSize().width - 2 * radius);
        dotY = 60 + radius + random.nextInt(getPreferredSize().height - 60 - 2 * radius);
    }

    private void gameOver() {
        mover.stop();
        clock.stop();
        best = Math.max(best, score);
        int again = JOptionPane.showConfirmDialog(this,
                "You caught the dot " + score + " times!\nBest so far: " + best + "\n\nPlay again?",
                "Time's up", JOptionPane.YES_NO_OPTION);
        if (again == JOptionPane.YES_OPTION) {
            score = 0;
            secondsLeft = 20;
            radius = 40;
            mover.setDelay(900);
            mover.start();
            clock.start();
        } else {
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        pen.setColor(Color.WHITE);
        pen.setFont(new Font("SansSerif", Font.BOLD, 22));
        pen.drawString("Score: " + score, 20, 35);
        pen.drawString("Time: " + secondsLeft, getWidth() - 120, 35);
        pen.setColor(new Color(255, 190, 40));
        pen.fillOval(dotX - radius, dotY - radius, 2 * radius, 2 * radius);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Catch the dot!");
            window.add(new Ex00CatchTheDot());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
