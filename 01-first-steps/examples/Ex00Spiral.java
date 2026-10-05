// Example 0: the hook. A rainbow spiral drawn in a window.
// Run it with:  java Ex00Spiral.java
//
// Then change 59 (near the bottom of paintComponent) to 90, 121 or 144
// and run it again.

import java.awt.*;
import javax.swing.*;

public class Ex00Spiral extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        pen.setStroke(new BasicStroke(2));
        Color[] colours = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.MAGENTA};

        double x = 400, y = 400, heading = 0;
        for (int step = 0; step < 180; step++) {
            double newX = x + step * 2 * Math.cos(Math.toRadians(heading));
            double newY = y + step * 2 * Math.sin(Math.toRadians(heading));
            pen.setColor(colours[step % 6]);
            pen.drawLine((int) x, (int) y, (int) newX, (int) newY);
            x = newX;
            y = newY;
            heading = heading + 59;
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Spiral");
        Ex00Spiral canvas = new Ex00Spiral();
        canvas.setBackground(Color.BLACK);
        canvas.setPreferredSize(new Dimension(800, 800));
        window.add(canvas);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
