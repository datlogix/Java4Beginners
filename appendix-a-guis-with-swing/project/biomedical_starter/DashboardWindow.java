// Run it with:
//     javac -d out *.java
//     java -cp out DashboardWindow
//
// Thresholds are simplified for teaching. This is not a clinical tool.

import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/** Enter vital signs, see their status at a glance, and watch the trend. */
public class DashboardWindow {

    static JPanel buildUi() {
        List<Reading> readings = new ArrayList<>();
        ChartPanel chart = new ChartPanel();
        // TODO: a form (heart rate, SpO2, temperature) with a Record button; three big
        //       status labels coloured green/red; a JList history of readings; the chart;
        //       a menu or buttons to save and load the readings (Module 14)
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(chart, BorderLayout.CENTER);
        panel.add(new JLabel("TODO: the form and the status labels", SwingConstants.CENTER), BorderLayout.NORTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Vitals dashboard");
            window.add(buildUi());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
