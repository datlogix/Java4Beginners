// Example 10: animation with a javax.swing.Timer. Every 16 ms (about 60 times
// a second), move the balls a little and repaint. Click to add a ball.
// Run it with:  java Ex10Animation.java

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Ex10Animation {

    static class Ball {
        double x, y, dx, dy;
        final int size;
        final Color colour;

        Ball(double x, double y, Random r) {
            this.x = x;
            this.y = y;
            this.dx = r.nextDouble() * 8 - 4;
            this.dy = r.nextDouble() * 8 - 4;
            this.size = 20 + r.nextInt(30);
            this.colour = Color.getHSBColor(r.nextFloat(), 0.8f, 0.95f);
        }

        void move(int width, int height) {
            dy += 0.25;                       // gravity
            x += dx;
            y += dy;
            if (x < 0 || x + size > width) {  // bounce off the sides
                dx = -dx;
                x = Math.max(0, Math.min(x, width - size));
            }
            if (y + size > height) {          // bounce off the floor, losing a little energy
                dy = -dy * 0.85;
                y = height - size;
            }
        }
    }

    static class BallPit extends JPanel {
        private final List<Ball> balls = new ArrayList<>();
        private final Random random = new Random();

        BallPit() {
            setPreferredSize(new Dimension(640, 420));
            setBackground(new Color(245, 245, 250));
            for (int i = 0; i < 6; i++) {
                balls.add(new Ball(100 + i * 70, 50, random));
            }
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    balls.add(new Ball(e.getX(), e.getY(), random));
                }
            });
            // The Timer runs its action on Swing's own thread, so it's safe to change
            // the balls and repaint from here. (Never use Thread.sleep in Swing code.)
            new Timer(16, e -> {
                for (Ball b : balls) {
                    b.move(getWidth(), getHeight());
                }
                repaint();
            }).start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D pen = (Graphics2D) g;
            pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (Ball b : balls) {
                pen.setColor(b.colour);
                pen.fillOval((int) b.x, (int) b.y, b.size, b.size);
            }
            pen.setColor(Color.DARK_GRAY);
            pen.drawString(balls.size() + " balls. Click to add one.", 10, 20);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Bouncing balls");
            window.add(new BallPit());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
