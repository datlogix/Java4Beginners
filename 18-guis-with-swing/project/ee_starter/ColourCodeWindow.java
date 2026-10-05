// Run it with:
//     javac -d out *.java
//     java -cp out ColourCodeWindow

import java.awt.*;
import javax.swing.*;

/** Choose four colours and see the value; or type a value and see the colours. */
public class ColourCodeWindow {

    static JPanel buildUi() {
        ResistorPanel picture = new ResistorPanel();
        // TODO: four JComboBox<BandColour>, each listing only the colours allowed in that band;
        //       a big result label ("4.7 kOhm +/-5%") that updates whenever a combo changes;
        //       a "value -> colours" text field (4k7, 220, 1M) that sets the combos;
        //       the E12 check from the README; and picture.setBands(...) to redraw
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(picture, BorderLayout.CENTER);
        panel.add(new JLabel("TODO: the band choosers and the result", SwingConstants.CENTER), BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Resistor colour code");
            window.add(buildUi());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
