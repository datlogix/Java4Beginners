// Example 0: the hook. A whole tree from ONE method that calls ITSELF.
// Run it with:  java Ex00FractalTree.java
// Then change ANGLE (try 15, 35, 60) or SHRINK (try 0.6, 0.8) and run it again.

import java.awt.*;
import javax.swing.*;

public class Ex00FractalTree extends JPanel {

    static final double ANGLE = 25;     // how far each branch turns
    static final double SHRINK = 0.72;  // each branch is this fraction of its parent

    /** Draws a branch, then two smaller branches growing out of its tip. */
    void branch(Graphics2D pen, double x, double y, double length, double heading, int depth) {
        if (depth == 0) {
            return;                                          // BASE CASE: too small, stop
        }
        double endX = x + length * Math.cos(Math.toRadians(heading));
        double endY = y - length * Math.sin(Math.toRadians(heading));
        pen.setStroke(new BasicStroke(depth));
        pen.setColor(depth > 3 ? new Color(110, 70, 30) : new Color(40, 150 + depth * 20, 60));
        pen.drawLine((int) x, (int) y, (int) endX, (int) endY);

        branch(pen, endX, endY, length * SHRINK, heading + ANGLE, depth - 1);   // RECURSIVE CASE:
        branch(pen, endX, endY, length * SHRINK, heading - ANGLE, depth - 1);   // two smaller trees
    }

    void draw(Graphics2D pen) {
        branch(pen, 400, 580, 140, 90, 11);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw(pen);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Fractal tree");
        Ex00FractalTree canvas = new Ex00FractalTree();
        canvas.setBackground(new Color(235, 245, 255));
        canvas.setPreferredSize(new Dimension(800, 600));
        window.add(canvas);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        System.out.println("That tree has " + ((int) Math.pow(2, 11) - 1) + " branches.");
    }
}
