// Example 0: the hook. ONE method that draws a house, called many times,
// with different sizes, places and colours, makes a whole village.
// Run it with:  java Ex00Village.java

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Ex00Village extends JPanel {

    /** Draws a house whose bottom-left corner is at (x, y). */
    static void drawHouse(Graphics2D pen, int x, int y, int size, Color wall) {
        pen.setColor(wall);
        pen.fillRect(x, y - size, size, size);                         // walls
        pen.setColor(new Color(150, 50, 30));
        int[] roofX = {x - size / 8, x + size / 2, x + size + size / 8};
        int[] roofY = {y - size, y - size - size * 2 / 3, y - size};
        pen.fillPolygon(roofX, roofY, 3);                              // roof
        pen.setColor(new Color(90, 55, 20));
        pen.fillRect(x + size * 2 / 5, y - size * 2 / 5, size / 5, size * 2 / 5);   // door
        pen.setColor(new Color(170, 220, 255));
        pen.fillRect(x + size / 8, y - size * 3 / 4, size / 5, size / 5);           // window
    }

    /** Draws a tree whose trunk stands on (x, y). */
    static void drawTree(Graphics2D pen, int x, int y, int height) {
        pen.setColor(new Color(110, 70, 30));
        pen.fillRect(x - height / 12, y - height / 2, height / 6, height / 2);
        pen.setColor(new Color(40, 140, 60));
        pen.fillOval(x - height / 3, y - height, height * 2 / 3, height * 2 / 3);
    }

    void draw(Graphics2D pen) {
        pen.setColor(new Color(120, 190, 90));
        pen.fillRect(0, 300, 800, 200);                                // grass
        pen.setColor(Color.YELLOW);
        pen.fillOval(680, 30, 70, 70);                                 // sun

        Random random = new Random(7);
        Color[] walls = {Color.WHITE, new Color(250, 220, 150), new Color(240, 180, 160), Color.LIGHT_GRAY};
        for (int i = 0; i < 9; i++) {
            int size = 40 + random.nextInt(50);
            drawHouse(pen, 20 + i * 85, 330 + random.nextInt(60), size, walls[i % walls.length]);
        }
        for (int i = 0; i < 6; i++) {
            drawTree(pen, 60 + i * 130, 470, 70 + random.nextInt(40));
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
        JFrame window = new JFrame("Village");
        Ex00Village canvas = new Ex00Village();
        canvas.setBackground(new Color(150, 200, 250));
        canvas.setPreferredSize(new Dimension(800, 500));
        window.add(canvas);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
