// Example 12: loops + drawing. Every shape here comes from a for loop.
// Now you can read the Module 1 spiral hook line by line.
// Run it with:  java Ex12DrawingWithLoops.java

import java.awt.*;
import javax.swing.*;

public class Ex12DrawingWithLoops extends JPanel {

    void draw(Graphics2D pen) {
        // Concentric circles: each one 20 pixels bigger, centred on (200, 200)
        pen.setStroke(new BasicStroke(3));
        for (int r = 20; r <= 180; r += 20) {
            pen.setColor(r % 40 == 0 ? Color.ORANGE : Color.BLUE);
            pen.drawOval(200 - r, 200 - r, 2 * r, 2 * r);
        }

        // Eight squares, each one darker than the last (two rows of four)
        for (int i = 0; i < 8; i++) {
            pen.setColor(new Color(0, 0, 0, 30 + i * 30));    // the 4th number is transparency
            pen.fillRect(420 + (i % 4) * 45, 40 + (i / 4) * 45, 40, 40);
        }

        // A checkerboard: a loop inside a loop
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                pen.setColor((row + col) % 2 == 0 ? Color.BLACK : Color.RED);
                pen.fillRect(420 + col * 30, 160 + row * 30, 30, 30);
            }
        }
    }

    // ---- Window set-up: you don't need to change anything below this line ----

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw(pen);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Drawing with loops");
        Ex12DrawingWithLoops canvas = new Ex12DrawingWithLoops();
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(640, 400));
        window.add(canvas);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
