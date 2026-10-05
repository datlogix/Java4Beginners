// Example 9: mouse events: a sketch pad. Drag to draw; right-click to change colour.
// Run it with:  java Ex09MouseEvents.java

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Ex09MouseEvents {

    record Stroke(Color colour, List<Point> points) { }

    static class SketchPad extends JPanel {
        private final List<Stroke> strokes = new ArrayList<>();
        private final Color[] colours = {Color.BLACK, Color.RED, new Color(0, 140, 0), Color.BLUE};
        private int colourIndex = 0;

        SketchPad() {
            setPreferredSize(new Dimension(640, 420));
            setBackground(Color.WHITE);

            // MouseAdapter has empty versions of every mouse method: override the ones you need
            MouseAdapter mouse = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        colourIndex = (colourIndex + 1) % colours.length;
                    } else {
                        strokes.add(new Stroke(colours[colourIndex], new ArrayList<>(List.of(e.getPoint()))));
                    }
                    repaint();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (!strokes.isEmpty() && SwingUtilities.isLeftMouseButton(e)) {
                        strokes.get(strokes.size() - 1).points().add(e.getPoint());
                        repaint();
                    }
                }
            };
            addMouseListener(mouse);          // presses, releases, clicks
            addMouseMotionListener(mouse);    // movement and dragging
        }

        void clear() {
            strokes.clear();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D pen = (Graphics2D) g;
            pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            pen.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (Stroke s : strokes) {
                pen.setColor(s.colour());
                for (int i = 1; i < s.points().size(); i++) {
                    Point a = s.points().get(i - 1), b = s.points().get(i);
                    pen.drawLine(a.x, a.y, b.x, b.y);
                }
            }
            pen.setColor(colours[colourIndex]);
            pen.fillRect(10, 10, 20, 20);
            pen.setColor(Color.GRAY);
            pen.drawString("drag to draw, right-click for the next colour", 40, 25);
        }
    }

    static JPanel buildUi() {
        SketchPad pad = new SketchPad();
        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> pad.clear());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(pad, BorderLayout.CENTER);
        panel.add(clear, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Sketch pad");
            window.add(buildUi());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
