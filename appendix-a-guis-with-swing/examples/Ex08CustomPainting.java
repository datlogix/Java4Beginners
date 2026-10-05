// Example 8: a component that paints itself. This is the pattern you've used
// since Module 1, explained: a JPanel subclass that overrides paintComponent.
// The graph redraws whenever the slider moves.
// Run it with:  java Ex08CustomPainting.java

import java.awt.*;
import javax.swing.*;

public class Ex08CustomPainting {

    /** A graph of y = sin(frequency x). */
    static class WaveGraph extends JPanel {
        private double frequency = 1;

        WaveGraph() {
            setPreferredSize(new Dimension(600, 300));
            setBackground(Color.WHITE);
        }

        void setFrequency(double frequency) {
            this.frequency = frequency;
            repaint();                    // "please call paintComponent again, soon"
        }

        // Swing calls this whenever the panel needs drawing: when the window opens,
        // is resized or uncovered, or after repaint(). NEVER call it yourself.
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);      // paints the background first
            Graphics2D pen = (Graphics2D) g;
            pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight(), mid = h / 2;   // use the CURRENT size

            pen.setColor(Color.LIGHT_GRAY);
            pen.drawLine(0, mid, w, mid);
            pen.setColor(new Color(30, 90, 200));
            pen.setStroke(new BasicStroke(2.5f));
            int lastX = 0, lastY = mid;
            for (int x = 0; x < w; x += 2) {
                double angle = x / (double) w * 2 * Math.PI * frequency;
                int y = (int) (mid - Math.sin(angle) * (h * 0.4));
                if (x > 0) {
                    pen.drawLine(lastX, lastY, x, y);
                }
                lastX = x;
                lastY = y;
            }
            pen.setColor(Color.BLACK);
            pen.drawString(String.format("y = sin(%.1f x)", frequency), 10, 20);
        }
    }

    static JPanel buildUi() {
        WaveGraph graph = new WaveGraph();
        JSlider slider = new JSlider(1, 100, 10);
        slider.addChangeListener(e -> graph.setFrequency(slider.getValue() / 10.0));
        graph.setFrequency(1);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(graph, BorderLayout.CENTER);
        panel.add(slider, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Custom painting");
            window.add(buildUi());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
