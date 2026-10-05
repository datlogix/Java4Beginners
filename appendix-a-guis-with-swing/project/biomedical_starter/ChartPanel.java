import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/** A line chart of heart rate over time, with the normal range shaded. */
public class ChartPanel extends JPanel {
    private final List<Reading> readings = new ArrayList<>();

    public ChartPanel() {
        setPreferredSize(new Dimension(560, 260));
        setBackground(Color.WHITE);
    }

    public void setReadings(List<Reading> newReadings) {
        readings.clear();
        readings.addAll(newReadings);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int left = 50, top = 20, w = getWidth() - left - 20, h = getHeight() - top - 40;
        // axes
        pen.setColor(Color.DARK_GRAY);
        pen.drawLine(left, top, left, top + h);
        pen.drawLine(left, top + h, left + w, top + h);
        pen.drawString("Heart rate (bpm), 40-160", 5, top - 5);
        // TODO: shade the normal band (60-100 bpm) in light green; plot each reading
        //       as a point joined by lines (x spread evenly, y scaled from 40 to 160);
        //       draw abnormal points in red; label the x axis with the reading times
        if (readings.isEmpty()) {
            pen.drawString("No readings yet", left + w / 2 - 40, top + h / 2);
        }
    }
}
