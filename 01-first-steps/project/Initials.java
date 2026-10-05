// Module 1 Project: Your Initials in Java Art
// Author: YOUR NAME
//
// Follow the TODOs in order. Run the program after EACH one.
// Run it with:  java Initials.java

import java.awt.*;
import javax.swing.*;

public class Initials extends JPanel {

    void draw(Graphics2D pen) {
        pen.setStroke(new BasicStroke(12));   // a thick pen for block letters

        // --- Letter 1 ---
        // Example: this draws a letter "L". Replace it with your own first initial.
        pen.setColor(Color.BLUE);
        pen.drawLine(100, 80, 100, 260);      // the upright: straight down
        pen.drawLine(100, 260, 200, 260);     // the foot: straight right

        // --- Letter 2 ---
        // TODO: choose a different colour with pen.setColor(...)
        // TODO: draw your second letter, starting at about x = 260

        // --- Signature ---
        // TODO: choose a font with pen.setFont(new Font("SansSerif", Font.BOLD, 28))
        // TODO: write your full name under the letters with pen.drawString("Your Name", 100, 340)
    }

    // ---- Window set-up: you don't need to change anything below this line ----
    // (except the message in the TODO at the very end)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        pen.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        draw(pen);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("My Initials");
        Initials canvas = new Initials();
        canvas.setBackground(Color.WHITE);    // TODO (optional): try a different background colour
        canvas.setPreferredSize(new Dimension(700, 400));
        window.add(canvas);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        // TODO: print a message in the terminal saying the artwork is finished,
        //       e.g. "Artwork complete! Close the window to exit."
    }
}
