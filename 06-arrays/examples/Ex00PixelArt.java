// Example 0: the hook. A picture stored as a grid of numbers.
// Each number is a colour: 0 = background (black), 1 = green, 2 = white.
// Run it with:  java Ex00PixelArt.java
// Then change some numbers in the grid and run it again.

import java.awt.*;
import javax.swing.*;

public class Ex00PixelArt extends JPanel {

    int[][] grid = {
        {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 2, 1, 1, 1, 2, 1, 1, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
        {0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0},
    };
    Color[] palette = {Color.BLACK, new Color(60, 220, 90), Color.WHITE};
    int cellSize = 40;

    void draw(Graphics2D pen) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                pen.setColor(palette[grid[row][col]]);
                pen.fillRect(20 + col * cellSize, 20 + row * cellSize, cellSize - 2, cellSize - 2);
            }
        }
    }

    // ---- Window set-up: you don't need to change anything below this line ----

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw((Graphics2D) g);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Pixel art");
        Ex00PixelArt canvas = new Ex00PixelArt();
        canvas.setBackground(Color.BLACK);
        canvas.setPreferredSize(new Dimension(480, 360));
        window.add(canvas);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        // The same picture, printed in the terminal
        for (int[] row : canvas.grid) {
            for (int value : row) {
                System.out.print(value == 1 ? "##" : "  ");
            }
            System.out.println();
        }
    }
}
