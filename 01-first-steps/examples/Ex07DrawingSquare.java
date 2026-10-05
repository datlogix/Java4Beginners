// Example 7: drawing in a window.
// Run it with:  java Ex07DrawingSquare.java
//
// Everything you need to change is inside draw(). The code underneath it
// opens the window. Modules 10 and 11 explain most of it, and Appendix A
// explains every line; for now, copy the pattern.

import java.awt.*;
import javax.swing.*;

public class Ex07DrawingSquare extends JPanel {

    void draw(Graphics2D pen) {
        pen.setColor(Color.BLUE);
        pen.setStroke(new BasicStroke(6));       // a thicker pen
        pen.drawRect(100, 80, 200, 200);          // x, y, width, height

        pen.setColor(Color.ORANGE);
        pen.fillOval(150, 130, 100, 100);         // a filled circle inside the square

        pen.setColor(Color.RED);
        pen.drawLine(100, 330, 300, 330);         // a line from (100, 330) to (300, 330)

        pen.setColor(Color.BLACK);
        pen.setFont(new Font("SansSerif", Font.BOLD, 24));
        pen.drawString("My first drawing", 105, 370);
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
        JFrame window = new JFrame("Drawing");
        Ex07DrawingSquare canvas = new Ex07DrawingSquare();
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(400, 400));
        window.add(canvas);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
